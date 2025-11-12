package model;

import java.io.Serializable;
import java.util.*; //ya implementa todos los paquetes necesarios

public abstract class Transporte implements Serializable{
    private String patente;
    private int capacidadPasajeros;
    private Set<Viaje> listaViajes;
    private boolean disponible;
    private float velocidadPromedioXhora;

    public Transporte(String patente, int capacidadPasajeros, boolean disponible, float velocidadPromedioXhora){
        this.patente = patente;
        this.capacidadPasajeros = capacidadPasajeros;
        listaViajes = new TreeSet<>(); // Usa compareTo() de Viaje
        this.disponible = disponible;
        this.velocidadPromedioXhora = velocidadPromedioXhora;
    }

    public String getPatente(){ return patente; }
    public int getCapacidadPasajeros(){ return capacidadPasajeros; }
    public boolean estaDisponible(){ return disponible; }
    public float getVelocidadPromedioXhora(){ return velocidadPromedioXhora; }

    public Set<Viaje> getListaViajes() {
        return Collections.unmodifiableSet(listaViajes);
    }

    public void agregarViaje(Viaje v) {
        if( v != null)
            listaViajes.add(v);
    }

    public void quitarViaje(Viaje v) {
        listaViajes.remove(v);
    }

    public void liberarTransporte() { disponible = true; }

    public void ocuparTransporte() { disponible = false; }

    public void setVelocidadPromedioXhora(float velocidadPromedioXhora){
        if(velocidadPromedioXhora > 0)
            this.velocidadPromedioXhora = velocidadPromedioXhora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transporte that = (Transporte) o;
        return Objects.equals(patente, that.patente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patente);
    }

    @Override
    public String toString() {
        String viajesNombres = "";

        for (Viaje v : listaViajes) {
            viajesNombres += v.getNombre() + ", ";
        }

        // Si hay viajes, eliminamos la última coma y espacio
        if (!listaViajes.isEmpty()) {
            viajesNombres = viajesNombres.substring(0, viajesNombres.length() - 2);
        } else {
            viajesNombres = "Sin viajes asignados";
        }

        return "Transporte [ Patente: " + patente
                + ", Capacidad de Pasajeros: " + capacidadPasajeros
                + ", Disponible: " + disponible
                + ", Velocidad promedio por hora: " + velocidadPromedioXhora
                + ", Viajes: " + viajesNombres + " ]";
    }
}