package com.training.app.myapp.exceptions;

public class ErrorUserValidationConstants {
    public static final String PasswordRegex= "^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=])"
            + "(?=\\S+$).{8,20}$";
    public static final String Required="This file cannot be null";
    public static final String EmailFormat="Email Format Required";
    public static final String PasswordFormat="Password must be Strong enough";

}
