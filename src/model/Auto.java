package model;

import java.util.Objects;

public class Auto extends Transporte {
    private float ValorBasePorViaje;
    private float ValorPorKmRecorrido;

public Auto (String patente, int capacidadPasajeros, boolean disponible, float velocidadPromedioXhora, float _ValorBasePorViaje, float _ValorPorKmRecorrido){
    super(patente, 4, disponible, velocidadPromedioXhora);
    this.ValorBasePorViaje = _ValorBasePorViaje;
    this.ValorPorKmRecorrido = _ValorPorKmRecorrido;
} //Constructor tengo que cambiar el nombre de las variables como en c?

public float  getValorBasePorViaje(){return ValorBasePorViaje;
}

public void setValorBasePorViaje(float ValorBasePorViaje){
    if(ValorBasePorViaje>0){
        this.ValorBasePorViaje=ValorBasePorViaje;
    }else {
        this.ValorBasePorViaje=0; //Tiene que ir?
        }
}
public float  getValorPorKmRecorrido(){return ValorPorKmRecorrido; //Tengo que chequear que sea disntinto de 0?
}

public void setValorPorKmRecorrido(float ValorPorKmRecorrido){
    if(ValorPorKmRecorrido>0)
        this.ValorPorKmRecorrido=ValorPorKmRecorrido;
    }

    @Override
    public float calculaCostePorViaje(float kms, int ps) {
        return 0;
    }

    @Override
    public String toString() {
    return "Auto [Valor Base Por viaje:" + ValorBasePorViaje + ",Valor por km recorrido: " + ValorPorKmRecorrido + " ]";
    }
}