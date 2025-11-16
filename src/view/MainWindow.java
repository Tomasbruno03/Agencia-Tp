package view;

import controller.*;
import view.destinos.CrearDestinoDialog;
import view.destinos.DestinoView;
import view.responsables.CrearResponsableDialog;
import view.responsables.ResponsableView;
import view.viajes.AsignarResponsableView;
import view.viajes.ConsultasViajesView;
import view.viajes.CrearViajeView;
import view.viajes.GestionEstadoViajeView;
import view.transportes.TransporteView;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class MainWindow extends JFrame {

    private ViajeController viajeController;
    private DestinoController destinoController;
    private TransporteController transporteController;
    private ResponsableController responsableController;
    private AgenciaController agenciaController;
    private ViajeConsultaController consultasViajesController;

    public MainWindow() {
        viajeController = new ViajeController();
        destinoController = new DestinoController();
        transporteController = new TransporteController();
        responsableController = new ResponsableController();
        agenciaController = new AgenciaController();
        consultasViajesController = new ViajeConsultaController();

        setTitle("Agencia de Turismo");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setJMenuBar(crearMenu());
    }

    private JMenuBar crearMenu() {
        JMenuBar menuBar = new JMenuBar();

        // --- Destinos ---
        JMenu destinosMenu = new JMenu("Destinos");
        JMenuItem listarDestinos = new JMenuItem("Listar destinos");
        JMenuItem crearDestino = new JMenuItem("Crear destino");
        listarDestinos.addActionListener(e -> new DestinoView(destinoController).setVisible(true));
        crearDestino.addActionListener(e -> new CrearDestinoDialog(destinoController).setVisible(true));
        destinosMenu.add(listarDestinos);
        destinosMenu.add(crearDestino);
        menuBar.add(destinosMenu);

        // --- Transportes ---
        JMenu transportesMenu = new JMenu("Transportes");
        JMenuItem listarTransportes = new JMenuItem("Listar transportes");
        listarTransportes.addActionListener(e -> new TransporteView().setVisible(true));
        transportesMenu.add(listarTransportes);
        menuBar.add(transportesMenu);

        // --- Responsables ---
        JMenu responsablesMenu = new JMenu("Responsables");
        JMenuItem listarResponsables = new JMenuItem("Listar responsables");
        JMenuItem crearResponsable = new JMenuItem("Crear responsable");
        listarResponsables.addActionListener(e -> new ResponsableView(responsableController).setVisible(true));
        crearResponsable.addActionListener(e -> new CrearResponsableDialog(responsableController).setVisible(true));
        responsablesMenu.add(listarResponsables);
        responsablesMenu.add(crearResponsable);
        menuBar.add(responsablesMenu);

        // --- Viajes ---
        JMenu viajesMenu = new JMenu("Viajes");
        JMenuItem crearViaje = new JMenuItem("Crear viaje");
        JMenuItem gestionarViaje = new JMenuItem("Iniciar / avanzar / finalizar viaje");
        JMenuItem asignarResponsable = new JMenuItem("Asignar responsable");
        JMenuItem quitarResponsable = new JMenuItem("Quitar responsable");
        JMenuItem consultarViajes = new JMenuItem("Consultar viajes");

        crearViaje.addActionListener(e -> new CrearViajeView(viajeController, destinoController, transporteController).setVisible(true));
        gestionarViaje.addActionListener(e -> new GestionEstadoViajeView().setVisible(true));
        asignarResponsable.addActionListener(e -> new AsignarResponsableView().setVisible(true));
        quitarResponsable.addActionListener(e -> new AsignarResponsableView(true).setVisible(true));
        consultarViajes.addActionListener(e -> new ConsultasViajesView().setVisible(true));

        viajesMenu.add(crearViaje);
        viajesMenu.add(gestionarViaje);
        viajesMenu.add(asignarResponsable);
        viajesMenu.add(quitarResponsable);
        viajesMenu.add(consultarViajes);
        menuBar.add(viajesMenu);

        // --- Reportes ---
        JMenu reportesMenu = new JMenu("Reportes");

        JMenuItem recaudacionTransporte = new JMenuItem("Recaudación por transporte");
        JMenuItem recaudacionDestino = new JMenuItem("Recaudación por destino");
        JMenuItem rankingResponsables = new JMenuItem("Ranking de responsables");

        // Mostrar y exportar recaudación transporte
        recaudacionTransporte.addActionListener(e -> {
            try {
                mostrarYExportarReporte(
                        "Recaudación por transporte",
                        agenciaController.getReporteRecaudacionTransporteComoTexto(),
                        filePath -> {
                            try {
                                agenciaController.exportarReporteRecaudacionTransporte(filePath);
                            } catch (IOException ex) {
                                mostrarError(ex);
                            }
                        }
                );
            } catch (IOException ex) {
                mostrarError(ex);
            }
        });

        // Mostrar y exportar recaudación destino
        recaudacionDestino.addActionListener(e -> {
            try {
                mostrarYExportarReporte(
                        "Recaudación por destino",
                        agenciaController.getReporteRecaudacionDestinoComoTexto(),
                        filePath -> {
                            try {
                                agenciaController.exportarReporteRecaudacionDestino(filePath);
                            } catch (IOException ex) {
                                mostrarError(ex);
                            }
                        }
                );
            } catch (IOException ex) {
                mostrarError(ex);
            }
        });

        // Mostrar y exportar ranking responsables
        rankingResponsables.addActionListener(e -> {
            try {
                mostrarYExportarReporte(
                        "Ranking de responsables",
                        agenciaController.getRankingResponsablesComoTexto(),
                        filePath -> {
                            try {
                                agenciaController.exportarRankingResponsables(filePath);
                            } catch (IOException ex) {
                                mostrarError(ex);
                            }
                        }
                );
            } catch (IOException ex) {
                mostrarError(ex);
            }
        });


        reportesMenu.add(recaudacionTransporte);
        reportesMenu.add(recaudacionDestino);
        reportesMenu.add(rankingResponsables);
        menuBar.add(reportesMenu);

        return menuBar;
    }

    // --- Métodos auxiliares ---
    private void mostrarYExportarReporte(String titulo, String contenido, FileExporter exportador) throws IOException {
        JTextArea area = new JTextArea(contenido);
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);
        int opcion = JOptionPane.showOptionDialog(this, scroll, titulo,
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, new String[]{"Cerrar", "Exportar a TXT"}, "Cerrar");

        if (opcion == 1) { // Exportar
            JFileChooser chooser = new JFileChooser();
            chooser.setSelectedFile(new File(titulo.replaceAll(" ", "_") + ".txt"));
            int seleccion = chooser.showSaveDialog(this);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File archivo = chooser.getSelectedFile();
                exportador.exportar(archivo.getAbsolutePath());
                JOptionPane.showMessageDialog(this, "Reporte exportado correctamente.");
            }
        }
    }

    private void mostrarError(Exception ex) {
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
    }

    @FunctionalInterface
    interface FileExporter {
        void exportar(String filePath) throws IOException;
    }
}
