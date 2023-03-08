/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class ConditionsController implements Initializable {

    @FXML
    private Label conditions;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conditions.setText("1/ Examen médical complet : Le client devra subir un examen médical complet \n "
                + "avant le départ pour s'assurer qu'il est en bonne santé et qu'il n'a aucune \n "
                + "condition médicale qui pourrait poser un risque pour lui ou pour les autres passagers.\n" +
"\n" +
                   "2/ Connaissance des risques : Le client devra être informé des risques associés \n aux voyages  "
                + "dans l'espace, y compris les risques de maladie, d'accident et de panne de l'équipement.  \n "
                + "Les risques seront clairement expliqués avant la réservation et le client devra \n"
                + "accepter ces risques avant de pouvoir réserver un vol.\n" +
"\n" +
                  "3/ Formation préalable : Le client devra suivre une formation de base pour apprendre les \n "
                + "procédures de sécurité et les techniques de survie en cas d'urgence. Cette formation  \n"
                + "sera dispensée avant le départ et sera obligatoire pour tous les passagers.\n" +
"\n" +
                  "4/ Équipement de protection : Le client devra porter un équipement de protection approprié,\n"
                + "tel qu'une combinaison spatiale, pour le protéger contre les radiations, les \n"
                + "températures extrêmes et les impacts de débris spatiaux.\n" +
"\n" +
                  "5/ Respecter les consignes de sécurité : Le client devra suivre strictement les consignes \n "
                + "de sécurité émises par les membres de l'équipage et les équipes de contrôle au sol.\n" +
"\n" +
                  "6/ Acceptation des frais : Les voyages dans l'espace peuvent être extrêmement coûteux \n"
                + "en raison de la complexité de la technologie et des exigences de sécurité élevées.Le client \n"
                + "devra accepter de payer les frais associés au voyage avant de pouvoir réserver un vol.\n" +
"\n" +
                  "7/ Restrictions de santé : Le client devra informer l'entreprise de tout problème de santé\n"
                + " potentiellement dangereux ou d'une condition médicale qui pourrait poser un risque pour \n"
                + " lui-même ou pour les autres passagers.");
    }    
    
}
