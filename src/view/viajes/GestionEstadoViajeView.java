package view.viajes;

import controller.ViajeController;
import exceptions.ValidacionException;

import javax.swing.*;
import java.awt.*;

public class GestionEstadoViajeView extends JFrame {

    private JTextField txtIdViaje;
    private JTextField txtKm;
    private JButton btnIniciar;
    private JButton btnAvanzar;
    private JButton btnFinalizar;

    private ViajeController viajeController;

    public GestionEstadoViajeView() {
        super("Gestión de Estado del Viaje");
        viajeController = new ViajeController();

        initUI();
        setVisible(true);
    }

    private void initUI() {
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));

        panel.add(new JLabel("ID del Viaje:"));
        txtIdViaje = new JTextField();
        panel.add(txtIdViaje);

        panel.add(new JLabel("Km a Avanzar:"));
        txtKm = new JTextField();
        panel.add(txtKm);

        btnIniciar = new JButton("Iniciar Viaje");
        btnAvanzar = new JButton("Avanzar Km");
        btnFinalizar = new JButton("Finalizar Viaje");

        panel.add(btnIniciar);
        panel.add(btnAvanzar);
        panel.add(btnFinalizar);

        add(panel, BorderLayout.CENTER);

        configurarEventos();
    }

    private void configurarEventos() {

        // INICIAR VIAJE
        btnIniciar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtIdViaje.getText());
                viajeController.iniciarViaje(id);

                JOptionPane.showMessageDialog(this,
                        "Viaje iniciado correctamente.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException ex) {
                mostrarError("El ID debe ser un número entero.");
            } catch (ValidacionException ex) {
                mostrarError(ex.getMessage());
            }
        });

        // AVANZAR VIAJE
        btnAvanzar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtIdViaje.getText());
                float km = Float.parseFloat(txtKm.getText());

                viajeController.avanzarViaje(id, km);

                JOptionPane.showMessageDialog(this,
                        "Se avanzó el viaje " + km + " km.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException ex) {
                mostrarError("El ID y los KM deben ser numéricos.");
            } catch (ValidacionException ex) {
                mostrarError(ex.getMessage());
            }
        });

        // FINALIZAR VIAJE
        btnFinalizar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtIdViaje.getText());
                viajeController.finalizarViaje(id);

                JOptionPane.showMessageDialog(this,
                        "Viaje finalizado con éxito.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException ex) {
                mostrarError("El ID debe ser un número entero.");
            } catch (ValidacionException ex) {
                mostrarError(ex.getMessage());
            }
        });
    }


    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this,
                mensaje,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}