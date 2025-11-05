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

    public agregarDestino(Destino d){
        try {

        }catch (DestinoYaExisteException e)
        {
            System.err.println(e.getMessage());
        }
    }




}

