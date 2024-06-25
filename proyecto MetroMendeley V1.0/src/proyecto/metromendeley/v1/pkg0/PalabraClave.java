/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.metromendeley.v1.pkg0;

/**
 *
 * @author mainp
 */
public class PalabraClave {
    private String palabra;
    private int frecuencia;

    public PalabraClave(String palabra) {
        this.palabra = palabra;
        this.frecuencia = 1;
    }

    public String getPalabra() {
        return palabra;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void incrementarFrecuencia() {
        frecuencia++;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass()!= obj.getClass()) {
            return false;
        }
        PalabraClave palabraClave = (PalabraClave) obj;
        return palabra.equals(palabraClave.palabra);
    }

    @Override
    public int hashCode() {
        return palabra.hashCode();
    }

    @Override
    public String toString() {
        return "PalabraClave{" +
                "palabra='" + palabra + '\'' +
                ", frecuencia=" + frecuencia +
                '}';
    }
}
