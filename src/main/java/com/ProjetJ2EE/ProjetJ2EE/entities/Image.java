package com.ProjetJ2EE.ProjetJ2EE.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Builder
@Data
public class Image
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ImageId;
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    public Image(Long imageId, Game game) {
        ImageId = imageId;
        this.game = game;
    }

    public Long getImageId() {
        return ImageId;
    }

    public void setImageId(Long imageId) {
        ImageId = imageId;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Image() {
    }

    @Override
    public String toString() {
        return "Image{" +
                "ImageId=" + ImageId +
                ", game=" + game +
                '}';
    }
}
