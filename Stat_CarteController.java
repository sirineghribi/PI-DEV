/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import services.CarteService;

/**
 * FXML Controller class
 *
 * @author Zeineb Ben Mami
 */
public class Stat_CarteController implements Initializable {

    @FXML
    private LineChart<String, Number> stat;
   int year=2020;
   XYChart.Series<String,Number> series = new XYChart.Series<>();

    /**
     * Initializes the controller ciass.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        CarteService c= new CarteService();
        
        
        // Ajouter les données      
        try{
            
        
        c.getAll().forEach((cf)->{
        
        int nbex=c.find_byYear(year++).size();
        
        System.out.println(c.getAll()+"nbex:"+nbex);
        series.getData().add(new XYChart.Data<>("year:"+year,nbex));
        });
        series.setName("Nombre de carte de fidelité par an ");
        stat.getData().add(series);
        }
        catch(Exception ex)
        {
            System.out.println("err"+ex.getMessage());
        }
    }    
    
}
