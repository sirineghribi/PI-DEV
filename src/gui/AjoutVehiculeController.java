/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
import entity.CategorieVehicule;
import entity.Maintenance;
import entity.Vehicule;
import entity.TypeCat;
import java.util.stream.Collectors;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import services.CategorieVehiculeServices;
import services.VehiculeServices;

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
    @FXML
    private Button vol;
    @FXML
    private Button user;
    @FXML
    private Button avis;
    @FXML
    private Button type_ab;
    @FXML
    private Button formation;
    @FXML
    private ComboBox<String> categorie_c;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new CategorieVehiculeServices().getAll().forEach((t)->categorie_c.getItems().add(t.get_nom_cat().toString()));
    }    

    @FXML
    private void ajouter_vehicule(ActionEvent event) {
        
            String nom = tf_nom.getText();
          //  int cat_vehicule = Integer.valueOf(tf_cat.getText());
            Float poid = Float.valueOf(tf_poid.getText());
            int vitesse = Integer.valueOf(tf_vitesse.getText());
            int passager = Integer.valueOf(tf_passager.getText());
            boolean status = Boolean.valueOf(tf_status.getText());
            
        
         if(!( tf_nom.getText().equals("") ))
                 {
                    // Vehicule v = new Vehicule(tf_nom.getText(),Float.parseFloat(tf_poid.getText()), Integer.parseInt(tf_vitesse.getText()),Integer.parseInt(tf_passager.getText()));
                  // Maintenance m1= new Maintenance(1,21,(float)2.5,(float)2.5,false);
                    CategorieVehicule c2 = new CategorieVehiculeServices().getAll().stream().filter(t->t.get_nom_cat().equals(CategorieVehicule.enumtypecat(categorie_c.getValue()))).collect(Collectors.toList()).get(0); 
                     
                     
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

    private void gestion_categorie(ActionEvent event) {
      try{
         Parent root = FXMLLoader.load(getClass().getResource("/gui/AjoutCategorie.fxml"));  
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
         Parent root = FXMLLoader.load(getClass().getResource("/gui/afficher_vh.fxml"));  
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
    private void open_vol(ActionEvent event) {
         try
                                {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Vols.fxml"));
                                    Parent root =loader.load();
                                    vol.getScene().setRoot(root);
                                    Scene scene = new Scene(root,816,458);
                                    Stage SecondaryStage=new Stage();
                                    SecondaryStage.setTitle("Afficher Vol !");
                                    SecondaryStage.setScene(scene);
                                    SecondaryStage.show();
                                }
                                catch(Exception ex)
                                {
                                    System.out.println("err:"+ex);
                                }
    }

    @FXML
    private void openuser(ActionEvent event) {
        try
                                {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AfficherUtilisateur.fxml"));
                                    Parent root =loader.load();
                                    vol.getScene().setRoot(root);
                                    Scene scene = new Scene(root,816,458);
                                    Stage SecondaryStage=new Stage();
                                    SecondaryStage.setTitle("Afficher utilisateurs !");
                                    SecondaryStage.setScene(scene);
                                    SecondaryStage.show();
                                }
                                catch(Exception ex)
                                {
                                    System.out.println("err:"+ex);
                                }
    }

    @FXML
    private void open_avis(ActionEvent event) {
        try
                                {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Back_avis.fxml"));
                                    Parent root =loader.load();
                                    vol.getScene().setRoot(root);
                                    Scene scene = new Scene(root,816,458);
                                    Stage SecondaryStage=new Stage();
                                    SecondaryStage.setTitle("Afficher utilisateurs !");
                                    SecondaryStage.setScene(scene);
                                    SecondaryStage.show();
                                }
                                catch(Exception ex)
                                {
                                    System.out.println("err:"+ex);
                                }
    }

    @FXML
    private void open_type_ab(ActionEvent event) {
        try
                                {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Add_Type_Abonnement_FXML.fxml"));
                                    Parent root =loader.load();
                                    vol.getScene().setRoot(root);
                                    Scene scene = new Scene(root,816,458);
                                    Stage SecondaryStage=new Stage();
                                    SecondaryStage.setTitle("Afficher utilisateurs !");
                                    SecondaryStage.setScene(scene);
                                    SecondaryStage.show();
                                    
                                }
                                catch(Exception ex)
                                {
                                    System.out.println("err:"+ex);
                                }
    }

    @FXML
    private void open_formation(ActionEvent event) {
         try
                                {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/displayformation.fxml"));
                                    Parent root =loader.load();
                                    vol.getScene().setRoot(root);
                                    Scene scene = new Scene(root,816,458);
                                    Stage SecondaryStage=new Stage();
                                    SecondaryStage.setTitle("Afficher utilisateurs !");
                                    SecondaryStage.setScene(scene);
                                    SecondaryStage.show();
                                    
                                }
                                catch(Exception ex)
                                {
                                    System.out.println("err:"+ex);
                                }
    }

    @FXML
    private void open_vehicule(ActionEvent event) {
        try
                                {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/afficher_vh.fxml"));
                                    Parent root =loader.load();
                                    vol.getScene().setRoot(root);
                                    Scene scene = new Scene(root,816,458);
                                    Stage SecondaryStage=new Stage();
                                    SecondaryStage.setTitle("Afficher utilisateurs !");
                                    SecondaryStage.setScene(scene);
                                    SecondaryStage.show();
                                    
                                }
                                catch(Exception ex)
                                {
                                    System.out.println("err:"+ex);
                                }
    }
    }
