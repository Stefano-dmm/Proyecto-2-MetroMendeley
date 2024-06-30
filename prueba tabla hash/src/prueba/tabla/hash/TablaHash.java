/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prueba.tabla.hash;
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
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
    private final String nombreArchivo = "tablaHash.txt";
    private final Path path = Paths.get("AlmacenHash");

    public TablaHash() {
        this.tablaInvestigaciones = new HashMap<>();
        crearDirectorioSiNoExiste();
        cargarInvestigacionesDesdeTxt();
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

    private void cargarInvestigacionesDesdeTxt() {
        try (BufferedReader br = new BufferedReader(new FileReader(path.resolve(nombreArchivo).toString()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(" => ", 2);
                if (partes.length == 2) {
                    tablaInvestigaciones.put(partes[0], partes[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public void agregarInvestigacion(String clave, String titulo, String autor, String palabrasClaves, String punteroArchivo) {
        // Verificar si ya existe una investigación con el mismo título y autor
        boolean existe = tablaInvestigaciones.values().stream()
                .anyMatch(valor -> valor.contains("Título: " + titulo) && valor.contains("Autor: " + autor));

        if (existe) {
            // Mostrar ventana de diálogo
            JOptionPane.showMessageDialog(null, "Archivo con Título: " + titulo + " y Autor: " + autor + " ya existente.");
        } else {
            String valor = String.format("Título: %s, Autor: %s, Palabras Claves: %s, Puntero al Archivo: %s", titulo, autor, palabrasClaves, punteroArchivo);
            tablaInvestigaciones.put(clave, valor);
            guardarInvestigacionesEnTxt(clave, valor);
            imprimirInvestigaciones();
        }
    }

    private void guardarInvestigacionesEnTxt(String clave, String valor) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path.resolve(nombreArchivo).toString(), true))) {
            bw.write(clave + " => " + valor);
            bw.newLine();
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
    
    public void eliminarInvestigacion(String clave) {
        if (tablaInvestigaciones.containsKey(clave)) {
            String detallesInvestigacion = tablaInvestigaciones.get(clave);
            // Suponiendo que la ruta del archivo es el último elemento después de dividir la cadena por ","
            String[] partes = detallesInvestigacion.split(",");
            String rutaArchivo = partes[partes.length - 1].trim(); // Asegúrate de que este índice corresponda a la ruta del archivo en tu estructura de datos
    
            // Corrección: Eliminar cualquier prefijo no deseado de la ruta del archivo
            rutaArchivo = rutaArchivo.replace("Puntero al Archivo: ", "").trim();
    
            try {
                Files.deleteIfExists(Paths.get(rutaArchivo));
                System.out.println("Archivo de investigación eliminado con éxito.");
            } catch (IOException e) {
                System.out.println("Error al eliminar el archivo de investigación: " + e.getMessage());
                return; // No continuar si falla la eliminación del archivo
            }
    
            tablaInvestigaciones.remove(clave);
            System.out.println("Investigación eliminada con éxito.");
            actualizarArchivoDeInvestigaciones();
        } else {
            System.out.println("La investigación con la clave proporcionada no existe.");
        }
    }

    private void actualizarArchivoDeInvestigaciones() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path.resolve(nombreArchivo).toString()))) {
            for (Map.Entry<String, String> entrada : tablaInvestigaciones.entrySet()) {
                bw.write(entrada.getKey() + " => " + entrada.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al actualizar el archivo: " + e.getMessage());
        }
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