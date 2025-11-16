package model;

import exceptions.ValidacionException;

public class CortaDistancia extends Viaje{
    private static final float cobrobase = 1500; // Valor inicial por defecto

    public CortaDistancia(int idVia, String nom, Destino destinoViaje,int cantP,Transporte t){
        super(idVia,nom,destinoViaje,cantP,t);
    }

    public float getCobrobase(){
        return cobrobase;
    }

    @Override
    public String toString(){
        return "CortaDistancia { Viaje = " + super.toString() + " [Cobro base en viajes de corta distancia:" + cobrobase + " ]} ";
    }

    @Override
    public float calcularCostoBase() {
        return cobrobase;
    }
}
