/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquivos;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author oracle
 */
public class Metodos {
     File x;
    
    public void eDirectorio(String cadea){
        x = new File(cadea);
        
        if(x.isDirectory()==true){
            
            System.out.println("E un directorio");
        }else{
            System.out.println("Non e un directorio");
        }
    }
    
    public void eFicheiro(String cadea){
       x = new File(cadea);
       
       if(x.isFile()==true){
           System.out.println("É un ficheiro");
       }else{
           System.out.println("Non e un ficheiro");
       }
    }
    
    public void creaDirectorio(String directorio){
       x = new File(directorio);
       
       if(x.exists()== true){
           System.out.println("O directorio xa existe");  
       }else{
         x.mkdir();
           System.out.println("Directorio creado");
       }
       
    }
    
    public void creaFicheiro(String dirName, String fileName) throws IOException{
      x = new File(dirName+"/"+fileName);
      
     // Se atópaste en windows debes sustituir o / por \\
      
      if(x.exists() == true){
          System.out.println("O ficheiro xa existe");
      }else{
          x.createNewFile();
          System.out.println("Ficheiro creado");
      }
    }
    
    public void modoAcceso(String dirName, String fileName){
        
        x = new File(dirName+"/"+fileName);
        
        if(x.exists()==true){
        if(x.canWrite()==true)
                System.out.println("escritura si");
        else
                System.out.println("escritura non");
        if(x.canRead()== true)
                System.out.println("lectura si");
        else
                System.out.println("lectura non");
        }
        
    }
    
    public void calculaLonxitude(String dirName, String fileName){
        
        x = new File(dirName+"/"+fileName);
        
        if(x.exists()==true){
            System.out.println("Lonxitude: "+x.length());
        }
        
    }
    
    public void mLectura(String dirName, String fileName){
        
        x = new File(dirName+"/"+fileName);
        
        if(x.exists()== true){
            x.setReadOnly();
            System.out.println("O arquivo pasa a ser só de lectura");
            
        }else{
            System.out.println("Non atopouse o arquivo");
            
        }
        
    }
    
    public void mEscritura(String dirName, String fileName){
        
        x = new File(dirName+"/"+fileName);
    
        if(x.exists() == true){
            x.setWritable(true);
            System.out.println("O arquivo pasa ser de escritura");
        }else{
            System.out.println("Non atopouse o arquivo");
        }
    }
    
    public void borraFicheiro(String dirName, String fileName){
        
         x = new File(dirName+"/"+fileName);   
     
         if(x.exists() == true){
             x.delete();
             System.out.println("Arquivo eliminado");
         }else{
             System.out.println("Ficheiro inexistente");
         }
     
    }
    
    public void borraDirectorio(String dirName){
        
        x = new File(dirName);
        
        if(x.exists() == true){
            x.delete();
            System.out.println("Directorio eliminado");
        }else{
            System.out.println("Ruta inexistente ou con descendencia");
        }
        
    }
    
    public void mContido(String dirName){
        
        x = new File(dirName);
        
        if(x.exists() == true){
            System.out.println("Contido:"+x.list());
        }else{
            System.out.println("Ruta inexistente ou con descendencia");
        }
        
    }
    
    
}
