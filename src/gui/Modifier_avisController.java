/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Avis;
import entity.Reclamation;
import services.AvisService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sirin
 */
public class Modifier_avisController implements Initializable {

    @FXML
    private TextField nvdesc;
    @FXML
    private Button vd;
 private Avis ravselect;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void back(ActionEvent event) {
         try{
         Parent root = FXMLLoader.load(getClass().getResource("/esprit/gui/Front_Avis.fxml"));  
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

    void initRec(Avis a) {
        this.ravselect = a;
    nvdesc.setText(a.getDescription());
    vd.setOnAction(event -> {
       String description = nvdesc.getText();
       Avis a1 = new Avis(a.getId_avis(),description);
       AvisService as = new AvisService();
       as.modifier(description, a);
                            });
    }

    }

   
