/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.egg.alumno.clase16.eextra1;

import com.egg.alumno.clase16.eextra1.biblioteca.entidades.Autor;
import com.egg.alumno.clase16.eextra1.biblioteca.entidades.Cliente;
import com.egg.alumno.clase16.eextra1.biblioteca.entidades.Editorial;
import com.egg.alumno.clase16.eextra1.biblioteca.entidades.Libro;
import com.egg.alumno.clase16.eextra1.biblioteca.entidades.Prestamo;
import com.egg.alumno.clase16.eextra1.biblioteca.servicios.AutorController;
import com.egg.alumno.clase16.eextra1.biblioteca.servicios.ClienteController;
import com.egg.alumno.clase16.eextra1.biblioteca.servicios.EditorialController;
import com.egg.alumno.clase16.eextra1.biblioteca.servicios.LibroController;
import com.egg.alumno.clase16.eextra1.biblioteca.servicios.PrestamoController;
import com.egg.alumno.clase16.eextra1.biblioteca.servicios.exceptions.NonexistentEntityException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author pc
 */
public class Clase16Eextra1 {


    private AutorController autorController;
    private EditorialController editorialController;
    private LibroController libroController;
    private PrestamoController prestamoController;
    private ClienteController clienteController;

    public Clase16Eextra1() {
    
        autorController = new AutorController();
        editorialController = new EditorialController();
        libroController = new LibroController();
        prestamoController = new PrestamoController();
        clienteController = new ClienteController();
    }

    public void ejecutar() throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            try {
                System.out.println("Menú Principal:");
                System.out.println("1) Autores");
                System.out.println("2) Editoriales");
                System.out.println("3) Libros");
                System.out.println("4) Préstamos");
                System.out.println("5) Clientes");
                System.out.println("6) Salir");
                
                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine();
                
                switch (opcion) {
                    case 1:
                        menuAutores();
                        break;
                    case 2:
                        menuEditoriales();
                        break;
                    case 3:
                        menuLibros();
                        break;
                    case 4:
                        menuPrestamos();
                        break;
                    case 5:
                        menuClientes();
                        break;
                    case 6:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                        break;
                }
                System.out.println();
            } catch (Exception ex) {
                throw ex;
            }
        }
    }

    private void menuAutores() throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            try {
                System.out.println("Menú de Autores:");
                System.out.println("1) Crear Autor");
                System.out.println("2) Buscar Autor");
                System.out.println("3) Listar Autores");
                System.out.println("4) Actualizar Autor");
                System.out.println("5) Eliminar Autor");
                System.out.println("6) Volver");
                
                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine();
                
                switch (opcion) {
                    case 1:
                        crearAutor();
                        break;
                    case 2:
                        buscarAutor();
                        break;
                    case 3:
                        listarAutores();
                        break;
                    case 4:
                        actualizarAutor();
                        break;
                    case 5:
                        eliminarAutor();
                        break;
                    case 6:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                        break;
                }
                System.out.println();
            } catch (NonexistentEntityException ex) {
               throw ex;
            } catch (Exception ex) {
                throw ex;
            }
        }
    }

    private void crearAutor() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre del autor: ");
        String nombre = scanner.nextLine();

        // Crear instancia de Autor y asignarle los valores ingresados
        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setAlta(true);

        // Llamar al controlador de Autores para crear el autor en la base de datos
        autorController.create(autor);
        System.out.println("Autor creado correctamente.");
    }

    private void buscarAutor() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ID del autor: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        // Llamar al controlador de Autores para buscar el autor por su ID
        Autor autor = autorController.findAutor(id);

        if (autor != null) {
            System.out.println("Autor encontrado:");
            System.out.println("ID: " + autor.getId());
            System.out.println("Nombre: " + autor.getNombre());
            System.out.println("Alta: " + autor.getAlta());
        } else {
            System.out.println("Autor no encontrado.");
        }
    }

    private void listarAutores() {
        // Llamar al controlador de Autores para obtener todos los autores
        List<Autor> autores = autorController.findAutorEntities();

        System.out.println("Listado de Autores:");
        for (Autor autor : autores) {
            System.out.println("ID: " + autor.getId());
            System.out.println("Nombre: " + autor.getNombre());
            System.out.println("Alta: " + autor.getAlta());
            System.out.println("--------------------");
        }
    }

    private void actualizarAutor() throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ID del autor: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        // Llamar al controlador de Autores para buscar el autor por su ID
        Autor autor = autorController.findAutor(id);

        if (autor != null) {
            try {
                System.out.print("Ingrese el nuevo nombre del autor: ");
                String nombre = scanner.nextLine();
                
                // Actualizar los datos del autor
                autor.setNombre(nombre);
                
                // Llamar al controlador de Autores para actualizar el autor en la base de datos
                autorController.edit(autor);
                System.out.println("Autor actualizado correctamente.");
            } catch (Exception ex) {
                throw ex;
            }
        } else {
            System.out.println("Autor no encontrado.");
        }
    }

    private void eliminarAutor() throws NonexistentEntityException {
        try {
            Scanner scanner = new Scanner(System.in);
            
            System.out.print("Ingrese el ID del autor: ");
            Long id = scanner.nextLong();
            scanner.nextLine();
            
            // Llamar al controlador de Autores para eliminar el autor por su ID
            autorController.destroy(id);
            System.out.println("Autor eliminado correctamente.");
        } catch (NonexistentEntityException ex) {
            throw ex;
        }
    }

    private void menuEditoriales() throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            try {
                System.out.println("Menú de Editoriales:");
                System.out.println("1) Crear Editorial");
                System.out.println("2) Buscar Editorial");
                System.out.println("3) Listar Editoriales");
                System.out.println("4) Actualizar Editorial");
                System.out.println("5) Eliminar Editorial");
                System.out.println("6) Volver");
                
                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine();
                
                switch (opcion) {
                    case 1:
                        crearEditorial();
                        break;
                    case 2:
                        buscarEditorial();
                        break;
                    case 3:
                        listarEditoriales();
                        break;
                    case 4:
                        actualizarEditorial();
                        break;
                    case 5:
                        eliminarEditorial();
                        break;
                    case 6:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                        break;
                }
                System.out.println();
            } catch (NonexistentEntityException ex) {
                throw ex;
            } catch (Exception ex) {
               throw ex;
            }
        }
    }

    private void crearEditorial() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre de la editorial: ");
        String nombre = scanner.nextLine();

        // Crear instancia de Editorial y asignarle los valores ingresados
        Editorial editorial = new Editorial();
        editorial.setNombre(nombre);
        editorial.setAlta(true);

        // Llamar al controlador de Editoriales para crear la editorial en la base de datos
        editorialController.create(editorial);
        System.out.println("Editorial creada correctamente.");
    }

    private void buscarEditorial() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ID de la editorial: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        // Llamar al controlador de Editoriales para buscar la editorial por su ID
        Editorial editorial = editorialController.findEditorial(id);

        if (editorial != null) {
            System.out.println("Editorial encontrada:");
            System.out.println("ID: " + editorial.getId());
            System.out.println("Nombre: " + editorial.getNombre());
            System.out.println("Alta: " + editorial.getAlta());
        } else {
            System.out.println("Editorial no encontrada.");
        }
    }

    private void listarEditoriales() {
        // Llamar al controlador de Editoriales para obtener todas las editoriales
        List<Editorial> editoriales = editorialController.findEditorialEntities();

        System.out.println("Listado de Editoriales:");
        for (Editorial editorial : editoriales) {
            System.out.println("ID: " + editorial.getId());
            System.out.println("Nombre: " + editorial.getNombre());
            System.out.println("Alta: " + editorial.getAlta());
            System.out.println("--------------------");
        }
    }

    private void actualizarEditorial() throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ID de la editorial: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        // Llamar al controlador de Editoriales para buscar la editorial por su ID
        Editorial editorial = editorialController.findEditorial(id);

        if (editorial != null) {
            try {
                System.out.print("Ingrese el nuevo nombre de la editorial: ");
                String nombre = scanner.nextLine();
                
                // Actualizar los datos de la editorial
                editorial.setNombre(nombre);
                
                // Llamar al controlador de Editoriales para actualizar la editorial en la base de datos
                editorialController.edit(editorial);
                System.out.println("Editorial actualizada correctamente.");
            } catch (Exception ex) {
                throw ex;
            }
        } else {
            System.out.println("Editorial no encontrada.");
        }
    }

    private void eliminarEditorial() throws NonexistentEntityException {
        try {
            Scanner scanner = new Scanner(System.in);
            
            System.out.print("Ingrese el ID de la editorial: ");
            Long id = scanner.nextLong();
            scanner.nextLine();
            
            // Llamar al controlador de Editoriales para eliminar la editorial por su ID
            editorialController.destroy(id);
            System.out.println("Editorial eliminada correctamente.");
        } catch (NonexistentEntityException ex) {
            throw ex;
        }
    }

    private void menuLibros() throws NonexistentEntityException, Exception {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            try {
                System.out.println("Menú de Libros:");
                System.out.println("1) Crear Libro");
                System.out.println("2) Buscar Libro");
                System.out.println("3) Listar Libros");
                System.out.println("4) Actualizar Libro");
                System.out.println("5) Eliminar Libro");
                System.out.println("6) Volver");
                
                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine();
                
                switch (opcion) {
                    case 1:
                        crearLibro();
                        break;
                    case 2:
                        buscarLibro();
                        break;
                    case 3:
                        listarLibros();
                        break;
                    case 4:
                        actualizarLibro();
                        break;
                    case 5:
                        eliminarLibro();
                        break;
                    case 6:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                        break;
                }
                System.out.println();
            } catch (NonexistentEntityException ex) {
                throw ex;
            } catch (Exception ex) {
                throw ex;
            }
        }
    }

    private void crearLibro() throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ISBN del libro: ");
        Long isbn = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Ingrese el título del libro: ");
        String titulo = scanner.nextLine();

        System.out.print("Ingrese el año de publicación del libro: ");
        int anio = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Ingrese la cantidad total de ejemplares del libro: ");
        int ejemplares = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Ingrese el ID del autor del libro: ");
        Long autorId = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Ingrese el ID de la editorial del libro: ");
        Long editorialId = scanner.nextLong();
        scanner.nextLine();

        // Obtener el autor y la editorial correspondientes a los IDs ingresados
        Autor autor = autorController.findAutor(autorId);
        Editorial editorial = editorialController.findEditorial(editorialId);

        if (autor != null && editorial != null) {
            try {
                // Crear instancia de Libro y asignarle los valores ingresados
                Libro libro = new Libro();
                libro.setIsbn(isbn);
                libro.setTitulo(titulo);
                libro.setAnio(anio);
                libro.setEjemplares(ejemplares);
                libro.setEjemplaresRestantes(ejemplares);
                libro.setAutor(autor);
                libro.setEditorial(editorial);
                
                // Llamar al controlador de Libros para crear el libro en la base de datos
                libroController.create(libro);
                System.out.println("Libro creado correctamente.");
            } catch (Exception ex) {
                System.out.println("No se pudo crear.");
                throw ex;
            }
        } else {
            System.out.println("Autor o editorial no encontrados.");
        }
    }

    private void buscarLibro() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ISBN del libro: ");
        Long isbn = scanner.nextLong();

        // Llamar al controlador de Libros para buscar el libro por su ISBN
        Libro libro = libroController.obtenerLibroPorISBN(isbn);

        if (libro != null) {
            System.out.println("Libro encontrado:");
            System.out.println("ISBN: " + libro.getIsbn());
            System.out.println("Título: " + libro.getTitulo());
            System.out.println("Año de Publicación: " + libro.getAnio());
            System.out.println("Ejemplares Prestados: " + libro.getEjemplaresPrestados());
            System.out.println("Ejemplares Restantes: " + libro.getEjemplaresRestantes());
            System.out.println("Autor: " + libro.getAutor().getNombre());
            System.out.println("Editorial: " + libro.getEditorial().getNombre());
        } else {
            System.out.println("Libro no encontrado.");
        }
    }

    private void listarLibros() {
        // Llamar al controlador de Libros para obtener todos los libros
        List<Libro> libros = libroController.findLibroEntities();

        System.out.println("Listado de Libros:");
        for (Libro libro : libros) {
            System.out.println("ISBN: " + libro.getIsbn());
            System.out.println("Título: " + libro.getTitulo());
            System.out.println("Año de Publicación: " + libro.getAnio());
            System.out.println("Ejemplares Prestados: " + libro.getEjemplaresPrestados());
            System.out.println("Ejemplares Restantes: " + libro.getEjemplaresRestantes());
            System.out.println("Autor: " + libro.getAutor().getNombre());
            System.out.println("Editorial: " + libro.getEditorial().getNombre());
            System.out.println("--------------------");
        }
    }

    private void actualizarLibro() throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ISBN del libro: ");
        Long isbn = scanner.nextLong();

        // Llamar al controlador de Libros para buscar el libro por su ISBN
        Libro libro = libroController.obtenerLibroPorISBN(isbn);

        if (libro != null) {
            try {
                System.out.print("Ingrese el nuevo título del libro: ");
                String titulo = scanner.nextLine();
                
                System.out.print("Ingrese el nuevo año de publicación del libro: ");
                int anio = scanner.nextInt();
                scanner.nextLine();
                
                System.out.print("Ingrese la nueva cantidad total de ejemplares del libro: ");
                int ejemplares = scanner.nextInt();
                scanner.nextLine();
                
                // Actualizar los datos del libro
                libro.setTitulo(titulo);
                libro.setAnio(anio);
                libro.setEjemplares(ejemplares);
                libro.setEjemplaresRestantes(ejemplares);
                
                // Llamar al controlador de Libros para actualizar el libro en la base de datos
                libroController.edit(libro);
                System.out.println("Libro actualizado correctamente.");
            } catch (Exception ex) {
                throw ex;
            }
        } else {
            System.out.println("Libro no encontrado.");
        }
    }

    private void eliminarLibro() throws NonexistentEntityException {
        try {
            Scanner scanner = new Scanner(System.in);
            
            System.out.print("Ingrese el ISBN del libro: ");
            Long isbn = scanner.nextLong();
            
            // Llamar al controlador de Libros para eliminar el libro por su ISBN
            libroController.destroy(isbn);
            System.out.println("Libro eliminado correctamente.");
        } catch (NonexistentEntityException ex) {
            throw ex;
        }
    }

    private void menuPrestamos() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            try {
                System.out.println("Menú de Préstamos:");
                System.out.println("1) Crear Préstamo");
                System.out.println("2) Buscar Préstamo");
                System.out.println("3) Listar Préstamos");
                System.out.println("4) Actualizar Préstamo");
                System.out.println("5) Eliminar Préstamo");
                System.out.println("6) Volver");
                
                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine();
                
                switch (opcion) {
                    case 1:
                        crearPrestamo();
                        break;
                    case 2:
                        buscarPrestamo();
                        break;
                    case 3:
                        listarPrestamos();
                        break;
                    case 4:
                        actualizarPrestamo();
                        break;
                    case 5:
                        eliminarPrestamo();
                        break;
                    case 6:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                        break;
                }
                System.out.println();
            } catch (NonexistentEntityException ex) {
                System.out.println("No existe : " + ex.getMessage());
            } catch (Exception ex) {
                System.out.println("Error inesperado : " + ex.getMessage());
            }
        }
    }

    private void crearPrestamo() throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ID del libro a prestar: ");
        Long libroId = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Ingrese el ID del cliente: ");
        Long clienteId = scanner.nextLong();
        scanner.nextLine();

        // Obtener el libro y el cliente correspondientes a los IDs ingresados
        Libro libro = libroController.findLibro(libroId);
        Cliente cliente = clienteController.findCliente(clienteId);

        if (libro != null && cliente != null) {
            // Verificar si hay ejemplares disponibles para prestar
            if (libro.getEjemplaresRestantes() > 0) {
                try {
                    // Crear instancia de Prestamo y asignarle los valores ingresados
                    Prestamo prestamo = new Prestamo();
                    prestamo.setLibro(libro);
                    prestamo.setCliente(cliente);
                    
                    // Llamar al controlador de Prestamos para crear el prestamo en la base de datos
                    prestamoController.create(prestamo);
                    System.out.println("Préstamo creado correctamente.");
                    
                    // Actualizar la cantidad de ejemplares prestados y restantes del libro
                    libro.setEjemplaresPrestados(libro.getEjemplaresPrestados() + 1);
                    libro.setEjemplaresRestantes(libro.getEjemplaresRestantes() - 1);
                    libroController.edit(libro);
                } catch (Exception ex) {
                    throw ex;
                }
            } else {
                System.out.println("No hay ejemplares disponibles para prestar.");
            }
        } else {
            System.out.println("Libro o cliente no encontrado.");
        }
    }

    private void buscarPrestamo() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ID del préstamo: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        // Llamar al controlador de Prestamos para buscar el préstamo por su ID
        Prestamo prestamo = prestamoController.findPrestamo(id);

        if (prestamo != null) {
            System.out.println("Préstamo encontrado:");
            System.out.println("ID: " + prestamo.getId());
            System.out.println("Fecha de Préstamo: " + prestamo.getFechaPrestamo());
            System.out.println("Fecha de Devolución: " + prestamo.getFechaDevolucion());
            System.out.println("Libro: " + prestamo.getLibro().getTitulo());
            System.out.println("Cliente: " + prestamo.getCliente().getNombre() + " " + prestamo.getCliente().getApellido());
        } else {
            System.out.println("Préstamo no encontrado.");
        }
    }

    private void listarPrestamos() {
        // Llamar al controlador de Prestamos para obtener todos los préstamos
        List<Prestamo> prestamos = prestamoController.findPrestamoEntities();

        System.out.println("Listado de Préstamos:");
        for (Prestamo prestamo : prestamos) {
            System.out.println("ID: " + prestamo.getId());
            System.out.println("Fecha de Préstamo: " + prestamo.getFechaPrestamo());
            System.out.println("Fecha de Devolución: " + prestamo.getFechaDevolucion());
            System.out.println("Libro: " + prestamo.getLibro().getTitulo());
            System.out.println("Cliente: " + prestamo.getCliente().getNombre() + " " + prestamo.getCliente().getApellido());
            System.out.println("--------------------");
        }
    }

    private void actualizarPrestamo() throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ID del préstamo: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        // Llamar al controlador de Prestamos para buscar el préstamo por su ID
        Prestamo prestamo = prestamoController.findPrestamo(id);

        if (prestamo != null) {
            try {
                System.out.print("Ingrese la nueva fecha de devolución (dd/mm/yyyy): ");
                String fechaDevolucionStr = scanner.nextLine();
                // Convertir la cadena de fecha a objeto Date
                
                Date fechaDevolucion;
                fechaDevolucion = new SimpleDateFormat("dd/MM/yyyy").parse(fechaDevolucionStr);

                // Actualizar la fecha de devolución del préstamo
                prestamo.setFechaDevolucion(fechaDevolucion);
                
                // Llamar al controlador de Prestamos para actualizar el préstamo en la base de datos
                prestamoController.edit(prestamo);
                System.out.println("Préstamo actualizado correctamente.");
            } catch (Exception ex) {
                throw ex;
            }
        } else {
            System.out.println("Préstamo no encontrado.");
        }
    }

    private void eliminarPrestamo() throws NonexistentEntityException {
        try {
            Scanner scanner = new Scanner(System.in);
            
            System.out.print("Ingrese el ID del préstamo: ");
            Long id = scanner.nextLong();
            scanner.nextLine();
            
            // Llamar al controlador de Prestamos para eliminar el préstamo por su ID
            prestamoController.destroy(id);
            System.out.println("Préstamo eliminado correctamente.");
        } catch (NonexistentEntityException ex) {
            throw ex;
        }
    }

    private void menuClientes() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            try {
                System.out.println("Menú de Clientes:");
                System.out.println("1) Crear Cliente");
                System.out.println("2) Buscar Cliente");
                System.out.println("3) Listar Clientes");
                System.out.println("4) Actualizar Cliente");
                System.out.println("5) Eliminar Cliente");
                System.out.println("6) Volver");
                
                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();
                scanner.nextLine();
                
                switch (opcion) {
                    case 1:
                        crearCliente();
                        break;
                    case 2:
                        buscarCliente();
                        break;
                    case 3:
                        listarClientes();
                        break;
                    case 4:
                        actualizarCliente();
                        break;
                    case 5:
                        eliminarCliente();
                        break;
                    case 6:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                        break;
                }
                System.out.println();
            } catch (NonexistentEntityException ex) {
                System.out.println("No existente :"+ex.getMessage());
            } catch (Exception ex) {
                System.out.println("Error inesperado : " + ex.getMessage());
            }
        }
    }

    private void crearCliente() {
        Scanner dato = new Scanner(System.in);

        System.out.print("Ingrese el documento del cliente: ");
        Long documento = dato.nextLong();

        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = dato.nextLine();

        System.out.print("Ingrese el apellido del cliente: ");
        String apellido = dato.nextLine();

        System.out.print("Ingrese el teléfono del cliente: ");
        String telefono = dato.nextLine();

        // Crear instancia de Cliente y asignarle los valores ingresados
        Cliente cliente = new Cliente();
        cliente.setDocumento(documento);
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setTelefono(telefono);

        // Llamar al controlador de Clientes para crear el cliente en la base de datos
        clienteController.create(cliente);
        System.out.println("Cliente creado correctamente.");
    }

    private void buscarCliente() {
        Scanner dato = new Scanner(System.in);

        System.out.print("Ingrese el ID del cliente: ");
        Long id = dato.nextLong();
        dato.nextLine();

        // Llamar al controlador de Clientes para buscar el cliente por su ID
        Cliente cliente = clienteController.findCliente(id);

        if (cliente != null) {
            System.out.println("Cliente encontrado:");
            System.out.println("ID: " + cliente.getId());
            System.out.println("Documento: " + cliente.getDocumento());
            System.out.println("Nombre: " + cliente.getNombre());
            System.out.println("Apellido: " + cliente.getApellido());
            System.out.println("Teléfono: " + cliente.getTelefono());
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private void listarClientes() {
        // Llamar al controlador de Clientes para obtener todos los clientes
        List<Cliente> clientes = clienteController.findClienteEntities();

        System.out.println("Listado de Clientes:");
        for (Cliente cliente : clientes) {
            System.out.println("ID: " + cliente.getId());
            System.out.println("Documento: " + cliente.getDocumento());
            System.out.println("Nombre: " + cliente.getNombre());
            System.out.println("Apellido: " + cliente.getApellido());
            System.out.println("Teléfono: " + cliente.getTelefono());
            System.out.println("--------------------");
        }
    }

    private void actualizarCliente() throws Exception {
        Scanner dato = new Scanner(System.in);

        System.out.print("Ingrese el ID del cliente: ");
        Long id = dato.nextLong();
        dato.nextLine();

        // Llamar al controlador de Clientes para buscar el cliente por su ID
        Cliente cliente = clienteController.findCliente(id);

        if (cliente != null) {
            try {
                System.out.print("Ingrese el nuevo documento del cliente: ");
                Long documento = dato.nextLong();
                
                System.out.print("Ingrese el nuevo nombre del cliente: ");
                String nombre = dato.nextLine();
                
                System.out.print("Ingrese el nuevo apellido del cliente: ");
                String apellido = dato.nextLine();
                
                System.out.print("Ingrese el nuevo teléfono del cliente: ");
                String telefono = dato.nextLine();
                
                // Actualizar los datos del cliente
                cliente.setDocumento(documento);
                cliente.setNombre(nombre);
                cliente.setApellido(apellido);
                cliente.setTelefono(telefono);
                
                // Llamar al controlador de Clientes para actualizar el cliente en la base de datos
                clienteController.edit(cliente);
                System.out.println("Cliente actualizado correctamente.");
            } catch (Exception ex) {
                throw ex;
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private void eliminarCliente() throws NonexistentEntityException {
        try {
            Scanner dato = new Scanner(System.in);
            
            System.out.print("Ingrese el ID del cliente: ");
            Long id = dato.nextLong();
            dato.nextLine();
            
            // Llamar al controlador de Clientes para eliminar el cliente por su ID
            clienteController.destroy(id);
            System.out.println("Cliente eliminado correctamente.");
        } catch (NonexistentEntityException ex) {
            throw ex;
        }
    }

    public static void main(String[] args) {
        try {
            Clase16Eextra1 app = new Clase16Eextra1();
            app.ejecutar();
        } catch (Exception ex) {
            System.out.println("Imposible iniciar : "+ex.getMessage());
        }
    }
}
