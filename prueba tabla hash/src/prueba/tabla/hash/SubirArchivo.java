/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba.tabla.hash;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class SubirArchivo {
    TablaHash tablaHash = new TablaHash(); // Asegúrate de tener esta clase definida en tu proyecto

    public void subir() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        int result = fileChooser.showOpenDialog(null);

    /**
     * Esta parte de codigo pertenece a una seccion que maneja la subida de archivos en una aplicacion.
     * Permite al usuario seleccionar un archivo a traves de un JFileChooser, extraer informacion relevante
     * como el nombre del archivo y el autor (a traves de un dialogo de entrada), y procesar el archivo seleccionado
     * para extraer palabras clave, copiarlo a un directorio de almacenamiento especifico y registrar la investigacion
     * en una tabla hash.
     */

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String rutaArchivo = selectedFile.getAbsolutePath();
            String nombreArchivo = selectedFile.getName();

            String autor = JOptionPane.showInputDialog("Ingrese el nombre del autor:");

            try {
                List<String> palabrasClave = PalabraClave.encontrarPalabrasClave(rutaArchivo);
                String palabrasClavesStr = String.join(", ", palabrasClave);

                Path almacenPath = Paths.get("almacenInvestigaciones");
                if (!Files.exists(almacenPath)) {
                    Files.createDirectory(almacenPath);
                }

                Path archivoDestino = almacenPath.resolve(nombreArchivo);
                Files.copy(Paths.get(rutaArchivo), archivoDestino, StandardCopyOption.REPLACE_EXISTING);

                // Extraer el nombre del archivo sin la extensión .txt
                String clave = nombreArchivo.substring(0, nombreArchivo.lastIndexOf('.'));

                // Llamar al método agregarInvestigacion con los parámetros actualizados
                tablaHash.agregarInvestigacion(clave, nombreArchivo, autor, palabrasClavesStr, archivoDestino.toString());
                
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al procesar el archivo: " + e.getMessage());
            }
        }
    }
}