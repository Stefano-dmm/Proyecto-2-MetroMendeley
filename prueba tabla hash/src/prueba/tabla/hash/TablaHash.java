/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prueba.tabla.hash;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TablaHash {
    private Map<String, String> tablaInvestigaciones;
    private final String nombreArchivo = "investigaciones.txt";
    private final Path path = Paths.get("AlmacenHash");

    public TablaHash() {
        this.tablaInvestigaciones = new HashMap<>();
        crearDirectorioSiNoExiste();
    }

    private void crearDirectorioSiNoExiste() {
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (IOException e) {
            System.out.println("Error al crear el directorio: " + e.getMessage());
        }
    }

    public void agregarInvestigacion(String clave, String titulo, String autor, String palabrasClaves, String punteroArchivo) {
        String valor = String.format("Título: %s, Autor: %s, Palabras Claves: %s, Puntero al Archivo: %s", titulo, autor, palabrasClaves, punteroArchivo);
        tablaInvestigaciones.put(clave, valor);
        guardarInvestigacionesEnTxt();
        imprimirInvestigaciones();
    }

    private void guardarInvestigacionesEnTxt() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path.resolve(nombreArchivo).toString()))) {
            for (Map.Entry<String, String> entrada : tablaInvestigaciones.entrySet()) {
                bw.write(entrada.getKey() + " => " + entrada.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    void imprimirInvestigaciones() {
        for (Map.Entry<String, String> entrada : tablaInvestigaciones.entrySet()) {
            System.out.println(entrada.getKey() + " => " + entrada.getValue());
        }
    }

    public String buscarInvestigacionPorCriterio(String criterio) {
        String resultados = tablaInvestigaciones.entrySet().stream()
                .filter(entry -> entry.getValue().toLowerCase().contains(criterio.toLowerCase()))
                .map(Map.Entry::getValue)
                .collect(Collectors.joining("\n"));
        
        System.out.println("Resultados de la búsqueda:\n" + resultados);
        return resultados;
    }
}

/*
Ejemplo de uso:
        
        // Agregar investigaciones
        tabla.agregarInvestigacion("inv1", "Impacto de la IA en la sociedad", "Jane Doe", "IA, sociedad, tecnología", "path/al/archivo1");
        tabla.agregarInvestigacion("inv2", "Robótica y futuro del trabajo", "John Smith", "robótica, trabajo, futuro", "path/al/archivo2");
        
        // Buscar investigaciones por criterio
        tabla.buscarInvestigacionPorCriterio("sociedad");
    }
}
*/