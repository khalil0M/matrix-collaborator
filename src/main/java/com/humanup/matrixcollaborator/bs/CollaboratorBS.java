package com.humanup.matrixcollaborator.bs;

import com.humanup.matrixcollaborator.exceptions.CollaboratorException;
import com.humanup.matrixcollaborator.vo.CollaboratorVO;

import java.util.List;

public interface CollaboratorBS {

    boolean createCollaborator(CollaboratorVO collaboratorVO) throws CollaboratorException;
    CollaboratorVO findCollaboratorByMailAdresse(String mailAdresse);
    List<CollaboratorVO> findListCollaborator();

}
