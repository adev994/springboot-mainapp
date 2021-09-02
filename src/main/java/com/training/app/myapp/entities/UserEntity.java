package com.training.app.myapp.entities;

import com.training.app.myapp.shared.dto.UserAddressDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="app_users")
public class UserEntity {


    @Id
    @GeneratedValue
    private long id;


    @Column(nullable = false)
    private String userId;

    @Column(nullable = false,length = 50)
    private String userName;

    @Column(nullable = false)
    private String passwordEncrypted;

    @Column(nullable = false,length = 60,unique = true)
    private String email;

    @Column
    private Boolean emailStatus=false;

    @Column(nullable = false,length = 20)
    private Boolean admin=false;

    @Column(nullable = false)
    private Boolean active=false;

    @OneToMany(mappedBy="user",  cascade=CascadeType.ALL)
    private List<AddressEntity> addresses;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private ContactEntity contact;

    @ManyToMany(mappedBy = "users",cascade = CascadeType.ALL)
    private Set<GroupEntity> groups;

    public ContactEntity getContact() {
        return contact;
    }

    public void setContact(ContactEntity contact) {
        this.contact = contact;
    }

    public Set<GroupEntity> getGroups() {
        return groups;
    }

    public void setGroups(Set<GroupEntity> groups) {
        this.groups = groups;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordEncrypted() {
        return passwordEncrypted;
    }

    public void setPasswordEncrypted(String passwordEncrypted) {
        this.passwordEncrypted = passwordEncrypted;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(Boolean emailStatus) {
        this.emailStatus = emailStatus;
    }

    public List<AddressEntity> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressEntity> addresses) {
        this.addresses = addresses;
    }
}
