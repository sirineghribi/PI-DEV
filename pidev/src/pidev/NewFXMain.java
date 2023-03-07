 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author lenovo
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            String chemin="/gui/AfficherUtilisateur.fxml";
            Parent root = FXMLLoader.load(getClass().getResource(chemin));
            Scene scene = new Scene(root,816,458);
            
            primaryStage.setTitle("Les vols");
            primaryStage.setScene(scene);
            primaryStage.show();
           
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
