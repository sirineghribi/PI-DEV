/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Avis;
import entity.NoteA;
import entity.Reclamation;
import entity.Typerec;
import entity.NoteA;
import entity.Vol;
import services.AvisService;
import services.VolService;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sirin
 */
public class StatController implements Initializable {

    @FXML
    private BarChart<String, Number> chart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        VolService v= new VolService();
     
        AvisService as= new AvisService();
Avis r = new Avis();
        // Ajouter les données
        new VolService().getAll().forEach((vv)->{
       int nbex=as.search_vol(vv).stream().filter((a)->a.getNote().equals(NoteA.Excellent)).collect(Collectors.toList()).size();
       int nbb=as.search_vol(vv).stream().filter((a)->a.getNote().equals(NoteA.Bien)).collect(Collectors.toList()).size();
       int nbmo=as.search_vol(vv).stream().filter((a)->a.getNote().equals(NoteA.Moyen)).collect(Collectors.toList()).size();
       int nma=as.search_vol(vv).stream().filter((a)->a.getNote().equals(NoteA.Mauvais)).collect(Collectors.toList()).size();
       int val=(nbex*5+nbb*4+nbmo*3+nma*2)/14;
          // System.out.println(as.getAll()+"nbex:"+nbex);
           //System.out.println(as.getAll()+"nbb:"+nbb);


       
        XYChart.Series<String,Number> series = new XYChart.Series<>();
        series.setName("Vol:"+vv.getDestination());
        series.getData().add(new XYChart.Data<>("vol"+vv.getId_v(),val));
        /*series.getData().add(new XYChart.Data<>(NoteA.Bien.toString(),nbb));
        series.getData().add(new XYChart.Data<>(NoteA.Moyen.toString(), nbmo));
        series.getData().add(new XYChart.Data<>(NoteA.Mauvais.toString(),nma));*/
        

        chart.getData().add(series);
                });
    }

        // Ajouter le graphique au conteneur
      
        // TODO

    @FXML
    private void backba(ActionEvent event) {
          try{
         Parent root = FXMLLoader.load(getClass().getResource("/gui/Back_avis.fxml"));  
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
    



