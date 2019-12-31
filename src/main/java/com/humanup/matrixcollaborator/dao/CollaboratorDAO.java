package com.humanup.matrixcollaborator.dao;

import com.humanup.matrixcollaborator.dao.entities.Collaborator;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CollaboratorDAO extends CrudRepository<Collaborator, Long> {

    Collaborator findByMailAdresse(String mailAdresse);
    List<Collaborator> findAll();
    @Query("SELECT c FROM Collaborator c WHERE lower(c.profile.profileTitle) like %:profileTitle% ")
    List<Collaborator> findListProfilesByProfileTitle(String profileTitle);

}
