package com.seavus.oauth.spring;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedGrantedAuthoritiesWebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.stream.Collectors;

public class ProjectResourceCustomAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, GrantedAuthoritiesContainer> {

    @Override
    public GrantedAuthoritiesContainer buildDetails(HttpServletRequest request) {
        Enumeration<String> principal = request.getHeaders("authorities");
        Collection<GrantedAuthority> authorities = Collections.list(principal)
                .stream()
                .map(value -> new SimpleGrantedAuthority(value))
                .collect(Collectors.toList());
        return new PreAuthenticatedGrantedAuthoritiesWebAuthenticationDetails(request, authorities);
    }
}
