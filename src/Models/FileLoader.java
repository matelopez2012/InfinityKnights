/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Controllers.EnemiesInformationController;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author matel
 */
public class FileLoader
{

    private String path;

    public FileLoader()
    {
    }

    public FileLoader(String path)
    {
        this.path = path;
    }

    public void open() throws MalformedURLException
    {
        URL response = null;
        File file = new File(this.path);
        if (file.exists())
            response = file.toURI().toURL();
        System.out.println(response);
        try
        {
            openStage(response);
        }
        catch (IOException ex)
        {
            System.out.println("Error al abrir el archivo" + ex);
        }
    }

    private void openStage(URL url) throws IOException
    {
        if (url != null)
        {
            FXMLLoader loader = new FXMLLoader(url);
            Parent panelEdit = loader.load();
            EnemiesInformationController controller = loader.getController();

            Scene scene = new Scene(panelEdit);//lo de por dentro
            Stage stage = new Stage(StageStyle.UNDECORATED);

            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * @return the path
     */
    public String getPath()
    {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path)
    {
        this.path = path;
    }

}
