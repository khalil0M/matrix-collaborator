package com.humanup.matrixcollaborator.dao.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.util.Date;

@FieldDefaults(level= AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString(of= {"interviewId","interviewTitle","interviewDescription","interviewDate"})
@Entity
public class Interview {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long interviewId;
    String interviewTitle;
    String interviewDescription;
    Date interviewDate;

    @ManyToOne
    @JoinColumn(name = "collaboratorMailAdresse")
    private Collaborator collaborator;

}
