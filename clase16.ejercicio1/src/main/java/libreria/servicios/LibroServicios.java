/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.servicios;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;

/**
 *
 * @author pc
 */
public class LibroServicios {

    Scanner leer = new Scanner(System.in, "UTF-8").useDelimiter("\n");

    public void crearLibro() {
        try {
            String persistenceUnitName = "com.egg.alumno_PU";
            EntityManager em = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
            var libro = new Libro();
            System.out.println("Ingrese el numero de ISBN del Libro: ");
            libro.setIsbn(leer.nextLong());
            System.out.println("Ingrese el título del Libro: ");
            libro.setTitulo(leer.next());
            System.out.println("Ingrese el año de publicación: ");
            libro.setAnio(leer.nextInt());
            System.out.println("Ingrese el autor: ");
            ponerAutor(libro);
            System.out.println("Ingrese la editorial : ");
            ponerEditorial(libro);
            libro.setAlta(Boolean.TRUE);
            em.getTransaction().begin();
            em.persist(libro);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            System.out.println("Error en creación de la entidad : " + e.getMessage());
        }
    }

    public Libro buscarLibro() {
        String persistenceUnitName = "com.egg.alumno_PU";
        EntityManager em = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
        System.out.println("Ingrese el ISBN que desea encontrar:  ");
        Libro buscado = em.find(Libro.class, leer.nextLong());
        return buscado;
    }

    public Libro modificarLibro() {
        Libro modificado = null;
        try {
            String persistenceUnitName = "com.egg.alumno_PU";
            EntityManager em = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
            modificado = buscarLibro();
            System.out.println("Ingrese el nuevo Titulo: ");
            modificado.setTitulo(leer.next());
            System.out.println("Ingrese Cantidada de Ejemplares Registrados: ");
            modificado.setEjemplares(leer.nextInt());
            System.out.println("Ingrese Ejemplares en Prestamo: ");
            modificado.setEjemplaresPrestados(leer.nextInt());
            System.out.println("Ingrese Ejemplares Restantes: ");
            modificado.setEjemplaresRestantes(leer.nextInt());
            em.getTransaction().begin();
            em.merge(modificado);
            em.getTransaction().commit();
        } catch (PersistenceException | InputMismatchException e) {
            System.out.println("Entrada inválida : " + e.getMessage());
        }
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

    public List<Libro> listarLibros() {
        String persistenceUnitName = "com.egg.alumno_PU";
        EntityManager em = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
        TypedQuery<Libro> consulta = em.createQuery("SELECT l FROM Libro l", Libro.class);
        return consulta.getResultList();
    }

    public Libro buscarLibroPorNombre(String nombre) {
        String persistenceUnitName = "com.egg.alumno_PU";
        EntityManager em = Persistence.createEntityManagerFactory(persistenceUnitName).createEntityManager();
        TypedQuery<Libro> listaLibros = em.createQuery("SELECT l FROM Libro l WHERE l.titulo = :nombre", Libro.class);
        listaLibros.setParameter("nombre", nombre);
        return listaLibros.getSingleResult();
    }

    private Libro ponerAutor(Libro libro) {
        try {
            AutorServicios asistencia = new AutorServicios();

            System.out.println("Ingrese el autor a asignar :");
            String paraAutor = leer.next();

            List<Autor> verifica = asistencia.buscarPorAutor(paraAutor);
            if (verifica.isEmpty()) {
                System.out.println("No encontrado. Se creará uno.");
                asistencia.crearAutor(new Autor());
                verifica = asistencia.buscarPorAutor(paraAutor);

                libro.setAutor(verifica.get(0));
            } else {
                for (Autor asignado : verifica) {
                    System.out.println("¿Desea asignar " + asignado.getNombre() + " ? (S/N)");
                    String duda = leer.next();
                    if (duda.equalsIgnoreCase("s")) {
                        libro.setAutor(asignado);
                        break;
                    }
                }
            }
        } catch (PersistenceException e) {
            System.out.println("Error al poner Autor:" + e.getMessage());
        }
        return libro;

    }

    private Libro ponerEditorial(Libro libro) {
        try {
            String paraEditorial = "";
            System.out.println("Ingrese el editor a asignar :");
            paraEditorial = leer.next();
            EditorialServicios asistencia = new EditorialServicios();
            Editorial verifica = asistencia.buscarEditorialPorNombre(paraEditorial);
            if (verifica == null) {
                System.out.println("No encontrado. Se creará uno.");
                asistencia.crearEditorial();
                verifica = asistencia.buscarEditorialPorNombre(paraEditorial);

                libro.setEditorial(verifica);
            } else {
                libro.setEditorial(verifica);
            }
        } catch (PersistenceException e) {
            System.out.println("Error al poner editorial:" + e.getMessage());
        }
        return libro;
    }

}
