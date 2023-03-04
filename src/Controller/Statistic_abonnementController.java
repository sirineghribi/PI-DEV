/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entity.Abonnement;
import entity.Utilisateur;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import services.AbonnementService;
import services.Type_abonnementService;

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
        new Type_abonnementService().getAll().stream().forEach((ta)
                -> {
            Abonnement a = new Abonnement(Date.valueOf(LocalDate.now()), ta, new Utilisateur());
            int per = new AbonnementService().select_byType(a).size() * 100 / new AbonnementService().getAll().size();
            String cat = ta.getNom() + ":" + per + "%";
            float val = new AbonnementService().select_byType(a).size();
            if (val > 0) {
                pieChartData.add(new PieChart.Data(cat, val));
            }
        });
        pie_abonnement.setData(pieChartData);
        pie_abonnement.setLabelLineLength(10);
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
        /*PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
        double x = 1.15;
        double y = 1.45;
        export_bt.setVisible(false);
        back_bt.setVisible(false);
        Parent node = export_bt.getScene().getRoot();
        node.getTransforms().add(new Scale(x, y));
        PageLayout pl = job.getPrinter().createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.EQUAL);
        job.showPrintDialog((Stage) (export_bt.getScene().getWindow()));
        job.printPage(pl, node);
        job.endJob();
        export_bt.setVisible(true);
        back_bt.setVisible(true);
        node.getTransforms().add(new Scale((1 / x), (1 / y)));
        chart();
        }*/
        float x=1.1f;
        float y=1.5f;
        float z=90;
        export_bt.setVisible(false);
        back_bt.setVisible(false);
        Parent node = export_bt.getScene().getRoot();
        node.getTransforms().add(new Rotate(z));
        node.getTransforms().add(new Scale(x, y));
        WritableImage nodeshot = node.snapshot(new SnapshotParameters(), null);
        export_bt.setVisible(true);
        back_bt.setVisible(true);
        node.getTransforms().add(new Scale((1 / x), (1 / y)));
        node.getTransforms().add(new Rotate(360-z));
        chart();
        FileChooser fc = new FileChooser();
        File file = new File("pdf.png");

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(nodeshot, null), "png", file);
        } catch (IOException e) {
            System.out.println("err1:" + e.getMessage());

        }

        PDDocument doc = new PDDocument();
        PDPage page = new PDPage();
        PDImageXObject pdimage;
        PDPageContentStream content;
        try {
            pdimage = PDImageXObject.createFromFile("pdf.png", doc);
            content = new PDPageContentStream(doc, page);
            content.drawImage(pdimage, 0, 0);
            content.close();
            doc.addPage(page);
            fc.setTitle("Statistique des types d'abonnements");
            fc.setInitialFileName("stat.pdf");
            String s = fc.showSaveDialog(back_bt.getScene().getWindow()).toString();
            doc.save(s);
            doc.close();
            file.delete();
        } catch (IOException ex) {
            System.out.println("err2" + ex.getMessage());
        }
    }
}
