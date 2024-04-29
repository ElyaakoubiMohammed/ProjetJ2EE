package com.ProjetJ2EE.ProjetJ2EE.entities;


import jakarta.persistence.*;
import lombok.Builder;

import java.util.List;
@Entity
@Builder
public class Purchase
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long PurchaseId;
    @OneToMany(mappedBy = "purchase")

    private List<Game>Games;
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Purchase(long purchaseId, List<Game> games, Account account) {
        PurchaseId = purchaseId;
        Games = games;
        this.account = account;
    }

    public Purchase() {

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
        return "Purchase{" +
                "Games=" + Games +
                ", account=" + account +
                '}';
    }
}
