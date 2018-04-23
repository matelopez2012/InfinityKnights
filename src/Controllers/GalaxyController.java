/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Nebula;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Sebas
 */
public class GalaxyController implements Initializable
{

    boolean activeNewNebula;
    boolean activeNewPlanetarySystem;
    boolean activeNewPlanet;
    boolean createNebula;
    String nameNebula;
    LinkedList<Nebula> nebulas;

    @FXML
    private BorderPane principalPane;
    @FXML
    private ImageView blueShip;
    private ImageView sonda;
    private ProgressBar energyBar;
    private ProgressIndicator indicator;
    private JFXHamburger hamburguer;
    @FXML
    private JFXDrawer drawer;

    public static BorderPane rootP;
    private Sphere planet;
    @FXML
    private ImageView btnNewNebula;
    @FXML
    private ImageView btnNewPlanetarySystem;
    @FXML
    private Label lblNewPlanetarySystem;
    @FXML
    private ImageView btnNewPlanet;
    @FXML
    private Label lblNewPlanet;
    @FXML
    private ImageView btnSuccesful;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        rootP = principalPane;
        //principalPane.getScene().getRoot().getChildrenUnmodifiable().
        this.activeNewNebula = false;
        this.activeNewPlanetarySystem = false;
        this.activeNewPlanet = false;
        this.createNebula = false;
        this.nameNebula = "";
        //this.btnSuccesful.setLayoutX(this.principalPane.getWidth() - 20);
        //this.btnSuccesful.setLayoutY(this.principalPane.getHeight()- 20);
        this.nebulas = new LinkedList<>();
        //this.btnPlusNebula.setOpacity(0.5);
        /*
        String path = "src/Views/SidePanelContent.fxml";

        try
        {
            URL urlFile = checkFile(path);
            System.out.println(urlFile);
            System.out.println(urlFile);
            VBox box = FXMLLoader.load(urlFile);
            System.out.println(box);
            drawer.setSidePane(box);
        }
        catch (MalformedURLException ex)
        {
            System.err.println("error");
        }
        catch (IOException ex)
        {
            System.err.println("error");
        }

        HamburgerSlideCloseTransition transition = new HamburgerSlideCloseTransition(hamburguer);
        transition.setRate(-1);
        hamburguer.addEventHandler(MouseEvent.MOUSE_PRESSED, (e)
                -> 
                {
                    transition.setRate(transition.getRate() * -1);
                    transition.play();

                    if (drawer.isShown())
                        drawer.close();
                    else
                        drawer.open();
        });
         */

        //No funciona el .show en la notificacion 
        //dateNotification.show();
    }

    /**
     * Transiciones para las naves
     *
     * @param event
     */
    @FXML
    private void action(MouseEvent event)
    {

//        TranslateTransition translateTransition = new TranslateTransition();
//        translateTransition.setNode(blueShip);
//        translateTransition.setDuration(Duration.seconds(3));
//        translateTransition.setCycleCount(TranslateTransition.INDEFINITE);
//        translateTransition.setAutoReverse(true);
//        translateTransition.setToX(600);
//        translateTransition.setToY(50);
//
//        RotateTransition sondaTransition = new RotateTransition();
//        sondaTransition.setNode(sonda);
//        sondaTransition.setByAngle(0);
//        sondaTransition.setToAngle(360);
//        sondaTransition.setDuration(Duration.seconds(3));
//        sondaTransition.setCycleCount(sondaTransition.INDEFINITE);
//        sondaTransition.setAutoReverse(true);
//
//        PhongMaterial material = new PhongMaterial();
//        material.setDiffuseColor(Color.GOLDENROD);
//        material.setSpecularColor(Color.rgb(30, 30, 30));
//        planet.setMaterial(material);
//
//        planet.rotateProperty();
//        planet.rotationAxisProperty();
//        sondaTransition.play();
//        translateTransition.play();
    }

    /**
     * Incrementa la barra de energia y el indicador
     *
     * @param event
     */
    private void increase(MouseEvent event)
    {
        energyBar.setProgress(energyBar.getProgress() + 0.01);
        indicator.setProgress(indicator.getProgress() + 0.1);
    }

    /**
     * Valida que una ruta de un archivo a cargar exista
     *
     * @param path es la ruta del archivo a validar
     * @return La url del archivo a cargar
     * @throws MalformedURLException
     */
    private URL checkFile(String path) throws MalformedURLException
    {
        URL response = null;
        File file = new File(path);
        if (file.exists())
            response = file.toURI().toURL();
        return response;
    }

    @FXML
    private void newNebula()
    {
        this.activeNewNebula = true;//Se utilizara en la createUniverse
        this.nameNebula = JOptionPane.showInputDialog("Digite el nombre de la Nebulosa").toUpperCase().trim();//NOMBRE_NEBULA        
//        this.mouseClicked = true;
//        panelActionClick();
    }

    @FXML
    private void newPlanetarySystem(MouseEvent event)
    {
        this.activeNewPlanetarySystem = true;
        JOptionPane.showMessageDialog(null, "Introduzca sistemas planetarios");
    }

    @FXML
    private void newPlanet(MouseEvent event)
    {
        this.activeNewPlanet = true;
    }

    private void panelActionClick()
    {
        /*
        //String nameNebula = JOptionPane.showInputDialog("Digite el nombre de la Nebulosa").toUpperCase().trim();//NOMBRE_NEBULA

        principalPane.getCenter().setOnMouseClicked((MouseEvent eventClick)
                -> 
                {
                    if (this.mouseClicked)//Controla el evento del mouse
                    {
                        if (this.activeNewNebula && !nameNebula.isEmpty())
                        {
                            ImageView imageView = new ImageView("Images/nebula.png");
                            imageView.setFitHeight(100);
                            imageView.setFitWidth(100);
                            imageView.setId(nameNebula);
                            imageView.setX(eventClick.getX() + 150);
                            imageView.setY(eventClick.getY() - 15);
                            //Label label = new Label(nameNebula);
                            principalPane.getChildren().add(imageView);
                            //principalPane.getChildren().add(label);

                            //label.setLayoutX(eventClick.getX());
                            //label.setLayoutY(eventClick.getY());
                            this.activeNewNebula = false;
                            Nebula newNebula = new Nebula(nameNebula);
                            newNebula.setView(imageView);
                            this.nebulas.add(newNebula);
                            System.out.println(activeNewNebula);
                            //this.principalPane.getChildren()
                        }
                        else
                        {
                            Image close = new Image("/Images/close.png");
                            Notifications dateNotification = Notifications.create()
                                    .title("Alert")
                                    .text("Type nebulosa's name")
                                    .hideAfter(Duration.seconds(2))
                                    .graphic(new ImageView(close))
                                    .position(Pos.TOP_CENTER);
                            dateNotification.darkStyle();
                            dateNotification.show();
                        }
                        //si le dan click
                        this.mouseClicked = false;
                    }
                    else
                    {

                    }
        });
         */
    }

    private LinkedList<GridPane> hideNebulas()
    {
        LinkedList<GridPane> viewNebulas = new LinkedList<>();
        this.nebulas.stream().forEach((Nebula nebula)
                -> 
                {
                    viewNebulas.add(nebula.getView());
        });
        return viewNebulas;
    }

    private boolean isNebula(Node objeto)
    {
        return this.nebulas.stream().anyMatch((nebula) -> (nebula.getView().equals(objeto)));
    }

    private void exitGame()
    {
        System.exit(0);
    }

    @FXML
    private void CreateUniverse(MouseEvent event)
    {
        if (this.createNebula)// && !nameNebula.isEmpty() Se implementara en la scena que pida los valores
        {
            ImageView imageView = new ImageView("Images/nebula.png");
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            imageView.setId(this.nameNebula);

            Label label = new Label(this.nameNebula);
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
            grid.setLayoutX(event.getScreenX());
            grid.setLayoutY(event.getScreenY());
            grid.setOnMouseClicked((MouseEvent evento)
                    -> 
                    {
                        if (this.createNebula)//Alerta que esta intentando crear una nebula sobre otra existente
                        {
                            Image close = new Image("/Images/close.png");
                            Notifications dateNotification = Notifications.create()
                                    .title("Alert")
                                    .text("Try again in another place")
                                    .hideAfter(Duration.seconds(2))
                                    .graphic(new ImageView(close))
                                    .position(Pos.TOP_CENTER);
                            dateNotification.darkStyle();
                            dateNotification.show();
                            this.createNebula = false;
                            this.activeNewNebula = true;
                        }
                        else//Accede a la creacion de sistemas planterios de la nebula seleccionada
                        {
                            GridPane encabezado = new GridPane();
                            encabezado.setPadding(new Insets(0, 0, 0, 30));//t, r, b, l
                            encabezado.getColumnConstraints().add(new ColumnConstraints(1300));
                            encabezado.setVgap(8);
                            encabezado.setHgap(5);

                            Label nebulaTitle = new Label("Nebula - > " + imageView.getId());
                            nebulaTitle.setFont(Font.font("Cambria", 30));
                            nebulaTitle.setTextFill(Color.web("#0076a3"));
                            nebulaTitle.setLayoutX(20);
                            nebulaTitle.setLayoutY(20);
                            nebulaTitle.setWrapText(true);
                            nebulaTitle.setVisible(true);

                            encabezado.setGridLinesVisible(true);//Muestra las lineas del GridPane
                            encabezado.add(nebulaTitle, 0, 0);//hijo, columna, fila
                            this.btnSuccesful.setVisible(true);
                            encabezado.add(this.btnSuccesful, 1, 0);
                            
                            this.principalPane.setTop(encabezado);
                            this.principalPane.getChildren().removeAll(hideNebulas());
                            //poner visibles los otros botones
                            this.btnNewNebula.setDisable(true);
                            this.btnNewNebula.setOpacity(0.5);
                            this.btnNewPlanetarySystem.setVisible(true);
                            this.lblNewPlanetarySystem.setVisible(true);
                            /*
                            System.out.println("elements");
                            System.out.println(grid.getTypeSelector());//tipoElemento
                            System.out.println(this.principalPane.getChildren().toString());
                            this.principalPane.getChildren().clear();
                            this.principalPane.getChildren().removeAll(hideNebulas());
                            System.out.println("se borraron");
                            //Mostrar el nombre de la nebulosa   
                            //this.principalPane.setTop(nebulaTitle);
                            System.out.println(this.principalPane.getChildren().toString());
                             */
                        }
            });
//            ImageView nebula = (ImageView) grid.getChildren().get(1);//ImageView nebula
//            JOptionPane.showMessageDialog(null, nebula);
            this.principalPane.getChildren().add(grid);

            Nebula newNebula = new Nebula(this.nameNebula);
            newNebula.setView(grid);
            this.nebulas.add(newNebula);
            System.out.println(activeNewNebula);
            this.createNebula = false;
        }

        if (this.activeNewNebula)
        {
            this.createNebula = true;
            this.activeNewNebula = false;
        }
    }

    @FXML
    private void back(MouseEvent event)
    {
        this.principalPane.getChildren().addAll(hideNebulas());
        this.principalPane.setTop(null);
        this.btnNewNebula.setDisable(false);
        this.btnNewNebula.setOpacity(1);
        this.btnNewPlanetarySystem.setVisible(false);
        this.lblNewPlanetarySystem.setVisible(false);
    }

}
