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
import java.util.concurrent.Semaphore;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class Restaurant {
   // clase logica para la funcionalidad del programa 
    
   private Cocinero cocineroE[]; 
   private Cocinero cocineroF[]; 
   private Cocinero cocineroP[];
   private int tiempo;
   private int Mesonr=0;
   
   private int MaxE;
   private int MaxF;
   private int MaxP;
   
   private int MaxCE;
   private int MaxCF;
   private int MaxCP;
   
   private int InicialE;
   private int InicialF;
   private int InicialP;
   
   private int InicialM;
   private int MaxM;
   
   private int produccionE=0;
   private int produccionF=0;
   private int produccionP=0;
   private int Id;
   private int consumirE=0;
   private int consumirF=0;
   private int consumirP=0;
   private Mesonero mesonero[];
   private Meson mesonMesonero;
   private Restaurant R;
   private Grafica grafica;
   private JefeMesoneros j;
   private Gerente g;
   
   private Meson MesonE;
   private Meson MesonF;
   private Meson MesonP;
   
   private Semaphore SPE;
   private Semaphore SPEE;
   private Semaphore SCE;
   
   private Semaphore SPF;
   private Semaphore SPEF;
   private Semaphore SCF;
   
   private Semaphore SPP;
   private Semaphore SPEP;
   private Semaphore SCP;
   
   
   private Semaphore SEM;
   private Semaphore SA;
   
   
   private int Entradas=0;
   private int Fuertes=0;
   private int Postres=0;
   private int cont=this.tiempo;
   

    public Restaurant() {
        this.R=this;
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

 
   // metodo para leer todos los datos iniciales del archivo txt
   public void LeerTxt() throws FileNotFoundException{
       
    Scanner s = new Scanner(new File("C:\\Users\\ASUS\\Documents\\NetBeansProjects\\Restaurante\\src\\restaurante\\Data.txt"));
    String line=s.nextLine();
    
    this.tiempo= parseInt(line.substring(30,35).trim());
    System.out.println(this.tiempo);
    line=s.nextLine();
    if(this.tiempo<0){
       JOptionPane.showMessageDialog(null, "El campo introducido es invalido");  
    }
      
    
    this.MaxE= parseInt(line.substring(30,35).trim());
    System.out.println(this.MaxE);
    line=s.nextLine();
        if(this.MaxE<0){
             JOptionPane.showMessageDialog(null, "El campo introducido es invalido");
        }
    
    this.MaxF= parseInt(line.substring(30,35).trim());
    System.out.println(this.MaxF);
    line=s.nextLine();
        if(this.MaxF<0){
             JOptionPane.showMessageDialog(null, "El campo introducido es invalido");
        }
    
    this.MaxP= parseInt(line.substring(30,35).trim());
    System.out.println(this.MaxP);
    line=s.nextLine();
        if(this.MaxCP<0){
             JOptionPane.showMessageDialog(null, "El campo introducido es invalido");
        }
    
    this.InicialE= parseInt(line.substring(30,35).trim());
    System.out.println(this.InicialE);
    line=s.nextLine();
        if(this.InicialE<0){
             JOptionPane.showMessageDialog(null, "El campo introducido es invalido");
        }
    
    this.InicialF= parseInt(line.substring(30,35).trim());
    System.out.println(this.InicialF);
    line=s.nextLine();
        if(this.InicialF<0){
             JOptionPane.showMessageDialog(null, "El campo introducido es invalido");
        }
    
    this.InicialP= parseInt(line.substring(30,35).trim());
    System.out.println(this.InicialP);
    line=s.nextLine();
        if(this.InicialP<0){
             JOptionPane.showMessageDialog(null, "El campo introducido es invalido");
        }
    
   this.MaxCE= parseInt(line.substring(30,35).trim());
    System.out.println(this.MaxCE);
    line=s.nextLine();
        if(this.MaxCE<0){
             JOptionPane.showMessageDialog(null, "El campo introducido es invalido");
        }
    
    this.MaxCF= parseInt(line.substring(30,35).trim());
    System.out.println(this.MaxCF);
    line=s.nextLine();
        if(this.MaxCF<0){
             JOptionPane.showMessageDialog(null, "El campo introducido es invalido");
        }
    this.MaxCP= parseInt(line.substring(30,35).trim());
    System.out.println(this.MaxCP);
    line=s.nextLine();
        if(this.MaxCP<0){
             JOptionPane.showMessageDialog(null, "El campo introducido es invalido");
        }
    
     this.InicialM= parseInt(line.substring(30,35).trim());
    System.out.println(this.InicialM);
    line=s.nextLine();
        if(this.InicialM<0){
             JOptionPane.showMessageDialog(null, "El campo introducido es invalido");
        }
    
     this.MaxE= parseInt(line.substring(30,35).trim());
    System.out.println(this.MaxM);
    line=s.nextLine();
        if(this.MaxM<0){
             JOptionPane.showMessageDialog(null, "El campo introducido es invalido");
        }

   }
   
   public void GenerarMesones(){
      this.MesonE = new Meson(MaxE);
       this.MesonF = new Meson(MaxF);
       this.MesonP =  new Meson(MaxP);
     }
   
    // metodo para crear el jefe
    public void CrearJefe(){
        this.j = new JefeMesoneros(this.R,this.SEM,this.tiempo);
        j.start();
    }
    
    //metodo para crear el gerente del restaurante
    public void CrearGerente(){
        this.g = new Gerente(this.R,this.SA,this.SEM,this.Mesonr);
        g.start();
    }
  
   //metodo para asignar el tamano a los vectores de mesoneros y cocineros
   public void CrearArreglos(){
       this.cocineroE = new Cocinero [this.MaxCE];
       this.cocineroF = new Cocinero [this.MaxCF];
       this.cocineroP = new Cocinero[this.MaxCP];
       this.mesonero = new Mesonero[this.MaxM];
   }
   
    //meotodo para crear los semaforos correspondientes de cada clase  
   public void Semaforos(){
       
       this.SPE= new Semaphore(this.MesonE.getTam());
       this.SPEE =new Semaphore(1);
       this.SCE = new Semaphore(0);
       
       this.SPF = new Semaphore(this.MesonF.getTam());
       this.SPEF = new Semaphore(1);
       this.SCF = new Semaphore(0);
       
       this.SPP = new Semaphore(this.MesonP.getTam());
       this.SPEP = new Semaphore(1);
       this.SCP = new Semaphore(0);
       
       this.SEM = new Semaphore(1);
       this.SA = new Semaphore(1);
   
   }
   
   
   //metodo para crear cada uno de los cocineros en una posicion especifica del arreglo
   public void CrearCocineros(){
      
       for (int i = 0; i < this.InicialE; i++) {
         this.cocineroE[i]=new Cocinero(this.SPE, this.SPEE,this.SCE, this.MesonE,this.produccionE,1,this.R,2);
 
       }
       for (int i = 0; i < this.InicialF; i++) {
         this.cocineroF[i]=new Cocinero(this.SPF, this.SPEF, this.SCF, this.MesonF, this.produccionF,2,this.R,1);
           
       }
       for (int i = 0; i < this.InicialP; i++) {
           this.cocineroP[i]=new Cocinero(this.SPP, this.SPEP, this.SCP, this.MesonP, this.produccionP,3, this.R,1);
           
           
       }
   }
   
 
   //metodo para crear cada uno de los mesoneros en una posicion especifica del arreglo
   public void CrearMesoneros(){

        for (int i =0; i <this.InicialM; i++) {
            this.mesonero[i]=new Mesonero(this.SCE,this.SPEE,this.SPE,this.SCF,this.SPEF,this.SPF,this.SCP,this.SPEP,this.SPP,2,this.consumirE,this.consumirF,this.consumirP,this.R,0,this.SA);
       }   
   }
   
     
       
   //metodo para enviarle la infomacion generada del restaurant a los cocineros
   public void RestaurantCocineros(Restaurant r){
       for (int i = 0; i <this.MaxCE; i++) {
           if(this.cocineroE[i]!=null){
               this.cocineroE[i].setR(r);
           }
           
       }
         for (int i = 0; i <this.MaxCF; i++) {
           if(this.cocineroF[i]!=null){
               this.cocineroF[i].setR(r);
           }
           
       }
           for (int i = 0; i <this.MaxCP; i++) {
           if(this.cocineroP[i]!=null){
               this.cocineroP[i].setR(r);
           }
           
       }
       
   }
   
   //metodo para enviarle la logica de restaurant a los mesoneros
   public void RestaurantMesoneros(Restaurant r){
           for (int i = 0; i < this.MaxM; i++) {
           if(this.mesonero[i]!=null){
               this.mesonero[i].setR(r);
           }
           
       }
       
   }
   
  
   // metodo para iniciar los Thread de cocineros, mesoneros, jefe, gerente y puedan comenzar su ejecuccion
   public void iniciar(){
       for (int i = 0; i < this.cocineroE.length; i++) {
           if(this.cocineroE[i]!=null){
              this.cocineroE[i].start();
        
           }
           
       }
       for (int i = 0; i <this.cocineroF.length; i++) {
           if(this.cocineroF[i]!=null){
            this.cocineroF[i].start();
               
           }
           
       }
       for (int i = 0; i <this.cocineroP.length; i++) {
           if(this.cocineroP[i]!=null){
            this.cocineroP[i].start();
               
           }
           
       }
       
       for (int i = 0; i < this.mesonero.length; i++) {
           if(this.mesonero[i]!=null){
               this.mesonero[i].start();
               
           }
           
       }
       
  
       this.R.grafica.getCantidadE().setText(Integer.toString(this.R.getInicialE()));
       this.R.grafica.getCantidadF().setText(Integer.toString(this.R.getInicialF()));
       this.R.grafica.getCantidadP().setText(Integer.toString(this.R.getInicialP()));
       this.R.grafica.getCantidadM().setText(Integer.toString(this.R.getInicialM()));
       this.CrearJefe();
       this.CrearGerente();
      
       this.R.getGrafica().getCerradas().setText(Integer.toString(this.R.getTiempo())); 
   }

    public Cocinero[] getCocineroE() {
        return cocineroE;
    }

    public void setCocineroE(Cocinero[] cocineroE) {
        this.cocineroE = cocineroE;
    }

    public Cocinero[] getCocineroF() {
        return cocineroF;
    }

    public void setCocineroF(Cocinero[] cocineroF) {
        this.cocineroF = cocineroF;
    }

    public Cocinero[] getCocineroP() {
        return cocineroP;
    }

    public void setCocineroP(Cocinero[] cocineroP) {
        this.cocineroP = cocineroP;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public int getMesonr() {
        return Mesonr;
    }

    public void setMesonr(int Mesonr) {
        this.Mesonr = Mesonr;
    }

    public int getMaxE() {
        return MaxE;
    }

    public void setMaxE(int MaxE) {
        this.MaxE = MaxE;
    }

    public int getMaxF() {
        return MaxF;
    }

    public void setMaxF(int MaxF) {
        this.MaxF = MaxF;
    }

    public int getMaxP() {
        return MaxP;
    }

    public void setMaxP(int MaxP) {
        this.MaxP = MaxP;
    }

    public int getMaxCE() {
        return MaxCE;
    }

    public void setMaxCE(int MaxCE) {
        this.MaxCE = MaxCE;
    }

    public int getMaxCF() {
        return MaxCF;
    }

    public void setMaxCF(int MaxCF) {
        this.MaxCF = MaxCF;
    }

    public int getMaxCP() {
        return MaxCP;
    }

    public void setMaxCP(int MaxCP) {
        this.MaxCP = MaxCP;
    }

    public int getInicialE() {
        return InicialE;
    }

    public void setInicialE(int InicialE) {
        this.InicialE = InicialE;
    }

    public int getInicialF() {
        return InicialF;
    }

    public void setInicialF(int InicialF) {
        this.InicialF = InicialF;
    }

    public int getInicialP() {
        return InicialP;
    }

    public void setInicialP(int InicialP) {
        this.InicialP = InicialP;
    }

    public int getInicialM() {
        return InicialM;
    }

    public void setInicialM(int InicialM) {
        this.InicialM = InicialM;
    }

    public int getMaxM() {
        return MaxM;
    }

    public void setMaxM(int MaxM) {
        this.MaxM = MaxM;
    }

    public int getProduccionE() {
        return produccionE;
    }

    public void setProduccionE(int produccionE) {
        this.produccionE = produccionE;
    }

    public int getProduccionF() {
        return produccionF;
    }

    public void setProduccionF(int produccionF) {
        this.produccionF = produccionF;
    }

    public int getProduccionP() {
        return produccionP;
    }

    public void setProduccionP(int produccionP) {
        this.produccionP = produccionP;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
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

    public int getConsumirP() {
        return consumirP;
    }

    public void setConsumirP(int consumirP) {
        this.consumirP = consumirP;
    }

    public Mesonero[] getMesonero() {
        return mesonero;
    }

    public void setMesonero(Mesonero[] mesonero) {
        this.mesonero = mesonero;
    }

    public Meson getMesonMesonero() {
        return mesonMesonero;
    }

    public void setMesonMesonero(Meson mesonMesonero) {
        this.mesonMesonero = mesonMesonero;
    }

    public Restaurant getR() {
        return R;
    }

    public void setR(Restaurant R) {
        this.R = R;
    }

    public Grafica getGrafica() {
        return grafica;
    }

    public void setGrafica(Grafica grafica) {
        this.grafica = grafica;
    }

    public JefeMesoneros getJ() {
        return j;
    }

    public void setJ(JefeMesoneros j) {
        this.j = j;
    }

    public Gerente getG() {
        return g;
    }

    public void setG(Gerente g) {
        this.g = g;
    }

    public Meson getMesonE() {
        return MesonE;
    }

    public void setMesonE(Meson MesonE) {
        this.MesonE = MesonE;
    }

    public Meson getMesonF() {
        return MesonF;
    }

    public void setMesonF(Meson MesonF) {
        this.MesonF = MesonF;
    }

    public Meson getMesonP() {
        return MesonP;
    }

    public void setMesonP(Meson MesonP) {
        this.MesonP = MesonP;
    }

    public Semaphore getSPE() {
        return SPE;
    }

    public void setSPE(Semaphore SPE) {
        this.SPE = SPE;
    }

    public Semaphore getSPEE() {
        return SPEE;
    }

    public void setSPEE(Semaphore SPEE) {
        this.SPEE = SPEE;
    }

    public Semaphore getSCE() {
        return SCE;
    }

    public void setSCE(Semaphore SCE) {
        this.SCE = SCE;
    }

    public Semaphore getSPF() {
        return SPF;
    }

    public void setSPF(Semaphore SPF) {
        this.SPF = SPF;
    }

    public Semaphore getSPEF() {
        return SPEF;
    }

    public void setSPEF(Semaphore SPEF) {
        this.SPEF = SPEF;
    }

    public Semaphore getSCF() {
        return SCF;
    }

    public void setSCF(Semaphore SCF) {
        this.SCF = SCF;
    }

    public Semaphore getSPP() {
        return SPP;
    }

    public void setSPP(Semaphore SPP) {
        this.SPP = SPP;
    }

    public Semaphore getSPEP() {
        return SPEP;
    }

    public void setSPEP(Semaphore SPEP) {
        this.SPEP = SPEP;
    }

    public Semaphore getSCP() {
        return SCP;
    }

    public void setSCP(Semaphore SCP) {
        this.SCP = SCP;
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

    public int getEntradas() {
        return Entradas;
    }

    public void setEntradas(int Entradas) {
        this.Entradas = Entradas;
    }

    public int getFuertes() {
        return Fuertes;
    }

    public void setFuertes(int Fuertes) {
        this.Fuertes = Fuertes;
    }

    public int getPostres() {
        return Postres;
    }

    public void setPostres(int Postres) {
        this.Postres = Postres;
    }

}
