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
public class PlanetarySystem {
    String name;
    LinkedList<Planet> planets;
    String image;
    int x;
    int y;
    
public PlanetarySystem()
    {
    }

    public PlanetarySystem(String name)
    {
        this.name = name;
        this.planets = new LinkedList<>();
        this.image = "nombredelaimagen";
        this.x = 0;//aleatoriamente  (int) (Math.random() * anchodelapantalla )+ 1
        this.y = 0;//aleatoriamente   (int) (Math.random() * altodelapantalla )+ 1
    }    
    
}
