package com.seavus.oauth.web.controller;

import com.seavus.oauth.web.model.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Controller
public class TaskClientController {

    @Value("${gateway.url:http://localhost:8081/gateway/}" + "tasks/")
    private String taskApiUrl;

    @Autowired
    private WebClient webClient;

    @GetMapping("/tasks")
    public String getTasks(Model model, @RequestParam("projectId") String projectId) {
        List<TaskModel> tasks = this.webClient.get()
                .uri(taskApiUrl + "?projectId=" + projectId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<TaskModel>>() {
                })
                .block();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

}
