/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.servicios;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import libreria.entidades.Editorial;

/**
 *
 * @author pc
 */
public class EditorialServicios {

    Scanner leer = new Scanner(System.in, "UTF-8").useDelimiter("\n");
    String persistenceUnitName = "com.egg.alumno_PU";
    EntityManager em = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();

    public void crearEditorial() {
        try {

            var editor = new Editorial();
            System.out.println("Ingrese el numero de id del editor: ");
            editor.setId(leer.nextLong());
            System.out.println("Ingrese el nombre del editor");
            editor.setNombre(leer.next());
            editor.setAlta(Boolean.TRUE);
            em.getTransaction().begin();
            em.persist(editor);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            System.out.println("Edición Invalida : " + e.getMessage());
        }
    }

    public Editorial buscarEditorial(Long Id) {
        String persistenceUnitName = "com.egg.alumno_PU";
        EntityManager em = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
        Editorial buscado = em.find(Editorial.class, Id);
        return buscado;
    }

    public Editorial buscarEditorialPorNombre(String Nombre) {
        String persistenceUnitName = "com.egg.alumno_PU";
        TypedQuery<Editorial> esBuscado = null;
        try {
            EntityManager em = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
            esBuscado = em.createQuery("SELECT ed FROM Editorial ed WHERE ed.Nombre LIKE :nombre", Editorial.class);
            esBuscado.setParameter("nombre", Nombre);
        } catch (Exception e) {
            System.out.println("Operación inválida");
            em.close();
        }

        return esBuscado.getSingleResult();
    }

    public Editorial modificarEditorial(Editorial aCambiar) {

        Editorial modificado = buscarEditorial(aCambiar.getId());
        System.out.println("Ingrese el nuevo nombre del editor: ");
        modificado.setNombre(leer.next());
        em.getTransaction().begin();
        em.merge(modificado);
        em.getTransaction().commit();
        return modificado;
    }

    public void eliminarEditorial() {

        System.out.println("Ingrese el ID a eliminar");
        Editorial eliminado = buscarEditorial(leer.nextLong());
        eliminado.setAlta(Boolean.FALSE);
        em.getTransaction().begin();
        em.merge(eliminado); // em.remove(eliminado); va en verdad pero se sugiere usar el alta como seguridad que el dato no se usa.
        em.getTransaction().commit();
        System.out.println("Baja del editor realizada con éxito");

    }

    public List<Editorial> listarEditoriales() {

        TypedQuery<Editorial> query = em.createQuery("SELECT ed FROM Editorial ed", Editorial.class);
        return query.getResultList();
    }

}
