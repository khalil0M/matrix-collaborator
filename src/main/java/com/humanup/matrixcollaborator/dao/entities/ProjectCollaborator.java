package com.humanup.matrixcollaborator.dao.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@FieldDefaults(level= AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString(of= {"mailAdresse"})
@Entity
@Table (name = "collaborator_project")
public class ProjectCollaborator {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "collaborator_project_id")
    Long collaboratorProjectId;

    @Column(name = "mail_adresse",unique = true)
    String mailAdresse;

    @ManyToOne
    @JoinColumn(name = "project_id")
    Project project;
}
