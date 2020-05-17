package com.humanup.matrixcollaborator.controllers;

import com.humanup.matrixcollaborator.aop.dto.ProjectCollaboratorException;
import com.humanup.matrixcollaborator.aop.dto.ProjectException;
import com.humanup.matrixcollaborator.bs.ProjectBS;
import com.humanup.matrixcollaborator.vo.ProjectCollaboratorVO;
import com.humanup.matrixcollaborator.vo.ProjectVO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProjectController {

    @Autowired
    private ProjectBS projectBS;


    @Operation(summary = "Create Project", description = " Create new project by title, description, ...", tags = { "project" })
    @RequestMapping(value="/project", method= RequestMethod.POST,consumes={ "application/json"})
    @ResponseBody
    public ResponseEntity createProject(@RequestBody ProjectVO project) throws ProjectException {
        Optional<Object> findProject = Optional.ofNullable(projectBS.findProjectByTitle(project.getProjectTitle()));

        if(findProject.isPresent()){
            return ResponseEntity.status(HttpStatus.FOUND).body("This Project is Founded");
        }
        projectBS.createProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }

    @Operation(summary = "Find Project by title", description = "Project search by %title% format", tags = { "project" })
    @RequestMapping(value="/project", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getProjectInfo(@RequestParam(value="title", defaultValue="Project title") String title){
        Optional<ProjectVO> findProject = Optional.ofNullable(projectBS.findProjectByTitle(title));
        if(findProject.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(findProject.get());
    }

    @Operation(summary = "Find all projects", description = "Find all projects", tags = { "project" })
    @RequestMapping(value="/project/all", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAllProjectInfo(){
        List<ProjectVO> findProjects = projectBS.findListProject();

        if(findProjects.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(findProjects);
    }

    @Operation(summary = "Create Project Collaborator", description = " Affect new project collaborator by mail adresse Collaborator, Id Project ...", tags = { "projectCollaborator" })
    @RequestMapping(value="/project/collaborator", method= RequestMethod.POST,consumes={ "application/json"})
    @ResponseBody
    public ResponseEntity createProjectCollaborator(@RequestBody ProjectCollaboratorVO projectCollaborator) throws ProjectCollaboratorException {
        projectBS.createProjectCollaborator(projectCollaborator);
        return ResponseEntity.status(HttpStatus.CREATED).body(projectCollaborator);
    }

    @Operation(summary = "Find Project Collaborator by mail adresse", description = "Project Collaborator search by %mailAdresse% format", tags = { "projectCollaborator" })
    @RequestMapping(value="/project/collaborator/all", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity findAllProjectBymailAdresse(@RequestParam(value="mailAdresse", defaultValue="mailAdresse") String mailAdresse){
        Optional<List<ProjectVO>> findProject = Optional.ofNullable(projectBS.findAllProjectBymailAdresse(mailAdresse));
        if(findProject.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(findProject.get());
    }

}
