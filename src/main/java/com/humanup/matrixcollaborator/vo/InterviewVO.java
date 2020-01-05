package com.humanup.matrixcollaborator.vo;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@FieldDefaults(level= AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(of= {"interviewTitle","interviewDescription","interviewDate","collaborator"})
public class InterviewVO {

    String interviewTitle;
    String interviewDescription;
    Date interviewDate;
    String collaborator;

}
