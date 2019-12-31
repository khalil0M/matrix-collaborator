package com.humanup.matrixcollaborator.vo;

import java.util.Date;

public class CollaboratorVO {

    private String firstName;
    private String lastName;
    private String mailAdresse;
    private Date birthDate;
    private String profile;

    public CollaboratorVO() {
    }

    public CollaboratorVO(String firstName, String lastName, String mailAdresse, Date birthDate, String profile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mailAdresse = mailAdresse;
        this.birthDate = birthDate;
        this.profile = profile;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMailAdresse() {
        return mailAdresse;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getProfile() {
        return profile;
    }


    public static class Builder {
        private String firstName;
        private String lastName;
        private String mailAdresse;
        private Date birthDate;
        private String profile;

        public Builder() {
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setMailAdresse(String mailAdresse) {
            this.mailAdresse = mailAdresse;
            return this;
        }

        public Builder setBirthDate(Date birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder setProfile(String profile) {
            this.profile = profile;
            return this;
        }


        public CollaboratorVO build() {
            return new CollaboratorVO(firstName, lastName, mailAdresse, birthDate, profile);
        }
    }

}
