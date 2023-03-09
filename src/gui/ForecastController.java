/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Utilisateur;
import entity.WeatherData;
import static gui.FrontformationController.user;
import static gui.VolfrontController.user;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.MaintenanceServices;

/**
 * FXML Controller class
 *
 * @author abder
 */
public class ForecastController implements Initializable {

    @FXML
    private TextField hum;
    @FXML
    private TextField press;
    @FXML
    private TextField lat;
    @FXML
    private TextField lon;
    @FXML
    private TextField desc;
    @FXML
    private TextField country;
    @FXML
    private TextField temp;
    @FXML
    private Button accueil;
    @FXML
    private Button profil;
    @FXML
    private Button abonnement;
    @FXML
    private Button rec;
    @FXML
    private Button formation;
    @FXML
    private Button vehicule;
    
    Utilisateur user ;
    
    /**
     * Initializes the controller class.
     */
    
    public void setuser(Utilisateur u)
    {
        this.user=u;
    }
    public void initialize(URL url, ResourceBundle rb) {
        
        WeatherData w = new MaintenanceServices().forecast();
        desc.setText(w.getDescription());
        hum.setText(w.getHumidity()+"");
        lon.setText(w.getLon()+"");
        lat.setText(w.getLat()+"");
        press.setText(w.getPressure()+"");
        country.setText(w.getName()+"");
        temp.setText(w.getTemp()+"");
        
        
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
    private void open_formation(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Frontformation.fxml"));
            Parent root = loader.load();
            accueil.getScene().setRoot(root);
            FrontformationController arc = loader.getController();
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
    
}
