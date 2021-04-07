package com.seavus.oauth.web.controller;

import com.seavus.oauth.web.model.ProjectModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
public class ProjectClientController {

    @Value("${resourceserver.api.project.url:http://localhost:8081/resource-server/api/projects/}")
    private String projectApiUrl;

    @Autowired
    private WebClient webClient;

    @GetMapping("/projects")
    public List getProjects(Model model) {
        return this.webClient.get()
            .uri(projectApiUrl)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<List<ProjectModel>>() {
            })
            .block();
    }

}
