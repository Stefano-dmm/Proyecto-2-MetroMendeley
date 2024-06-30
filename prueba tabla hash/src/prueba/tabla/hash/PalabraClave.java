/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba.tabla.hash;

/**
 *
 * @author mainp
 */
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PalabraClave {

    public static List<String> encontrarPalabrasClave(String rutaArchivo) throws IOException {
        Map<String, Long> frecuenciaPalabras = new HashMap<>();

        // Leer el archivo y contar la frecuencia de cada palabra
        try (Stream<String> lineas = Files.lines(Paths.get(rutaArchivo))) {
            lineas.forEach(linea -> {
                String[] palabras = linea.toLowerCase().split("\\W+");
                for (String palabra : palabras) {
                    frecuenciaPalabras.merge(palabra, 1L, Long::sum);
                }
            });
        }

        // Ordenar palabras por frecuencia y devolver las más repetidas
        return frecuenciaPalabras.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(10) // Ajustar este valor según cuántas palabras claves se deseen retornar
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}