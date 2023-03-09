/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import entity.Reclamation;
import entity.Typerec;
import entity.Utilisateur;
import static gui.ModifierUtilisateurController.user1;
import static gui.VolfrontController.user;
import services.ReclamationService;
import services.UtilisateurService;
import tools.MaConnection;
import java.sql.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sirin
 */
public class AjoutReclamationController implements Initializable {

    @FXML
    private Label ltype;
    @FXML
    private Label ldesc;
    @FXML
    private TextArea txtdesc;
    @FXML
    private Button btn;
    @FXML
    private ComboBox<Typerec> type;
    @FXML
    private Button consrec;

    static Utilisateur user;
    
    @FXML
    private Button accueil;
    @FXML
    private Button profil;
    @FXML
    private Button rec;
    @FXML
    private Button abonnement;
    @FXML
    private Button formation;
    @FXML
    private Button vehicule;

    /**
     * Initializes the controller class.
     */
    public void setuser(Utilisateur user) {
        this.user = user;
        System.out.println(user);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Typerec> opt = FXCollections.observableArrayList(Typerec.service, Typerec.technique, Typerec.autre);
        type.setPromptText("choix du type ");
        type.setItems(opt);

    }

    @FXML
    private void addRec(ActionEvent event) {

        Typerec tr = type.getValue();
        String description = txtdesc.getText();

        if (!(type == null || txtdesc.getText().equals(""))) {

            Reclamation r = new Reclamation(tr, description, user, "non traité");

            ReclamationService rs = new ReclamationService();
            rs.ajouter(r);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Ajout avec succées!"
                    + "Nous avons bien reçu votre reclamation chére client" + " \n Nous sommes sincèrement désolés pour ce désagrément. \nNous mettons tout"
                    + " en œuvre pour résoudre ce problème au plus vite et reviendrons vers vous par mail .Merci d’avance de votre patience.\n ");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error alert");
            alert.setHeaderText("Les champs de texte d'un formulaire ne doivent pas être null/vide");
            alert.showAndWait();
        }

    }

    @FXML
    private void consulteReclamation(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/Front_Reclamation.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Probleme:" + e);
        }

    }

    private void AllerAvis(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/Volfront.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Probleme:" + e);
        }

    }

    @FXML
    private void open_accueil(ActionEvent event) {
         try
                                {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Volfront.fxml"));
                                    Parent root =loader.load();
                                    accueil.getScene().setRoot(root);
                                    VolfrontController rc =loader.getController();
                                    rc.setuser(user);
                                    Scene scene = new Scene(root,893,394);
                                    Stage SecondaryStage=new Stage();
                                    SecondaryStage.setTitle("Reserver Vol !");
                                    SecondaryStage.setScene(scene);
                                    SecondaryStage.show();
                                }
                                catch(Exception ex)
                                {
                                    System.out.println("err:"+ex);
                                }
    }

    @FXML
    private void open_profil(ActionEvent event) {
        try
                                {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ModifierUtilisateur.fxml"));
                                    Parent root =loader.load();
                                    accueil.getScene().setRoot(root);
                                    ModifierUtilisateurController controller = loader.getController();
                                    controller.initUser(user);
                                    Scene scene = new Scene(root);
                                    Stage SecondaryStage=new Stage();
                                    SecondaryStage.setTitle("Interplanetary !");
                                    SecondaryStage.setScene(scene);
                                    SecondaryStage.show();
                                }
                                catch(Exception ex)
                                {
                                    System.out.println("err:"+ex);
                                }
    }

    @FXML
    private void open_reclamation(ActionEvent event) {
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
