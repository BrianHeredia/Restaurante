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
public class JefeMesoneros extends Thread{
    Restaurant r;
    Grafica g;
    private Semaphore SEM;
    private int Tiempo;
    private boolean ejecutar=true;
    private boolean pausar=false;

    public Restaurant getR() {
        return r;
    }

    public void setR(Restaurant r) {
        this.r = r;
    }

    public Grafica getG() {
        return g;
    }

    public void setG(Grafica g) {
        this.g = g;
    }

    public Semaphore getSEM() {
        return SEM;
    }

    public void setSEM(Semaphore SEM) {
        this.SEM = SEM;
    }

    public int getTiempo() {
        return Tiempo;
    }

    public void setTiempo(int Tiempo) {
        this.Tiempo = Tiempo;
    }

    public boolean isEjecutar() {
        return ejecutar;
    }

    public void setEjecutar(boolean ejecutar) {
        this.ejecutar = ejecutar;
    }

    public JefeMesoneros(Restaurant r,  Semaphore SEM, int Tiempo) {
        this.r = r;
        this.g = g;
        this.SEM = SEM;
        this.Tiempo = Tiempo;
    }

    

    public void setPausar(boolean pausar) {
        this.pausar = pausar;
    }
     
    public synchronized void Renaudar(){
        this.setPausar(false);
        notify();
    }
    
 
    
        
    // Metodos
  public void run(){
      while(ejecutar){
          try {
             this.r.getGrafica().getjLabel12().setText("durmiendo");
             this.sleep(937,5*this.Tiempo);
             this.r.getGrafica().getjLabel12().setText("Despierto");
             
              
              SEM.acquire();
               //metodo de cronometar
               this.sleep(62,5*this.Tiempo);
               this.Cronometrar();
              SEM.release();
              
                   synchronized(this){
                     if (pausar)
                    this.wait();
           
                }
              
          } catch (InterruptedException ex) {
              Logger.getLogger(JefeMesoneros.class.getName()).log(Level.SEVERE, null, ex);
          }
      
      }
  }
  
  //metodo para ir reduciendo las horas para realizar el despacho
  public void Cronometrar(){
     
      if(this.r.getCont()!=0 && this.r.getCont()>0){
          this.r.setCont(this.r.getCont()-1);
          this.r.getGrafica().getCerradas().setText(Integer.toString(this.r.getCont()));
      }

  } 
}
