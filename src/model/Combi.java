package model;

import java.util.Objects;

public class Combi extends Transporte {

    private float valorBasePorViaje;
    private float valorPorPasajeroPorKmRecorrido;

    public Combi(String patente, boolean disponible, float velocidadPromedioXhora, float valorBasePorViaje, float valorPorPasajeroPorKmRecorrido) {

        super(patente, 16, disponible, velocidadPromedioXhora); //La capacidad la mando como parametro fijo?
        this.valorBasePorViaje = valorBasePorViaje;
        this.valorPorPasajeroPorKmRecorrido = valorPorPasajeroPorKmRecorrido;
    } //Tengo que cambiar los nombres como en c??

    public float getValorBasePorViaje() {
        return valorBasePorViaje;
    }

    public void setValorBasePorViaje(float valorBasePorViaje) {
        if (valorBasePorViaje>0){
        this.valorBasePorViaje = valorBasePorViaje;
    }    }

    public float getValorPorPasajeroPorKmRecorrido() {
        return valorPorPasajeroPorKmRecorrido;
    }

    public void setValorPorPasajeroPorKmRecorrido(float valorPorPasajeroPorKmRecorrido) {
        if(valorPorPasajeroPorKmRecorrido>0){
        this.valorPorPasajeroPorKmRecorrido = valorPorPasajeroPorKmRecorrido;
    }    }


    @Override
    public float calculaCostePorViaje(float kms, int ps) {
        return 0;
    }

    @Override
    public String toString() {
        return "Combi [Valor Base Por viaje:" + valorBasePorViaje + ",Valor por pasajero por km recorrido: " + valorPorPasajeroPorKmRecorrido + " ]";
    }
}