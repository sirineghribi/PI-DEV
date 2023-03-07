/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Genre;
import entity.Roles;
import entity.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author Zeineb Ben Mami
 */
public class ModifierUtilisateurController implements Initializable {

    int id=0;
    
   
    @FXML
    private TextField nomLB;
    @FXML
    private TextField prenomLB;
    @FXML
    private TextField emailLB;
    @FXML
    private ComboBox<Genre> combLB;
    @FXML
    private DatePicker dateLB;
    @FXML
    private TextField mdpLB;
    @FXML
    private Button Modifier;
    @FXML
    private AnchorPane glob;
    @FXML
    private Button logout;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ObservableList<Genre>list;
      list = FXCollections.observableArrayList(Genre.femme,Genre.homme);
       combLB.setItems(list); 
    }    

    
    public boolean valid_email(String s){
    return s.contains("@gmail.")||s.contains("@esprit.")||s.contains("@email.")||s.contains("@yahoo.");
    }
    
    @FXML
    private void Modifier(ActionEvent event) {
       
        if(nomLB.getText()==null||prenomLB.getText()==null||!valid_email(emailLB.getText())||(dateLB.getValue().compareTo((LocalDate.now())))>0){
         Alert alert = new Alert(Alert.AlertType.ERROR, "INVALID INPUT.");
        alert.showAndWait();
        return;
        }
              
         
         String nom = nomLB.getText();
            String Prenom =prenomLB.getText();
            String email = emailLB.getText();
            String mdp=mdpLB.getText();
            Date date =Date.valueOf(dateLB.getValue());
            Genre  genre = combLB.getValue();
            // int num = Integer.valueOf(numLB.getText());
             
            Utilisateur u = new Utilisateur(id, nom, Prenom, genre, email, mdp, date);
          // Utilisateur u = new Utilisateur(id, nom, Prenom, email, mdp, date, num, genre);
            UtilisateurService ut = new  UtilisateurService();
            ut.modifierU(u);
            
           
            
            
        
    
        
    }

    void initUser(Utilisateur User) {
     
    UtilisateurService service = new UtilisateurService();
    Utilisateur user = service.trouverUtilisateurParEmail(User.getEmail());
    if (user != null) {
        id = user.getId();
        nomLB.setText(user.getNom());
        prenomLB.setText(user.getPrenom());
        combLB.setValue(user.getGenre());
        emailLB.setText(user.getEmail());
        mdpLB.setText(user.getMdp());
        LocalDate date = LocalDate.parse((user.getDate_n().toString()));
        dateLB.setValue(date);
       //numLB.setText(String.valueOf(user.getNum()));
    }
    }

    @FXML
    private void logout(ActionEvent event) {
          Platform.exit();
    }

 
    
}
