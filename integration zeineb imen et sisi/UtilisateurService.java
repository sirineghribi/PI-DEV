/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.Roles;
import entity.Utilisateur;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import tools.MaConnection;

/**
 *
 * @author Zeineb Ben Mami
 */
public class UtilisateurService implements InterfaceService<Utilisateur>{

     Connection cnx;
    public UtilisateurService() {
          cnx = MaConnection.getInstance().getCnx();
    }
    

    @Override
    public void ajouter(Utilisateur t) {
       
         try {
            String sql = "insert into utilisateur(nom,prenom,date_n,genre,email,mdp,type,num)"
                    + "values (?,?,?,?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getNom());
            ste.setString(2, t.getPrenom());
            ste.setDate(3, t.getDate_n());
            ste.setString(4, t.getGenre().toString());
            ste.setString(5, t.getEmail());
            ste.setString(6, t.getMdp());
            ste.setString(7, t.getType().toString());
            ste.setInt(8, t.getNum());
            ste.executeUpdate();
            System.out.println(" user added successfully!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
       
    
     

    @Override
    public List<Utilisateur> getAll() {
         List<Utilisateur> utilisateur = new ArrayList<>();
        try {
            String sql = "select * from utilisateur";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            
            while (s.next()) {
               
                Utilisateur u = new Utilisateur(s.getInt("id"), s.getString("nom"), s.getString("prenom"),
                        Utilisateur.stringTogenre(s.getString("genre")),s.getString("email"),s.getString("mdp"),Utilisateur.stringTorole(s.getString("type")),s.getDate("date_n"),s.getInt("num"));
                utilisateur.add(u);
               
              
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return utilisateur;
    }

    @Override
    public void supprimer(Utilisateur t) {
         String sql = "delete from utilisateur where id=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId());
            ste.executeUpdate();
            System.out.println("user deleted ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void modifier(Utilisateur t) {
          String sql = "update utilisateur set nom=?,prenom=?,date_n=?,genre=?,email=?,mdp=? where id=?";

        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
             ste.setString(1, t.getNom());
             ste.setString(2, t.getPrenom());
             ste.setDate(3, t.getDate_n());
             ste.setString(4, t.getGenre().toString());
             ste.setString(5, t.getEmail());
             ste.setString(6, t.getMdp());
              ste.setInt(7, t.getId());
             
             
            
            
            
            
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Utilisateur> findById(int id) {
         List<Utilisateur> utilisateur = new ArrayList<>();
         
        try {
            
         
            String sql = "select * from utilisateur where id=?";
           
            PreparedStatement ste = cnx.prepareStatement(sql);
             ste.setInt(1,id);
                 
            ResultSet s = ste.executeQuery();
            while (s.next()) {
               
                Utilisateur u = new Utilisateur(s.getInt("id"), s.getString("nom"), s.getString("prenom"),
                         Utilisateur.stringTogenre(s.getString("genre")),s.getString("email"),s.getString("mdp"),Utilisateur.stringTorole(s.getString("type")),s.getDate("date_n"),s.getInt("id"));
                utilisateur.add(u);
               
              
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return utilisateur;
       /* List<Utilisateur> utilisateur = new ArrayList<>();

    try {
        String sql = "select * from utilisateur where id=?";
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, id);

        ResultSet s = ste.executeQuery();
        while (s.next()) {
            Utilisateur u = new Utilisateur(s.getInt("id"), s.getString("nom"), s.getString("prenom"),
                    Utilisateur.stringTogenre(s.getString("genre")), s.getString("email"), s.getString("mdp"),
                    Utilisateur.stringTorole(s.getString("type")), s.getDate("date_n"), s.getInt("id"));
            utilisateur.add(u);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return utilisateur.stream().collect(Collectors.toList());
        */
    }

    @Override
   public List<Utilisateur> trier() {
    List<Utilisateur> utilisateur = new ArrayList<>();
    try {
        String sql = "SELECT * FROM utilisateur ORDER BY nom ASC";
        PreparedStatement ste = cnx.prepareStatement(sql);
        ResultSet s = ste.executeQuery();

        while (s.next()) {
            Utilisateur u = new Utilisateur(s.getInt("id"), s.getString("nom"), s.getString("prenom"),
                    Utilisateur.stringTogenre(s.getString("genre")), s.getString("email"), s.getString("mdp"),
                    Utilisateur.stringTorole(s.getString("type")), s.getDate("date_n"), s.getInt("num"));
            utilisateur.add(u);
        }

    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return utilisateur.stream().sorted(Comparator.comparing(Utilisateur::getNom)).collect(Collectors.toList());
}





/*public List<Utilisateur> find(int id) {
    List<Utilisateur> utilisateur = new ArrayList<>();

    try {
        String sql = "select * from utilisateur where id=?";
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setInt(1, id);

        ResultSet s = ste.executeQuery();
        while (s.next()) {
            Utilisateur u = new Utilisateur(s.getInt("id"), s.getString("nom"), s.getString("prenom"),
                    Utilisateur.stringTogenre(s.getString("genre")), s.getString("email"), s.getString("mdp"),
                    Utilisateur.stringTorole(s.getString("type")), s.getDate("date_n"), s.getInt("id"));
            utilisateur.add(u);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return utilisateur.stream().collect(Collectors.toList());
}

  
    /* public List<Utilisateur> findByEmail(String email) {
         List<Utilisateur> utilisateur = new ArrayList<>();
         
        try {
            
         
            String sql = "select * from utilisateur where email=?";
           
            PreparedStatement ste = cnx.prepareStatement(sql);
             ste.setString(1,email);
                 
            ResultSet s = ste.executeQuery();
            while (s.next()) {
               
                Utilisateur u = new Utilisateur(s.getInt("id"), s.getString("nom"), s.getString("prenom"),
                Utilisateur.stringTogenre(s.getString("genre")),s.getString("email"),s.getString("mdp"),Utilisateur.stringTorole(s.getString("type")),s.getDate("date_n"));
                utilisateur.add(u);
               
              
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return utilisateur;
        
    }*/

   public void modifierU(Utilisateur t) {
          String sql = "update utilisateur set nom=?,prenom=?,date_n=?,genre=?,email=?,mdp=? where email=?";

        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
             ste.setString(1, t.getNom());
             ste.setString(2, t.getPrenom());
             ste.setDate(3, t.getDate_n());
             ste.setString(4, t.getGenre().toString());
             ste.setString(5, t.getEmail());
             ste.setString(6, t.getMdp());
              //ste.setInt(7, t.getId());
               ste.setString(7, t.getEmail());
             
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   
   public Utilisateur trouverUtilisateurParEmail(String email) {
  List<Utilisateur> utilisateurs = new ArrayList<>();
    try {
        String sql = "SELECT * FROM utilisateur WHERE email=?";
        PreparedStatement ste = cnx.prepareStatement(sql);
        ste.setString(1, email);
        ResultSet s = ste.executeQuery();
        while (s.next()) {
            Utilisateur utilisateur = new Utilisateur(s.getInt("id"), s.getString("nom"), s.getString("prenom"),
                    Utilisateur.stringTogenre(s.getString("genre")), s.getString("email"), s.getString("mdp"),
                    Utilisateur.stringTorole(s.getString("type")), s.getDate("date_n"),s.getInt("num"));
            utilisateurs.add(utilisateur);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return utilisateurs.stream()
            .sorted(Comparator.comparing(Utilisateur::getNom))
            .findFirst()
            .orElse(null);
}
   
}
