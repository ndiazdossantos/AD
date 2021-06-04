package pvehiculos;
import java.io.Serializable;
import javax.persistence.*;
@Entity
public class Vehiculos implements Serializable{
  private static final long serialVersionUID = 1L; 
    @Id 
    String codveh;  	 
    String nomveh  ;		
    int prezoorixe ;	
    int anomatricula;  

    public Vehiculos() {
    }

    public Vehiculos(String codveh, String nomveh, int prezoorixe, int anomatricula) {
        this.codveh = codveh;
        this.nomveh = nomveh;
        this.prezoorixe = prezoorixe;
        this.anomatricula = anomatricula;
    }

    public String getCodveh() {
        return codveh;
    }

    public void setCodveh(String codveh) {
        this.codveh = codveh;
    }

    public String getNomveh() {
        return nomveh;
    }

    public void setNomveh(String nomveh) {
        this.nomveh = nomveh;
    }

    public int getPrezoorixe() {
        return prezoorixe;
    }

    public void setPrezoorixe(int prezoorixe) {
        this.prezoorixe = prezoorixe;
    }

    public int getAnomatricula() {
        return anomatricula;
    }

    public void setAnomatricula(int anomatricula) {
        this.anomatricula = anomatricula;
    }

    @Override
    public String toString() {
        return "Vehiculos{" + "codveh=" + codveh + ", nomveh=" + nomveh + ", prezoorixe=" + prezoorixe + ", anomatricula=" + anomatricula + '}';
    }
    
}
