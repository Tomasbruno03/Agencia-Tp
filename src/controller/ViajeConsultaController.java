package controller;

import model.*;
import java.util.*;

public class ViajeConsultaController {

    public List<Viaje> listarViajesPorDestino(String nombreDestino) {
        Agencia agencia = Agencia.getInstance();
        Destino d = agencia.buscarDestinoPorNombre(nombreDestino);

        if (d == null) {
            return Collections.emptyList();
        }

        List<Viaje> resultado = new ArrayList<>();

        for (Transporte t : agencia.getTransportes()) {
            for (Viaje v : t.getListaViajes()) {
                if (v.getDestinoDelViaje().equals(d)) {
                    resultado.add(v);
                }
            }
        }

        return resultado;
    }


    public List<Viaje> listarViajesPorEstado(estado estadoBusqueda) {
        Agencia agencia = Agencia.getInstance();
        List<Viaje> resultado = new ArrayList<>();

        for (Transporte t : agencia.getTransportes()) {
            for (Viaje v : t.getListaViajes()) {
                if (v.getEstado() == estadoBusqueda) {
                    resultado.add(v);
                }
            }
        }

        return resultado;
    }
}
