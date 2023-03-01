/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import tn.esprit.entity.CategorieVehicule;
import tn.esprit.services.CategorieVehiculeServices;
import tn.esprit.services.VehiculeServices;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class Mod_catController implements Initializable {

    @FXML
    private TextField tf_lieu;
    private CategorieVehicule ravselect;
    
    @FXML
    private Button btnm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    void initcatvh(CategorieVehicule a) {
        this.ravselect = a;
    
     tf_lieu.setText(a.getLieu());
    
    btnm.setOnAction(event -> {
       String lieu = tf_lieu.getText();
       CategorieVehicule a1 = new CategorieVehicule(a.get_id_cat(), lieu);
       CategorieVehiculeServices av = new CategorieVehiculeServices();
      av.modifierCat(lieu, a1);
                            });
    }

    @FXML
    private void modifier_onclick(ActionEvent event) {
    }
}
