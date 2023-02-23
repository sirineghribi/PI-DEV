/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Formation;
import entity.Utilisateur;
import entity.typeformation;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import services.FormationeServices;

/**
 * FXML Controller class
 *
 * @author abder
 */
public class ModifyFormationController implements Initializable {

    @FXML
    private ComboBox<typeformation> preptype;
    @FXML
    private TextField nbrhours;
    @FXML
    private Button modifyformation;
    @FXML
    private DatePicker datepicker;
    private Formation fo;

    public void setFo(Formation fo) {
        this.fo = fo;
        System.out.println(fo);
        nbrhours.setText(String.valueOf(fo.getNbrheur()));
        preptype.setValue(fo.getType());
        LocalDate date = LocalDate.parse(""+fo.getDate());
        datepicker.setValue(date);
        modifyformation.setOnAction((ActionEvent event) -> {

int nbrh=Integer.valueOf(nbrhours.getText());
        Date date1=Date.valueOf(datepicker.getValue());
        typeformation T=preptype.getValue();
        FormationeServices fs=new FormationeServices();
        Utilisateur u3 = new Utilisateur(2,"mn","imen","femme","imenmn@gmail.com","ii",Date.valueOf("2002-06-26") );
        
        Formation f =new Formation(fo.getId_f(),u3,T,date1,nbrh);
            System.out.println(T);
        fs.modifier(f);
        
                                     });
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<typeformation> L=FXCollections.observableArrayList(typeformation.gforceprep,typeformation.muscleprep,typeformation.skeletonprep);
       preptype.setItems(L);
    }    

    
}
