package com.humanup.matrixcollaborator.bs.impl;

import com.humanup.matrixcollaborator.aop.dto.ProjectCollaboratorException;
import com.humanup.matrixcollaborator.aop.dto.ProjectException;
import com.humanup.matrixcollaborator.bs.ProjectBS;
import com.humanup.matrixcollaborator.bs.impl.sender.RabbitMQProjectSender;
import com.humanup.matrixcollaborator.dao.ProjectCollaboratorDAO;
import com.humanup.matrixcollaborator.dao.ProjectDAO;
import com.humanup.matrixcollaborator.dao.entities.Project;
import com.humanup.matrixcollaborator.vo.ProjectCollaboratorVO;
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

    @Autowired
    private ProjectCollaboratorDAO projectCollaboratorDAO;

    @Autowired
    RabbitMQProjectSender rabbitMQProjectSender;

    @Override
    @Transactional(transactionManager="transactionManagerWrite",rollbackFor = ProjectException.class)
    public boolean createProject(ProjectVO projectVO) throws ProjectException {
        if (null == projectVO) throw new ProjectException();
        rabbitMQProjectSender.send(projectVO);
        return true;
    }

    @Override
    @Transactional(transactionManager="transactionManagerWrite",rollbackFor = ProjectCollaboratorException.class)
    public boolean createProjectCollaborator(ProjectCollaboratorVO projectCollaboratorVO) throws ProjectCollaboratorException {
        if (null == projectCollaboratorVO) throw new ProjectCollaboratorException();
        rabbitMQProjectSender.send(projectCollaboratorVO);
        return true;
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

    @Override
    public List<ProjectVO> findAllProjectBymailAdresse(String mailAdresse) {
        return projectCollaboratorDAO.findAllProjectBymailAdresse(mailAdresse).stream()
                .map(
                        projectFinded ->
                                ProjectVO.builder()
                                        .projectTitle(projectFinded.getProjectTitle())
                                        .projectDescription(projectFinded.getProjectDescription())
                                        .build())
                .collect(Collectors.toList());
    }

}
