/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.sun.org.glassfish.gmbal.Description;
import services.ReclamationService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import entity.Reclamation;
import static gui.ModifierUtilisateurController.user1;
import static gui.ReservationController.user;
import static gui.VolfrontController.user;
import services.ReclamationService;
import java.awt.image.RescaleOp;
import java.io.IOException;
import java.sql.Date;
import java.util.Optional;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sirin
 */
public class Front_ReclamationController implements Initializable {

    private TableView<Reclamation> tab;
    private TableColumn<?, ?> coldesc;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private ImageView global;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grd;
    @FXML
    static Button rec;
    @FXML
    private Button accueil;
    @FXML
    private Button profil;
    static Reclamation reclamation;
    @FXML
    private Button abonnement;
    @FXML
    private Button formation;
    @FXML
    private Button vehicule;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ReclamationService rs= new ReclamationService();
        int rr=0;
        for (Reclamation r:rs.afficher_Reclamation()){


               Button b=new Button("Description :"+r.getDescription());
               b.setPrefSize(250, 250);
               b.setStyle("-fx-text-fill: white; ");
               b.setStyle("-fx-border-color: black;");
               b.setStyle("-fx-background-color:#7601351;");   

 
               grd.add(b,0, rr);
               rr++;

               b.setOnAction(event -> {
               System.out.println(r);
               reclamation=r;
               

        });



        }

        
        
        
        
        
      /* ReclamationService rs = new ReclamationService();

ObservableList<Reclamation> liste=FXCollections.observableArrayList(rs.afficher_Reclamation());
       tab.setItems(liste);
       // setreclamtions();
        tab.setItems(liste);
         Reclamation r =new Reclamation();
        //ReclamationService rs= new ReclamationService(); 
              

        
        coldesc.setCellValueFactory(new PropertyValueFactory<>("description"));
       

tab.setItems(rs.afficher_Reclamation());*/
    }    

   

    @FXML
    private void supprimer(ActionEvent event) {
         Reclamation r= tab.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(AlertType.CONFIRMATION);
       ReclamationService rs= new ReclamationService();   
       
        if (r!=null){
            
        
        alert.setTitle("Confirmation de suppression");
         alert.setHeaderText("Confiramation de suppression");
         alert.setContentText("Voulez-vous vraiment supprimer cette reclamation!");
    
        Optional<ButtonType> result = alert.showAndWait();
        
       if (result.get() == ButtonType.OK){

            rs.supprimer(r);
           rs.afficher_Reclamation();
       }
        
    }
  else
    {
           Alert al = new Alert(AlertType.ERROR);

           al.setTitle("Error alert");
           al.setHeaderText("Vous devez selectionner au moins une reclamation pour suprrimer");
   
            al.showAndWait();
    }
    }

    
    @FXML
    private void Allermodifier(ActionEvent event) {
         Reclamation r= tab.getSelectionModel().getSelectedItem();
    if (r != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Modiffier_rec.fxml"));
                Parent root = loader.load();
                Modiffier_recController controller = loader.getController();
                controller.initRec(r);

                Scene scene = new Scene(root);

                Stage stage = (Stage) global.getScene().getWindow();

                stage.setTitle("Modifier Reclamation");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
    }
    }

    @FXML
    private void allerajout(ActionEvent event) {
        try{
         Parent root = FXMLLoader.load(getClass().getResource("/gui/AjoutReclamation.fxml"));  
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

    @FXML
    private void open_accueil(ActionEvent event) {
         try
                                {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Volfront.fxml"));
                                    Parent root =loader.load();
                                    accueil.getScene().setRoot(root);
                                    VolfrontController rc =loader.getController();
                                    rc.setuser(user1);
                                    Scene scene = new Scene(root,893,394);
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
    private void open_profil(ActionEvent event) {
        try
                                {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ModifierUtilisateur.fxml"));
                                    Parent root =loader.load();
                                    accueil.getScene().setRoot(root);
                                    ModifierUtilisateurController controller = loader.getController();
                                    controller.initUser(user1);
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
    private void open_reclamation(ActionEvent event) {
         try
                                {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AjoutReclamation.fxml"));
                                    Parent root =loader.load();
                                    accueil.getScene().setRoot(root);
                                     AjoutReclamationController arc=loader.getController();
                                    arc.setuser(user1);
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
    private void open_abonnement(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Front_Abonnement.fxml"));
            Parent root = loader.load();
            accueil.getScene().setRoot(root);
            Front_AbonnementController arc = loader.getController();
            arc.setU(user1);
            Scene scene = new Scene(root);
            Stage SecondaryStage = new Stage();
            SecondaryStage.setTitle("Interplanetary");
            SecondaryStage.setScene(scene);
            SecondaryStage.show();
        } catch (Exception ex) {
            System.out.println("err:" + ex);
        }
    }

    @FXML
    private void open_formation(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Frontformation.fxml"));
            Parent root = loader.load();
            accueil.getScene().setRoot(root);
            FrontformationController arc = loader.getController();
            arc.setuser(user1);
            Scene scene = new Scene(root);
            Stage SecondaryStage = new Stage();
            SecondaryStage.setTitle("Interplanetary");
            SecondaryStage.setScene(scene);
            SecondaryStage.show();
        } catch (Exception ex) {
            System.out.println("err:" + ex);
        }
    }

    @FXML
    private void open_vehicule(ActionEvent event) {
        try
                                {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/front_vh.fxml"));
                                    Parent root =loader.load();
                                    accueil.getScene().setRoot(root);
                                    Front_vhController rc =loader.getController();
                                    rc.setuser(user1);
                                    
                                    Scene scene = new Scene(root,893,394);
                                    Stage SecondaryStage=new Stage();
                                    SecondaryStage.setTitle("Interplanetary");
                                    SecondaryStage.setScene(scene);
                                    SecondaryStage.show();
                                }
                                catch(Exception ex)
                                {
                                    System.out.println("err:"+ex);
                                }
        
    }
}
