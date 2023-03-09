/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Formation;
import entity.Utilisateur;
import static gui.ModifierUtilisateurController.user1;
import static gui.VolfrontController.user;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.FormationeServices;

/**
 * FXML Controller class
 *
 * @author abder
 */
public class FrontformationController implements Initializable {

    @FXML
    private TextField nbr;
    @FXML
    private TextField type;
    @FXML
    private DatePicker datedeb;

    static Utilisateur user;
    
    @FXML
    private Button accueil;
    @FXML
    private Button profil;
    @FXML
    private Button abonnement;
    @FXML
    private Button formation;
    @FXML
    private Button rec;
    @FXML
    private Button vehicule;
    @FXML
    private Button meteo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //setuser(user);
    }

    public void setuser(Utilisateur u) {
        this.user=u;
        if (new FormationeServices().checkformation(u)) {

            Formation f = new FormationeServices().getformation(u);
            datedeb.setValue(LocalDate.parse(f.getDate().toString()));
            nbr.setText(f.getNbrheur() + "");
            type.setText(f.getType().toString());
        }

    }

    @FXML
    private void open_accueil(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Volfront.fxml"));
            Parent root = loader.load();
            accueil.getScene().setRoot(root);
            VolfrontController rc = loader.getController();
            rc.setuser(user);
            Scene scene = new Scene(root, 893, 394);
            Stage SecondaryStage = new Stage();
            SecondaryStage.setTitle("Interplanetary");
            SecondaryStage.setScene(scene);
            SecondaryStage.show();
        } catch (Exception ex) {
            System.out.println("err:" + ex);
        }
    }

    @FXML
    private void open_profil(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ModifierUtilisateur.fxml"));
            Parent root = loader.load();
            accueil.getScene().setRoot(root);
            ModifierUtilisateurController controller = loader.getController();
            controller.initUser(user);
            Scene scene = new Scene(root);
            Stage SecondaryStage = new Stage();
            SecondaryStage.setTitle("Interplanetary");
            SecondaryStage.setScene(scene);
            SecondaryStage.show();
        } catch (Exception ex) {
            System.out.println("err:" + ex);
        }
    }

    @FXML
    private void open_abonnement(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Front_Abonnement.fxml"));
            Parent root = loader.load();
            accueil.getScene().setRoot(root);
            Front_AbonnementController arc = loader.getController();
            arc.setU(user);
            Scene scene = new Scene(root);
            Stage SecondaryStage = new Stage();
            SecondaryStage.setTitle("Interplanetary");
            SecondaryStage.setScene(scene);
            SecondaryStage.show();
        } catch (Exception ex) {
            System.out.println("err:" + ex);
        }
    }

    @FXML
    private void open_formation(ActionEvent event) {
    }

    @FXML
    private void open_reclamation(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AjoutReclamation.fxml"));
            Parent root = loader.load();
            accueil.getScene().setRoot(root);
            AjoutReclamationController arc = loader.getController();
            arc.setuser(user);
            Scene scene = new Scene(root);
            Stage SecondaryStage = new Stage();
            SecondaryStage.setTitle("Interplanetary");
            SecondaryStage.setScene(scene);
            SecondaryStage.show();
        } catch (Exception ex) {
            System.out.println("err:" + ex);
        }
    }

    @FXML
    private void open_vehicule(ActionEvent event) {
        try
                                {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/front_vh.fxml"));
                                    Parent root =loader.load();
                                    accueil.getScene().setRoot(root);
                                    Front_vhController rc =loader.getController();
                                    rc.setuser(user);
                                    
                                    Scene scene = new Scene(root,893,394);
                                    Stage SecondaryStage=new Stage();
                                    SecondaryStage.setTitle("Interplanetary");
                                    SecondaryStage.setScene(scene);
                                    SecondaryStage.show();
                                }
                                catch(Exception ex)
                                {
                                    System.out.println("err:"+ex);
                                }
    }

    @FXML
    private void meteo_onclick(ActionEvent event) {
       try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/forecast.fxml"));
            Parent root = loader.load();
            accueil.getScene().setRoot(root);
            ForecastController arc = loader.getController();
            arc.setuser(user);
            Scene scene = new Scene(root);
            Stage SecondaryStage = new Stage();
            SecondaryStage.setTitle("Interplanetary");
            SecondaryStage.setScene(scene);
            SecondaryStage.show();
        } catch (Exception ex) {
            System.out.println("err:" + ex);
        } 
    }
}
