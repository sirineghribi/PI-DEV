/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.Type_abonnement;
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
 * @author zied loukil
 */
public class Type_abonnementService implements InterfaceService<Type_abonnement>{
    private String sql;
    private static final Connection mc=MaConnection.getInstance().getCnx();
    private Statement ste;
    private PreparedStatement pste;

    public Type_abonnementService() 
    {
        sql="";
    }
    public List<Type_abonnement> select_byName(Type_abonnement t)
    {
        sql="SELECT * FROM type_abonnement WHERE nom=?";
        List<Type_abonnement> l=new ArrayList<>();
        try {
            pste=mc.prepareStatement(sql);
            pste.setString(1,t.getNom());
            ResultSet r=pste.executeQuery();
            while(r.next())
            {
                l.add(new Type_abonnement(r.getInt("id"),r.getString("nom"),r.getString("description"),r.getFloat("periode"), r.getFloat("offre"),r.getFloat("prix")));
            }
        } catch (SQLException ex) 
        {
           System.out.println("select_byName type_abonnement failed:"+ex.getMessage());
        }
        return l;
    }
    public List<Type_abonnement> search_byName(String s)
    {
        return  getAll().stream().filter((ta)->ta.getNom().toLowerCase().contains(s.toLowerCase())).collect(Collectors.toList());
    }
    public List<Type_abonnement> select_byDesc(Type_abonnement t)
    {
        sql="SELECT * FROM type_abonnement WHERE description=?";
        List<Type_abonnement> l=new ArrayList<>();
        try {
            pste=mc.prepareStatement(sql);
            pste.setString(1,t.getDescription());
            ResultSet r=pste.executeQuery();
            while(r.next())
            {
                l.add(new Type_abonnement(r.getInt("id"),r.getString("nom"),r.getString("description"),r.getFloat("periode"), r.getFloat("offre"),r.getFloat("prix")));
            }
        } catch (SQLException ex) 
        {
           System.out.println("select_byDesc type_abonnement failed:"+ex.getMessage());
        }
        return l;
    }
    public List<Type_abonnement> search_byDesc(String s)
    {
        return  getAll().stream().filter((ta)->ta.getDescription().toLowerCase().contains(s.toLowerCase())).collect(Collectors.toList());
    }
    public List<Type_abonnement> select_byID(Type_abonnement t)
    {
        sql="SELECT * FROM type_abonnement WHERE id=?";
        List<Type_abonnement> l=new ArrayList<>();
        try {
            pste=mc.prepareStatement(sql);
            pste.setInt(1,t.getId());
            ResultSet r=pste.executeQuery();
            while(r.next())
            {
                l.add(new Type_abonnement(r.getInt("id"),r.getString("nom"),r.getString("description"),r.getFloat("periode"), r.getFloat("offre"),r.getFloat("prix")));
            }
        } catch (SQLException ex) 
        {
           System.out.println("select_byID type_abonnement failed:"+ex.getMessage());
        }
        return l;
    }
    public boolean exist(Type_abonnement t)
    {
       return !select_byID(t).isEmpty();
    }
    @Override
    public void ajouter(Type_abonnement t) 
    {
        sql="INSERT INTO type_abonnement(id,periode,description,nom,offre,prix) values(?,?,?,?,?,?)";
        try{
        pste=mc.prepareStatement(sql);
        pste.setInt(1,t.getId());
        pste.setFloat(2,t.getPeriode());
        pste.setString(3,t.getDescription());
        pste.setString(4,t.getNom());
        pste.setFloat(5,t.getOffre());
        pste.setFloat(6,t.getPrix());
        pste.executeUpdate();
        }
        catch(SQLException ex)
        {
            System.out.println("add type failed:"+ex.getMessage());
        }
    }
    @Override
    public List<Type_abonnement> getAll() 
    {
        sql="SELECT * FROM type_abonnement";
       List<Type_abonnement> l=new ArrayList<>(); 
       try {
            ste=mc.createStatement();
            ResultSet r=ste.executeQuery(sql);
            while(r.next())
            {
                l.add(new Type_abonnement(r.getInt("id"),r.getString("nom"),r.getString("description"),r.getFloat("periode"), r.getFloat("offre"),r.getFloat("prix")));
            }
        } catch (SQLException ex) 
        {
           System.out.println("aff type_abonnement failed:"+ex.getMessage());
        }
        return l;
    }
    @Override
    public void supprimer(Type_abonnement t) 
    {
        if(exist(t))
        {
        sql="DELETE FROM type_abonnement WHERE id=?";
        try {
            pste=mc.prepareStatement(sql);
            pste.setInt(1,t.getId());
            pste.executeUpdate();
        } catch (SQLException ex) 
        {
            System.out.println("delete type_abonnement failed:"+ex.getMessage());
        }
        }
        else 
        {
            System.out.println("entry dosent exist");
        }
    }
    @Override
    public void modifier(Type_abonnement t) 
    {
        if(exist(t))
        {
        sql="UPDATE type_abonnement SET nom=?,description=?,periode=?,offre=?,prix=? WHERE id=?";
        try {
            pste=mc.prepareStatement(sql);
            pste.setString(1,t.getNom());
            pste.setString(2,t.getDescription());
            pste.setFloat(3,t.getPeriode());
            pste.setFloat(4,t.getOffre());
            pste.setFloat(5,t.getPrix());
            pste.setInt(6,t.getId());
            pste.executeUpdate();
        } catch (SQLException ex) 
        {
            System.out.println("update type_abonnement failed:"+ex.getMessage());
        }
        }
        else 
        {
            System.out.println("entry dosent exist");
        }
    }
    @Override
    public List<Type_abonnement> findById(int id) 
    {
        return select_byID(new Type_abonnement(id,"","",0,0,0));
    }
    @Override
    public List<Type_abonnement> trier() 
    {
        return getAll().stream().sorted().collect(Collectors.toList());
    }
    public List<Type_abonnement> trier_par_Offre()
    {
        return getAll().stream().sorted((o1, o2) -> (int)(o1.getOffre()*100-o2.getOffre()*100)
        ).collect(Collectors.toList());
    }
    public List<Type_abonnement> trier_par_periode()
    {
        return getAll().stream().sorted((o1, o2) -> (int)(o1.getPeriode()-o2.getPeriode())
        ).collect(Collectors.toList());
    }
    public List<Type_abonnement> trier_par_prix()
    {
        return getAll().stream().sorted((o1, o2) -> (int)(o1.getPrix()-o2.getPrix())
        ).collect(Collectors.toList());
    }
}
