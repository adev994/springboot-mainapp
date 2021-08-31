package com.training.app.myapp.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name="contacts")
public class ContactEntity {


        @Id
        @GeneratedValue
        private long id;


        @Column(length = 30)
        private String contactId;

        @Column
        private String mobile;

        @Column
        private String skype;

        @OneToOne
        @JoinColumn(name="users_id")
        private UserEntity user;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getContactId() {
            return contactId;
        }

        public void setContactId(String contactId) {
            this.contactId = contactId;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getSkype() {
            return skype;
        }

        public void setSkype(String skype) {
            this.skype = skype;
        }

        public UserEntity getUser() {
            return user;
        }

        public void setUser(UserEntity user) {
            this.user = user;
        }


}
