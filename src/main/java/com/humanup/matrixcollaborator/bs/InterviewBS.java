package com.humanup.matrixcollaborator.bs;

import com.humanup.matrixcollaborator.exceptions.CollaboratorException;
import com.humanup.matrixcollaborator.vo.InterviewVO;

import java.util.List;

public interface InterviewBS {

    boolean createInterview(InterviewVO interviewVO) throws CollaboratorException;
    InterviewVO findInterviewByTitle(String InterviewTitle);
    List<InterviewVO> findListInterview();
    List<InterviewVO> findListCollaboratorsByCollaboratorMailAdresse(String mailAdresse);

}
