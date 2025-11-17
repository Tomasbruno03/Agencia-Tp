package controller;

import model.*;
import java.util.*;
/**
 * Controlador encargado de gestionar las operaciones relacionadas con los
 * {@link Transporte} disponibles en la {@link Agencia}. Permite consultar
 * qué transportes pueden utilizarse para un destino específico teniendo
 * en cuenta las restricciones definidas en cada tipo de transporte.
 * <p>
 * Este controlador actúa como intermediario entre la vista y el modelo,
 * validando los datos recibidos antes de delegar al sistema central.
 */
public class TransporteController {
    private Agencia agencia = Agencia.getInstance(); //ver



    public Set<Transporte> obtenerTransportePorDestino(String nombreDestino) throws Exception{
        Destino destino = agencia.buscarDestinoPorNombre(nombreDestino);

        if(destino == null){
            throw new Exception("No existe el destino:" + nombreDestino);
        }

        return agencia.transportesParaDestino(destino);
    }
}



