/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import entity.Reclamation;
import services.ReclamationService;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
//import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author sirin
 */
public class Back_reclamationController implements Initializable {

    private TableColumn<Reclamation,Integer> id_rec;
    @FXML
    private TableColumn<Reclamation,String> type;
    @FXML
    private TableColumn<Reclamation,String> desc;
    @FXML
    private TableColumn<Reclamation,String> id_c;
    @FXML
    private TableColumn<Reclamation,String> etat;
    @FXML
    private TableView<Reclamation> backtabr;
    @FXML
    private Button tr;
    List<Reclamation> lis;
    @FXML
    private ImageView globale;
    List<Reclamation> list;
    @FXML
    private TextField idcherche;
    @FXML
    private Button recherche;
    @FXML
    private Button vol1;
    @FXML
    private Button user;
    @FXML
    private Button avis;
    @FXML
    private Button type_ab;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list=new ReclamationService().getAll();
        aff();
         //TextFields.bindAutoCompletion(idcherche,"technique","Service","Autre");
         //pour la selection multiple
         backtabr.getSelectionModel().setSelectionMode(
         SelectionMode.MULTIPLE
         );
                
    }    
    public void aff()
    {
     ReclamationService rs = new ReclamationService();
        System.out.println(rs.afficher_Reclamation());
        ObservableList<Reclamation> liste=FXCollections.observableArrayList(list);
     //  backtabr.setItems(liste);
       Reclamation r =new Reclamation();
       // ReclamationService rs= new ReclamationService(); 
       type.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(cell.getValue().getType().toString());
            return s;
        });
desc.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(cell.getValue().getDescription());
            return s;
        });        
id_c.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(cell.getValue().getUtilisateur().getEmail());
            return s;
        }); 

     etat.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(cell.getValue().getEtat());
            return s;
        }); 

    backtabr.setItems(rs.afficher_Reclamation());
  
    }
    
    @FXML
    private void traiter_reclamation(ActionEvent event) {
         Reclamation r= backtabr.getSelectionModel().getSelectedItem();
         System.out.println(r);
    if (r != null) {
            try { 
                if(r.getEtat().equals("non traité")){
                      r.setEtat("traité");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
                Parent root = loader.load();
                FXMLDocumentController controller = loader.getController();
                controller.initRec(r);

                Scene scene = new Scene(root);

                Stage stage = (Stage) globale.getScene().getWindow();

                stage.setTitle("mail");
                stage.setScene(scene);
                
                stage.show();
                
                }else {Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Reclamation déja traité");
            alert.showAndWait();}
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }
        
        /*           Reclamation r= backtabr.getSelectionModel().getSelectedItem();
           
        try{
            if(r.getEtat().equals("non traité")){
         Parent root = FXMLLoader.load(getClass().getResource("/esprit/gui/FXMLDocument.fxml"));  
         Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
         r.setEtat("traité");
        stage.show();
       
        }else {Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("reclamation déja traité");
            alert.showAndWait();}
        
        }
        
               
        catch(Exception e)
        {
            System.out.println("Probleme:"+e);
        } 
        */
    }

    @FXML
    private void Allerbackavis(ActionEvent event) {
        
         try{
         Parent root = FXMLLoader.load(getClass().getResource("/gui/Back_avis.fxml"));  
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
    
    /* public void search_rec(){
         
        
        ObservableList<Reclamation> liste=FXCollections.observableArrayList(lis);
         type.setCellValueFactory(new PropertyValueFactory<>("type"));
        desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        id_c.setCellValueFactory(new PropertyValueFactory<>("utilisateur"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        backtabr.setItems(liste);
             FilteredList<Reclamation> filteredData = new FilteredList(liste, b -> true); 
        c.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate((Reclamation prd) -> {
            System.out.println(newValue);
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (prd.getType().toString().toLowerCase().contains(lowerCaseFilter) ) {
                    return true;
                
                }else{
                    return false;
                }
            });
         });
                   SortedList<Reclamation> sortedData = new SortedList<>(filteredData);
                           sortedData.comparatorProperty().bind(backtabr.comparatorProperty());
                                backtabr.setItems(sortedData);
         //System.out.println(observablerec);
         
    
}*/

    @FXML
    private void trier(ActionEvent event) {
        list=new ReclamationService().trier();
        aff();
    }

    @FXML
    private void idcherche(ActionEvent event) {
    }

    @FXML
    private void recherche(ActionEvent event) {
         backtabr.getSelectionModel().clearSelection();
    for (int i = 0; i < backtabr.getItems().size(); i++) {
        if (backtabr.getItems().get(i).getType().equals(idcherche.getText())) {
            // select the row
            backtabr.getSelectionModel().select(i);
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
         {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AfficherUtilisateur.fxml"));
                Parent root =loader.load();
                vol1.getScene().setRoot(root);
                Scene scene = new Scene(root,816,458);
                Stage SecondaryStage=new Stage();
                SecondaryStage.setTitle("Afficher utilisateurs !");
                SecondaryStage.setScene(scene);
                SecondaryStage.show();
            } catch (IOException ex) {
                Logger.getLogger(Back_reclamationController.class.getName()).log(Level.SEVERE, null, ex);
            }
                                }
                                
    }

    @FXML
    private void open_avis(ActionEvent event) {
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
}
