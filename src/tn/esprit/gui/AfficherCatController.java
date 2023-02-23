/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import tn.esprit.entity.CategorieVehicule;
import tn.esprit.entity.Vehicule;
import tn.esprit.services.CategorieVehiculeServices;
import tn.esprit.services.VehiculeServices;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AfficherCatController implements Initializable {

    @FXML
    private TableView<CategorieVehicule> tabCat;
    @FXML
    private TableColumn<CategorieVehicule, String> id_cat;
    @FXML
    private TableColumn<CategorieVehicule, String> nom_cat;
    @FXML
    private TableColumn<CategorieVehicule, String> lieu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CategorieVehiculeServices vs = new CategorieVehiculeServices();

ObservableList<CategorieVehicule> liste=FXCollections.observableArrayList(vs.getAll());
       tabCat.setItems(liste);
       
        
         CategorieVehicule p = new CategorieVehicule();
        // System.out.println(vs.getAll());
              

        
       // id_vehicule.setCellValueFactory(new PropertyValueFactory<>("id_vehicule"));
       id_cat.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(String.valueOf(cell.getValue().get_id_cat()));
            return s;
        });
       
        nom_cat.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(String.valueOf(cell.getValue().get_nom_cat()));
          //  s.set(cell.getValue().get_nom_cat().toString());
            
            return s;
        }); 
        lieu.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(cell.getValue().getLieu());
            return s;
        
        });
    }    

    @FXML
    private void supprimerCat(ActionEvent event) {
        
        CategorieVehicule a= tabCat.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
       CategorieVehiculeServices as= new CategorieVehiculeServices();  
       
        if (a!=null){
           
       
        alert.setTitle("Confirmation de suppression");
         alert.setHeaderText("Confiramation de suppression");
         alert.setContentText("Voulez-vous vraiment supprimer cette reclamation!");
   
        Optional<ButtonType> result = alert.showAndWait();
         // ... user chose OK
       if (result.get() == ButtonType.OK){

            as.supprimerCat(a);
           as.getAll();
           
       }
       
    }
  else
    {
           Alert al = new Alert(Alert.AlertType.ERROR);

           al.setTitle("Error alert");
           al.setHeaderText("Vous devez selectionner au moins un vehicule à suprrimer");
   
            al.showAndWait();
    }
    }
 
}
    
