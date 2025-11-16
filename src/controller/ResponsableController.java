package controller;

import exceptions.ValidacionException;
import model.*;

public class ResponsableController {

    public ResponsableABordo crearResponsable(String dni, String nombre, float sueldoPorViaje) {

        Agencia agencia = Agencia.getInstance();

        // Validaciones básicas
        if (dni == null || dni.isEmpty()) {
            throw new ValidacionException("El DNI no puede estar vacío.");
        }
        if (nombre == null || nombre.isEmpty()) {
            throw new ValidacionException("El nombre no puede estar vacío.");
        }
        if (sueldoPorViaje <= 0) {
            throw new ValidacionException("El sueldo por viaje debe ser positivo.");
        }

        // Es necesario validar acá cuando ya se validó en agencia si es duplicado??
        if (agencia.buscarResponsablePorDni(dni) != null) {
            throw new ValidacionException("Ya existe un responsable con DNI " + dni);
        }

        // Crear responsable
        ResponsableABordo nuevo = new ResponsableABordo(nombre, true, dni, sueldoPorViaje);

        // Guardarlo en Agencia
        agencia.agregarResponsable(nuevo);

        return nuevo;
    }


    public ResponsableABordo buscarResponsablePorDni(String dni) {
        Agencia agencia = Agencia.getInstance();
        return agencia.buscarResponsablePorDni(dni);
    }
}