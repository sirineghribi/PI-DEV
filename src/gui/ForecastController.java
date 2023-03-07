/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.WeatherData;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import services.MaintenanceServices;

/**
 * FXML Controller class
 *
 * @author abder
 */
public class ForecastController implements Initializable {

    @FXML
    private TextField hum;
    @FXML
    private TextField press;
    @FXML
    private TextField lat;
    @FXML
    private TextField lon;
    @FXML
    private TextField desc;
    @FXML
    private TextField country;
    @FXML
    private TextField temp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        WeatherData w = new MaintenanceServices().forecast();
        desc.setText(w.getDescription());
        hum.setText(w.getHumidity()+"");
        lon.setText(w.getLon()+"");
        lat.setText(w.getLat()+"");
        press.setText(w.getPressure()+"");
        country.setText(w.getName()+"");
        temp.setText(w.getTemp()+"");
        
        
    }    
    
}
