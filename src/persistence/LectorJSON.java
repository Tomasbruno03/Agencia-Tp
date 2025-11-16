package persistence;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//esta clase se podria decir que es un gestor de persistencia ya que sabe escribir como leer
import model.Agencia;
import  model.Transporte;
import model.Destino;
import model.ResponsableABordo;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class LectorJSON {
    public void cargarDatos(Agencia agencia, String RutaArchivo){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Transporte.class,new TransporteDeserializer());
        Gson gson = gsonBuilder.create();

        try(FileReader reader = new FileReader(RutaArchivo)){
            LoteDatos lote = gson.fromJson(reader, LoteDatos.class);
            if(lote.getDestinos() != null) {//para cada destino en la lista,llama a agencia.agregaDestino()
                lote.getDestinos().forEach(agencia::agregarDestino);
            }
            if(lote.getResponsables()!=null){
                lote.getResponsables().forEach(agencia::agregarResponsable);//lo mismo para reponsable
            }
            if(lote.getTransportes()!=null){
                lote.getTransportes().forEach(agencia::Agregar_TransporteALista);
            }
            System.out.println("Archivo cargado exitosamente desde " + RutaArchivo);
        }catch (IOException e){ //Archivo no encontrado
            System.err.println("Error FATAL: No se pudo encontrar el archivo JSON en " + RutaArchivo);
            e.printStackTrace();
        }catch (Exception e){
            System.err.println("Error FATAL : El archivo JSON esta mal formado o dañado.");
            e.printStackTrace();
        }
    }
}
