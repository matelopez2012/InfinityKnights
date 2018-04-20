/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Sebas
 */
public class Planet {
    String name;
    int paladium; //valor random de 0 a 30000
    int iridium; //valor random de 0 a 30000
    int platinium; //valor random de 0 a 30000
    int elementZero; //valor random de 0 a 30000
    int x;
    int y;
    String image;
    
    public Planet()
    {
    }

    public Planet(String name, int x, int y, String image)
    {
        this.name = name;
        this.paladium = (int) (Math.random() * 30000) + 1;
        this.iridium = (int) (Math.random() * 30000) + 1;
        this.platinium = (int) (Math.random() * 30000) + 1;
        this.elementZero = (int) (Math.random() * 30000) + 1;
        this.x = x;
        this.y = y;
        this.image = image;
    }
}
