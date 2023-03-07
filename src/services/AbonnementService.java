/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import entity.Abonnement;
import entity.Reservation;
import entity.Roles;
import entity.Utilisateur;
import entity.Type_abonnement;
import entity.Vol;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import tools.MaConnection;

/**
 *
 * @author zied loukil
 */
public class AbonnementService implements InterfaceService<Abonnement> {

    private String sql;
    private static final Connection mc = MaConnection.getInstance().getCnx();
    private Statement ste;
    private PreparedStatement pste;

    @Override
    public void ajouter(Abonnement t) {
        sql = "INSERT INTO abonnement(id,date_achat,id_ty,id_c) values(?,?,?,?)";
        try {
            pste = mc.prepareStatement(sql);
            pste.setInt(1, t.getId());
            pste.setDate(2, t.getD());
            pste.setInt(4, t.getC().getId());
            pste.setInt(3, t.getType().getId());
            pste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("add abonnement failed:" + ex.getMessage());
        }
    }

    @Override
    public List<Abonnement> getAll() {
        sql = "SELECT * FROM abonnement "
                + "INNER JOIN type_abonnement on abonnement.id_ty=type_abonnement.id "
                + "INNER JOIN utilisateur on abonnement.id_c=utilisateur.id";
        List<Abonnement> l = new ArrayList<>();
        try {
            ste = mc.createStatement();
            ResultSet r = ste.executeQuery(sql);
            while (r.next()) {
                if (!(new Type_abonnementService()).findById(r.getInt("abonnement.id_ty")).isEmpty()) {
                    Type_abonnement t = new Type_abonnement(r.getInt("type_abonnement.id"), r.getString("type_abonnement.nom"), r.getString("type_abonnement.description"), r.getFloat("type_abonnement.periode"), r.getFloat("type_abonnement.offre"), r.getFloat("type_abonnement.prix"));
                    Utilisateur u = new Utilisateur(r.getInt("utilisateur.id"), r.getString("utilisateur.nom"), r.getString("utilisateur.prenom"), Utilisateur.stringTogenre(r.getString("utilisateur.genre")), r.getString("utilisateur.email"), r.getString("utilisateur.mdp"), r.getDate("utilisateur.date_n"));
                    Abonnement a = new Abonnement(r.getInt("abonnement.id"),
                            r.getDate("abonnement.date_achat"),
                            t,
                            u);
                    l.add(a);
                }
            }
        } catch (SQLException ex) {
            System.out.println("aff abonnement failed:" + ex.getMessage());
        }
        return l;
    }

    @Override
    public void supprimer(Abonnement t) {
        sql = "DELETE FROM abonnement WHERE id=?";
        try {
            pste = mc.prepareStatement(sql);
            pste.setInt(1, t.getId());
            pste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("delete abonnement failed:" + ex.getMessage());
        }
    }

    @Override
    public void modifier(Abonnement t) {
        sql = "UPDATE abonnement SET date_achat=?,id_ty=?,id_c=? WHERE id=?";
        try {
            pste = mc.prepareStatement(sql);
            pste.setDate(1, t.getD());
            pste.setInt(2, t.getType().getId());
            pste.setInt(3, t.getC().getId());
            pste.setInt(4, t.getId());
            pste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("update abonnement failed:" + ex.getMessage());
        }
    }

    public List<Abonnement> select_byID(Abonnement t) {
        sql = "SELECT * FROM abonnement "
                + "INNER JOIN type_abonnement on abonnement.id_ty=type_abonnement.id "
                + "INNER JOIN utilisateur on abonnement.id_c=utilisateur.id "
                + "WHERE abonnement.id=?";
        List<Abonnement> l = new ArrayList<>();
        try {
            pste = mc.prepareStatement(sql);
            pste.setInt(1, t.getId());
            ResultSet r = pste.executeQuery();
            while (r.next()) {
                if (!(new Type_abonnementService()).findById(r.getInt("abonnement.id_ty")).isEmpty()) {
                    Type_abonnement t1 = new Type_abonnement(r.getInt("type_abonnement.id"), r.getString("type_abonnement.nom"), r.getString("type_abonnement.description"), r.getFloat("type_abonnement.periode"), r.getFloat("type_abonnement.offre"), r.getFloat("type_abonnement.prix"));
                    Utilisateur u = new Utilisateur(r.getInt("utilisateur.id"), r.getString("utilisateur.nom"), r.getString("utilisateur.prenom"), Utilisateur.stringTogenre(r.getString("utilisateur.genre")), r.getString("utilisateur.email"), r.getString("utilisateur.mdp"), r.getDate("utilisateur.date_n"));
                    Abonnement a = new Abonnement(r.getInt("abonnement.id"),
                            r.getDate("abonnement.date_achat"),
                            t1,
                            u);
                    l.add(a);
                }
            }
        } catch (SQLException ex) {
            System.out.println("select_byID abonnement failed:" + ex.getMessage());
        }
        return l;
    }

    public List<Abonnement> select_byType(Abonnement t) {
        sql = "SELECT * FROM abonnement "
                + "INNER JOIN type_abonnement on abonnement.id_ty=type_abonnement.id "
                + "INNER JOIN utilisateur on abonnement.id_c=utilisateur.id "
                + "WHERE abonnement.id_ty=?";
        List<Abonnement> l = new ArrayList<>();
        try {
            pste = mc.prepareStatement(sql);
            pste.setInt(1, t.getType().getId());
            ResultSet r = pste.executeQuery();
            while (r.next()) {
                if (!(new Type_abonnementService()).findById(r.getInt("abonnement.id_ty")).isEmpty()) {
                    Type_abonnement t1 = new Type_abonnement(r.getInt("type_abonnement.id"), r.getString("type_abonnement.nom"), r.getString("type_abonnement.description"), r.getFloat("type_abonnement.periode"), r.getFloat("type_abonnement.offre"), r.getFloat("type_abonnement.prix"));
                    Utilisateur u = new Utilisateur(r.getInt("utilisateur.id"), r.getString("utilisateur.nom"), r.getString("utilisateur.prenom"), Utilisateur.stringTogenre(r.getString("utilisateur.genre")), r.getString("utilisateur.email"), r.getString("utilisateur.mdp"), r.getDate("utilisateur.date_n"));
                    Abonnement a = new Abonnement(r.getInt("abonnement.id"),
                            r.getDate("abonnement.date_achat"),
                            t1,
                            u);
                    l.add(a);
                }
            }
        } catch (SQLException ex) {
            System.out.println("select_byID abonnement failed:" + ex.getMessage());
        }
        return l;
    }

    @Override
    public List<Abonnement> findById(int id) {
        return select_byID(new Abonnement(id, Date.valueOf(LocalDate.now()), new Type_abonnement(), new Utilisateur()));
    }

    @Override
    public List<Abonnement> trier() {
        return getAll().stream().sorted().collect(Collectors.toList());
    }

    public List<Abonnement> select_byID_u(Abonnement t) {
        sql = "SELECT * FROM abonnement "
                + "INNER JOIN type_abonnement on abonnement.id_ty=type_abonnement.id "
                + "INNER JOIN utilisateur on abonnement.id_c=utilisateur.id "
                + "WHERE utilisateur.id=?";
        List<Abonnement> l = new ArrayList<>();
        try {
            pste = mc.prepareStatement(sql);
            pste.setInt(1, t.getC().getId());
            ResultSet r = pste.executeQuery();
            while (r.next()) {
                if (!(new Type_abonnementService()).findById(r.getInt("abonnement.id_ty")).isEmpty()) {
                    Type_abonnement t1 = new Type_abonnement(r.getInt("type_abonnement.id"), r.getString("type_abonnement.nom"), r.getString("type_abonnement.description"), r.getFloat("type_abonnement.periode"), r.getFloat("type_abonnement.offre"), r.getFloat("type_abonnement.prix"));
                    Utilisateur u = new Utilisateur(r.getInt("utilisateur.id"), r.getString("utilisateur.nom"), r.getString("utilisateur.prenom"), Utilisateur.stringTogenre(r.getString("utilisateur.genre")), r.getString("utilisateur.email"), r.getString("utilisateur.mdp"), r.getDate("utilisateur.date_n"));
                    Abonnement a = new Abonnement(r.getInt("abonnement.id"),
                            r.getDate("abonnement.date_achat"),
                            t1,
                            u);
                    l.add(a);
                }
            }
        } catch (SQLException ex) {
            System.out.println("select_byID_u abonnement failed:" + ex.getMessage());
        }
        return l;
    }

    public Boolean HasAbonnement(Utilisateur u) {
        Abonnement a = new Abonnement(0, Date.valueOf(LocalDate.now()), new Type_abonnement(), u);
        if (!this.select_byID_u(a).isEmpty()) {
            this.select_byID_u(a).stream().forEach((ab) -> {
                LocalDate date = LocalDate.parse((ab.getD().toString()));
                date = date.plusDays((long) ab.getType().getPeriode());
                if (date.isBefore(LocalDate.now())) {
                    try {
                        SMS(ab);
                    } catch (IOException ex) {
                        System.out.println("err" + ex.getMessage());
                    }
                    this.supprimer(ab);
                }
            });
            return !this.select_byID_u(a).isEmpty();
        } else {
            return false;
        }
    }

    public Abonnement getAbonnement(Utilisateur u) {
        Abonnement a = new Abonnement(0, Date.valueOf(LocalDate.now()), new Type_abonnement(), u);
        if (this.HasAbonnement(u)) {
            a = this.select_byID_u(a).get(0);
        } else {
            a = null;
        }
        return a;

    }

    public Reservation implement_offre(Reservation t) {
        Utilisateur u = t.getUtilisateur();
        Vol v = t.getVol();
        t.setPrix(v.getPrix());
        if (this.HasAbonnement(u)) {
            Abonnement a = this.getAbonnement(u);
            t.setPrix((1 - a.getType().getOffre()) * v.getPrix());
        }
        return t;
    }

    public void SMS(Abonnement a) throws IOException {
        /*LocalDate date = LocalDate.parse((a.getD().toString()));
        date = date.plusDays((long) a.getType().getPeriode());
        int num = 29428612;
        String msg = "Hello " + a.getC().getNom() + "!we are sorry to inform you that your subcribtion " + a.getType().getNom() + " has already expired at" + date.getDayOfMonth() + " " + date.getMonth() + " " + date.getYear();
        System.out.println(msg);
        String s = "curl.exe -X POST \"https://api.twilio.com/2010-04-01/Accounts/ACd76065a353d77ee7eb1bc1b22792a497/Messages.json\"";
        s += " --data-urlencode \"Body=" + msg + "\"";
        s += " --data-urlencode \"From=+12766002481\"";
        s += " --data-urlencode \"To=+216" + num + "\"";
        s += " -u \"ACd76065a353d77ee7eb1bc1b22792a497:3ac261280dc2001e7312e5cc6662392f\"";
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", s);
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
        line = r.readLine();
        if (line == null) {
        break;
        }
        System.out.println(line);
        }*/
        int num=29428611;
        LocalDate date = LocalDate.parse((a.getD().toString()));
        date = date.plusDays((long) a.getType().getPeriode());
        String msg = "Hello " + a.getC().getNom() + " ! we are sorry to inform you that your subcribtion " + a.getType().getNom() + " has already expired at " + date.getDayOfMonth() + " " + date.getMonth() + " " + date.getYear();
        String ACCOUNT_SID ="ACd76065a353d77ee7eb1bc1b22792a497";
        String AUTH_TOKEN = "174c9d6243eecec3e6008c8587e9af92";
        /*String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
        String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");*/
        Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
        Message message = Message.creator(new com.twilio.type.PhoneNumber("+216"+num)
                                         ,new com.twilio.type.PhoneNumber("+12766002481"), 
                                          msg).create();
        message.getSid();
        
    }
}
