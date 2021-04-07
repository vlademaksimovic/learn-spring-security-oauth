package com.seavus.oauth.service;

import com.seavus.oauth.persistence.model.Task;

public interface ITaskService {

    Iterable<Task> findByProjectId(Long id);

}