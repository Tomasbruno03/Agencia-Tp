package model;

import exceptions.ValidacionException;

import java.beans.DesignMode;
import java.util.*;

;
/**
 * Representa un viaje dentro del sistema.
 * Cada viaje posee:
 * <ul>
 *     <li>ID único</li>
 *     <li>Nombre único</li>
 *     <li>Destino</li>
 *     <li>Cantidad de pasajeros</li>
 *     <li>Estado (pendiente, en curso, finalizado)</li>
 *     <li>Transporte asignado</li>
 *     <li>Responsables a bordo (solo larga distancia)</li>
 * </ul>
 *
 * Además, el viaje registra el avance en kilómetros.
 */
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

    /**
     * Constructor
     *
     * @param idVia id único del viaje.
     * @param nom nombre del viaje.
     * @param destinoViaje destino asignado.
     * @param cantPasajeros cantidad de pasajeros.
     * @param t transporte asignado.
     */
    // CONSTRUCTOR
    public Viaje(int idVia, String nom, Destino destinoViaje,int cantPasajeros,Transporte t) {
        this.idViaje = idVia;
        this.nombre = nom;
        this.destinoDelViaje = destinoViaje;
        this.cantPasajeros = cantPasajeros;
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

    public Set<ResponsableABordo> getResponsables() {
        return Collections.unmodifiableSet(Responsables);
    }

    public Transporte getTransporteAsignado() {
        return TransporteAsignado;
    }

    @Override
    public int compareTo(Viaje o) {
        return nombre.compareTo(o.getNombre());
    }
    /**
     * Determina si dos viajes son iguales mediante su identificador único.
     *
     * @param o objeto a comparar.
     * @return true si tienen el mismo id, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Viaje viaje = (Viaje) o;
        return idViaje == viaje.getIdViaje();
    }
    /**
     * Calcula el hash del viaje con base en el id.
     *
     * @return valor hash del viaje.
     */
    @Override
    public int hashCode() {
        return Objects.hash(idViaje);
    }


    public abstract float calcularCostoBase();

    public float calcularCostoFinal()
    {
        // Usa el nombre correcto del método abstracto de Transporte
        return calcularCostoBase() + TransporteAsignado.calculaCostoPorViaje(destinoDelViaje.getCantKm(),cantPasajeros);
    }


    public void iniciar() {
        if (estadoActual != estado.PENDIENTE)
            throw new IllegalStateException("El viaje ya fue iniciado o finalizado.");
        if (this.cantPasajeros <= 0)
            throw new IllegalStateException("No se puede iniciar un viaje con 0 pasajeros.");
        estadoActual = estado.EN_CURSO;
    }
    /**
     * Avanza la distancia recorrida en km.
     *
     * @param delta kilómetros a sumar.
     * @throws IllegalStateException si no está en curso.
     * @throws IllegalArgumentException si delta es inválido.
     */
    public void avanzarKm(float delta) {
        if (estadoActual != estado.EN_CURSO)
            throw new IllegalStateException("Solo se puede avanzar un viaje en curso.");
        if (delta <= 0)
            throw new IllegalArgumentException("La distancia debe ser positiva.");
        avanceKmRecorridos += delta;
    }


    /**
     * Finaliza el viaje, libera responsables y marca estado finalizado.
     *
     * @throws IllegalStateException si no está en curso.
     */
    public void finalizar() {
        if (estadoActual != estado.EN_CURSO)
            throw new IllegalStateException("Solo se puede finalizar un viaje en curso.");
        estadoActual = estado.FINALIZADO;
        liberarResponsables();
    }
    public estado getEstado(){return estadoActual;}

    /**
     * Libera a todos los responsables asignados al viaje, acumula
     * sus kilómetros recorridos y limpia la lista de responsables.
     */
    public void liberarResponsables()
    {
        Iterator<ResponsableABordo> res=Responsables.iterator();
        while (res.hasNext())
        {
            ResponsableABordo r=res.next();
            r.AcumularKmRecorridos(avanceKmRecorridos);
            r.Liberar();
            res.remove();
        }
    }

    public void AgregarResponsable(ResponsableABordo r)
    {
        this.Responsables.add(r);
    }

    public void QuitarResponsable(ResponsableABordo r)
    {
        this.Responsables.remove(r);
    }

    public void AgregarPasajeros(int n)
    {
        if(TransporteAsignado!=null&&(cantPasajeros+n>TransporteAsignado.getCapacidadPasajeros())){
            throw new IllegalStateException("No hay capacidad disponible en el transporte.");
        }else{
            this.cantPasajeros+=n;
        }

    }


    public void AgregarUnPasajero()
    {
        this.AgregarPasajeros(1);
    }

    public void QuitarPasajeros()
    {
        this.cantPasajeros--;
    }
    /**
     * Asigna un transporte al viaje validando capacidad y restricciones.
     *
     * @param transporte transporte a asignar.
     * @throws ValidacionException si el transporte es nulo o no tiene capacidad suficiente.
     */
    public void setTransporteAsignado(Transporte transporte) throws ValidacionException {
        if (transporte == null) {
            throw new ValidacionException("El transporte no puede ser nulo.");
        }

        if (transporte.getCapacidadPasajeros() < this.cantPasajeros) {
            throw new ValidacionException("Capacidad excedida. El transporte solo permite " +
                    transporte.getCapacidadPasajeros() + " pasajeros (este viaje tiene " + this.cantPasajeros + ").");
        }

        // 2. Validar las restricciones del TIPO de viaje (Doble Dispatch)
        //this.validarRestriccionTransporte(transporte);

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
    /**
     * Devuelve una representación textual del viaje, incluyendo información
     *  como identificador, nombre, destino, cantidad de pasajeros,
     * estado actual, kilómetros avanzados y el transporte asignado.
     * <p>
     * El nombre de la clase concreta (CortaDistancia o LargaDistancia) se
     * obtiene dinámicamente mediante {@code getClass().getSimpleName()}.
     *
     * @return una cadena descriptiva con los datos principales del viaje.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + " { " + "id=" + idViaje + ", nombre='" + nombre + ", destino=" + destinoDelViaje.getNombre() + ", Cantidad de pasajeros=" + cantPasajeros + ", estado=" + estadoActual + ", Kilometros avanzados=" + avanceKmRecorridos + ", transporte asignado =" + TransporteAsignado.getPatente() + " }";
    }


}
