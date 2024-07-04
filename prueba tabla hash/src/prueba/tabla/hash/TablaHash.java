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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TablaHash {
    private static Map<String, String> tablaInvestigaciones;
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
            // Luego de copiar el archivo y antes del catch, agregar el mensaje de éxito
JOptionPane.showMessageDialog(null, "Investigación '" + nombreArchivo + "' subida", "Subida completada", JOptionPane.INFORMATION_MESSAGE);
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

    public void imprimirTitulos() {
        tablaInvestigaciones.keySet().forEach(System.out::println);
    }

    public String buscarInvestigacionPorCriterio(String criterio) {
        String resultados = tablaInvestigaciones.entrySet().stream()
                .filter(entry -> entry.getValue().toLowerCase().contains(criterio.toLowerCase()))
                .map(Map.Entry::getValue)
                .collect(Collectors.joining("\n"));

        System.out.println("Resultados de la búsqueda:\n" + resultados);
        return resultados;
    }private List<String> leerNombresInvestigaciones() {
    List<String> nombres = new ArrayList<>();
    try (BufferedReader reader = Files.newBufferedReader(Paths.get(nombreArchivo))) {
        String linea;
        while ((linea = reader.readLine()) != null) {
            // Aquí asumimos que el nombre de la investigación está al inicio de cada línea
            // y está separado del resto de la información por un carácter específico, como ":".
            String nombre = linea.split(":")[0];
            nombres.add(nombre);
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + e.getMessage());
    }
    return nombres;
    }
    
    public static void eliminarInvestigacion(String clave) {
        if (tablaInvestigaciones.containsKey(clave)) {
            String detallesInvestigacion = tablaInvestigaciones.get(clave);
            String[] partes = detallesInvestigacion.split(",");
            String rutaArchivo = partes[partes.length - 1].trim();
            rutaArchivo = rutaArchivo.replace("Puntero al Archivo: ", "").trim();
    
            try {
                Files.deleteIfExists(Paths.get(rutaArchivo));
                System.out.println("Archivo de investigación eliminado con éxito.");
            } catch (IOException e) {
                System.out.println("Error al eliminar el archivo de investigación: " + e.getMessage());
                return;
            }
    
            tablaInvestigaciones.remove(clave);
            System.out.println("Investigación eliminada con éxito.");
    
            // Eliminar la línea correspondiente del archivo tablahash.txt
            try {
                Path pathArchivo = Paths.get("AlmacenHash/tablaHash.txt");
                List<String> lineas = Files.readAllLines(pathArchivo);
                lineas.removeIf(linea -> linea.contains(clave)); // Suponiendo que la clave es única y suficiente para identificar la línea
                Files.write(pathArchivo, lineas);
                System.out.println("Línea correspondiente eliminada del archivo tablahash.txt.");
            } catch (IOException e) {
                System.out.println("Error al actualizar el archivo tablahash.txt: " + e.getMessage());
            }
        } else {
            System.out.println("La investigación con la clave proporcionada no existe.");
        } 
    }
    
    public void buscarYMostrar(int indice) {
        try {
            Path archivoCompleto = path.resolve(nombreArchivo);
            List<String> lineas = Files.readAllLines(archivoCompleto);
    
            if (indice >= 0 && indice < lineas.size()) {
                String linea = lineas.get(indice);
                String rutaArchivo = linea.substring(linea.lastIndexOf(": ") + 2);
                rutaArchivo = rutaArchivo.replaceAll("[<>:\"/\\|?*]", ""); // Ajusta según sea necesario
                String contenidoArchivo = new String(Files.readAllBytes(Paths.get(rutaArchivo)));
    
                // Crear una ventana para mostrar el contenido del archivo
                JFrame frame = new JFrame("Contenido del Archivo");
                JTextArea textArea = new JTextArea(25, 50);
                textArea.setText(contenidoArchivo);
                textArea.setEditable(false); // Hacer el área de texto no editable
    
                // Agregar un JScrollPane al JTextArea
                JScrollPane scrollPane = new JScrollPane(textArea);
                frame.getContentPane().add(scrollPane);
    
                // Configurar la ventana
                frame.pack(); // Ajustar el tamaño de la ventana al contenido
                frame.setLocationRelativeTo(null); // Centrar la ventana
                frame.setVisible(true); // Mostrar la ventana
            } else {
                JOptionPane.showMessageDialog(null, "Índice fuera de rango", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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