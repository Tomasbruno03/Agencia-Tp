package persistence;

import model.Transporte;
import model.ResponsableABordo;
import model.Destino;

import java.util.List;

public class LoteDatos { //esta clase es un molde que debe imitar la forma exacta del JSON
    private List<Destino>destinos; //las listas son [] en el JSON
    private List<Transporte>transportes;
    private List<ResponsableABordo>responsables;

    public List<Destino> getDestinos(){
        return destinos;
    }
    public List<ResponsableABordo> getResponsables(){
        return responsables;
    }
    public List<Transporte> getTransportes(){
        return transportes;
    }
}