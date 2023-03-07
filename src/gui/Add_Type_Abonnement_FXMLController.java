/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entity.Abonnement;
import entity.Type_abonnement;
import entity.Utilisateur;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.AbonnementService;
import services.Type_abonnementService;

/**
 * FXML Controller class
 *
 * @author zied loukil
 */
public class Add_Type_Abonnement_FXMLController implements Initializable {

    @FXML
    private TextField Nom_type;
    @FXML
    private TextField Desc_type;
    @FXML
    private TextField Prix_type;
    @FXML
    private TextField Offre_type;
    @FXML
    private TextField Periode_type;
    @FXML
    private Button add_button;
    @FXML
    private TableView<Type_abonnement> list_type;
    @FXML
    private TableColumn<Type_abonnement, String> nom_col;
    @FXML
    private TableColumn<Type_abonnement, Float> prix_col;
    @FXML
    private TableColumn<Type_abonnement, String> offre_col;
    @FXML
    private TableColumn<Type_abonnement, String> periode_col;
    @FXML
    private TableColumn<Type_abonnement, String> desc_col;
    @FXML
    private Button update_type;
    @FXML
    private Button delete_type;
    @FXML
    private Button stat_bt;
    @FXML
    private Button export_bt;
    @FXML
    private TextField search_bar;
    private List<Type_abonnement> sr=new Type_abonnementService().getAll();
    @FXML
    private Button search_bt;

    /**
     * initialises the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        search_bar.textProperty().addListener((observable) -> {
    if(!new Type_abonnementService().search_byName(search_bar.getText()).isEmpty())
            sr=new Type_abonnementService().search_byName(search_bar.getText());
        else 
            sr=new Type_abonnementService().search_byDesc(search_bar.getText());
        list();
});
        list();
    }

    @FXML
    public void load_update() {
        Type_abonnement ta = list_type.getSelectionModel().selectedItemProperty().get();
        if (ta != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Update_Type_Abonnement.fxml"));
                Parent root = loader.load();
                Update_Type_AbonnementController ac = loader.getController();
                ac.SetType_Abonnement(ta);
                Stage s = (Stage) (Nom_type.getScene().getWindow());
                s.close();
                Scene scene = new Scene(root, 200, 300);
                Stage SecondaryStage = new Stage();
                SecondaryStage.setX(0);
                SecondaryStage.setY(0);
                SecondaryStage.setTitle("update_Type_Abonnement!");
                SecondaryStage.setScene(scene);
                SecondaryStage.show();
            } catch (Exception ex) {
                System.out.println("err:" + ex);
            }
        } else {
            alert("failed to update type_abonnement", "please select a type");
        }
    }

    public void list() {
        ObservableList<Type_abonnement> liste = FXCollections.observableArrayList(sr);
        list_type.setItems(liste);
        nom_col.setCellValueFactory(cell -> {
            StringProperty s = new SimpleStringProperty();
            s.set(cell.getValue().getNom());
            return s;
        });
        desc_col.setCellValueFactory(cell
                -> {
            StringProperty s = new SimpleStringProperty();
            s.set(cell.getValue().getDescription());
            return s;
        });
        prix_col.setCellValueFactory(cell
                -> {
            FloatProperty s = new SimpleFloatProperty();
            s.set(cell.getValue().getPrix());
            return s.asObject();
        });
        offre_col.setCellValueFactory(cell
                -> {
            StringProperty s = new SimpleStringProperty();
            s.set(cell.getValue().getOffre() * 100 + "%");
            return s;
        });
        periode_col.setCellValueFactory(cell
                -> {
            StringProperty s = new SimpleStringProperty();
            float periode = cell.getValue().getPeriode();
            int y = (int) (periode / 365);
            int m = (int) ((periode % 365) / 30);
            int d = (int) ((periode % 365) % 30);
            s.set(y + " years," + m + " months and " + d + " days");
            return s;
        });
    }

    public void clear_TextField() {
        Nom_type.clear();
        Desc_type.clear();
        Prix_type.clear();
        Offre_type.clear();
        Periode_type.clear();
    }

    private void alert(String a, String b) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(a);
        alert.setHeaderText(null);
        alert.setContentText(b);
        alert.show();
    }

    private void info(String a, String b) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(a);
        alert.setHeaderText(null);
        alert.setContentText(b);
        alert.show();
    }

    private boolean test() {
        try {
            float prix = Float.valueOf((Prix_type.getText()));
            float offre = Float.valueOf((Offre_type.getText()));
            float periode = Float.valueOf((Periode_type.getText()));
            return (periode > 0) && (prix > 0) && (offre > 0) && (offre < 100);
        } catch (Exception ex) {
            return false;
        }
    }

    @FXML
    private void add(ActionEvent event) {
        if (test()) {
            String nom = Nom_type.getText();
            String desc = Desc_type.getText();
            float prix = Float.valueOf((Prix_type.getText()));
            float offre = Float.valueOf((Offre_type.getText())) / 100;
            float periode = Float.valueOf((Periode_type.getText()));
            Type_abonnement ta = new Type_abonnement(nom, desc, periode, offre, prix);
            new Type_abonnementService().ajouter(ta);
            info("type d'abonnement ajouteé", "check the list for any modifications");
            clear_TextField();
            list();
        } else {
            alert("Failed to add type_abonnement", "Check your inputs");
        }
    }

    @FXML
    private void delete_type(ActionEvent event) {
        Type_abonnement ta = list_type.getSelectionModel().selectedItemProperty().get();
        if ((ta != null) && (new AbonnementService().select_byType(new Abonnement(Date.valueOf(LocalDate.MAX), ta, new Utilisateur()))).isEmpty()) {
            new Type_abonnementService().supprimer(ta);
            list();
        } else if (ta == null) {
            alert("failed to delete type_abonnement", "pelese select a type");
        } else {
            alert("failed to delete type_abonnement", "this type cant be deleted");
        }

    }

    /*LocalDate date = LocalDate.parse((User.getDate_n().toString()));
   dateLB.setValue(date);*/
    @FXML
    private void statistic(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Statistic_abonnement.fxml"));
            Parent root = loader.load();
            Statistic_abonnementController ac = loader.getController();
            ac.chart();
            Stage s = (Stage) (Nom_type.getScene().getWindow());
            s.close();
            Scene scene = new Scene(root, 740, 410);
            Stage SecondaryStage = new Stage();
            SecondaryStage.setX(0);
            SecondaryStage.setY(0);
            SecondaryStage.setTitle("Statistic_Abonnement!");
            SecondaryStage.setScene(scene);
            SecondaryStage.show();
        } catch (Exception ex) {
            System.out.println("err:" + ex);
        }
    }

    @FXML
    private void export(ActionEvent event) {
        try {
            FileChooser fc = new FileChooser();
            fc.setTitle("types d'abonnements");
            fc.setInitialFileName("type abonnements.csv.");
            String s = fc.showSaveDialog(list_type.getScene().getWindow()).toString();
            //choosing file location
            if (s != null) {
                File file = new File(s);
                Writer w = new BufferedWriter(new FileWriter(file));
                w.write("Nom,Prix,Offre,Periode,nbr_d'abonnement,pourcentage");
                //columns's names
                list_type.getItems().stream().forEach((ta) -> {

                    try {
                        float max = new AbonnementService().getAll().size();
                        float nbr = new AbonnementService().select_byType(new Abonnement(Date.valueOf(LocalDate.now()), ta, new Utilisateur())).size();
                        float per = (nbr * 100) / max;
                        w.write("\n" + ta.getNom() + "   ," + ta.getPrix() + "    ," + ta.getOffre() * 100 + "%   ," + ta.getPeriode() + "    ," + nbr + "           ," + per + "%");
                    } catch (IOException ex) {
                        System.out.println("err:" + ex.getMessage());
                    }
                });
                //fill excel file
                w.flush();
                w.close();
                //save and close
            }
        } catch (IOException ex) {
            System.out.println("err:" + ex.getMessage());
        }
    }
    
    @FXML
    private void search(ActionEvent event) {
        if(!new Type_abonnementService().search_byName(search_bar.getText()).isEmpty())
            sr=new Type_abonnementService().search_byName(search_bar.getText());
        else 
            sr=new Type_abonnementService().search_byDesc(search_bar.getText());
        list();
    }
}
