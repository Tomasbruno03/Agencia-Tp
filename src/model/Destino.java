package model;
import java.util.Locale;
import java.util.Scanner;

public class Destino {
    private String Nombre;
    private float CantKm;

    public Destino(String Nom, float Ckm){
        Nombre=Nom;
        CantKm=Ckm;
    }
    public Destino(){}

    public String getNombre() {
        return Nombre;
    }
    public float getCantKm() {
        return CantKm;
    }
    
    @Override
    public String toString() {
        return "Destino{nombre='" + Nombre + "', cantKm=" + CantKm + "}";
    }

}
