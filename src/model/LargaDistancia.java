package model;

public class LargaDistancia extends Viaje{

    public LargaDistancia (int idVia, String nom, Destino destinoViaje){
        super(idVia,nom,destinoViaje);
    }

    @Override
        public void iniciar() {
        if (getEstado() != estado.PENDIENTE)
            throw new IllegalStateException("El viaje ya fue iniciado o finalizado.");
        if (getResponsables().isEmpty())
            throw new IllegalStateException("Los viajes largos requieren al menos un responsable.");
        ponerEnCurso();
    }

    @Override
    public float calcularCosto() {
        return 0;
    }

}
