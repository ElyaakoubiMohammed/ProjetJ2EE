package com.ProjetJ2EE.ProjetJ2EE.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Wishlist
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long wishlistId;
    @OneToMany(mappedBy = "wishlist")

    private List<Game> Games;
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;


}
