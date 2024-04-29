package com.ProjetJ2EE.ProjetJ2EE.entities;

import jakarta.persistence.*;
import lombok.Builder;


@Entity
@Builder
public class Specs
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long SpecsId;
    private String OperatingSystem;
    private String Processor;
    private String GPU;
    private int Memory;
    private int Storage;

    public Specs(Long specsId, String operatingSystem, String processor, String GPU, int memory, int storage) {
        SpecsId = specsId;
        OperatingSystem = operatingSystem;
        Processor = processor;
        this.GPU = GPU;
        Memory = memory;
        Storage = storage;
    }
    public Specs()
    {

    }

    public Long getSpecsId() {
        return SpecsId;
    }

    public void setSpecsId(Long specsId) {
        SpecsId = specsId;
    }

    public String getOperatingSystem() {
        return OperatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        OperatingSystem = operatingSystem;
    }

    public String getProcessor() {
        return Processor;
    }

    public void setProcessor(String processor) {
        Processor = processor;
    }

    public String getGPU() {
        return GPU;
    }

    public void setGPU(String GPU) {
        this.GPU = GPU;
    }

    public int getMemory() {
        return Memory;
    }

    public void setMemory(int memory) {
        Memory = memory;
    }

    public int getStorage() {
        return Storage;
    }

    public void setStorage(int storage) {
        Storage = storage;
    }

    @Override
    public String toString() {
        return "Specs{" +
                "SpecsId=" + SpecsId +
                ", OperatingSystem='" + OperatingSystem + '\'' +
                ", Processor='" + Processor + '\'' +
                ", GPU='" + GPU + '\'' +
                ", Memory=" + Memory +
                ", Storage=" + Storage +
                '}';
    }
}

