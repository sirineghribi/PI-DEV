/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Vehicule;
import entity.Vol;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import services.VehiculeServices;
import services.VolService;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class ModifierVolController implements Initializable {

    
    @FXML
    private Button modifier_btn;
    @FXML
    private ComboBox<Integer> vehicule_choixm;
    @FXML
    private TextField destination_txtm;
    @FXML
    private TextField prix_txtm;
    @FXML
    private DatePicker date_choixm;
  
    private Vol vol;

    public void setVol(Vol vol) {
        this.vol = vol;
        System.out.println(vol);
        destination_txtm.setText(vol.getDestination());
        prix_txtm.setText(""+vol.getPrix());
        vehicule_choixm.setValue(vol.getMt().get_id_vehicule());
        LocalDate date = LocalDate.parse(""+vol.getDate());
        date_choixm.setValue(date);
        
        modifier_btn.setOnAction((ActionEvent event) -> {
        String destination=destination_txtm.getText();
        float prix = Float.valueOf(prix_txtm.getText());
        int v=Integer.valueOf(vehicule_choixm.getValue().toString());
        VehiculeServices vs=new VehiculeServices();
        Vehicule vehicule=vs.findById(v).get(0);
        Date datee=Date.valueOf(date_choixm.getValue());
            System.out.println(vol.getId_v());
        VolService volservice=new VolService();
       
        Vol vol1 =new Vol(vol.getId_v(),destination,"planifié",prix,datee,vehicule);
         System.out.println(vol1);
        volservice.modifier(vol1);
                               
                                     });
                     
                 
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      VolService volservice=new VolService();
        List<Integer> l= volservice.id_vehicule_list();
       vehicule_choixm.setItems(observableArrayList(l));  
        
    }    
    
    
   
    
}
