package com.humanup.matrixcollaborator.bs.impl.listner;


import com.humanup.matrixcollaborator.dao.InterviewDAO;
import com.humanup.matrixcollaborator.dao.entities.Interview;
import com.humanup.matrixcollaborator.vo.InterviewVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;


@Component
@RefreshScope
public class RabbitMQInterviewListner {
  private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQInterviewListner.class);

  @Autowired
  private InterviewDAO interviewDAO;

  @RabbitListener(queues = { "${interview.queue.name}" })
  public void receive(InterviewVO interviewVO) {
    try {
      LOGGER.info("Receive  message... {} ", interviewVO.toString());
      String email = interviewVO.getMailAdresse();

      if (null == email || StringUtils.isEmpty(email)) {
        LOGGER.info("Received message as generic: {}", interviewVO.toString());
      }

      Interview interviewToSave = Interview.builder()
              .interviewTitle(interviewVO.getInterviewTitle())
              .interviewDescription(interviewVO.getInterviewDescription())
              .interviewDate(interviewVO.getInterviewDate())
              .mailAdresse(interviewVO.getMailAdresse())
              .build();
               interviewDAO.save(interviewToSave);
    }catch(Exception ex){
      LOGGER.info("Error  message... {} ", ex.getMessage(),ex);

    }
  }
}

