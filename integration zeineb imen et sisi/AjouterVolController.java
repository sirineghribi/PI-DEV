/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entity.Vehicule;
import entity.Vol;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.VehiculeServices;
import services.VolService;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class AjouterVolController implements Initializable {

    
    @FXML
    private ComboBox<Integer> vehicule_choix;
    @FXML
    private TextField destination_txt;
    @FXML
    private TextField prix_txt;
    @FXML
    private Button ajouter_btn;
    @FXML
    private DatePicker date_choix;
     VolService volservice=new VolService();
    @FXML
    private Button accueil;
    @FXML
    private Button ajouter_btn1;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       VolService volservice=new VolService();
        List<Integer> l= volservice.id_vehicule_list();
       vehicule_choix.setItems(observableArrayList(l));
        accueil.setStyle("-fx-text-fill: white;"); 
        ajouter_btn1.setStyle("-fx-text-fill: white;"); 
        ajouter_btn.setStyle("-fx-text-fill: white;"); 
    }    

    @FXML
    private void addVol(ActionEvent event) {
        
        
      
        if (destination_txt.getText().equals("")||date_choix.getValue()==null||vehicule_choix.getSelectionModel().isEmpty()||prix_txt.getText().trim().isEmpty())
        {
            afficher_alerte ("Veuillez remplir tous les champs !");
        }
        else if (Float.valueOf(prix_txt.getText()) < 0.0f)
        {
            afficher_alerte ("Le prix doit etre positif !");
        }
        else
        {
       String destination=destination_txt.getText();
        float prix = Float.valueOf(prix_txt.getText());
        int v=Integer.valueOf(vehicule_choix.getValue().toString());
        VehiculeServices vs=new VehiculeServices();
        Vehicule vehicule=vs.findById(v).get(0);
        Date date=Date.valueOf(date_choix.getValue());
        System.out.println("prix:"+prix);
        Vol vol =new Vol(destination,"planifié",prix,date,vehicule);
        volservice.ajouter(vol);
        afficher_info ();
        vider();      
        }
      
    }
    private void afficher_alerte (String a)
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite lors de l'ajout.");
        alert.setContentText(a);
        alert.showAndWait();  
          return;
    }
    private void afficher_info ()
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Succée");
        alert.setHeaderText("Ajout du vol.");
        alert.setContentText("Ajout avec succée !");
        alert.showAndWait();  
          return;
    }
    private void vider()
    {
        destination_txt.clear();
        prix_txt.clear();
        date_choix.setValue(null);
       vehicule_choix.setValue(null);
    }
    private void update ()
    {   
        
        try {
           
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Vols.fxml"));
            Parent root =loader.load();
                                 
                                
            accueil.getScene().setRoot(root);
            Scene scene = new Scene(root);
            Stage SecondaryStage=new Stage();
            SecondaryStage.setTitle("Les Vols !");
            SecondaryStage.setScene(scene);
            SecondaryStage.show();
            
        } catch (IOException ex) {
            System.out.println(ex+"");
        }
        
        
    }

    @FXML
    private void accueil(ActionEvent event) {
        update();
    }

    @FXML
    private void vider_t(ActionEvent event) {
        vider();
    }

}
