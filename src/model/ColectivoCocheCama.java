package model;

import java.util.Objects;

public class ColectivoCocheCama extends Transporte{
private int CantSemiCama;
private int CantCama;
private float ValorPorPasajeroPorKmRecorrido;
private float ValorPlazaTipoCamaPorKmRecorrido;

    public ColectivoCocheCama (String patente, int capacidadPasajeros, boolean disponible, float velocidadPromedioXhora, int CantSemiCama,int CantCama,float ValorPorPasajeroPorKmRecorrido, float ValorPlazaTipoCamaPorKmRecorrido){
        super(patente, 32, disponible, velocidadPromedioXhora);
        this.CantSemiCama = 6;
        this.CantCama = 26;
        this.ValorPorPasajeroPorKmRecorrido= ValorPorPasajeroPorKmRecorrido;
        this.ValorPlazaTipoCamaPorKmRecorrido= ValorPlazaTipoCamaPorKmRecorrido;
    }

    public int getCantSemiCama(){return CantSemiCama;}

    public int getCantCama(){return CantCama;}

    public float getValorPorPasajeroPorKmRecorrido(){return ValorPorPasajeroPorKmRecorrido;}

    public float getValorPlazaTipoCamaPorKmRecorrido(){return ValorPlazaTipoCamaPorKmRecorrido;}

    public void setValorPorPasajeroPorKmRecorrido(float ValorPorPasajeroPorKmRecorrido){
        this.ValorPorPasajeroPorKmRecorrido= ValorPorPasajeroPorKmRecorrido;
    }

    public void setValorPlazaTipoCamaPorKmRecorrido(float ValorPlazaTipoCamaPorKmRecorrido){
        this.ValorPlazaTipoCamaPorKmRecorrido=ValorPlazaTipoCamaPorKmRecorrido;
    }

    @Override
    public String toString(){
        return "ColectivoCocheCama [Cantidad de pasajes Semi Cama:" + CantSemiCama + ",Cantidad de pasajes Cama: " + CantCama + "Valor por pasajero por km recorrido:" + ValorPorPasajeroPorKmRecorrido + ",Valor por plaza tipo cama por km recorrido: " + ValorPlazaTipoCamaPorKmRecorrido +" ]";
    }

}
