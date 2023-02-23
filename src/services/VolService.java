/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entity.Vehicule;
import entity.Vol;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tools.MaConnection;

/**
 *
 * @author lenovo
 */
public class VolService implements InterfaceService<Vol>{

    
    Connection cnx;

    public VolService() {
        cnx = MaConnection.getInstance().getCnx();
    }
   
    @Override
    public void ajouter(Vol t) {
        try {
            
           int a=this.get_nbr_place(t.getId_mt()).get(0).get_nbr_pas();                   // a modifier selon la classe de amine 
            String sql = "insert into vol(id_mt,date,destination,prix,etat,nbr_place)"
                    + "values (?,?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId_mt());
            ste.setDate(2, t.getDate());
            ste.setString(3, t.getDestination());
            ste.setFloat(4, t.getPrix());
            ste.setString(5, t.getEtat());
            ste.setInt(6, a);
            ste.executeUpdate();
            System.out.println("Vol ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
// ******************* c'est findbyid de amine  a supprimer lors de l'integration
   public List<Vehicule> get_nbr_place(int id) 
   {   List <Vehicule> mt=new ArrayList<>();
       try {
             String sql = "select * from vehicule where id_vehicule=?";
             PreparedStatement ste = cnx.prepareStatement(sql);
             ste.setInt(1,id);
             ResultSet s = ste.executeQuery();
             while (s.next()) {
                Vehicule v = new Vehicule(s.getInt("id_vehicule"),s.getInt("nbr_pas"));
                mt.add(v);
            }
            
       }
       catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      return mt; 
   }
   //*****************************************************************
    @Override
    public List<Vol> getAll() {
        List <Vol> vols=new ArrayList<>();
        try {
            String sql = "select * from vol";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
                Vol v = new Vol(s.getInt("id_v"), s.getInt("id_mt"),s.getInt("nbr_place"),s.getString("destination"), s.getString("etat"),s.getFloat("prix"),s.getDate("date"));
                vols.add(v);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return vols; 
    }

    @Override
    public void supprimer(Vol t) {
        String sql = "delete from vol where id_v=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            System.out.println(t.getId_v());
            ste.setInt(1, t.getId_v());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public List<Vol> findById(int id) {
        List <Vol> vols=new ArrayList<>();
     
        try {
            String sql = "select * from vol where id_v= ?";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1,id);
            ResultSet s = ste.executeQuery();
            while (s.next()) {
                Vol v = new Vol(s.getInt("id_v"), s.getInt("id_mt"),s.getInt("nbr_place"),s.getString("destination"), s.getString("etat"),s.getFloat("prix"),s.getDate("date"));
                vols.add(v);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return vols;
    }
    
    @Override
    public void modifier(Vol t) {
        
                
         String sql = "update vol set id_mt=?,date=?,destination=?,prix=?,etat=? where id_v=?";
         
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId_mt());
            ste.setDate(2, t.getDate());
            ste.setString(3, t.getDestination());
            ste.setFloat(4, t.getPrix());
            ste.setString(5, t.getEtat());
            ste.setInt(6,t.getId_v());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void modifier_etat(Vol t,String etat )
    {
      String sql = "update vol set etat=? where id_v=?";
         
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1,etat);
            ste.setInt(2,t.getId_v());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
    }
    
    
    @Override
    public List<Vol> trier() {
       
        List <Vol> vols=new ArrayList<>();
        try {
            String sql = "select * from vol order by date asc ";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ResultSet s = ste.executeQuery();
            while (s.next()) {
                Vol v = new Vol(s.getInt("id_v"), s.getInt("id_mt"),s.getInt("nbr_place"),s.getString("destination"), s.getString("etat"),s.getFloat("prix"),s.getDate("date"));
                vols.add(v);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return vols;
    }
    
    
   
}
