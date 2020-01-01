package com.humanup.matrixcollaborator.dao.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Interview {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long interviewId;
    private String interviewTitle;
    private String interviewDescription;
    private Date interviewDate;

    @ManyToOne
    @JoinColumn(name = "collaboratorMailAdresse")
    private Collaborator collaborator;

    protected Interview() {}

    public Interview(String interviewTitle, String interviewDescription, Date interviewDate, Collaborator collaborator) {
        this.interviewTitle = interviewTitle;
        this.interviewDescription = interviewDescription;
        this.interviewDate = interviewDate;
        this.collaborator = collaborator;
    }

    @Override
    public String toString() {
        return String.format(
                "Interview[id=%d, interviewTitle='%s', interviewDescription='%s']",
                interviewId, interviewTitle, interviewDescription);
    }

    public Long getInterviewId() {
        return interviewId;
    }
    public String getInterviewTitle() {
        return interviewTitle;
    }
    public String getInterviewDescription() {
        return interviewDescription;
    }
    public Date getInterviewDate() {
        return interviewDate;
    }
    public Collaborator getCollaborator() {
        return collaborator;
    }

    public static class Builder{

        private String interviewTitle;
        private String interviewDescription;
        private Date interviewDate;
        private Collaborator collaborator;

        public Builder() {
        }

        public Builder setInterviewTitle(String interviewTitle) {
            this.interviewTitle = interviewTitle;
            return this;
        }

        public Builder setInterviewDescription(String interviewDescription) {
            this.interviewDescription = interviewDescription;
            return this;
        }

        public Builder setInterviewDate(Date interviewDate) {
            this.interviewDate = interviewDate;
            return this;
        }

        public Builder setCollaborator(Collaborator collaborator) {
            this.collaborator = collaborator;
            return this;
        }

        public Interview build(){
            return new Interview( interviewTitle,  interviewDescription, interviewDate, collaborator);
        }

    }

}
