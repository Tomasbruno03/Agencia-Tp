package model;

import exceptions.ValidacionException;
import model.Viaje;

import java.io.Serializable;

public class CortaDistancia extends Viaje implements Serializable {
    private static final float cobrobase = 1500; // Valor inicial por defecto
    private static final long serialVersionUID = 1L;

    public CortaDistancia(int idVia, String nom, Destino destinoViaje,int cantP,Transporte t){
        super(idVia,nom,destinoViaje,cantP,t);
    }

    public float getCobrobase(){
        return cobrobase;
    }

    @Override
    public String toString(){
        return "CortaDistancia [Cobro base en viajes de corta distancia:" + cobrobase + " ]";
    }

    @Override
    public float calcularCostoBase() {
        return cobrobase;
    }
}
