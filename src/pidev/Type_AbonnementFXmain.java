/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author zied loukil
 */
public class Type_AbonnementFXmain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            String chemin = "/gui/Add_Type_Abonnement_FXML.fxml";
            String chemin2 = "/gui/Front_Abonnement.fxml";
            String chemin3 = "/gui/Statistic_abonnement.fxml";
            Parent root = FXMLLoader.load(getClass().getResource(chemin));
            //Scene scene = new Scene(root,650, 450);
            Scene scene = new Scene(root);
            primaryStage.setTitle("Type_Abonnement!");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception ex) {
            System.out.println("err:" + ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
