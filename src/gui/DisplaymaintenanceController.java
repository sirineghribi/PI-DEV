/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Formation;
import entity.Maintenance;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                MaintenanceServices mss = new MaintenanceServices();
                
        ObservableList<Maintenance> liste=FXCollections.observableArrayList(mss.getAll());
        tablemaint.setItems(liste);
        idv.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(String.valueOf(cell.getValue().getVehicule().get_id_vehicule()));
            return s;
        });
        statusmaint.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(String.valueOf(cell.getValue().getStatus()));
            return s;
        });
timemaint.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(String.valueOf(cell.getValue().getDuree()));
            return s;
        });
           coutmaint.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(String.valueOf(cell.getValue().getCout()));
            return s;
        });
                categoryveh.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(String.valueOf(cell.getValue().getVehicule().get_cat_vehicule()));
            return s;
        });
                
    }    
    
}
