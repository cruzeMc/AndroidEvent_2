package com.events.model;

import android.graphics.Bitmap;

/**
 * Created by Cruze on 12/17/2016.
 */

public class Register {
    public String firstName;
    public String lastName;
    public String userName;
    public String email;
    public String password;
    public String passwordConfirmation;
    public String address;
    public String sex;
    public String age;
    public Bitmap pic;

    public Register(String firstName, String lastName, String userName, String email, String password, String passwordConfirmation, String address, String sex, String age, Bitmap pic) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
        this.address = address;
        this.sex = sex;
        this.age = age;
        this.pic = pic;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Bitmap getPic() {
        return pic;
    }

    public void setPic(Bitmap pic) {
        this.pic = pic;
    }
}
