/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entity.CategorieVehicule;
import tools.MaConnection;

/**
 *
 * @author ASUS
 */
public class CategorieVehiculeServices implements InterfaceService <CategorieVehicule> {
    
    Connection cnx;

    public CategorieVehiculeServices() {
        cnx = MaConnection.getInstance().getCnx();
    }

    @Override
    public void ajouter(CategorieVehicule cv) {
        try {
          //  INSERT INTO articles( article_name, article_content, category_id, img, url )
            //        VALUES( ?, ?, ( SELECT category_id FROM categories WHERE categories.category_id = ? ), ?, ?) 
           
            String sql = "insert into CategorieVehicule(id_cat, nom_cat, id_vh) "
                     + "values (?,?, (select id_vehicule from Vehicule where Vehicule.id_vehicule = ?))";
            
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, cv.get_id_cat());
            ste.setString(2, cv.get_nom_cat());
            ste.setInt(3, cv.get_id_vh());
            ste.executeUpdate();
            System.out.println("Categorie ajoutÃ©e");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public List<CategorieVehicule> getAll() {
        List<CategorieVehicule> vehicules = new ArrayList<>();
        try {
            String sql = "select * from CategorieVehicule";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
                CategorieVehicule cv = new CategorieVehicule(s.getInt(1), s.getString("nom_cat"), s.getInt(3));
                vehicules.add(cv);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return vehicules;
    }

    @Override
    public List<CategorieVehicule> findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

public void supprimerVehicule(CategorieVehicule cv) {
        String sql = "delete from CategorieVehicule where nom_cat=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, cv.get_nom_cat());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void modifierCatVehiculeparNom(String nom_cat, CategorieVehicule cv) {
        String sql = "update CategorieVehicule set nom_cat=? where id_vh=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, nom_cat);
            ste.setString(2,cv.get_nom_cat());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void supprimer(CategorieVehicule t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(CategorieVehicule t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CategorieVehicule> trier() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
