package com.humanup.matrixcollaborator.dao;

import com.humanup.matrixcollaborator.dao.entities.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProjectDAO  extends CrudRepository<Project, Long> {

    List<Project> findAll();
    Project findByProjectId(long projectId);
    Project findByProjectTitle(String projectTitle);

}