/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author sirin
 */
public class Rep {
    private int id;
     private String reponse;
     private Avis avis;
public Rep(){}
    public Rep(String reponse, Avis avis) {
        this.reponse = reponse;
        this.avis = avis;
    }

    public Rep(String reponse) {
        this.reponse = reponse;
    }

    public Rep(int id, String reponse) {
        this.id = id;
        this.reponse = reponse;
    }

    public Rep(int id, String reponse, Avis avis) {
        this.id = id;
        this.reponse = reponse;
        this.avis = avis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public Avis getAvis() {
        return avis;
    }

    public void setAvis(Avis avis) {
        this.avis = avis;
    }

    @Override
    public String toString() {
        return "Rep{" + "id=" + id + ", reponse=" + reponse + ", avis=" + avis + '}';
    }

    
     
}
