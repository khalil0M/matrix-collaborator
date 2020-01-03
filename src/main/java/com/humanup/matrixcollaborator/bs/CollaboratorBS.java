package com.humanup.matrixcollaborator.bs;

import com.humanup.matrixcollaborator.exceptions.CollaboratorException;
import com.humanup.matrixcollaborator.exceptions.ProjectException;
import com.humanup.matrixcollaborator.vo.CollaboratorVO;

import java.util.List;

public interface CollaboratorBS {

    boolean createCollaborator(CollaboratorVO collaboratorVO) throws CollaboratorException;
    boolean addProjectsCollaborator(String email,List<Integer> projects) throws ProjectException;
    CollaboratorVO findCollaboratorByMailAdresse(String mailAdresse);
    List<CollaboratorVO> findListCollaborator();

}
