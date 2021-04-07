package com.seavus.oauth.service.impl;

import com.seavus.oauth.persistence.model.Task;
import com.seavus.oauth.persistence.repository.ITaskRepository;
import com.seavus.oauth.service.ITaskService;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements ITaskService {

    private ITaskRepository taskRepository;

    public TaskServiceImpl(ITaskRepository projectRepository) {
        this.taskRepository = projectRepository;
    }

    @Override
    public Iterable<Task> findByProjectId(Long id) {
        return taskRepository.findByProjectId(id);
    }
}