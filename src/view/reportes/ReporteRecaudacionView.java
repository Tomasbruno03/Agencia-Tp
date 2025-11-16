package view.reportes;

import controller.AgenciaController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ReporteRecaudacionView extends JFrame {

    private AgenciaController agenciaController;
    private JTextArea txtReporte;
    private JButton btnExportar;

    public ReporteRecaudacionView() {
        super("Reporte de Recaudación");
        agenciaController = new AgenciaController();
        initUI();
        cargarReporte();
    }

    private void initUI() {
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        txtReporte = new JTextArea();
        txtReporte.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtReporte);

        btnExportar = new JButton("Exportar a archivo");
        btnExportar.addActionListener(e -> exportarReporte());

        add(scroll, BorderLayout.CENTER);
        add(btnExportar, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void cargarReporte() {
        String reporte = agenciaController.getReporteRecaudacionComoTexto();
        txtReporte.setText(reporte);
    }

    private void exportarReporte() {
        JFileChooser fileChooser = new JFileChooser();
        int seleccion = fileChooser.showSaveDialog(this);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            try {
                agenciaController.getReporteRecaudacionComoTexto(path);
                JOptionPane.showMessageDialog(this, "Reporte exportado correctamente.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al exportar: " + ex.getMessage());
            }
        }
    }
}
