package com.ProjetJ2EE.ProjetJ2EE.entities;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.List;
@Entity
@Builder
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

    public Wishlist(long wishlistId, List<Game> games, Account account) {
        this.wishlistId = wishlistId;
        Games = games;
        this.account = account;
    }

    public Wishlist() {
    }

    public long getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(long wishlistId) {
        this.wishlistId = wishlistId;
    }

    public List<Game> getGames() {
        return Games;
    }

    public void setGames(List<Game> games) {
        Games = games;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Wishlist{" +
                "wishlistId=" + wishlistId +
                ", Games=" + Games +
                ", account=" + account +
                '}';
    }
}
