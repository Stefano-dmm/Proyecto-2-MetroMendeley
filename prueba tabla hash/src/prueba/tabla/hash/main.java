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
        TablaHash tabla = new TablaHash();
        
        // Agregar un libro de prueba
        tabla.agregarInvestigacion("libroPrueba", "Introducción a la Programación", "Juan Pérez", "programación, introducción, educación", "/ruta/al/libroPrueba.pdf");
        
        // Imprimir todas las investigaciones para verificar
        System.out.println("Libro de prueba agregado:");
        tabla.imprimirInvestigaciones();
        
        // Buscar el libro de prueba por un criterio específico, por ejemplo, "programación"
        System.out.println("\nBuscando investigaciones que contienen 'programación':");
        tabla.buscarInvestigacionPorCriterio("programación");
    }
}
