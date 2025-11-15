package controller;

import model.*;
import java.util.*;

public class TransporteController {
    private Agencia agencia = Agencia.getInstance(); //ver

    public void asociarTransporteADestino(String patente, String nombreDestino)
        throws Exception{
        Transporte transporte = agencia.buscarTransportePorPatente(patente);
            if(transporte == null)
                throw new Exception("No existe transporte con patente: " + patente);


        Destino destino = agencia.buscarDestinoPorNombre(nombreDestino);
        if(destino == null)
            throw new Exception("No existe destino con nombre: " + nombreDestino);

        agencia.asociarTransporteADestino(destino, transporte);

        System.out.println("Transporte " + patente + " asociado correctamente a destino " + nombreDestino);

    }

    public Set<Transporte> obtenerTransportePorDestino(String nombreDestino) throws Exception{
        Destino destino = agencia.buscarDestinoPorNombre(nombreDestino);

        if(destino == null){
            throw new Exception("No existe el destino:" + nombreDestino);
        }

        return agencia.transportesPorDestino(destino);
    }
}



