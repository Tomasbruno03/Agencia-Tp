package model;

import exceptions.ValidacionException;

public class CortaDistancia extends Viaje{
    public float cobrobase;

    public CortaDistancia(int idVia, String nom, Destino destinoViaje,int cantP,Transporte t){
        super(idVia,nom,destinoViaje,cantP,t);
        this.cobrobase=1000;
    }

    public float getCobrobase(){
        return cobrobase;
    }

    public void setCobrobase(float cobrobase){
        this.cobrobase=cobrobase;
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
