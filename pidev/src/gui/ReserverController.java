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
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import entity.Reservation;
import entity.Utilisateur;
import entity.Vol;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
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
    @FXML
    private Label destination;
    @FXML
    private Label date;
    @FXML
    private Label montant;
    @FXML
    private Button accueil;

    public void setVol(Vol vol) {
        this.vol = vol;
        System.out.println(vol);
      
        date.setText(""+vol.getDate());
        montant.setText(""+vol.getPrix()+" DT");
        destination.setText(""+vol.getDestination());
           
            reserver.setOnAction((ActionEvent event) -> { 
              
                
              Alert alert = new Alert(Alert.AlertType.ERROR);
       
        if (cin_txt.getText()==null||num_txt.getText()==null)
        {
            afficher_alerte ("Veuillez remplir tous les champs !");
        }
        
            int cin=Integer.valueOf(cin_txt.getText());
            int num=Integer.valueOf(num_txt.getText());
            boolean c=condition.isSelected();
       
            Utilisateur u3 = new Utilisateur(3,"Mnejja","Imen","femme","imen.mnejja@esprit.tn","123",Date.valueOf("2002-01-10") );
            ReservationService rs = new ReservationService();
            Date dd=Date.valueOf(java.time.LocalDate.now());
            Reservation r=new Reservation(cin,num,1,"En attente",Date.valueOf("2023-02-23"),vol, u3);
            if (c==true)
                    {  
                        
                       if (confirmerReservation()==1) 
                       {   if (rs.verif_exist(r)==false)
                       {
                           rs.ajouter(r); 
                           generer_QRcode(""+r.getUtilisateur().getId()+r.getVol().getId_v());
                           lire_QR(""+r.getUtilisateur().getId()+r.getVol().getId_v());
                           
                        System.out.println(r);
                                try
                                { 
                                    VolService volservice=new VolService();
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Reservation.fxml"));
                                    Parent root =loader.load();
                                    reserver.getScene().setRoot(root);
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
                       else afficher_alerte ("Vous avez reservez ce vol  !");
                    }
                       else System.out.println("refusé");
                    }
            
            else 
        {
             afficher_alerte ("Vous devez acceptez les conditions !");
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

    @FXML
    private void accueil(ActionEvent event) {
         try
             {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Volfront.fxml"));
                Parent root =loader.load();
                accueil.getScene().setRoot(root);
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
