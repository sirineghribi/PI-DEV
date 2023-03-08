/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Avis;
import entity.NoteA;
import entity.Rep;
import entity.Utilisateur;
import services.AvisService;
import services.Repservice;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sirin
 */
public class ReponseController implements Initializable {

    @FXML
    private Button pub;
     private Avis ravselect;
    @FXML
    private TextArea nvdes;
    @FXML
    private TextArea rep;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void publier(ActionEvent event) {
       
        String reponse= rep.getText();
        
        
         if(!( rep.getText().equals("") ))
                 {
                     
        //Utilisateur u2 = new Utilisateur(2,"mn","imen","femme","imenmn@gmail.com","ii",Date.valueOf("2002-06-26") );

        //Avis a = new Avis(14,NoteA.Excellent,"tout est bien passé",u2);
        Rep a1=new Rep(reponse,ravselect);

        Repservice as = new Repservice();
        as.ajouter(a1);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Ajout avec succées!");
            alert.showAndWait();
                 }
        else{
        Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Error alert");
alert.setHeaderText("Les champs de texte d'un formulaire ne doivent pas être vide");
alert.showAndWait();
        }
    }
     
     void initRec(Avis a) {
        this.ravselect = a;
    nvdes.setText(a.getDescription());
    String description = nvdes.getText();
    pub.setOnAction(event -> {
       
      // Avis a1 = new Avis(a.getId_avis(),description);
       AvisService as = new AvisService();
        Repservice rs= new Repservice();
        String reponse = rep.getText();
      Rep r =new Rep(reponse,a);
    
     
      rs.ajouter(r);
                            });
    }

    @FXML
    private void backav(ActionEvent event) {
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
}
