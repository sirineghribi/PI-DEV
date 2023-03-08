/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Avis;
import entity.Genre;
import entity.NoteA;
import entity.Utilisateur;
import entity.Vol;
import static gui.ModifierUtilisateurController.user1;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import services.AvisService;
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
    static Utilisateur user;
    @FXML
    private Button accueil;
    @FXML
    private Button profil;
    @FXML
    private ComboBox<NoteA> combon;
    @FXML
    private TextArea desc;
    @FXML
    private Button publier;
    @FXML
    private Button consulter;
    @FXML
    private Button rec;
    @FXML
    private Button abonnement;

    /**
     * Initializes the controller class.
     */
    public void setuser(Utilisateur user) {
        this.user = user;
        System.out.println(user);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<NoteA> opt = FXCollections.observableArrayList(NoteA.Excellent, NoteA.Bien, NoteA.Moyen, NoteA.Mauvais);
        combon.setPromptText("choix du type ");
        combon.setItems(opt);

        VolService volservice = new VolService();
        List<Vol> vols = volservice.getAll();

        ObservableList<Vol> liste = FXCollections.observableArrayList(volservice.getAll());
        scroll.setStyle("background-color: #712194;");
        int r = 0;
        for (Vol v : vols) {

            Button b = new Button("Destination :" + v.getDestination() + "\t Prix :" + v.getPrix() + "\n Date :" + v.getDate());
            b.setLayoutY(b.getHeight() + 3);
            b.setPrefSize(1000, 1000);
            b.setStyle("-fx-text-fill: white; ");
            b.setStyle("-fx-border-color: black;");

            grd.add(b, 0, r);
            r++;

            b.setOnAction(event -> {
                System.out.println(v);
                vvv = v;
                name.setText("Vol : Destination " + v.getDestination());

            });

            publier.setOnAction(event -> {
                NoteA na = combon.getValue();
                String description = desc.getText();

                if (!(combon == null || desc.getText().equals(""))) {

                    Avis a = new Avis(na, description, user, vvv);

                    AvisService as = new AvisService();
                    as.ajouter(a);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText("Ajout avec succées!");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error alert");
                    alert.setHeaderText("Les champs de texte d'un formulaire ne doivent pas être vide");
                    alert.showAndWait();
                }

            });

        }

        button.setOnAction(event -> {
            // if (v.getNbr_place()>0)
            if ((vvv != null) && (vvv.getNbr_place() > 0)) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/reserver.fxml"));
                    Parent root = loader.load();
                    button.getScene().setRoot(root);
                    ReserverController rc = loader.getController();
                    rc.setVol(vvv);
                    rc.setuser(user);
                    Scene scene = new Scene(root);
                    Stage SecondaryStage = new Stage();
                    SecondaryStage.setTitle("Reserver Vol !");
                    SecondaryStage.setScene(scene);
                    SecondaryStage.show();
                } catch (Exception ex) {
                    System.out.println("err:" + ex);
                }

            } else {
                if (vvv == null) {
                    afficher_alerte("Aucun element est séléctionné !");
                }
                if (vvv.getNbr_place() <= 0) {
                    afficher_alerte("Ce vol n'est plus disponible !");
                }

            }
        });

    }

    private void afficher_alerte(String a) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite lors de la reservation !");
        alert.setContentText(a);
        alert.showAndWait();
        return;
    }

    @FXML
    private void historique(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Reservation.fxml"));
            Parent root = loader.load();
            button.getScene().setRoot(root);
            ReservationController rc = loader.getController();
            rc.setUser(user);
            System.out.println(user);
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
    private void open_accueil(ActionEvent event) {
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
            SecondaryStage.setTitle("Interplanetary");
            SecondaryStage.setScene(scene);
            SecondaryStage.show();
        } catch (Exception ex) {
            System.out.println("err:" + ex);
        }
    }

    @FXML
    private void consulter(ActionEvent event) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/Front_Avis.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Probleme:" + e);
        }
    }

    @FXML
    private void open_reclamation(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AjoutReclamation.fxml"));
            Parent root = loader.load();
            accueil.getScene().setRoot(root);
            AjoutReclamationController arc = loader.getController();
            arc.setuser(user);
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
    private void open_abonnement(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Front_Abonnement.fxml"));
            Parent root = loader.load();
            accueil.getScene().setRoot(root);
            Front_AbonnementController arc = loader.getController();
            arc.setU(user);
            Scene scene = new Scene(root);
            Stage SecondaryStage = new Stage();
            SecondaryStage.setTitle("Interplanetary");
            SecondaryStage.setScene(scene);
            SecondaryStage.show();
        } catch (Exception ex) {
            System.out.println("err:" + ex);
        }
    }
}
