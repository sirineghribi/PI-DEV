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
    
    private Utilisateur UserSelectionne;
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
    private Button Afficher;
    @FXML
    private AnchorPane glob;

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
            
            Utilisateur u = new Utilisateur(id, nom, Prenom, genre, email, mdp, date);
            UtilisateurService ut = new  UtilisateurService();
            ut.modifier(u);
            
           
            
            
        
    
        
    }

    void initUser(Utilisateur User) {
        this.UserSelectionne = User;
    id=User.getId();
    nomLB.setText(User.getNom());
    prenomLB.setText(User.getPrenom());
    combLB.setValue(User.getGenre());
   //dateLB.setDatePicker(User.getDate_n().toString());
   emailLB.setText(User.getEmail());
   mdpLB.setText(User.getMdp());
   LocalDate date = LocalDate.parse((User.getDate_n().toString()));
   dateLB.setValue(date); 
    
    }

    @FXML
    private void Afficher(ActionEvent event) {
        try {
            FXMLLoader loader = new   FXMLLoader(getClass().getResource("AfficherUtilisateur.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) glob.getScene().getWindow();
            stage.setTitle("INFORMATION USER");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
