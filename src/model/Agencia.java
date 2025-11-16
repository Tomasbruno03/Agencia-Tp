package model;

import exceptions.*;

import java.io.Serializable;
import java.util.*;
import java.util.Set.*;

/**
* Representa la agencia de turismo que administra destinos, transporte, responsable a bordo y viaje
 *Implementa el patron Singleton
*
*La agencia es responsable de:
* <ul>
 *      <li>Registrar destinos.</li>
 *      <li>Registrar transportes disponibles.</li>
 *      <li>Registrar responsables a bordo.</li>
 *      <li>Crear viajes verificando todas las restricciones correspondientes.</li>
 *      <li>Generar reportes de recaudación y ranking de responsables.</li>
 * </ul>
 */
public class Agencia implements Serializable {

    /**
     *  Conjunto de transportes registrados en la agencia
     */
    private Set<Transporte> ListaTransporte;
    /**
     * Conjunto de responsables a bordo registrados.
     */
    private Set<ResponsableABordo> SetResponsables;
    /**
     * Conjunto de destinos habilitados por la agencia.
     */
    private Set<Destino> DestinosDisponibles; // catálogo
    /**
     * Mapa que relaciona cada destino con la cantidad de viajes creados hacia él.
     */
    private Map<Destino, Integer> CantidadDeViajesxDestino;
    /**
     * Contador interno de viajes creados, para asignar IDs únicos.
     */
    int cantViajesCreados;

    /**
     * Instancia única para implementar el patrón Singleton.
     */

    private static Agencia instancia; // Necesario para el patron Singleton

    /**
     * Constructor privado. Inicializa las colecciones principales.
     */
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

    public void agregarDestino(Destino d) {
        if (DestinosDisponibles.contains(d)) {
            throw new DestinoYaExisteException(d.getNombre());
        }
        DestinosDisponibles.add(d);
    }

    public Set<Transporte> transportesParaDestino(Destino d) {
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

    public int obtenerProximoNumeroDeViaje(Destino d) {
        return CantidadDeViajesxDestino.getOrDefault(d, 0) + 1;
    }

    public Transporte buscarTransportePorPatente(String patente){
        for(Transporte t : ListaTransporte){
            if(t.getPatente().equalsIgnoreCase(patente)){
                return t;
            }
        }
        return null;
    }
    /**
     * Recibe un Responsable (creado por LectorJSON) y lo añade
     * al Set de responsables de la agencia.
     */
    public void agregarResponsable(ResponsableABordo r) {
        if (r != null) {
            // Añade al atributo 'SetResponsables' que definiste arriba
            this.SetResponsables.add(r);
        }
    }

    public Destino buscarDestinoPorNombre(String nombre){
        for(Destino d : DestinosDisponibles){
            if(d.getNombre().equalsIgnoreCase(nombre)){
                return d;
            }
        }
        return null;
    }

    public Viaje buscarViajePorId(int idViaje) {
        for (Transporte t : ListaTransporte) {
            for (Viaje v : t.getListaViajes()) {
                if (v.getIdViaje() == idViaje) {
                    return v;
                }
            }
        }
        return null;
    }

    public ResponsableABordo buscarResponsablePorDni(String dni) {
        for (ResponsableABordo r : SetResponsables) {
            if (r.GetDni().equalsIgnoreCase(dni)) {
                return r;
            }
        }
        return null;
    }

    public List<ResponsableABordo> GenerarRankingResponsables()
    {
        List<ResponsableABordo> r= new ArrayList<>(this.getResponsables());
        Collections.sort(r);
        return r;
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

    public Map<Destino, Float> getReporteRecaudacionPorDestino() {


        Map<Destino, Float> recaudacion = new HashMap<>();

        for (Transporte t : this.ListaTransporte) {

            for (Viaje v : t.getListaViajes()) {
                if (v.estaFinalizado()) {
                    Destino d = v.getDestinoDelViaje();
                    float costo = v.calcularCostoFinal(); // (Asegurate de corregir esto)
                    recaudacion.put(d, recaudacion.getOrDefault(d, 0f) + costo);
                }
            }
        }
        return recaudacion;
    }
    public Map<Transporte,Float> GenerarReporteRecaudadoPorTransporte()
    {
        Map<Transporte,Float>recaudacion = new HashMap<>();;

        for(Transporte t: ListaTransporte)
        {

            for(Viaje v: t.getListaViajes())
            {
                if(v.estaFinalizado())
                {
                    float recaudadoxT=v.calcularCostoFinal();
                    recaudacion.put(t, recaudacion.getOrDefault(t, 0f) + recaudadoxT);
                }

            }
        }

        return recaudacion;
    }





}

