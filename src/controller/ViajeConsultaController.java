package controller;

import model.*;
import java.util.*;
/**
 * Controlador dedicado a realizar consultas sobre los {@link Viaje}
 * registrados en el sistema. Permite acceder a información filtrada
 * según destino o estado del viaje.
 * <p>
 * Este controlador NO modifica datos; solo consulta la información
 * disponible en la {@link Agencia}.
 */
public class ViajeConsultaController {

    /**
     * Obtiene una lista de viajes cuyo destino coincide con el nombre
     * indicado.
     * <p>
     * Si el destino no existe en la agencia, se retorna una lista vacía.
     *
     * @param nombreDestino nombre del destino por el cual se desea filtrar los viajes.
     * @return una lista de {@link Viaje} que tienen como destino el indicado,
     *         o una lista vacía si el destino no existe o no tiene viajes asociados.
     */
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
