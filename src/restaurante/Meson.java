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
public class Meson {
   private int[] Meson;
   private int tam;
    
    public Meson(int tam){
        this.tam=tam;
        Meson = new int[tam];
    }

    public int getTam() {
        return tam;
    }

    public int getVec(int i,int val) {
        return Meson[i]=val;
    }

    public void setVec(int i, int val) {
        this.Meson[i] = val;
    }
    
    public void imprimir(){
        for(int i=0; i<tam; i++){
            System.out.print(Meson[i]+" ");
        }
        System.out.println("");
    } 
}
