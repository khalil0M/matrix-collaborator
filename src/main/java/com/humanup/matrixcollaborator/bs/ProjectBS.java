package com.humanup.matrixcollaborator.bs;

import com.humanup.matrixcollaborator.aop.dto.ProjectCollaboratorException;
import com.humanup.matrixcollaborator.aop.dto.ProjectException;
import com.humanup.matrixcollaborator.vo.ProjectCollaboratorVO;
import com.humanup.matrixcollaborator.vo.ProjectVO;

import java.util.List;

public interface ProjectBS {

    boolean createProjectCollaborator(ProjectCollaboratorVO projectCollaboratorVO) throws ProjectCollaboratorException;
    boolean createProject(ProjectVO projectVO) throws ProjectException;
    ProjectVO findProjectByTitle(String projectTitle);
    List<ProjectVO> findListProject();
    List<ProjectVO> findAllProjectBymailAdresse(String mailAdresse);

}
