package com.ProjetJ2EE.ProjetJ2EE.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;

@Entity
@Builder
public class Session
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long SessionId;
    private double PrixHeure;
    private int HoursNum;

    public Session(Long sessionId, double prixHeure, int hoursNum) {
        SessionId = sessionId;
        PrixHeure = prixHeure;
        HoursNum = hoursNum;
    }


    public Session()
    {

    }

    public Long getSessionId() {
        return SessionId;
    }

    public void setSessionId(Long sessionId) {
        SessionId = sessionId;
    }

    public double getPrixHeure() {
        return PrixHeure;
    }

    public void setPrixHeure(double prixHeure) {
        PrixHeure = prixHeure;
    }

    public int getHoursNum() {
        return HoursNum;
    }

    public void setHoursNum(int hoursNum) {
        HoursNum = hoursNum;
    }

    @Override
    public String toString() {
        return "Session{" +
                "SessionId=" + SessionId +
                ", PrixHeure=" + PrixHeure +
                ", HoursNum=" + HoursNum +
                '}';
    }
}
