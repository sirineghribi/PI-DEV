/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entity.Abonnement;
import entity.Carte_fidelite;
import entity.Genre;
import entity.Type_abonnement;
import entity.Utilisateur;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import services.AbonnementService;
import services.CarteService;
import services.Type_abonnementService;

/**
 * FXML Controller class
 *
 * @author zied loukil
 */
public class Front_AbonnementController implements Initializable {

    @FXML
    private DatePicker date_achat;
    @FXML
    private DatePicker date_expiration;
    @FXML
    private Label l1;
    @FXML
    private Label l2;
    @FXML
    private Label l3;
    @FXML
    private Label l0;
    @FXML
    private GridPane grid;
    @FXML
    private TextField nom_type;
    @FXML
    private Button annulerBT;
    private Utilisateur u = new Utilisateur(2, "nom", "prenom", Genre.femme, "email", "mdp", Date.valueOf(LocalDate.now()));

    /**
     * initialises the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Utilisateur t = u;
        t.setId(1);
        setU(t);
    }

    public void setU(Utilisateur t) {
        u = t;
        List<Type_abonnement> tas = new Type_abonnementService().getAll();
        aff(tas);
    }

    private void alert(String a, String b) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(a);
        alert.setHeaderText(null);
        alert.setContentText(b);
        alert.show();
    }

    private void info(String a, String b) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(a);
        alert.setHeaderText(null);
        alert.setContentText(b);
        alert.show();
    }

    @FXML
    private void delete_abonnement(ActionEvent event) {
        Abonnement a = new AbonnementService().getAbonnement(u);
        new AbonnementService().supprimer(a);
        aff(new Type_abonnementService().getAll());
    }

    private void get_abonnement(ActionEvent event, Type_abonnement ta) {
        AbonnementService as = new AbonnementService();
        CarteService cs = new CarteService();
        if (!cs.findById_u(u.getId()).isEmpty()) {
            Carte_fidelite f = cs.findById_u(u.getId()).get(0);
            if (f.getNbr_point() >= ta.getPrix()) {
                f.setNbr_point((int) (f.getNbr_point() - ta.getPrix()));
                cs.modifier(f);
                if (as.HasAbonnement(u)) {
                    Abonnement a = new Abonnement(as.getAbonnement(u).getId(), Date.valueOf(LocalDate.now()), ta, u);
                    as.modifier(a);
                } else {
                    Abonnement a = new Abonnement(Date.valueOf(LocalDate.now()), ta, u);
                    as.ajouter(a);
                }
                info("Thanks for the SUB", "your credit is at " + f.getNbr_point());
            } else {
                alert("transaction failed", "you dont have enough credit");
            }
        } else {
            alert("transaction failed", "you need to have a loyalty card");
        }
        aff(new Type_abonnementService().getAll());
    }
    public void aff(List<Type_abonnement> tas)
    {
        AbonnementService as = new AbonnementService();
        if (as.HasAbonnement(u)) {
            Abonnement a = as.getAbonnement(u);
            nom_type.setText(a.getType().getNom());
            LocalDate date = LocalDate.parse((a.getD().toString()));
            date_achat.setValue(date);
            date = date.plusDays((long) a.getType().getPeriode());
            date_expiration.setValue(date);
        }
        l0.setVisible(!as.HasAbonnement(u));
        l1.setVisible(as.HasAbonnement(u));
        l2.setVisible(as.HasAbonnement(u));
        l3.setVisible(as.HasAbonnement(u));
        annulerBT.setVisible(as.HasAbonnement(u));
        nom_type.setVisible(as.HasAbonnement(u));
        date_achat.setVisible(as.HasAbonnement(u));
        date_expiration.setVisible(as.HasAbonnement(u));
        int r = 1;
        grid.add(new Label(" Nom de Type:"), 0, 0);
        grid.add(new Label(" description de Type:"), 0, 1);
        grid.add(new Label(" Periode de type:"), 0, 2);
        grid.add(new Label(" Offre de type:"), 0, 3);
        grid.add(new Label(" Prix de type:"), 0, 4);
        Label l = new Label(" Get/Switch to:");
        grid.add(l, 0, 5);
        for (Type_abonnement ta : tas) {
            grid.add(new Label(" " + ta.getNom()), r, 0);
            grid.add(new Label(" " + ta.getDescription()), r, 1);
            float periode = ta.getPeriode();
            int y = (int) (periode / 365);
            int m = (int) ((periode % 365) / 30);
            int d = (int) ((periode % 365) % 30);
            String s = y + " years," + m + " months and " + d + " days";
            grid.add(new Label(" " + s), r, 2);
            grid.add(new Label("" + (ta.getOffre() * 100) + "%"), r, 3);
            grid.add(new Label("" + ta.getPrix() + "pt"), r, 4);
            Button b = new Button(ta.getNom());
            b.setOnAction((event) -> get_abonnement(event, ta));
            grid.add(b, r, 5);
            r++;
        }
    }

}
