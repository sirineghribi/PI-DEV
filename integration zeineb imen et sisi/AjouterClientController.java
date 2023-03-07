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
import javafx.stage.Stage;
import services.UtilisateurService;
import tools.EmailSender;

/**
 * FXML Controller class
 *
 * @author Zeineb Ben Mami
 */
public class AjouterClientController implements Initializable {

    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtprenom;
    @FXML
    private TextField txtemail;
    @FXML
    private TextField txtmdp;
    @FXML
    private ComboBox<Genre> comb;
    @FXML
    private DatePicker date_n;
    @FXML
    private Button inscription;
    @FXML
    private TextField txtnum;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      ObservableList<Genre>list;
      list = FXCollections.observableArrayList(Genre.femme,Genre.homme);
       comb.setItems(list); 
    }    

    public boolean valid_email(String s){
    return s.contains("@gmail.")||s.contains("@esprit.")||s.contains("@email.")||s.contains("@yahoo.");
    }
    
    @FXML
    private void inscription(ActionEvent event) {
        
     if (txtnom.getText() == null || txtprenom.getText() == null ||txtnum.getText()==null|| !valid_email(txtemail.getText()) || (date_n.getValue().compareTo((LocalDate.now()))) > 0) {
        Alert alert = new Alert(Alert.AlertType.ERROR, "INVALID INPUT.");
        alert.showAndWait();
        return;
    }

    String nom = txtnom.getText();
    String prenom = txtprenom.getText();
    String email = txtemail.getText();
    String mdp = txtmdp.getText();
    Date date = Date.valueOf(date_n.getValue());
    Genre genre = comb.getValue();
    int num = Integer.valueOf(txtnum.getText());
    
   
    Utilisateur u = new Utilisateur(nom, prenom, genre, email, mdp, date, Roles.C, num);
    UtilisateurService ut = new UtilisateurService();
    ut.ajouter(u);

    // Envoi de l'e-mail
    String to = email;
    String subject = "Inscription réussie";
    String body = "Bonjour " + prenom + ",\n\nFélicitations, vous êtes maintenant inscrit sur notre plateforme.\n\nCordialement,\nL'équipe de notre plateforme";

    EmailSender.sendEmail(to, subject, body);

    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 730, 290);
        Stage stage = new Stage();
        stage.setTitle("LOGIN");
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    

   
}
   
}
