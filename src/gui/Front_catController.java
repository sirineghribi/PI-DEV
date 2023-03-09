/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import entity.CategorieVehicule;
import services.CategorieVehiculeServices;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class Front_catController implements Initializable {

    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        affichage(new CategorieVehiculeServices().getAll());
        
    
    } 
    
    public void affichage(List <CategorieVehicule> tas){
        int r = 1;
        grid.add(new Label(" Type categorie"), 0, 0);
        grid.add(new Label(" lieu"), 1, 0);
        
        for (CategorieVehicule ta : tas) {
            grid.add(new Label(" " + ta.get_nom_cat()), 0, r);
            grid.add(new Label(" " + ta.getLieu()), 1, r);
            r++;
    }
    
}
}
