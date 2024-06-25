/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.metromendeley.v1.pkg0;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mainp
 */

public class TablaHash {
    private Map<Integer, Investigacion> investigaciones;
    private String rutaAlmacen;

    public TablaHash() {
        this.investigaciones = new HashMap<>();
        this.rutaAlmacen = "almacen"; // ruta relativa a la carpeta "almacen"
        crearCarpetaAlmacen();
    }

    private void crearCarpetaAlmacen() {
        File carpetaAlmacen = new File(rutaAlmacen);
        if (!carpetaAlmacen.exists()) {
            if (!carpetaAlmacen.mkdir()) {
                System.err.println("Error al crear la carpeta " + rutaAlmacen);
            } else {
                System.out.println("Carpeta " + rutaAlmacen + " creada con éxito");
            }
        } else {
            System.out.println("La carpeta " + rutaAlmacen + " ya existe");
        }
    }

    public void addInvestigacion(Investigacion investigacion) {
        int hash = investigacion.hashCode();
        investigaciones.put(hash, investigacion);
        guardarArchivoInvestigacion(investigacion, hash);
    }

    private void guardarArchivoInvestigacion(Investigacion investigacion, int hash) {
        String rutaArchivo = rutaAlmacen + "/" + hash + ".txt";
        try {
            File archivo = new File(rutaArchivo);
            archivo.createNewFile();
            // guardar el contenido del archivo aquí
            System.out.println("Archivo guardado en " + rutaArchivo);
        } catch (IOException e) {
            System.err.println("Error al guardar archivo: " + e.getMessage());
        }
    }

    public Investigacion getInvestigacion(int hash) {
        return investigaciones.get(hash);
    }

    public void printTablaHash() {
        System.out.println("Tabla Hash:");
        System.out.println("-----------");
        for (Map.Entry<Integer, Investigacion> entry : investigaciones.entrySet()) {
            System.out.println("Hash: " + entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println("-----------");
    }
}
