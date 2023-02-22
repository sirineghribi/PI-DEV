/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Reservation;
import entity.Utilisateur;
import entity.Vol;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.ReservationService;
import services.VolService;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class ReserverController implements Initializable {

    
    private Vol vol ;
    @FXML
    private Button reserver;
    @FXML
    private TextField cin_txt;
    @FXML
    private TextField num_txt;
    @FXML
    private CheckBox condition;

    public void setVol(Vol vol) {
        this.vol = vol;
        System.out.println(vol);
      
        
    
           
            reserver.setOnAction((ActionEvent event) -> { 
                
            int cin=Integer.parseInt(cin_txt.getText());
            int num=Integer.parseInt(num_txt.getText());
            boolean c=condition.isSelected();
            Utilisateur u3 = new Utilisateur(1,"mn","imen","femme","imenmn@gmail.com","ii",Date.valueOf("2002-06-26") );
            ReservationService rs = new ReservationService();
            Reservation r=new Reservation(cin,num,1,"En attente",Date.valueOf("2023-12-12"),vol, u3);
            if (c==true)
                    {  rs.ajouter(r); 
                                try
                                { 
                                    VolService volservice=new VolService();
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Reservation.fxml"));
                                    Parent root =loader.load();
                                    ReservationController rc =loader.getController();
                                    rc.setUser(u3);
                                    Scene scene = new Scene(root);
                                    Stage SecondaryStage=new Stage();
                                    SecondaryStage.setTitle("Reserver Vol !");
                                    SecondaryStage.setScene(scene);
                                    SecondaryStage.show();
                                }
                                catch(Exception ex)
                                {
                                    System.out.println("err:"+ex);
                                }
                    }
            
            else 
        {
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Erreur");
             alert.setHeaderText("Une erreur s'est produite lors de la reservation .");
             alert.setContentText("Vous devez acceptez les conditions !");
             alert.showAndWait();        
             return;
        }
        
        });
        
    
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
