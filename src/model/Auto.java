package model;

import java.util.Objects;
/**
 * Representa un transporte de tipo Auto.
 * Tiene capacidad máxima de 4 pasajeros y su costo se calcula
 * mediante un valor base fijo más un valor por kilómetro recorrido.
 *
 * Los autos NO pueden realizar viajes de larga distancia según las reglas
 * del dominio (más de 100 km).
 */
public class Auto extends Transporte {
    private float ValorBasePorViaje;
    private float ValorPorKmRecorrido;

    /**
     * Constructor.
     *
     * @param patente patente única del auto.
     * @param velocidadPromedioXhora velocidad promedio del viaje.
     * @param _ValorBasePorViaje costo fijo inicial del viaje.
     * @param _ValorPorKmRecorrido costo variable por kilómetro.
     */
    public Auto (String patente, float velocidadPromedioXhora, float _ValorBasePorViaje, float _ValorPorKmRecorrido){
        super(patente,  4, velocidadPromedioXhora);
        this.ValorBasePorViaje = _ValorBasePorViaje;
        this.ValorPorKmRecorrido = _ValorPorKmRecorrido;
    } //Constructor tengo que cambiar el nombre de las variables como en c?


    public float  getValorBasePorViaje(){ return ValorBasePorViaje; }
    /**
     * Establece el valor base cobrado por viaje para este auto.
     * Solo se actualiza si el valor recibido es positivo.
     *
     * @param ValorBasePorViaje nuevo valor base por viaje.
     */
    public void setValorBasePorViaje(float ValorBasePorViaje){
        if(ValorBasePorViaje>0){
            this.ValorBasePorViaje=ValorBasePorViaje;
        }else {
            this.ValorBasePorViaje=0; //Tiene que ir?
            }
    }

    public float  getValorPorKmRecorrido() { return ValorPorKmRecorrido; }
    /**
     * Establece el valor cobrado por kilómetro recorrido.
     * Solo se actualiza si el valor recibido es positivo.
     *
     * @param ValorPorKmRecorrido nuevo valor por kilómetro recorrido.
     */
    public void setValorPorKmRecorrido(float ValorPorKmRecorrido){
        if(ValorPorKmRecorrido>0)
            this.ValorPorKmRecorrido=ValorPorKmRecorrido;
        }
    /**
     * Calcula el costo del viaje como:
     * valor base + (valor por km * kilómetros)
     *
     * @param kilometros distancia total.
     * @param pasajeros cantidad de pasajeros.
     * @return costo total del viaje.
     */
    @Override
    public float calculaCostoPorViaje(float kilometros,int pasajeros){
        return this.ValorBasePorViaje + (ValorPorKmRecorrido * kilometros);
    }
    /**
     * Devuelve una representación en texto del auto, incluyendo
     * los valores propios del transporte y sus tarifas configuradas.
     *
     * @return una cadena descriptiva del auto.
     */
    @Override
    public String toString() {
    return "Auto { Transporte= " + super.toString() + "[Valor Base Por viaje:" + ValorBasePorViaje + ",Valor por km recorrido: " + ValorPorKmRecorrido + " ]}";
    }
    /**
     * Determina si el auto puede realizar un viaje a un destino dado.
     * Los autos NO pueden realizar viajes de larga distancia (> 100 km).
     *
     * @param d destino a evaluar.
     * @return false si el destino es de larga distancia.
     */
    @Override
    public boolean cumpleCondiciones(Destino d){
        return !(d.esLargaDistancia());
    }
}