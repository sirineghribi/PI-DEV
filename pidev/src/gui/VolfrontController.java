/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Vol;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import services.VolService;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class VolfrontController implements Initializable {

    @FXML
    private ListView<Vol> myListView;
    @FXML
    private Button button;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VolService volservice=new VolService();
        ObservableList<Vol> liste=FXCollections.observableArrayList(volservice.getAll());
        myListView.getItems().addAll(volservice.getAll());
		
	
        button.setOnAction(event -> {
            Vol selectedItem = myListView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                try
                                {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/reserver.fxml"));
                                    Parent root =loader.load();
                                    ReserverController rc =loader.getController();
                                    rc.setVol(selectedItem);
                                    Scene scene = new Scene(root);
                                    Stage SecondaryStage=new Stage();
                                    SecondaryStage.setTitle("Reserver Vol !");
                                    SecondaryStage.setScene(scene);
                                    SecondaryStage.show();
                                }
                                catch(Exception ex)
                                {
                                    System.out.println("err:"+ex);
                                }
                
                
            } else {
                System.out.println("Aucun élément sélectionné.");
            }
        });	
	
       
    }    
    
}
