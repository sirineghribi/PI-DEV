/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.Carte_fidelite;
import entity.Reservation;
import entity.Utilisateur;
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
public class ReservationService implements InterfaceService<Reservation> {

    
    Connection cnx;

    public ReservationService() {
        cnx = MaConnection.getInstance().getCnx();
    }
    
    @Override
    public void ajouter(Reservation t) {
        VolService v=new VolService();
        
        float a=v.findById(t.getVol().getId_v()).get(0).getPrix();
        
         try {
            if (t.getConditionA()==1) 
            {String sql = " insert into reservation (id_c,id_v,cin,num_phone,etat,conditionA,date_res,prix) "        
                    + "values (?,?,?,?,?,?,?,?)";         
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, t.getUtilisateur().getId());
            ste.setInt(2, t.getVol().getId_v());
            ste.setInt(3, t.getCin());
            ste.setInt(4, t.getNum_phone());
            ste.setString(5, "En attente");
            ste.setInt(6,1);
            ste.setDate(7, Date.valueOf("2023-12-12"));
            ste.setFloat(8,a);
            ste.executeUpdate();
            System.out.println("Reservation ajouté");
            
            // ajout carte fidelité ***************************************
            int nb=this.findById(t.getUtilisateur().getId()).size();
            if (nb>1)
            {
                Carte_fidelite c=new Carte_fidelite(100,t.getUtilisateur().getId());
                try {
            
            String sql1 = "insert into carte_fidelite(nbr_point,id_u)"
                    + "values (?,?)";
            PreparedStatement ste1 = cnx.prepareStatement(sql1);
            ste1.setInt(1, c.getNbr_point());
            ste1.setInt(2, c.getId_u());
            ste1.executeUpdate();
            System.out.println(" card added successfully!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            
            }
          //***************************************************************************************************** 
            } else System.out.println("Veuillez accepté les conditions !"); 
            
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         
    }
/*
    @Override
    public List<Reservation> getAll() {
        List <Reservation> reservations=new ArrayList<>();
        try {
            String sql = "select * from reservation";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
                UtilisateurService us=new UtilisateurService();
                Utilisateur u=us.findById(s.getInt("id_c")).get(0);
                VolService vs=new VolService();
                Vol v=vs.findById(s.getInt("id_v")).get(0); 
                Reservation r = new Reservation(s.getInt("id_r"),s.getInt("cin"),s.getInt("num_phone"),s.getInt("conditionA"),s.getString("etat"),s.getDate("date_res"),s.getFloat("prix"),v,u);
                reservations.add(r);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return reservations; 
    }*/
    @Override
    public List<Reservation> getAll() {
        List <Reservation> reservations=new ArrayList<>();
        try {
            String sql = "select U.id,U.nom,U.prenom,U.email,V.*,R.cin,R.num_phone,R.etat,R.prix,R.date_res  from reservation R  inner join"
                    +"utilisateur U on R.id_c=U.id inner join vol V on R.id_v=V.id_v ";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
                UtilisateurService us=new UtilisateurService();
                Utilisateur u=us.findById(s.getInt("id_c")).get(0);
                VolService vs=new VolService();
                Vol v=vs.findById(s.getInt("id_v")).get(0); 
                Reservation r = new Reservation(s.getInt("id_r"),s.getInt("cin"),s.getInt("num_phone"),s.getInt("conditionA"),s.getString("etat"),s.getDate("date_res"),s.getFloat("prix"),v,u);
                reservations.add(r);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return reservations; 
    }
    
    public void modifier_etatR(Reservation t , String etat)
    {
        String sql = "update reservation set etat=? where id_r=?";
         
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, etat);
            ste.setInt(2, t.getId_r());
            ste.executeUpdate();
            System.out.println("Reservation modifié");
            
            if (etat.equals("Confirmé"))
            {
                VolService v=new VolService();
                v.modifier_etat(t.getVol(), "Confirmé");
                v.modifier_nb_place(t.getVol());
                System.out.println("vol modifié");
            }
            
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public List<Reservation> findById(int id) {                             // id client  (pour client)
         List <Reservation> reservations=new ArrayList<>();
         try {
            String sql = "select R.id_v,V.id_v,U.id,U.nom,U.prenom,U.email,V.date,V.destination,R.num_phone,R.etat,R.prix,R.date_res  from reservation R  inner join"
                    +"utilisateur U on R.id_c=U.id inner join vol V on R.id_v=V.id_v where id_c=?";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1,id);
            ResultSet s = ste.executeQuery();
            while (s.next()) {                     
                VolService vs=new VolService();
                Vol v= new Vol();
                v.setId_v(s.getInt("id_v"));
                v.setDate(s.getDate("date"));
                v.setDestination(s.getString("destination"));
                Utilisateur u=new Utilisateur();
                u.setId(s.getInt("id"));
                u.setEmail(s.getString("email"));
                u.setNom(s.getString("nom"));
                u.setPrenom(s.getString("prenom"));   
                Reservation r = new Reservation(s.getInt("id_r"),s.getInt("num_phone"),s.getString("etat"),s.getDate("date_res"),s.getFloat("prix"),v,u);
                reservations.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         return reservations; 
    }

    @Override
    public List<Reservation> trier() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Reservation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void modifier(Reservation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
