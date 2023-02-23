/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entity;

/**
 *
 * @author abder
 */
public class Maintenance {
    
    private int id_v,id_m;
    private boolean status;
    Float duree,cout;
    public Maintenance() {
}
    
    public Maintenance(int id_m, int id_v, boolean status, Float duree, Float cout) {
        this.id_v = id_v;
        this.id_m = id_m;
        this.status= status;
        this.duree = duree;
        this.cout=cout;
    }

    public Maintenance(int id_v, boolean status, Float duree, Float cout) {
        this.id_v = id_v;
        this.status = status;
        this.duree = duree;
        this.cout = cout;
    }

    public Maintenance(boolean status) {
        this.status = status;
    }
    
    

    public int getId_v() {
        return id_v;
    }
    
        public int getId_m() {
        return id_m;
    }

    public Float getDuree() {
        return duree;
    }

    public Float getCout() {
        return cout;
    }

    public boolean getStatus() {
        return status;
    }

    public void setId_f(int id_m) {
        this.id_m = id_m;
    }

    public void setDuree(Float duree) {
        this.duree = duree;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Maintenance{" + "id_m=" + id_m + ", id_v=" + id_v + ", status" + status + ", duree=" + duree + ",cout="+ cout+'}';
    }
    
    
}
