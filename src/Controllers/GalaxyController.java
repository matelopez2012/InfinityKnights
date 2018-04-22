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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Sphere;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Sebas
 */
public class GalaxyController implements Initializable {

    boolean activeNewNebula = false;

    @FXML
    private BorderPane principalPane;
    @FXML
    private ImageView blueShip;
    @FXML
    private ImageView sonda;
    @FXML
    private ProgressBar energyBar;
    @FXML
    private ProgressIndicator indicator;
    @FXML
    private JFXHamburger hamburguer;
    @FXML
    private JFXDrawer drawer;

    public static BorderPane rootP;
    @FXML
    private Sphere planet;
    @FXML
    private Circle circleTest;
    @FXML
    private ImageView btnPlusNebula;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        rootP = principalPane;

        String path = "src/Views/SidePanelContent.fxml";

        try {
            URL urlFile = checkFile(path);
            System.out.println(urlFile);
            System.out.println(urlFile);
            VBox box = FXMLLoader.load(urlFile);
            System.out.println(box);
            drawer.setSidePane(box);
        } catch (MalformedURLException ex) {
            System.err.println("error");
        } catch (IOException ex) {
            System.err.println("error");
        }

        HamburgerSlideCloseTransition transition = new HamburgerSlideCloseTransition(hamburguer);
        transition.setRate(-1);
        hamburguer.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isShown()) {
                drawer.close();
            } else {
                drawer.open();
            }
        });

        //No funciona el .show en la notificacion 
        //dateNotification.show();
    }

    /**
     * Transiciones para las naves
     *
     * @param event
     */
    @FXML
    private void action(MouseEvent event) {

        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(blueShip);
        translateTransition.setDuration(Duration.seconds(3));
        translateTransition.setCycleCount(TranslateTransition.INDEFINITE);
        translateTransition.setAutoReverse(true);
        translateTransition.setToX(600);
        translateTransition.setToY(50);

        RotateTransition sondaTransition = new RotateTransition();
        sondaTransition.setNode(sonda);
        sondaTransition.setByAngle(0);
        sondaTransition.setToAngle(360);
        sondaTransition.setDuration(Duration.seconds(3));
        sondaTransition.setCycleCount(sondaTransition.INDEFINITE);
        sondaTransition.setAutoReverse(true);

        PhongMaterial material = new PhongMaterial();
        material.setDiffuseColor(Color.GOLDENROD);
        material.setSpecularColor(Color.rgb(30, 30, 30));
        planet.setMaterial(material);

        planet.rotateProperty();
        planet.rotationAxisProperty();
        sondaTransition.play();
        translateTransition.play();
    }

    /**
     * Incrementa la barra de energia y el indicador
     *
     * @param event
     */
    @FXML
    private void increase(MouseEvent event) {
        energyBar.setProgress(energyBar.getProgress() + 0.01);
        indicator.setProgress(indicator.getProgress() + 0.1);
    }

    /**
     * Mueve la sonda hacia el punto de click
     *
     * @param event
     */
    @FXML
    private void moveSonda(MouseEvent event) {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setNode(blueShip);
        translateTransition.setDuration(Duration.seconds(1));
        translateTransition.setToX(event.getSceneX() - 230);
        translateTransition.setToY(event.getSceneY() - 50);

        translateTransition.play();
        //System.out.println(pointMouse.x + " " + pointMouse.y);

    }

    /**
     * Valida que una ruta de un archivo a cargar exista
     *
     * @param path es la ruta del archivo a validar
     * @return La url del archivo a cargar
     * @throws MalformedURLException
     */
    private URL checkFile(String path) throws MalformedURLException {
        URL response = null;
        File file = new File(path);
        if (file.exists()) {
            response = file.toURI().toURL();
        }
        return response;
    }

    @FXML
    private void newNebula() {
        panelActionClick();
    }

    private void panelActionClick() {
        String nameNebula = JOptionPane.showInputDialog("Digite el nombre de la Nebulosa");
        Nebula nebula = new Nebula(nameNebula);
        if (!nameNebula.isEmpty()) {
            principalPane.getCenter().setOnMouseClicked((MouseEvent eventClick) -> {
                ImageView imageView = new ImageView("Images/nebulaIcon5.png");
                imageView.setFitHeight(200);
                imageView.setFitWidth(200);
                principalPane.getChildren().add(imageView);
                imageView.setX(eventClick.getX());
                imageView.setY(eventClick.getY());
                System.out.println(activeNewNebula);               
            });
        } else {
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

    }

    private void exitGame() {
        System.exit(0);
    }

}
