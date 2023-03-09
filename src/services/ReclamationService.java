/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.Reclamation;
import entity.Typerec;
import entity.Utilisateur;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import tools.MaConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author sirin
 */
public class ReclamationService implements InterfaceService<Reclamation>{
Connection cnx;

    public ReclamationService() {
        cnx = MaConnection.getInstance().getCnx();
    }
    @Override
    public void ajouter(Reclamation t) {
        try {
            String sql = "insert into reclamation(type,description,id_c,etat)"
                    + "values (?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getType().toString());
            ste.setString(2, t.getDescription());
            ste.setInt(3, t.getUtilisateur().getId());
            ste.setString(4, t.getEtat());
            ste.executeUpdate();
            System.out.println("****Reclamation est bien ajoutée**\n"
                    + "Nous avons bien reçu votre reclamation chére client concernant:"+t.getType()  + " \n Nous sommes sincèrement désolés pour ce désagrément \n.Nous mettons tout"
                    + " en œuvre pour résoudre ce problème au plus vite et reviendrons vers vous par mail .Merci d’avance de votre patience.\n ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
             System.out.println("****l'utilisateur invalide **");
            
        }
    }
 public List<Reclamation> getAll() {
        List<Reclamation> reclamations = new ArrayList<>();
        try {
             String sql = "select * from reclamation inner join utilisateur on reclamation.id_c = utilisateur.id";

            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
                 Utilisateur u = new Utilisateur(s.getInt("utilisateur.id"), s.getString("utilisateur.nom"), s.getString("utilisateur.prenom"),
                 Utilisateur.stringTogenre(s.getString("utilisateur.genre")),s.getString("utilisateur.email"),s.getString("utilisateur.mdp"),Utilisateur.stringTorole(s.getString("utilisateur.type")),s.getDate("utilisateur.date_n"),s.getInt("utilisateur.num"));
                 Reclamation r = new Reclamation(s.getInt("reclamation.id_rec"),Reclamation.enumtype(s.getString("type")),s.getString("reclamation.description"),u,s.getString("reclamation.etat")
                        );
                reclamations.add(r);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamations;
    }

    
        public ObservableList<Reclamation> afficher_Reclamation() 
     {
        ObservableList<Reclamation> reclamations=FXCollections.observableArrayList();
        try { 
            String sql = "select * from reclamation inner join utilisateur on reclamation.id_c = utilisateur.id ";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
             
Utilisateur u = new Utilisateur(s.getInt("utilisateur.id"), s.getString("utilisateur.nom"), s.getString("utilisateur.prenom"),
                 Utilisateur.stringTogenre(s.getString("utilisateur.genre")),s.getString("utilisateur.email"),s.getString("utilisateur.mdp"),Utilisateur.stringTorole(s.getString("utilisateur.type")),s.getDate("utilisateur.date_n"),s.getInt("utilisateur.num"));
                 Reclamation r = new Reclamation(s.getInt("reclamation.id_rec"),Reclamation.enumtype(s.getString("type")),s.getString("reclamation.description"),u,s.getString("reclamation.etat")
                        );                        
                reclamations.add(r);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamations;
    }

    
    public void supprimer(Reclamation t) {
        if(t.getEtat().equals("non traité")){
        String sql = "delete from reclamation where id_rec=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1,t.getId_rec());
            ste.executeUpdate();
           System.out.println("suppression effectuée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }}
        else{System.err.println("impossible votre reclamation est déja traitée");}
    }

   
    public void modifier(String description,Reclamation t) {
         if(t.getEtat().equals("non traité")){
             
         String sql = "update reclamation set description=? where id_rec=?";
         try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1,description);
            ste.setInt(2,t.getId_rec());
            ste.executeUpdate();
             System.out.println("bien modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
           
        }}
        else{System.err.println("impossible votre reclamation est déja traitée");}

    }
 public void modifieretat(String etat,Reclamation t) {
        
             
         String sql = "update reclamation set etat=? where id_rec=?";
         try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1,etat);
            ste.setInt(2,t.getId_rec());
            ste.executeUpdate();
             System.out.println("bien modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
           
        }

    }
    @Override
    public List<Reclamation> findById(int id) {
       List<Reclamation> reclamations = new ArrayList<>();
          try {
            
         
            String sql = "select * from reclamation inner join utilisateur on reclamation.id_c = utilisateur.id  where reclamation.id_rec=?";
           
            PreparedStatement ste = cnx.prepareStatement(sql);
             ste.setInt(1,id);
                 
            ResultSet s = ste.executeQuery();
            while (s.next()) {
                //Utilisateur u=new Utilisateur();
               Utilisateur u = new Utilisateur(s.getInt("utilisateur.id"), s.getString("utilisateur.nom"), s.getString("utilisateur.prenom"),
                 Utilisateur.stringTogenre(s.getString("utilisateur.genre")),s.getString("utilisateur.email"),s.getString("utilisateur.mdp"),Utilisateur.stringTorole(s.getString("utilisateur.type")),s.getDate("utilisateur.date_n"),s.getInt("utilisateur.num"));
                 Reclamation r = new Reclamation(s.getInt("reclamation.id_rec"),Reclamation.enumtype(s.getString("type")),s.getString("reclamation.description"),u,s.getString("reclamation.etat")
                        );
                reclamations.add(r);

                
              
            }System.out.println("la reclamtion est trouvée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamations;
       
    }

    @Override
    public List<Reclamation> trier() {
       List<Reclamation> reclamations = new ArrayList<>();
         
        try {
             String sql = "select * from reclamation inner join utilisateur on reclamation.id_c = utilisateur.id order by reclamation.id_rec DESC";
               PreparedStatement ste = cnx.prepareStatement(sql);
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
               
                Utilisateur u = new Utilisateur(s.getInt("utilisateur.id"), s.getString("utilisateur.nom"), s.getString("utilisateur.prenom"),
                 Utilisateur.stringTogenre(s.getString("utilisateur.genre")),s.getString("utilisateur.email"),s.getString("utilisateur.mdp"),Utilisateur.stringTorole(s.getString("utilisateur.type")),s.getDate("utilisateur.date_n"),s.getInt("utilisateur.num"));
                 Reclamation r = new Reclamation(s.getInt("reclamation.id_rec"),Reclamation.enumtype(s.getString("type")),s.getString("reclamation.description"),u,s.getString("reclamation.etat")
                        );
                reclamations.add(r);
  
            } System.err.println("Voici la liste trié DESC");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamations;
     }
   
       public List<Reclamation> trierASC() {
       List<Reclamation> reclamations = new ArrayList<>();
         
        try {
             String sql = "select * from reclamation inner join utilisateur on reclamation.id_c = utilisateur.id order by reclamation.id_rec ASC";
               PreparedStatement ste = cnx.prepareStatement(sql);
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
                
               Utilisateur u = new Utilisateur(s.getInt("utilisateur.id"), s.getString("utilisateur.nom"), s.getString("utilisateur.prenom"),
                 Utilisateur.stringTogenre(s.getString("utilisateur.genre")),s.getString("utilisateur.email"),s.getString("utilisateur.mdp"),Utilisateur.stringTorole(s.getString("utilisateur.type")),s.getDate("utilisateur.date_n"),s.getInt("utilisateur.num"));
                 Reclamation r = new Reclamation(s.getInt("reclamation.id_rec"),Reclamation.enumtype(s.getString("type")),s.getString("reclamation.description"),u,s.getString("reclamation.etat")
                        );
                reclamations.add(r);

               
               
              
            } System.err.println("Voici la liste trié ASC");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return reclamations;
     }

    @Override
    public void modifier(Reclamation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
               
              
       
    
    
    
    }
    

