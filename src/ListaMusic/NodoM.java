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
public class NodoM {
    
   public String nombre, direccion;
   public NodoM suguiente, anterior;
    
    public NodoM(String nombre, String direccion){
        this.nombre=nombre;
        this.direccion = direccion;
    
    }
}
