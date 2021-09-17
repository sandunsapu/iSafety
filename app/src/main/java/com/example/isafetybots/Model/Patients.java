package com.example.isafetybots.Model;

public class Patients {

    private String fullName,mobile,password;

    public Patients()
    {

    }

    public Patients(String fullName, String mobile, String password) {
        this.fullName = fullName;
        this.mobile = mobile;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
