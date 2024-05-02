package com.ProjetJ2EE.ProjetJ2EE.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
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

    @Override
    public String toString() {
        return "Image{" +
                "ImageId=" + ImageId +
                ", game=" + game +
                '}';
    }
}
