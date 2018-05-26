/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.TreeTableView;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author matel
 */
public class CreateGalaxyController implements Initializable
{

    @FXML
    private JFXButton btnCreate;
    @FXML
    private JFXTextField inpNebula;
    @FXML
    private TreeTableView<String> table2;
    TreeTableColumn<String, String> column;
    TreeItem<String> root;
    LinkedList<TreeItem<String>> elementos;
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // Creating a column
        column = new TreeTableColumn<>("InfinityKnights");
        root = new TreeItem<>("Nebulosas");
        this.table2.setRoot(root);
        this.table2.getColumns().add(column);
        this.table2.setPrefWidth(152);
        this.elementos = new LinkedList<>();
    }
    
    @FXML
    private void newNebula(ActionEvent event)
    {
        final TreeItem<String> nuevo = new TreeItem<>(this.inpNebula.getText());
        if(!existe(nuevo))
            this.elementos.add(nuevo);
        else
            JOptionPane.showMessageDialog(null, "La nebulosa -> " + nuevo.getValue() + " <- Ya existe");
        root.setExpanded(true);
        System.out.println(this.table2.getVisibleLeafIndex(column));
        root.getChildren().setAll(this.elementos);
        column.setPrefWidth(150);
        //Defining cell content
        column.setCellValueFactory((CellDataFeatures<String, String> p)
        -> 
        {
            return new ReadOnlyStringWrapper(p.getValue().getValue());
        });
    }
    
    private boolean existe(TreeItem<String> nuevo){
        for (TreeItem<String> item : this.elementos)
        {
            if(item.getValue().equalsIgnoreCase(nuevo.getValue()))
                return true;
        }
        return false;
    }
}
