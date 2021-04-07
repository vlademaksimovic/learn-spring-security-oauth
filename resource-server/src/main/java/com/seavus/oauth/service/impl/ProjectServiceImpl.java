package com.seavus.oauth.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import com.seavus.oauth.persistence.model.Project;
import com.seavus.oauth.service.IProjectService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.seavus.oauth.persistence.repository.IProjectRepository;

@Service
public class ProjectServiceImpl implements IProjectService {

    private IProjectRepository projectRepository;

    public ProjectServiceImpl(IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public Project save(Project project) {
        if (StringUtils.isEmpty(project.getId())) {
            project.setDateCreated(LocalDate.now());
        }
        return projectRepository.save(project);
    }

    @Override
    public Iterable<Project> findAll() {
        return projectRepository.findAll();
    }
}
