/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

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
public class Planet {
    private String name;
    private int paladium; //valor random de 0 a 30000
    private int iridium; //valor random de 0 a 30000
    private int platinium; //valor random de 0 a 30000
    private int elementZero; //valor random de 0 a 30000
    private GridPane view;
    
    public Planet()
    {
    }

    public Planet(String name)
    {
        this.name = name;
        this.paladium = (int) (Math.random() * 30000) + 1;
        this.iridium = (int) (Math.random() * 30000) + 1;
        this.platinium = (int) (Math.random() * 30000) + 1;
        this.elementZero = (int) (Math.random() * 30000) + 1;
    }

    public Planet(String name, double x, double y){
        ImageView imageView = new ImageView("Images/sonda.png");
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
     * @return the paladium
     */
    public int getPaladium()
    {
        return paladium;
    }

    /**
     * @param paladium the paladium to set
     */
    public void setPaladium(int paladium)
    {
        this.paladium = paladium;
    }

    /**
     * @return the iridium
     */
    public int getIridium()
    {
        return iridium;
    }

    /**
     * @param iridium the iridium to set
     */
    public void setIridium(int iridium)
    {
        this.iridium = iridium;
    }

    /**
     * @return the platinium
     */
    public int getPlatinium()
    {
        return platinium;
    }

    /**
     * @param platinium the platinium to set
     */
    public void setPlatinium(int platinium)
    {
        this.platinium = platinium;
    }

    /**
     * @return the elementZero
     */
    public int getElementZero()
    {
        return elementZero;
    }

    /**
     * @param elementZero the elementZero to set
     */
    public void setElementZero(int elementZero)
    {
        this.elementZero = elementZero;
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
    
    
}
