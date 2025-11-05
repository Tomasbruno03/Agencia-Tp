package model;

import java.beans.DesignMode;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

;

public abstract class Viaje implements Comparable <Viaje>{


    // #### DECLARACION DE VARIABLES ####
    private int idViaje; //Es clave primariaAutoincremental
    private String nombre; // El nombre es unico, del viaje del Destino y el Id
    private Destino destinoDelViaje;
    private int cantPasajeros; // Empieza en 0
    private estado estadoActual;
    private Set<ResponsableABordo>Responsables= new HashSet<>();
    private float avanceKmRecorridos;


    // CONSTRUCTOR
    public Viaje(int idVia, String nom, Destino destinoViaje) {
        this.idViaje = idVia;
        this.nombre = nom;
        this.destinoDelViaje = destinoViaje;
        this.cantPasajeros = 1;
        this.estadoActual = estado.PENDIENTE; // Estado inicial
        this.avanceKmRecorridos = 0;
    }
    /*
    GETTERS
    Y
    SETTERS
    */

    public int getIdViaje(){
        return idViaje;
    }

    public String getNombre() {
        return nombre;
    }

    public Destino getDestinoDelViaje() {
        return destinoDelViaje;
    }

    public int getCantPasajeros() {
        return cantPasajeros;
    }

    public float getAvanceKmRecorridos() {
        return avanceKmRecorridos;
    }

    @Override
    public int compareTo(Viaje o) {
        return nombre.compareTo(o.getNombre());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Viaje viaje = (Viaje) o;
        return idViaje == viaje.idViaje;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idViaje, nombre, estadoActual);
    }
    public void iniciar() {
        if (estadoActual != estado.PENDIENTE)
            throw new IllegalStateException("El viaje ya fue iniciado o finalizado.");
        if (Responsables.isEmpty())
            throw new IllegalStateException("Los viajes largos requieren al menos un responsable.");
        estadoActual = estado.EN_CURSO;
    }

    public void avanzarKm(float delta) {
        if (estadoActual != estado.EN_CURSO)
            throw new IllegalStateException("Solo se puede avanzar un viaje en curso.");
        if (delta <= 0)
            throw new IllegalArgumentException("La distancia debe ser positiva.");
        avanceKmRecorridos += delta;
    }

    public void finalizar() {
        if (estadoActual != estado.EN_CURSO)
            throw new IllegalStateException("Solo se puede finalizar un viaje en curso.");
        estadoActual = estado.FINALIZADO;
        //liberarResponsables(); #### Aun no esta definida
    }
    public abstract float calcularCosto();

}
