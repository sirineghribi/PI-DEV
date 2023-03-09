/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Avis;
import static gui.ModifierUtilisateurController.user1;
import static gui.VolfrontController.user;
import services.AvisService;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sirin
 */
public class Front_AvisController implements Initializable {

    private TableView<Avis> tabavis;
    @FXML
    private Button mod;
    @FXML
    private Button supp;
    private TableColumn<?, ?> desccola;
    @FXML
    private ImageView global;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grd;
static Avis avv;
    @FXML
    private Button accueil;
    @FXML
    private Button profil;
    @FXML
    private Button rec;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AvisService as = new AvisService();
         int rr=0;
        for (Avis av : as.afficher_Avis()){


               Button b=new Button("Description :"+av.getDescription());
               b.setPrefSize(350, 350);
               b.setStyle("-fx-text-fill: white; ");
               b.setStyle("-fx-border-color: black;");
               b.setStyle("-fx-background-color:#7601351;");

 
               grd.add(b,0, rr);
               rr++;

               b.setOnAction(event -> {
               System.out.println(av);
              avv=av;
               

        });


    }   } 

    
    @FXML
    private void Modifier_Avis(ActionEvent event) {
             Avis a= tabavis.getSelectionModel().getSelectedItem();
    if (a!= null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Modifier_avis.fxml"));
                Parent root = loader.load();
                Modifier_avisController controller = loader.getController();
                controller.initRec(a);

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
    private void Supprimer_Avis(ActionEvent event) {
        Avis a= tabavis.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(AlertType.CONFIRMATION);
       AvisService as= new AvisService();   
       
        if (a!=null){
            
        
        alert.setTitle("Confirmation de suppression");
         alert.setHeaderText("Confiramation de suppression");
         alert.setContentText("Voulez-vous vraiment supprimer cette reclamation!");
    
        Optional<ButtonType> result = alert.showAndWait();
     
       if (result.get() == ButtonType.OK){

            as.supprimer(a);
           as.afficher_Avis();
       }
        
    }
  else
    {
           Alert al = new Alert(AlertType.ERROR);

           al.setTitle("Error alert");
           al.setHeaderText("Vous devez selectionner au moins une reclamation pour supprimer");
   
            al.showAndWait();
    }
    }

    @FXML
    private void allerajout(ActionEvent event) {
        try{
         Parent root = FXMLLoader.load(getClass().getResource("/gui/Volfront.fxml"));  
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
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ModifierUtilisateur.fxml"));
            Parent root = loader.load();
            accueil.getScene().setRoot(root);
            ModifierUtilisateurController controller = loader.getController();
            controller.initUser(user);
            Scene scene = new Scene(root);
            Stage SecondaryStage = new Stage();
            SecondaryStage.setTitle("Reserver Vol !");
            SecondaryStage.setScene(scene);
            SecondaryStage.show();
        } catch (Exception ex) {
            System.out.println("err:" + ex);
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
