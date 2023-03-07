/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Genre;
import entity.Utilisateur;
import entity.Vol;
import static gui.ReserverController.user;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import services.VolService;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class VolfrontController implements Initializable {

    
    @FXML
    private Button button;
    @FXML
    private GridPane grd;
    static Vol vvv;
    @FXML
    private Label name;
    @FXML
    private Button reservations;
    @FXML
    private ScrollPane scroll;
    static Utilisateur user ;
    @FXML
    private Button accueil;
    @FXML
    private Button profil;
    /**
     * Initializes the controller class.
     */
    
    public void setuser(Utilisateur user)
    {
       this.user=user; 
        System.out.println(user);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VolService volservice=new VolService();
        List<Vol> vols=volservice.getAll();
       
         ObservableList<Vol> liste=FXCollections.observableArrayList(volservice.getAll());
         scroll.setStyle("background-color: #712194;");
	 int r=0;
        for (Vol v:vols){
          
           
               Button b=new Button("Destination :"+v.getDestination()+"\t Prix :"+v.getPrix()+"\n Date :"+v.getDate());
               b.setLayoutY(b.getHeight()+3);
               b.setPrefSize(1000, 1000);   
               b.setStyle("-fx-text-fill: white; ");
               b.setStyle("-fx-border-color: black;");
              
 
               grd.add(b,0, r);
               r++;
             
               b.setOnAction(event -> {
               System.out.println(v);
               vvv=v;
               name.setText("Vol : Destination "+v.getDestination());
               
        });
           
             
                   
        }
         
        button.setOnAction(event -> {
            // if (v.getNbr_place()>0)
            if ((vvv != null)&&(vvv.getNbr_place()>0)) {
                try
                                {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/reserver.fxml"));
                                    Parent root =loader.load();
                                    button.getScene().setRoot(root);
                                    ReserverController rc =loader.getController();
                                    rc.setVol(vvv);
                                    rc.setuser(user);
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
                
                
            } else {
                if(vvv == null)
                afficher_alerte ("Aucun element est séléctionné !");
                if (vvv.getNbr_place()<=0)
                 afficher_alerte ("Ce vol n'est plus disponible !");
                
            }
        });	
	
       
    }    
    private void afficher_alerte (String a)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite lors de la reservation !");
        alert.setContentText(a);
        alert.showAndWait();  
          return;
    }

    @FXML
    private void historique(ActionEvent event) {
         try
                                {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Reservation.fxml"));
                                    Parent root =loader.load();
                                    button.getScene().setRoot(root);
                                    ReservationController rc =loader.getController();
                                    //Utilisateur u3 = new Utilisateur(3,"Mnejja","Imen",Genre.femme,"imen.mnejja@esprit.tn","123",Date.valueOf("2002-01-10") );
                                    rc.setUser(user);
                                    System.out.println(user);
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

    @FXML
    private void open_accueil(ActionEvent event) {
    }

    @FXML
    private void open_profil(ActionEvent event) {
         try
                                {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ModifierUtilisateur.fxml"));
                                    Parent root =loader.load();
                                    accueil.getScene().setRoot(root);
                                    ModifierUtilisateurController controller = loader.getController();
                                     controller.initUser(user);
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
}
