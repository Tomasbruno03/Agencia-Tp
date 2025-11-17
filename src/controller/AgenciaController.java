package controller;

import model.Agencia;
import model.Destino;
import model.ResponsableABordo;
import model.Transporte;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * Controlador principal encargado de gestionar todas las operaciones de consulta
 * y generación de reportes relacionadas con la {@link Agencia}.
 * <p>
 * Esta clase funciona como intermediario entre la vista (o interfaz de usuario)
 * y el modelo, encapsulando la lógica necesaria para producir reportes de:
 * <ul>
 *     <li>Ranking de responsables según kilómetros acumulados.</li>
 *     <li>Recaudación total por destino.</li>
 *     <li>Recaudación total por transporte.</li>
 * </ul>
 * Además, brinda métodos para exportar dichos reportes en archivos de texto.
 */
public class AgenciaController {

    /** Instancia única de la agencia que administra los datos del sistema. */

    private final Agencia agencia=Agencia.getInstance();;

    /**
     * Genera un texto formateado que contiene el ranking de responsables ordenados
     * por cantidad de kilómetros recorridos, de mayor a menor.
     *
     * @return una cadena con el ranking de responsables, lista para mostrar por pantalla.
     */
    public String getRankingResponsablesComoTexto() {

        // 1. Pide los datos puros al Modelo
        List<ResponsableABordo> ranking = agencia.GenerarRankingResponsables();

        // 2. EL CONTROLADOR construye el string (usa un StringBuilder)
        StringBuilder sb = new StringBuilder("--- Ranking de Responsables (por KM) ---\n");

        if (ranking.isEmpty()) {
            sb.append("No hay responsables en toda la agencia.\n");
        } else {
            for (ResponsableABordo r : ranking) {
                sb.append(String.format("- %s: %.2f km\n", r.GetNombre(), r.getCantKmAcumulados()));
            }
        }

        return sb.toString();
    }

    /**
     * Exporta a un archivo de texto el ranking de responsables generado por
     * {@link #getRankingResponsablesComoTexto()}.
     *
     * @param filePath ruta del archivo donde se guardará el ranking.
     * @throws IOException si ocurre un error al escribir el archivo.
     */
    public void exportarRankingResponsables(String filePath) throws IOException {

        String contenidoDelRanking = getRankingResponsablesComoTexto();

        try {
            Files.writeString(Paths.get(filePath), contenidoDelRanking);
        } catch (IOException e) {
            throw new IOException("Error al guardar el archivo de ranking: " + e.getMessage());
        }
    }
    /**
     * Construye un texto con la recaudación total obtenida por cada destino.
     * Solo se consideran los viajes finalizados.
     *
     * @return una cadena formateada con el detalle de recaudación por destino.
     */
    public String getReporteRecaudacionDestinoComoTexto() {

        Map<Destino, Float> reporteDatos = agencia.getReporteRecaudacionPorDestino();

        StringBuilder sb = new StringBuilder("--- Recaudación Total por Destino ---\n");

        float montoTotalRecaudado = 0f;


        if (reporteDatos.isEmpty()) {
            sb.append("No hay viajes finalizados para generar un reporte.\n");

        } else {
            for (Map.Entry<Destino, Float> entry : reporteDatos.entrySet()) {
                Destino d = entry.getKey();
                Float recaudado = entry.getValue();

                sb.append(String.format("- Destino: %s --- Total: $%.2f\n", d.getNombre(), recaudado));

                montoTotalRecaudado += recaudado;
            }
        }
        sb.append("--------------------------------------\n");
        sb.append(String.format("MONTO TOTAL RECAUDADO: $%.2f\n", montoTotalRecaudado));

        return sb.toString();
    }
    public void exportarReporteRecaudacionDestino(String filePath) throws IOException {
        // Reutiliza el método anterior
        String contenido = getReporteRecaudacionDestinoComoTexto();

        try {
            Files.writeString(Paths.get(filePath), contenido);
        } catch (IOException e) {
            throw new IOException("Error al guardar el reporte de destinos: " + e.getMessage());
        }
    }

    // --- 3. REPORTE DE RECAUDACIÓN POR TRANSPORTE (El que faltaba) ---

    /**
     * Obtiene la recaudación por transporte como String (para pantalla).
     */
    public String getReporteRecaudacionTransporteComoTexto() {
        Map<Transporte, Float> reporteDatos = agencia.GenerarReporteRecaudadoPorTransporte(); // Llama al Modelo
        StringBuilder sb = new StringBuilder("--- Recaudación Total por Transporte ---\n");
        float montoTotalRecaudado = 0f;

        if (reporteDatos.isEmpty()) {
            sb.append("No hay viajes finalizados para generar un reporte.\n");
        } else {
            for (Map.Entry<Transporte, Float> entry : reporteDatos.entrySet()) {
                Transporte t = entry.getKey();
                Float recaudado = entry.getValue();
                sb.append(String.format("- Transporte (Patente): %s --- Total: $%.2f\n", t.getPatente(), recaudado));
                montoTotalRecaudado += recaudado;
            }
        }
        sb.append("--------------------------------------\n");
        sb.append(String.format("MONTO TOTAL RECAUDADO: $%.2f\n", montoTotalRecaudado));
        return sb.toString();
    }
    /**
     * Exporta el reporte de recaudación por transporte a un archivo de texto.
     *
     * @param filePath ruta del archivo donde se almacenará el archivo generado.
     * @throws IOException si ocurre un error durante la escritura del archivo.
     */
    public void exportarReporteRecaudacionTransporte(String filePath) throws IOException {

        String contenido = getReporteRecaudacionTransporteComoTexto();

        try {
            Files.writeString(Paths.get(filePath), contenido);
        } catch (IOException e) {
            throw new IOException("Error al guardar el reporte de transportes: " + e.getMessage());
        }
    }
}



