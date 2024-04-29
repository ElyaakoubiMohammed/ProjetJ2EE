package com.ProjetJ2EE.ProjetJ2EE.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;

@Entity
@Builder
public class Facture
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long FactureId;
    private double PrixTotal;
    private int HoursTotal;
    private int SessionsNumber;

    public Facture(Long factureId, double prixTotal, int hoursTotal, int sessionsNumber) {
        FactureId = factureId;
        PrixTotal = prixTotal;
        HoursTotal = hoursTotal;
        SessionsNumber = sessionsNumber;
    }

    public Facture()
    {

    }
    public Long getFactureId() {
        return FactureId;
    }

    public void setFactureId(Long factureId) {
        FactureId = factureId;
    }

    public double getPrixTotal() {
        return PrixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        PrixTotal = prixTotal;
    }

    public int getHoursTotal() {
        return HoursTotal;
    }

    public void setHoursTotal(int hoursTotal) {
        HoursTotal = hoursTotal;
    }

    public int getSessionsNumber() {
        return SessionsNumber;
    }

    public void setSessionsNumber(int sessionsNumber) {
        SessionsNumber = sessionsNumber;
    }

    @Override
    public String toString() {
        return "Facture{" +
                "FactureId=" + FactureId +
                ", PrixTotal=" + PrixTotal +
                ", HoursTotal=" + HoursTotal +
                ", SessionsNumber=" + SessionsNumber +
                '}';
    }
}

