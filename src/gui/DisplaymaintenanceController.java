/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Formation;
import entity.Maintenance;
import entity.Vehicule;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import services.FormationeServices;
import services.MaintenanceServices;

/**
 * FXML Controller class
 *
 * @author abder
 */
public class DisplaymaintenanceController implements Initializable {

    @FXML
    private TableView<Maintenance> tablemaint;
    @FXML
    private TableColumn<Maintenance, String> idv;
    @FXML
    private TableColumn<Maintenance, String> statusmaint;
    @FXML
    private TableColumn<Maintenance, String> timemaint;
    @FXML
    private TableColumn<Maintenance, String> coutmaint;
    @FXML
    private TableColumn<Maintenance, String> categoryveh;
    @FXML
    private Button maintajout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MaintenanceServices mss = new MaintenanceServices();

        ObservableList<Maintenance> liste = FXCollections.observableArrayList(mss.getAll());
        tablemaint.setItems(liste);
        idv.setCellValueFactory(cell -> {
            StringProperty s = new SimpleStringProperty();
            s.set(String.valueOf(cell.getValue().getVehicule().get_id_vehicule()));
            return s;
        });
        statusmaint.setCellValueFactory(cell -> {
            StringProperty s = new SimpleStringProperty();
            s.set(String.valueOf(cell.getValue().getStatus()));
            return s;
        });
        timemaint.setCellValueFactory(cell -> {
            StringProperty s = new SimpleStringProperty();
            s.set(String.valueOf(cell.getValue().getDuree()));
            return s;
        });
        coutmaint.setCellValueFactory(cell -> {
            StringProperty s = new SimpleStringProperty();
            s.set(String.valueOf(cell.getValue().getCout()));
            return s;
        });
        categoryveh.setCellValueFactory(cell -> {
            StringProperty s = new SimpleStringProperty();
            s.set(String.valueOf(cell.getValue().getVehicule().get_cat_vehicule()));
            return s;
        });

    }

    @FXML
    private void addmaintenance(ActionEvent event) {

        Maintenance ta = tablemaint.getSelectionModel().selectedItemProperty().get();
        if (ta != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierMaintenance.fxml"));
                Parent root = loader.load();
                ModifierMaintenanceController ac = loader.getController();
                ac.setM(ta);
                Stage s = (Stage) (maintajout.getScene().getWindow());
                s.close();
                Scene scene = new Scene(root);
                Stage SecondaryStage = new Stage();
                SecondaryStage.setX(0);
                SecondaryStage.setY(0);
                SecondaryStage.setTitle("modifier maintenance");
                SecondaryStage.setScene(scene);
                SecondaryStage.show();
            } catch (Exception ex) {
                System.out.println("err:" + ex);
            }
        } else {
            alert("Selection not found", "Please select a row");
        }
    }

    private void alert(String a, String b) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(a);
        alert.setHeaderText(null);
        alert.setContentText(b);
        alert.show();
    }
}
