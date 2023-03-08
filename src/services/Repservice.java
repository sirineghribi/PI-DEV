/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.Rep;
import entity.Avis;

import tools.MaConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author sirin
 */
public class Repservice {
    Connection cnx;
    public Repservice() {
        cnx = MaConnection.getInstance().getCnx();
    }
  
    public void ajouter(Rep t) {
        try {
            String sql = "insert into rep(reponse,id_avis)"
                    + "values (?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getReponse());
             ste.setInt(2, t.getAvis().getId_avis());
           
         
            ste.executeUpdate();
            System.out.println("****Réponse publiée**");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
