package controller;

import exceptions.ValidacionException;
import model.*;

public class ViajeController {

    public Viaje crearViaje(String nombreDestino, String patente, int cantPasajeros) {

        Agencia agencia = Agencia.getInstance();

        Destino d = agencia.buscarDestinoPorNombre(nombreDestino);
        if (d == null) {
            throw new ValidacionException("El destino no existe.");
        }

        Transporte t = agencia.buscarTransportePorPatente(patente);
        if (t == null) {
            throw new ValidacionException("El transporte no existe.");
        }

        int nro = agencia.obtenerProximoNumeroDeViaje(d);
        String nombreViaje = d.getNombre() + "-" + nro;

        return agencia.crearViaje(nombreViaje, d, cantPasajeros, t);
    }
}
