/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entity.Abonnement;
import entity.Type_abonnement;
import entity.Utilisateur;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import services.AbonnementService;
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

    /**
     * initialises the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Utilisateur u=new Utilisateur(1, "nom", "prenom", "genre", "email", "mdp",Date.valueOf(LocalDate.now()));
        AbonnementService as=new AbonnementService();
        if(as.HasAbonnement(u))
        {
            Abonnement a=as.getAbonnement(u);
            nom_type.setText(a.getType().getNom());
            LocalDate date = LocalDate.parse((a.getD().toString()));
            date_achat.setValue(date);
            date=date.plusDays((long) a.getType().getPeriode());
            date_expiration.setValue(date);
        }
        l0.setVisible(!as.HasAbonnement(u));
        l1.setVisible(as.HasAbonnement(u));
        l2.setVisible(as.HasAbonnement(u));
        l3.setVisible(as.HasAbonnement(u));
        nom_type.setVisible(as.HasAbonnement(u));
        date_achat.setVisible(as.HasAbonnement(u));
        date_expiration.setVisible(as.HasAbonnement(u));
        List<Type_abonnement> tas=new Type_abonnementService().getAll();
        int c=0;
        int r=1;
        grid.add(new Label(" Nom de Type:"),0, 0);
        grid.add(new Label(" description de Type:"),0, 1);
        grid.add(new Label(" Periode de type:"),0, 2);
        grid.add(new Label(" Offre de type:"),0, 3);
        grid.add(new Label(" Prix de type:"), 0, 4);
        for(Type_abonnement ta:tas)
        {
        grid.add(new Label(" "+ta.getNom()),r, 0);
        grid.add(new Label(" "+ta.getDescription()),r, 1);
        float periode=ta.getPeriode();
        int y=(int) (periode/365);
        int m=(int) ((periode%365)/30);
        int d=(int) ((periode%365)%30);
        String s=y+" years,"+m+" months and "+d+" days";
        grid.add(new Label(" "+s),r, 2);
        grid.add(new Label(""+(ta.getOffre()*100)+"%"), r, 3);
        grid.add(new Label(""+ta.getPrix()+"DT"), r, 4);
        r++;
        }
    }    
    
}
