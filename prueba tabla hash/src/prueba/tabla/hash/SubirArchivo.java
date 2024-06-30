/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba.tabla.hash;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class SubirArchivo {

    private TablaHash tablaHash;

    public SubirArchivo(TablaHash tablaHash) {
        this.tablaHash = tablaHash;
    }

    public void subir() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        int result = fileChooser.showOpenDialog(null);

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

                // Usar el nombre del archivo como clave
                String clave = nombreArchivo;

                // Llamar al método agregarInvestigacion con los parámetros actualizados
                tablaHash.agregarInvestigacion(clave, nombreArchivo, autor, palabrasClavesStr, archivoDestino.toString());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al procesar el archivo: " + e.getMessage());
            }
        }
    }
}