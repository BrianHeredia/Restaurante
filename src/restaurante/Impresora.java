/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class Impresora extends Thread{
    Restaurant rest;

    public Impresora(Restaurant rest) {
        this.rest = rest;
    }
    
          @Override
    public void run(){
        while(true){
            this.rest.getMesonE().imprimir();
            this.rest.getMesonF().imprimir();
            this.rest.getMesonP().imprimir();
           
            try {
                this.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}
