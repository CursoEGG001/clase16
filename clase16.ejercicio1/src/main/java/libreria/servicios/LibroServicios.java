/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.servicios;

import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import libreria.entidades.Libro;


/**
 *
 * @author pc
 */
public class LibroServicios {
    
    Scanner leer = new Scanner(System.in);

    public void crearLibro() {
        String persistenceUnitName = "com.egg.alumno_PU";
        EntityManager em = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
        var libro = new Libro();
        System.out.println("Ingrese el numero de ISBN del Libro: ");
        libro.setIsbn(leer.nextLong());
        System.out.println("Ingrese el título del Libro");
        libro.setTitulo(leer.next());
        libro.setAlta(Boolean.TRUE);
        em.getTransaction().begin();
        em.persist(libro);
        em.getTransaction().commit();
    }

    public Libro buscarLibro() {
        String persistenceUnitName = "com.egg.alumno_PU";
        EntityManager em = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
        System.out.println("Ingrese el ISBN que desea encontrar:  ");
        Libro buscado = em.find(Libro.class, leer.nextLong());
        return buscado;
    }

    public Libro modificarLibro() {
        String persistenceUnitName = "com.egg.alumno_PU";
        EntityManager em = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
        Libro modificado = buscarLibro();
        System.out.println("Ingrese el nuevo Titulo: ");
        modificado.setTitulo(leer.next());
        em.getTransaction().begin();
        em.merge(modificado);
        em.getTransaction().commit();
        return modificado;
    }

    public void eliminarLibro() {
        String persistenceUnitName = "com.egg.alumno_PU";
        EntityManager em = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
        Libro eliminado = buscarLibro();
        eliminado.setAlta(Boolean.FALSE);
        em.getTransaction().begin();
        em.merge(eliminado); // em.remove(eliminado); va en verdad pero se sugiere usar el alta como seguridad que el dato no se usa.
        em.getTransaction().commit();
        System.out.println("Baja del Libro realizada con éxito");

    }
}
