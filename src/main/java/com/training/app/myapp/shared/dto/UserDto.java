package com.training.app.myapp.shared.dto;


import java.util.List;

public class UserDto {


    private long id;
    private String userId;
    private String userName;
    private String password;
    private String passwordEncrypted;
    private Boolean admin;
    private String email;
    private Boolean emailStatus=false;
    private Boolean active;
    private List<UserAddressDto> addresses;
    private ContactDto contact;

    public ContactDto getContact() {
        return contact;
    }

    public void setContact(ContactDto contact) {
        this.contact = contact;
    }

    public List<UserAddressDto> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<UserAddressDto> addresses) {
        this.addresses = addresses;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Boolean   isActive() {
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

    public Boolean getActive() {
        return active;
    }
}
