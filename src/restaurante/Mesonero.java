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
public class Mesonero extends Thread{
    
   private Semaphore SCE;
   private Semaphore SPEE;
   private Semaphore SPE;
  
 
   private Semaphore SCF;
   private Semaphore SPEF;
   private Semaphore SPF;
   
   private Semaphore SCP;
   private Semaphore SPEP;
   private Semaphore SPP;
    
    private  int Trabajo;
    private int consumirE;
    private int consumirF;
    private int consumirP;
    private Grafica grafica;
    private boolean ejecutar=true;
    Restaurant r;
    private int id;
    private Semaphore SA;
    
    private boolean pausa;
    
    public Mesonero( Semaphore SCE, Semaphore SPEE, Semaphore SPE,Semaphore SCF, Semaphore SPEF, Semaphore SPF,Semaphore SCP, Semaphore SPEP, Semaphore SPP , int Trabajo, int consumirE,int consumirF,int consumirP, Restaurant r, int id,Semaphore SA) {
        this.SCP = SCP;
        this.SPEP = SPEP;
        this.SPP = SPP;
        this.SCF = SCF;
        this.SPEF = SPEF;
        this.SPF = SPF;
        this.SCE = SCE;
        this.SPEE = SPEE;
        this.SPE = SPE;
        this.Trabajo = Trabajo;
        this.consumirP = consumirP;
        this.consumirF=consumirF;
        this.consumirE=consumirE;
        this.r = r;
        this.id = id;
        this.SA=SA;
    }

    public Semaphore getSCP() {
        return SCP;
    }

    public void setSCP(Semaphore SCP) {
        this.SCP = SCP;
    }

    public Semaphore getSPEP() {
        return SPEP;
    }

    public void setSPEP(Semaphore SPEP) {
        this.SPEP = SPEP;
    }

    public Semaphore getSPP() {
        return SPP;
    }

    public void setSPP(Semaphore SPP) {
        this.SPP = SPP;
    }

    

    public int getTrabajo() {
        return Trabajo;
    }

    public void setTrabajo(int Trabajo) {
        this.Trabajo = Trabajo;
    }

    public int getConsumirP() {
        return consumirP;
    }

    public void setConsumirP(int consumir) {
        this.consumirP = consumirP;
    }

    
    
    
    public Grafica getGrafica() {
        return grafica;
    }

    public void setGrafica(Grafica grafica) {
        this.grafica = grafica;
    }

    public Semaphore getSCE() {
        return SCE;
    }

    public void setSCE(Semaphore SCE) {
        this.SCE = SCE;
    }

    public Semaphore getSPEE() {
        return SPEE;
    }

    public void setSPEE(Semaphore SPEE) {
        this.SPEE = SPEE;
    }

    public Semaphore getSPE() {
        return SPE;
    }

    public void setSPE(Semaphore SPE) {
        this.SPE = SPE;
    }

    public Semaphore getSCF() {
        return SCF;
    }

    public void setSCF(Semaphore SCF) {
        this.SCF = SCF;
    }

    public Semaphore getSPEF() {
        return SPEF;
    }

    public void setSPEF(Semaphore SPEF) {
        this.SPEF = SPEF;
    }

    public Semaphore getSPF() {
        return SPF;
    }

    public void setSPF(Semaphore SPF) {
        this.SPF = SPF;
    }

    public int getConsumirE() {
        return consumirE;
    }

    public void setConsumirE(int consumirE) {
        this.consumirE = consumirE;
    }

    public int getConsumirF() {
        return consumirF;
    }

    public void setConsumirF(int consumirF) {
        this.consumirF = consumirF;
    }

    public Restaurant getR() {
        return r;
    }

    public void setR(Restaurant r) {
        this.r = r;
    }

    public Semaphore getSA() {
        return SA;
    }

    public void setSA(Semaphore SA) {
        this.SA = SA;
    }

   

    
 

    public void setId(int id) {
        this.id = id;
    }

    public boolean isPausa() {
        return pausa;
    }

    public void setPausa(boolean pausa) {
        this.pausa = pausa;
    }
    
    public synchronized void Renaudar(){
        this.setPausa(false);
    }

    public boolean isEjecutar() {
        return ejecutar;
    }

    public void setEjecutar(boolean ejecutar) {
        this.ejecutar = ejecutar;
    }
    
    
       @Override
    public void run(){
        while(ejecutar){
        try {
            
            
                this.SCE.acquire(2);
                this.SPEE.acquire();
                //consumir entradas
                this.ConsumirEntradas();
                this.SPEE.release();
                this.SPE.release(2);
                
                this.SCF.acquire();
                this.SPEF.acquire();
                //consumir fuertes
                this.ConsumirFuertes();
                this.SPEF.release();
                this.SPF.release();
                
                 this.SCP.acquire();
                this.SPEP.acquire();
                //consumir postres
                this.ConsumirPostres();
            
                this.SPEP.release();
                this.SPP.release();
                this.sleep(1000*2);
                
                 synchronized(this){
                    if (pausa)
                        this.wait();
                        
                }
//                this.SA.acquire();
                this.r.setMesonr(this.r.getMesonr()+1);
                System.out.println("telefono creado "+" [ "+this.r.getMesonr()+" ]");
                this.r.getGrafica().getOrdenes().setText(Integer.toString(this.r.getMesonr()));
                
//                this.SA.release();
//              
            } catch (InterruptedException ex) {
                Logger.getLogger(Cocinero.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
    }
    
    public void ConsumirPostres(){
       
       this.r.getMesonP().setVec(this.r.getConsumirP(), 0);
       this.r.setConsumirP((this.r.getConsumirP()+1)%this.r.getMesonP().getTam());
       if(this.r.getPostres()>0){
       this.r.setPostres(this.r.getPostres()-1);
       this.r.getGrafica().getPostres().setText(Integer.toString(this.r.getPostres()));
       }
    }
    
    public void ConsumirFuertes(){
        this.r.getMesonF().setVec(this.r.getConsumirF(), 0);
        this.r.setConsumirF((this.r.getConsumirF()+1)%this.r.getMesonF().getTam());
            if(this.r.getFuertes()>0){
       this.r.setFuertes(this.r.getFuertes()-1);
       this.r.getGrafica().getFuertes().setText(Integer.toString(this.r.getFuertes()));
       }
    }
    
    public void ConsumirCables(){
        this.r.getMesonE().setVec(this.r.getConsumirE(), 0);
        this.r.setConsumirE((this.r.getConsumirE()+1)%this.r.getMesonE().getTam());
        this.r.getMesonE().setVec(this.r.getConsumirE(), 0);
        this.r.setConsumirE((this.r.getConsumirE()+1)%this.r.getMesonE().getTam());
        if(this.r.getEntradas()>0){
            this.r.setEntradas(this.r.getEntradas()-2);
            this.r.getGrafica().getEntradas().setText(Integer.toString(this.r.getEntradas()));
        }
        
    }
  
}
