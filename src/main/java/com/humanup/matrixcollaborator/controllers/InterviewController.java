package com.humanup.matrixcollaborator.controllers;

import com.humanup.matrixcollaborator.bs.InterviewBS;
import com.humanup.matrixcollaborator.exceptions.CollaboratorException;
import com.humanup.matrixcollaborator.vo.InterviewVO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class InterviewController {

    @Autowired
    private InterviewBS interviewBS;

    @Operation(summary = "Create Interview", description = " Create new interview by title, description, Date, Collaborator ...", tags = { "interview" })
    @RequestMapping(value="/interview", method= RequestMethod.POST,consumes={ "application/json"})
    @ResponseBody
    public ResponseEntity createInterview(@RequestBody InterviewVO interview) throws CollaboratorException {
        Optional<Object> findInterview = Optional.ofNullable(interviewBS.findInterviewByTitle(interview.getInterviewTitle()));

        if(findInterview.isPresent()){
            return ResponseEntity.status(HttpStatus.FOUND).body("This Interview is Founded");
        }
        interviewBS.createInterview(interview);
        return ResponseEntity.status(HttpStatus.CREATED).body(interview);
    }

    @Operation(summary = "Find interview by title", description = "Interview search by %title% format", tags = { "interview" })
    @RequestMapping(value="/interview", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getInterviewInfo(@RequestParam(value="title", defaultValue="Interview") String title){
        Optional<InterviewVO> findInterview = Optional.ofNullable(interviewBS.findInterviewByTitle(title));
        if(findInterview.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(findInterview.get());
    }

    @Operation(summary = "Find all interviews", description = "Find all interviews", tags = { "interview" })
    @RequestMapping(value="/interview/all", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAllPersonInfo(){
        List<InterviewVO> findInterviews = interviewBS.findListInterview();

        if(findInterviews.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(findInterviews);
    }

    @Operation(summary = "Find all interviews by collaborator", description = "Find all interviews by collaborator mail adresse", tags = { "interview" })
    @RequestMapping(value="/collaborator/all/interview", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getCollaboratorInterviews(@RequestParam(value="email", defaultValue="robot@sqli.com") String email){
        Optional<List<InterviewVO>> findCollaborator = Optional.ofNullable(interviewBS.findListCollaboratorsByCollaboratorMailAdresse(email));
        if(findCollaborator.get().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This Email not Found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(findCollaborator.get());
    }

}
