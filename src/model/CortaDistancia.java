package model;

public class CortaDistancia extends Viaje{
    public float cobrobase;

    public CortaDistancia(int idVia, String nom, Destino destinoViaje,float cobrobase,Transporte t){
        super(idVia,nom,destinoViaje,t);
        this.cobrobase=cobrobase;
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
    public float calcularCosto() {
        return 0;
    }
}
