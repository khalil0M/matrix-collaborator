package com.humanup.matrixcollaborator.dao.entities;
import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Collaborator {

    @Id
    @Column(unique = true)
    private String mailAdresse;


    @OneToMany(mappedBy="collaborator",fetch=FetchType.LAZY)
    private List<Interview> interviewList;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "collaborator_project",
            joinColumns = { @JoinColumn(name = "mail_adresse") },
            inverseJoinColumns = { @JoinColumn(name = "project_id") })
    private Set<Project> projects = new HashSet<>();

    public Collaborator() {
    }

    public Collaborator(String mailAdresses,Set<Project> projects) {
        this.mailAdresse = mailAdresses;
        this.projects = projects;
    }

    @Override
    public String toString() {
        return String.format(
                "Collaborator[mailAdresse='%s']",
                mailAdresse);
    }

    public String getMailAdresse() {
        return mailAdresse;
    }
    public Set<Project> getProjects() {
        return projects;
    }
    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public static class Builder{
        private String mailAdresse;
        private Set<Project> projetcs;

        public Builder() {
        }

        public Builder setMailAdresse(String mailAdresse) {
            this.mailAdresse = mailAdresse;
            return this;
        }

        public Builder setProjects(Set<Project> projects) {
            this.projetcs = projects;
            return this;
        }

        public Collaborator build(){
            return new Collaborator(mailAdresse, projetcs);
        }
    }



}
