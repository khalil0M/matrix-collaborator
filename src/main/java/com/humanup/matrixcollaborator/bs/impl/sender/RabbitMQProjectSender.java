package com.humanup.matrixcollaborator.bs.impl.sender;

import com.humanup.matrixcollaborator.vo.ProjectCollaboratorVO;
import com.humanup.matrixcollaborator.vo.ProjectVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProjectSender {
  private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProjectSender.class);

  private final RabbitTemplate rabbitTemplate;

  public RabbitMQProjectSender(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  @Value("${project.queue.name}")
  String queueProject;

  public void send(ProjectVO project) {
    rabbitTemplate.convertAndSend(queueProject, project);
    LOGGER.info("Sending message... {} ", project.toString());
  }

  @Value("${project.collaborator.queue.name}")
  String queueProjectCollaborator;

  public void send(ProjectCollaboratorVO projectCollaborator) {
    rabbitTemplate.convertAndSend(queueProjectCollaborator, projectCollaborator);
    LOGGER.info("Sending message... {} ", projectCollaborator.toString());
  }
}
