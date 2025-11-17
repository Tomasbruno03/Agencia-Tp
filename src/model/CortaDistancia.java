package model;

import exceptions.ValidacionException;
/**
 * Representa un viaje de corta distancia (≤ 100 km).
 * Incluye un costo base fijo definido por el sistema.
 */
public class CortaDistancia extends Viaje{
    private static final float cobrobase = 1500; // Valor inicial por defecto


    /**
     * Constructor.
     */
    public CortaDistancia(int idVia, String nom, Destino destinoViaje,int cantP,Transporte t){
        super(idVia,nom,destinoViaje,cantP,t);
    }

    public float getCobrobase(){
        return cobrobase;
    }

    /**
     * Retorna una representación textual del viaje de corta distancia.
     * Incluye la información general del viaje provista por la clase base
     * {@code Viaje} y el cobro base específico para este tipo de viaje.
     *
     * @return una cadena descriptiva del viaje de corta distancia.
     */
    @Override
    public String toString(){
        return "CortaDistancia { Viaje = " + super.toString() + " [Cobro base en viajes de corta distancia:" + cobrobase + " ]} ";
    }
    /**
     * @return costo base fijo de viajes cortos.
     */
    @Override
    public float calcularCostoBase() {
        return cobrobase;
    }
}
