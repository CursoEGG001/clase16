/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.egg.alumno.clase16.ejercicio1;

import java.util.Scanner;
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
            System.out.println("0. Salir");

            option = leer.nextInt();

            switch (option) {
                case 1:
                    // Create an author
                    System.out.println("{[Create un Autor]}");
                    au.crearAutor();
                    break;
                case 2:
                    // Search for an author
                    System.out.println("{[Buscate un Autor]}");
                    System.out.println(au.buscarAutor());
                    break;
                case 3:
                    // Delete an author
                    System.out.println("{[Borrate un autor]}");
                    au.eliminarAutor();
                    break;
                case 4:
                    // Modify an author
                    System.out.println("{[Cambiate un Autor]}");
                    au.modificarAutor();
                    break;
                case 5:
                    // Create a book
                    System.out.println("{[Create un Libro]}");
                    lb.crearLibro();
                    break;
                case 6:
                    // Search for a book
                    System.out.println("{[Buscate un Libro]}");
                    System.out.println(lb.buscarLibro());
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
                    // Search for an editorial
                    System.out.println("{[Buscate un Editorial]}");
                    System.out.println(ed.buscarEditorial());
                    break;
                case 11:
                    // Delete an editorial
                    System.out.println("{[Borrate un autor]}");
                    ed.eliminarEditorial();
                    break;
                case 12:
                    // Modify an editorial
                    System.out.println("{[Cambiate un Autor]}");
                    ed.modificarEditorial();
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
}
