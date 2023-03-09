package gui;



import entity.Reclamation;
import services.ReclamationService;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FXMLDocumentController {


   

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button sm;
    @FXML
    private TextField toField;
    @FXML
    private TextField subjectField;
    @FXML
    private TextArea messageArea;
      private Reclamation rec;
   
    void sendMail() {
        //smartwheels0@gmail.com
        //sivxbctdmnybwtli
        String fromEmail = "sirine.ghribi@esprit.tn";
        String password = "201JFT307330";
        String toEmail = toField.getText();
        String subject = subjectField.getText();
        String message = messageArea.getText();

       Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromEmail));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            msg.setSubject(subject);
            msg.setText(message);
            Transport.send(msg);
            System.out.println("Mail sent to " + toEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    void initRec(Reclamation a) {
        this.rec = a;
    toField.setText(a.getUtilisateur().getEmail());
    sm.setOnAction(event -> {
       String description = toField.getText();
    a.setEtat("traité");
    sendMail();
       ReclamationService rs = new ReclamationService();
       rs.modifieretat("traité",a);
     
                            });
    }

    @FXML
    private void backrec(ActionEvent event) {
         try{
         Parent root = FXMLLoader.load(getClass().getResource("/gui/Back_reclamation.fxml"));  
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
    }

