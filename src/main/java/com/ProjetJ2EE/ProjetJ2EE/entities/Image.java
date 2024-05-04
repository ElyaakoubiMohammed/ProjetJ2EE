package com.ProjetJ2EE.ProjetJ2EE.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
public class Image
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ImageId;
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @Lob
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private byte[] image;
    private String PictureBase64;

    @Builder
    public Image(Long imageId, Game game, byte[] image, String PictureBase64) {
        this.ImageId = imageId;
        this.game = game;
        this.image = image;
        this.PictureBase64 = PictureBase64;
    }
}
