package com.ProjetJ2EE.ProjetJ2EE.entities;

import jakarta.persistence.*;
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
    private String username;
    private String email;
    private String Password;
    private String Country;
    private int GameCount;
    private String FirstName;
    private String LastName;
    private int Age;
    private String Gender;
    private String usertype;

    @Lob
    @Column(name = "Pimage", columnDefinition = "LONGBLOB")
    private byte[] image;
    private String PictureBase64;
    @OneToOne(mappedBy = "account")
    private Wishlist wishlist;
    public void setImage(byte[] image) {
        this.image = image;
    }


}
