
package pvehiculos2;

import java.io.Serializable;
import javax.persistence.*;
@Entity
public class Venfin implements Serializable{
    private static final long serialVersionUID = 1L; 
    @Id 
    Double id;
    String dni  ;		
    String nomec ;
    String nomevh ; 
    Double prezof; 

    public Venfin() {
    }

    public Venfin(Double id, String dni, String nomec, String nomevh, Double prezof) {
        this.id = id;
        this.dni = dni;
        this.nomec = nomec;
        this.nomevh = nomevh;
        this.prezof = prezof;
    }

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNomec() {
        return nomec;
    }

    public void setNomec(String nomec) {
        this.nomec = nomec;
    }

    public String getNomevh() {
        return nomevh;
    }

    public void setNomevh(String nomevh) {
        this.nomevh = nomevh;
    }

    public Double getPrezof() {
        return prezof;
    }

    public void setPrezof(Double prezof) {
        this.prezof = prezof;
    }

    @Override
    public String toString() {
        return "Venfin{" + "id=" + id + ", dni=" + dni + ", nomec=" + nomec + ", nomevh=" + nomevh + ", prezof=" + prezof + '}';
    }
    
}

