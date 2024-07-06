/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba.tabla.hash;

/**
 * La clase main es como el director de orquesta de nuestra aplicacion de tabla hash. Aqui es donde todo comienza.
 * Su trabajo principal es preparar el escenario para que nuestra aplicacion luzca bien y funcione sin problemas.
 *
 * Asi es como lo hace:
 * - Primero, trata de ponerle un traje bonito a la aplicacion usando el tema "Nimbus" para la interfaz. Nimbus hace que todo se vea moderno y pulido.
 * - Si por alguna razon Nimbus no esta disponible, no hay problema. Se queda con el estilo clasico de Java, que tambien esta bien.
 * - Despues de decidir sobre la moda de la aplicacion, procede a mostrar la ventana principal para que el usuario pueda empezar a interactuar con la aplicacion.
 *
 * Basicamente, se asegura de que la aplicacion se vea bien y este lista para el show antes de abrir las puertas al publico.
 */
public class main {
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new VentanaCliente().setVisible(true);
        });
    }
}