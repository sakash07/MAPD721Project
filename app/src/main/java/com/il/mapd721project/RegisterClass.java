package com.il.mapd721project;

public class RegisterClass {
    private String fullname;
    private String email;
    private String password;
    private String retype_pwd;
    private String address;

    public RegisterClass() {
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRetype_pwd() {
        return retype_pwd;
    }

    public void setRetype_pwd(String retype_pwd) {
        this.retype_pwd = retype_pwd;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
