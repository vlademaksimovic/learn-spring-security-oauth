package com.seavus.oauth.service;

import java.util.Optional;

import com.seavus.oauth.persistence.model.Project;

public interface IProjectService {
    Optional<Project> findById(Long id);

    Project save(Project project);

    Iterable<Project> findAll();

}
