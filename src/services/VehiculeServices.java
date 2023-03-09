/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
//import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import entity.CategorieVehicule;
import entity.Maintenance;
import entity.Vehicule;
import tools.MaConnection;

/**
 *
 * @author ASUS
 */
public class VehiculeServices implements InterfaceService<Vehicule> {

    Connection cnx;

    public VehiculeServices() {
        cnx = MaConnection.getInstance().getCnx();
    }

    @Override
    public void ajouter(Vehicule v) {
        try {
            
            String sql = "insert into Vehicule(nom_vh, cat_vehicule, poid_sup, vitesse, nbr_pas, status) "
                    + "values (?,?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, v.getNom_vh()); 
            ste.setInt(2, v.getCategorieVehicule().get_id_cat());
            ste.setFloat(3, v.get_poid_sup());
            ste.setInt(4, v.get_vitesse());
            ste.setInt(5, v.get_nbr_pas());
            ste.setBoolean(6, v.getStatus());
            ste.executeUpdate();
            System.out.println("vehicule ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
  
    @Override
    public List<Vehicule> getAll() {
       //ObservableList<Vehicule> vehicules=FXCollections.observableArrayList();
         List<Vehicule> vehicules = new ArrayList<>();
        try {
            String sql = "select * from Vehicule inner join categorievehicule on vehicule.cat_vehicule = Categorievehicule.id_cat";
                    
                    // inner join Maintenance on Vehicule.status = Maintenance.status ";                    
                    //inner join Maintenance on Vehicule.status = Maintenance.status;
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {
                  Vehicule v = new Vehicule(s.getInt("Vehicule.id_vehicule"),s.getString("Vehicule.nom_vh"), new CategorieVehicule(s.getInt("CategorieVehicule.id_cat"),CategorieVehicule.enumtypecat(s.getString("CategorieVehicule.nom_cat")), s.getString("CategorieVehicule.lieu")) , s.getFloat("Vehicule.poid_sup"), s.getInt("Vehicule.vitesse"),s.getInt("Vehicule.nbr_pas"), s.getBoolean("Vehicule.status"));
                          //new Maintenance(s.getBoolean("Maintenance.status")) );
                  vehicules.add(v);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return vehicules;
    }

    @Override
    public List<Vehicule> findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

public void supprimerVehicule(Vehicule v) {
        String sql = "delete from Vehicule where id_vehicule = ?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, v.get_id_vehicule());
            ste.executeUpdate();
            System.out.println("vehicule supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void modifierVehiculeparNom(String nom_vh, Vehicule v) {
        String sql = "update Vehicule set nom_vh=? where id_vehicule=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, nom_vh);
            ste.setInt(2,v.get_id_vehicule());
            ste.executeUpdate();
            System.out.println("vehicule modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }    
    public List<Vehicule> searchbynom(String c){
        return getAll().stream().filter((m)-> m.getNom_vh().contains(c)).collect(Collectors.toList());
    }
    
    public List <Vehicule> sort(){
        return getAll().stream().sorted().collect(Collectors.toList());
    }
    public void genererPdf(Vehicule v){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File file = fileChooser.showSaveDialog(null);
if (file != null)
{
try {
                Document pdf = new Document();
                PdfWriter.getInstance(pdf, new FileOutputStream(file));
                pdf.open();
                Font font = new Font(Font.FontFamily.TIMES_ROMAN, 24);   
                Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 10); 
                Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 15); 
                Paragraph p=new Paragraph("Les details de "+v.getNom_vh()+"\n \n ",font);
                 Paragraph p1=new Paragraph(" Poids : "+v.get_poid_sup()+"\n"+" Nombre de places : "+v.get_nbr_pas()+"\n Vitesse : "+v.get_vitesse()+"\n Categorie : "+v.getCategorieVehicule().get_nom_cat()+"\n \n \n ",font2);
                Image image1 = Image.getInstance("C:\\Users\\lenovo\\Desktop\\S2\\Pidev\\java\\Ressources\\logoforinterplanetary.png");
                image1.scaleAbsolute(70f, 70f);
                image1.setAlignment(Element.ALIGN_RIGHT);
                pdf.add(image1);
                 Image image = set_image(v);
                image.scaleAbsolute(300f, 300f);
                image.setAlignment(Element.ALIGN_CENTER);
                 pdf.add(p);  
                pdf .add(p1);
                pdf.add(image); 
               
                pdf.close();
                
            } catch (IOException | DocumentException e) {
                e.printStackTrace();
            }


}
    }
   private Image set_image(Vehicule p)
    {
        try {
            String chemin;
            if (p.getNom_vh().equals("vv1"))
            {
                chemin="C:\\Users\\lenovo\\Desktop\\java\\Ressources\\attachments\\View-of-the-approaching-space-shuttle-Atlantis-480f474.jpg";
                
            }else if (p.getNom_vh().equals("v2"))
            {
                chemin="C:\\Users\\lenovo\\Desktop\\java\\Ressources\\attachments\\2-tosafelyexpl.jpg";
            }else if (p.getNom_vh().equals("v3"))
            {
                chemin="C:\\Users\\lenovo\\Desktop\\java\\Ressources\\attachments\\images.jpg";
            }else if (p.getNom_vh().equals("v4"))
            {
                chemin="C:\\Users\\lenovo\\Desktop\\java\\Ressources\\attachments\\main-qimg-59f415c829b32b7237707bb694d9e010-lq.jpg";
                
            }
            else
            {
                chemin="C:\\Users\\lenovo\\Desktop\\java\\Ressources\\attachments\\5b64c0358ea82f32008b4d16.webp";
            }
            Image image = Image.getInstance(chemin);
            return image ;
        } catch (BadElementException ex) {
            Logger.getLogger(VehiculeServices.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VehiculeServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }  

    @Override
    public void supprimer(Vehicule t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Vehicule t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Vehicule> trier() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

   