package com.training.app.myapp.exceptions;

public enum UserErrors {
    Not_found("Sorry,this user is not found "),
    Field_Required("Sorry, there are some fields required"),
    User_exist("Sorry, the user is already exist");

    private String error_message;

    UserErrors(String error_message) {
        this.error_message = error_message;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }
}
