/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.egg.alumno.clase16.ejercicio1;

import java.util.List;
import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.servicios.AutorServicios;
import libreria.servicios.EditorialServicios;
import libreria.servicios.LibroServicios;

/**
 *
 * @author pc
 */
public class Clase16Ejercicio1 {

    public static void main(String[] args) {
        AutorServicios au = new AutorServicios();
        LibroServicios lb = new LibroServicios();
        EditorialServicios ed = new EditorialServicios();

        Scanner leer = new Scanner(System.in, "UTF-8");

        int option;
        do {
            System.out.println("\t{[Menu]}");
            System.out.println("1. Crear un autor");
            System.out.println("2. Buscar un autor");
            System.out.println("3. Eliminar un autor");
            System.out.println("4. Modificar un autor");
            System.out.println("5. Crear un libro");
            System.out.println("6. Buscar un libro");
            System.out.println("7. Eliminar un libro");
            System.out.println("8. Modificar un libro");
            System.out.println("9. Crear una editorial");
            System.out.println("10. Buscar una editorial");
            System.out.println("11. Eliminar una editorial");
            System.out.println("12. Modificar una editorial");
            System.out.println("13. Listar Autores");
            System.out.println("14. Listar Libros");
            System.out.println("15. Listar Editoriales");
            System.out.println("0. Salir");

            option = leer.nextInt();

            switch (option) {
                case 1:
                    // Create an author
                    System.out.println("{[Create un Autor]}");
                    Autor creado = new Autor();
                    System.out.println("Ingrese un nombre de autor:");
                    creado.setNombre(leer.next());
                    creado.setAlta(Boolean.TRUE);
                    au.crearAutor(creado);
                    break;
                case 2:
                    // Search for an author
                    System.out.println("{[Buscate un Autor]}");
                    //System.out.println(au.buscarAutor());
                    System.out.println("Ingrese un Nombre de Autor");
                    String dato = leer.next();
                    System.out.println(au.buscarPorAutor(dato));
                    break;
                case 3:
                    // Delete an author
                    System.out.println("{[Borrate un autor]}");
                    System.out.println("Ingrese un nombre a buscar para eliminar:");
                    String busqueda = leer.next();
                    List<Autor> encontrados = au.buscarPorAutor(busqueda);
                    for (Autor encontrado : encontrados) {
                        if (encontrado.getNombre().contains(busqueda)) {
                            au.eliminarAutor(au.buscarAutor(encontrado.getId()));
                        }
                    }

                    break;
                case 4:
                    // Modify an author
                    System.out.println("{[Cambiate un Autor]}");
                    System.out.println("Ingrese un nombre a buscar para Modificar:");
                    busqueda = leer.next();
                    List<Autor> aModificar = au.buscarPorAutor(busqueda);
                    for (Autor elegido : aModificar) {
                        if (elegido.getNombre().contains(busqueda)) {
                            System.out.println("Nombre: "+elegido.getNombre()+ ", "+elegido.getId());
                        au.modificarAutor(elegido);
                        }
                        
                    }
                    
                    break;
                case 5:
                    // Create a book
                    System.out.println("{[Create un Libro]}");
                    lb.crearLibro();
                    break;
                case 6:
                    MenuBuscaLibro(leer, lb);
                    break;
                case 7:
                    // Delete a book
                    System.out.println("{[Borrate un Libro]}");
                    lb.eliminarLibro();
                    break;
                case 8:
                    // Modify a book
                    System.out.println("{[Cambiate un Libro]}");
                    lb.modificarLibro();
                    break;
                case 9:
                    // Create an editorial
                    System.out.println("{[Create un Editor]}");
                    ed.crearEditorial();
                    break;
                case 10:
                    MenuBuscarEditoriales(leer, ed, lb);
                    break;
                case 11:
                    // Delete an editorial
                    System.out.println("{[Borrate un autor]}");
                    ed.eliminarEditorial();
                    break;
                case 12:
                    // Modify an editorial
                    System.out.println("{[Cambiate un Autor]}");
                    ed.modificarEditorial(ed.buscarEditorialPorNombre(leer.next()));
                    break;
                case 13:
                    menuMuestraAutores(au);
                    break;
                case 14:
                    menuMuestraLibros(lb);
                    break;
                case 15:
                    menuMuestraEditoriales(ed);
                    break;
                case 0:
                    // Exit
                    break;
                default:
                    System.out.println("Opción no valida");
            }
        } while (option != 0);

        System.out.println("-----------------------------------");

    }

    private static void MenuBuscarEditoriales(Scanner leer, EditorialServicios ed, LibroServicios lb) throws AssertionError {
        // Search for an editorial
        System.out.println("{[Buscate un Editorial]}");
        System.out.println("¿Qué desea buscar?");
        System.out.println("-1. Busca una Editorial");
        System.out.println("-2. Libros de una Editorial");
        System.out.println("Elija una opción");

        int criterio = leer.nextInt();
        switch (criterio) {
            case 1:
                System.out.println(ed.buscarEditorialPorNombre(leer.next()));
                break;
            case 2:
                System.out.println("Ingrese la editorial a mostrar:");
                String aBuscar = leer.next();
                for (Libro susLibros : lb.listarLibros()) {
                    for (Editorial deEditorial : ed.listarEditoriales()) {
                        if (susLibros.getEditorial().getNombre().contains(aBuscar) && deEditorial.getNombre().contains(aBuscar)) {
                            System.out.println("Ed. : " + deEditorial.getNombre() + ", " + susLibros.getTitulo() + " (" + susLibros.getIsbn() + ") ");
                        }
                    }

                }
                break;
            default:
                throw new AssertionError();
        }
    }

    private static void menuMuestraEditoriales(EditorialServicios ed) {
        System.out.println("Lista de Editoriales");
        for (Editorial laEditorial : ed.listarEditoriales()) {
            System.out.println("Ed. " + laEditorial.getNombre());
        }
    }

    private static void menuMuestraLibros(LibroServicios lb) {
        System.out.println("Lista de Libros:\n\n");
        for (Libro listarLibro : lb.listarLibros()) {
            System.out.println("(" + listarLibro.getIsbn() + ")" + listarLibro.getTitulo()
                    + " , " + listarLibro.getAutor()
                    + " , " + listarLibro.getAnio()
                    + "\n\t" + listarLibro.getEditorial()
                    + "\tPr.: " + listarLibro.getEjemplaresPrestados()
                    + "\t N°EJ: " + listarLibro.getEjemplares());
        }
    }

    private static void menuMuestraAutores(AutorServicios au) {
        System.out.println("Lista de Autores:\n\n");
        for (Autor losAutores : au.listarAutores()) {
            System.out.println(losAutores.getNombre() + "\t[" + losAutores.getId() + "]");
        }
        return;
    }

    private static void MenuBuscaLibro(Scanner leer, LibroServicios lb) throws AssertionError {
        // Search for a book
        System.out.println("{[Buscate un Libro]}");
        System.out.println("-1. Buscar por ISBN");
        System.out.println("-2. Buscar por Nombre");
        System.out.println("-3. Buscar por Autor");
        int criterio = leer.nextInt();

        switch (criterio) {
            case 1:
                System.out.println("Escriba el ISBN del Libro :");
                System.out.println(lb.buscarLibro());
                break;
            case 2:
                System.out.println("Escriba un nombre de libro :");
                String paraBuscar = leer.next();

                System.out.println(lb.buscarLibroPorNombre(paraBuscar));
                break;
            case 3:
                for (Libro filtroLibro : lb.listarLibros()) {
                    System.out.print("Ingrese un Autor para buscar: ");
                    Autor delFiltro = filtroLibro.getAutor();
                    if (filtroLibro.getAutor().getAlta() && delFiltro.getNombre().contains((leer.next()))) {
                        System.out.println("Autor: "
                                + delFiltro.getNombre() + "\t("
                                + delFiltro.getId()
                                + ") ,Libro: "
                                + filtroLibro.getTitulo());
                    }
                }
                break;
            default:
                throw new AssertionError();
        }
    }
}
