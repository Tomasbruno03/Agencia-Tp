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

    public void asociarTransporteADestino(Destino destino, Transporte transporte){ //chequear con agregar transporte por destino?????
        if(destino == null || transporte == null)
            throw new IllegalArgumentException("Destiono o transporte es null.");

        Set<Transporte> lista = TreeMapaDestinos.get(destino); //Obtiene el set

        if(lista==null){ //Creo el set
            lista = new HashSet<>();
            TreeMapaDestinos.put(destino, lista);
        }
        lista.add(transporte); //Agregar transporte a la lista
    }

    public Viaje crearViaje(String nombreViaje, Destino destino, int cantPasajeros){
        if(destino == null)
            throw new IllegalArgumentException("Destino no existente"); //VER

        Viaje nuevoViaje;

        if(destino.esLargaDistancia()){
            nuevoViaje = new LargaDistancia(nombreViaje,destino,cantPasajeros);
        }else {
            nuevoViaje = new CortaDistancia(nombreViaje,destino,cantPasajeros);
        }

        CantidadDeViajesxDestino.put(destino,CantidadDeViajesxDestino.getOrDefault(destino, 0) + 1);

        return  nuevoViaje;
    }





}

