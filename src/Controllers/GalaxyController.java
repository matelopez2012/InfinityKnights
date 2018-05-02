/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Nebula;
import Models.Planet;
import Models.PlanetarySystem;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import models.JSONReader;
import org.controlsfx.control.Notifications;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author Sebas
 */
public class GalaxyController implements Initializable
{

    boolean createNebula;
    boolean createPlanetarySystem;
    boolean createPlanet;
    boolean sePuedeCrear;

    boolean planetarySystemView;
    boolean planetsView;

    Nebula auxNebula;
    PlanetarySystem auxPlanetarySystem;

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
    @FXML
    private JFXButton btnNewNebula;
    @FXML
    private JFXButton btnNewPlanetarySystem;
    @FXML
    private Label lblNewPlanetarySystem;
    @FXML
    private JFXButton btnNewPlanet;
    @FXML
    private Label lblNewPlanet;
    @FXML
    private ImageView btnSuccesful;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnLoad;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

        this.rootP = this.principalPane;
        this.createNebula = false;
        this.createPlanetarySystem = false;
        this.createPlanet = false;
        this.sePuedeCrear = true;
        this.planetarySystemView = true;
        this.planetsView = false;
        this.auxNebula = null;
        this.auxPlanetarySystem = null;
        this.nebulas = new LinkedList<>();
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

    private void panelActionClick()
    {
        /*
        //String nebulaName = JOptionPane.showInputDialog("Digite el nombre de la Nebulosa").toUpperCase().trim();//NOMBRE_NEBULA

        principalPane.getCenter().setOnMouseClicked((MouseEvent eventClick)
                -> 
                {
                    if (this.mouseClicked)//Controla el evento del mouse
                    {
                        if (this.activeNewNebula && !nebulaName.isEmpty())
                        {
                            ImageView imageView = new ImageView("Images/nebula.png");
                            imageView.setFitHeight(100);
                            imageView.setFitWidth(100);
                            imageView.setId(nebulaName);
                            imageView.setX(eventClick.getX() + 150);
                            imageView.setY(eventClick.getY() - 15);
                            //Label label = new Label(nebulaName);
                            principalPane.getChildren().add(imageView);
                            //principalPane.getChildren().add(label);

                            //label.setLayoutX(eventClick.getX());
                            //label.setLayoutY(eventClick.getY());
                            this.activeNewNebula = false;
                            Nebula newNebula = new Nebula(nebulaName);
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

    private LinkedList<GridPane> hidePlanetarySystems()
    {
        LinkedList<PlanetarySystem> auxPlanetarySystems = this.auxNebula.getPlanetarySystems();
        LinkedList<GridPane> viewPlanetarySystems = new LinkedList<>();
        if (auxPlanetarySystems.size() > 0)
            auxPlanetarySystems.stream().forEach((auxPlanetarySystem)
                    -> 
                    {
                        viewPlanetarySystems.add(auxPlanetarySystem.getView());
            });
        return viewPlanetarySystems;
    }

    private LinkedList<GridPane> hidePlanets()
    {
        LinkedList<Planet> auxPlanets = this.auxPlanetarySystem.getPlanets();
        LinkedList<GridPane> viewPlanets = new LinkedList<>();

        auxPlanets.stream().forEach((auxPlanet)
                -> 
                {
                    viewPlanets.add(auxPlanet.getView());
        });
        return viewPlanets;
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
        if (this.createNebula)// && !nebulaName.isEmpty() Se implementara en la scena que pida los valores
        {
            String nebulaName = JOptionPane.showInputDialog("Digite el nombre de la Nebulosa").toUpperCase().trim();//NOMBRE_NEBULA
            ImageView imageView = new ImageView("Images/nebula.png");
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            imageView.setId(nebulaName);

            Label label = new Label(nebulaName);
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
            Nebula newNebula = new Nebula(nebulaName);
            newNebula.setView(grid);
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
                            this.sePuedeCrear = false;
                        }
                        else//Accede a la creacion de sistemas planterios de la nebula seleccionada
                        {
                            this.auxNebula = newNebula;
                            this.planetarySystemView = true;
                            this.principalPane.getChildren().addAll(hidePlanetarySystems());//muestra todos los sistemas planetarios de esa nebulosa
                            GridPane encabezado = new GridPane();
                            encabezado.setPadding(new Insets(0, 0, 0, 30));//t, r, b, l
                            encabezado.getColumnConstraints().add(new ColumnConstraints(1300));
                            encabezado.setVgap(8);
                            encabezado.setHgap(5);

                            Label nebulaTitle = new Label("Nebula - > " + nebulaName);
                            nebulaTitle.setFont(Font.font("Cambria", 30));
                            nebulaTitle.setTextFill(Color.web("#0076a3"));
                            nebulaTitle.setLayoutX(20);
                            nebulaTitle.setLayoutY(20);
                            nebulaTitle.setWrapText(true);
                            nebulaTitle.setVisible(true);

                            encabezado.setGridLinesVisible(true);//Muestra las lineas del GridPane
                            this.btnSuccesful.setVisible(true);

                            encabezado.add(nebulaTitle, 0, 0);//hijo, columna, fila

                            encabezado.add(this.btnSuccesful, 1, 0);
//                            this.nebulaHeader = encabezado;
                            this.principalPane.setTop(encabezado);

                            this.principalPane.getChildren().removeAll(hideNebulas());
                            //poner visibles los otros botones
                            this.btnNewNebula.setDisable(true);
                            this.btnNewNebula.setOpacity(0.5);
                            this.btnNewPlanetarySystem.setVisible(true);
                            this.lblNewPlanetarySystem.setVisible(true);
                        }
            });
            if (this.sePuedeCrear)
            {//Si se puede crear cualquier elemento, verifica que no se cree uno sobre el otro
                this.principalPane.getChildren().add(grid);
                this.nebulas.add(newNebula);
                this.createNebula = false;
            }
            else
                this.sePuedeCrear = true;
        }

        if (this.createPlanetarySystem)
        {
            String planetarySystemName = JOptionPane.showInputDialog("Digite el nombre del Sistema Planetario").toUpperCase().trim();
            ImageView imageView = new ImageView("Images/nebulaIcon2.png");
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            imageView.setId(planetarySystemName);

            Label label = new Label(planetarySystemName);
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
            PlanetarySystem newPlanetarySystem = new PlanetarySystem(planetarySystemName);
            newPlanetarySystem.setView(grid);
            grid.setOnMouseClicked((MouseEvent evento)
                    -> 
                    {
                        if (this.createPlanetarySystem)//Alerta que esta intentando crear un sistema planetario sobre otra existente
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
                            this.sePuedeCrear = false;
                        }
                        else//Accede a la creacion de sistemas planterios de la nebula seleccionada
                        {
                            this.auxPlanetarySystem = newPlanetarySystem;
                            this.principalPane.getChildren().addAll(hidePlanets());//Agrega todos los planetas de este sistema planetario
                            GridPane encabezado = new GridPane();
                            encabezado.setPadding(new Insets(0, 0, 0, 30));//t, r, b, l
                            encabezado.getColumnConstraints().add(new ColumnConstraints(1300));
                            encabezado.setVgap(8);
                            encabezado.setHgap(5);
                            Label PlanetaryTitle = new Label("Nebula - > " + this.auxNebula.getName() + " -- Planetary system -> " + planetarySystemName);
                            PlanetaryTitle.setFont(Font.font("Cambria", 30));
                            PlanetaryTitle.setTextFill(Color.web("#0076a3"));
                            PlanetaryTitle.setLayoutX(20);
                            PlanetaryTitle.setLayoutY(20);
                            PlanetaryTitle.setWrapText(true);
                            PlanetaryTitle.setVisible(true);
                            encabezado.setGridLinesVisible(true);//Muestra las lineas del GridPane
                            encabezado.add(PlanetaryTitle, 0, 0);//hijo, columna, fila
                            this.btnSuccesful.setVisible(true);

                            encabezado.add(this.btnSuccesful, 1, 0);
//                            this.planetarySystemHeader = encabezado;
                            this.principalPane.setTop(encabezado);
                            this.principalPane.getChildren().removeAll(hidePlanetarySystems());
                            //poner visibles los otros botones
                            this.btnNewPlanetarySystem.setDisable(true);
                            this.btnNewPlanetarySystem.setOpacity(0.5);
                            this.btnNewPlanet.setVisible(true);
                            this.lblNewPlanet.setVisible(true);

                            this.planetarySystemView = false;//vista de los sistemas planetarios
                            this.planetsView = true;//vista de los planetas
                        }
            });
            if (this.sePuedeCrear)
            {//Si se puede crear cualquier elemento, verifica que no se cree uno sobre el otro
                this.principalPane.getChildren().add(grid);
                this.auxNebula.add(newPlanetarySystem);
                this.createPlanetarySystem = false;
            }
            else
                this.sePuedeCrear = true;
        }

        if (this.createPlanet)
        {
            String planetName = JOptionPane.showInputDialog("Digite el nombre del Planeta").toUpperCase().trim();
            ImageView imageView = new ImageView("Images/sonda.png");
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            imageView.setId(planetName);

            Label label = new Label(planetName);
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
            Planet newPlanet = new Planet(planetName);
            newPlanet.setView(grid);
            grid.setOnMouseClicked((MouseEvent evento)
                    -> 
                    {
                        if (this.createPlanet)//Alerta que esta intentando crear un planeta encima de otro
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
                            this.sePuedeCrear = false;
                        }
                        else//Accede a la creacion de sistemas planterios de la nebula seleccionada

                            JOptionPane.showMessageDialog(null, "Planeta: " + planetName + "\niridio: " + newPlanet.getIridium() + "\npaladio: " + newPlanet.getPaladium() + "\nplatino: " + newPlanet.getPlatinium() + "\nElemento zero: " + newPlanet.getElementZero()); //                            GridPane encabezado = new GridPane();

            });
            if (this.sePuedeCrear)
            {//Si se puede crear cualquier elemento, verifica que no se cree uno sobre el otro
                this.principalPane.getChildren().add(grid);
                this.auxPlanetarySystem.add(newPlanet);
                this.createPlanet = false;
            }
            else
                this.sePuedeCrear = true;
        }
    }

    @FXML
    private void back(MouseEvent event)
    {
        if (this.planetarySystemView)
        {//si estaba en esta vista
            this.principalPane.getChildren().removeAll(hidePlanetarySystems());//oculta todos los sistemas planetarios
            this.principalPane.getChildren().addAll(hideNebulas());
            this.btnNewNebula.setDisable(false);
            this.btnNewNebula.setOpacity(1);
            this.btnNewPlanetarySystem.setVisible(false);
            this.lblNewPlanetarySystem.setVisible(false);
            this.principalPane.setTop(null);
            this.planetarySystemView = false;
        }
        if (this.planetsView)
        {//si estaba en esta vista
            GridPane encabezado = new GridPane();
            encabezado.setPadding(new Insets(0, 0, 0, 30));//t, r, b, l
            encabezado.getColumnConstraints().add(new ColumnConstraints(1300));
            encabezado.setVgap(8);
            encabezado.setHgap(5);
            encabezado.setGridLinesVisible(true);//Muestra las lineas del GridPane
            Label nebulaTitle = new Label("Nebula - > " + this.auxNebula.getName());
            nebulaTitle.setFont(Font.font("Cambria", 30));
            nebulaTitle.setTextFill(Color.web("#0076a3"));
            nebulaTitle.setLayoutX(20);
            nebulaTitle.setLayoutY(20);
            nebulaTitle.setWrapText(true);
            nebulaTitle.setVisible(true);
            encabezado.add(nebulaTitle, 0, 0);//hijo, columna, fila
            this.btnSuccesful.setVisible(true);

            encabezado.add(this.btnSuccesful, 1, 0);

            this.principalPane.getChildren().removeAll(hidePlanets());//oculta todos los planetas
            this.principalPane.getChildren().addAll(hidePlanetarySystems());
            this.btnNewPlanetarySystem.setDisable(false);
            this.btnNewPlanetarySystem.setOpacity(1);
            this.btnNewPlanet.setVisible(false);
            this.lblNewPlanet.setVisible(false);
            this.principalPane.setTop(encabezado);
            this.planetsView = false;
            this.planetarySystemView = true;
        }

    }

    @FXML
    private void newNebula()
    {
        this.createNebula = true;//Se utilizara en la createUniverse
    }

    @FXML
    private void newPlanetarySystem(ActionEvent event)
    {
        this.createPlanetarySystem = true;//Se utilizara en la createUniverse
    }

    @FXML
    private void newPlanet(ActionEvent event)
    {
        this.createPlanet = true;//Se utilizara en la createUniverse
    }

    @FXML
    private void writeJSON(ActionEvent event)
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Juego");
        String workingDir = System.getProperty("user.dir") + System.getProperty("file.separator") + "GamesSaved";
        fileChooser.setInitialDirectory(new File(workingDir));

        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(//D:\Universidad\Semestre 8\Análisis\InfinityKnights\src\GamesSaved
                new FileChooser.ExtensionFilter("JSON Files", "*.json*")
        );
        File file = fileChooser.showSaveDialog(rootP.getScene().getWindow());
        if (file != null)
        {
            JSONObject game = new JSONObject();
            JSONObject nebulosas = new JSONObject();
            JSONArray listNebulas = new JSONArray();
            for (Nebula nebula : this.nebulas)
            {
                JSONObject nebulaData = new JSONObject();
                JSONArray nPosition = new JSONArray();
                nPosition.put(new Double(nebula.getView().getLayoutX()));
                nPosition.put(new Double(nebula.getView().getLayoutY()));
                nebulaData.put("POSITION", nPosition);
                for (PlanetarySystem planetarySystem : nebula.getPlanetarySystems())
                {
                    JSONObject planetarySystemsData = new JSONObject();
                    JSONArray psPosition = new JSONArray();
                    psPosition.put(new Double(planetarySystem.getView().getLayoutX()));
                    psPosition.put(new Double(planetarySystem.getView().getLayoutY()));
                    planetarySystemsData.put("POSITION", psPosition);
                    for (Planet planet : planetarySystem.getPlanets())
                    {
                        JSONObject planetData = new JSONObject();
                        JSONArray pPosition = new JSONArray();
                        pPosition.put(new Double(planetarySystem.getView().getLayoutX()));
                        pPosition.put(new Double(planetarySystem.getView().getLayoutY()));
                        planetData.put("POSITION", pPosition);
                        planetData.put("IRIDIO", planet.getIridium());
                        planetData.put("PALADIO", planet.getPaladium());
                        planetData.put("PLATINO", planet.getPlatinium());
                        planetData.put("ZERO", planet.getElementZero());
                        planetarySystemsData.put(planet.getName(), planetData);
                    }
                    nebulaData.put(planetarySystem.getName(), planetarySystemsData);
                }
                nebulosas.put(nebula.getName(), nebulaData);
            }
            listNebulas.put(nebulosas);
            game.put("NEBULAS", listNebulas);

            FileWriter fw;
            BufferedWriter bw = null;
            try
            {
                fw = new FileWriter(file, false);
                bw = new BufferedWriter(fw);
                bw.write(game.toString());
                bw.flush();//vacia todos los buffers de salida, obliga a que se escriban todos los datos
            }
            catch (Exception e)
            {
                System.out.println("Error al abrir el archivo" + e);
            }
            finally
            {
                try
                {
                    bw.close();
                }
                catch (Exception e2)
                {
                    System.out.println("Error al cerrar el archivo" + e2);
                }
            }
        }
    }

    @FXML
    private void loadGame(ActionEvent event) throws MalformedURLException
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Juego");
        String workingDir = System.getProperty("user.dir") + System.getProperty("file.separator") + "GamesSaved";
        fileChooser.setInitialDirectory(new File(workingDir));
        fileChooser.getExtensionFilters().addAll(//D:\Universidad\Semestre 8\Análisis\InfinityKnights\src\GamesSaved
                new FileChooser.ExtensionFilter("JSON Files", "*.json*")
        );

        // Obtener el JSON seleccionado
        File file = fileChooser.showOpenDialog(rootP.getScene().getWindow());
        if (file != null)
        {
            System.out.println(file.getAbsolutePath());
            URL auxURL = checkFile(file.getAbsolutePath());
            JOptionPane.showMessageDialog(null, auxURL);
            JOptionPane.showMessageDialog(null, auxURL.getFile().substring(1));
            JSONReader loader = new JSONReader(auxURL.getFile().substring(1));
            this.nebulas = loader.readJSON();
        }

    }
}
