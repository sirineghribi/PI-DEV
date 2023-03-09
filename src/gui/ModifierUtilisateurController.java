/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Genre;
import entity.Roles;
import entity.Utilisateur;
import static gui.VolfrontController.user;
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
    @FXML
    private TextField numLB;
    @FXML
    private Button accueil;
   
    
    static Utilisateur user1 ;
    @FXML
    private Button rec_btn;
    @FXML
    private Button abonnement;
    @FXML
    private Button formation;
    @FXML
    private Button vehicule;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          ObservableList<Genre>list;
      list = FXCollections.observableArrayList(Genre.femme,Genre.homme);
       combLB.setItems(list); 
    }    

    public static void setUser1(Utilisateur user1) {
        ModifierUtilisateurController.user1 = user1;
    }

    
    public boolean valid_email(String s){
    return s.contains("@gmail.")||s.contains("@esprit.")||s.contains("@email.")||s.contains("@yahoo.");
    }
    
    @FXML
    private void Modifier(ActionEvent event) {
       
        if(nomLB.getText()==null||prenomLB.getText()==null||emailLB.getText()==null||mdpLB.getText()==null||dateLB.getValue()==null){
         Alert alert = new Alert(Alert.AlertType.ERROR, "INVALID INPUT.");
        alert.showAndWait();
        return;
       
       }
       
       
         if(nomLB.getText().isEmpty()){
         Alert alert = new Alert(Alert.AlertType.ERROR, " NOM INVALIDE.");
        alert.showAndWait();
        return;
       
       }
         
         
          if(prenomLB.getText().isEmpty()){
         Alert alert = new Alert(Alert.AlertType.ERROR, "PRENOM INVALIDE.");
        alert.showAndWait();
        return;
       
       }
       
        if(!valid_email(emailLB.getText())){
         Alert alert = new Alert(Alert.AlertType.ERROR, "EMAIL INVALIDE.");
        alert.showAndWait();
        return;
       
       }
       
        if((dateLB.getValue().compareTo((LocalDate.now())))>0||dateLB.getValue()==null ){
         Alert alert = new Alert(Alert.AlertType.ERROR, "DATE DE NAISSANCE INVALIDE.");
        alert.showAndWait();
        return;
       
       }
       
       
       
         if(mdpLB.getText().isEmpty()){
         Alert alert = new Alert(Alert.AlertType.ERROR, " MOT DE PASSE INVALIDE.");
        alert.showAndWait();
        return;
       
       }
              
         
         String nom = nomLB.getText();
            String Prenom =prenomLB.getText();
            String email = emailLB.getText();
            String mdp=mdpLB.getText();
            Date date =Date.valueOf(dateLB.getValue());
            Genre  genre = combLB.getValue();
             int num = Integer.valueOf(numLB.getText());
             
            //Utilisateur u = new Utilisateur(id, nom, Prenom, genre, email, mdp, date);
           Utilisateur u = new Utilisateur(id, nom, Prenom, email, mdp, date, num, genre);
            UtilisateurService ut = new  UtilisateurService();
            ut.modifierU(u);
            info("Cordonnées modifiées ! ","Modification avec succée");
           
            
            
        
    
        
    }
 private void info(String a, String b) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(a);
        alert.setHeaderText(null);
        alert.setContentText(b);
        alert.show();
    }
    void initUser(Utilisateur User) {
     
    UtilisateurService service = new UtilisateurService();
    Utilisateur user = service.trouverUtilisateurParEmail(User.getEmail());
        setUser1(user);
    if (user != null) {
        id = user.getId();
        nomLB.setText(user.getNom());
        prenomLB.setText(user.getPrenom());
        combLB.setValue(user.getGenre());
        emailLB.setText(user.getEmail());
        mdpLB.setText(user.getMdp());
        LocalDate date = LocalDate.parse((user.getDate_n().toString()));
        dateLB.setValue(date);
        numLB.setText(String.valueOf(user.getNum()));
    }
    }

    @FXML
    private void logout(ActionEvent event) {
          try
                                {   Stage stageE = (Stage)logout.getScene().getWindow();
                                    stageE.close();
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Login.fxml"));
                                    Parent root =loader.load();
                                    Scene scene = new Scene(root);
                                    Stage SecondaryStage=new Stage();
                                    SecondaryStage.setTitle("InterPlanetary");
                                    SecondaryStage.setScene(scene);
                                    SecondaryStage.show();
                                }
                                catch(Exception ex)
                                {
                                    System.out.println("err:"+ex);
                                }
    }

    @FXML
    private void open_accueil(ActionEvent event) {
         try
                                {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Volfront.fxml"));
                                    Parent root =loader.load();
                                    accueil.getScene().setRoot(root);
                                    VolfrontController rc =loader.getController();
                                    rc.setuser(user1);
                                    
                                    Scene scene = new Scene(root,893,394);
                                    Stage SecondaryStage=new Stage();
                                    SecondaryStage.setTitle("Interplanetary");
                                    SecondaryStage.setScene(scene);
                                    SecondaryStage.show();
                                }
                                catch(Exception ex)
                                {
                                    System.out.println("err:"+ex);
                                }
    }

    @FXML
    private void open_reclamation(ActionEvent event) {
        try
                                {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AjoutReclamation.fxml"));
                                    Parent root =loader.load();
                                    accueil.getScene().setRoot(root);
                                    
                                    AjoutReclamationController arc=loader.getController();
                                    arc.setuser(user1);
                                    Scene scene = new Scene(root);
                                    Stage SecondaryStage=new Stage();
                                    SecondaryStage.setTitle("Interplanetary");
                                    SecondaryStage.setScene(scene);
                                    SecondaryStage.show();
                                }
                                catch(Exception ex)
                                {
                                    System.out.println("err:"+ex);
                                }
    }

    @FXML
    private void open_abonnement(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Front_Abonnement.fxml"));
            Parent root = loader.load();
            accueil.getScene().setRoot(root);
            Front_AbonnementController arc = loader.getController();
            arc.setU(user1);
            Scene scene = new Scene(root);
            Stage SecondaryStage = new Stage();
            SecondaryStage.setTitle("Interplanetary");
            SecondaryStage.setScene(scene);
            SecondaryStage.show();
        } catch (Exception ex) {
            System.out.println("err:" + ex);
        }
    }

    @FXML
    private void open_formation(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Frontformation.fxml"));
            Parent root = loader.load();
            accueil.getScene().setRoot(root);
            FrontformationController arc = loader.getController();
            arc.setuser(user1);
            Scene scene = new Scene(root);
            Stage SecondaryStage = new Stage();
            SecondaryStage.setTitle("Interplanetary");
            SecondaryStage.setScene(scene);
            SecondaryStage.show();
        } catch (Exception ex) {
            System.out.println("err:" + ex);
        }
    }

    @FXML
    private void open_vehicule(ActionEvent event) {
        try
                                {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/front_vh.fxml"));
                                    Parent root =loader.load();
                                    accueil.getScene().setRoot(root);
                                    Front_vhController rc =loader.getController();
                                    rc.setuser(user1);
                                    
                                    Scene scene = new Scene(root,893,394);
                                    Stage SecondaryStage=new Stage();
                                    SecondaryStage.setTitle("Interplanetary");
                                    SecondaryStage.setScene(scene);
                                    SecondaryStage.show();
                                }
                                catch(Exception ex)
                                {
                                    System.out.println("err:"+ex);
                                }
    }

 
    
}
