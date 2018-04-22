package Controllers;

import Models.FileLoader;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class SidePanelContentController implements Initializable {

    FileLoader loader;
    @FXML
    private JFXButton btnEnemies;

    @FXML
    private JFXButton btnWeapons;

    @FXML
    private JFXButton btnImprovements;

    @FXML
    private JFXButton btnMaps;

    @FXML
    private JFXButton btnProbes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.loader = new FileLoader();
    }

    @FXML
    private void openViews(ActionEvent event) {
        try {
            JFXButton btn = (JFXButton) event.getSource();
            switch (btn.getId()) {
                case "btnEnemies":
                    System.out.println(btn.getText());
                    loader.setPath("src/Views/EnemiesInformation.fxml");
                    this.loader.open();
                    break;
                case "btnWeapons":
                    System.out.println(btn.getText());
                    break;
                case "btnImprovements":
                    System.out.println(btn.getText());
                    break;
                case "btnMaps":
                    System.out.println(btn.getText());
                    break;
            }
        } catch (IOException ex) {
            Logger.getLogger(SidePanelContentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
