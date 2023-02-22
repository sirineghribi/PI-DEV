/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Reservation;
import entity.Utilisateur;
import entity.Vol;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import services.ReservationService;


/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class ReservationController implements Initializable {

    @FXML
    private ListView<Reservation> list_reservation;
    private Utilisateur user;

    public void setUser(Utilisateur user) {
        this.user = user;
        System.out.println(user); 
         ReservationService rs = new ReservationService();
      // ObservableList<Reservation> liste=FXCollections.observableArrayList(rs.findById(user.getId()));
        list_reservation.getItems().addAll(rs.findById(user.getId()));
    }
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
        
        
        
    }    
    
}
