
package Models;


public class Arista<M> {
    
    private Vertice<M> inicio;
    private Vertice<M> hasta;
    private int peso;

    public Arista() {
    }

    public Arista(Vertice<M> inicio, Vertice<M> hasta, int peso) {
        this.inicio = inicio;
        this.hasta = hasta;
        this.peso = peso;
    }

    public Vertice<M> getInicio() {
        return inicio;
    }

    public void setInicio(Vertice<M> inicio) {
        this.inicio = inicio;
    }

    public Vertice<M> getHasta() {
        return hasta;
    }

    public void setHasta(Vertice<M> hasta) {
        this.hasta = hasta;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
    
    
}
