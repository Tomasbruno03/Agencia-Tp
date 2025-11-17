package view.viajes;

import controller.ResponsableController;
import controller.ViajeController;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class AsignarResponsableView extends JFrame {

    private ViajeController viajeController;
    private ResponsableController responsableController;

    private JComboBox<Viaje> comboViajes;
    private JComboBox<ResponsableABordo> comboResponsables;

    private boolean modoQuitar; // false = asignar, true = quitar

    public AsignarResponsableView() {
        modoQuitar = false;
    }

    public AsignarResponsableView(boolean modoQuitar) {
        super(modoQuitar ? "Quitar Responsable de Viaje" : "Asignar Responsable a Viaje");
        this.modoQuitar = modoQuitar;

        this.viajeController = new ViajeController();
        this.responsableController = new ResponsableController();

        initUI();
        setVisible(true);
    }

    private void initUI() {
        setLayout(new BorderLayout());
        setSize(500, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Combo de viajes
        panel.add(new JLabel("Viaje:"));

        comboViajes = new JComboBox<>();
        cargarViajes();
        panel.add(comboViajes);


        // Combo de responsables
        panel.add(new JLabel(modoQuitar ? "Responsable asignado:" : "Responsable disponible:"));

        comboResponsables = new JComboBox<>();
        cargarResponsables();
        panel.add(comboResponsables);

        // Botón principal
        JButton btnEjecutar = new JButton(modoQuitar ? "Quitar" : "Asignar");
        btnEjecutar.addActionListener(e -> ejecutarAccion());

        add(panel, BorderLayout.CENTER);
        add(btnEjecutar, BorderLayout.SOUTH);
    }

    private void cargarViajes() {
        comboViajes.removeAllItems();

        Agencia agencia = Agencia.getInstance();

        // Recorre todos los transportes y sus viajes
        for (Transporte t : agencia.getTransportes()) {
            for (Viaje v : t.getListaViajes()) {
                comboViajes.addItem(v);
            }
        }
    }

    private void cargarResponsables() {
        comboResponsables.removeAllItems();

        Agencia agencia = Agencia.getInstance();
        Set<ResponsableABordo> responsables = agencia.getResponsables();

        if (modoQuitar) {
            // Mostrar SOLO los responsables asignados al viaje
            Viaje viajeSeleccionado = (Viaje) comboViajes.getSelectedItem();
            if (viajeSeleccionado != null) {
                for (ResponsableABordo r : viajeSeleccionado.getResponsables()) {
                    comboResponsables.addItem(r);
                }
            }
        } else {
            // Mostrar responsables disponibles
            for (ResponsableABordo r : responsables) {
                if (r.GetEstaDisp()) {
                    comboResponsables.addItem(r);
                }
            }
        }

        comboViajes.addActionListener(e -> cargarResponsables());
    }

    private void ejecutarAccion() {

        Viaje viaje = (Viaje) comboViajes.getSelectedItem();
        ResponsableABordo responsable = (ResponsableABordo) comboResponsables.getSelectedItem();

        if (viaje == null || responsable == null) {
            JOptionPane.showMessageDialog(this,
                    "Debe seleccionar un viaje y un responsable.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            if (modoQuitar) {
                viajeController.quitarResponsableDeViaje(viaje.getIdViaje(), responsable.GetDni());
                JOptionPane.showMessageDialog(this,
                        "Responsable quitado correctamente.");
            } else {
                viajeController.asignarResponsableAViaje(viaje.getIdViaje(), responsable.GetDni());
                JOptionPane.showMessageDialog(this,
                        "Responsable asignado correctamente.");
            }

            // Recargar combos
            cargarResponsables();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

