package model;

import java.util.Objects;

public class ColectivoSemiCama extends Transporte{
private float valorPorPasajerosPorKmRecorrido;

    public ColectivoSemiCama (String patente, boolean disponible, float velocidadPromedioXhora, float valorPorPasajerosPorKmRecorrido) {

        super(patente, 40, disponible, velocidadPromedioXhora); //La capacidad la mando como parametro fijo?
        this.valorPorPasajerosPorKmRecorrido = valorPorPasajerosPorKmRecorrido;
    }

public float getvalorPorPasajerosPorKmRecorrido(){
        return valorPorPasajerosPorKmRecorrido;
}

public void setvalorPorPasajerosPorKmRecorrido(float valorPorPasajerosPorKmRecorrido){
        this.valorPorPasajerosPorKmRecorrido=valorPorPasajerosPorKmRecorrido;
}

@Override
    public String toString(){
    return "ColectivoSemiCama [Valor Por Pasajeros Por Km Recorrido:" + valorPorPasajerosPorKmRecorrido + " ]";
}
}
