package com.humanup.matrixcollaborator.bs.impl.sender;

import com.humanup.matrixcollaborator.vo.InterviewVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQInterviewSender {
  private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQInterviewSender.class);

  private final RabbitTemplate rabbitTemplate;

  public RabbitMQInterviewSender(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  @Value("${interview.queue.name}")
  String queueName;

  public void send(InterviewVO interview) {
    rabbitTemplate.convertAndSend(queueName, interview);
    LOGGER.info("Sending message... {} ", interview.toString());
  }
}
