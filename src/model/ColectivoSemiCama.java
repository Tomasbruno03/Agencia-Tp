package model;

import java.util.Objects;
/**
 * Representa un colectivo Semi-Cama.
 * Tiene capacidad de 40 pasajeros y su costo depende del
 * valor por pasajero por kilómetro recorrido.
 */
public class ColectivoSemiCama extends Transporte{
    private float valorPorPasajerosPorKmRecorrido;
    /**
     * Constructor.
     *
     * @param patente patente única.
     * @param velocidadPromedioXhora velocidad promedio.
     * @param valorPorPasajerosPorKmRecorrido costo por pasajero por km.
     */
    public ColectivoSemiCama (String patente, float velocidadPromedioXhora, float valorPorPasajerosPorKmRecorrido) {

        super(patente, 40, velocidadPromedioXhora); //La capacidad la mando como parametro fijo?
        this.valorPorPasajerosPorKmRecorrido = valorPorPasajerosPorKmRecorrido;
    }

    public float getvalorPorPasajerosPorKmRecorrido(){
        return valorPorPasajerosPorKmRecorrido;
}

    public void setvalorPorPasajerosPorKmRecorrido(float valorPorPasajerosPorKmRecorrido){
        this.valorPorPasajerosPorKmRecorrido=valorPorPasajerosPorKmRecorrido;
    }
    /**
     * Calcula el costo total multiplicando:
     * valor por pasajero * cantidad pasajeros * kms.
     */
    @Override
    public float calculaCostoPorViaje(float kms, int ps) {
        return valorPorPasajerosPorKmRecorrido * ps * kms;
    }
    /**
     * Devuelve una representación textual del colectivo semi cama,
     * incluyendo los datos del transporte y la tarifa por pasajero.
     *
     * @return descripción del colectivo semi cama.
     */
    @Override
    public String toString(){
    return "ColectivoSemiCama { Transporte =" + super.toString() + " [Valor Por Pasajeros Por Km Recorrido:" + valorPorPasajerosPorKmRecorrido + " ]}";
    }
}
