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
@ToString(of= {"interviewId","interviewTitle","interviewDescription","interviewDate","mailAdresse"})
@Entity
@Table (name = "interview")
public class Interview {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "interview_id")
    Long interviewId;
    @Column(name = "interview_title")
    String interviewTitle;
    @Column(name = "interview_description")
    String interviewDescription;
    @Column(name = "interview_date")
    Date interviewDate;
    @Column(name = "collaborator_mail_adresse")
    String mailAdresse;

    /*@ManyToOne
    @JoinColumn(name = "collaborator_mail_adresse")
    private ProjectCollaborator projectCollaborator;*/

}
