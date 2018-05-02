/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.LinkedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author Sebas
 */
public class PlanetarySystem {
    private GridPane view;
    private String name;
    private LinkedList<Planet> planets;
    
public PlanetarySystem()
    {
    }

    public PlanetarySystem(String name)
    {
        this.view = null;
        this.name = name;
        this.planets = new LinkedList<>();
    }
    
    public PlanetarySystem(String name, double x, double y){
        ImageView imageView = new ImageView("Images/nebulaIcon2.png");
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            imageView.setId(name);

            Label label = new Label(name);
            label.setFont(Font.font("Cambria", 30));
            label.setTextFill(Color.WHITE);

            GridPane grid = new GridPane();
            grid.setPadding(new Insets(10, 10, 10, 10));//t, r, b, l
            grid.setVgap(8);
            grid.setHgap(5);
            grid.setAlignment(Pos.CENTER);
            grid.add(label, 0, 0);//hijo, columna, fila
            grid.add(imageView, 0, 1);
            grid.setGridLinesVisible(true);//Muestra las lineas del GridPane
            grid.setLayoutX(x);
            grid.setLayoutY(y);
    }

    public void add(Planet newPlanet){
        this.planets.add(newPlanet);
    }
    /**
     * @return the view
     */
    public GridPane getView()
    {
        return view;
    }

    /**
     * @param view the view to set
     */
    public void setView(GridPane view)
    {
        this.view = view;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the planets
     */
    public LinkedList<Planet> getPlanets()
    {
        return planets;
    }

    /**
     * @param planets the planets to set
     */
    public void setPlanets(LinkedList<Planet> planets)
    {
        this.planets = planets;
    }

    

    
    
}
