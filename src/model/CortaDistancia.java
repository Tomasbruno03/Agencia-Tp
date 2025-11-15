package model;

public class CortaDistancia extends Viaje{
    public float cobrobase;

    public CortaDistancia(int idVia, String nom, Destino destinoViaje){
        super(idVia,nom,destinoViaje);
        this.cobrobase=1000;
    }

    public float getCobrobase(){
        return cobrobase;
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
