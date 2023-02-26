/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entity.Type_abonnement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.Type_abonnementService;

/**
 * FXML Controller class
 *
 * @author zied loukil
 */
public class Update_Type_AbonnementController implements Initializable {

    @FXML
    private Button update_type;
    @FXML
    private Button cancel_type;
    private Type_abonnement t;
    @FXML
    private TextField nom_type;
    @FXML
    private TextField desc_type;
    @FXML
    private TextField prix_type;
    @FXML
    private TextField offre_type;
    @FXML
    private TextField periode_type;

    /**
     * initialises the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("init");
    }   
      private boolean test()
    {
        try
        {
            float prix=Float.valueOf((prix_type.getText()));
            float offre=Float.valueOf((offre_type.getText()));
            float periode=Float.valueOf((periode_type.getText())); 
            return (periode>0)&&(prix>0)&&(offre>0)&&(offre<100);
        }
        catch(Exception ex)
        {
           return false;         
        }
    }
    public void SetType_Abonnement(Type_abonnement t)
    {
        System.out.println("hi");
        nom_type.setText(t.getNom());
        desc_type.setText(t.getDescription());
        prix_type.setText(""+t.getPrix());
        offre_type.setText(t.getOffre()*100+"");
        periode_type.setText(""+t.getPeriode());
        this.t=t;
    }
    /*public void clear_TextField()
    {
    nom_type.clear();
    desc_type.clear();
    prix_type.clear();
    offre_type.clear();
    periode_type.clear();
    }*/
    private void alert(String a,String b)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(a);
        alert.setHeaderText(null);
        alert.setContentText(b);
        alert.show();
    }
    
    @FXML
    private void update_type(ActionEvent event) {
        if(test())
        {
        t.setNom(nom_type.getText());
        t.setDescription(desc_type.getText());
        t.setOffre(Float.valueOf(offre_type.getText())/100);
        t.setPeriode(Float.valueOf(periode_type.getText()));
        t.setPrix(Float.valueOf(prix_type.getText()));
        new Type_abonnementService().modifier(t);
        load_list();
        }
        else
        {
           alert("Failed to modify type_abonnement","Check your inputs");
        }
    }

    @FXML
    private void cancel_update(ActionEvent event) {
        load_list();
    }
    public void load_list()
    {
         try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Add_Type_Abonnement_FXML.fxml"));
            Parent root =loader.load();
            Add_Type_Abonnement_FXMLController ac =loader.getController();
            Stage s=(Stage)(desc_type.getScene().getWindow());
            s.close();
            Scene scene = new Scene(root,750,450);
            Stage SecondaryStage=new Stage();
            /*SecondaryStage.setX(0);
            SecondaryStage.setY(0);*/
            SecondaryStage.setTitle("Type_Abonnement!");
            SecondaryStage.setScene(scene);
            SecondaryStage.show();
        }
        catch(Exception ex)
        {
            System.out.println("err:"+ex);  
        }
    }
}
