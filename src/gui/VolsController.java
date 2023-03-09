/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entity.Vol;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.VolService;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class VolsController implements Initializable {

    @FXML
    private TableView<Vol> tablevols;
    @FXML
    private TableColumn<Vol,String> date_c;
    @FXML
    private TableColumn<Vol,String> destination_c;
    @FXML
    private TableColumn<Vol,String> prix_c;
    @FXML
    private TableColumn<Vol,String> etat_c;
    @FXML
    private TableColumn<Vol,String> place_dispo;
    @FXML
    private TableColumn<Vol, String> categorie_v;
    @FXML
    private TableColumn<Vol, String> poids_sup;
    @FXML
    private TableColumn<Vol, String> vitesse;
    @FXML
    private Button ajout_onClick;
    @FXML
    private TableColumn<Vol, String> action;
    
    VolService volservice=new VolService();
    
    @FXML
    private TableColumn<Vol, String> action1;
    @FXML
    private TextField search;
    @FXML
    private Button trier;
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
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       update();
       // ajout_onClick.setStyle("-fx-text-fill: white;"); 
       // trier.setStyle("-fx-text-fill: white;");
    }    
    public void update()
    {

        ObservableList <Vol> liste=FXCollections.observableArrayList(volservice.getAll());
        tablevols.setItems(liste);
        setvols();
        
        search.textProperty().addListener((observable, oldValue, newValue) -> {
        
        ObservableList<Vol> filteredList = liste.filtered(
            object -> object.getDestination().contains(newValue)
        );
       
        tablevols.setItems(filteredList);
    });
        
        
    }
    @FXML
    private void ajout_onClick(ActionEvent event) {
        try
                                {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AjouterVol.fxml"));
                                    Parent root =loader.load();
                                    ajout_onClick.getScene().setRoot(root);
                                    Scene scene = new Scene(root);
                                    Stage SecondaryStage=new Stage();
                                    SecondaryStage.setTitle("Ajouter Vol !");
                                    SecondaryStage.setScene(scene);
                                    SecondaryStage.show();
                                }
                                catch(Exception ex)
                                {
                                    System.out.println("err:"+ex);
                                }
    }

private void setvols()
{
    destination_c.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(cell.getValue().getDestination());
            return s;
        });
    date_c.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(cell.getValue().getDate().toString());
            return s;
        });
    prix_c.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(String.valueOf(cell.getValue().getPrix()));
            return s;
        });
    etat_c.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(cell.getValue().getEtat());
            return s;
        });
    place_dispo.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(String.valueOf(cell.getValue().getNbr_place()));
            return s;
        });
    categorie_v.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(String.valueOf(cell.getValue().getMt().getNom_vh()));
            return s;
        });
    poids_sup.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(String.valueOf(cell.getValue().getMt().get_poid_sup()));
            return s;
        });
    vitesse.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(String.valueOf(cell.getValue().getMt().get_vitesse()));
            return s;
        });
    
    
     Callback<TableColumn<Vol, String>, TableCell<Vol, String>> cellFactory= (final TableColumn<Vol, String> param) -> {
         final TableCell<Vol, String> cell = new TableCell<Vol, String>() {
             
             final Button btn = new Button("Supprimer");
             
             @Override
             public void updateItem(String item, boolean empty) {
                 super.updateItem(item, empty);
                 if (empty) {
                     setGraphic(null);
                     setText(null);
                 } else {
                     btn.setOnAction(event -> {
                               Vol v = getTableView().getItems().get(getIndex());
                              if (v.getEtat().equals("planifié"))
                              {System.out.println(v);
                              if (confirmerSuppression()==1)
                               volservice.supprimer(v);
                               update();
                              }
                              else afficher_alerte("Vol déja confirmé !");
                            });
                     btn.setStyle("-fx-text-fill: white;");
                     

                     setGraphic(btn);
                     setText(null);
                 }
             }
         };
         return cell;
    };
     Callback<TableColumn<Vol, String>, TableCell<Vol, String>> cellFactory1= (final TableColumn<Vol, String> param) -> {
         final TableCell<Vol, String> cell = new TableCell<Vol, String>() {
             
             final Button btn = new Button("Modifier");
             
             @Override
             public void updateItem(String item, boolean empty) {
                 super.updateItem(item, empty);
                 if (empty) {
                     setGraphic(null);
                     setText(null);
                 } else {
                     btn.setOnAction((ActionEvent event) -> {
                            Vol v = getTableView().getItems().get(getIndex());
                              
                               
                               
                                try
                                {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ModifierVol.fxml"));
                                    Parent root =loader.load();
                                    ModifierVolController ac =loader.getController();
                                    ac.setVol(v);
                                     update();
                                    ajout_onClick.getScene().setRoot(root);
                                    Scene scene = new Scene(root,816,458);
                                    Stage SecondaryStage=new Stage();
                                    SecondaryStage.setTitle("Modifier Vol !");
                                    SecondaryStage.setScene(scene);
                                    SecondaryStage.show();
                                }
                                catch(Exception ex)
                                {
                                    System.out.println("err:"+ex);
                                }
                               
                               
                               
                                     });
                     btn.setStyle("-fx-text-fill: white;");
                     setGraphic(btn);
                     setText(null);
                 }
             }
         };
         return cell;
    };

        action.setCellFactory(cellFactory);
        action1.setCellFactory(cellFactory1);
        
}
   private void afficher_alerte (String a)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite lors de la suppression.");
        alert.setContentText(a);
        alert.showAndWait();  
          return;
    } 
    private int confirmerSuppression(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Fenetre de confirmation");
        alert.setHeaderText("Suppression du VOL");
        alert.setContentText("Confirmez la suppression ce Vol?");
        ButtonType buttonTypeOne = new ButtonType("Oui");
        ButtonType buttonTypeTwo = new ButtonType("Non");
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == buttonTypeOne)
            return 1;
        else return 2;
    }  
    @FXML
    private void trier(ActionEvent event) {
        ObservableList <Vol> liste=FXCollections.observableArrayList(volservice.trier());
        tablevols.setItems(liste);
        setvols(); 
    }

    @FXML
    private void open_vol(ActionEvent event) {
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

}
