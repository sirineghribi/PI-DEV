/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.Avis;
import entity.Reclamation;
import entity.Utilisateur;
import entity.Vol;
import entity.Vehicule;
import entity.CategorieVehicule;
import tools.MaConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author sirin
 */
public class AvisService implements InterfaceService<Avis>{
Connection cnx;
    public AvisService() {
        cnx = MaConnection.getInstance().getCnx();
    }
    @Override
    public void ajouter(Avis t) {
        try {
            String sql = "insert into avis(note,description,id_c,vol)"
                    + "values (?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getNote().toString());
            ste.setString(2, t.getDescription());
            ste.setInt(3, t.getUtilisateur().getId());
            ste.setInt(4, t.getVol().getId_v());
         
            ste.executeUpdate();
            System.out.println("****Avis ajoutée**");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
 
    
    public List<Avis> getAll() {
        List<Avis> lavis = new ArrayList<>();
        try {
            String sql = "select * from avis inner join utilisateur on avis.id_c = utilisateur.id inner join vol on avis.vol = vol.id_v ";
            
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
 VolService v= new VolService();
 Vol vv = new Vol();
  vv=new VolService().findById(s.getInt("vol.id_v")).get(0);

  UtilisateurService us=new UtilisateurService();
                Utilisateur u=new Utilisateur();
                 u=us.findById(s.getInt("utilisateur.id")).get(0);
                 
    Vehicule ve= vv.getMt();

 Avis a = new Avis(s.getInt("avis.id_avis"),Avis.enumnote(s.getString("avis.note")),s.getString("avis.description"),new Utilisateur(s.getInt("utilisateur.id"), s.getString("utilisateur.nom"), s.getString("utilisateur.prenom"),
                        Utilisateur.stringTogenre(s.getString("utilisateur.genre")),s.getString("utilisateur.email"),s.getString("utilisateur.mdp"),Utilisateur.stringTorole(s.getString("utilisateur.type")),s.getDate("utilisateur.date_n"),s.getInt("utilisateur.num")),new Vol(s.getInt("vol.id_v"),s.getInt("vol.nbr_place"),s.getString("vol.destination"), s.getString("vol.etat"),s.getFloat("vol.prix"),s.getDate("vol.date"),ve));
                lavis.add(a);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lavis;
    }

    
    public void supprimer(Avis t) {
        
        String sql = "delete from avis where id_avis=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1,t.getId_avis());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      System.out.println("suppression avec success");
    }

   
    public void modifier(String description,Avis t) {
         
         String sql = "update avis set description=? where id_avis=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, description);
            ste.setInt(2,t.getId_avis());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
      System.out.println("modifié avec success");

    }

    @Override
    public List<Avis> findById(int id) {
     List<Avis> lavis = new ArrayList<>();
         
        try {
            
         
            String sql = "select * from avis inner join utilisateur ON avis.id_c = utilisateur.id where id_avis=?";
           
            PreparedStatement ste = cnx.prepareStatement(sql);
             ste.setInt(1,id);
                 
            ResultSet s = ste.executeQuery();
            while (s.next()) {
                Vehicule ve=new Vehicule();
                Utilisateur u=new Utilisateur();
                Avis a = new Avis(s.getInt("avis.id_avis"),Avis.enumnote(s.getString("avis.note")),s.getString("avis.description"),new Utilisateur(s.getInt("utilisateur.id"), s.getString("utilisateur.nom"), s.getString("utilisateur.prenom"),
                        Utilisateur.stringTogenre(s.getString("utilisateur.genre")),s.getString("utilisateur.email"),s.getString("utilisateur.mdp"),Utilisateur.stringTorole(s.getString("utilisateur.type")),s.getDate("utilisateur.date_n"),s.getInt("utilisateur.num")),new Vol(s.getInt("vol.id_v"),s.getInt("vol.nbr_place"),s.getString("vol.destination"), s.getString("vol.etat"),s.getFloat("vol.prix"),s.getDate("vol.date"),ve));
                lavis.add(a);
                System.out.println("l'avis est trouvée");
              
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
              System.err.println("!l'avis n'existe pas!");
        }
        return lavis;
    }

    @Override
    public List<Avis> trier() {
       List<Avis> lavis = new ArrayList<>();
         
        try {
            
         
            String sql = "SELECT * FROM avis INNER JOIN utilisateur ON avis.id_c = utilisateur.id ORDER BY avis.id_avis DESC";
           
            PreparedStatement ste = cnx.prepareStatement(sql);
            
                 
            ResultSet s = ste.executeQuery();
            while (s.next()) {
            Vehicule ve=new Vehicule();
                Utilisateur u=new Utilisateur();
                Avis a = new Avis(s.getInt("avis.id_avis"),Avis.enumnote(s.getString("avis.note")),s.getString("avis.description"),new Utilisateur(s.getInt("utilisateur.id"), s.getString("utilisateur.nom"), s.getString("utilisateur.prenom"),
                        Utilisateur.stringTogenre(s.getString("utilisateur.genre")),s.getString("utilisateur.email"),s.getString("utilisateur.mdp"),Utilisateur.stringTorole(s.getString("utilisateur.type")),s.getDate("utilisateur.date_n"),s.getInt("utilisateur.num")),new Vol(s.getInt("vol.id_v"),s.getInt("vol.nbr_place"),s.getString("vol.destination"), s.getString("vol.etat"),s.getFloat("vol.prix"),s.getDate("vol.date"),ve));
                lavis.add(a);
                
              
            }
             System.err.println("Voici la liste trié DESC");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lavis;
    }
     public List<Avis> trierASC() {
       List<Avis> lavis = new ArrayList<>();
         
        try {
            
         
            String sql = "SELECT * FROM avis INNER JOIN utilisateur ON avis.id_c = utilisateur.id ORDER BY avis.id_avis ASC";
           
            PreparedStatement ste = cnx.prepareStatement(sql);
            
                 
            ResultSet s = ste.executeQuery();
            while (s.next()) {

               Vehicule ve=new Vehicule();
                Utilisateur u=new Utilisateur();
                Avis a = new Avis(s.getInt("avis.id_avis"),Avis.enumnote(s.getString("avis.note")),s.getString("avis.description"),new Utilisateur(s.getInt("utilisateur.id"), s.getString("utilisateur.nom"), s.getString("utilisateur.prenom"),
                        Utilisateur.stringTogenre(s.getString("utilisateur.genre")),s.getString("utilisateur.email"),s.getString("utilisateur.mdp"),Utilisateur.stringTorole(s.getString("utilisateur.type")),s.getDate("utilisateur.date_n"),s.getInt("utilisateur.num")),new Vol(s.getInt("vol.id_v"),s.getInt("vol.nbr_place"),s.getString("vol.destination"), s.getString("vol.etat"),s.getFloat("vol.prix"),s.getDate("vol.date"),ve));
                lavis.add(a);
              
              
            }
             System.err.println("VOici la liste trié ASC");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lavis;
    }

    
    public ObservableList<Avis> afficher_Avis() {
ObservableList<Avis> lavis=FXCollections.observableArrayList();
 try {
            String sql = "select * from avis inner join utilisateur on avis.id_c = utilisateur.id inner join vol on avis.vol = vol.id_v";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
                UtilisateurService us=new UtilisateurService();
                Utilisateur u=new Utilisateur();
                 u=us.findById(s.getInt("id_c")).get(0);
                 VolService v =new VolService();
                 Vol v2=new Vol();
                 v2=v.findById(s.getInt("id_v")).get(0);
                 VehiculeServices vs= new VehiculeServices();
                 //    Vehicule ve= new Vehicule(s.getInt("id_mt"), s.getString("cat_vehicule"),s.getFloat("poid_sup"), s.getInt("vitesse"));
                Vehicule ve=vs.findById(s.getInt("id_mt")).get(0);
               // Vol v1 = new Vol(s.getInt("id_v"),s.getInt("nbr_place"),s.getString("destination"), s.getString("etat"),s.getFloat("prix"),s.getDate("date"),ve);
                 
                
                Avis a = new Avis(s.getInt("avis.id_avis"),Avis.enumnote(s.getString("avis.note")),s.getString("avis.description"),new Utilisateur(s.getInt("utilisateur.id"), s.getString("utilisateur.nom"), s.getString("utilisateur.prenom"),
                        Utilisateur.stringTogenre(s.getString("utilisateur.genre")),s.getString("utilisateur.email"),s.getString("utilisateur.mdp"),Utilisateur.stringTorole(s.getString("utilisateur.type")),s.getDate("utilisateur.date_n"),s.getInt("utilisateur.num")),new Vol(s.getInt("vol.id_v"),s.getInt("vol.nbr_place"),s.getString("vol.destination"), s.getString("vol.etat"),s.getFloat("vol.prix"),s.getDate("vol.date"),ve));
               lavis.add(a);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lavis;


    }

    

    
    public ObservableList<Avis> afficher_Reclamation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated
    }
    
    
    public List<Avis> search_vol(Vol v){
  return  getAll().stream().filter((a)->a.getVol().getId_v()==v.getId_v()).collect(Collectors.toList());}

    @Override
    public void modifier(Avis t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}



 
  