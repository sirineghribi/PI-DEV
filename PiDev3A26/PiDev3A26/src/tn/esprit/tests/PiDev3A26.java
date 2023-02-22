/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import tn.esprit.entity.Personne;
import tn.esprit.services.PersonneService;
import tn.esprit.tools.MaConnection;

/**
 *
 * @author Fayechi
 */
public class PiDev3A26 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PersonneService ps = new PersonneService();
        Personne p1 = new Personne(3,24, "chabchoub", "karim");
       // ps.ajouter(p1);
       //ps.supprimerPersonne(p1);
       ps.modifierPersonne("karim", p1);
        System.out.println(ps.getAll());
        
        
    }

}
