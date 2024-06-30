/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba.tabla.hash;

/**
 *
 * @author mainp
 */
public class main {
    public static void main(String[] args) {
        // Crear una instancia de la tabla hash
        TablaHash tablaHash = new TablaHash();
        
        // Crear una instancia de SubirArchivo pasando la tabla hash
        SubirArchivo subirArchivo = new SubirArchivo(tablaHash);
        
        // Llamar al método subir para iniciar el proceso de subida de archivo
        subirArchivo.subir();
    }
}