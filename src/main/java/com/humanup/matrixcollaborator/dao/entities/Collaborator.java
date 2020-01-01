package com.humanup.matrixcollaborator.dao.entities;
import javax.persistence.*;

@Entity
public class Collaborator {

    @Id
    @Column(unique = true)
    private String mailAdresse;


    public Collaborator() {
    }

    public Collaborator(String mailAdresses) {
        this.mailAdresse = mailAdresses;
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

    public static class Builder{
        private String mailAdresse;

        public Builder() {
        }

        public Builder setMailAdresse(String mailAdresse) {
            this.mailAdresse = mailAdresse;
            return this;
        }

        public Collaborator build(){
            return new Collaborator(mailAdresse);
        }
    }



}
