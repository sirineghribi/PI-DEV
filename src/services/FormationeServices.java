/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import entity.Formation;
import entity.Utilisateur;
import entity.typeformation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tools.MaConnection;

/**
 *
 * @author abder
 */
public class FormationeServices implements InterfaceService<Formation> {

    Connection cnx;

    public FormationeServices() {
        cnx = MaConnection.getInstance().getCnx();
    }

    @Override
    public void ajouter(Formation f) {
        try {
            String sql = "insert into Formation(id_c,nbr_heure,type,date)"
                    + "values (?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);

            ste.setInt(1, f.getUtilisateur().getId());
            ste.setInt(2, f.getNbrheur());
            ste.setString(3, f.getType().toString());
            ste.setDate(4, f.getDate());
            ste.executeUpdate();
            System.out.println("Formation ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<Formation> getAll() {
        List<Formation> Formations = new ArrayList<>();
        try {
            String sql = "select * from Formation inner join utilisateur on utilisateur.id=formation.id_c";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
                typeformation t = Formation.stringToType(s.getString("formation.type"));
                Utilisateur u = new Utilisateur(s.getInt("utilisateur.id"), s.getString("utilisateur.nom"), s.getString("utilisateur.prenom"), Utilisateur.stringTogenre(s.getString("utilisateur.genre")), s.getString("utilisateur.email"), s.getString("utilisateur.mdp"), s.getDate("utilisateur.date_n"));
                Formation f = new Formation(s.getInt("formation.id_f"), u, t, s.getDate("formation.date"), s.getInt("formation.nbr_heure"));
                Formations.add(f);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Formations;
    }

    public List<Formation> findByIduser(int id_f) {
        List<Formation> Formations = new ArrayList<>();
        try {
            String sql = "select * from Formation inner join utilisateur on utilisateur.id=formation.id_c where utilisateur.id=?";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id_f);
            ResultSet s = ste.executeQuery();

            while (s.next()) {
                Utilisateur u = new Utilisateur(s.getInt("utilisateur.id"), s.getString("utilisateur.nom"), s.getString("utilisateur.prenom"), Utilisateur.stringTogenre(s.getString("utilisateur.genre")), s.getString("utilisateur.email"), s.getString("utilisateur.mdp"), s.getDate("utilisateur.date_n"));
                Formation f = new Formation(s.getInt("formation.id_f"), u, Formation.stringToType(s.getString("formation.type")), s.getDate("formation.date"), s.getInt("formation.nbr_heure"));

                Formations.add(f);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Formations;
    }
    
    
    

    public boolean checkformation(Utilisateur u) {
        if (!findByIduser(u.getId()).isEmpty()) {
            findByIduser(u.getId()).stream().forEach((ab) -> {
                LocalDate date = LocalDate.parse((ab.getDate().toString()));
                date = date.plusDays((long) ab.getNbrheur());
                if (date.isBefore(LocalDate.now())) {
                    
                    
                    try {
                        sms(ab);
                    } catch (IOException ex) {
                        Logger.getLogger(FormationeServices.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    this.supprimer(ab);
                }
            });
return !findByIduser(u.getId()).isEmpty();
        }
        else return false;
    }
    
    public Formation getformation (Utilisateur u){
        
        Formation f= new Formation ();
        if(checkformation(u))
            f=findByIduser(u.getId()).get(0);
        else f=null;
        
        return f;
        
    }
    
    public static void sms(Formation f) throws IOException{
    LocalDate date = LocalDate.parse((f.getDate().toString()));
                date = date.plusDays((long) f.getNbrheur());
    int num=99520530;
        
       
        String msg = "Congratulations you are ready to take off.mission passed Respect +";
        String ACCOUNT_SID ="ACaf129c44776b8d63d9cbbb9b22c71fa6";
        String AUTH_TOKEN = "980e3ff08d8b69c2664ba6541f254ca3";
       
        Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
        Message message = Message.creator(new com.twilio.type.PhoneNumber("+216"+num)
                                         ,new com.twilio.type.PhoneNumber("+12763239794"), 
                                          msg).create();
        message.getSid();
        } 

    
    
    
    
    
    

    @Override
    public List<Formation> findById(int id_f) {
        List<Formation> Formations = new ArrayList<>();
        try {
            String sql = "select * from Formation inner join utilisateur on utilisateur.id=formation.id_c where formation.id_f=?";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, id_f);
            ResultSet s = ste.executeQuery();
            while (s.next()) {
             
                Utilisateur u = new Utilisateur(s.getInt("utilisateur.id"), s.getString("utilisateur.nom"), s.getString("utilisateur.prenom"), Utilisateur.stringTogenre(s.getString("utilisateur.genre")), s.getString("utilisateur.email"), s.getString("utilisateur.mdp"), s.getDate("utilisateur.date_n"));
                Formation f = new Formation(s.getInt("formation.id_f"), u, Formation.stringToType(s.getString("formation.type")), s.getDate("formation.date"), s.getInt("formation.nbr_heure"));

                Formations.add(f);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Formations;
    }

    @Override
    public void supprimer(Formation t) {
        String sql = "delete from Formation where id_f=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getId_f());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Formation t) {

        String sql = "update Formation set id_c=?, type=?, date=?, nbr_heure=? where id_f=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(5, t.getId_f());
            ste.setInt(1, t.getUtilisateur().getId());
            ste.setInt(4, t.getNbrheur());
            ste.setString(2, t.getType().toString());
            ste.setDate(3, t.getDate());
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   
    @Override
    public List<Formation> trier() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
