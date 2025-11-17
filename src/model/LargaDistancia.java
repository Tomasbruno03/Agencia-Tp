package model;

import exceptions.ValidacionException;


public class LargaDistancia extends Viaje{

    public LargaDistancia (int idVia, String nom, Destino destinoViaje,int cantp,Transporte t){
        super(idVia,nom,destinoViaje,cantp,t);
    }

    @Override
    public float calcularCostoBase() {
        float Sueldo=0;

        for(ResponsableABordo r: this.getResponsables()){
            Sueldo+=r.GetSueldo();
        }
        return Sueldo;
    }
    @Override
    public String toString(){
        return "LargaDistancia ";
    }
}
