/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Zeineb Ben Mami
 */
public class Reservation {
   private  int id_r,id_c;

    public Reservation() {
    }

    public Reservation(int id_r, int id_c) {
        this.id_r = id_r;
        this.id_c = id_c;
    }
    
      public Reservation( int id_c) {
     
        this.id_c = id_c;
    }

    public int getId_r() {
        return id_r;
    }

    public void setId_r(int id_r) {
        this.id_r = id_r;
    }

    public int getId_c() {
        return id_c;
    }

    public void setId_c(int id_c) {
        this.id_c = id_c;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id_r=" + id_r + ", id_c=" + id_c + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reservation other = (Reservation) obj;
        if (this.id_r != other.id_r) {
            return false;
        }
        if (this.id_c != other.id_c) {
            return false;
        }
        return true;
    }
   
   
    
    
    
    
}
