/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Carte_fidelite;
import entity.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.CarteService;
import services.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author Zeineb Ben Mami
 */
public class CarteController implements Initializable {

    @FXML
    private AnchorPane card;
    @FXML
    private TableView<Carte_fidelite> CardTable;
    @FXML
    private TableColumn<Carte_fidelite, String> numCol;
    @FXML
    private TableColumn<Carte_fidelite, String> nbrCol;
    @FXML
    private TableColumn<Carte_fidelite, String> idCol;
    @FXML
    private Button supprimer;
    @FXML
    private Button back;
    @FXML
    private TextField idT;
    @FXML
    private Button stat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDate();
    }    

    @FXML
    private void supprimer(ActionEvent event) {
        int numero = Integer.parseInt(idT.getText());
        Carte_fidelite c = new Carte_fidelite(numero);
        CarteService cs = new CarteService();
        cs.supprimer(c);
       loadDate();
        
    }

    @FXML
    private void back(ActionEvent event) {
         try {
            FXMLLoader loader = new   FXMLLoader(getClass().getResource("AfficherUtilisateur.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) card.getScene().getWindow();
            stage.setTitle("INFORMATION USER");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void loadDate() {
           numCol.setCellValueFactory(cell -> {
    IntegerProperty idProp = new SimpleIntegerProperty(cell.getValue().getNumero());
    return idProp.asString();
     });
        
        nbrCol.setCellValueFactory(cell -> {
    IntegerProperty idProp = new SimpleIntegerProperty(cell.getValue().getNbr_point());
    return idProp.asString();
     });
        
        
             idCol.setCellValueFactory(cell->{
            StringProperty s=new SimpleStringProperty();
            s.set(cell.getValue().getUtilisateur().getPrenom());
            return s;
        });
        
        
         /* idCol.setCellValueFactory(cell -> {
    IntegerProperty idProp = new SimpleIntegerProperty(cell.getValue().getUtilisateur().getId());
    return idProp.asString();
     });*/
         
      
         
      CarteService cs = new  CarteService();
     ObservableList<Carte_fidelite> cardData = FXCollections.observableArrayList(cs.getAll());
     CardTable.setItems(cardData);  
    }


    @FXML
    private void selected(MouseEvent event) {
        Carte_fidelite s = CardTable.getSelectionModel().getSelectedItem();
        if (s == null) {
            idT.setText("vide");
        } else {
            int i = s.getNumero();
            idT.setText(String.valueOf(i));
        }
    }

    @FXML
    private void stat(ActionEvent event) {
         try {
            FXMLLoader loader = new   FXMLLoader(getClass().getResource("/gui/Stat_Carte.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("STATISTIQUES CARTE");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
