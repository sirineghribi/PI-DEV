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
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import entity.Reservation;
import entity.Utilisateur;
import static gui.AjoutReclamationController.user;
import static gui.ReserverController.user;
import static gui.VolfrontController.user;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.ReservationService;


/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class ReservationController implements Initializable {

   
    static Utilisateur user;
    
    
    ReservationService rs = new ReservationService();
  //  private Button accueil;
    @FXML
    private TableColumn<Reservation, String> date;
    @FXML
    private TableColumn<Reservation, String> montant;
    @FXML
    private TableColumn<Reservation, String> destination;
    @FXML
    private TableColumn<Reservation, String> date_vol;
    @FXML
    private TableColumn<Reservation, String> etat;
    @FXML
    private TableView<Reservation> tableview;
    @FXML
    private Button accueil1;
    @FXML
    private Button profil;
    @FXML
    private Button abonnement;
    @FXML
    private Button reclamation;
    
    
    public void setUser(Utilisateur user) {
        this.user = user;
        System.out.println(user); 
        ObservableList<Reservation> liste=FXCollections.observableArrayList(rs.findById(user.getId()));
        setTable(liste);   
    }
   
    private void setTable(ObservableList<Reservation> liste)
    {
        tableview.setItems(liste);
        tableview.setStyle("-fx-background-color: #712194");
        
        montant.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(String.valueOf(cell.getValue().getPrix()));
            return s;
        });
        destination.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(String.valueOf(cell.getValue().getVol().getDestination()));
            return s;
        });
        date_vol.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(String.valueOf(cell.getValue().getVol().getDate()));
            return s;
        });
        etat.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(String.valueOf(cell.getValue().getEtat()));
            return s;
        });
        date.setCellValueFactory(cell -> {
            StringProperty s=new SimpleStringProperty();
            s.set(String.valueOf(cell.getValue().getDate_res()));
            return s;
        });
        
       /* Callback<TableColumn<Reservation, String>, TableCell<Reservation, String>> cellFactory= (final TableColumn<Reservation, String> param) -> {
         final TableCell<Reservation, String> cell = new TableCell<Reservation, String>() {
             
             final Button btn = new Button("Details");
             
             @Override
             public void updateItem(String item, boolean empty) {
                 super.updateItem(item, empty);
                 if (empty) {
                     setGraphic(null);
                     setText(null);
                 } else {
                     btn.setOnAction(event -> {
                         Reservation r = getTableView().getItems().get(getIndex());
                              generer_pdf(r);
                             
                            });
                     btn.setStyle("-fx-text-fill: white;");
                     

                     setGraphic(btn);
                     setText(null);
                 }
             }
         };
         return cell;
    };
      details.setCellFactory(cellFactory);  */
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
        
        
        
    }    
    public void generer_pdf(Reservation r)
    {
     

        Document pdf = new Document();  
        try {
                
                 PdfWriter.getInstance(pdf, new FileOutputStream("C:\\Users\\lenovo\\Desktop\\S2\\Pidev\\java\\Ressources\\"+r.getUtilisateur().getId()+r.getVol().getId_v()+".pdf"));
                pdf.open();
                Font font = new Font(Font.FontFamily.TIMES_ROMAN, 24);   
                Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 10); 
                Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 12); 
                Paragraph p=new Paragraph("Veuillez trouver ci-dessous ",font);
                Paragraph pp=new Paragraph("Votre confirmation de reservation ",font);
                Paragraph p1=new Paragraph(" Date de reservation  \n"+r.getDate_res(),font1);
                Paragraph pp1=new Paragraph("Veuillez imprimer cette confirmation de reservation !",font1);
                p1.setAlignment(Element.ALIGN_RIGHT);
                Image image = Image.getInstance("C:\\Users\\lenovo\\Desktop\\S2\\Pidev\\java\\Ressources\\logoforinterplanetary.png");
                image.scaleAbsolute(70f, 70f);
                image.setAlignment(Element.ALIGN_RIGHT);
                pdf.add(image);
                pdf.add(p);
                pdf.add(pp);
                pdf.add(p1);
                pdf.add(pp1);
                pdf.add(new Paragraph("\n \t Nom : " + r.getUtilisateur().getNom()+" \n \t Prenom : "+r.getUtilisateur().getPrenom(),font2));
                pdf.add(new Paragraph("\t Cin : " + r.getCin()+" \n \t Numero de téléphone : " +r.getNum_phone()+"\n \t Montant payé : "+r.getPrix()+" DT",font2));
                pdf.add(new Paragraph("\n \n")); 
                LineSeparator line = new LineSeparator();
                line.setLineWidth(0.5f);
                pdf.add(line);
                pdf.add(new Paragraph("\n")); 
                pdf.add(new Paragraph("\t Destination : " + r.getVol().getDestination()+" \n \t Date du vol : " +r.getVol().getDate()+"\n \t Prix : "+r.getVol().getPrix()+" DT",font2));
                Image imageQR = Image.getInstance("C:\\Users\\lenovo\\Desktop\\S2\\Pidev\\java\\Ressources\\QR"+r.getUtilisateur().getId()+r.getVol().getId_v()+".png");
                imageQR.scaleAbsolute(200f, 200f);
                imageQR.setAlignment(Element.ALIGN_CENTER);
                pdf.add(imageQR);
                pdf.close();
                System.out.println("pdf done");
                
            } catch (IOException | DocumentException e) {
                e.printStackTrace();
            }
        
        
         
    }
  

    @FXML
    private void open_accueil(ActionEvent event) {
         try
                                {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Volfront.fxml"));
                                    Parent root =loader.load();
                                    accueil1.getScene().setRoot(root);
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
    private void open_profil(ActionEvent event) {
         try
                                {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ModifierUtilisateur.fxml"));
                                    Parent root =loader.load();
                                    accueil1.getScene().setRoot(root);
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

    @FXML
    private void open_abonnement(ActionEvent event) {
        
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Front_Abonnement.fxml"));
            Parent root = loader.load();
            accueil1.getScene().setRoot(root);
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
            accueil1.getScene().setRoot(root);
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
    }
    
