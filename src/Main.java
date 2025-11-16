import model.Agencia;
import persistence.LectorJSON;
import view.MainWindow;
import view.destinos.CrearDestinoDialog;
import view.destinos.DestinoView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando sistema de Agencia...");
        Agencia miAgencia = Agencia.getInstance();
        LectorJSON lector = new LectorJSON();
        lector.cargarDatos(miAgencia, "resources/datos_agencia.json");
        System.out.println("Sistema listo. Agencia cargada.");
        SwingUtilities.invokeLater(() -> {
            new MainWindow().setVisible(true);
        });


    }
}
