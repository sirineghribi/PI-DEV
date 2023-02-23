/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.entity.CategorieVehicule;
import tn.esprit.entity.Maintenance;
import tn.esprit.entity.TypeCat;
import tn.esprit.entity.Vehicule;
import tn.esprit.services.CategorieVehiculeServices;
import tn.esprit.services.VehiculeServices;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjoutCategorieController implements Initializable {

   
    @FXML
    private TextField lieu;
    @FXML
    private ComboBox<TypeCat> comboCat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<TypeCat> opt =  FXCollections.observableArrayList(TypeCat.CARGO, TypeCat.CIVIL);
      comboCat.setPromptText("choix du type ");
      comboCat.setItems(opt);
    }    

    @FXML
    private void AjouterCategorie(ActionEvent event) {
        
        
        TypeCat tr=comboCat.getValue();
        String lieuu= lieu.getText();
       
       
         if(!(comboCat ==null || lieu.getText().equals("") ))
                 {
                     
                     
       
        CategorieVehicule r=new CategorieVehicule(tr,lieuu);

        CategorieVehiculeServices rs = new CategorieVehiculeServices();
        rs.ajouter(r);
                    Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Ajout avec succées!");
            alert.showAndWait();
                 }
        else{
        Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Error alert");
alert.setHeaderText("Les champs de texte d'un formulaire ne doivent pas être null/vide");
alert.showAndWait();
        }
         
         
         
         
                 }
    


/*
    private void gestion_categorie(ActionEvent event) {
      try{
         Parent root = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/AjoutCategorie.fxml"));  
         Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
               
        catch(Exception e)
        {
            System.out.println("Probleme:"+e);
        }   
    }  */

    @FXML
    private void AfficherCategorie(ActionEvent event) {
        
        try{
         Parent root = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/AfficherCat.fxml"));  
         Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
               
        catch(Exception e)
        {
            System.out.println("Probleme:"+e);
        } 
           
    }
    
    
}