/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.BufferedWriter;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import entity.Vehicule;
import services.VehiculeServices;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class Afficher_vhController implements Initializable {

    
    @FXML
    private TableView<Vehicule> tab;
    @FXML
    private TableColumn<Vehicule, String> id_vehicule;
    @FXML
    private TableColumn<Vehicule, String > nom_vh;
    @FXML
    private TableColumn<Vehicule, String> cat_vehicule;
    @FXML
    private TableColumn<Vehicule, String> poid_sup;
    @FXML
    private TableColumn<Vehicule, String> vitesse;
    @FXML
    private TableColumn<Vehicule, String> nbr_pas;
    @FXML
    private TableColumn<Vehicule, String> status;
    @FXML
    private AnchorPane gb;
    @FXML
    private Button export_bt;
    @FXML
    private TextField search;
    private List<Vehicule> searchnom;
    @FXML
    private Button sorting;
    @FXML
    private Button vol;
    @FXML
    private Button user;
    @FXML
    private Button avis;
    @FXML
    private Button type_ab;
    @FXML
    private Button formation;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        VehiculeServices vs = new VehiculeServices();
        searchnom=vs.getAll();
        search.textProperty().addListener((o)->{
            if(!search.getText().isEmpty() )
                searchnom=vs.searchbynom(String.valueOf(search.getText()));
            else searchnom=vs.getAll();
                if(searchnom.isEmpty())
                    searchnom=vs.getAll();
                 display();   
                    
                    });
        display();
        

      
    }    


    @FXML
    private void modifierVehicule(ActionEvent event) {
        Vehicule a=tab.getSelectionModel().getSelectedItem();
        if(a!=null){
        try{
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Mod_vh.fxml")); 
         //Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                Parent root = loader.load();
                Mod_vhController controller = loader.getController();
                controller.initvh(a);
        Scene scene = new Scene(root);
        Stage stage = (Stage) gb.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        }
               
        catch(Exception e)
        {
            System.out.println("Probleme:"+e);
        } 
           
    }}
    

    @FXML
    private void supprimerVehicule(ActionEvent event) {
        
      
        Vehicule a= tab.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(AlertType.CONFIRMATION);
       VehiculeServices as= new VehiculeServices();  
       
        if (a!=null){
           
       
        alert.setTitle("Confirmation de suppression");
         alert.setHeaderText("Confiramation de suppression");
         alert.setContentText("Voulez-vous vraiment supprimer ce vehicule!");
   
        Optional<ButtonType> result = alert.showAndWait();
         // ... user chose OK
       if (result.get() == ButtonType.OK){

            as.supprimerVehicule(a);
           as.getAll();
       }
       
    }
  else
    {
           Alert al = new Alert(AlertType.ERROR);

           al.setTitle("Error alert");
           al.setHeaderText("Vous devez selectionner au moins un vehicule à suprrimer");
   
            al.showAndWait();
    }
    }

    @FXML
    private void export(ActionEvent event) {
         try {
            FileChooser fc = new FileChooser();
            fc.setTitle("Vehicule");
            fc.setInitialFileName("type categorie.csv.");
            String s = fc.showSaveDialog(tab.getScene().getWindow()).toString();
            if (s != null) {
                File file = new File(s);
                Writer w = new BufferedWriter(new FileWriter(file));
                w.write("Nom_vh,nom_cat,poid_sup,vitesse,nbr_pas,status");
                tab.getItems().stream().forEach((ta) -> {

                    try {
                     //   int vitesse = new VehiculeService().getAll().size();
                     //   float nbr = new AbonnementService().select_byType(new Abonnement(Date.valueOf(LocalDate.now()), ta, new Utilisateur())).size();
                     //   float per = (nbr * 100) / max;
                        w.write("\n" + ta.getNom_vh() + "   ," + ta.getCategorieVehicule().get_nom_cat() + "    ," + ta.get_poid_sup() + "  ," + ta.get_vitesse() + "," + ta.get_nbr_pas() + "," + ta.getStatus());
                    } catch (IOException ex) {
                        System.out.println("err:" + ex.getMessage());
                    }
                });
                w.flush();
                w.close();
            }
        } catch (IOException ex) {
            System.out.println("err:" + ex.getMessage());
        }
    }
    
    public void display(){
           VehiculeServices vs = new VehiculeServices();

ObservableList<Vehicule> liste=FXCollections.observableArrayList(searchnom);
       tab.setItems(liste);
       
        
         Vehicule p = new Vehicule();
        // System.out.println(vs.getAll());
              

        
       // id_vehicule.setCellValueFactory(new PropertyValueFactory<>("id_vehicule"));
       id_vehicule.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(String.valueOf(cell.getValue().get_id_vehicule()));
            return s;
        });
       
       cat_vehicule.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(String.valueOf(cell.getValue().getCategorieVehicule().get_id_cat()));
            return s;
        });
       
        nom_vh.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(cell.getValue().getNom_vh());
            
            return s;
        }); 
        poid_sup.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(String.valueOf(cell.getValue().get_poid_sup()));
            return s;
        });
        
        vitesse.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(String.valueOf(cell.getValue().get_vitesse()));
            return s;
        });
        
        nbr_pas.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(String.valueOf(cell.getValue().get_nbr_pas()));
            return s;
        });
        
        status.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(String.valueOf(cell.getValue().getStatus()));
            return s;
        }); 
    }

    @FXML
    private void sort(ActionEvent event) {
        searchnom=new VehiculeServices().sort();
        display();
    }

    @FXML
    private void open_vol(ActionEvent event) {
         try
                                {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Vols.fxml"));
                                    Parent root =loader.load();
                                    vol.getScene().setRoot(root);
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
                                    vol.getScene().setRoot(root);
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
                                    vol.getScene().setRoot(root);
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
                                    vol.getScene().setRoot(root);
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
                                    vol.getScene().setRoot(root);
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
                                    vol.getScene().setRoot(root);
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
    private void ajout_onclick(ActionEvent event) {
        try
                                {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AjoutVehicule.fxml"));
                                    Parent root =loader.load();
                                    vol.getScene().setRoot(root);
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
    private void gestion_categorie(ActionEvent event) {
        try
                                {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AfficherCat.fxml"));
                                    Parent root =loader.load();
                                    vol.getScene().setRoot(root);
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
}
