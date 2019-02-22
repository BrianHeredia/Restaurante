/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class Gerente extends Thread{
   private Restaurant r;
    private Semaphore SEM;
    private Semaphore SA;
    private int n;
    private boolean ejecutar=true;
    private boolean pausar=false;
    private JefeMesoneros jefe;
    private int Mesonr;

    public Gerente(Restaurant r, Semaphore SEM, Semaphore SA, int Mesonr) {
        this.r = r;
        this.SEM = SEM;
        this.SA = SA;
        this.Mesonr = Mesonr;
    }

    public Restaurant getR() {
        return r;
    }

    public void setR(Restaurant r) {
        this.r = r;
    }

    public Semaphore getSEM() {
        return SEM;
    }

    public void setSEM(Semaphore SEM) {
        this.SEM = SEM;
    }

    public Semaphore getSA() {
        return SA;
    }

    public void setSA(Semaphore SA) {
        this.SA = SA;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public boolean isEjecutar() {
        return ejecutar;
    }

    public void setEjecutar(boolean ejecutar) {
        this.ejecutar = ejecutar;
    }

    public JefeMesoneros getJefe() {
        return jefe;
    }

    public void setJefe(JefeMesoneros jefe) {
        this.jefe = jefe;
    }

    public int getMesonr() {
        return Mesonr;
    }

    public void setMesonr(int Mesonr) {
        this.Mesonr = Mesonr;
    }

     public void setPausar(boolean pausar) {
        this.pausar = pausar;
    }
     
    public synchronized void Renaudar(){
        this.setPausar(false);
        notify();
    }
    
    
    @Override
    public void run(){
        while(ejecutar){
            try {
                SEM.acquire(1);
                //seccion critica
                    if(this.r.getCont()==0){
                        this.Cerrar();
                        this.r.setCont(this.r.getTiempo());
                        this.r.getGrafica().getCerradas().setText(Integer.toString(this.r.getCont()));
                        this.r.getGrafica().getOrdenes().setText(Integer.toString(this.r.getMesonr()));
                    }
                SEM.release(1);
                this.Dormir();
                
                synchronized(this){
                    if (pausar)
                        this.wait();
                        
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Gerente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //metodo para hacer cerrar las ordenes
    public void Cerrar() throws InterruptedException{
        
       SA.acquire(1);
        this.r.setMesonr(0);
        this.r.getGrafica().getOrdenes().setText(Integer.toString(this.r.getMesonr()));
        this.SA.release();
        
            
    }
    
    //metodo para hacer dormir el gerente
     public void Dormir(){
        try {
            Random r = new Random();
            if(this.r.getCont()!=0){
                 this.n=r.nextInt(((18-6)+1)+6);
            }else{
             this.n=0;
            }
            this.r.getGrafica().getjLabel11().setText("Durmiendo");
            this.sleep(this.n*1000/24);
            this.r.getGrafica().getjLabel11().setText("Despierto");
        } catch (InterruptedException ex) {
            Logger.getLogger(Gerente.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}
