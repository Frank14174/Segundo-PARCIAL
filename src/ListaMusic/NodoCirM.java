/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListaMusic;

/**
 *
 * @author Franklin
 */
public class NodoCirM {
    
    String nombre;
    NodoCirM siguiente,anterior;
    
    public NodoCirM(String entrada){
        nombre=entrada;
        siguiente=anterior=this;
    
    }
    
}
