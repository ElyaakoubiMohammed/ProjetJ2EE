package com.ProjetJ2EE.ProjetJ2EE.entities;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.List;

@Entity
@Builder
public class Categorie
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CategorieId;
    private String CategorieType;
    private String DescriptionCatgegorie;
    private int GameNumber;
    @OneToMany(mappedBy = "categorie")
    private List<Game> gameList;

    public Categorie(Long categorieId, String categorieType, String descriptionCatgegorie, int gameNumber, List<Game> gameList) {
        CategorieId = categorieId;
        CategorieType = categorieType;
        DescriptionCatgegorie = descriptionCatgegorie;
        GameNumber = gameNumber;
        this.gameList = gameList;
    }

    public Categorie() {
    }

    public Long getCategorieId() {
        return CategorieId;
    }

    public void setCategorieId(Long categorieId) {
        CategorieId = categorieId;
    }

    public String getCategorieType() {
        return CategorieType;
    }

    public void setCategorieType(String categorieType) {
        CategorieType = categorieType;
    }

    public String getDescriptionCatgegorie() {
        return DescriptionCatgegorie;
    }

    public void setDescriptionCatgegorie(String descriptionCatgegorie) {
        DescriptionCatgegorie = descriptionCatgegorie;
    }

    public int getGameNumber() {
        return GameNumber;
    }

    public void setGameNumber(int gameNumber) {
        GameNumber = gameNumber;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "CategorieId=" + CategorieId +
                ", CategorieType='" + CategorieType + '\'' +
                ", DescriptionCatgegorie='" + DescriptionCatgegorie + '\'' +
                ", GameNumber=" + GameNumber +
                ", gameList=" + gameList +
                '}';
    }
}
