/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entity.Abonnement;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import services.AbonnementService;

/**
 * FXML Controller class
 *
 * @author zied loukil
 */
public class Statistic_abonnementController implements Initializable {

    @FXML
    private PieChart pie_abonnement;
    @FXML
    private Button back_bt;
    @FXML
    private Button export_bt;

    /**
     * initialises the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*Label caption=new Label("");*/
 /*for(PieChart.Data data:pie_abonnement.getData())
        {
        data.getNode().setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
        caption.setTranslateX(event.getSceneX());
        caption.setTranslateY(event.getSceneY());
        caption.setText(String.valueOf(data.getPieValue())+"%");
        }
        });
        }*/
    }

    public void chart() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        new AbonnementService().getAll().forEach((Abonnement a) -> {
            int per = new AbonnementService().select_byType(a).size() * 100 / new AbonnementService().getAll().size();
            String cat = a.getType().getNom() + ":" + per + "%";
            float val = new AbonnementService().select_byType(a).size();
            pieChartData.add(new PieChart.Data(cat, val));
        });
        pie_abonnement.setData(pieChartData);
        pie_abonnement.setLabelLineLength(-60);
    }

    @FXML
    private void back(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Add_Type_Abonnement_FXML.fxml"));
            Parent root = loader.load();
            Add_Type_Abonnement_FXMLController ac = loader.getController();
            Stage s = (Stage) (export_bt.getScene().getWindow());
            s.close();
            Scene scene = new Scene(root, 750, 450);
            Stage SecondaryStage = new Stage();
            /*SecondaryStage.setX(0);
            SecondaryStage.setY(0);*/
            SecondaryStage.setTitle("Type_Abonnement!");
            SecondaryStage.setScene(scene);
            SecondaryStage.show();
        } catch (Exception ex) {
            System.out.println("err:" + ex);
        }
    }

    @FXML
    private void export(ActionEvent event) {
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            double x = 1.15;
            double y = 1.45;
            Parent node = export_bt.getScene().getRoot();
            node.getTransforms().add(new Scale(x, y));
            Printer p = job.getPrinter();
            PageLayout pl = p.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.EQUAL);
            job.setPrinter(p);
            job.showPrintDialog((Stage) (export_bt.getScene().getWindow()));
            job.printPage(pl, node);
            job.endJob();
            node.getTransforms().add(new Scale((1/x),(1/y)));
            chart();
        }

    }
}
