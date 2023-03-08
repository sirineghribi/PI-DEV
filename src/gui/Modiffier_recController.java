/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Reclamation;
import services.ReclamationService;
import java.net.URL;
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
public class Modiffier_recController implements Initializable {

    @FXML
    private TextArea nvdes;
    @FXML
    private Button vd;
    private Reclamation recselect;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    

    @FXML
    private void allerback(ActionEvent event) {
        try{
         Parent root = FXMLLoader.load(getClass().getResource("/esprit/gui/Front_Reclamation.fxml"));  
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

    void initRec(Reclamation r) {
        this.recselect = r;
    nvdes.setText(r.getDescription());
    vd.setOnAction(event -> {
       String description = nvdes.getText();
       Reclamation r1 = new Reclamation(r.getId_rec(),description);
       ReclamationService rs = new ReclamationService();
       rs.modifier(description, r);
                            });
    }
    }
    

