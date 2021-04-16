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
public class ListaCirM {
    
    private NodoCirM lc;
    
    
    public ListaCirM (){
        lc = null;
       
    }
    
    
    public ListaCirM insertar (String entrada){
        
        NodoCirM nuevo;
        nuevo= new NodoCirM(entrada);
        
        if(lc != null){
            nuevo.siguiente=lc.siguiente;
            lc.siguiente=nuevo;
        }else{
            nuevo.anterior=lc.anterior;
            lc.anterior=nuevo;
        }
        lc=nuevo;
        return this;
    }
    
    public void eliminar(String entrada){
        
        NodoCirM actual;
        
        actual = lc;
        
        while ((actual.siguiente != lc) && (actual.anterior != lc) && !(actual.siguiente.nombre.equals(entrada) && !(actual.anterior.nombre.equals(entrada)))) {

            if (!actual.siguiente.nombre.equals(entrada)) {

                actual = actual.siguiente;
            } else if (!actual.anterior.nombre.equals(entrada)) {
                actual = actual.anterior;
            }
        }
        
        if(actual.siguiente.nombre.equals(entrada)){
          
            NodoCirM p;
            p=actual.anterior;
            
            if(lc==lc.siguiente){
                lc=null;
            }else {
                if(p==lc){
                    lc=actual;
                }
                actual.siguiente=p.siguiente;
            }
            p=null;
               
        }
        
        
    }
    
    public void desplegarListaCir(){
    
    NodoCirM actual;
    actual=lc;
    do{
        System.out.println(actual.nombre);
        actual=actual.siguiente;
    }while(actual != lc);
    
    }
    
    
    public void borrarlista(){
    
        NodoCirM p;
        
        if(lc !=null){
            
            p=lc;
            do{
                NodoCirM t;
                t=p;
                p=p.siguiente;
                p=p.anterior;
                t=null;
            }while(p != lc);
        }else{
            System.out.println("Lista Vacia");
        }
    }
}