package com.ProjetJ2EE.ProjetJ2EE.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long AccountId;
    private String Username;
    private String Email;
    private String Password;
    private String Country;
    private int GameCount;
    private String FirstName;
    private String LastName;
    private int Age;
    private String Gender;
    private String UserType;



    @Override
    public String toString() {
        return "Account{" +
                "AccountId=" + AccountId +
                ", Username='" + Username + '\'' +
                ", email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                ", Country='" + Country + '\'' +
                ", GameCount=" + GameCount +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Age=" + Age +
                ", Gender='" + Gender + '\'' +
                ", UserType='" + UserType + '\'' +
                '}';
    }
}
