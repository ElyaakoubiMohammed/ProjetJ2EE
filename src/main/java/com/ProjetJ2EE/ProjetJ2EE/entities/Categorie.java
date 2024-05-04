package com.ProjetJ2EE.ProjetJ2EE.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Categorie
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CategorieId;
    private String categorieType;
    private String DescriptionCategorie;
    private Integer GameNumber;
    @OneToMany(mappedBy = "categorie")
    private List<Game> gameList;


    public String getCategorieType() {
        return categorieType;
    }
}
