package com.seavus.oauth.persistence.repository;

import com.seavus.oauth.persistence.model.Project;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IProjectRepository extends PagingAndSortingRepository<Project, Long> {
}
