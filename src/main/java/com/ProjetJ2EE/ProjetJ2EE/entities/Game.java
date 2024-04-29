package com.ProjetJ2EE.ProjetJ2EE.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Game
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long GameId;
    private String GameName;
    private double Prix;
    private boolean Promo;
    private String Description;
    private int PourcentagePromo;
    private int Rating;
    private Date PublishDate;
    @ManyToOne
    private Specs MinSpecs;
    @ManyToOne
    private Specs RecSpecs;
    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;
    @ManyToOne
    private Purchase purchase;
    @ManyToOne
    private Wishlist wishlist;

    @ManyToOne
    private Image image;


}
