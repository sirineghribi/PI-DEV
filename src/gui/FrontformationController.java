/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Formation;
import entity.Utilisateur;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import services.FormationeServices;

/**
 * FXML Controller class
 *
 * @author abder
 */
public class FrontformationController implements Initializable {

    @FXML
    private TextField nbr;
    @FXML
    private TextField type;
    @FXML
    private DatePicker datedeb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setuser(new Utilisateur(1, "nom", "prenom", "genre", "email", "mdp", Date.valueOf(LocalDate.now())));
    }

    public void setuser(Utilisateur u) {
        if (new FormationeServices().checkformation(u)) {
            
            Formation f = new FormationeServices().getformation(u);
            datedeb.setValue(LocalDate.parse(f.getDate().toString()));
            nbr.setText(f.getNbrheur()+"");
            type.setText(f.getType().toString());
        }
        
    }
}
