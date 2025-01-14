package com.example.dynamiclayout;

public class Candidate {
    private final String name;
    private final String details;

    public Candidate(String name, String details) {
        this.name = name;
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }
}
