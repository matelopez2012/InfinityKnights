/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Sebas
 */
public class EnemiesInformationController implements Initializable {

    @FXML
    private AnchorPane paneEnemiesInfo;
    @FXML
    private JFXButton btnNodrize;
    @FXML
    private JFXButton btnExplorer;
    @FXML
    private JFXButton btnMedium;
    @FXML
    private JFXButton btnExit;
    @FXML
    private ImageView enemieImage;
    @FXML
    private Text txtTypeValue;
    @FXML
    private Text txtNameValue;
    @FXML
    private Text txtRaceValue;
    @FXML
    private Text txtHurtValue;
    @FXML
    private Text txtLifeValue;

    SidePanelContentController sidePanelContentController = new SidePanelContentController();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        paneEnemiesInfo.setOpacity(0);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), paneEnemiesInfo);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

        TranslateTransition translateTransition = new TranslateTransition();
        System.out.println("Entre a la transicion");
        translateTransition.setDuration(Duration.seconds(2));
        translateTransition.setCycleCount(TranslateTransition.INDEFINITE);
        translateTransition.setNode(enemieImage);
        translateTransition.setAutoReverse(true);
        translateTransition.setToY(15);
        translateTransition.play();
    }

    @FXML
    private void changeInformation(ActionEvent event) {
        JFXButton btn = (JFXButton) event.getSource();
        switch (btn.getId()) {
            case "btnNodrize":
                showInfoNodrize();
                break;
            case "btnMedium":
                showInfoMedium();
                break;
            case "btnExplorer":
                showInfoExplorer();
                break;
        }
    }

    private void showInfoNodrize() {
        enemieImage.setImage(new Image("Images/enemyImage2.png"));
        txtTypeValue.setText("NODRIZE");
        txtNameValue.setText("ELITE");
        txtHurtValue.setText("50");
        txtLifeValue.setText("5000");
    }

    private void showInfoMedium() {
        enemieImage.setImage(new Image("Images/enemyImage1.png"));
        txtTypeValue.setText("MEDIUM");
        txtNameValue.setText("SANGUEILI");
        txtHurtValue.setText("10");
        txtLifeValue.setText("2000");
    }

    private void showInfoExplorer() {
        enemieImage.setImage(new Image("Images/enemyImage5.png"));
        txtTypeValue.setText("EXPLORER");
        txtNameValue.setText("GRUNT");
        txtHurtValue.setText("5");
        txtLifeValue.setText("1000");
    }

    @FXML
    private void exit(ActionEvent event) {
        SidePanelContentController controller = new SidePanelContentController();
        Stage stage = (Stage) btnExit.getScene().getWindow();
        
        stage.close();

    }

}
