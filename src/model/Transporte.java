package model;

import java.io.Serializable;
import java.util.*; //ya implementa todos los paquetes necesarios
/**
 * Clase abstracta que modela un medio de transporte utilizado para viajes.
 * Define atributos comunes y el cálculo del costo variable según el tipo.
 */
public abstract class Transporte implements Serializable{
    private String patente;
    private int capacidadPasajeros;
    private Set<Viaje> listaViajes;
    private boolean disponible;
    private float velocidadPromedioXhora;
    /**
     * Constructor.
     *
     * @param patente patente única.
     * @param capacidadPasajeros cantidad máxima de pasajeros.
     * @param velocidadPromedioXhora velocidad promedio en km/h.
     */
    public Transporte(String patente, int capacidadPasajeros, float velocidadPromedioXhora){
        this.patente = patente;
        this.capacidadPasajeros = capacidadPasajeros;
        listaViajes = new TreeSet<>(); // Usa compareTo() de Viaje
        this.velocidadPromedioXhora = velocidadPromedioXhora;
    }

    public String getPatente(){ return patente; }

    public int getCapacidadPasajeros(){ return capacidadPasajeros; }

    public float getVelocidadPromedioXhora(){ return velocidadPromedioXhora; }
    /** @return viajes asignados. */

    public Set<Viaje> getListaViajes() {
        return Collections.unmodifiableSet(listaViajes);
    }
    /**
     * Determina si el transporte cumple las condiciones para realizar un viaje
     * hacia el destino especificado.
     *
     * @param d destino a evaluar.
     * @return true si cumple las reglas del tipo de transporte.
     */
    public boolean cumpleCondiciones(Destino d){
        return true;
    }
    /**
     * Agrega un viaje a la lista de viajes asignados a este transporte.
     *
     * @param v el viaje a agregar; si es {@code null}, no se realiza ninguna operación.
     */
    public void agregarViaje(Viaje v) {
        if(v != null)
            listaViajes.add(v);

    }

    /**
     * Indica si este transporte está disponible para asignarse a un nuevo viaje.
     * Un transporte está NO disponible si al menos uno de sus viajes
     * está pendiente o en curso.
     *
     * @return {@code true} si no tiene viajes pendientes o en curso,
     *         {@code false} en caso contrario.
     */
    public boolean estaDisponible(){
        boolean disp=true;
        Iterator<Viaje> Lv= listaViajes.iterator();
        while (disp && Lv.hasNext())
        {
            Viaje v= Lv.next();
            if(v.estaPendiente()||v.estaEnCurso()){
                disp=false;
            }
        }
        return disp;
    }
    /**
     * Elimina un viaje específico de la lista de viajes asignados a este transporte.
     *
     * @param v el viaje a quitar.
     */
    public void quitarViaje(Viaje v) {
        listaViajes.remove(v);
    }

    public void liberarTransporte() { disponible = true; }


    public void ocuparTransporte() { disponible = false; }

    public void setVelocidadPromedioXhora(float velocidadPromedioXhora){
        if(velocidadPromedioXhora > 0)
            this.velocidadPromedioXhora = velocidadPromedioXhora;
    }
    /**
     * Calcula el costo variable según la lógica del transporte concreto.
     *
     * @param kms kilómetros del viaje.
     * @param ps cantidad de pasajeros.
     * @return costo variable del viaje.
     */
    public abstract float  calculaCostoPorViaje(float kms, int ps);

    /**
     * Determina si este transporte es igual a otro objeto.
     * Dos transportes son iguales si tienen la misma patente.
     *
     * @param o el objeto a comparar.
     * @return {@code true} si ambos representan el mismo transporte,
     *         {@code false} en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transporte that = (Transporte) o;
        return Objects.equals(patente, that.patente);
    }
    /**
     * Genera un hash basado únicamente en la patente del transporte.
     *
     * @return el hash del objeto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(patente);
    }
    /**
     * Devuelve una descripción textual del transporte, incluyendo:
     * patente, capacidad, estado de disponibilidad, velocidad promedio
     * y los nombres de los viajes asignados.
     *
     * @return cadena descriptiva del transporte.
     */
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