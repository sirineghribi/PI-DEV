/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Maintenance;
import entity.Vehicule;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import services.MaintenanceServices;

/**
 * FXML Controller class
 *
 * @author abder
 */

public class AjouterMaintenanceController implements Initializable {

    private Vehicule V=new Vehicule();
    @FXML
    private Button ajoutermaintenance;
    @FXML
    private RadioButton prete;
    @FXML
    private TextField cout;
    @FXML
    private TextField duree;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setV(Vehicule V) {
        this.V = V;
    }

    @FXML
    private void ajoutermaintenance(ActionEvent event) {
        
        V=new Vehicule (5, 45);
         try {
            boolean status = false;
            if (prete.isSelected()) {
                status = true;
            }
            Float dure = Float.valueOf(duree.getText());
            Float cou = Float.valueOf(cout.getText());

            Maintenance M = new Maintenance(V, status, dure, cou);
            MaintenanceServices ms = new MaintenanceServices();
            ms.ajouter(M);
        } catch (NumberFormatException e) {
            // Handle the exception here, e.g. show an error message to the user
        }
        
        
    }
    
    
}
