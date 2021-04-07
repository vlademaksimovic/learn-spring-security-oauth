package com.seavus.oauth.persistence.repository;

import com.seavus.oauth.persistence.model.Task;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ITaskRepository extends PagingAndSortingRepository<Task, Long> {

    Iterable<Task> findByProjectId(Long id);
}