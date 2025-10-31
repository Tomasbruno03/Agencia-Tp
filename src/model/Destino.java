package model;
import java.util.Locale;
import java.util.Scanner;
import java.utils.Objects;

public class Destino implements Comparable <Destino>{
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

    @Override
    public int compareTo(Destino otro)
    {
        return Nombre.compareTo(otro.Nombre);
    }

    @Override
    public Boolean equals(Object o){
        if(this == o) //son el mismo objeto en memoria?
            return true;
        if(o == null || getClass() != o.getClass()) // o el objeto es nulo o son de clases distintas
            return false;
        ResponsableABordo that  = (ResponsableABordo) o; //casteo necesario para usar una variable de tipo clase
        return Objects.equals(this.Nombre,that.Nombre);
    }
}
