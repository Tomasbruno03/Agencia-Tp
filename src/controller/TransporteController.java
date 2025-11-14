package controller;

import model.*;
import java.util.*;

public class TransporteController {
    private

    public void asociarTransporteDestino(String patente, String nombreDestino)
        throws Exception{
        Transporte transporte = agencia.buscarTransportePorPatente(patente);
            if(transporte == null)
                throw new Exception("No existe transporte con patente: " + patente);


        Destino destino = agencia.buscarDestinoPoNombre(nombreDestino);
        if(destino == null)
            throw new Exception("No existe destino con nombre: " + nombreDestino);

        agencia.asociarTransporteADestino(destino, transporte);

        System.out.println("Transporte " + patente + "asociado correctamente a destino " + nombreDestino);

    }
}



