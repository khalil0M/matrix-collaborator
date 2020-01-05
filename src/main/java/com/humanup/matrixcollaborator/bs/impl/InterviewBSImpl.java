package com.humanup.matrixcollaborator.bs.impl;

import com.humanup.matrixcollaborator.bs.InterviewBS;
import com.humanup.matrixcollaborator.dao.CollaboratorDAO;
import com.humanup.matrixcollaborator.dao.InterviewDAO;
import com.humanup.matrixcollaborator.dao.entities.Collaborator;
import com.humanup.matrixcollaborator.dao.entities.Interview;
import com.humanup.matrixcollaborator.exceptions.CollaboratorException;
import com.humanup.matrixcollaborator.vo.InterviewVO;
import org.apache.commons.lang3.StringUtils;
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
    private CollaboratorDAO collaboratorDAO;

    @Override
    @Transactional(rollbackFor = CollaboratorException.class)
    public boolean createInterview(InterviewVO interviewVO) throws CollaboratorException {
        Collaborator collaborator = collaboratorDAO.findByMailAdresse(interviewVO.getCollaborator());
        String email =  interviewVO.getCollaborator();

        if(null == collaborator || null == email || StringUtils.isEmpty(email)){
            throw new CollaboratorException();
        }
        Interview interviewToSave = Interview.builder()
                .interviewTitle(interviewVO.getInterviewTitle())
                .interviewDescription(interviewVO.getInterviewDescription())
                .interviewDate(interviewVO.getInterviewDate())
                .collaborator(collaborator)
                .build();
        return  interviewDAO.save(interviewToSave)!=null;
    }

    @Override
    public InterviewVO findInterviewByTitle(String interviewTitle) {
        Optional<Interview> interviewFinded = Optional.ofNullable(interviewDAO.findByInterviewTitle(interviewTitle));
        if(interviewFinded.isPresent()) {
            return  InterviewVO.builder()
                    .interviewTitle(interviewFinded.get().getInterviewTitle())
                    .interviewDescription(interviewFinded.get().getInterviewDescription())
                    .interviewDate(interviewFinded.get().getInterviewDate())
                    .collaborator(interviewFinded.get().getCollaborator().getMailAdresse())
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
                        .collaborator(interviewFinded.getCollaborator().getMailAdresse())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<InterviewVO> findListCollaboratorsByCollaboratorMailAdresse(String mailAdresse) {
        return interviewDAO.findListCollaboratorsByCollaboratorMailAdresse(mailAdresse)
                .stream()
                .map(interviewFinded ->  InterviewVO.builder()
                        .interviewTitle(interviewFinded.getInterviewTitle())
                        .interviewDescription(interviewFinded.getInterviewDescription())
                        .interviewDate(interviewFinded.getInterviewDate())
                        .collaborator(interviewFinded.getCollaborator().getMailAdresse())
                        .build())
                .collect(Collectors.toList());
    }
}
