/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante;

/**
 *
 * @author ASUS
 */
public class Gerente extends Empleado implements Runnable{
    
    public Gerente(float tiempo_trabajo) {
        super(tiempo_trabajo);
    }

    @Override
    public void run() {
        
    }
    
}
