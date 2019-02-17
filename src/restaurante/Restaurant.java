/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante;

import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.Integer.parseInt;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class Restaurant extends javax.swing.JFrame {
    // tt => task time
    float ttFuertes = (float) 0.25;
    float ttEntradas = (float) 0.33;
    float ttPostres = (float) 0.30;
    float ttMesoneros = (float) 0.15;
    int ordenes;
    int tiempo; //duracion en segundos de una hora de la simulacion
    int capEnt; //capacidad maxima de meson de entradas
    int capFrt; //capacidad maxima de meson de fuertes
    int capPtr; //capacidad maxima de meson de postres
    int canEnt; //cantidad inicial de cocienros de entradas
    int canFrt; //cantidad inicial de cocienros de fuertes
    int canPtr; //cantidad inicial de cocienros de postres
    int maxEnt; //cantidad maxima de cocienros de entradas
    int maxFrt; //cantidad maxima de cocienros de fuertes
    int maxPtr; //cantidad maxima de cocienros de postres
    int canMes; //cantidad inicial de mesoneros
    int maxMes; //cantidad maxima de mesoneros
    Cocinero_fuertes fuertes []; //vacantes para cocineros de platos fuertes
    Cocinero_entradas entradas []; //vacantes para  cocineros de entradas
    Cocinero_postres postres []; //vacantes para  cocineros de postres
    Meson mesonFuertes = new Meson(30); //Instanciando meson de platos fuertes con capacidad 30 platos
    Meson mesonEntradas = new Meson(20); //Instanciando meson de entradas con capacidad 20 platos
    Meson mesonPostres = new Meson(10); //Instanciando meson de postres con capacidad 10 platos
    JefeMesoneros jefe = new JefeMesoneros((float)0.05);//Instanciando 1 jefe de mesoneros con tiempo de trabajo (duracion task) 0.05
    Gerente gerente = new Gerente((float)0.1);//Instanciando 1 gerente con tiempo de trabajo (duracion task) 0.1
    Mesoneros mesoneros [] ;//vacantes pata mesoneros
    /**
     * Creates new form Restaurant
     */
    public Restaurant() {
        //Instanciar 1 cocienro de cada tipo//
        fuertes[0] = new Cocinero_fuertes(ttFuertes);
        entradas[0] = new Cocinero_entradas(ttEntradas);
        postres[0] = new Cocinero_postres(ttPostres);
        //Instanciar 1 mesonero
        mesoneros[0] = new Mesoneros(ttMesoneros);
        initComponents();
    }
    
    public void leerValores() throws FileNotFoundException{
       
    Scanner s = new Scanner(new File("C:\\Users\\ASUS\\Documents\\NetBeansProyects\\Restaurante\\src\\data.txt"));
    String line=s.nextLine();
    
    this.tiempo = parseInt(line.substring(40,50).trim());
    System.out.println(this.tiempo);
    line=s.nextLine();
    if(this.tiempo<0){
       JOptionPane.showMessageDialog(null, "El campo introducido es invalido");      
    }
      
    this.capEnt = parseInt(line.substring(40,50).trim());
    System.out.println(this.capEnt);
    line=s.nextLine();
    if(this.capEnt<0){
         JOptionPane.showMessageDialog(null, "El campo introducido es invalido");
    }
    
    this.capFrt = parseInt(line.substring(40,50).trim());
    System.out.println(this.capFrt);
    line=s.nextLine();
        if(this.capFrt<0){
             JOptionPane.showMessageDialog(null, "El campo introducido es invalido");
        }
    
    this.capPtr = parseInt(line.substring(40,50).trim());
    System.out.println(this.capPtr);
    line=s.nextLine();
        if(this.capPtr <0){
             JOptionPane.showMessageDialog(null, "El campo introducido es invalido");
        }
    
    this.canEnt = parseInt(line.substring(40,50).trim());
    System.out.println(this.canEnt );
    line=s.nextLine();
        if(this.canEnt <0){
             JOptionPane.showMessageDialog(null, "El campo introducido es invalido");
        }
    
    this.canFrt = parseInt(line.substring(40,50).trim());
    System.out.println(this.canFrt );
    line=s.nextLine();
        if(this.canFrt <0){
             JOptionPane.showMessageDialog(null, "El campo introducido es invalido");
        }
    
    this.canPtr = parseInt(line.substring(40,50).trim());
    System.out.println(this.canPtr);
    line=s.nextLine();
        if(this.canPtr <0){
             JOptionPane.showMessageDialog(null, "El campo introducido es invalido");
        }
    
    this.maxEnt= parseInt(line.substring(40,50).trim());
    System.out.println(this.maxEnt);
    line=s.nextLine();
        if(this.maxEnt <0){
             JOptionPane.showMessageDialog(null, "El campo introducido es invalido");
        }
    
   this.maxFrt = parseInt(line.substring(40,50).trim());
    System.out.println(this.maxFrt );
    line=s.nextLine();
        if(this.maxFrt <0){
             JOptionPane.showMessageDialog(null, "El campo introducido es invalido");
        }
    
    this.maxPtr= parseInt(line.substring(40,50).trim());
    System.out.println(this.maxPtr);
    line=s.nextLine();
        if(this.maxPtr <0){
             JOptionPane.showMessageDialog(null, "El campo introducido es invalido");
        }
    this.canMes = parseInt(line.substring(40,50).trim());
    System.out.println(this.canMes );
    line=s.nextLine();
        if(this.canMes <0){
             JOptionPane.showMessageDialog(null, "El campo introducido es invalido");
        }
    
     this.maxMes= parseInt(line.substring(40,50).trim());
    System.out.println(this.maxMes);
    line=s.nextLine();
        if(this.maxMes<0){
             JOptionPane.showMessageDialog(null, "El campo introducido es invalido");
        }
   }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Restaurant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Restaurant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Restaurant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Restaurant.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Restaurant().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
