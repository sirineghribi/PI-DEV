/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import tn.esprit.entity.CategorieVehicule;
import tn.esprit.entity.Vehicule;
import tn.esprit.services.VehiculeServices;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class Mod_vhController implements Initializable {

    @FXML
    private Button btnm;
    private Vehicule ravselect;
    @FXML
    private TextField tf_nom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modifier_onclick(ActionEvent event) {
    }

    void initvh(Vehicule a) {
        this.ravselect = a;
    
     tf_nom.setText(a.getNom_vh());
    
    btnm.setOnAction(event -> {
       String nom_vh = tf_nom.getText();
       Vehicule a1 = new Vehicule(a.get_id_vehicule(), nom_vh);
       VehiculeServices av = new VehiculeServices();
      av.modifierVehiculeparNom(nom_vh, a1);
                            });
    }
  

}