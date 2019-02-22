/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante;

import java.io.FileNotFoundException;

/**
 *
 * @author ASUS
 */
public class App {
    public static void main(String args[]) throws FileNotFoundException {
            Restaurant r = new Restaurant();
            Grafica g= new Grafica(r);
            g.setF(r);
            r.setGrafica(g);
            Impresora impresora=new Impresora(r);           
            r.LeerTxt();
            r.CrearArreglos();
            r.GenerarMesones();
            r.Semaforos();
            r.CrearCocineros();
            r.CrearMesoneros();
            r.RestaurantCocineros(r);
            r.RestaurantMesoneros(r);
            r.iniciar();
            r.getGrafica().setVisible(true);
            impresora.start();
            System.out.println(r.getMesonr());     
    }
}
