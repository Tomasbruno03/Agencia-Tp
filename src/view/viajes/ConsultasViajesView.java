package view.viajes;

import controller.ViajeConsultaController;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ConsultasViajesView extends JFrame {

    private ViajeConsultaController consultaController;
    private JComboBox<Destino> comboDestinos;
    private JComboBox<estado> comboEstado;
    private JTextArea resultados;

    public ConsultasViajesView() {
        super("Consultas de Viajes");
        consultaController = new ViajeConsultaController();

        initUI();
        setVisible(true);
    }

    private void initUI() {
        setSize(600, 450);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel filtros = new JPanel(new GridLayout(3, 2, 10, 10));
        filtros.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        filtros.add(new JLabel("Filtrar por destino:"));
        comboDestinos = new JComboBox<>();
        cargarDestinos();
        filtros.add(comboDestinos);

        filtros.add(new JLabel("Filtrar por estado:"));
        comboEstado = new JComboBox<>(estado.values());
        filtros.add(comboEstado);

        JButton btnDestino = new JButton("Buscar por destino");
        JButton btnEstado = new JButton("Buscar por estado");

        btnDestino.addActionListener(e -> buscarPorDestino());
        btnEstado.addActionListener(e -> buscarPorEstado());

        filtros.add(btnDestino);
        filtros.add(btnEstado);

        resultados = new JTextArea();
        resultados.setEditable(false);
        JScrollPane scroll = new JScrollPane(resultados);

        add(filtros, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }

    private void cargarDestinos() {
        comboDestinos.removeAllItems();
        Agencia agencia = Agencia.getInstance();
        for (Destino d : agencia.getDestinos()) comboDestinos.addItem(d);
    }

    private void buscarPorDestino() {
        resultados.setText("");
        Destino d = (Destino) comboDestinos.getSelectedItem();
        if (d == null) return;

        List<Viaje> lista = consultaController.listarViajesPorDestino(d.getNombre());
        for (Viaje v : lista) resultados.append(v.toString() + "\n");
    }

    private void buscarPorEstado() {
        resultados.setText("");
        estado e = (estado) comboEstado.getSelectedItem();
        if (e == null) return;

        List<Viaje> lista = consultaController.listarViajesPorEstado(e);
        for (Viaje v : lista) resultados.append(v.toString() + "\n");
    }
}