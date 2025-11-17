package model;

import exceptions.ValidacionException;

/**
 * Representa un viaje de larga distancia (> 100 km).
 * Para este tipo de viaje se requiere al menos un responsable a bordo.
 * Su costo base es la suma de los sueldos de los responsables asignados.
 */
public class LargaDistancia extends Viaje{
    /**
     * Contructor.
     */
    public LargaDistancia (int idVia, String nom, Destino destinoViaje,int cantp,Transporte t){
        super(idVia,nom,destinoViaje,cantp,t);
    }
    /**
     * Calcula el costo base sumando los sueldos de los responsables.
     *
     * @return costo base total.
     */
    @Override
    public float calcularCostoBase() {
        float Sueldo=0;

        for(ResponsableABordo r: this.getResponsables()){
            Sueldo+=r.GetSueldo();
        }
        return Sueldo;
    }
    /**
     * Devuelve una representación textual del viaje de larga distancia.
     * La descripción incluye la información general del viaje proporcionada
     * por la clase base {@code Viaje} y la cantidad de responsables asignados
     * al viaje en ese momento.
     *
     * @return una cadena descriptiva del viaje de larga distancia.
     */
    @Override
    public String toString() {
        return "LargaDistancia { viaje=" + super.toString() + ", responsables=" + getResponsables().size() + " }";
    }
}
