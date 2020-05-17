package com.humanup.matrixcollaborator.bs.impl.listner;


import com.humanup.matrixcollaborator.dao.InterviewDAO;
import com.humanup.matrixcollaborator.dao.ProjectCollaboratorDAO;
import com.humanup.matrixcollaborator.dao.ProjectDAO;
import com.humanup.matrixcollaborator.dao.entities.Interview;
import com.humanup.matrixcollaborator.dao.entities.Project;
import com.humanup.matrixcollaborator.dao.entities.ProjectCollaborator;
import com.humanup.matrixcollaborator.vo.InterviewVO;
import com.humanup.matrixcollaborator.vo.ProjectCollaboratorVO;
import com.humanup.matrixcollaborator.vo.ProjectVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;


@Component
@RefreshScope
public class RabbitMQProjectListner {
  private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProjectListner.class);

  @Autowired
  private ProjectDAO projectDAO;

  @Autowired
  private ProjectCollaboratorDAO projectCollaboratorDAO;

  @RabbitListener(queues = { "${project.queue.name}" })
  public void receiveProject(ProjectVO projectVO) {
    try {
      LOGGER.info("Receive  message... {} ", projectVO.toString());
      String email = projectVO.getProjectTitle();

      if (null == email || StringUtils.isEmpty(email)) {
        LOGGER.info("Received message as generic: {}", projectVO.toString());
      }

      Project projectToSave =  Project.builder()
              .projectTitle(projectVO.getProjectTitle())
              .projectDescription(projectVO.getProjectDescription())
              .build();
               projectDAO.save(projectToSave);
    }catch(Exception ex){
      LOGGER.info("Error  message... {} ", ex.getMessage(),ex);

    }
  }

  @RabbitListener(queues = { "${project.collaborator.queue.name}" })
  public void receiveProjectCollaborator(ProjectCollaboratorVO projectCollaboratorVO) {
    try {
      LOGGER.info("Receive  message... {} ", projectCollaboratorVO.toString());
      String email = projectCollaboratorVO.getMailAdresse();
      Project project = projectDAO.findByProjectId(projectCollaboratorVO.getProjectId());

      if (null == email || null == project || StringUtils.isEmpty(email)) {
        LOGGER.info("Received message as generic: {}", projectCollaboratorVO.toString());
      }

      ProjectCollaborator collaboratorToSave = ProjectCollaborator.builder()
              .mailAdresse(email)
              .project(project)
              .build();
      projectCollaboratorDAO.save(collaboratorToSave);
    }catch(Exception ex){
      LOGGER.info("Error  message... {} ", ex.getMessage(),ex);
    }
  }
}

