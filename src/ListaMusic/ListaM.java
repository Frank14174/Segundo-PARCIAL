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
public class ListaM {
    
    public NodoM primera;
    public NodoM ultima;
    public int tama;
    
    public ListaM(){
    primera = ultima = null;
    tama =0;
    }
    
    public boolean IsEmpety(){
    return primera == null;
    }
    
    public void clear(){
    while (!IsEmpety()){
        borrar(primera);
    }
    }
    
    public void insertar (String nom, String dir){
    NodoM nuevo = new NodoM(nom,dir);
    
    if(IsEmpety()){
        primera = nuevo;
        ultima = nuevo;
    
    }else{
        nuevo.anterior = ultima;
        ultima.suguiente = nuevo;
        ultima = nuevo;
    }
    tama++;
    }

    public int indice(NodoM b){
    NodoM aux = primera;
    int con =0;
    
        while(aux != null){
            if(aux==b){
                return con;
            }
            aux = aux.suguiente;
            con++;
        }
        return -1;
    }
    
    public NodoM ObtenerCa(int indice){
    if(indice < 0 || indice >= tama){
        return null;
    }
    int n =0;
    NodoM aux = primera;
    while(n != indice){
        aux = aux.suguiente;
        n++;
    }
            
    return aux;
    }
    
    public void borrar(NodoM b){
    if(b== primera){
        if(tama == 1){
            primera = null;
            tama --;
            return;
        }
        primera.suguiente.anterior = null;
        primera = primera.suguiente;
        tama --;
        return;
    }
    tama--;
    if(b== primera){
        primera.anterior.suguiente= null;
        primera = primera.anterior;
        return;
    }
    b.suguiente.anterior = b.anterior;
    b.suguiente.anterior.suguiente = b.suguiente;
    }
    
    public boolean buscar(String nombre, String ruta){
    NodoM aux = primera;
    
    while(aux != null){
        if(aux.nombre.equals(nombre) && aux.direccion.equals(ruta)){
        return true;
        }
        aux = aux.suguiente;
    }
    return false;
    }
}
