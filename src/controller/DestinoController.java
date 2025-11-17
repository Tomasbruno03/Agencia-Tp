package controller;

import exceptions.DestinoYaExisteException;
import exceptions.ValidacionException;
import model.Agencia;
import model.Destino;

import java.util.Set;

/**
 * Controlador encargado de gestionar las operaciones relacionadas con los
 * {@link Destino} dentro del sistema. Se comunica con la {@link Agencia}
 * para registrar nuevos destinos, listarlos o realizar búsquedas por nombre.
 * <p>
 * Esta clase valida datos de entrada y transforma excepciones del modelo en
 * excepciones de validación más amigables para la capa superior.
 */
public class DestinoController {

    /**
     * Crea y registra un nuevo destino dentro de la agencia.
     * <p>
     * Valida que el nombre no sea vacío y que la distancia en kilómetros sea válida.
     * Si el destino ya existe, se captura la excepción proveniente del modelo y
     * se transforma en una {@link ValidacionException}.
     *
     * @param nombre nombre del destino a crear.
     * @param km     cantidad de kilómetros del destino.
     * @throws ValidacionException si el destino ya existe o los datos son inválidos.
     */
    public void crearDestino(String nombre, int km) {
        try {
            Destino d1 = new Destino(nombre, km);
            Agencia.getInstance().agregarDestino(d1);
        } catch (DestinoYaExisteException e) {
            throw new ValidacionException(e.getMessage());
        }
    }

    public Set<Destino> listarDestinos() {
        return Agencia.getInstance().getDestinos();
    }

    public Destino buscarDestinoPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ValidacionException("El nombre del destino no puede ser vacío.");
        }

        Destino encontrado = Agencia.getInstance().buscarDestinoPorNombre(nombre);

        if (encontrado == null) {
            throw new ValidacionException("Destino '" + nombre + "' no encontrado.");
        }

        return encontrado;
    }
}