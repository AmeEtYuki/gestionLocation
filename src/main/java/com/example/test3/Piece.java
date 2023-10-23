package com.example.test3;

public class Piece {
    int id;
    float surface;
    String libelle;

    public Piece(int id, float surface, String libelle) {
        this.id = id;
        this.surface = surface;
        this.libelle = libelle;
    }

    public int getId() {
        return id;
    }

    public float getSurface() {
        return surface;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSurface(float surface) {
        this.surface = surface;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    @Override
    public String toString() {
        return this.libelle+" surface : "+this.surface;
    }
}

