package com.seavus.oauth.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedGrantedAuthoritiesUserDetailsService;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

@Configuration
public class TaskResourceSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public AbstractPreAuthenticatedProcessingFilter preAuthFilter() throws Exception {
        RequestHeaderAuthenticationFilter preAuthFilter = new RequestHeaderAuthenticationFilter();
        preAuthFilter.setPrincipalRequestHeader("username");
        preAuthFilter.setAuthenticationManager(authenticationManager());
        TaskResourceCustomAuthenticationDetailsSource authDetailsSource = new TaskResourceCustomAuthenticationDetailsSource();
        preAuthFilter.setAuthenticationDetailsSource(authDetailsSource);
        return preAuthFilter;
    }

    @Bean
    public AuthenticationProvider preAuthAuthenticationProvider() {
        PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
        provider.setPreAuthenticatedUserDetailsService(new PreAuthenticatedGrantedAuthoritiesUserDetailsService());
        return provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {// @formatter:off
        http.addFilterAt(preAuthFilter(), AbstractPreAuthenticatedProcessingFilter.class)
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/tasks/**")
                .hasAuthority("SCOPE_read")
                .antMatchers(HttpMethod.POST, "/api/tasks")
                .hasAuthority("SCOPE_write")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }//@formatter:on

}