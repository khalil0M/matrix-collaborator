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
@ToString(of= {"projectId","projectTitle","projectDescription"})
@Entity
@Table (name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "project_id")
    Long projectId;
    @Column(name = "project_title")
    String projectTitle;
    @Column(name = "project_description")
    String projectDescription;

    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    List<ProjectCollaborator> projectsCollaborator;

}
