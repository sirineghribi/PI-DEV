/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import services.MaintenanceServices;

/**
 * FXML Controller class
 *
 * @author abder
 */
public class StatisticsmaintController implements Initializable {

    @FXML
    private PieChart bar;
    float total=0 ;
    int i=0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    ObservableList<PieChart.Data> datastat= FXCollections.observableArrayList();
        new MaintenanceServices ().getAll().forEach((m)->total+=m.getCout());
        new MaintenanceServices ().getAll().forEach((m)->{
        float val=m.getCout();
        String s="Maintenance:"+m.getId_m()+" "+(int)(val*100/total);
        datastat.add(new PieChart.Data(s,val));
        
        });
    bar.setData(datastat);
        
        
        
    }    

    
}
