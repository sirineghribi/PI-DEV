/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
//import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
//import javax.swing.event.ChangeListener;
import entity.CategorieVehicule;
import entity.Utilisateur;
import entity.Vehicule;
import static gui.ModifierUtilisateurController.user1;
import static gui.VolfrontController.user;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import services.VehiculeServices;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class Front_vhController implements Initializable {

    private ListView<Vehicule> myTableView;
    Vehicule p;
    Vehicule CurrentVh;
    @FXML
    private Button button;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grd;
    @FXML
    private Label name;
    @FXML
    static Button vehicule;
    @FXML
    private ImageView vehicule_img1;
    @FXML
    private ImageView vehicule_img2;
    @FXML
    private ImageView vehicule_img3;
    @FXML
    private ImageView vehicule_img4;
    @FXML
    private Button accueil;
    @FXML
    private Button profil;
    @FXML
    private Button abonnement;
    @FXML
    private Button rec;
    @FXML
    private Button formation;
    Utilisateur user;
    /**
     * Initializes the controller class.
     */
    public void setuser(Utilisateur user)
    {
        this.user=user;
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        VehiculeServices vs = new VehiculeServices();
        vehicule_img1.setVisible(false);
        vehicule_img2.setVisible(false);
        vehicule_img3.setVisible(false);
        vehicule_img4.setVisible(false);

        List<Vehicule> v1 = vs.getAll();

        ObservableList<Vehicule> liste = FXCollections.observableArrayList(vs.getAll());
        scroll.setStyle("background-color: #712194;");
        int r = 0;
        for (Vehicule v : v1) {

            Button b = new Button("Nom vh :" + v.getNom_vh() + "\t nom cat :" + v.getCategorieVehicule().get_nom_cat());
            b.setPrefSize(1000, 1000);
            b.setStyle("-fx-text-fill: white; ");

            grd.add(b, 0, r);
            r++;

            b.setOnAction(event -> {
                System.out.println(v);
               p = v;

                name.setText("Vehicule :  " + v.getNom_vh());
                set_image(v);
            });
        }

        button.setOnAction(event -> {
            if (p != null) {
                vs.genererPdf(p);
            } else {

                System.out.println("Aucun élément sélectionné !s");
            }
        });
    }

    /*
    public void genererPdf(Vehicule v){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File file = fileChooser.showSaveDialog(null);
if (file != null)
{
try {
                Document pdf = new Document();
                PdfWriter.getInstance(pdf, new FileOutputStream(file));
                pdf.open();
                Font font = new Font(Font.FontFamily.TIMES_ROMAN, 24);   
                Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 10); 
                Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 12); 
                Paragraph p=new Paragraph("Veuillez trouver ci-dessous ",font);
                pdf.add(new Paragraph("\t nom_vh : " + v.getNom_vh()+" \n \t Numero de téléphone : " +r.getNum_phone()+"\n \t Montant payé : "+r.getPrix()+" DT",font2));
                Image image = Image.getInstance("C:\\Users\\lenovo\\Desktop\\S2\\Pidev\\java\\Ressources\\logoforinterplanetary.png");
                image.scaleAbsolute(70f, 70f);
                image.setAlignment(Element.ALIGN_RIGHT);
                pdf.add(image); 
                pdf.add(p);  
               
                pdf.close();
                
            } catch (IOException | DocumentException e) {
                e.printStackTrace();
            }


}
    }
     */
    private void set_image(Vehicule p) {
        String chemin;

        if (p.getNom_vh().equals("vv1")) {
            vehicule_img1.setVisible(true);
            vehicule_img2.setVisible(false);
            vehicule_img3.setVisible(false);
            vehicule_img4.setVisible(false);
            System.out.println("1");

        } else if (p.getNom_vh().equals("v2")) {
            vehicule_img1.setVisible(false);
            vehicule_img2.setVisible(true);
            vehicule_img3.setVisible(false);
            vehicule_img4.setVisible(false);
            System.out.println("2");
        } else if (p.getNom_vh().equals("v3")) {
            vehicule_img1.setVisible(false);
            vehicule_img2.setVisible(false);
            vehicule_img3.setVisible(true);
            vehicule_img4.setVisible(false);
            System.out.println("3");
        } else if (p.getNom_vh().equals("v4")) {
            vehicule_img1.setVisible(false);
            vehicule_img2.setVisible(false);
            vehicule_img3.setVisible(false);
            vehicule_img4.setVisible(true);
            System.out.println("4");

        } else {
            vehicule_img1.setVisible(false);
            vehicule_img2.setVisible(false);
            vehicule_img3.setVisible(false);
            vehicule_img4.setVisible(false);
            System.out.println("4");
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
                                    rc.setuser(user);
                                    
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
    private void open_formation(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Frontformation.fxml"));
            Parent root = loader.load();
            accueil.getScene().setRoot(root);
            FrontformationController arc = loader.getController();
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
    private void open_vehicule(ActionEvent event) {
    }
}
