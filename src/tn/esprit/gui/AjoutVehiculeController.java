/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.esprit.entity.CategorieVehicule;
import tn.esprit.entity.Maintenance;
import tn.esprit.entity.Vehicule;
import tn.esprit.entity.TypeCat;
import tn.esprit.services.CategorieVehiculeServices;
import tn.esprit.services.VehiculeServices;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjoutVehiculeController implements Initializable {

    @FXML
    private TextField tf_nom;
    @FXML
    private TextField tf_poid;
    @FXML
    private TextField tf_vitesse;
    @FXML
    private TextField tf_passager;
    @FXML
    private TextField tf_status;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
    }    

    @FXML
    private void ajouter_vehicule(ActionEvent event) {
        
            String nom = tf_nom.getText();
          //  int cat_vehicule = Integer.valueOf(tf_cat.getText());
            Float poid = Float.valueOf(tf_poid.getText());
            int vitesse = Integer.valueOf(tf_vitesse.getText());
            int passager = Integer.valueOf(tf_passager.getText());
            boolean status = Boolean.valueOf(tf_status.getText());
        
        
         if(!( tf_nom.getText().equals("") || tf_poid.getText().equals("") || tf_vitesse.getText().equals("") || tf_passager.getText().equals("") ))
                 {
                    // Vehicule v = new Vehicule(tf_nom.getText(),Float.parseFloat(tf_poid.getText()), Integer.parseInt(tf_vitesse.getText()),Integer.parseInt(tf_passager.getText()));
                  // Maintenance m1= new Maintenance(1,21,(float)2.5,(float)2.5,false);
                    CategorieVehicule c2 = new CategorieVehicule(16, TypeCat.CARGO, "New Jersey"); 
                     
                     
                    Vehicule v1 = new Vehicule(nom,c2,poid,vitesse,passager,status);
        
                 VehiculeServices rs = new VehiculeServices();
        
        rs.ajouter(v1);
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

    @FXML
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
        
    }

    @FXML
    private void afficherVehicule(ActionEvent event) {
        
         try{
         Parent root = FXMLLoader.load(getClass().getResource("/tn/esprit/gui/afficher_vh.fxml"));  
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
