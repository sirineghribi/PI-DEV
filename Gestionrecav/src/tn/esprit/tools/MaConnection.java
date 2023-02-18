/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.tools;


import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

/**
 *
 * @author sirin
 */
public class MaConnection {
    private Connection cnx;
        String url = "jdbc:mysql://localhost:3306/pidev";
        String user = "root";
       ///ut.println("Cnx etablie ");
        } catch (SQLException ex) {
            System.out.println("error");
        }
    }
    public static MaConnection getInstance(){
        if(ct ==null)
            ct= new MaConnection();
        return ct;
    }

    public Connection getCnx() {
        return cnx;
    }



}
