import model.Agencia;
import persistence.LectorJSON;
import view.MainWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando sistema de Agencia...");

        // Instancia única de la agencia
        Agencia miAgencia = Agencia.getInstance();

        // Carga de datos desde JSON
        LectorJSON lector = new LectorJSON();
        lector.cargarDatos(miAgencia, "resources/datos_agencia.json");

        System.out.println("Sistema listo. Agencia cargada.");

        // Inicializa la interfaz gráfica en el hilo de Swing
        SwingUtilities.invokeLater(() -> new MainWindow().setVisible(true));
    }
}
