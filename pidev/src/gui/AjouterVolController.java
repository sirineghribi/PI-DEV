/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entity.Vehicule;
import entity.Vol;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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
          
    }    

    @FXML
    private void addVol(ActionEvent event) {
        
        String destination=destination_txt.getText();
        float prix = Float.valueOf(prix_txt.getText());
        int v=Integer.valueOf(vehicule_choix.getValue().toString());
        VehiculeServices vs=new VehiculeServices();
        Vehicule vehicule=vs.findById(v).get(0);
        Date date=Date.valueOf(date_choix.getValue());
        
        
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite lors de l'ajout.");
        
        if (destination.isEmpty())
        {
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();  
            return;
        }
      
        if (prix < 0.0f)
        {
            alert.setContentText("Prix doit etre positif !");
            alert.showAndWait();        
            return;
        }
        
        
        VolService volservice=new VolService();
        Vol vol =new Vol(destination,"planifié",prix,date,vehicule);
        volservice.ajouter(vol);
        
        
        
        
    }
   

   
    
}
