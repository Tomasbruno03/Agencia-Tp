package controller;

import model.Agencia;
import model.Destino;

public class DestinoController {

    public void crearDestino(String nombre, int km) {
        if(km > 0 && nombre.isBlank()){
            Destino d1 = new Destino(nombre, km);
            Agencia.getInstance().agregarDestino(d1);
        }

    }
}
