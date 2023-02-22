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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.UtilisateurService;
import tools.MaConnection;

/**
 * FXML Controller class
 *
 * @author Zeineb Ben Mami
 */
public class AfficherUtilisateurController implements Initializable {

    @FXML
    private TableColumn<Utilisateur,String> idCol;
    @FXML
    private TableColumn<Utilisateur, String> nomCol;
    @FXML
    private TableColumn<Utilisateur, String> prenomCol;
    @FXML
    private TableColumn<Utilisateur, String> dateCol;
    @FXML
    private TableColumn<Utilisateur, String> genreCol;
    @FXML
    private TableColumn<Utilisateur, String> emailCol;
    @FXML
    private TableColumn<Utilisateur, String> mdpCol;
    @FXML
    private TableColumn<Utilisateur, String> RoleCol;
    @FXML
    private TableView<Utilisateur> userTable;
    @FXML
    private Button Modifier;
    @FXML
    private Button Supprimer;
    @FXML
    private TextField idT;
    @FXML
    private AnchorPane globale;
    @FXML
    private Button Ajouter;
    @FXML
    private Button carte;
   

   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        loadDate();
        
       
    }    

    private void loadDate() {
        idCol.setCellValueFactory(cell -> {
    IntegerProperty idProp = new SimpleIntegerProperty(cell.getValue().getId());
    return idProp.asString();
     });
        
        nomCol.setCellValueFactory(cell->{
            StringProperty s=new SimpleStringProperty();
            s.set(cell.getValue().getNom());
            return s;
        });
        
        prenomCol.setCellValueFactory(cell->{
            StringProperty s=new SimpleStringProperty();
            s.set(cell.getValue().getPrenom());
            return s;
        });
        
        genreCol.setCellValueFactory(cell -> {
    StringProperty genre_G = new SimpleStringProperty(cell.getValue().getGenre().toString());
    return genre_G;
});
        
        
        
         dateCol.setCellValueFactory(cell->{
            StringProperty s=new SimpleStringProperty();
            s.set(cell.getValue().getDate_n().toString());
            return s;
        });
        
        
        emailCol.setCellValueFactory(cell->{
            StringProperty s=new SimpleStringProperty();
            s.set(cell.getValue().getEmail());
            return s;
        });
        
        
        mdpCol.setCellValueFactory(cell->{
            StringProperty s=new SimpleStringProperty();
            s.set(cell.getValue().getMdp());
            return s;
        });
        
        
       RoleCol.setCellValueFactory(cell -> {
    StringProperty roleProp = new SimpleStringProperty(cell.getValue().getType().toString());
    return roleProp;
});
       UtilisateurService ut = new  UtilisateurService();
     ObservableList<Utilisateur> userData = FXCollections.observableArrayList(ut.getAll());
     userTable.setItems(userData);   
    }

    @FXML
    private void Modifier(ActionEvent event) {
        Utilisateur UserSelectionne = userTable.getSelectionModel().getSelectedItem();
    if (UserSelectionne != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierUtilisateur.fxml"));
                Parent root = loader.load();
                ModifierUtilisateurController controller = loader.getController();
                controller.initUser(UserSelectionne);
                
                Scene scene = new Scene(root);
                
                Stage stage = (Stage) globale.getScene().getWindow();
                
                stage.setTitle("Modifier Utilisateur");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }
    }

    @FXML
    private void Supprimer(ActionEvent event) {
        int id = Integer.parseInt(idT.getText());
        Utilisateur u = new Utilisateur(id);
        UtilisateurService us = new UtilisateurService();
        us.supprimer(u);
       loadDate();
        
    }

    @FXML
    private void displaySelected(MouseEvent event) {
      
        Utilisateur s = userTable.getSelectionModel().getSelectedItem();
        if (s == null) {
            idT.setText("vide");
        } else {
            int i = s.getId();
            idT.setText(String.valueOf(i));
        }
    }

    @FXML
    private void Ajt(ActionEvent event) {
        try {
            FXMLLoader loader = new   FXMLLoader(getClass().getResource("Ajouter.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) globale.getScene().getWindow();
            stage.setTitle("AJOUTER USER");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void carte(ActionEvent event) {
        try {
            FXMLLoader loader = new   FXMLLoader(getClass().getResource("Carte.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) globale.getScene().getWindow();
            stage.setTitle("INFORMATION CARTE");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    
    
    
    

   
    
}

   