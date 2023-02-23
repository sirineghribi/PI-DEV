/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Formation;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.FormationeServices;

/**
 * FXML Controller class
 *
 * @author abder
 */
public class DisplayformationController implements Initializable {

    @FXML
    private TableColumn<Formation, String> IDDIS;
    @FXML
    private TableColumn<Formation, String> typedis;
    @FXML
    private TableColumn<Formation, String> datedis;
    @FXML
    private TableColumn<Formation, String> hoursdis;
    @FXML
    private TableColumn<Formation, String> nomdis;
    @FXML
    private TableColumn<Formation, String> prenomdis;
    @FXML
    private TableColumn<Formation, String> maildis;
    @FXML
    private TableView<Formation> tabelformation;
    @FXML
    private Button supprimerdis;
    @FXML
    private Button modifierdis;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FormationeServices fss = new FormationeServices();
        ObservableList<Formation> liste=FXCollections.observableArrayList(fss.getAll());
        tabelformation.setItems(liste);
        maildis.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(String.valueOf(cell.getValue().getUtilisateur().getEmail()));
            return s;
        });
        nomdis.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(String.valueOf(cell.getValue().getUtilisateur().getNom()));
            return s;
        });
         prenomdis.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(String.valueOf(cell.getValue().getUtilisateur().getPrenom()));
            return s;
        });
           hoursdis.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(String.valueOf(cell.getValue().getNbrheur()));
            return s;
        });
                datedis.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(String.valueOf(cell.getValue().getDate()));
            return s;
        });
                
            typedis.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(String.valueOf(cell.getValue().getType()));
            return s;
        });
           IDDIS.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(String.valueOf(cell.getValue().getId_f()));
            return s;
        });
           
    
supprimerdis.setOnAction(event -> {
                             Formation so = tabelformation.getSelectionModel().getSelectedItem();  
                               System.out.println(so);
                               FormationeServices sf = new FormationeServices();
                               sf.supprimer(so);
                            });
modifierdis.setOnAction((ActionEvent event) -> {

Formation so = tabelformation.getSelectionModel().getSelectedItem();

                                try
                                {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ModifyFormation.fxml"));
                                    Parent root =loader.load();
                                    ModifyFormationController ha =loader.getController();
                                    ha.setFo(so);
                                    //ajout_onClick.getScene().setRoot(root);
                                    Scene scene = new Scene(root);
                                    Stage SecondaryStage=new Stage();
                                    SecondaryStage.setTitle("Modifier Formation !");
                                    SecondaryStage.setScene(scene);
                                    SecondaryStage.show();
                                }
                                catch(Exception ex)
                                {
                                    System.out.println("err:"+ex);
                                }



                                     });

        
}
    }    
    

