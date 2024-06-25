/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto.metromendeley.v1.pkg0;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProyectoMetroMendelyV1 {
    public static void main(String[] args) {
        String rutaAlmacen = "almacen";
        File directorioAlmacen = new File(rutaAlmacen);

        if (!directorioAlmacen.exists()) {
            if (directorioAlmacen.mkdir()) {
                System.out.println("Directorio 'almacen' creado con éxito");
            } else {
                System.out.println("Error al crear el directorio 'almacen'");
                return;
            }
        }

        if (directorioAlmacen.list().length == 0) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto", "txt");
            chooser.setFileFilter(filter);
            chooser.setMultiSelectionEnabled(true);
            int returnVal = chooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File[] files = chooser.getSelectedFiles();
                for (File file : files) {
                    try {
                        directorioAlmacen.mkdir();
                        File destino = new File(directorioAlmacen, file.getName());
                        file.renameTo(destino);
                    } catch (Exception e) {
                        System.out.println("Error al mover el archivo: " + e.getMessage());
                    }
                }
            } else {
                System.out.println("No se seleccionó ningún archivo");
                return;
            }
        }

        List<String> palabrasClave = new ArrayList<>();
        for (File file : directorioAlmacen.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                palabrasClave.addAll(leerArchivo(file.getAbsolutePath()));
            }
        }

        Map<String, PalabraClave> mapaPalabrasClave = new HashMap<>();

        for (String palabra : palabrasClave) {
            PalabraClave palabraClave = mapaPalabrasClave.get(palabra);
            if (palabraClave == null) {
                palabraClave = new PalabraClave(palabra);
                mapaPalabrasClave.put(palabra, palabraClave);
            } else {
                palabraClave.incrementarFrecuencia();
            }
        }

        VentanaUsuario ventana = new VentanaUsuario(mapaPalabrasClave);
    }

    public static List<String> leerArchivo(String rutaArchivo) {
        List<String> palabrasClave = new ArrayList<>();
        try {
            java.util.Scanner scanner = new java.util.Scanner(new File(rutaArchivo));
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] palabras = linea.split("\\s+");
                for (String palabra : palabras) {
                    palabrasClave.add(palabra);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return palabrasClave;
    }
}

class PalabraClave {
    private String palabra;
    private int frecuencia;

    public PalabraClave(String palabra) {
        this.palabra = palabra;
        this.frecuencia = 1;
    }

    public void incrementarFrecuencia() {
        this.frecuencia++;
    }

    @Override
    public String toString() {
        return palabra + ": " + frecuencia;
    }
}

class VentanaUsuario {
    private JFrame ventana;
    private JTextArea texto;

    public VentanaUsuario(Map<String, PalabraClave> mapaPalabrasClave) {
        ventana = new JFrame("Ventana de Usuario");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new BorderLayout());

        texto = new JTextArea();
        texto.setEditable(false);

        for (PalabraClave palabraClave : mapaPalabrasClave.values()) {
            texto.append(palabraClave.toString() + "\n");
        }

        ventana.add(new JScrollPane(texto), BorderLayout.CENTER);

        JButton botonCerrar = new JButton("Cerrar");
        botonCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.dispose();
            }
        });

        ventana.add(botonCerrar, BorderLayout.SOUTH);

        ventana.pack();
        ventana.setVisible(true);
    }
}