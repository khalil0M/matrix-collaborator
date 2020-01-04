package com.humanup.matrixcollaborator.bs.impl;
import com.humanup.matrixcollaborator.bs.CollaboratorBS;
import com.humanup.matrixcollaborator.bs.ProjectBS;
import com.humanup.matrixcollaborator.dao.CollaboratorDAO;
import com.humanup.matrixcollaborator.dao.ProjectDAO;
import com.humanup.matrixcollaborator.dao.entities.Collaborator;
import com.humanup.matrixcollaborator.dao.entities.Project;
import com.humanup.matrixcollaborator.exceptions.CollaboratorException;
import com.humanup.matrixcollaborator.exceptions.ProjectException;
import com.humanup.matrixcollaborator.vo.CollaboratorVO;
import com.humanup.matrixcollaborator.vo.ProjectVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class CollaboratorBSImpl implements CollaboratorBS {


    @Autowired
    private CollaboratorDAO collaboratorDAO;

    @Autowired
    private ProjectDAO projectDAO;

    @Override
    @Transactional(rollbackFor = CollaboratorException.class)
    public boolean createCollaborator(CollaboratorVO collaboratorVO) throws CollaboratorException {
        String email =  collaboratorVO.getMailAdresse();

        Collaborator collaboratorToSave = Collaborator.builder()
                .mailAdresse(collaboratorVO.getMailAdresse())
                .build();
        return  collaboratorDAO.save(collaboratorToSave)!=null;
    }

    @Override
    @Transactional(rollbackFor = ProjectException.class)
    public boolean addProjectsCollaborator(String email,List<Integer> projects) throws ProjectException {
        Collaborator collaboratorToUpdate = collaboratorDAO.findByMailAdresse(email);
        if(null == collaboratorToUpdate || null == email || StringUtils.isEmpty(email)){
            throw new ProjectException();
        }
        Set<Project> collected = projects.stream().map(id -> projectDAO.findByProjectId(id)).collect(Collectors.toSet());
        Set<Project> collaboratorProjects = collaboratorToUpdate.getProjects();
        if(!collaboratorProjects.isEmpty()){
            collaboratorProjects.addAll(collected);
        }else{
            collaboratorToUpdate.setProjects(collected);
        }
        return collaboratorDAO.save(collaboratorToUpdate)!=null;
    }

    @Override
    public CollaboratorVO findCollaboratorByMailAdresse(String mailAdresse) {
        Optional<Collaborator> collaboratorFinded = Optional.ofNullable(collaboratorDAO.findByMailAdresse(mailAdresse));
        if(collaboratorFinded.isPresent()) {
            return  CollaboratorVO.builder()
                    .mailAdresse(collaboratorFinded.get().getMailAdresse())
                    .projectVOList(collaboratorFinded.get().getProjects().stream()
                    .map(project ->  ProjectVO.builder()
                    .projectTitle(project.getProjectTitle())
                    .projectDescription(project.getProjectDescription()).build())
                    .collect(Collectors.toList()))
                    .build();
        }
        return null;
    }

    @Override
    public List<CollaboratorVO> findListCollaborator() {
        return collaboratorDAO.findAll()
                .stream()
                .map(collaboratorFinded -> CollaboratorVO.builder()
                .mailAdresse(collaboratorFinded.getMailAdresse())
                .projectVOList(collaboratorFinded.getProjects().stream()
                .map(project ->  ProjectVO.builder()
                .projectTitle(project.getProjectTitle())
                .projectDescription(project.getProjectDescription()).build())
                .collect(Collectors.toList()))
                .build())
                .collect(Collectors.toList());
    }

}
