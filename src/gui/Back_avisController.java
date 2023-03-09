/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import entity.Avis;
import services.AvisService;
import java.io.IOException;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.SelectionMode;
//import org.controlsfx.control.textfield.TextFields;


/**
 * FXML Controller class
 *
 * @author sirin
 */
public class Back_avisController implements Initializable {

    @FXML
    private TableView<Avis> tabav;
    @FXML
    private TableColumn<Avis, String> note;
    @FXML
    private TableColumn<Avis, String> desa;
    @FXML
    private TableColumn<Avis, String> uti;
    @FXML
    private TableColumn<Avis, String> vol;
    @FXML
    private ImageView globale;
    @FXML
    private Button statistique;
    private List <Avis> list;
    @FXML
    private Button vol1;
    @FXML
    private Button user;
    @FXML
    private Button avis;
    @FXML
    private TextField idcherche;
    @FXML
    private Button recherche;
    @FXML
    private Button abonnement;
    @FXML
    private Button formation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AvisService as = new AvisService();

list= as.getAll();
aff();
// TextFields.bindAutoCompletion(idcherche,"Excellent","Bien","Moyen","Mauvais");
 //pour la selection multiple
 //tabav.getSelectionModel().setSelectionMode(
      //   SelectionMode.MULTIPLE
    }    
    public void aff()
    {
          AvisService as = new AvisService();

ObservableList<Avis> liste=FXCollections.observableArrayList(list);
       
        tabav.setItems(liste);
         Avis a =new Avis();
        note.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(cell.getValue().getNote().toString());
            return s;
        });
desa.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(cell.getValue().getDescription());
            return s;
        });        
uti.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(cell.getValue().getUtilisateur().getEmail());
            return s;
        }); 

     vol.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(cell.getValue().getVol().getDestination());
            return s;
        });

tabav.setItems(as.afficher_Avis());
    }

    @FXML
    private void repondreavis(ActionEvent event) {
     Avis a= tabav.getSelectionModel().getSelectedItem();
    if (a!= null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Reponse.fxml"));
                Parent root = loader.load();
                ReponseController controller = loader.getController();
                controller.initRec(a);

                Scene scene = new Scene(root);

                Stage stage = (Stage) globale.getScene().getWindow();

                stage.setTitle("reponse");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }
      
    }

    @FXML
    private void tri(ActionEvent event) {
          list=new AvisService().trier();
          aff();
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("la liste est triée !");
            alert.showAndWait();
       
    }

    @FXML
    private void rech(ActionEvent event) {
    }

    @FXML
    private void statistique(ActionEvent event) {
                   try{
         Parent root = FXMLLoader.load(getClass().getResource("/gui/stat.fxml"));  
         Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
        catch(Exception e)
        {
            System.out.println("Probleme:"+e);
        }
        
        
    }

    @FXML
    private void allerbackrec(ActionEvent event) {
         try{
         Parent root = FXMLLoader.load(getClass().getResource("/gui/Back_reclamation.fxml"));  
         Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
        catch(Exception e)
        {
            System.out.println("Probleme:"+e);
        }
        
    }

   

   
    @FXML
    private void idcherche(ActionEvent event) {
        
    }

    @FXML
    private void recherche(ActionEvent event) {
           tabav.getSelectionModel().clearSelection();
    for (int i = 0; i < tabav.getItems().size(); i++) {
        if (tabav.getItems().get(i).getNote().toString().equals(idcherche.getText())) {
            tabav.getSelectionModel().select(i);
        }
    }
    }

   
     @FXML
    private void open_vol(ActionEvent event) {
         try
                                {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Vols.fxml"));
                                    Parent root =loader.load();
                                    vol1.getScene().setRoot(root);
                                    Scene scene = new Scene(root,816,458);
                                    Stage SecondaryStage=new Stage();
                                    SecondaryStage.setTitle("Afficher Vol !");
                                    SecondaryStage.setScene(scene);
                                    SecondaryStage.show();
                                }
                                catch(Exception ex)
                                {
                                    System.out.println("err:"+ex);
                                }
    }

    @FXML
    private void openuser(ActionEvent event) {
        try
                                {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AfficherUtilisateur.fxml"));
                                    Parent root =loader.load();
                                    vol1.getScene().setRoot(root);
                                    Scene scene = new Scene(root,816,458);
                                    Stage SecondaryStage=new Stage();
                                    SecondaryStage.setTitle("Afficher utilisateurs !");
                                    SecondaryStage.setScene(scene);
                                    SecondaryStage.show();
                                }
                                catch(Exception ex)
                                {
                                    System.out.println("err:"+ex);
                                }
    }

    @FXML
    private void open_avis(ActionEvent event) {
        try
                                {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Back_avis.fxml"));
                                    Parent root =loader.load();
                                    vol1.getScene().setRoot(root);
                                    Scene scene = new Scene(root,816,458);
                                    Stage SecondaryStage=new Stage();
                                    SecondaryStage.setTitle("Afficher utilisateurs !");
                                    SecondaryStage.setScene(scene);
                                    SecondaryStage.show();
                                }
                                catch(Exception ex)
                                {
                                    System.out.println("err:"+ex);
                                }
    }

    @FXML
    private void open_type_ab(ActionEvent event) {
        try
                                {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Add_Type_Abonnement_FXML.fxml"));
                                    Parent root =loader.load();
                                    vol1.getScene().setRoot(root);
                                    Scene scene = new Scene(root,816,458);
                                    Stage SecondaryStage=new Stage();
                                    SecondaryStage.setTitle("Afficher utilisateurs !");
                                    SecondaryStage.setScene(scene);
                                    SecondaryStage.show();
                                    
                                }
                                catch(Exception ex)
                                {
                                    System.out.println("err:"+ex);
                                }
    }

    @FXML
    private void open_formation(ActionEvent event) {
         try
                                {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/displayformation.fxml"));
                                    Parent root =loader.load();
                                    vol1.getScene().setRoot(root);
                                    Scene scene = new Scene(root,816,458);
                                    Stage SecondaryStage=new Stage();
                                    SecondaryStage.setTitle("Afficher utilisateurs !");
                                    SecondaryStage.setScene(scene);
                                    SecondaryStage.show();
                                    
                                }
                                catch(Exception ex)
                                {
                                    System.out.println("err:"+ex);
                                }
    }

    @FXML
    private void open_vehicule(ActionEvent event) {
        try
                                {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/afficher_vh.fxml"));
                                    Parent root =loader.load();
                                    vol1.getScene().setRoot(root);
                                    Scene scene = new Scene(root,816,458);
                                    Stage SecondaryStage=new Stage();
                                    SecondaryStage.setTitle("Afficher utilisateurs !");
                                    SecondaryStage.setScene(scene);
                                    SecondaryStage.show();
                                    
                                }
                                catch(Exception ex)
                                {
                                    System.out.println("err:"+ex);
                                }
    }
}
