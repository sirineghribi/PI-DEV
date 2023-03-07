/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Vehicule;
import entity.Vol;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.VehiculeServices;
import services.VolService;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class ModifierVolController implements Initializable {

    @FXML
    private Button modifier_btn;
    @FXML
    private ComboBox<Integer> vehicule_choixm;
    @FXML
    private TextField destination_txtm;
    @FXML
    private TextField prix_txtm;
    @FXML
    private DatePicker date_choixm;

    private Vol vol;
    @FXML
    private Button accueil;

    public void setVol(Vol vol) {
        this.vol = vol;
        System.out.println(vol);
        destination_txtm.setText(vol.getDestination());
        prix_txtm.setText("" + vol.getPrix());
        vehicule_choixm.setValue(vol.getMt().get_id_vehicule());
        LocalDate date = LocalDate.parse("" + vol.getDate());
        date_choixm.setValue(date);

        modifier_btn.setOnAction((ActionEvent event) -> {

            if (destination_txtm.getText().equals("") || date_choixm.getValue() == null || vehicule_choixm.getSelectionModel().isEmpty() || prix_txtm.getText().trim().isEmpty()) {
                afficher_alerte("veuillez remplir tous les champs !");
            }
            if (Float.valueOf(prix_txtm.getText()) < 0.0f) {
                afficher_alerte("Prix doit etre positif !");
            } else {

                String destination = destination_txtm.getText();
                float prix = Float.valueOf(prix_txtm.getText());
                int v = Integer.valueOf(vehicule_choixm.getValue().toString());
                VehiculeServices vs = new VehiculeServices();
                Vehicule vehicule = vs.findById(v).get(0);
                Date datee = Date.valueOf(date_choixm.getValue());
                System.out.println(vol.getId_v());
                VolService volservice = new VolService();
                Vol vol1 = new Vol(vol.getId_v(), destination, "planifié", prix, datee, vehicule);
                System.out.println(vol1);
                volservice.modifier(vol1);
                afficher_info();
            }
        });

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VolService volservice = new VolService();
        List<Integer> l = volservice.id_vehicule_list();
        vehicule_choixm.setItems(observableArrayList(l));
        modifier_btn.setStyle("-fx-text-fill: white;");
        accueil.setStyle("-fx-text-fill: white;");
    }

    private void afficher_alerte(String a) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite lors de la modification.");
        alert.setContentText(a);
        alert.showAndWait();
        return;
    }

    private void afficher_info() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succée");
        alert.setHeaderText("Modification du vol.");
        alert.setContentText("Modification avec succée !");
        alert.showAndWait();
        return;
    }

    @FXML
    private void accueil(ActionEvent event) {
        try {
            /* Stage stageE = (Stage)ajouter_btn.getScene().getWindow();
            stageE.close();*/
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Vols.fxml"));
            Parent root = loader.load();
            modifier_btn.getScene().setRoot(root);
            Scene scene = new Scene(root);
            Stage SecondaryStage = new Stage();
            SecondaryStage.setTitle("Les vols !");
            SecondaryStage.setScene(scene);
            SecondaryStage.show();
            

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
         
    }

}
