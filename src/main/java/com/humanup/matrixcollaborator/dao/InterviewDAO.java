package com.humanup.matrixcollaborator.dao;

import com.humanup.matrixcollaborator.dao.entities.Interview;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface InterviewDAO extends CrudRepository<Interview, Long> {

    List<Interview> findAll();
    Interview findByInterviewId(long interviewId);
    Interview findByInterviewTitle(String interviewTitle);
    @Query("SELECT i FROM Interview i WHERE i.mailAdresse = :mailAdresse ")
    List<Interview> findListInterviewsByCollaboratorMailAdresse(String mailAdresse);

}
