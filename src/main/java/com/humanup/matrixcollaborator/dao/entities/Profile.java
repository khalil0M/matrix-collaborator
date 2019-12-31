package com.humanup.matrixcollaborator.dao.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long profileId;
    private String profileTitle;
    private String profileDescription;



    @OneToMany(mappedBy="profile",fetch=FetchType.LAZY)
    private List<Collaborator> personList;

    protected Profile() {}

    public Profile(String profileTitle, String profileDescription) {
        this.profileTitle = profileTitle;
        this.profileDescription = profileDescription;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                profileId, profileTitle, profileDescription);
    }


    public Long getId() {
        return this.profileId;
    }
    public String getProfileTitle() {
        return this.profileTitle;
    }
    public String getProfileDescription() {
        return this.profileDescription;
    }
    public List<Collaborator> getPersonList() {
        return personList;
    }

    public static class Builder{

        private String profileTitle;
        private String profileDescription;

        public Builder() {
        }

        public Builder setProfileTitle(String profileTitle) {
            this.profileTitle = profileTitle;
            return this;
        }

        public Builder setProfileDescription(String profileDescription) {
            this.profileDescription = profileDescription;
            return this;
        }

        public Profile build(){
            return new Profile( profileTitle,  profileDescription);
        }
    }

}
