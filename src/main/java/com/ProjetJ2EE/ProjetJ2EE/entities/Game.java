package com.ProjetJ2EE.ProjetJ2EE.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gameId;
    private String gameName;
    private double prix;
    private boolean promo;
    private String description;
    private int pourcentagePromo;
    private int rating;
    private Date publishDate;

    @ManyToOne
    private Specs minSpecs;

    @ManyToOne
    private Specs recSpecs;

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
