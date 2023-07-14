/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.servicios;

import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import libreria.entidades.Editorial;

/**
 *
 * @author pc
 */
public class EditorialServicios {

    Scanner leer = new Scanner(System.in, "UTF-8").useDelimiter("\n");

    public void crearEditorial() {
        String persistenceUnitName = "com.egg.alumno_PU";
        EntityManager em = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
        var editor = new Editorial();
        System.out.println("Ingrese el numero de id del editor: ");
        editor.setId(leer.nextLong());
        System.out.println("Ingrese el nombre del editor");
        editor.setNombre(leer.next());
        editor.setAlta(Boolean.TRUE);
        em.getTransaction().begin();
        em.persist(editor);
        em.getTransaction().commit();
    }

    public Editorial buscarEditorial() {
        String persistenceUnitName = "com.egg.alumno_PU";
        EntityManager em = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
        System.out.println("Ingrese el numero de ID del editor que desea encontrar:  ");

        Editorial buscado = em.find(Editorial.class, leer.nextLong());
        return buscado;
    }

    public Editorial modificarEditorial() {
        String persistenceUnitName = "com.egg.alumno_PU";
        EntityManager em = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
        Editorial modificado = buscarEditorial();
        System.out.println("Ingrese el nuevo nombre del editor: ");
        modificado.setNombre(leer.next());
        em.getTransaction().begin();
        em.merge(modificado);
        em.getTransaction().commit();
        return modificado;
    }

    public void eliminarEditorial() {
        String persistenceUnitName = "com.egg.alumno_PU";
        EntityManager em = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
        Editorial eliminado = buscarEditorial();
        eliminado.setAlta(Boolean.FALSE);
        em.getTransaction().begin();
        em.merge(eliminado); // em.remove(eliminado); va en verdad pero se sugiere usar el alta como seguridad que el dato no se usa.
        em.getTransaction().commit();
        System.out.println("Baja del editor realizada con éxito");

    }
}
