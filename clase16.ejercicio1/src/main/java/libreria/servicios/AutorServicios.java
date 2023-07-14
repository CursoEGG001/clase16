/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.servicios;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import libreria.entidades.Autor;

/**
 *
 * @author pc
 */
public class AutorServicios {

    Scanner leer = new Scanner(System.in, "UTF-8").useDelimiter("\n");

    public void crearAutor() {
        String persistenceUnitName = "com.egg.alumno_PU";
        EntityManager em = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
        var autor = new Autor();
        System.out.println("Ingrese el numero de id del autor: ");
        autor.setId(leer.nextLong());
        System.out.println("Ingrese el nombre del Autor");
        autor.setNombre(leer.next());
        autor.setAlta(Boolean.TRUE);
        em.getTransaction().begin();
        em.persist(autor);
        em.getTransaction().commit();
    }

    public Autor buscarAutor() {
        String persistenceUnitName = "com.egg.alumno_PU";
        EntityManager em = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
        System.out.println("Ingrese el numero de ID del autor que desea encontrar:  ");
        Autor buscado = em.find(Autor.class, leer.nextLong());
        return buscado;
    }

    public List<Autor> buscarPorAutor(String nombre) {
        String persistenceUnitName = "com.egg.alumno_PU";
        EntityManager em = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
        System.out.println("Ingrese el numero de ID del autor que desea encontrar:  ");

        List<Autor> busqueda = em.createQuery("SELECT a FROM Autor a WHERE a.Nombre LIKE :nombre")
                .setParameter("nombre", "%" + nombre + "%")
                .getResultList();
        return busqueda;
    }

    public Autor modificarAutor() {
        String persistenceUnitName = "com.egg.alumno_PU";
        EntityManager em = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
        Autor modificado = buscarAutor();
        System.out.println("Ingrese el nuevo nombre del autor: ");
        modificado.setNombre(leer.next());
        em.getTransaction().begin();
        em.merge(modificado);
        em.getTransaction().commit();
        return modificado;
    }

    public void eliminarAutor() {
        String persistenceUnitName = "com.egg.alumno_PU";
        EntityManager em = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
        Autor eliminado = buscarAutor();
        eliminado.setAlta(Boolean.FALSE);
        em.getTransaction().begin();
        em.merge(eliminado); // em.remove(eliminado); va en verdad pero se sugiere usar el alta como seguridad que el dato no se usa.
        em.getTransaction().commit();
        System.out.println("Baja del autor realizada con éxito");

    }

}
