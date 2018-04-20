/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXProgressBar;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Sebas
 */
public class LoadGameController implements Initializable {

    @FXML
    private AnchorPane loadPane;
    @FXML
    private ProgressBar lifeBar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void loadGame() {
        
        int progress = 0;
        System.out.println(lifeBar.getProgress());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(LoadGameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        progress = (int) Math.floor(Math.random() * 20);
        lifeBar.setProgress(lifeBar.getProgress() + progress);
        System.out.println(lifeBar.getProgress());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(LoadGameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        progress = (int) Math.floor(Math.random() * 20);
        lifeBar.setProgress(lifeBar.getProgress() + progress);
        System.out.println(lifeBar.getProgress());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(LoadGameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        progress = (int) Math.floor(Math.random() * 20);
        lifeBar.setProgress(lifeBar.getProgress() + progress);

        System.out.println("holaaaaaaa");

    }

}
