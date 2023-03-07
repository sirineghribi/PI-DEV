/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailSender {
   private static final String YOUR_EMAIL = "zeineb.benmami@esprit.tn"; // Entrez votre adresse e-mail ici
    private static final String YOUR_PASSWORD = "201JFT3257"; // Entrez votre mot de passe ici
    private static final String SMTP_HOST = "smtp.gmail.com"; // Entrez l'hôte SMTP de votre fournisseur de messagerie ici
    
    public static void sendEmail(String to, String subject, String body) {
        // Paramètres de connexion SMTP
        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");

        // Création d'une session avec l'authentification
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(YOUR_EMAIL, YOUR_PASSWORD);
            }
        });

        try {
            // Création d'un message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(YOUR_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);
            
            
            
// Création de la partie texte
        MimeMultipart multipart = new MimeMultipart("related");
        
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(body, "UTF-8", "html");

        // Ajout du bouton HTML
        String button = "<div><a href=\"confirmation\">Confirmer l'inscription</a></div>";
        MimeBodyPart buttonPart = new MimeBodyPart();
        buttonPart.setContent(button, "text/html; charset=utf-8");
        multipart.addBodyPart(messageBodyPart);
        multipart.addBodyPart(buttonPart);
        message.setContent(multipart);

        
            // Envoi du message
            Transport.send(message);

            System.out.println("E-mail envoyé avec succès.");

        } catch (MessagingException e) {
            System.out.println("Erreur lors de l'envoi de l'e-mail : " + e.getMessage());
        }
    }
}