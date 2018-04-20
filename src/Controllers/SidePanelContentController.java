package Controllers;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SidePanelContentController implements Initializable {

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

    public void setBtnEnemies(JFXButton btnEnemies) {
        this.btnEnemies = btnEnemies;
    }

    public JFXButton getBtnEnemies() {
        return btnEnemies;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void openViews(ActionEvent event) {
        try {
            JFXButton btn = (JFXButton) event.getSource();
            switch (btn.getId()) {
                case "btnEnemies":
                    openViewEnemies(event);
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

    private void openViewEnemies(ActionEvent event) throws MalformedURLException, IOException {
        String path = "src/Views/EnemiesInformation.fxml";

        URL url = checkFile(path);

        changeView(url);
    }

    /**
     * *
     * Se encarga de validar la existencia de un archivo
     *
     * @param path es la ruta del aarchivo a validar
     * @return la Url de este archivo
     * @throws MalformedURLException para manejo de excepciones
     */
    private URL checkFile(String path) throws MalformedURLException {
        URL response = null;
        File file = new File(path);
        if (file.exists()) {
            response = file.toURI().toURL();
        }
        System.out.println(response);
        return response;
    }

    /**
     * *
     * Este método se encarga de cargar las diferentes vistas desde el SidePanel
     *
     * @param url se recibe el url dela paguna a cargar
     * @throws IOException para menejo de excepciones
     */
    private void changeView(URL url) throws IOException {
        if (url != null) {
            FXMLLoader loader = new FXMLLoader(url);
            Parent newPanel = loader.load();
            EnemiesInformationController controller = loader.getController();

            Scene scene = new Scene(newPanel);
            Stage stage = new Stage(StageStyle.UNDECORATED);

            stage.setScene(scene);
            stage.show();
            //No sirve el habilitar de nuevo elboton para la vista de enemigos
//            btnEnemies.setDisable(false);
        }
    }
}
