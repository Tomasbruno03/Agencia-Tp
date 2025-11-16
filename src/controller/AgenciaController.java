package controller;

import model.Agencia;
import model.Destino;
import model.ResponsableABordo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class AgenciaController {

    private final Agencia agencia=Agencia.getInstance();;

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
    public void exportarRankingResponsables(String filePath) throws IOException {

        String contenidoDelRanking = getRankingResponsablesComoTexto();

        // 2. Escribe el contenido en el archivo (Forma moderna de Java)
        try {
            Files.writeString(Paths.get(filePath), contenidoDelRanking);
        } catch (IOException e) {
            // 3. Si falla, le informa a la Vista (UI)
            throw new IOException("Error al guardar el archivo: " + e.getMessage());
        }
    }

    public String getReporteRecaudacionComoTexto() {

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
}


