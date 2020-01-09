package com.humanup.matrixcollaborator.bs.impl;

import com.humanup.matrixcollaborator.bs.ProjectBS;
import com.humanup.matrixcollaborator.dao.InterviewDAO;
import com.humanup.matrixcollaborator.dao.ProjectDAO;
import com.humanup.matrixcollaborator.dao.entities.Interview;
import com.humanup.matrixcollaborator.dao.entities.Project;
import com.humanup.matrixcollaborator.vo.InterviewVO;
import com.humanup.matrixcollaborator.vo.ProjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ProjectBSImpl implements ProjectBS {

    @Autowired
    private ProjectDAO projectDAO;

    @Override
    public boolean createProject(ProjectVO projectVO) {
        Project typeToSave =  Project.builder()
                .projectTitle(projectVO.getProjectTitle())
                .projectDescription(projectVO.getProjectDescription())
                .build();
        return projectDAO.save(typeToSave) != null;
    }

    @Override
    public ProjectVO findProjectByTitle(String projectTitle) {
        Optional<Project> projectFinded = Optional.ofNullable(projectDAO.findByProjectTitle(projectTitle));
        if(projectFinded.isPresent()) {
            return  ProjectVO.builder()
                    .projectTitle(projectFinded.get().getProjectTitle())
                    .projectDescription(projectFinded.get().getProjectDescription())
                    .build();
        }
        return null;
    }

    @Override
    public List<ProjectVO> findListProject() {
        return projectDAO.findAll()
                .stream()
                .map(projectFinded ->  ProjectVO.builder()
                        .projectTitle(projectFinded.getProjectTitle())
                        .projectDescription(projectFinded.getProjectDescription())
                        .build())
                .collect(Collectors.toList());
    }
}
