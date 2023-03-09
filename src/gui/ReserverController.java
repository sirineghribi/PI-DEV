/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import entity.Genre;
import entity.Reservation;
import entity.Utilisateur;
import entity.Vol;
import static gui.ReservationController.user;
import static gui.VolfrontController.user;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import services.ReservationService;
import services.VolService;
import javax.mail.* ; 
import javax.mail.internet.*;  

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
    @FXML
    private Label destination;
    @FXML
    private Label date;
    @FXML
    private Label montant;
    
    @FXML
    private Label consulter;
    
    
    static Utilisateur user ;
    @FXML
    private Button accueil1;
    @FXML
    private Button profil;
    @FXML
    private Button formation;
    @FXML
    private Button vehicule;
    public void setuser(Utilisateur user)
    {
       this.user=user; 
    }
    public void setVol(Vol vol) {
        this.vol = vol;
        System.out.println(vol);
      
        date.setText(""+vol.getDate());
        montant.setText(""+vol.getPrix()+" DT");
        destination.setText(""+vol.getDestination());
           
            reserver.setOnAction((ActionEvent event) -> { 
              
                
              Alert alert = new Alert(Alert.AlertType.ERROR);
       
        if (cin_txt.getText().equals("")||num_txt.getText().equals(""))
        {
            afficher_alerte ("Veuillez remplir tous les champs !");
        }
        else 
        {  int cin=Integer.valueOf(cin_txt.getText());
            int num=Integer.valueOf(num_txt.getText());
            boolean c=condition.isSelected();
       
           
            ReservationService rs = new ReservationService();
            Date dd=Date.valueOf(java.time.LocalDate.now());
            Reservation r=new Reservation(cin,num,1,"En attente",Date.valueOf("2023-02-23"),vol, user);
            if (c==true)
                    {  
                        
                       if (confirmerReservation()==1) 
                       {   if (rs.verif_exist(r)==false)
                       {
                           rs.ajouter(r); 
                           r=rs.findById(user.getId()).get(rs.findById(user.getId()).size()-1);
                           generer_QRcode(""+r.getUtilisateur().getId()+r.getVol().getId_v());
                           lire_QR(""+r.getUtilisateur().getId()+r.getVol().getId_v());
                           
                           envoyer_mail(r);
                           
                        System.out.println(r);
                                try
                                { 
                                    VolService volservice=new VolService();
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Reservation.fxml"));
                                    Parent root =loader.load();
                                    reserver.getScene().setRoot(root);
                                    ReservationController rc =loader.getController();
                                    rc.setUser(user);
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
                       else afficher_alerte ("Vous avez reservez ce vol  !");
                    }
                       else System.out.println("refusé");
                    }
            
            else 
        {
             afficher_alerte ("Vous devez acceptez les conditions !");
        }
        }
        });
        
            
    }
    private int confirmerReservation(){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Fenetre de confirmation");
        alert.setHeaderText("Réservation du VOL");
        alert.setContentText("Confirmez la réservation d'une place dans ce Vol?");
        ButtonType buttonTypeOne = new ButtonType("Oui");
        ButtonType buttonTypeTwo = new ButtonType("Non");
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == buttonTypeOne)
            return 1;
        else return 2;
    }   
     private void afficher_alerte (String a)
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur s'est produite lors de la reservation .");
        alert.setContentText(a);
        alert.showAndWait();  
          return;
    }
     
    public static void generateQRcode(String data, String path, String charset, Map map, int h, int w) throws WriterException, IOException  
{  
        //the BitMatrix class represents the 2D matrix of bits  
        //MultiFormatWriter is a factory class that finds the appropriate Writer subclass 
        //for the BarcodeFormat requested and encodes the barcode with the supplied contents.  
        BitMatrix matrix = new MultiFormatWriter().encode(new String(data.getBytes(charset), charset), BarcodeFormat.QR_CODE, w, h);  
        MatrixToImageWriter.writeToFile(matrix, path.substring(path.lastIndexOf('.') + 1), new File(path));  
}  
     private void generer_QRcode(String id) 
     {
        try {
            //data that we want to store in the QR code
            String str= String.valueOf(id);
            //path where we want to get QR Code
            String path = "C:\\Users\\lenovo\\Desktop\\S2\\Pidev\\java\\Ressources\\QR"+id+".png";
            //Encoding charset to be used
            String charset = "UTF-8";
            Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
            //generates QR code with Low level(L) error correction capability
            hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            //invoking the user-defined method that creates the QR code
            generateQRcode(str, path, charset, hashMap, 200, 200);//increase or decrease height and width accodingly
            //prints if the QR code is generated
            System.out.println("QR Code created successfully.");
        } catch (WriterException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
         
     }
     public static String readQRcode(String path, String charset, Map map) throws FileNotFoundException, IOException, NotFoundException  
     {  
         BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new FileInputStream(path)))));  
         Result rslt = new MultiFormatReader().decode(binaryBitmap);  
        return rslt.getText();  
      }  
     
     private void lire_QR(String id)
     {
        try {
            //path where the QR code is saved
            String path = "C:\\Users\\lenovo\\Desktop\\S2\\Pidev\\java\\Ressources\\QR"+id+".png";
            //Encoding charset to be used
            String charset = "UTF-8";
            Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
            //generates QR code with Low level(L) error correction capability
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L); 
            System.out.println("Data stored in the QR Code is: \n"+ readQRcode(path, charset, hintMap));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }
     }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void accueil(ActionEvent event) {
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
  private void envoyer_mail(Reservation r)
  {
      System.out.println(r);
      ReservationController rss=new ReservationController();
      rss.generer_pdf(r);
      
      
      final String from = "imen.mnejja@esprit.tn";
      final String password = "201JFT3419*/";
      String to = "imen.mnejja@esprit.tn";
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.user", from);
        properties.setProperty("mail.smtp.password", password);
        properties.setProperty("mail.smtp.port", "587");
        properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        
        
        
      Session session = Session.getInstance(properties, new Authenticator() {
          protected PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication(from, password);
          }
      });
      
      try {
          Message message = new MimeMessage(session);
          message.setFrom(new InternetAddress(from));
          message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
          message.setSubject("Reservation confirmé");
         // message.setText("Test message body");
          
        
         
        
        // Créer un objet MimeMultipart pour les pièces jointes
        MimeMultipart multipart = new MimeMultipart();
        String body = "<html><p>Bonjour,<br><br>Veuillez trouver ci-dessous votre confirmation de reservation.<br>Veuillez imprimer cette confirmation de reservation !"
                + "<br>Cordialement,<br><h2>Interplanetary<h2></p><img src='cid:logo' style='width:120px; height:120px;'> </html>";
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(body, "text/html");
        multipart.addBodyPart(messageBodyPart);
        MimeBodyPart imagePart = new MimeBodyPart();
          try {
              imagePart.attachFile("C:\\Users\\lenovo\\Desktop\\S2\\Pidev\\java\\Ressources\\logoforinterplanetary.png");
          } catch (IOException ex) {
              System.out.println(""+ex);
          }
            imagePart.setContentID("<logo>");
            imagePart.setDisposition(MimeBodyPart.INLINE);
             multipart.addBodyPart(imagePart);
       // Ajouter la pièce jointe PDF
        MimeBodyPart attachmentPart = new MimeBodyPart();
        DataSource source = new FileDataSource("C:\\Users\\lenovo\\Desktop\\S2\\Pidev\\java\\Ressources\\"+r.getUtilisateur().getId()+r.getVol().getId_v()+".pdf");
        attachmentPart.setDataHandler(new DataHandler(source));
        attachmentPart.setFileName("Reservation.pdf");
        multipart.addBodyPart(attachmentPart);

        // Ajouter le contenu du message et les pièces jointes au message
        message.setContent(multipart);
         
         Transport.send(message);
          System.out.println("Mail sent successfully.");
      } catch (MessagingException ex) {
          System.out.println(ex.getMessage());
      }
      
  }

    @FXML
    private void consulter(MouseEvent event) {
         try
                                {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/conditions.fxml"));
                                    Parent root =loader.load();
                                    Scene scene = new Scene(root);
                                    Stage SecondaryStage=new Stage();
                                    SecondaryStage.setTitle("Les conditions !");
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
    private void open_formation(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Frontformation.fxml"));
            Parent root = loader.load();
            accueil1.getScene().setRoot(root);
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
         try
                                {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/front_vh.fxml"));
                                    Parent root =loader.load();
                                    accueil1.getScene().setRoot(root);
                                    Front_vhController rc =loader.getController();
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
}
