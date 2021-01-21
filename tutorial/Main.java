package tutorial;

import javax.persistence.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("$objectdb/db/p2.odb");
        EntityManager em = emf.createEntityManager();
        EntityManager em2 = emf.createEntityManager();

      /*  // Store 10 Point objects in the database:
        em.getTransaction().begin();
        for (int i = 0; i < 10; i++) {
            Point p = new Point(i, i);
            em.persist(p);
        }
        em.getTransaction().commit();
*/
        // Find the number of Point objects in the database:
        Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");
        System.out.println("Total Points: " + q1.getSingleResult());

        // Find the average X value:
        Query q2 = em.createQuery("SELECT AVG(p.x) FROM Point p");
        System.out.println("Average X: " + q2.getSingleResult());
        
        // amosar os atributos do punto con id =10 
        
        Query q3 = em.createQuery("SELECT p FROM Point p where id=10");
        System.out.println("ID 10: "+ q3.getSingleResult());
       
        // actualizar o punto de id =10
    
        em2.getTransaction().begin();
        Query q4 = em2.createQuery("UPDATE Point p set y=(y+2) where id=10");
        q4.executeUpdate();
        em2.getTransaction().commit();
        em2.close();
        
        //ELIMINAR el punto del ID=5
        em2 = emf.createEntityManager();
        em2.getTransaction().begin();
        Query q5 = em2.createQuery("DELETE FROM Point p WHERE id=5");
        q5.executeUpdate();
        em2.getTransaction().commit();
        em2.close();
        
        
         Scanner sc = new Scanner(System.in);

        //actualizacion masiva selectiva
        em2 = emf.createEntityManager();
        em2.getTransaction().begin();
        System.out.println("Introduce valor y para sustiuir todos los inferiores por 1000: ");
        int prt = sc.nextInt();
        Query q6 = em2.createQuery("UPDATE Point p set y=1000 where y<"+prt);
        q6.executeUpdate();
        em2.getTransaction().commit();
        em2.close();
        
        // borrado masivo selectivo masivo
        
        em2 = emf.createEntityManager();
        em2.getTransaction().begin();
        System.out.println("Introduce valor x para eliminarlo ");
        int prt2 = sc.nextInt();
        Query q7 = em2.createQuery("DELETE FROM Point p WHERE x<"+prt2);
        q7.executeUpdate();
        em2.getTransaction().commit();
        em2.close();
        

        // Retrieve all the Point objects from the database:
        TypedQuery<Point> query =
            em.createQuery("SELECT p FROM Point p", Point.class);
        List<Point> results = query.getResultList();
        for (Point p : results) {
            System.out.println("ID: "+p.getId()+" POINT: "+p);
            
        }
        
        // mostrar todos en los que x sea mayor a 3
        em2 = emf.createEntityManager();
        TypedQuery<Point> query2 = 
        em2.createQuery("SELECT p FROM Point p where x>3", Point.class);
        List<Point> results2 = query2.getResultList();
        for (Point p2 : results2) {
        System.out.println(" ID:"+p2.getId()+" X:"+p2.getX()+" Y:"+p2.getY());
        }

        // Close the database connection:
        em.close();
        em2.close();
        emf.close();
    }
}