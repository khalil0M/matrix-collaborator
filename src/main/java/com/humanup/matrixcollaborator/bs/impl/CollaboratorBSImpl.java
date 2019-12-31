package com.humanup.matrixcollaborator.bs.impl;
import com.humanup.matrixcollaborator.bs.CollaboratorBS;
import com.humanup.matrixcollaborator.dao.CollaboratorDAO;
import com.humanup.matrixcollaborator.dao.entities.Collaborator;
import com.humanup.matrixcollaborator.dao.entities.Profile;
import com.humanup.matrixcollaborator.exceptions.ProfileException;
import com.humanup.matrixcollaborator.vo.CollaboratorVO;
import com.humanup.matrixcollaborator.dao.ProfileDAO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class CollaboratorBSImpl implements CollaboratorBS {

    @Autowired
    private ProfileDAO profileDAO;

    @Autowired
    private CollaboratorDAO collaboratorDAO;

    @Override
    @Transactional(rollbackFor = ProfileException.class)
    public boolean createCollaborator(CollaboratorVO collaboratorVO) throws ProfileException {
        Profile profile = profileDAO.findByProfileTitle(collaboratorVO.getProfile());
        String email =  collaboratorVO.getMailAdresse();

        if(null == profile || null == email || StringUtils.isEmpty(email)){
            throw new ProfileException();
        }
        Collaborator collaboratorToSave =new Collaborator.Builder()
                .setMailAdresse(email)
                .setFirstName(collaboratorVO.getFirstName())
                .setLastName(collaboratorVO.getLastName())
                .setBirthDate(collaboratorVO.getBirthDate())
                .setProfile(profile)
                .build();
        return  collaboratorDAO.save(collaboratorToSave)!=null;
    }

    @Override
    public CollaboratorVO findCollaboratorByMailAdresse(String mailAdresse) {
        Optional<Collaborator> collaboratorFinded = Optional.ofNullable(collaboratorDAO.findByMailAdresse(mailAdresse));
        if(collaboratorFinded.isPresent()) {
            return new CollaboratorVO.Builder()
                    .setMailAdresse(collaboratorFinded.get().getMailAdresse())
                    .setBirthDate(collaboratorFinded.get().getBirthDate())
                    .setFirstName(collaboratorFinded.get().getFirstName())
                    .setLastName(collaboratorFinded.get().getLastName())
                    .setProfile(collaboratorFinded.get().getProfile().getProfileTitle())
                    .build();
        }
        return null;
    }

    @Override
    public List<CollaboratorVO> findListCollaborator() {
        return collaboratorDAO.findAll()
                .stream()
                .map(collaboratorFinded -> new CollaboratorVO.Builder()
                        .setMailAdresse(collaboratorFinded.getMailAdresse())
                        .setBirthDate(collaboratorFinded.getBirthDate())
                        .setFirstName(collaboratorFinded.getFirstName())
                        .setLastName(collaboratorFinded.getLastName())
                        .setProfile(collaboratorFinded.getProfile().getProfileTitle())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<CollaboratorVO> findListProfilesByProfileTitle(String profileTitle) {
        return collaboratorDAO.findListProfilesByProfileTitle(profileTitle)
                .stream()
                .map(collaboratorFinded -> new CollaboratorVO.Builder()
                        .setMailAdresse(collaboratorFinded.getMailAdresse())
                        .setBirthDate(collaboratorFinded.getBirthDate())
                        .setFirstName(collaboratorFinded.getFirstName())
                        .setLastName(collaboratorFinded.getLastName())
                        .setProfile(collaboratorFinded.getProfile().getProfileTitle())
                        .build())
                .collect(Collectors.toList());
    }
}
