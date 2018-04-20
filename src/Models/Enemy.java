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
public class Enemy {

    String name;
    String type;
    String threatLevel;//Nivel de amenaza
    int hurt;
    int life;//de 0 - 100, un progreso de vida en cada uno

    public Enemy() {
    }

    public Enemy(String name, String type, String threatLevel, int hurt, int life) {
        this.name = name;
        this.type = type;
        this.threatLevel = threatLevel;
        this.hurt =  hurt;
        this.life = life;
    }
}
