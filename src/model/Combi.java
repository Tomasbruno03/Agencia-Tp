package model;

import java.util.Objects;
/**
 * Representa un transporte de tipo Combi.
 * Tiene capacidad fija de 16 pasajeros y su costo consiste en:
 * valor base fijo + valor por pasajero por kilómetro recorrido.
 */
public class Combi extends Transporte {

    private float valorBasePorViaje;
    private float valorPorPasajeroPorKmRecorrido;
    /**
     * Constructor.
     *
     * @param patente patente única.
     * @param velocidadPromedioXhora velocidad promedio.
     * @param valorBasePorViaje costo fijo inicial.
     * @param valorPorPasajeroPorKmRecorrido costo variable por pasajero y km.
     */
    public Combi(String patente, float velocidadPromedioXhora, float valorBasePorViaje, float valorPorPasajeroPorKmRecorrido) {
        super(patente, 16, velocidadPromedioXhora); //La capacidad la mando como parametro fijo?
        this.valorBasePorViaje = valorBasePorViaje;
        this.valorPorPasajeroPorKmRecorrido = valorPorPasajeroPorKmRecorrido;
    }
    /**
     * Devuelve el valor base cobrado por viaje para una combi.
     *
     * @return valor base por viaje.
     */
    public float getValorBasePorViaje() {
        return valorBasePorViaje;
    }

    public void setValorBasePorViaje(float valorBasePorViaje) {
        if (valorBasePorViaje>0)
            this.valorBasePorViaje = valorBasePorViaje;
    }

    public float getValorPorPasajeroPorKmRecorrido() {
        return valorPorPasajeroPorKmRecorrido;
    }

    public void setValorPorPasajeroPorKmRecorrido(float valorPorPasajeroPorKmRecorrido) {
        if(valorPorPasajeroPorKmRecorrido>0)
            this.valorPorPasajeroPorKmRecorrido = valorPorPasajeroPorKmRecorrido;
    }

    /**
     * @return costo total según:
     * valor base + (valor por pasajero por km * pasajeros * kms)
     */
    @Override
    public float calculaCostoPorViaje(float kms, int ps) {
        return valorBasePorViaje + (valorPorPasajeroPorKmRecorrido * ps * kms);
    }
    /**
     * Devuelve una representación textual de la combi,
     * incluyendo los valores heredados del transporte y
     * sus tarifas configuradas.
     *
     * @return una cadena descriptiva de la combi.
     */
    @Override
    public String toString() {
        return "Combi { Transporte = " + super.toString() + "[Valor Base Por viaje:" + valorBasePorViaje + ",Valor por pasajero por km recorrido: " + valorPorPasajeroPorKmRecorrido + " ]}";
    }

}