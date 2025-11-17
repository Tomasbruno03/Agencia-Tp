package model;

import java.util.Objects;
/**
 * Representa un responsable a bordo asignado a viajes de larga distancia.
 * Acumula kilómetros recorridos y posee un sueldo fijo por viaje.
 */
public class ResponsableABordo implements Comparable<ResponsableABordo> {
    private String nombre;
    private Boolean EstaDisp; //S o N
    private String Dni;
    private float SueldoXViaje;
    private float CantKmAcumulados;

    /**
     * Constructor.
     *
     * @param nombre nombre del responsable.
     * @param EstaDisp indica si está disponible.
     * @param Dni DNI del responsable.
     * @param SueldoXViaje sueldo fijo por viaje.
     */
    public ResponsableABordo(String nombre, Boolean EstaDisp, String Dni, float SueldoXViaje){
        this.nombre = nombre;
        this.EstaDisp = EstaDisp;
        this.Dni = Dni;
        this.SueldoXViaje = SueldoXViaje;
        CantKmAcumulados=0;
    }
    public String GetNombre(){
        return nombre;
    }
    public Boolean GetEstaDisp(){
        return EstaDisp;
    }
    public String GetDni(){
        return Dni;
    }
    public float GetSueldo(){
        return SueldoXViaje;
    }
    public float getCantKmAcumulados(){return CantKmAcumulados;}

    /**
     * Acumula kilómetros recorridos en un viaje finalizado.
     *
     * @param km kilómetros recorridos.
     */
    public void AcumularKmRecorridos(float km)
    {
        CantKmAcumulados+=km;
    }

    public void Ocupar() { //unico set que podriamos llegar a necesitar, dar de baja o no el responsable
        EstaDisp = false;
    }
    /**
     * Libera al responsable dejándolo disponible.
     */
    public void Liberar(){
        EstaDisp=true;
    }

    /**
     * Compara este responsable con otro en función de los kilómetros acumulados.
     * El orden es descendente: el responsable con más kilómetros queda antes.
     *
     * @param otro el responsable con el cual se compara.
     * @return un valor negativo si este responsable tiene más kilómetros,
     *         cero si tienen la misma cantidad,
     *         o un valor positivo si este responsable tiene menos kilómetros.
     */
    @Override
    public int compareTo(ResponsableABordo otro){
        return Float.compare(otro.getCantKmAcumulados(), this.getCantKmAcumulados()); //devuelve 0 si son iguales,-1 o 1
    }
    /**
     * Determina si este responsable es igual a otro objeto.
     * Dos responsables se consideran iguales si tienen el mismo DNI.
     *
     * @param o el objeto a comparar.
     * @return {@code true} si ambos objetos representan al mismo responsable,
     *         {@code false} en caso contrario.
     */
    @Override
    public boolean equals(Object o){
        if(this == o) //son el mismo objeto en memoria?
            return true;
        if(o == null || getClass() != o.getClass()) // o el objeto es nulo o son de clases distintas
            return false;
        ResponsableABordo that  = (ResponsableABordo) o; //casteo necesario para usar una variable de tipo clase
        return Objects.equals(this.Dni,that.GetDni());
    }
    /**
     * Retorna una representación en texto del responsable,
     * mostrando su nombre, DNI y si se encuentra disponible.
     *
     * @return una cadena descriptiva del responsable.
     */
    @Override
    public String toString(){
        return "Responsable [Nombre: " + nombre + ", DNI: " + Dni + ", Disponible: " + EstaDisp + "]";
    }
}