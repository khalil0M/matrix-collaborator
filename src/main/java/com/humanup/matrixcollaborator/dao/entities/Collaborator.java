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
public class Collaborator {

    @Id
    @Column(unique = true)
    String mailAdresse;


    @OneToMany(mappedBy="collaborator",fetch=FetchType.LAZY)
    List<Interview> interviewList;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "collaborator_project",
            joinColumns = { @JoinColumn(name = "mail_adresse") },
            inverseJoinColumns = { @JoinColumn(name = "project_id") })
    Set<Project> projects = new HashSet<>();

}
