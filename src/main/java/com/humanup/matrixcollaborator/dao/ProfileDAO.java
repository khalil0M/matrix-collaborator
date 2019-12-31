package com.humanup.matrixcollaborator.dao;
import com.humanup.matrixcollaborator.dao.entities.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProfileDAO extends CrudRepository<Profile, Long> {

    Profile findByProfileTitle(String profileTitle);
    List<Profile> findAll();
    Profile findByProfileId(long profileId);
    @Query("SELECT p FROM Profile p WHERE lower(p.profileTitle) like %:profileTitle% ")
    List<Profile> findListProfilesByTitle(String profileTitle);

}
