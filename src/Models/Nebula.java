/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.LinkedList;

/**
 *
 * @author Sebas
 */
public class Nebula {
    
    String name;
    LinkedList<PlanetarySystem> planetarySystems;
    int x;
    int y;
    String image;
    
    public Nebula()
    {
    }

    public Nebula(String name)
    {
        this.name = name;
        this.planetarySystems = new LinkedList<>();
        this.x = 0;//aleatoriamente  (int) (Math.random() * anchodelapantalla )+ 1
        this.y = 0;//aleatoriamente   (int) (Math.random() * altodelapantalla )+ 1
        this.image = "nombredelaimagen";
    }
}
