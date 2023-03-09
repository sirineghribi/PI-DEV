/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Roles;
import entity.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.UtilisateurService;
import tools.EmailSender;
import tools.MaConnection;

/**
 * FXML Controller class
 *
 * @author Zeineb Ben Mami
 */
public class LoginController implements Initializable {
Connection cnx;
public PreparedStatement st;
public ResultSet result;

    @FXML
    private Button Login;
    @FXML
    private TextField txtem;
    @FXML
    private TextField txtmp;
    @FXML
    private ImageView glob;
    @FXML
    private Button insc;
    @FXML
    private Button mdpoub;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cnx= MaConnection.getInstance().getCnx();
    }    

    @FXML
    private void Login(ActionEvent event) {
        
        
        
        String email = txtem.getText();
    String mdp = txtmp.getText();
    String sql = "select * from utilisateur where email=? and mdp=?";
    try {
        PreparedStatement st = cnx.prepareStatement(sql);
        st.setString(1, email);
        st.setString(2, mdp);
        ResultSet result = st.executeQuery();
        if (result.next()) {
            Utilisateur user = new Utilisateur(result.getInt("id"), result.getString("nom"),
                    result.getString("prenom"), Utilisateur.stringTogenre(result.getString("genre")),
                    result.getString("email"), result.getString("mdp"), Utilisateur.stringTorole(result.getString("type")),
                    result.getDate("date_n"));
            if (user.getType() == Roles.C) {
                try {
                    Stage stageE = (Stage)insc.getScene().getWindow();
                    stageE.close();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierUtilisateur.fxml"));
                    Parent root = loader.load();
                    ModifierUtilisateurController controller = loader.getController();
                    controller.initUser(user);
                   
                    Scene scene = new Scene(root, 893,394);
                    Stage stage = (Stage) glob.getScene().getWindow();
                    stage.setTitle("MY PROFILE");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            } else if (user.getType() == Roles.A) {
                try {
                    Stage stageE = (Stage)insc.getScene().getWindow();
                   stageE.close();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherUtilisateur.fxml"));
                    Parent root = loader.load();
                    //insc.getScene().setRoot(root);
                    Scene scene = new Scene(root, 816,458);
                    Stage stage = new Stage();
                    stage.setTitle("USERS");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Email ou Mot de passe incorrect!");
            alert.showAndWait();
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
      /*  String email = txtem.getText();
    String mdp = txtmp.getText();
    String sql = "select * from utilisateur where email=? and mdp=?";
    try {
        PreparedStatement st = cnx.prepareStatement(sql);
        st.setString(1, email);
        st.setString(2, mdp);
        ResultSet result = st.executeQuery();
        if (result.next()) {
            Utilisateur user = new Utilisateur(result.getInt("id"), result.getString("nom"),
                    result.getString("prenom"), Utilisateur.stringTogenre(result.getString("genre")),
                    result.getString("email"), result.getString("mdp"), Utilisateur.stringTorole(result.getString("type")),
                    result.getDate("date_n"));
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierUtilisateur.fxml"));
                Parent root = loader.load();
                ModifierUtilisateurController controller = loader.getController();
                controller.initUser(user);
                Scene scene = new Scene(root);
                Stage stage = (Stage) glob.getScene().getWindow();
                stage.setTitle("MY PROFILE");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Email ou Mot de passe incorrect!");
            alert.showAndWait();
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }*/
       /* String email=txtem.getText();
        String mdp=txtmp.getText();
        String sql="select email, mdp from utilisateur";
    try {
        
        st=cnx.prepareStatement(sql);
        result=st.executeQuery();
        if(result.next()){
        if(email.equals(result.getString("email"))&&mdp.equals(result.getString("mdp"))){
            
            
       
            try {
                Utilisateur user = new Utilisateur();
               
                 FXMLLoader loader = new   FXMLLoader(getClass().getResource("ModifierUtilisateur.fxml"));
                Parent root = loader.load();
                 ModifierUtilisateurController controller = loader.getController();
                controller.initUser(user);
                 Scene scene = new Scene(root);
                Stage stage = (Stage)glob.getScene().getWindow();
                stage.setTitle("Profile USER");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        
        
        
        
        
        }else{ Alert alert = new Alert(Alert.AlertType.ERROR, "Email ou Mot de passe incorrect!");
        alert.showAndWait();}
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }*/
        
    }

    @FXML
    private void insc(ActionEvent event) {
          try {
            FXMLLoader loader = new   FXMLLoader(getClass().getResource("AjouterClient.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("INSCRIPTION");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void mdpOub(ActionEvent event) {
         String email = txtem.getText();

// Générer un nombre aléatoire entre 1000 et 9999
int code = (int) (Math.random() * (9999 - 1000 + 1)) + 1000;

// Envoyer le code à l'utilisateur par e-mail
String subject = "Récupération de mot de passe";
String body = "Voici votre code de récupération de mot de passe : " + code;
EmailSender.sendEmail(email, subject, body);

// Demander à l'utilisateur d'entrer le code
TextInputDialog dialog = new TextInputDialog();
dialog.setTitle("Récupération de mot de passe");
dialog.setHeaderText(null);
dialog.setContentText("Un code de récupération de mot de passe a été envoyé à votre adresse e-mail. Veuillez entrer le code ci-dessous :");

Optional<String> result = dialog.showAndWait();

if (result.isPresent()) {
    String inputCode = result.get();

    if (inputCode.equals(Integer.toString(code))) {
        // Si le code est correct, vérifier le type de l'utilisateur
        String sql = "select * from utilisateur where email=?";
        try {
            PreparedStatement st = cnx.prepareStatement(sql);
            st.setString(1, email);
            ResultSet resultUser = st.executeQuery();
            if (resultUser.next()) {
                Utilisateur user = new Utilisateur(resultUser.getInt("id"), resultUser.getString("nom"),
                        resultUser.getString("prenom"), Utilisateur.stringTogenre(resultUser.getString("genre")),
                        resultUser.getString("email"), resultUser.getString("mdp"), Utilisateur.stringTorole(resultUser.getString("type")),
                        resultUser.getDate("date_n"));
                if (user.getType() == Roles.C) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierUtilisateur.fxml"));
                        Parent root = loader.load();
                        ModifierUtilisateurController controller = loader.getController();
                        controller.initUser(user);
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) glob.getScene().getWindow();
                        stage.setTitle("MY PROFILE");
                        stage.setScene(scene);
                        stage.show();
                        
                         Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("REMINDER");
                        alert.setHeaderText(null);
                        alert.setContentText("n'oubliez pas de changer votre mot de passe!");

                        alert.showAndWait();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                } else if (user.getType() == Roles.A) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherUtilisateur.fxml"));
                        Parent root = loader.load();
                        Scene scene = new Scene(root, 800, 400);
                        Stage stage = new Stage();
                        stage.setTitle("USERS");
                        stage.setScene(scene);
                        stage.show();
                        
                       
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Utilisateur non trouvé!");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    } else {
        // Sinon, afficher un message d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Le code que vous avez entré est incorrect.");

        alert.showAndWait();
    }
}
    }

    
}
