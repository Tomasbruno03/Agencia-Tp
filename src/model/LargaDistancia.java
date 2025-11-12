package model;

import exceptions.ValidacionException;

import java.util.Iterator;

public class LargaDistancia extends Viaje{

    public LargaDistancia (int idVia, String nom, Destino destinoViaje){
        super(idVia,nom,destinoViaje);
    }

    @Override
    public float calcularCostoBase() {
        float Sueldo=0;
        Iterator<ResponsableABordo> r= this.getResponsables().iterator();
        while(r.hasNext()){

            ResponsableABordo Respon=r.next();
            Sueldo+=Respon.GetSueldo();
        }
        return Sueldo;
    }
}
