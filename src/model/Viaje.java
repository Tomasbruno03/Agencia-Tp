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
    private int cantPasajeros; // Empieza en 1. Suponiendo que al crear el viaje es porque al menos un pasajero lo solicito
    private estado estadoActual;
    private Set<ResponsableABordo>Responsables= new HashSet<>();
    private float avanceKmRecorridos;
    private Transporte TransporteAsignado;


    // CONSTRUCTOR
    public Viaje(int idVia, String nom, Destino destinoViaje,Transporte t) {
        this.idViaje = idVia;
        this.nombre = nom;
        this.destinoDelViaje = destinoViaje;
        this.cantPasajeros = 1;
        this.estadoActual = estado.PENDIENTE; // Estado inicial
        this.avanceKmRecorridos = 0;
        this.TransporteAsignado=t;

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

    public Transporte getTransporteAsignado() {
        return TransporteAsignado;
    }

    @Override
    public int compareTo(Viaje o) {
        return nombre.compareTo(o.getNombre());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Viaje viaje = (Viaje) o;
        return idViaje == viaje.getIdViaje();
    }

    @Override
    public int hashCode() {
        return Objects.hash(idViaje);
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
        liberarResponsables();
    }
    public estado getEstado(){return estadoActual;};
    public abstract float calcularCosto();

    public void liberarResponsables()
    {
        Responsables.clear();
    }

    public void AgregarResponsable(ResponsableABordo r)
    {
        this.Responsables.add(r);
    }

    public void QuitarResponsable(ResponsableABordo r)
    {
        this.Responsables.remove(r);
    }

    public void AgregarPasajeros()
    {
        if(TransporteAsignado!=null&&(cantPasajeros==TransporteAsignado.getCapacidadPasajeros())){
            throw new IllegalStateException("No hay capacidad disponible en el transporte.");
        }else{
            this.cantPasajeros++;
        }

    }

    public void QuitarPasajeros()
    {
        this.cantPasajeros--;
    }
    public void setTransporteAsignado(Transporte transporte) {
        this.TransporteAsignado = transporte;
    }
    public boolean estaPendiente() {
        return estadoActual == estado.PENDIENTE;
    }

    public boolean estaEnCurso() {
        return estadoActual == estado.EN_CURSO;
    }

    public boolean estaFinalizado() {
        return estadoActual == estado.FINALIZADO;
    }
    public float getKmTotales() {
        return destinoDelViaje.getCantKm();
    }

    public float getKmRestantes() {
        return getKmTotales() - avanceKmRecorridos;
    }

    public float getPorcentajeAvance() {
        float total = getKmTotales();
        return total <= 0 ? 0 : (avanceKmRecorridos / total) * 100f;
    }



}
