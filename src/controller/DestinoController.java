package controller;

import exceptions.DestinoYaExisteException;
import exceptions.ValidacionException;
import model.Agencia;
import model.Destino;

public class DestinoController {

    public void crearDestino(String nombre, int km) {
        try {
            Destino d1 = new Destino(nombre, km);
            Agencia.getInstance().agregarDestino(d1);
        }catch (DestinoYaExisteException e){
            throw new ValidacionException(e.getMessage());
        }





    }
}
