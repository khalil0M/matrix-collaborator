package com.humanup.matrixcollaborator.vo;

public class CollaboratorVO {

    private String mailAdresse;

    public CollaboratorVO() {
    }

    public CollaboratorVO(String mailAdresse) {
        this.mailAdresse = mailAdresse;
    }

    public String getMailAdresse() {
        return mailAdresse;
    }

    public static class Builder {
        private String mailAdresse;

        public Builder() {
        }

        public Builder setMailAdresse(String mailAdresse) {
            this.mailAdresse = mailAdresse;
            return this;
        }

        public CollaboratorVO build() {
            return new CollaboratorVO(mailAdresse);
        }
    }

}
