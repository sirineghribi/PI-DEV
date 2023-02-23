package gui;

import entity.Maintenance;
import entity.Vehicule;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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

public class AjouterMaintenanceController implements Initializable {

    @FXML
    private TextField duree;
    @FXML
    private TextField cout;
    @FXML
    private Button addmaintenance;
    @FXML
    private CheckBox prete;
    @FXML
    private CheckBox enAttente;
    @FXML
    private Button affichermai;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addmaintenance.setOnAction(this::ajoutermaintenance);
    }

    private void ajoutermaintenance(ActionEvent event) {
        try {
            boolean status = false;
            if (prete.isSelected()) {
                status = true;
            }
            Float dure = Float.valueOf(duree.getText());
            Float cou = Float.valueOf(cout.getText());
            Vehicule v = new Vehicule(4, "cargo", 14, 100, 15, true);
            Maintenance m = new Maintenance(v, status, dure, cou);
            MaintenanceServices ms = new MaintenanceServices();
            ms.ajouter(m);
        } catch (NumberFormatException e) {
            // Handle the exception here, e.g. show an error message to the user
        }
    }
private void afficherermai(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/displaymaintenance.fxml"));
    Parent root = loader.load();
    DisplaymaintenanceController dm = loader.getController();
    
    Scene scene = new Scene(root);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();
}


}
