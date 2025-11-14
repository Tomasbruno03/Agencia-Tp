package model;

public class LargaDistancia extends Viaje{

    public LargaDistancia (int idVia, String nom, Destino destinoViaje){
        super(idVia,nom,destinoViaje);
    }

    @Override
        public void iniciar() {
        if (estadoActual != estado.PENDIENTE)
            throw new IllegalStateException("El viaje ya fue iniciado o finalizado.");
        if (Responsables.isEmpty())
            throw new IllegalStateException("Los viajes largos requieren al menos un responsable.");
        estadoActual = estado.EN_CURSO;
    }
}
