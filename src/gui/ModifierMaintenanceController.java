package gui;

import entity.Maintenance;
import entity.Vehicule;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.MaintenanceServices;

public class ModifierMaintenanceController implements Initializable {

    @FXML
    private TextField duree;
    @FXML
    private TextField cout;
    @FXML
    private Button addmaintenance;
    @FXML
    private CheckBox prete;
    @FXML
    private Button affichermai;
    Maintenance M = new Maintenance();
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addmaintenance.setOnAction(this::modifiermaintenance);
    }

    private void modifiermaintenance(ActionEvent event) {
        try {
            boolean status = false;
            if (prete.isSelected()) {
                status = true;
            }
            Float dure = Float.valueOf(duree.getText());
            Float cou = Float.valueOf(cout.getText());

            M.setDuree(dure);
            M.setStatus(status);
            M.setCout(cou);
            MaintenanceServices ms = new MaintenanceServices();
            ms.modifier(M);
        } catch (NumberFormatException e) {
            // Handle the exception here, e.g. show an error message to the user
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("displaymaintenance.fxml"));
            Parent root = loader.load();
            DisplaymaintenanceController dm = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = (Stage) (cout.getScene().getWindow());
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /*    @FXML
    private void afficherermai(ActionEvent event)  {
    
    try {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("displaymaintenance.fxml"));
    Parent root = loader.load();
    DisplaymaintenanceController dm = loader.getController();
    Scene scene = new Scene(root);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();
    } catch (IOException ex) {
    System.out.println(ex.getMessage());
    }
    
     */
//}
    @FXML
    private void affichermai(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("displaymaintenance.fxml"));
            Parent root = loader.load();
            DisplaymaintenanceController dm = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = (Stage) (cout.getScene().getWindow());
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void setM(Maintenance m) {
        this.M = m;
        cout.setText(M.getCout() + "");
        duree.setText(M.getDuree() + "");

        prete.setSelected(M.getStatus());
    }

    @FXML
    private void modifermaintenance(ActionEvent event) {
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
