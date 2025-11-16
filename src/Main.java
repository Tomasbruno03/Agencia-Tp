import model.Agencia;
import persistence.LectorJSON;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando sistema de Agencia...");
        Agencia miAgencia = Agencia.getInstance();
        LectorJSON lector = new LectorJSON();
        lector.cargarDatos(miAgencia, "resources/datos_agencia.json");
        System.out.println("Sistema listo. Agencia cargada.");
    }
}
