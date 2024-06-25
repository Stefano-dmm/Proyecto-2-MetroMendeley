/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.metromendeley.v1.pkg0;

import java.util.List;

/**
 *
 * @author mainp
 */
 public class Investigacion {
    // Título de la investigación
    private String titulo;
    // Lista de autores de la investigación
    private List<String> autores;
    // Cuerpo de la investigación
    private String cuerpo;
    // Lista de palabras clave relacionadas con la investigación
    private List<PalabraClave> palabrasClave;

    /**
     * Constructor de la clase Investigacion
     * @param titulo Título de la investigación
     * @param autores Lista de autores de la investigación
     * @param cuerpo Cuerpo de la investigación
     * @param palabrasClave Lista de palabras clave relacionadas con la investigación
     */
    public Investigacion(String titulo, List<String> autores, String cuerpo, List<PalabraClave> palabrasClave) {
        this.titulo = titulo;
        this.autores = autores;
        this.cuerpo = cuerpo;
        this.palabrasClave = palabrasClave;
    }

    /**
     * Método hashCode() sobrescrito para comparar objetos de tipo Investigacion
     * @return Hash code del título de la investigación
     */
    @Override
    public int hashCode() {
        return titulo.hashCode();
    }

    /**
     * Método equals() sobrescrito para comparar objetos de tipo Investigacion
     * @param obj Objeto a comparar
     * @return True si el objeto es igual a este, false en caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Investigacion otra = (Investigacion) obj;
        return titulo.equals(otra.titulo);
    }
}
