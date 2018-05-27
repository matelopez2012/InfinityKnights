
package Models;

import java.util.LinkedList;


public class Vertice <M>{
    
    private String nombre;
    private LinkedList<Vertice<M>> adyacentes;
    private M element;
    public Vertice() {
    }

    public Vertice(M element, String nombre) {
        this.nombre = nombre;
        this.adyacentes = new LinkedList<>();
        this.element = element;
        System.out.println("se añadio el vertice");
    }
    
    public boolean añadirAdyacente(Vertice<M> nuevo){
        return this.adyacentes.add(nuevo);        
    }

    public LinkedList<Vertice<M>> getAdyacentes() {
        return adyacentes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
    
    
    
}
