package com.humanup.matrixcollaborator.dao.entities;


import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Collaborator {

    @Id
    @Column(unique = true)
    private String mailAdresse;
    private String firstName;
    private String lastName;
    private Date birthDate;


    @ManyToOne
    @JoinColumn(name = "profileId")
    private Profile profile;

    public Collaborator() {
    }

    public Collaborator(String firstName, String lastName, String mailAdresses, Date birthDate, Profile profile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mailAdresse = mailAdresses;
        this.birthDate = birthDate;
        this.profile = profile;
    }

    @Override
    public String toString() {
        return String.format(
                "Collaborator[mailAdresse=%s, firstName='%s', lastName='%s']",
                mailAdresse, firstName, lastName);
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

    public Profile getProfile() {
        return this.profile;
    }


    public static class Builder{
        private String mailAdresse;
        private String firstName;
        private String lastName;
        private Date birthDate;
        private Profile profile;

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

        public Builder setProfile(Profile profile) {
            this.profile = profile;
            return this;
        }

        public Collaborator build(){
            return new Collaborator( firstName,  lastName,  mailAdresse, birthDate,profile);
        }
    }



}
