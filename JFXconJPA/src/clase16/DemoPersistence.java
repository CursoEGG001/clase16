/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package clase16;

import static javafx.application.Application.launch;
import biblioteca.entidades.Autor;
import biblioteca.entidades.Cliente;
import biblioteca.entidades.Editorial;
import biblioteca.entidades.Libro;
import biblioteca.entidades.Prestamo;
import biblioteca.servicios.AutorController;
import biblioteca.servicios.ClienteController;
import biblioteca.servicios.EditorialController;
import biblioteca.servicios.LibroController;
import biblioteca.servicios.PrestamoController;
import biblioteca.servicios.exceptions.NonexistentEntityException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author pc
 */
public class DemoPersistence extends Application {

    private AutorController manejoAutor = new AutorController();
    private ClienteController manejoCliente = new ClienteController();
    private EditorialController manejoEditorial = new EditorialController();
    private LibroController manejoLibro = new LibroController();
    private PrestamoController manejoPrestamo = new PrestamoController();
    ComboBox<String> menuComboBox = new ComboBox<>();

//    public static void main(String[] args) {
//        launch(args);
//    }
    @Override
    public void start(Stage primaryStage) {

        VBox principio = new VBox();
        Scene escena = new Scene(principio, 640, 400);
        principio.getChildren().add(menuComboBox);

        menuComboBox.getItems().addAll("Autores", "Editoriales", "Libros", "Prestamos", "Clientes");
        menuComboBox.setOnAction(event -> {
            String opcion = menuComboBox.getSelectionModel().getSelectedItem();
            if (opcion.equals("Autores")) {
                if (principio.getChildren().size() > 1 && principio.getChildren().get(1) instanceof VBox) {
                    principio.getChildren().remove(1);
                }
                principio.getChildren().add(menuAutores());
            } else if (opcion.equals("Editoriales")) {
                if (principio.getChildren().size() > 1 && principio.getChildren().get(1) instanceof VBox) {
                    principio.getChildren().remove(1);
                }
                principio.getChildren().add(menuEditoriales());
            } else if (opcion.equals("Libros")) {
                if (principio.getChildren().size() > 1 && principio.getChildren().get(1) instanceof VBox) {
                    principio.getChildren().remove(1);
                }
                principio.getChildren().add(menuLibros());
            } else if (opcion.equals("Prestamos")) {
                if (principio.getChildren().size() > 1 && principio.getChildren().get(1) instanceof VBox) {
                    principio.getChildren().remove(1);
                }
                principio.getChildren().add(menuPrestamos());
            } else if (opcion.equals("Clientes")) {
                if (principio.getChildren().size() > 1 && principio.getChildren().get(1) instanceof VBox) {
                    principio.getChildren().remove(1);
                }
                principio.getChildren().add(menuClientes());
            }
        });

        primaryStage.setScene(escena);
        primaryStage.show();
    }

    private VBox PresentaMenuPrincipal() {
        Label label1 = new Label("Menú principal:");
        Label label2 = new Label("1) Autores");
        Label label3 = new Label("2) Editoriales");
        Label label4 = new Label("3) Libros");
        Label label5 = new Label("4) Préstamos");
        Label label6 = new Label("5) Clientes");
        Label label7 = new Label("6) Salir");

        // Create the VBox
        VBox cajitaPrincipal = new VBox();
        cajitaPrincipal.getChildren().addAll(label1, label2, label3, label4, label5, label6, label7);
        return cajitaPrincipal;
    }

    private VBox menuAutores() {

        Button crearAutorButton = new Button("Crear autor");
        Button buscarAutorButton = new Button("Buscar autor");
        Button listarAutoresButton = new Button("Listar autores");
        Button actualizarAutorButton = new Button("Actualizar autor");
        Button eliminarAutorButton = new Button("Eliminar autor");
        // Create the VBox
        VBox cajitaAutores = new VBox();
        // Create the buttons
        crearAutorButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Stage paraAutor = new Stage();
                paraAutor.setScene(new Scene(crearAutor(), 400, 200));
                paraAutor.showAndWait();
            }
        });
        buscarAutorButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage paraAutor = new Stage();
                paraAutor.setScene(new Scene(buscarAutor(), 400, 300));
                paraAutor.showAndWait();
            }
        });
        listarAutoresButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage paraAutor = new Stage();
                paraAutor.setScene(new Scene(listarAutores(), 512, 480));
                paraAutor.showAndWait();
            }
        });
        actualizarAutorButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage paraAutor = new Stage();
                paraAutor.setScene(new Scene(actualizarAutor(), 400, 200));
                paraAutor.showAndWait();
            }
        });
        eliminarAutorButton.setOnAction(event -> {
            try {
                Stage paraAutor = new Stage();
                paraAutor.setScene(new Scene(eliminarAutor(), 400, 200));
                paraAutor.showAndWait();

            } catch (NonexistentEntityException ex) {
                Logger.getLogger(DemoPersistence.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        cajitaAutores.getChildren().addAll(crearAutorButton, buscarAutorButton, listarAutoresButton, actualizarAutorButton, eliminarAutorButton);
        return cajitaAutores;
    }

    private HBox crearAutor() {
        TextField nombreTextField = new TextField();
        Button crearAutorButton = new Button("Crear autor");
        Label messageLabel = new Label("Esperando...");

        // Create the Label
        Label nombreLabel = new Label("Nombre: ");

        // Create the TextField
        nombreTextField.setPromptText("Ingrese el nombre del autor");

        // Create the Button
        crearAutorButton.setOnAction((ActionEvent event) -> {
            Autor autor = new Autor();
            autor.setNombre(nombreTextField.getText());
            autor.setAlta(Boolean.TRUE);
            manejoAutor.create(autor);
            messageLabel.setText(autor.getNombre() + " se agregó a la lista");
        });

        // Create the HBox
        HBox cajitaNuevoAutor = new HBox();
        cajitaNuevoAutor.getChildren().addAll(nombreLabel, nombreTextField, crearAutorButton, messageLabel);
        return cajitaNuevoAutor;
    }

    private VBox buscarAutor() {
        TextField idTextField = new TextField();
        Button buscarAutorButton = new Button("Buscar autor");
        Label idLabel = new Label("ID: ");
        Label statusLabel = new Label("Esperando...");

        idTextField.setPromptText("Ingrese el ID del autor");

        // Create the Button
        buscarAutorButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    // Get the ID from the TextField
                    Long id = Long.parseLong(idTextField.getText());

                    // Call the AutorController to find the author by ID
                    Autor autor = manejoAutor.findAutor(id);

                    if (autor != null) {
                        statusLabel.setText("Se encontró :\n"
                                + "ID: " + autor.getId()
                                + "\nNombre: " + autor.getNombre());
                    } else {
                        statusLabel.setText("No encontrado");
                    }
                } catch (Exception e) {
                    statusLabel.setText("Error inesperado");
                }
            }

        });

        // Create the HBox
        HBox cajitaBuscadora1 = new HBox();

        cajitaBuscadora1.getChildren()
                .addAll(idLabel, idTextField, buscarAutorButton);

        // Create the VBox
        VBox cajitaBuscaAutor = new VBox();

        cajitaBuscaAutor.getChildren().addAll(cajitaBuscadora1,statusLabel);
        return cajitaBuscaAutor;
    }

    private VBox listarAutores() {
        TableView<Autor> autoresTableView = new TableView<>();

        // Create the columns
        TableColumn<Autor, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Autor, String> nombreColumn = new TableColumn<>("Nombre");
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Autor, Boolean> altaColumn = new TableColumn<>("Alta");
        altaColumn.setCellValueFactory(new PropertyValueFactory<>("alta"));

        // Add the columns to the TableView
        autoresTableView.getColumns().addAll(idColumn, nombreColumn, altaColumn);

        // Load the data from the database
        List<Autor> autores = manejoAutor.findAutorEntities();
        autoresTableView.getItems().setAll(autores);

        // Create the VBox
        VBox cajitaListaAutores = new VBox();
        cajitaListaAutores.getChildren().addAll(autoresTableView);
        return cajitaListaAutores;
    }

    private VBox actualizarAutor() {
        TextField idTextField;
        TextField nameTextField;
        Button updateButton;
        Label messageLabel;
        VBox cajitaActualizaAutor;

        idTextField = new TextField();
        nameTextField = new TextField();
        updateButton = new Button("Actualizar");
        messageLabel = new Label();
        cajitaActualizaAutor = new VBox();

        updateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    long id = Long.parseLong(idTextField.getText());
                    String name = nameTextField.getText();

                    // Call the Author Controller to find the author by ID
                    Autor autor = manejoAutor.findAutor(id);

                    if (autor != null) {
                        autor.setNombre(name);
                        manejoAutor.edit(autor);
                        messageLabel.setText("Autor Actualizado correctamente.");
                    } else {
                        messageLabel.setText("Autor no encontrado.");
                    }
                } catch (Exception ex) {
                    messageLabel.setText(ex.getMessage());
                }
            }
        });

        cajitaActualizaAutor.getChildren().addAll(new Label("ID autor:"),idTextField,new Label("Nombre Autor:"), nameTextField, updateButton, messageLabel);
        return cajitaActualizaAutor;
    }

    private VBox eliminarAutor() throws NonexistentEntityException {
        TextField idTextField;
        Button deleteButton;
        Label messageLabel;
        VBox cajitaBorraAutor;

        idTextField = new TextField();
        deleteButton = new Button("Eliminar");
        messageLabel = new Label();
        cajitaBorraAutor = new VBox();

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    long id = Long.parseLong(idTextField.getText());

                    // Call the Author Controller to delete the author by ID
                    manejoAutor.destroy(id);
                    messageLabel.setText("Autor correctamente borrado");
                } catch (NonexistentEntityException ex) {
                    messageLabel.setText(ex.getMessage());
                }
            }
        });

        cajitaBorraAutor.getChildren().addAll(idTextField, deleteButton, messageLabel);
        return cajitaBorraAutor;

    }

    private VBox menuEditoriales() {
        Button crearButton;
        Button buscarButton;
        Button listarButton;
        Button actualizarButton;
        Button eliminarButton;
        Button volverButton;

        crearButton = new Button("Crear Editorial");
        buscarButton = new Button("Buscar Editorial");
        listarButton = new Button("Listar Editoriales");
        actualizarButton = new Button("Actualizar Editorial");
        eliminarButton = new Button("Eliminar Editorial");
        volverButton = new Button("Volver");

        VBox cajitaMenuEditoriales = new VBox();
        Pane presentador = new Pane();
        cajitaMenuEditoriales.getChildren().add(presentador);
        crearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (presentador.getChildren().size() > 1 && presentador.getChildren().get(1) instanceof VBox) {
                    presentador.getChildren().remove(1);
                }
                presentador.getChildren().add(crearEditorial());

            }
        });

        buscarButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (presentador.getChildren().size() > 1 && presentador.getChildren().get(1) instanceof VBox) {
                    presentador.getChildren().remove(1);
                }
                presentador.getChildren().add(buscarEditorial());
            }
        });

        listarButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (presentador.getChildren().size() > 1 && presentador.getChildren().get(1) instanceof VBox) {
                    presentador.getChildren().remove(1);
                }
                presentador.getChildren().add(listarEditoriales());
            }
        });

        actualizarButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (presentador.getChildren().size() > 1 && presentador.getChildren().get(1) instanceof VBox) {
                    presentador.getChildren().remove(1);
                }
                presentador.getChildren().add(actualizarEditorial());
            }
        });

        eliminarButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (presentador.getChildren().size() > 1 && presentador.getChildren().get(1) instanceof VBox) {
                    presentador.getChildren().remove(1);
                }
                presentador.getChildren().add(eliminarEditorial());
            }
        });

        volverButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                presentador.getChildren().remove(0);
            }
        });
        cajitaMenuEditoriales.getChildren().addAll(crearButton, buscarButton, listarButton, actualizarButton, eliminarButton, volverButton);
        return cajitaMenuEditoriales;
    }

    private VBox crearEditorial() {
        TextField nombreTextField = new TextField();
        Button crearButton = new Button("Crear");
        Label messageLabel = new Label();
        VBox cajitaCreaEditorial = new VBox();

        crearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    String nombre = nombreTextField.getText();

                    // Crear instancia de Editorial y asignarle los valores ingresados
                    Editorial editorial = new Editorial();
                    editorial.setNombre(nombre);
                    editorial.setAlta(true);

                    // Llamar al controlador de Editoriales para crear la editorial en la base de datos
                    manejoEditorial.create(editorial);
                    messageLabel.setText("Editorial creada correctamente.");
                } catch (Exception ex) {
                    messageLabel.setText(ex.getMessage());
                }
            }
        });
        cajitaCreaEditorial.getChildren().addAll(nombreTextField, crearButton, messageLabel);
        return cajitaCreaEditorial;
    }

    private VBox buscarEditorial() {
        TextField idTextField = new TextField();
        Button buscarButton = new Button("Buscar");
        Label messageLabel = new Label();
        VBox cajitaBuscaEditorial = new VBox();

        buscarButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    long id = Long.parseLong(idTextField.getText());

                    // Llamar al controlador de Editoriales para buscar la editorial por su ID
                    Editorial editorial = manejoEditorial.findEditorial(id);

                    if (editorial != null) {
                        messageLabel.setText("Editorial encontrada:");
                        messageLabel.setText("ID: " + editorial.getId());
                        messageLabel.setText("Nombre: " + editorial.getNombre());
                        messageLabel.setText("Alta: " + editorial.getAlta());
                    } else {
                        messageLabel.setText("Editorial no encontrada.");
                    }
                } catch (Exception ex) {
                    messageLabel.setText(ex.getMessage());
                }
            }
        });
        cajitaBuscaEditorial.getChildren().addAll(idTextField, buscarButton, messageLabel);
        return cajitaBuscaEditorial;

    }

    private VBox listarEditoriales() {
        TableView<Editorial> tableView;
        ObservableList<Editorial> listaEditoriales = FXCollections.observableArrayList();
        tableView = new TableView<>(listaEditoriales);
        VBox cajitaListaEditoriales = new VBox();
        HBox acomodaListaEditoriales = new HBox();

        // Llamar al controlador de Editoriales para obtener todas las editoriales
        List<Editorial> editoriales = manejoEditorial.findEditorialEntities();

        // Agregar las editoriales al TableView
        for (Editorial editorial : editoriales) {
            listaEditoriales.add(editorial);
        }

        // Crear las columnas del TableView
        TableColumn<Editorial, Long> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Editorial, String> nombreColumn = new TableColumn<>("Nombre");
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Editorial, Boolean> altaColumn = new TableColumn<>("Alta");
        altaColumn.setCellValueFactory(new PropertyValueFactory<>("alta"));

        // Agregar las columnas al TableView
        tableView.getColumns().addAll(idColumn, nombreColumn, altaColumn);

        acomodaListaEditoriales.getChildren().add(tableView);
        cajitaListaEditoriales.getChildren().add(tableView);
        return cajitaListaEditoriales;

    }

    private VBox actualizarEditorial() {
        TextField idTextField = new TextField();
        TextField nombreTextField = new TextField();
        Button actualizarButton = new Button("Actualizar");
        Label messageLabel = new Label();
        VBox cajitaActualizaEditorial = new VBox();

        actualizarButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    long id = Long.parseLong(idTextField.getText());
                    String nombre = nombreTextField.getText();

                    // Llamar al controlador de Editoriales para buscar la editorial por su ID
                    Editorial editorial = manejoEditorial.findEditorial(id);

                    if (editorial != null) {
                        editorial.setNombre(nombre);
                        manejoEditorial.edit(editorial);
                        messageLabel.setText("Editorial actualizada correctamente.");
                    } else {
                        messageLabel.setText("Editorial no encontrada.");
                    }
                } catch (Exception ex) {
                    messageLabel.setText(ex.getMessage());
                }
            }
        });

        cajitaActualizaEditorial.getChildren().addAll(idTextField, nombreTextField, actualizarButton, messageLabel);
        return cajitaActualizaEditorial;

    }

    private VBox eliminarEditorial() {
        TextField idTextField = new TextField();
        Button eliminarButton = new Button("Eliminar");
        Label messageLabel = new Label();
        VBox cajitaEliminaEditorial = new VBox();

        eliminarButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    long id = Long.parseLong(idTextField.getText());

                    // Llamar al controlador de Editoriales para eliminar la editorial por su ID
                    manejoEditorial.destroy(id);
                    messageLabel.setText("Editorial eliminada correctamente.");
                } catch (NonexistentEntityException ex) {
                    messageLabel.setText(ex.getMessage());
                }
            }
        });

        cajitaEliminaEditorial.getChildren().addAll(idTextField, eliminarButton, messageLabel);
        return cajitaEliminaEditorial;

    }

    private VBox menuLibros() {
        TextField opcionTextField = new TextField();
        Button crearButton = new Button("Crear");
        Button buscarButton = new Button("Buscar");
        Button listarButton = new Button("Listar");
        Button actualizarButton = new Button("Actualizar");
        Button eliminarButton = new Button("Eliminar");
        Button salirButton = new Button("Salir");
        VBox cajitaMenuLibros = new VBox();

        crearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Code to create a book
            }
        });

        buscarButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Code to search for a book
            }
        });

        listarButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Code to list all books
            }
        });

        actualizarButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Code to update a book
            }
        });

        eliminarButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Code to delete a book
            }
        });

        salirButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Code to exit the application
            }
        });

        cajitaMenuLibros.getChildren().addAll(crearButton, buscarButton, listarButton, actualizarButton, eliminarButton, salirButton);
        return cajitaMenuLibros;

    }

    private VBox crearLibro() throws Exception {
        TextField isbnTextField = new TextField("ISBN");
        TextField tituloTextField = new TextField("Titulo");
        TextField anioTextField = new TextField("Año");
        TextField ejemplaresTextField = new TextField("Cantidad de ejemplares");
        TextField autorIdTextField = new TextField("ID de autor");
        TextField editorialIdTextField = new TextField("ID de editor");
        Button crearButton = new Button("Crear");
        Label messageLabel = new Label();
        VBox cajitaCreaLibro = new VBox();

        crearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    // Obtener los valores ingresados
                    long isbn = Long.parseLong(isbnTextField.getText());
                    String titulo = tituloTextField.getText();
                    int anio = Integer.parseInt(anioTextField.getText());
                    int ejemplares = Integer.parseInt(ejemplaresTextField.getText());
                    long autorId = Long.parseLong(autorIdTextField.getText());
                    long editorialId = Long.parseLong(editorialIdTextField.getText());

                    // Obtener el autor y la editorial correspondientes a los IDs ingresados
                    Autor autor = manejoAutor.findAutor(autorId);
                    Editorial editorial = manejoEditorial.findEditorial(editorialId);

                    if (autor != null && editorial != null) {
                        // Crear instancia de Libro y asignarle los valores ingresados
                        Libro libro = new Libro();
                        libro.setIsbn(isbn);
                        libro.setTitulo(titulo);
                        libro.setAnio(anio);
                        libro.setEjemplares(ejemplares);
                        libro.setEjemplaresRestantes(ejemplares);
                        libro.setEjemplaresPrestados(0);
                        libro.setAutor(autor);
                        libro.setEditorial(editorial);
                        libro.setAlta(Boolean.TRUE);

                        // Llamar al controlador de Libros para crear el libro en la base de datos
                        manejoLibro.create(libro);
                        messageLabel.setText("Libro creado correctamente.");
                    } else {
                        messageLabel.setText("Autor o editorial no encontrados.");
                    }
                } catch (Exception ex) {
                    messageLabel.setText(ex.getMessage());
                }
            }
        });

        cajitaCreaLibro.getChildren().addAll(isbnTextField, tituloTextField, anioTextField, ejemplaresTextField, autorIdTextField, editorialIdTextField, crearButton, messageLabel);
        return cajitaCreaLibro;
    }

    private VBox buscarLibro() {
        TextField isbnTextField = new TextField();
        Button buscarButton = new Button("Buscar");
        Label messageLabel = new Label();
        VBox cajitaBuscaLibro = new VBox();

        buscarButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    // Obtener el valor ingresado
                    long isbn = Long.parseLong(isbnTextField.getText());

                    // Llamar al controlador de Libros para buscar el libro por su ISBN
                    Libro libro = manejoLibro.obtenerLibroPorISBN(isbn);

                    if (libro != null) {
                        String salida = ("Libro encontrado:")
                                + ("\nISBN: " + libro.getIsbn())
                                + ("\nTítulo: " + libro.getTitulo())
                                + ("\nAño de Publicación: " + libro.getAnio())
                                + ("\nEjemplares Prestados: " + libro.getEjemplaresPrestados())
                                + ("\nEjemplares Restantes: " + libro.getEjemplaresRestantes())
                                + ("\nAutor: " + libro.getAutor().getNombre())
                                + ("\nEditorial: " + libro.getEditorial().getNombre());
                        messageLabel.setText(salida);
                    } else {
                        messageLabel.setText("Libro no encontrado.");
                    }
                } catch (InputMismatchException e) {
                    messageLabel.setText("Error al ingresar datos");
                }
            }
        });
        cajitaBuscaLibro.getChildren().addAll(isbnTextField, buscarButton, messageLabel);
        return cajitaBuscaLibro;

    }

    private VBox listarLibros() {

        VBox cajitaListaLibro = new VBox();
        HBox acomolaLista = new HBox();

        // Llamar al controlador de Libros para obtener todos los libros
        List<Libro> libros = manejoLibro.findLibroEntities();

        // Crear una tabla para mostrar los libros
        TableView<Libro> tableView = new TableView<>();
        tableView.setEditable(false);

        // Crear columnas para el TableView
        TableColumn<Libro, Long> isbnColumn = new TableColumn<>("ISBN");
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));

        TableColumn<Libro, String> tituloColumn = new TableColumn<>("Título");
        tituloColumn.setCellValueFactory(new PropertyValueFactory<>("titulo"));

        TableColumn<Libro, Integer> anioColumn = new TableColumn<>("Año de Publicación");
        anioColumn.setCellValueFactory(new PropertyValueFactory<>("anio"));

        TableColumn<Libro, Integer> ejemplaresPrestadosColumn = new TableColumn<>("Ejemplares Prestados");
        ejemplaresPrestadosColumn.setCellValueFactory(new PropertyValueFactory<>("ejemplaresPrestados"));

        TableColumn<Libro, Integer> ejemplaresRestantesColumn = new TableColumn<>("Ejemplares Restantes");
        ejemplaresRestantesColumn.setCellValueFactory(new PropertyValueFactory<>("ejemplaresRestantes"));

        TableColumn<Libro, String> autorColumn = new TableColumn<>("Autor");
        autorColumn.setCellValueFactory(new PropertyValueFactory<>("autor.nombre"));

        TableColumn<Libro, String> editorialColumn = new TableColumn<>("Editorial");
        editorialColumn.setCellValueFactory(new PropertyValueFactory<>("editorial.nombre"));

        // Agregar las columnas a la tabla
        tableView.getColumns().addAll(isbnColumn, tituloColumn, anioColumn, ejemplaresPrestadosColumn, ejemplaresRestantesColumn, autorColumn, editorialColumn);

        // Agregar los libros a la tabla
        ObservableList<Libro> data = FXCollections.observableArrayList(libros);
        tableView.setItems(data);

        acomolaLista.getChildren().add(tableView);
        cajitaListaLibro.getChildren().add(tableView);
        return cajitaListaLibro;
    }

    private VBox actualizarLibro() throws Exception {
        TextField isbnTextField = new TextField();
        TextField tituloTextField = new TextField();
        TextField anioTextField = new TextField();
        TextField ejemplaresTextField = new TextField();
        Button actualizarButton = new Button("Actualizar");
        Label messageLabel = new Label();
        VBox cajitaActualizaLibro = new VBox();

        actualizarButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    // Obtener los valores ingresados
                    long isbn = Long.parseLong(isbnTextField.getText());
                    String titulo = tituloTextField.getText();
                    int anio = Integer.parseInt(anioTextField.getText());
                    int ejemplares = Integer.parseInt(ejemplaresTextField.getText());

                    // Llamar al controlador de Libros para buscar el libro por su ISBN
                    Libro libro = manejoLibro.obtenerLibroPorISBN(isbn);

                    if (libro != null) {
                        // Actualizar los datos del libro
                        libro.setTitulo(titulo);
                        libro.setAnio(anio);
                        libro.setEjemplares(ejemplares);
                        libro.setEjemplaresRestantes(ejemplares);

                        // Llamar al controlador de Libros para actualizar el libro en la base de datos
                        manejoLibro.edit(libro);
                        messageLabel.setText("Libro actualizado correctamente.");
                    } else {
                        messageLabel.setText("Libro no encontrado.");
                    }
                } catch (Exception ex) {
                    messageLabel.setText(ex.getMessage());
                }
            }
        });
        cajitaActualizaLibro.getChildren().addAll(isbnTextField, tituloTextField, anioTextField, ejemplaresTextField, actualizarButton, messageLabel);
        return cajitaActualizaLibro;
    }

    private VBox eliminarLibro() throws NonexistentEntityException {
        TextField isbnTextField;
        Button eliminarButton;
        Label messageLabel;
        VBox cajitaEliminarLibro = new VBox();

        isbnTextField = new TextField();
        eliminarButton = new Button("Eliminar");
        messageLabel = new Label();

        eliminarButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    // Obtener el valor ingresado
                    long isbn = Long.parseLong(isbnTextField.getText());

                    // Llamar al controlador de Libros para eliminar el libro por su ISBN
                    manejoLibro.destroy(isbn);
                    messageLabel.setText("Libro eliminado correctamente.");
                } catch (NonexistentEntityException ex) {
                    messageLabel.setText(ex.getMessage());
                }
            }
        });

        cajitaEliminarLibro.getChildren().addAll(isbnTextField, eliminarButton, messageLabel);

        return cajitaEliminarLibro;
    }

    private VBox menuPrestamos() {
        Button crearPrestamoButton = new Button("Crear Préstamo");
        Button buscarPrestamoButton = new Button("Buscar Préstamo");
        Button listarPrestamosButton = new Button("Listar Préstamos");
        Button actualizarPrestamoButton = new Button("Actualizar Préstamo");
        Button eliminarPrestamoButton = new Button("Eliminar Préstamo");
        Button salirButton = new Button("Volver");
        Label messageLabel = new Label();
        VBox cajitaMenuPrestamos = new VBox();

        crearPrestamoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    cajitaMenuPrestamos.getChildren().add(crearPrestamo());
                } catch (Exception ex) {
                    messageLabel.setText(ex.getMessage());
                }
            }
        });

        buscarPrestamoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    cajitaMenuPrestamos.getChildren().add(buscarPrestamo());
                } catch (Exception ex) {
                    messageLabel.setText(ex.getMessage());
                }
            }
        });

        listarPrestamosButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    cajitaMenuPrestamos.getChildren().add(listarPrestamos());
                } catch (Exception ex) {
                    messageLabel.setText(ex.getMessage());
                }
            }
        });

        actualizarPrestamoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    cajitaMenuPrestamos.getChildren().add(actualizarPrestamo());
                } catch (Exception ex) {
                    messageLabel.setText(ex.getMessage());
                }
            }
        });

        eliminarPrestamoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    cajitaMenuPrestamos.getChildren().add(eliminarPrestamo());
                } catch (Exception ex) {
                    messageLabel.setText(ex.getMessage());
                }
            }
        });

        salirButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(cajitaMenuPrestamos.getParent());

            }
        });
        cajitaMenuPrestamos.getChildren().addAll(crearPrestamoButton, buscarPrestamoButton, listarPrestamosButton, actualizarPrestamoButton, eliminarPrestamoButton, salirButton);
        return cajitaMenuPrestamos;
    }

    private VBox crearPrestamo() throws Exception {
        TextField libroIsbnTextField = new TextField();
        TextField clienteIdTextField = new TextField();
        Button crearPrestamoButton = new Button("Crear préstamo");
        Label messageLabel = new Label();
        VBox cajitaCreaPrestamo = new VBox();

        crearPrestamoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    // Obtener el libro y el cliente correspondientes a los IDs ingresados
                    Libro libro = manejoLibro.findLibro(Long.parseLong(libroIsbnTextField.getText()));
                    Cliente cliente = manejoCliente.findCliente(Long.parseLong(clienteIdTextField.getText()));

                    // Verificar si hay ejemplares disponibles para prestar
                    if (libro.getEjemplaresRestantes() > 0) {
                        // Crear instancia de Prestamo y asignarle los valores ingresados
                        Prestamo prestamo = new Prestamo();
                        prestamo.setLibro(libro);
                        prestamo.setCliente(cliente);

                        // Llamar al controlador de Prestamos para crear el prestamo en la base de datos
                        manejoPrestamo.create(prestamo);
                        messageLabel.setText("Préstamo creado correctamente.");

                        // Actualizar la cantidad de ejemplares prestados y restantes del libro
                        libro.setEjemplaresPrestados(libro.getEjemplaresPrestados() + 1);
                        libro.setEjemplaresRestantes(libro.getEjemplaresRestantes() - 1);
                        manejoLibro.edit(libro);
                    } else {
                        messageLabel.setText("No hay ejemplares disponibles para prestar.");
                    }
                } catch (Exception ex) {
                    messageLabel.setText(ex.getMessage());
                }
            }
        });

        cajitaCreaPrestamo.getChildren().addAll(libroIsbnTextField, clienteIdTextField, crearPrestamoButton, messageLabel);
        return cajitaCreaPrestamo;
    }

    private VBox buscarPrestamo() {
        TextField prestamoIdTextField = new TextField();
        Button buscarPrestamoButton = new Button("Buscar préstamo");
        Label messageLabel = new Label();
        VBox cajitaBuscaPrestamo = new VBox();

        buscarPrestamoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    // Obtener el ID del préstamo ingresado
                    Long id = Long.parseLong(prestamoIdTextField.getText());

                    // Llamar al controlador de Prestamos para buscar el préstamo por su ID
                    Prestamo prestamo = manejoPrestamo.findPrestamo(id);

                    if (prestamo != null) {
                        messageLabel.setText("Préstamo encontrado:");
                        messageLabel.setText("ID: " + prestamo.getId());
                        messageLabel.setText("Fecha de Préstamo: " + prestamo.getFechaPrestamo());
                        messageLabel.setText("Fecha de Devolución: " + prestamo.getFechaDevolucion());
                        messageLabel.setText("Libro: " + prestamo.getLibro().getTitulo());
                        messageLabel.setText("Cliente: " + prestamo.getCliente().getNombre() + " " + prestamo.getCliente().getApellido());
                    } else {
                        messageLabel.setText("Préstamo no encontrado.");
                    }
                } catch (Exception ex) {
                    messageLabel.setText(ex.getMessage());
                }
            }
        });

        cajitaBuscaPrestamo.getChildren().addAll(prestamoIdTextField, buscarPrestamoButton, messageLabel);
        return cajitaBuscaPrestamo;
    }

    private VBox listarPrestamos() {
        TableView prestamosTableView = new TableView<>();
        Button listarPrestamosButton = new Button("Listar préstamos");
        Label messageLabel = new Label();
        VBox cajitaListaPrestamos = new VBox();
        HBox acomodaLista = new HBox();

        listarPrestamosButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    // Llamar al controlador de Prestamos para obtener todos los préstamos
                    List<Prestamo> prestamos = manejoPrestamo.findPrestamoEntities();

                    // Limpiar la tabla de préstamos
                    prestamosTableView.getItems().clear();

                    // Agregar las columnas a la tabla de préstamos
                    TableColumn<Prestamo, Long> idColumn = new TableColumn<>("ID");
                    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

                    TableColumn<Prestamo, String> fechaPrestamoColumn = new TableColumn<>("Fecha de préstamo");
                    fechaPrestamoColumn.setCellValueFactory(new PropertyValueFactory<>("fechaPrestamo"));

                    TableColumn<Prestamo, String> fechaDevolucionColumn = new TableColumn<>("Fecha de devolución");
                    fechaDevolucionColumn.setCellValueFactory(new PropertyValueFactory<>("fechaDevolucion"));

                    TableColumn<Prestamo, String> libroColumn = new TableColumn<>("Libro");
                    libroColumn.setCellValueFactory(new PropertyValueFactory<>("libro.titulo"));

                    TableColumn<Prestamo, String> clienteColumn = new TableColumn<>("Cliente");
                    clienteColumn.setCellValueFactory(new PropertyValueFactory<>("cliente.nombre + ' ' + cliente.apellido"));

                    // Agregar las columnas a la tabla de préstamos
                    prestamosTableView.getColumns().addAll(idColumn, fechaPrestamoColumn, fechaDevolucionColumn, libroColumn, clienteColumn);

                    // Agregar los préstamos a la tabla de préstamos
                    for (Prestamo prestamo : prestamos) {
                        prestamosTableView.getItems().add(prestamo);
                    }
                } catch (Exception ex) {
                    messageLabel.setText(ex.getMessage());
                }
            }
        });

        acomodaLista.getChildren().addAll(prestamosTableView, listarPrestamosButton, messageLabel);
        cajitaListaPrestamos.getChildren().add(acomodaLista);
        return cajitaListaPrestamos;
    }

    private VBox actualizarPrestamo() throws Exception {
        TableView<Prestamo> prestamosTableView = new TableView<>();
        Button actualizarPrestamoButton = new Button("Actualizar préstamo");
        Label messageLabel = new Label();
        Label prestamoLabel = new Label();
        TextField idTextField = new TextField();
        TextField nuevoValorTextField = new TextField();
        VBox cajitaActualizaPrestamo = new VBox();
        HBox acomodaLista = new HBox();

        actualizarPrestamoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    // Obtener el ID del préstamo del usuario
                    Long id = Long.valueOf(idTextField.getText());

                    // Llamar al controlador de Prestamos para buscar el préstamo por su ID
                    Prestamo prestamo = manejoPrestamo.findPrestamo(id);

                    if (prestamo != null) {
                        // Obtener la nueva fecha de devolución del usuario
                        String nuevoValorStr = nuevoValorTextField.getText();

                        // Actualizar la fecha de devolución del préstamo
                        prestamo.setFechaDevolucion(new SimpleDateFormat("dd/MM/yyyy").parse(nuevoValorStr));

                        // Llamar al controlador de Prestamos para actualizar el préstamo en la base de datos
                        manejoPrestamo.edit(prestamo);
                        messageLabel.setText("Préstamo actualizado correctamente.");
                        prestamoLabel.setText("El préstamo con ID " + id + " ha sido actualizado a " + nuevoValorStr);
                    } else {
                        messageLabel.setText("Préstamo no encontrado.");
                    }
                } catch (Exception ex) {
                    messageLabel.setText("Error al actualizar el préstamo.");
                }
            }
        });

        // Llena la tabla con los préstamos
        prestamosTableView.setItems((ObservableList<Prestamo>) manejoPrestamo.findPrestamoEntities());
        acomodaLista.getChildren().add(prestamosTableView);
        cajitaActualizaPrestamo.getChildren().addAll(actualizarPrestamoButton, prestamoLabel, idTextField, nuevoValorTextField, messageLabel, acomodaLista);
        return cajitaActualizaPrestamo;

    }

    private VBox eliminarPrestamo() throws NonexistentEntityException {
        TextField idTextField = new TextField();
        Button eliminarPrestamoButton = new Button("Eliminar préstamo");
        Label messageLabel = new Label();
        VBox cajitaBorraPrestamo = new VBox();

        eliminarPrestamoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    // Obtener el ID del préstamo del usuario
                    Long id = Long.valueOf(idTextField.getText());

                    // Llamar al controlador de Prestamos para eliminar el préstamo por su ID
                    manejoPrestamo.destroy(id);
                    messageLabel.setText("Préstamo eliminado correctamente.");
                } catch (NonexistentEntityException ex) {
                    messageLabel.setText("Préstamo no encontrado.");
                }
            }
        });

        cajitaBorraPrestamo.getChildren().addAll(idTextField, eliminarPrestamoButton, messageLabel);
        return cajitaBorraPrestamo;
    }

    private VBox menuClientes() {
        Button crearClienteButton = new Button("Crear Cliente");
        Button buscarClienteButton = new Button("Buscar Cliente");
        Button listarClientesButton = new Button("Listar Clientes");
        Button actualizarClienteButton = new Button("Actualizar Cliente");
        Button eliminarClienteButton = new Button("Eliminar Cliente");
        Button volverButton = new Button("Volver");

        VBox cajitaMenuCliente = new VBox(10);
        cajitaMenuCliente.getChildren().addAll(
                crearClienteButton, buscarClienteButton, listarClientesButton,
                actualizarClienteButton, eliminarClienteButton, volverButton
        );
        return cajitaMenuCliente;

    }

    private VBox crearCliente() {
        TextField documentoTextField = new TextField();
        TextField nombreTextField = new TextField();
        TextField apellidoTextField = new TextField();
        TextField telefonoTextField = new TextField();
        Button crearClienteButton = new Button("Crear cliente");
        Label messageLabel = new Label();

        VBox cajitaCreaCliente = new VBox();

        crearClienteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    // Obtener los datos del usuario
                    Long documento = Long.valueOf(documentoTextField.getText());
                    String nombre = nombreTextField.getText();
                    String apellido = apellidoTextField.getText();
                    String telefono = telefonoTextField.getText();

                    // Crear instancia de Cliente y asignarle los valores ingresados
                    Cliente cliente = new Cliente();
                    cliente.setDocumento(documento);
                    cliente.setNombre(nombre);
                    cliente.setApellido(apellido);
                    cliente.setTelefono(telefono);

                    // Llamar al controlador de Clientes para crear el cliente en la base de datos
                    manejoCliente.create(cliente);
                    messageLabel.setText("Cliente creado correctamente.");
                } catch (Exception ex) {
                    messageLabel.setText("Error al crear el cliente.");
                }
            }
        });
        cajitaCreaCliente.getChildren().addAll(documentoTextField, nombreTextField, apellidoTextField, telefonoTextField, crearClienteButton, messageLabel);
        return cajitaCreaCliente;
    }

    private VBox buscarCliente() {
        TextField idTextField = new TextField();
        Button buscarClienteButton = new Button("Buscar cliente");
        Label messageLabel = new Label();
        VBox cajitaBuscaCliente = new VBox();

        buscarClienteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    // Obtener el ID del cliente del usuario
                    Long id = Long.valueOf(idTextField.getText());

                    // Llamar al controlador de Clientes para buscar el cliente por su ID
                    Cliente cliente = manejoCliente.findCliente(id);

                    if (cliente != null) {
                        messageLabel.setText("Cliente encontrado:");
                        messageLabel.setText("ID: " + cliente.getId());
                        messageLabel.setText("Documento: " + cliente.getDocumento());
                        messageLabel.setText("Nombre: " + cliente.getNombre());
                        messageLabel.setText("Apellido: " + cliente.getApellido());
                        messageLabel.setText("Teléfono: " + cliente.getTelefono());
                    } else {
                        messageLabel.setText("Cliente no encontrado.");
                    }
                } catch (Exception ex) {
                    messageLabel.setText("Error al buscar el cliente.");
                }

            }
        });

        cajitaBuscaCliente.getChildren().addAll(idTextField, buscarClienteButton, messageLabel);
        return cajitaBuscaCliente;
    }

    private VBox listarClientes() {
        TableView<Cliente> clientesTableView = new TableView<>();
        Button buscarClienteButton = new Button("Buscar cliente");
        Label messageLabel = new Label();
        VBox cajitaListaClientes = new VBox();
        HBox acomodaLista = new HBox();

        // Crear columnas para la tabla
        TableColumn<Cliente, Long> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Cliente, String> documentoColumn = new TableColumn<>("Documento");
        documentoColumn.setCellValueFactory(new PropertyValueFactory<>("documento"));
        TableColumn<Cliente, String> nombreColumn = new TableColumn<>("Nombre");
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableColumn<Cliente, String> apellidoColumn = new TableColumn<>("Apellido");
        apellidoColumn.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        TableColumn<Cliente, String> telefonoColumn = new TableColumn<>("Teléfono");
        telefonoColumn.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        // Agregar columnas a la tabla
        clientesTableView.getColumns().addAll(idColumn, documentoColumn, nombreColumn, apellidoColumn, telefonoColumn);

        // Llamar al controlador de Clientes para obtener todos los clientes
        List<Cliente> clientes = manejoCliente.findClienteEntities();

        // Agregar clientes a la tabla
        clientesTableView.setItems(FXCollections.observableArrayList(clientes));

        // Agregar botón "Buscar cliente" a la ventana
        buscarClienteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Obtener el ID del cliente del usuario
                Long id = clientesTableView.selectionModelProperty().get().getSelectedItem().getId();

                // Llamar al controlador de Clientes para buscar el cliente por su ID
                Cliente cliente = manejoCliente.findCliente(id);

                if (cliente != null) {
                    String salida = ("Cliente encontrado:")
                            + ("\nID: " + cliente.getId())
                            + ("\nDocumento: " + cliente.getDocumento())
                            + ("\nNombre: " + cliente.getNombre())
                            + ("\nApellido: " + cliente.getApellido())
                            + ("\nTeléfono: " + cliente.getTelefono());
                    messageLabel.setText(salida);
                } else {
                    messageLabel.setText("Cliente no encontrado.");
                }
            }
        });
        acomodaLista.getChildren().add(clientesTableView);
        cajitaListaClientes.getChildren().addAll(buscarClienteButton, acomodaLista, messageLabel);
        return cajitaListaClientes;
    }

    private VBox actualizarCliente() throws Exception {
        TextField idTextField = new TextField();
        TextField documentoTextField = new TextField();
        TextField nombreTextField = new TextField();
        TextField apellidoTextField = new TextField();
        TextField telefonoTextField = new TextField();
        Button actualizarClienteButton = new Button("Actualizar cliente");
        Label messageLabel = new Label();

        actualizarClienteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    // Obtener el ID del cliente del usuario
                    Long id = Long.valueOf(idTextField.getText());

                    // Llamar al controlador de Clientes para buscar el cliente por su ID
                    Cliente cliente = manejoCliente.findCliente(id);

                    if (cliente != null) {
                        // Obtener los nuevos datos del cliente del usuario
                        Long documento = Long.valueOf(documentoTextField.getText());
                        String nombre = nombreTextField.getText();
                        String apellido = apellidoTextField.getText();
                        String telefono = telefonoTextField.getText();

                        // Actualizar los datos del cliente
                        cliente.setDocumento(documento);
                        cliente.setNombre(nombre);
                        cliente.setApellido(apellido);
                        cliente.setTelefono(telefono);

                        // Llamar al controlador de Clientes para actualizar el cliente en la base de datos
                        manejoCliente.edit(cliente);
                        messageLabel.setText("Cliente actualizado correctamente.");
                    } else {
                        messageLabel.setText("Cliente no encontrado.");
                    }
                } catch (Exception ex) {
                    messageLabel.setText("Error al actualizar el cliente.");
                }
            }
        });

        VBox cajitaActualizaCliente = new VBox(10);
        cajitaActualizaCliente.getChildren().addAll(
                new Label("Ingrese el ID del cliente:"),
                idTextField,
                new Label("Ingrese el nuevo documento del cliente:"),
                documentoTextField,
                new Label("Ingrese el nuevo nombre del cliente:"),
                nombreTextField,
                new Label("Ingrese el nuevo apellido del cliente:"),
                apellidoTextField,
                new Label("Ingrese el nuevo teléfono del cliente:"),
                telefonoTextField,
                actualizarClienteButton,
                messageLabel
        );
        return cajitaActualizaCliente;
    }

    private VBox eliminarCliente() throws NonexistentEntityException {
        TextField idTextField = new TextField();
        Button eliminarClienteButton = new Button("Eliminar cliente");
        Label messageLabel = new Label();

        eliminarClienteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    // Obtener el ID del cliente del usuario
                    Long id = Long.valueOf(idTextField.getText());

                    // Llamar al controlador de Clientes para eliminar el cliente por su ID
                    manejoCliente.destroy(id);
                    messageLabel.setText("Cliente eliminado correctamente.");
                } catch (InputMismatchException | NonexistentEntityException ex) {
                    messageLabel.setText("Error al eliminar el cliente.");
                }
            }
        });

        VBox cajitaEliminaCliente = new VBox(10);
        cajitaEliminaCliente.getChildren().addAll(
                new Label("Ingrese el ID del cliente:"),
                idTextField,
                eliminarClienteButton,
                messageLabel
        );
        return cajitaEliminaCliente;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
