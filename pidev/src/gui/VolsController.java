/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entity.Vol;
import java.io.IOException;
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
import services.VolService;

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
   
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ObservableList<Vol> liste=FXCollections.observableArrayList(volservice.getAll());
        tablevols.setItems(liste);
        setvols();
        tablevols.setItems(liste);
        
    }    

    @FXML
    private void ajout_onClick(ActionEvent event) {
       try {
            String chemin="/gui/AjouterVol.fxml";
            Parent root = FXMLLoader.load(getClass().getResource(chemin));
            Scene scene = new Scene(root, 424, 424);
            Stage stage=new Stage();
            stage.setTitle("Ajouter vol");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
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
            s.set(String.valueOf(cell.getValue().getMt().get_cat_vehicule()));
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
                               System.out.println(v);
                               volservice.supprimer(v);
                            });
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
                                    //ajout_onClick.getScene().setRoot(root);
                                    Scene scene = new Scene(root);
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
    
}
