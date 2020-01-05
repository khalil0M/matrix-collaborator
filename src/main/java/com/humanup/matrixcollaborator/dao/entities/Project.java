package com.humanup.matrixcollaborator.dao.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@FieldDefaults(level= AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString(of= {"projectId","projectTitle","projectDescription"})
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long projectId;
    String projectTitle;
    String projectDescription;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "projects")
    Set<Collaborator> collaborators = new HashSet<>();

}
