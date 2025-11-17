package controller;

import exceptions.ValidacionException;
import model.*;
/**
 * Controlador encargado de gestionar todas las operaciones relacionadas con los
 * {@link Viaje} dentro del sistema. Permite crear viajes, iniciar su recorrido,
 * avanzar su progreso, finalizarlos y administrar los responsables asignados.
 * <p>
 * Este controlador actúa como intermediario entre la vista y la lógica del modelo,
 * realizando validaciones y transformando excepciones internas en mensajes más claros.
 */
public class ViajeController {

    Agencia agencia = Agencia.getInstance();
    /**
     * Crea un nuevo viaje a partir de un destino, una patente de transporte y la cantidad de pasajeros.
     * <p>
     * Valida que el destino y el transporte existan. El nombre del viaje se genera automáticamente
     * combinando el nombre del destino y un número correlativo.
     *
     * @param nombreDestino nombre del destino al cual se realizará el viaje.
     * @param patente       patente del transporte asignado.
     * @param cantPasajeros cantidad de pasajeros que participarán del viaje.
     * @return el {@link Viaje} creado correctamente.
     * @throws ValidacionException si el destino o el transporte no existen.
     */
    public Viaje crearViaje(String nombreDestino, String patente, int cantPasajeros) {

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

    private Viaje obtenerViaje(int idViaje) {
        Viaje v = agencia.buscarViajePorId(idViaje);
        if (v == null)
            throw new ValidacionException("No existe viaje con ID: " + idViaje);
        return v;
    }

    public void iniciarViaje(int idViaje) {
        Viaje v = obtenerViaje(idViaje);

        try {
            v.iniciar();
        } catch (IllegalStateException e) {
            throw new ValidacionException("No se pudo iniciar el viaje: " + e.getMessage());
        }
    }

    public void avanzarViaje(int idViaje, float km) {
        Viaje v = obtenerViaje(idViaje);

        try {
            v.avanzarKm(km);
        } catch (IllegalStateException | IllegalArgumentException e) {
            throw new ValidacionException("No se pudo avanzar el viaje: " + e.getMessage());
        }
    }

    public void finalizarViaje(int idViaje) {
        Viaje v = obtenerViaje(idViaje);

        try {
            v.finalizar();
        } catch (IllegalStateException e) {
            throw new ValidacionException("No se pudo finalizar el viaje: " + e.getMessage());
        }
    }

    public void asignarResponsableAViaje(int idViaje, String dniResponsable) {

        Viaje v = obtenerViaje(idViaje);

        ResponsableABordo r = agencia.buscarResponsablePorDni(dniResponsable);
        if (r == null)
            throw new ValidacionException("No existe responsable con DNI " + dniResponsable);

        if (!r.GetEstaDisp())
            throw new ValidacionException("El responsable no está disponible.");

        v.AgregarResponsable(r);
        r.Ocupar();
    }

    public void quitarResponsableDeViaje(int idViaje, String dniResponsable) {

        Viaje v = obtenerViaje(idViaje);

        ResponsableABordo r = agencia.buscarResponsablePorDni(dniResponsable);
        if (r == null)
            throw new ValidacionException("No existe responsable con DNI " + dniResponsable);

        if (!v.getResponsables().contains(r))
            throw new ValidacionException("El responsable no está asignado a este viaje.");

        v.QuitarResponsable(r);
        r.Liberar();
    }

}
