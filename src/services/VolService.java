/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entity.Vehicule;
import entity.Vol;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
            
           int a=this.get_nbr_place(t.getMt().get_id_vehicule()).get(0).get_nbr_pas();                   // a modifier selon la classe de amine 
            String sql = "insert into vol(id_mt,date,destination,prix,etat,nbr_place)"
                    + "values (?,?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getMt().get_id_vehicule());
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
// ******************* 
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
            String sql = " select v.*,mt.* from vol v inner join vehicule mt on v.id_mt=mt.id_vehicule ";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
                Vehicule ve= new Vehicule(s.getInt("id_mt"), s.getString("cat_vehicule"),s.getFloat("poid_sup"), s.getInt("vitesse"));
                Vol v = new Vol(s.getInt("id_v"),s.getInt("nbr_place"),s.getString("destination"), s.getString("etat"),s.getFloat("prix"),s.getDate("date"),ve);
                vols.add(v);

            }
            System.out.println("done");
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
            String sql = "select v.*,mt.* from vol v inner join vehicule mt on v.id_mt=mt.id_vehicule where id_v=?";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1,id);
            ResultSet s = ste.executeQuery();
            while (s.next()) {  
               Vehicule ve= new Vehicule(s.getInt("id_mt"), s.getString("cat_vehicule"),s.getFloat("poid_sup"), s.getInt("vitesse"));
               Vol v = new Vol(s.getInt("id_v"),s.getInt("nbr_place"),s.getString("destination"), s.getString("etat"),s.getFloat("prix"),s.getDate("date"),ve);
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
            ste.setInt(1, t.getMt().get_id_vehicule());
            ste.setDate(2, t.getDate());
            ste.setString(3, t.getDestination());
            ste.setFloat(4, t.getPrix());
            ste.setString(5, t.getEtat());
            ste.setInt(6,t.getId_v());
            ste.executeUpdate();
            System.out.println("modifié");
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
            System.out.println("etat modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
    }
    
    public void modifier_nb_place(Vol t )
    {
      String sql = "update vol set nbr_place=? where id_v=?";
         VolService vs=new VolService();
        try {
            System.out.println(vs.findById(t.getId_v()).get(0).getNbr_place());
            if (vs.findById(t.getId_v()).get(0).getNbr_place()>0)
            {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1,(vs.findById(t.getId_v()).get(0).getNbr_place())-1);
            ste.setInt(2,t.getId_v());
            ste.executeUpdate();
            System.out.println("nb--");}
            else System.out.println("nb=0");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
    }
    
    @Override
    public List<Vol> trier() {
       
        return getAll().stream().sorted((r1,r2)->(int)(r1.getDate().compareTo(r2.getDate()))).collect(Collectors.toList());
        
    }
    public List<Vol> search_bydestiantion(String t)
    {
        String sql="select v.*,mt.* from vol v inner join vehicule mt on v.id_mt=mt.id_vehicule WHERE v.destination LIKE ?";
        List<Vol> vols=new ArrayList<>();
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1,t);
            ResultSet s=ste.executeQuery();
            while(s.next())
            {
                Vehicule ve= new Vehicule(s.getInt("id_mt"), s.getString("cat_vehicule"),s.getFloat("poid_sup"), s.getInt("vitesse"));
                Vol v = new Vol(s.getInt("id_v"),s.getInt("nbr_place"),s.getString("destination"), s.getString("etat"),s.getFloat("prix"),s.getDate("date"),ve);
                vols.add(v); 
            }
        } catch (SQLException ex) 
        {
           System.out.println(ex.getMessage());
        }
        return vols;
    }
    public List<Integer> id_vehicule_list()
    {
        List <Integer> result=new ArrayList<>();
        try {
            String sql = "select id_vehicule from vehicule ";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ResultSet s = ste.executeQuery();
            while (s.next()) {
                result.add(s.getInt("id_vehicule"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return result;
    }
   
}
