package model;

import exceptions.ValidacionException;

public class LargaDistancia extends Viaje{

    public LargaDistancia (int idVia, String nom, Destino destinoViaje){
        super(idVia,nom,destinoViaje);
    }

    @Override
    public float calcularCostoBase() {
        return 0;
    }
}
