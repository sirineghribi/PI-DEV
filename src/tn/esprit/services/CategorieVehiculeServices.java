/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import tn.esprit.entity.CategorieVehicule;
import tn.esprit.tools.MaConnection;
import tn.esprit.entity.TypeCat;


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
           
            String sql = "insert into CategorieVehicule(nom_cat, lieu) "
                     + "values (?,?)";
            
            PreparedStatement ste = cnx.prepareStatement(sql);
           
            ste.setString(1, cv.get_nom_cat().toString());
            ste.setString(2, cv.getLieu());
            ste.executeUpdate();
            System.out.println("Categorie ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public List<CategorieVehicule> getAll() {
        List<CategorieVehicule> CatVehicule = new ArrayList<>();
        try {
            String sql = "select * from CategorieVehicule ";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
                
                CategorieVehicule cv = new CategorieVehicule(s.getInt(1),CategorieVehicule.enumtypecat(s.getString("nom_cat")), s.getString(3));
                CatVehicule.add(cv);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return CatVehicule;
    }

    @Override
    public List<CategorieVehicule> findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

public void supprimerCat(CategorieVehicule cv) {
        String sql = "delete from CategorieVehicule where id_cat=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, cv.get_id_cat());
            ste.executeUpdate();
            System.out.println("Categorie supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void modifierCat(String lieu, CategorieVehicule cv) {
        String sql = "update CategorieVehicule set lieu=? where id_cat=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, lieu);
            ste.setInt(2, cv.get_id_cat());
            ste.executeUpdate();
            System.out.println("categorie vehicule modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }


}
