package model;

import java.util.*;
/**
 * Representa un colectivo Coche Cama.
 * Capacidad total: 32 pasajeros.
 *  - 26 plazas cama
 *  - 6 plazas semi cama
 *
 * El costo depende de:
 *  - valor por pasajero por km
 *  - valor adicional por cada plaza tipo cama utilizada
 */
public class ColectivoCocheCama extends Transporte{
private int CantSemiCama;
private int CantCama;
private float ValorPorPasajeroPorKmRecorrido;
private float ValorPlazaTipoCamaPorKmRecorrido;
    /**
     * Constructor.
     *
     * @param patente patente única.
     * @param velocidadPromedioXhora velocidad promedio.
     * @param ValorPorPasajeroPorKmRecorrido costo base por pasajero por km.
     * @param ValorPlazaTipoCamaPorKmRecorrido extra por cada plaza cama.
     */
    public ColectivoCocheCama (String patente, float velocidadPromedioXhora, float ValorPorPasajeroPorKmRecorrido, float ValorPlazaTipoCamaPorKmRecorrido){
        super(patente, 32, velocidadPromedioXhora);
        this.CantSemiCama = 6;
        this.CantCama = 26;
        this.ValorPorPasajeroPorKmRecorrido= ValorPorPasajeroPorKmRecorrido;
        this.ValorPlazaTipoCamaPorKmRecorrido= ValorPlazaTipoCamaPorKmRecorrido;
    }

    public int getCantSemiCama(){return CantSemiCama;}

    public int getCantCama(){return CantCama;}

    public float getValorPorPasajeroPorKmRecorrido(){return ValorPorPasajeroPorKmRecorrido;}

    public float getValorPlazaTipoCamaPorKmRecorrido(){return ValorPlazaTipoCamaPorKmRecorrido;}

    public void setValorPorPasajeroPorKmRecorrido(float ValorPorPasajeroPorKmRecorrido){
        this.ValorPorPasajeroPorKmRecorrido= ValorPorPasajeroPorKmRecorrido;
    }

    public void setValorPlazaTipoCamaPorKmRecorrido(float ValorPlazaTipoCamaPorKmRecorrido){
        this.ValorPlazaTipoCamaPorKmRecorrido=ValorPlazaTipoCamaPorKmRecorrido;
    }
    /**
     * Calcula el costo total del viaje considerando:
     *  - costo por pasajero por km
     *  - costo adicional por plazas tipo cama (hasta 26)
     *
     * @param kms kilómetros del viaje.
     * @param ps cantidad de pasajeros.
     * @return costo total.
     */
    @Override
    public float calculaCostoPorViaje(float kms, int ps) {
        int pasajerosEnCama = Math.min(ps, CantCama); // hasta 26 camas

        float costoBase = ValorPorPasajeroPorKmRecorrido * ps * kms;
        float costoCamas = ValorPlazaTipoCamaPorKmRecorrido * pasajerosEnCama * kms;

        return costoBase + costoCamas;
    }
    /**
     * Los colectivos coche cama SOLO pueden realizar viajes de larga distancia.
     *
     * @param d destino a evaluar.
     * @return true si el destino es de larga distancia.
     */
    @Override
    public boolean cumpleCondiciones(Destino d){
        return d.esLargaDistancia();
    }
    /**
     * Devuelve una representación textual del colectivo coche cama,
     * incluyendo datos básicos del transporte (patente, capacidad, velocidad)
     * y las tarifas específicas de este tipo de colectivo.
     *
     * @return cadena descriptiva del colectivo coche cama.
     */
    @Override
    public String toString(){
        return "ColectivoCocheCama { Transporte = " + super.toString() + "[Cantidad de pasajes Semi Cama:" + CantSemiCama + ",Cantidad de pasajes Cama: " + CantCama + "Valor por pasajero por km recorrido:" + ValorPorPasajeroPorKmRecorrido + ",Valor por plaza tipo cama por km recorrido: " + ValorPlazaTipoCamaPorKmRecorrido +" ]}";
    }

}
