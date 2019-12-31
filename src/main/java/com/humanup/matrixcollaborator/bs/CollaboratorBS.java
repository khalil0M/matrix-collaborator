package com.humanup.matrixcollaborator.bs;

import com.humanup.matrixcollaborator.exceptions.ProfileException;
import com.humanup.matrixcollaborator.vo.CollaboratorVO;

import java.util.List;

public interface CollaboratorBS {

    boolean createCollaborator(CollaboratorVO collaboratorVO) throws ProfileException;
    CollaboratorVO findCollaboratorByMailAdresse(String mailAdresse);
    List<CollaboratorVO> findListCollaborator();
    List<CollaboratorVO> findListProfilesByProfileTitle(String profileTitle);

}
