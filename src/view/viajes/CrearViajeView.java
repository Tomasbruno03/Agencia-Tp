package view.viajes;

import controller.DestinoController;
import controller.TransporteController;
import controller.ViajeController;
import exceptions.ValidacionException;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class CrearViajeView extends JFrame {

    private JComboBox<String> comboDestinos;
    private JComboBox<String> comboTransportes;
    private JTextField txtCantPasajeros;
    private JButton btnCrear;
    private JButton btnAsignarResponsables;

    private ViajeController viajeController;
    private DestinoController destinoController;
    private TransporteController transporteController;

    private boolean esLargaDistancia = false;

    public CrearViajeView(ViajeController vc, DestinoController dc, TransporteController tc) {
        super("Crear Viaje");
        this.viajeController = vc;
        this.destinoController = dc;
        this.transporteController = tc;

        initUI();
        cargarDestinos();
    }

    private void initUI() {
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        comboDestinos = new JComboBox<>();
        comboTransportes = new JComboBox<>();
        txtCantPasajeros = new JTextField();
        btnCrear = new JButton("Crear Viaje");
        btnAsignarResponsables = new JButton("Asignar Responsables");
        btnAsignarResponsables.setEnabled(false);

        add(new JLabel("Destino:"));
        add(comboDestinos);

        add(new JLabel("Transporte:"));
        add(comboTransportes);

        add(new JLabel("Cantidad de pasajeros:"));
        add(txtCantPasajeros);

        add(btnAsignarResponsables);
        add(btnCrear);

        // Cuando se selecciona un destino, se filtran transportes
        comboDestinos.addActionListener(e -> actualizarTransportes());

        btnCrear.addActionListener(e -> crearViaje());

        btnAsignarResponsables.addActionListener(e -> {
            // Abrir la vista para asignar responsables
            new AsignarResponsableView().setVisible(true);
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void cargarDestinos() {
        try {
            Set<Destino> destinos = destinoController.listarDestinos();
            comboDestinos.removeAllItems();
            for (Destino d : destinos) {
                comboDestinos.addItem(d.getNombre());
            }
            if (comboDestinos.getItemCount() > 0) {
                comboDestinos.setSelectedIndex(0);
                actualizarTransportes();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error cargando destinos: " + e.getMessage());
        }
    }

    private void actualizarTransportes() {
        try {
            String nombreDestino = (String) comboDestinos.getSelectedItem();
            Destino destino = destinoController.buscarDestinoPorNombre(nombreDestino);
            comboTransportes.removeAllItems();

            esLargaDistancia = destino.getCantKm() > 100;
            btnAsignarResponsables.setEnabled(esLargaDistancia);

            Set<Transporte> transportes = Agencia.getInstance().getTransportes();
            for (Transporte t : transportes) {
                String clase = t.getClass().getSimpleName();
                if (esLargaDistancia && clase.equals("Auto")) continue; // No autos en larga distancia
                if (!esLargaDistancia && clase.equals("ColectivoCocheCama")) continue; // No coche cama en corta distancia
                comboTransportes.addItem(t.getPatente());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error filtrando transportes: " + e.getMessage());
        }
    }

    private void crearViaje() {
        try {
            String destino = (String) comboDestinos.getSelectedItem();
            String patente = (String) comboTransportes.getSelectedItem();
            int cantPasajeros = Integer.parseInt(txtCantPasajeros.getText());

            Viaje viaje = viajeController.crearViaje(destino, patente, cantPasajeros);

            JOptionPane.showMessageDialog(this, "Viaje creado correctamente: " + viaje.getNombre());

            // Si es larga distancia, habilitar asignación de responsables
            if (esLargaDistancia) {
                btnAsignarResponsables.setEnabled(true);
            } else {
                dispose();
            }

        } catch (ValidacionException ve) {
            JOptionPane.showMessageDialog(this, "Error: " + ve.getMessage());
        } catch (NumberFormatException ne) {
            JOptionPane.showMessageDialog(this, "Ingrese un número válido de pasajeros.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al crear viaje: " + ex.getMessage());
        }
    }
}
