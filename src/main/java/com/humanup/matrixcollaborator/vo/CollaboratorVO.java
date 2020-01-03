package com.humanup.matrixcollaborator.vo;

import java.util.List;

public class CollaboratorVO {

    private String mailAdresse;
    private List<ProjectVO> projectVOList;

    public CollaboratorVO() {
    }

    public CollaboratorVO(String mailAdresse, List<ProjectVO> projectVOList) {
        this.mailAdresse = mailAdresse;
        this.projectVOList = projectVOList;
    }

    public String getMailAdresse() {
        return mailAdresse;
    }

    public List<ProjectVO> getProjectVOList() {
        return projectVOList;
    }

    public static class Builder {
        private String mailAdresse;
        private List<ProjectVO> projectVOList;

        public Builder() {
        }

        public Builder setMailAdresse(String mailAdresse) {
            this.mailAdresse = mailAdresse;
            return this;
        }

        public Builder setProjects(List<ProjectVO> projectVOList) {
            this.projectVOList = projectVOList;
            return this;
        }

        public CollaboratorVO build() {
            return new CollaboratorVO(mailAdresse, projectVOList);
        }
    }

}
