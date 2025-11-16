package model;

import exceptions.*;

import java.io.Serializable;
import java.util.*;
import java.util.Set.*;

public class Agencia implements Serializable {

    private Set<Transporte> ListaTransporte;
    private Set<ResponsableABordo> SetResponsables;
    private Set<Destino> DestinosDisponibles; // catálogo
    private Map<Destino, Integer> CantidadDeViajesxDestino;
    int cantViajesCreados;

    private static Agencia instancia; // Necesario para el patron Singleton

    private Agencia() {
        this.ListaTransporte = new HashSet<>();
        this.SetResponsables = new HashSet<>();
        this.DestinosDisponibles = new HashSet<>();
        this.CantidadDeViajesxDestino = new HashMap<>();
        cantViajesCreados=0;
    }

    public static Agencia getInstance() {
        if (instancia == null) {
            instancia = new Agencia();
        }
        return instancia;


    }
    // #### GETTERS SEGUROS (solo lectura) ####
    public Set<Transporte> getTransportes() {
        return Collections.unmodifiableSet(ListaTransporte);
    }

    public Set<Destino> getDestinos() {
        return Collections.unmodifiableSet(DestinosDisponibles);
    }

    public Set<ResponsableABordo> getResponsables() {
        return Collections.unmodifiableSet(SetResponsables);
    }

    public int obtenerProximoNumeroDeViaje(Destino d) {
        return CantidadDeViajesxDestino.getOrDefault(d, 0) + 1;
    }

    public void agregarDestino(Destino d) {
        if (DestinosDisponibles.contains(d)) {
            throw new DestinoYaExisteException(d.getNombre());
        }
        DestinosDisponibles.add(d);
    }

    public Set<Transporte> transportesPorDestino(Destino d) {
        Set<Transporte> ListaDisponibles= new HashSet<Transporte>();

        for (Transporte t : this.ListaTransporte){
            if(t.estaDisponible() && t.cumpleCondiciones(d)){
                ListaDisponibles.add(t);
            }
        }
        return ListaDisponibles;
    }


    public void Agregar_TransporteALista(Transporte t){
        ListaTransporte.add(t);
    }


    public Transporte buscarTransportePorPatente(String patente){
        for(Transporte t : ListaTransporte){
            if(t.getPatente().equalsIgnoreCase(patente)){
                return t;
            }
        }
        return null;
    }

    public Destino buscarDestinoPorNombre(String nombre){
        for(Destino d : DestinosDisponibles){
            if(d.getNombre().equalsIgnoreCase(nombre)){
                return d;
            }
        }
        return null;
    }



    public Viaje crearViaje(String nombreViaje, Destino destino,int cantPasajeros, Transporte t){
        if(destino == null)
            throw new IllegalArgumentException("Destino no existente"); //Si el destinol no existe

        if(t==null)
            throw new IllegalArgumentException("Transporte no puede ser nulo"); //Si el transporte Cargado es nulo

        if(!t.estaDisponible())
        {
            throw new ValidacionException("El transporte " + t.getPatente() + " ya no está disponible.");
        }
        if(!t.cumpleCondiciones(destino)){
            throw new ValidacionException("El transporte " + t.getPatente() + " No cumple las condiciones para este destino.");
        }
        if (t.getCapacidadPasajeros() < cantPasajeros) {
            throw new ValidacionException("Capacidad excedida. El transporte solo permite " + t.getCapacidadPasajeros());
        }

        Viaje nuevoViaje;

        if(destino.esLargaDistancia()){
            nuevoViaje = new LargaDistancia(cantViajesCreados+1,nombreViaje,destino,cantPasajeros,t);
        }else {
            nuevoViaje = new CortaDistancia(cantViajesCreados+1,nombreViaje,destino,cantPasajeros,t);
        }
        t.agregarViaje(nuevoViaje);

        CantidadDeViajesxDestino.put(destino,CantidadDeViajesxDestino.getOrDefault(destino, 0) + 1);

        return  nuevoViaje;
    }





}

