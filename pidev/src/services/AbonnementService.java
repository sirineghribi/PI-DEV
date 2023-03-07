/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.Abonnement;
import entity.Utilisateur;
import entity.Type_abonnement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import tools.MaConnection;

/**
 *
 * @author zied loukil
 */
public class AbonnementService implements InterfaceService<Abonnement>{
    private String sql;
    private static final Connection mc=MaConnection.getInstance().getCnx();
    private Statement ste;
    private PreparedStatement pste;

    @Override
    public void ajouter(Abonnement t) {
        sql="INSERT INTO abonnement(id,date_achat,id_ty,id_c) values(?,?,?,?)";
        try{
        pste=mc.prepareStatement(sql);
        pste.setInt(1,t.getId());
        pste.setDate(2,t.getD());
        pste.setInt(4,t.getC().getId());
        pste.setInt(3,t.getType().getId());
        pste.executeUpdate();
        }
        catch(SQLException ex)
        {
            System.out.println("add abonnement failed:"+ex.getMessage());
        }
    }

    @Override
    public List<Abonnement> getAll() {
       sql="SELECT * FROM abonnement";
       List<Abonnement> l=new ArrayList<>(); 
       try {
            ste=mc.createStatement();
            ResultSet r=ste.executeQuery(sql);
            while(r.next())
            {
                if(!(new Type_abonnementService()).findById(r.getInt("id_ty")).isEmpty())
                {
                    Type_abonnement t=(new Type_abonnementService()).findById(r.getInt("id_ty")).get(0);
                    Abonnement a=new Abonnement(r.getInt("id"),
                                     r.getDate("date_achat"),
                                     t,
                                     new UtilisateurService().findById(r.getInt("id_c")).get(0)
                                     
                                    );
                    l.add(a);
                }
            }
        } catch (SQLException ex) 
        {
           System.out.println("aff abonnement failed:"+ex.getMessage());
        }
        return l;
    }

    @Override
    public void supprimer(Abonnement t) {
        sql="DELETE FROM abonnement WHERE id=?";
        try {
            pste=mc.prepareStatement(sql);
            pste.setInt(1,t.getId());
            pste.executeUpdate();
        } catch (SQLException ex) 
        {
            System.out.println("delete abonnement failed:"+ex.getMessage());
        }
    }

    @Override
    public void modifier(Abonnement t) {
        sql="UPDATE abonnement SET date_achat=?,id_ty=?,id_c=? WHERE id=?";
        try {
            pste=mc.prepareStatement(sql);
            pste.setDate(1,t.getD());
            pste.setInt(2,t.getType().getId());
            pste.setInt(3,t.getC().getId());
            pste.setInt(4,t.getId());
            pste.executeUpdate();
        } catch (SQLException ex) 
        {
            System.out.println("update abonnement failed:"+ex.getMessage());
        }
    }

    public List<Abonnement> select_byID(Abonnement t){
        sql="SELECT * FROM abonnement WHERE id=?";
        List<Abonnement> l=new ArrayList<>();
        try {
            pste=mc.prepareStatement(sql);
            pste.setInt(1,t.getId());
            ResultSet r=pste.executeQuery();
            while(r.next())
            {
               if(!(new Type_abonnementService().findById(r.getInt("id_ty")).isEmpty()))
               {Type_abonnement a=new Type_abonnementService().findById(r.getInt("id_ty")).get(0);
                l.add(new Abonnement(r.getInt("id"),
                                     r.getDate("date_achat"),
                                     a,
                                     new UtilisateurService().findById(r.getInt("id_c")).get(0)
                                    ));
            }
            }
        } catch (SQLException ex) 
        {
           System.out.println("select_byID abonnement failed:"+ex.getMessage());
        }
        return l;
    }
    
    @Override
    public List<Abonnement> findById(int id) {
        return select_byID(new Abonnement(id,Date.valueOf(LocalDate.now()),new Type_abonnement(),new Utilisateur()));
    }
    
    @Override
    public List<Abonnement> trier() {
        return getAll().stream().sorted().collect(Collectors.toList());
    }
    
}
