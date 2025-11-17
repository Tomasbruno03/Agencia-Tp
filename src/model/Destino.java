package model;
import exceptions.ValidacionException;
import java.util.Objects;

/**
 * Representa un destino turístico con nombre único y una cantidad de kilómetros.
 * Determina si es de corta o larga distancia según su distancia.
 */
public class Destino implements Comparable <Destino>{

    private String Nombre;
    private float CantKm;

    /**
     * Constructor.
     *
     * @param Nom nombre del destino.
     * @param Ckm kilómetros del destino.
     * @throws ValidacionException si los datos son inválidos.
     */
    public Destino(String Nom, float Ckm) throws ValidacionException {
        //Valida datos
        if (Ckm <= 0) {
            throw new ValidacionException("Los kilómetros deben ser un valor positivo.");
        }
        if (Nom == null || Nom.trim().isEmpty()) {
            throw new ValidacionException("El nombre no puede estar vacío.");
        }

        // Si pasa, se crea el objeto
        this.Nombre = Nom;
        this.CantKm = Ckm;
    }

    public String getNombre() {
        return Nombre;
    }

    public float getCantKm() {
        return CantKm;
    }

    /**
     * Retorna una representación en texto del destino,
     * incluyendo su nombre y la cantidad de kilómetros.
     *
     * @return una cadena con el formato:
     *         {@code Destino{nombre='X', cantKm=Y}}
     */
    @Override
    public String toString() {
        return "Destino{nombre='" + Nombre + "', cantKm=" + CantKm + "}";
    }
/**
     * Compara este destino con otro destino según el nombre, en orden alfabético.
 *
         * @param otro el destino con el cual se va a comparar.
            * @return un valor negativo si este destino es menor al otro,
 *         cero si son iguales,
 *         o un valor positivo si este destino es mayor al otro.
 */
    @Override
    public int compareTo(Destino otro)
    {
        return Nombre.compareTo(otro.getNombre());
    }

    /**
     * Compara este destino con otro objeto para determinar igualdad.
     * Dos destinos son considerados iguales si tienen el mismo nombre.
     *
     * @param o el objeto a comparar.
     * @return {@code true} si representan el mismo destino,
     *         {@code false} en caso contrario.
     */
    @Override
    public boolean equals(Object o){
        if(this == o) //son el mismo objeto en memoria?
            return true;
        if(o == null || getClass() != o.getClass()) // o el objeto es nulo o son de clases distintas
            return false;
        Destino that  = (Destino) o; //casteo necesario para usar una variable de tipo clase
        return Objects.equals(this.Nombre,that.getNombre());
    }
    /** @return true si supera los 100 km. */
    public boolean esLargaDistancia(){
        return this.CantKm>100;
    }

    /**
     * Calcula el código hash del destino basado únicamente en su nombre.
     * Esto es coherente con la implementación de {@link #equals(Object)}.
     *
     * @return el hash generado para este destino.
     */
    @Override
    public int hashCode(){
        return Objects.hash(this.Nombre);
    }
}
