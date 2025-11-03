package model;

import java.util.*;
import java.util.Objects;

public abstract class Transporte { //Constructor, getters y settters
    private String patente;
    private int capacidadPasajeros;
    private Set<Viaje> listaViajes;
    private boolean disponible;
    private float velocidadPromedioXhora;

    public Transporte(String patente, int capacidadPasajeros, boolean disponible, float velocidadPromedioXhora){
        this.patente = patente;
        this.capacidadPasajeros = capacidadPasajeros;
        this.listaViajes = new TreeSet<>(); // ordenados por id
        this.disponible = disponible;
        this.velocidadPromedioXhora = velocidadPromedioXhora;
    }

    public abstract String getPatente();
    public abstract int getCapacidadPasajeros(); // es igual a capacidad maxima
    //
    public abstract boolean estaDisponible();
    public abstract float getVelocidadPromedioXhora();

    public abstract boolean setDisponible(boolean disponible); // DUDA1
    public abstract float setVelocidadPromedioXhora(float velocidadPromedioXhora); // DUDA2
    //

    public String toString() {
        return "Transporte [ Patente: " + patente + ", Capacidad de Pasajeros: " + capacidadPasajeros + ", Disponible: " + disponible + ", Velocidad promedio por hora: " + velocidadPromedioXhora" ]";
    }
}