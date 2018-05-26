package Models;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Grafo<M> {

    private LinkedList<Vertice<M>> listaVertices;
    private LinkedList<Arista<M>> listaAristas;

    public Grafo() {
        this.listaVertices = new LinkedList<>();
        this.listaAristas = new LinkedList<>();
        System.out.println("se creo el grafo");
    }

    public Grafo(LinkedList<Vertice<M>> listaVertices, LinkedList<Arista<M>> listaAristas) {
        this.listaVertices = listaVertices;
        this.listaAristas = listaAristas;
    }

    public boolean añadirVertice(M element, String nombre) {
        Vertice<M> nuevo = new Vertice<>(element, nombre);
        if (listaVertices.contains(nuevo)) {
            return false;
        }
        this.listaVertices.add(nuevo);
        return true;
    }

    public boolean añadirArista(String desde, String hasta, int peso) {
        Vertice<M> Vdesde = buscarVertice(desde);
        Vertice<M> Vhasta = buscarVertice(hasta);
        if (!(this.listaVertices.contains(Vdesde) || this.listaVertices.contains(Vhasta))) {
            return false;
        }
        Arista<M> nueva = new Arista<>(Vdesde, Vhasta, peso);
        Vdesde.añadirAdyacente(Vhasta);
        if (!existeArista(nueva)) {
            this.listaAristas.add(nueva);
        }
        nueva = new Arista<>(Vhasta, Vdesde, peso);
        Vhasta.añadirAdyacente(Vdesde);
        if (!existeArista(nueva))
            this.listaAristas.add(nueva);
        return true;
    }

    private boolean existeArista(Arista<M> aux) {
        for (Arista<M> arista : listaAristas) {
            if (arista.getInicio().getNombre().equals(aux.getInicio().getNombre())
                    && arista.getHasta().getNombre().equals(aux.getHasta().getNombre())) {
                return true;
            }
        }
        return false;
    }

    public Vertice buscarVertice(String nombre) {
        for (Vertice<M> v : listaVertices) {
            if (v.getNombre().equals(nombre)) {
                return v;
            }

        }
        return null;
    }

    public Arista buscarArista(String desde, String hasta) {
        for (Arista<M> a : this.listaAristas) {
            if ((a.getInicio().getNombre().equals(desde)) && (a.getHasta().getNombre().equals(hasta))) {
                return a;
            }
        }
        return null;
    }

    public void recorridoAnchura(String inicio) {
        LinkedList<String> recorrido = new LinkedList<>();
        Queue<String> q = new LinkedList<>();
        q.add(inicio);
        while (!q.isEmpty()) {
            String aux = q.remove();
            Vertice<M> verAux = buscarVertice(aux);
            recorrido.add(aux);
            for (Vertice<M> ady : verAux.getAdyacentes()) {
                if (!recorrido.contains(ady.getNombre()) && !q.contains(ady.getNombre())) {
                    q.add(ady.getNombre());
                } else {
                    System.out.println("Ciclo " + recorrido);
                }
            }
        }

        System.out.println("Recorrido");

    }

    public void recorridoProfundidad(String inicio) {
        LinkedList<String> recorrido = new LinkedList<>();
        Stack<String> p = new Stack<>();
        p.push(inicio);
        while (!p.isEmpty()) {
            String aux = p.pop();
            Vertice<M> verAux = buscarVertice(aux);
            if (!recorrido.contains(aux)) {
                recorrido.add(aux);
            } else {
                System.out.println("Ciclo" + recorrido);
            }

            boolean condicion = true;
            for (Vertice<M> ady : verAux.getAdyacentes()) {
                if (condicion) {
                    if (!recorrido.contains(ady.getNombre())) {
                        p.push(aux);
                        p.push(ady.getNombre());
                        condicion = false;
                    }
                }

            }
        }
        System.out.println("Recorrido " + recorrido);
    }

    public void ciclos() {
        LinkedList<LinkedList<String>> listaCiclos = new LinkedList<>();
        for (Vertice<M> v : this.listaVertices) {
            ciclos(v.getNombre(), v.getNombre(), new LinkedList<>(), listaCiclos);
        }
        for (LinkedList<String> c : listaCiclos) {
            System.out.println("Ciclo->" + c);
        }
    }

    public void ciclos(String comienzo, String desde, LinkedList<String> camino, LinkedList<LinkedList<String>> ciclos) {
        Vertice<M> first = buscarVertice(comienzo);
        Vertice<M> verticeDesde = buscarVertice(desde);
        camino.add(desde);
        if (verticeDesde.getAdyacentes().contains(first)) {
            ciclos.add(camino);
        } else {
            for (Vertice<M> ady : verticeDesde.getAdyacentes()) {
                if (!camino.contains(ady.getNombre())) {
                    LinkedList<String> nuevoCamino = new LinkedList<>(camino);
                    ciclos(comienzo, ady.getNombre(), nuevoCamino, ciclos);
                }
            }
        }
    }

    public void ciclos2(String desde) {
        LinkedList<LinkedList<String>> ciclos = new LinkedList<>();
        LinkedList<String> nuevo = new LinkedList<>();
        LinkedList<String> marcados = new LinkedList<>();

        nuevo.add(desde);
        Stack<String> pila = new Stack<>();
        pila.add(desde);

        while (!pila.isEmpty()) {
            String aux = pila.pop();
            Vertice<M> verAux = buscarVertice(aux);
            boolean condicion = false;

            if (!marcados.contains(aux)) {
                nuevo.add(aux);
                marcados.add(aux);
                condicion = true;
            } else {
                marcados.remove(aux);
            }
            for (Vertice<M> ady : verAux.getAdyacentes()) {
                if (condicion) {
                    if (!nuevo.contains(ady.getNombre())) {
                        pila.push(aux);
                        pila.push(ady.getNombre());
                        condicion = false;
                    } else {
                        ciclos.add(nuevo);
                        //nuevo.clear();
                        condicion = false;
                    }
                }
            }
        }
        for (LinkedList<String> c : ciclos) {
            System.out.println("Ciclo->" + c);
        }

    }

    public void todosCiclos(String desde, LinkedList<String> ciclos) {
        Vertice<M> verticeDesde = buscarVertice(desde);

        ciclos.add(desde);
        for (Vertice<M> ady : verticeDesde.getAdyacentes()) {
            if (!ciclos.contains(ady.getNombre())) {
                LinkedList<String> otros = new LinkedList<>(ciclos);
                todosCiclos(ady.getNombre(), otros);
            } else if (ady.getNombre().equals(ciclos.get(0))) //ciclos.add(ady.getNombre());
            {
                System.out.println("Ciclo->" + ciclos);
            }

        }
    }

    public LinkedList<String> menorCamino(String a, String b, LinkedList<String> marked) {
        Vertice<M> desde = buscarVertice(a);
        Vertice<M> hasta = buscarVertice(b);
        LinkedList<String> camino = new LinkedList<>();
        camino.add(a);
        marked.add(a);
        if (desde.getAdyacentes().contains(hasta)) {
            camino.add(b);
            System.out.println("Camino" + camino);
            return camino;
        } else {
            for (Vertice<M> ady : desde.getAdyacentes()) {
                if (!marked.contains(ady.getNombre())) {
                    camino = (menorCamino(ady.getNombre(), b, marked));
                    System.out.println("Camino" + camino);
                }
            }
        }

        return camino;
    }

    public void caminosEntre(String desde, String hasta) {
        Vertice<M> verticeDesde = buscarVertice(desde);
        Vertice<M> verticeHasta = buscarVertice(hasta);

        LinkedList<LinkedList<Vertice<M>>> caminos = new LinkedList<>();

        for (Vertice<M> v : verticeDesde.getAdyacentes()) {
            LinkedList<Vertice<M>> lista = new LinkedList<>();
            lista.add(verticeDesde);
            Queue<Vertice<M>> cola = new LinkedList<>();
            cola.addAll(v.getAdyacentes());

            caminos = (camino(v, verticeHasta, cola, lista));

        }
        for (LinkedList<Vertice<M>> lista : caminos) {
            if (lista != null) {
                System.out.println("Camino : ");
                for (Vertice<M> v : lista) {
                    System.out.print(v.getNombre() + " - ");
                }
                System.out.println("");
            }
            System.out.println("No hay caminos");
        }

    }

    public LinkedList<LinkedList<Vertice<M>>> camino(Vertice<M> actual, Vertice<M> hasta, Queue<Vertice<M>> cola, LinkedList<Vertice<M>> lista) {
        lista.add(actual);
        LinkedList<LinkedList<Vertice<M>>> mas = new LinkedList<>();
        //Codiciones de escape
        if (actual.getNombre().equals(hasta.getNombre())) {
            return mas;
        }

        while (!cola.isEmpty()) {
            LinkedList<Vertice<M>> nueva = new LinkedList<>(lista);
            actual = cola.poll();
            Queue<Vertice<M>> colaNueva = new LinkedList<>(actual.getAdyacentes());
            camino(actual, hasta, colaNueva, nueva);
        }
        for (LinkedList<Vertice<M>> lista2 : mas) {
            if (lista2 != null) {
                for (Vertice<M> v : lista2) {
                    if (!lista.contains(v)) {
                        lista.add(v);
                    }
                }
            }

        }
        return mas;
    }

    public LinkedList<Vertice<M>> getListaVertices() {
        return listaVertices;
    }

    public void setListaVertices(LinkedList<Vertice<M>> listaVertices) {
        this.listaVertices = listaVertices;
    }

    public LinkedList<Arista<M>> getListaAristas() {
        return listaAristas;
    }

    public void setListaAristas(LinkedList<Arista<M>> listaAristas) {
        this.listaAristas = listaAristas;
    }

    public void menorCamino(String desde, String hasta) {
        int[] valores = new int[this.listaVertices.size()];
        String[] ponderados = new String[this.listaVertices.size()];
        dijsktra(desde, valores, ponderados);
        
        Stack<String> camino = new Stack<>();
        camino.push(hasta);
        while (!hasta.equals(desde)) {
            int posicionHasta = this.listaVertices.indexOf(buscarVertice(hasta));
            camino.push(ponderados[posicionHasta]);
            hasta = ponderados[posicionHasta];
        }
        System.out.println(camino);

    }

    /**
     *
     * @param vertice el string del vertice (nombre) desde es el objeto del
     * vertice
     */
    public void dijsktra(String vertice, int[] valores, String[] ponderados) {
        Vertice<M> desde = buscarVertice(vertice);
        boolean[] marcados = new boolean[this.listaVertices.size()];
        //Iniciar el arreglo de valores en infinito
        Arrays.fill(valores, Integer.MAX_VALUE);
        Arrays.fill(marcados, false);

        //Iniciar el valor del vertice en cero (0)
        valores[this.listaVertices.indexOf(desde)] = 0;
        ponderados[this.listaVertices.indexOf(desde)] = vertice;

        System.out.println(Arrays.toString(valores));
        System.out.println(Arrays.toString(ponderados));
        System.out.println("\n");
        disktra(valores, ponderados, marcados);
    }

    private void disktra(int[] valores, String[] ponderados, boolean[] marcados) {
        if (!todosEstanMarcados(marcados)) {
            int posicionMenor = menorNoMarcado(valores, marcados);
            if (posicionMenor != -1) {
                for (Vertice<M> ady : this.listaVertices.get(posicionMenor).getAdyacentes()) {
                    Arista<M> arista = buscarArista(this.listaVertices.get(posicionMenor).getNombre(), ady.getNombre());
                    int acumulado = valores[posicionMenor];
                    int posicionAdyacente = this.listaVertices.indexOf(ady);
                    if (!marcados[posicionAdyacente]) {
                        if (acumulado + arista.getPeso() < valores[posicionAdyacente]) {
                            valores[posicionAdyacente] = acumulado + arista.getPeso();
                            ponderados[posicionAdyacente] = this.listaVertices.get(posicionMenor).getNombre();
                        }
                    }
                }
                System.out.println(Arrays.toString(valores));
                System.out.println(Arrays.toString(ponderados));
                System.out.println("\n");
                marcados[posicionMenor] = true;
                disktra(valores, ponderados, marcados);
            }
        }
    }

    private int menorNoMarcado(int[] valores, boolean[] marcados) {
        int posicionMenor = 0;
        int menor = Integer.MAX_VALUE;
        for (int i = 0; i < valores.length; i++) {
            if (!marcados[i] && valores[i] < menor) {
                menor = valores[i];
                posicionMenor = i;
            }
        }
        return menor == Integer.MAX_VALUE ? -1 : posicionMenor;
    }

    private boolean todosEstanMarcados(boolean[] marcados) {
        for (boolean b : marcados) {
            if (b == false) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String salida = "";
        for (Vertice<M> v : listaVertices) {
            salida += v.getNombre() + " -> ";

            for (Vertice<M> a : v.getAdyacentes()) {
                salida += a.getNombre() + " - " + buscarArista(v.getNombre(), a.getNombre()).getPeso() + ", ";
            }
            salida += "\n";
        }
        return salida;
    }

}
