package controller;

import model.Agencia;
import model.ResponsableABordo;

import java.util.List;

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

}
