package model;

import exceptions.*;

import java.io.Serializable;
import java.util.*;
import java.util.Set.*;

public class Agencia implements Serializable {

    private Set<Transporte> ListaTransporte;
    private Set<ResponsableABordo> SetResponsables;
    private Set<Destino> DestinosDisponibles; // catálogo
    private Map<Destino, Set<Transporte>> TreeMapaDestinos;
    private Map<Destino, Integer> CantidadDeViajesxDestino;

    private static Agencia instancia; // Necesario para el patron Singleton

    private Agencia() {
        this.ListaTransporte = new HashSet<>();
        this.SetResponsables = new HashSet<>();
        this.DestinosDisponibles = new HashSet<>();
        this.TreeMapaDestinos = new HashMap<>();
        this.CantidadDeViajesxDestino = new HashMap<>();
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

    public void agregarDestino(Destino d) {
        if (DestinosDisponibles.contains(d)) {
            throw new DestinoYaExisteException(d.getNombre());
        }
        DestinosDisponibles.add(d);
    }

    public Set<Transporte> transportesPorDestino(Destino destino) {
        return TreeMapaDestinos.getOrDefault(destino, Collections.emptySet());
    }


    public void Agregar_TransporteALista(Transporte t){
        ListaTransporte.add(t);
    }

    public void agregarTransportePorDestino(Transporte t) {
        for (Viaje viaje : t.getListaViajes()) {
            Destino destino = viaje.getDestinoDelViaje();
            Set<Transporte> L = transportesPorDestino(destino);

            if (L == null) {
                L = new HashSet<>();
                L.add(t);
                TreeMapaDestinos.put(destino, L);
            } else {
                L.add(t);
            }
        }
    }






}

