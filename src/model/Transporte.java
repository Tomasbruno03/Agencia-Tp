package model;

import java.util.*;
import java.util.Objects;

public abstract class Transporte {
    private String patente;
    private int capacidadPasajeros;
    private Set<Viaje> listaViajes; // atributo, Constructor, getters, settters, toCompare, toString
    private boolean disponible;
    private float velocidadPromedioXhora;

    public Transporte(String patente, int capacidadPasajeros, boolean disponible, float velocidadPromedioXhora){
        this.patente = patente;
        this.capacidadPasajeros = capacidadPasajeros;
        this.listaViajes = new TreeSet<>(); // ordenados por id
        this.disponible = disponible;
        this.velocidadPromedioXhora = velocidadPromedioXhora;
    }

    public String getPatente(){ return patente; }
    public int getCapacidadPasajeros(){ return capacidadPasajeros; }
    public boolean estaDisponible(){ return disponible; }
    public float getVelocidadPromedioXhora(){ return velocidadPromedioXhora; }

    public boolean setDisponible(boolean disponible){
        if (disponible != this.disponible)
            this.disponible = disponible;
    }

    public float setVelocidadPromedioXhora(float velocidadPromedioXhora){
        if(velocidadPromedioXhora > 0)
            this.velocidadPromedioXhora = velocidadPromedioXhora;
    }

    public String toString() {
        return "Transporte [ Patente: " + patente + ", Capacidad de Pasajeros: " + capacidadPasajeros + ", Disponible: " + disponible + ", Velocidad promedio por hora: " + velocidadPromedioXhora" ]";
    }
}