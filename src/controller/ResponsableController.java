package controller;

import exceptions.ValidacionException;
import model.*;
/**
 * Controlador encargado de gestionar las operaciones relacionadas con los
 * {@link ResponsableABordo}. Centraliza la creación, validación y búsqueda
 * de responsables dentro de la {@link Agencia}.
 * <p>
 * Esta clase actúa como intermediaria entre la capa de presentación y el modelo,
 * garantizando que los datos ingresados sean válidos antes de delegar la operación
 * al sistema principal.
 */
public class ResponsableController {

    public ResponsableABordo crearResponsable(String dni, String nombre, float sueldoPorViaje) {

        Agencia agencia = Agencia.getInstance();

        if (dni == null || dni.isEmpty()) {
            throw new ValidacionException("El DNI no puede estar vacío.");
        }
        if (nombre == null || nombre.isEmpty()) {
            throw new ValidacionException("El nombre no puede estar vacío.");
        }
        if (sueldoPorViaje <= 0) {
            throw new ValidacionException("El sueldo por viaje debe ser positivo.");
        }

        ResponsableABordo nuevo = new ResponsableABordo(nombre, true, dni, sueldoPorViaje); // si es duplicado, acá saltará la excepción
        if(nuevo == null)
            throw new ValidacionException("No se creo el responsable.");

        agencia.agregarResponsable(nuevo);

        return nuevo;
    }


    public ResponsableABordo buscarResponsablePorDni(String dni) {
        Agencia agencia = Agencia.getInstance();
        return agencia.buscarResponsablePorDni(dni);
    }
}