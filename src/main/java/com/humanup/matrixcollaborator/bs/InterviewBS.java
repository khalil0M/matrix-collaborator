package com.humanup.matrixcollaborator.bs;

import com.humanup.matrixcollaborator.aop.dto.InterviewException;
import com.humanup.matrixcollaborator.vo.InterviewVO;

import java.util.List;

public interface InterviewBS {

    boolean createInterview(InterviewVO interviewVO) throws InterviewException;
    InterviewVO findInterviewByTitle(String interviewTitle);
    List<InterviewVO> findListInterview();
    List<InterviewVO> findListInterviewsByCollaboratorMailAdresse(String mailAdresse);

}
