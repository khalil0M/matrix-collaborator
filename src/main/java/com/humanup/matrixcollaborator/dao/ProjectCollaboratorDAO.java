package com.humanup.matrixcollaborator.dao;

import com.humanup.matrixcollaborator.dao.entities.Project;
import com.humanup.matrixcollaborator.dao.entities.ProjectCollaborator;
import com.humanup.matrixcollaborator.vo.ProjectCollaboratorVO;
import com.humanup.matrixcollaborator.vo.ProjectVO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProjectCollaboratorDAO extends CrudRepository<ProjectCollaborator, Long> {
    @Query("SELECT pc.project FROM ProjectCollaborator pc WHERE lower(pc.mailAdresse) = :mailAdresse")
    List<Project> findAllProjectBymailAdresse(String mailAdresse);
}
