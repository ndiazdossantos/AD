

package pvehiculos3;
import java.io.Serializable;
import javax.persistence.*;
@Entity
public class Vendas implements Serializable{
    private static final long serialVersionUID = 1L; 
    @Id 
    int id;  	 
    String dni  ;		
    String codvh ;	
    Double tasas; 

    public Vendas() {
    }

    public Vendas(int id, String dni, String codvh, Double tasas) {
        this.id = id;
        this.dni = dni;
        this.codvh = codvh;
        this.tasas = tasas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCodvh() {
        return codvh;
    }

    public void setCodvh(String codvh) {
        this.codvh = codvh;
    }

    public Double getTasas() {
        return tasas;
    }

    public void setTasas(Double tasas) {
        this.tasas = tasas;
    }
    
    
    
}
