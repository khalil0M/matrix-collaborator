package com.humanup.matrixcollaborator.bs.impl;

import com.humanup.matrixcollaborator.aop.dto.InterviewException;
import com.humanup.matrixcollaborator.bs.InterviewBS;
import com.humanup.matrixcollaborator.bs.impl.sender.RabbitMQInterviewSender;
import com.humanup.matrixcollaborator.dao.InterviewDAO;
import com.humanup.matrixcollaborator.dao.entities.Interview;
import com.humanup.matrixcollaborator.vo.InterviewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class InterviewBSImpl implements InterviewBS {
    @Autowired
    private InterviewDAO interviewDAO;

    @Autowired
    RabbitMQInterviewSender rabbitMQInterviewSender;

    @Override
    @Transactional(transactionManager="transactionManagerWrite",rollbackFor = InterviewException.class)
    public boolean createInterview(InterviewVO interviewVO) throws InterviewException {
        if (null == interviewVO) throw new InterviewException();
        rabbitMQInterviewSender.send(interviewVO);
        return true;
    }

    @Override
    public InterviewVO findInterviewByTitle(String interviewTitle) {
        Optional<Interview> interviewFinded = Optional.ofNullable(interviewDAO.findByInterviewTitle(interviewTitle));
        if(interviewFinded.isPresent()) {
            return  InterviewVO.builder()
                    .interviewTitle(interviewFinded.get().getInterviewTitle())
                    .interviewDescription(interviewFinded.get().getInterviewDescription())
                    .interviewDate(interviewFinded.get().getInterviewDate())
                    .mailAdresse(interviewFinded.get().getMailAdresse())
                    .build();
        }
        return null;
    }

    @Override
    public List<InterviewVO> findListInterview() {
        return interviewDAO.findAll()
                .stream()
                .map(interviewFinded ->  InterviewVO.builder()
                        .interviewTitle(interviewFinded.getInterviewTitle())
                        .interviewDescription(interviewFinded.getInterviewDescription())
                        .interviewDate(interviewFinded.getInterviewDate())
                        .mailAdresse(interviewFinded.getMailAdresse())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<InterviewVO> findListInterviewsByCollaboratorMailAdresse(String mailAdresse) {
        return interviewDAO.findListInterviewsByCollaboratorMailAdresse(mailAdresse)
                .stream()
                .map(interviewFinded ->  InterviewVO.builder()
                        .interviewTitle(interviewFinded.getInterviewTitle())
                        .interviewDescription(interviewFinded.getInterviewDescription())
                        .interviewDate(interviewFinded.getInterviewDate())
                        .mailAdresse(interviewFinded.getMailAdresse())
                        .build())
                .collect(Collectors.toList());
    }
}
