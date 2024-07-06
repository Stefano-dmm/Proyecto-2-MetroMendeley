/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba.tabla.hash;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * La clase PalabraClave se encarga de encontrar y contar la frecuencia de cada palabra en un archivo de texto.
 * Es como un detective que lee meticulosamente cada linea del archivo, descompone las oraciones en palabras
 * y lleva un registro de cuantas veces aparece cada palabra.
 *
 * Aqui esta como lo hace:
 * - Primero, abre el archivo de texto ubicado en la ruta especificada.
 * - Luego, lee el archivo linea por linea.
 * - Para cada linea, divide el texto en palabras, ignorando cualquier signo de puntuacion y convirtiendo todo a minusculas para evitar duplicados.
 * - Finalmente, cuenta cuantas veces aparece cada palabra en todo el archivo y guarda esa informacion en un mapa.
 *
 * Es una herramienta util para analizar el contenido de un archivo y entender cuales son los temas mas frecuentes en el texto.
 */

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