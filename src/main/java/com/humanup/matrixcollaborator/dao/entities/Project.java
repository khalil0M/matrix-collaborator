package com.humanup.matrixcollaborator.dao.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long projectId;
    private String projectTitle;
    private String projectDescription;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "projects")
    private Set<Collaborator> collaborators = new HashSet<>();

    protected Project() {}

    public Project(String projectTitle, String projectDescription) {
        this.projectTitle = projectTitle;
        this.projectDescription = projectDescription;
    }

    @Override
    public String toString() {
        return String.format(
                "Project[id=%d, projectTitle='%s', projectDescription='%s']",
                projectId, projectTitle, projectDescription);
    }

    public Long getProjectId() {
        return projectId;
    }
    public String getProjectTitle() {
        return projectTitle;
    }
    public String getProjectDescription() {
        return projectDescription;
    }
    public Set<Collaborator> getCollaborators() {
        return collaborators;
    }

    public static class Builder{

        private String projectTitle;
        private String projectDescription;

        public Builder() {
        }

        public Builder setProjectTitle(String projectTitle) {
            this.projectTitle = projectTitle;
            return this;
        }

        public Builder setProjectDescription(String projectDescription) {
            this.projectDescription = projectDescription;
            return this;
        }

        public Project build(){
            return new Project(projectTitle,  projectDescription);
        }

    }

}
