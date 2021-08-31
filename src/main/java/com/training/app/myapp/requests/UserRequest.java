package com.training.app.myapp.requests;

import com.training.app.myapp.exceptions.ErrorUserValidationConstants;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class UserRequest {

    @NotNull(message =ErrorUserValidationConstants.Required)
    private String userName;

    @NotNull(message = ErrorUserValidationConstants.Required)
    @Pattern(regexp = ErrorUserValidationConstants.PasswordRegex)
    private String password;

    private Boolean admin;

    @NotNull(message =ErrorUserValidationConstants.Required)
    @Email(message =ErrorUserValidationConstants.EmailFormat)
    private String email;

    private ContactRequest contact;

    public ContactRequest getContact() {
        return contact;
    }

    public void setContact(ContactRequest contact) {
        this.contact = contact;
    }

    private boolean active;
    private List<UserAddressesRequest> addresses;

    public List<UserAddressesRequest> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<UserAddressesRequest> addresses) {
        this.addresses = addresses;
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

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }



    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
