/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class Cocinero extends Thread{
    private Semaphore SE;
    private Semaphore SP;
    private Semaphore SC;
    private  int Trabajo;
    private int produccion=0;
    private int consumir;
    private Grafica grafica;
    private Meson meson;
    Restaurant r;
    private boolean ejecutar=true;
    private int id;
    private boolean pausa=false;

    public Cocinero(Semaphore SP, Semaphore SE, Semaphore SC, Meson meson, int produccion, int  id, Restaurant r, int Trabajo ) {
        this.SE = SE;
        this.SP = SP;
        this.Trabajo = Trabajo;
        this.produccion = produccion;
        this.meson = meson;
        this.r = r;
        this.id = id;
    }
    
    

    public Semaphore getSE() {
        return SE;
    }

    public void setSE(Semaphore SE) {
        this.SE = SE;
    }

    public Semaphore getSP() {
        return SP;
    }

    public void setSP(Semaphore SP) {
        this.SP = SP;
    }

    public Semaphore getSC() {
        return SC;
    }

    public void setSC(Semaphore SC) {
        this.SC = SC;
    }

    public int getTrabajo() {
        return Trabajo;
    }

    public void setJornada(int Trabajo) {
        this.Trabajo = Trabajo;
    }

    public int getProduc() {
        return produccion;
    }

    public void setProduc(int produccion) {
        this.produccion = produccion;
    }

    public int getProduccion() {
        return produccion;
    }

    public void setProduccion(int produccion) {
        this.produccion = produccion;
    }

    public int getConsumir() {
        return consumir;
    }

    public void setConsumir(int consumir) {
        this.consumir = consumir;
    }

    public Meson getMeson() {
        return meson;
    }

    public void setMeson(Meson meson) {
        this.meson = meson;
    }

   



    public Grafica getGrafica() {
        return grafica;
    }

    public void setGrafica(Grafica grafica) {
        this.grafica = grafica;
    }

    public Restaurant getR() {
        return r;
    }

    public void setR(Restaurant r) {
        this.r = r;
    }

    

    public boolean isEjecutar() {
        return ejecutar;
    }

    public void setEjecutar(boolean ejecutar) {
        this.ejecutar = ejecutar;
    }

    public boolean isPausa() {
        return pausa;
    }
    
   

    public void setPausa(boolean pausa) {
        this.pausa = pausa;
    }
     
    public synchronized void Renaudar(){
        this.setPausa(false);
        notify();
    }
    
    
    
      @Override
    public void run(){
        while(ejecutar){
        try {
                SP.acquire();
                SE.acquire(); 
                this.Cocinar();          
                SE.release();
                SC.release();
                
                this.sleep(1000*this.Trabajo);
                 synchronized(this){
                    if (pausa)
                        this.wait();       
                }
              
            } catch (InterruptedException ex) {
                Logger.getLogger(Cocinero.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
    }
    
    //metodo para cocinar cada uno de los platos establecidos por el restaurant
    public void Cocinar(){
         
        if(this.id==1){
           
            this.r.getMesonE().setVec(this.r.getProduccionE(), 1);
            this.r.setProduccionE((this.r.getProduccionE()+1)%this.r.getMesonE().getTam());  
            this.r.setEntradas(this.r.getEntradas()+1);
           
            if(this.r.getEntradas()<=this.r.getMaxE()){
            this.r.getGrafica().getEntradas().setText(Integer.toString(this.r.getEntradas()));
            }   
              
        }
        else if(this.id==2){
            this.r.getMesonF().setVec(this.r.getProduccionF(), 2);
            this.r.setProduccionF((this.r.getProduccionF()+1)%this.r.getMesonF().getTam());
            this.r.setFuertes(this.r.getFuertes()+1);
        
        if(this.r.getFuertes()<= this.r.getMaxF()){
            this.r.getGrafica().getFuertes().setText(Integer.toString(this.r.getFuertes()));
        }  
        }else{
            this.r.getMesonP().setVec(this.r.getProduccionP(), 3);
            this.r.setProduccionP((this.r.getProduccionP()+1)%this.r.getMesonP().getTam());
            this.r.setPostres(this.r.getPostres()+1);
        
        if(this.r.getPostres()<= this.r.getMaxP()){
            this.r.getGrafica().getPostres().setText(Integer.toString(this.r.getPostres()));
        }
        }
      
    }
    

    
}
