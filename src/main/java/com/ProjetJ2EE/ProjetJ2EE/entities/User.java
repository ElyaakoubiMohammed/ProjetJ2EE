package com.ProjetJ2EE.ProjetJ2EE.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;

import java.util.Date;

@Entity
@Builder
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UserId;
    private String FirstName;
    private String LastName;
    private Date Date_Naissance;
    private String Gender;
    private String UserType;

    public User(Long userId, String firstName, String lastName, Date date_Naissance, String gender, String userType) {
        UserId = userId;
        FirstName = firstName;
        LastName = lastName;
        Date_Naissance = date_Naissance;
        Gender = gender;
        UserType = userType;
    }
    public User()
    {

    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public Date getDate_Naissance() {
        return Date_Naissance;
    }

    public void setDate_Naissance(Date date_Naissance) {
        Date_Naissance = date_Naissance;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserId=" + UserId +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Date_Naissance=" + Date_Naissance +
                ", Gender='" + Gender + '\'' +
                ", UserType='" + UserType + '\'' +
                '}';
    }
}
