package com.humanup.matrixcollaborator.bs;

import com.humanup.matrixcollaborator.vo.ProjectVO;

import java.util.List;

public interface ProjectBS {

    boolean createProject(ProjectVO projectVO);
    ProjectVO findProjectByTitle(String projectTitle);
    List<ProjectVO> findListProject();

}
