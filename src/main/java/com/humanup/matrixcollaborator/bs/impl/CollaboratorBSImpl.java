package com.humanup.matrixcollaborator.bs.impl;
import com.humanup.matrixcollaborator.bs.CollaboratorBS;
import com.humanup.matrixcollaborator.dao.CollaboratorDAO;
import com.humanup.matrixcollaborator.dao.entities.Collaborator;
import com.humanup.matrixcollaborator.exceptions.CollaboratorException;
import com.humanup.matrixcollaborator.vo.CollaboratorVO;
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
    private CollaboratorDAO collaboratorDAO;

    @Override
    @Transactional(rollbackFor = CollaboratorException.class)
    public boolean createCollaborator(CollaboratorVO collaboratorVO) throws CollaboratorException {
        String email =  collaboratorVO.getMailAdresse();

        Collaborator collaboratorToSave =new Collaborator.Builder()
                .setMailAdresse(email)
                .build();
        return  collaboratorDAO.save(collaboratorToSave)!=null;
    }

    @Override
    public CollaboratorVO findCollaboratorByMailAdresse(String mailAdresse) {
        Optional<Collaborator> collaboratorFinded = Optional.ofNullable(collaboratorDAO.findByMailAdresse(mailAdresse));
        if(collaboratorFinded.isPresent()) {
            return new CollaboratorVO.Builder()
                    .setMailAdresse(collaboratorFinded.get().getMailAdresse())
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
                        .build())
                .collect(Collectors.toList());
    }

}
