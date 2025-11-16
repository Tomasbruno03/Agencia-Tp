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

import javax.swing.*;

public class MainWindow extends JFrame {

    private ViajeController viajeController;
    private DestinoController destinoController;
    private TransporteController transporteController;
    private ResponsableController responsableController;
    private AgenciaController agenciaController;
    private ViajeConsultaController consultasViajesController;

    public MainWindow() {
        // Inicializa controladores
        viajeController = new ViajeController();
        destinoController = new DestinoController();
        transporteController = new TransporteController();
        responsableController = new ResponsableController();
        agenciaController = new AgenciaController();
        consultasViajesController = new ViajeConsultaController();

        // Configuración básica de la ventana
        setTitle("Agencia de Turismo");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configurar menú
        setJMenuBar(crearMenu());
    }

    private JMenuBar crearMenu() {
        JMenuBar menuBar = new JMenuBar();

        // Destinos
        JMenu destinosMenu = new JMenu("Destinos");
        JMenuItem listarDestinos = new JMenuItem("Listar destinos");
        JMenuItem crearDestino = new JMenuItem("Crear destino");

        listarDestinos.addActionListener(e -> {
            // Abrir ventana DestinoView
            new DestinoView(destinoController).setVisible(true);
        });

        crearDestino.addActionListener(e -> {
            new CrearDestinoDialog(destinoController).setVisible(true);
        });

        destinosMenu.add(listarDestinos);
        destinosMenu.add(crearDestino);
        menuBar.add(destinosMenu);

        // Transportes
        JMenu transportesMenu = new JMenu("Transportes");
        JMenuItem listarTransportes = new JMenuItem("Listar transportes");

        listarTransportes.addActionListener(e -> {
            new view.TransporteView().setVisible(true);
        });

        transportesMenu.add(listarTransportes);
        menuBar.add(transportesMenu);

        // Responsables
        JMenu responsablesMenu = new JMenu("Responsables");
        JMenuItem listarResponsables = new JMenuItem("Listar responsables");
        JMenuItem crearResponsable = new JMenuItem("Crear responsable");

        listarResponsables.addActionListener(e -> {
            new ResponsableView(responsableController).setVisible(true);
        });

        crearResponsable.addActionListener(e -> {
            new CrearResponsableDialog(responsableController).setVisible(true);
        });

        responsablesMenu.add(listarResponsables);
        responsablesMenu.add(crearResponsable);
        menuBar.add(responsablesMenu);

        // Viajes
        JMenu viajesMenu = new JMenu("Viajes");
        JMenuItem crearViaje = new JMenuItem("Crear viaje");
        JMenuItem gestionarViaje = new JMenuItem("Iniciar / avanzar / finalizar viaje");
        JMenuItem asignarResponsable = new JMenuItem("Asignar responsable");
        JMenuItem quitarResponsable = new JMenuItem("Quitar responsable");
        JMenuItem consultarViajes = new JMenuItem("Consultar viajes");

        crearViaje.addActionListener(e -> new CrearViajeView(viajeController, destinoController, transporteController).setVisible(true));
        gestionarViaje.addActionListener(e -> new GestionEstadoViajeView().setVisible(true));
        asignarResponsable.addActionListener(e -> new AsignarResponsableView().setVisible(true));
        quitarResponsable.addActionListener(e -> new AsignarResponsableView(true).setVisible(true)); // true=modo quitar
        consultarViajes.addActionListener(e -> new ConsultasViajesView().setVisible(true));

        viajesMenu.add(crearViaje);
        viajesMenu.add(gestionarViaje);
        viajesMenu.add(asignarResponsable);
        viajesMenu.add(quitarResponsable);
        viajesMenu.add(consultarViajes);
        menuBar.add(viajesMenu);

        // Reportes
        JMenu reportesMenu = new JMenu("Reportes");
        JMenuItem recaudacionTransporte = new JMenuItem("Recaudación por transporte");
        JMenuItem recaudacionDestino = new JMenuItem("Recaudación por destino");
        JMenuItem rankingResponsables = new JMenuItem("Ranking de responsables");

        recaudacionTransporte.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, agenciaController.getReporteRecaudacionComoTexto());
        });

        recaudacionDestino.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, agenciaController.getReporteRecaudacionComoTexto());
        });

        rankingResponsables.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, agenciaController.getRankingResponsablesComoTexto());
        });

        reportesMenu.add(recaudacionTransporte);
        reportesMenu.add(recaudacionDestino);
        reportesMenu.add(rankingResponsables);
        menuBar.add(reportesMenu);

        return menuBar;
    }
}
