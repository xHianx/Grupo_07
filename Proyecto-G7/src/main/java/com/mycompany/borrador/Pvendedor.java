/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.borrador;

import Clases.Combustible;
import Clases.Vehiculos;
import Clases.tipoVehiculo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Pvendedor extends Application {
    private String usuario;
    private String rutaArchivo = "C:\\ruta\\compartida\\vehiculos.txt"; // Ruta compartida
    private DoubleLinkedList vehiculos;

    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("No se usa");
    }

    public void start(Stage primaryStage, String usuario) {
        // Imagen y texto estilizado
        this.usuario = usuario;
        this.rutaArchivo = "C:\\Users\\Cristhian\\Downloads\\Borrador\\Vendedor" + this.usuario + ".txt";
        ImageView imageView = new ImageView(new Image("file:C:\\Users\\Cristhian\\Downloads\\Borrador\\src\\main\\java\\Imagenes\\logo.png")); // Reemplaza "your_image_path.png" con la ruta de tu imagen
        imageView.setFitHeight(90); // Tamaño de la imagen más grande
        imageView.setFitWidth(90); // Ajusta el ancho si es necesario
        imageView.setPreserveRatio(true);
        Label lblAutoSelecto = new Label("AUTOSELECTO");
        lblAutoSelecto.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 24px; -fx-font-weight: bold;");

        HBox hBoxImagenTexto = new HBox(imageView, lblAutoSelecto);
        hBoxImagenTexto.setAlignment(Pos.CENTER_LEFT); // Alineación a la izquierda
        hBoxImagenTexto.setSpacing(10);

        // Módulo de búsqueda
        Font font = Font.font("Arial Black", FontWeight.BOLD, 20);
        Font font2 = Font.font("Arial", FontWeight.BOLD, 14);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLACK);

        lblAutoSelecto.setFont(font);
        lblAutoSelecto.setEffect(dropShadow);

        Label lblBuscar = new Label("BUSCADOR");
        lblBuscar.setFont(font2);

        TextField tfBuscar = new TextField();
        tfBuscar.setEffect(dropShadow);
        tfBuscar.setPromptText("Escribe lo que quieres buscar");
        Button btnBuscar = new Button("Buscar");
        btnBuscar.setEffect(dropShadow);
        btnBuscar.setFont(font2);

        HBox hBoxBuscar = new HBox(lblBuscar, tfBuscar, btnBuscar);
        hBoxBuscar.setAlignment(Pos.CENTER); // Alineación centrada
        hBoxBuscar.setSpacing(10);

        Button btnCerrarSesion = new Button("Cerrar sesión");
        btnCerrarSesion.setEffect(dropShadow);
        btnCerrarSesion.setFont(font2);
        btnCerrarSesion.setOnAction(e -> {
            Stage appStage = new Stage();
            new App().start(appStage); // Asumiendo que tienes una clase llamada App con un método start
            primaryStage.close();
        });

        // Lista para mostrar los vehículos
        ListView<String> listView = new ListView<>();
        cargarVehiculos(listView);

        // Contenedor principal
        VBox vboxMain = new VBox();
        vboxMain.setSpacing(10);
        vboxMain.setPadding(new Insets(20));

        Button btnModificarVehiculo = new Button("Modificar Vehículo");
        btnModificarVehiculo.setEffect(dropShadow);
        btnModificarVehiculo.setFont(font2);
        btnModificarVehiculo.setOnAction(e -> modificarVehiculoSeleccionado(listView));

        Button btnAgregarVehiculo = new Button("Agregar Vehículo");
        btnAgregarVehiculo.setEffect(dropShadow);
        btnAgregarVehiculo.setFont(font2);
        btnAgregarVehiculo.setOnAction(e -> mostrarVentanaAgregarVehiculo(listView));

        Button btnBorrarVehiculo = new Button("Borrar Vehículo");
        btnBorrarVehiculo.setEffect(dropShadow);
        btnBorrarVehiculo.setFont(font2);
        btnBorrarVehiculo.setOnAction(e -> borrarVehiculoSeleccionado(listView));

        Button btnActualizar = new Button("Actualizar");
        btnActualizar.setEffect(dropShadow);
        btnActualizar.setFont(font2);
        btnActualizar.setOnAction(e -> {
            listView.getItems().clear();
            cargarVehiculos(listView);
        });

        HBox hBoxBotones = new HBox(btnAgregarVehiculo, btnBorrarVehiculo, btnModificarVehiculo, btnActualizar);
        hBoxBotones.setAlignment(Pos.CENTER);
        hBoxBotones.setSpacing(10);

        // HBox para la parte superior con la imagen, el texto y la búsqueda
        HBox hBoxTop = new HBox(hBoxImagenTexto, hBoxBuscar, btnCerrarSesion);
        hBoxTop.setAlignment(Pos.CENTER_LEFT);
        hBoxTop.setSpacing(20);
        hBoxTop.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
        hBoxTop.setPadding(new Insets(10));
        hBoxTop.setMinHeight(100); // Establecer la altura mínima para la franja

        // Alineación del botón de cerrar sesión a la derecha
        HBox.setHgrow(hBoxImagenTexto, Priority.ALWAYS);
        HBox.setHgrow(hBoxBuscar, Priority.ALWAYS);
        hBoxImagenTexto.setAlignment(Pos.CENTER_LEFT);
        hBoxBuscar.setAlignment(Pos.CENTER);
        hBoxTop.setAlignment(Pos.CENTER);

        // Añadir el HBox superior al VBox principal y la lista de vehículos
        vboxMain.getChildren().addAll(hBoxTop, listView, hBoxBotones);

        // Configuración de la escena y el escenario
        Scene scene = new Scene(vboxMain, 900, 600);
        primaryStage.setTitle("Vendedor");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Acción del botón buscar
        btnBuscar.setOnAction(event -> filtrarVehiculosPorMarca(tfBuscar.getText(), listView));
    }

    public void cargarVehiculos(ListView<String> listView) {
        try (BufferedReader bf = new BufferedReader(new FileReader(rutaArchivo))) {
            String line;
            boolean archivoVacio = true;
            while ((line = bf.readLine()) != null) {
                archivoVacio = false;
                String[] tokens = line.split(",");
                String vehiculoInfo = "Marca: " + tokens[0] + ", Modelo: " + tokens[1] + ", Tipo: " + tokens[3] + ", Kilometraje: " + tokens[5];
                listView.getItems().add(vehiculoInfo);
            }
            if (archivoVacio) {
                // Mostrar un mensaje de advertencia si el archivo está vacío
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Archivo Vacío");
                alert.setHeaderText(null);
                alert.setContentText("El archivo de vehículos está vacío.");
                alert.showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarVentanaAgregarVehiculo(ListView<String> listView) {
    Stage agregarStage = new Stage();
    agregarStage.setTitle("Agregar Vehículo");

    VBox vbox = new VBox(10);
    vbox.setPadding(new Insets(20));

    Label titleLabel = new Label("Ingreso de Datos");
    titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
    titleLabel.setAlignment(Pos.CENTER);

    Label marcaLabel = new Label("Marca:");
    TextField marcaField = new TextField();

    Label modeloLabel = new Label("Modelo:");
    TextField modeloField = new TextField();

    Label precioLabel = new Label("Precio:");
    TextField precioField = new TextField();

    Label tipoLabel = new Label("Tipo:");
    ComboBox<tipoVehiculo> tipoBox = new ComboBox<>();
    tipoBox.getItems().addAll(tipoVehiculo.values());

    Label anioLabel = new Label("Año:");
    TextField anioField = new TextField();

    Label kilometrajeLabel = new Label("Kilometraje:");
    TextField kilometrajeField = new TextField();

    Label combustibleLabel = new Label("Combustible:");
    ComboBox<Combustible> combustibleBox = new ComboBox<>();
    combustibleBox.getItems().addAll(Combustible.values());

    Label traccionLabel = new Label("Tracción:");
    TextField traccionField = new TextField();

    Label transmisionLabel = new Label("Transmisión:");
    TextField transmisionField = new TextField();

    Label imagenesLabel = new Label("Imágenes:");
    Button btnAgregarImagen = new Button("Agregar Imagen");
    ListView<String> listaImagenes = new ListView<>();
    btnAgregarImagen.setOnAction(e -> {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(agregarStage);
        if (file != null) {
            listaImagenes.getItems().add(file.toURI().toString());
        }
    });

    Button btnGuardar = new Button("Guardar");
    btnGuardar.setOnAction(e -> {
        String marca = marcaField.getText();
        String modelo = modeloField.getText();
        String precio = precioField.getText();
        String tipo = tipoBox.getValue().toString();
        String anio = anioField.getText();
        String kilometraje = kilometrajeField.getText();
        String combustible = combustibleBox.getValue().toString();
        String traccion = traccionField.getText();
        String transmision = transmisionField.getText();
        String imagenes = String.join(";", listaImagenes.getItems());

        String vehiculoData = String.join(",", marca, modelo, precio, tipo, anio, kilometraje, combustible, traccion, transmision, imagenes);

        // Guardar en el archivo compartido
        try (PrintWriter writer = new PrintWriter(new FileWriter("vehiculosCompartidos.txt", true))) {
            writer.println(vehiculoData);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Guardar en el archivo personal del vendedor
        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaArchivo, true))) {
            writer.println(vehiculoData);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Vehículo Agregado");
            alert.setHeaderText(null);
            alert.setContentText("El vehículo ha sido agregado exitosamente.");
            alert.showAndWait();
            listView.getItems().clear();
            cargarVehiculos(listView);
            agregarStage.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    });

    vbox.getChildren().addAll(
        titleLabel,
        new HBox(10, marcaLabel, marcaField),
        new HBox(10, modeloLabel, modeloField),
        new HBox(10, precioLabel, precioField),
        new HBox(10, tipoLabel, tipoBox),
        new HBox(10, anioLabel, anioField),
        new HBox(10, kilometrajeLabel, kilometrajeField),
        new HBox(10, combustibleLabel, combustibleBox),
        new HBox(10, traccionLabel, traccionField),
        new HBox(10, transmisionLabel, transmisionField),
        new HBox(10, imagenesLabel, btnAgregarImagen),
        listaImagenes,
        btnGuardar
    );

    Scene scene = new Scene(vbox, 400, 600);
    agregarStage.setScene(scene);
    agregarStage.show();
}

    public void borrarVehiculoSeleccionado(ListView<String> listView) {
    ObservableList<String> selectedItems = listView.getSelectionModel().getSelectedItems();

    if (!selectedItems.isEmpty()) {
        String selectedItem = selectedItems.get(0);
        listView.getItems().remove(selectedItem);

        borrarElementoDelArchivo(rutaArchivo, selectedItem);
        borrarElementoDelArchivo("vehiculosCompartidos.txt", selectedItem);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Elemento Borrado");
        alert.setHeaderText(null);
        alert.setContentText("El elemento seleccionado ha sido borrado.");
        alert.showAndWait();
        listView.getItems().clear();
        cargarVehiculos(listView);
    } else {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ningún Elemento Seleccionado");
        alert.setHeaderText(null);
        alert.setContentText("No se ha seleccionado ningún elemento.");
        alert.showAndWait();
    }
}

private void borrarElementoDelArchivo(String rutaArchivo, String elemento) {
    try {
        File archivo = new File(rutaArchivo);
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            String vehiculoInfo = "Marca: " + line.split(",")[0] + ", Modelo: " + line.split(",")[1] + ", Tipo: " + line.split(",")[3] + ", Kilometraje: " + line.split(",")[5];
            if (!vehiculoInfo.equals(elemento)) {
                sb.append(line);
                sb.append("\n");
            }
        }
        br.close();
        FileWriter fw = new FileWriter(archivo);
        fw.write(sb.toString());
        fw.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    public void modificarVehiculoSeleccionado(ListView<String> listView) {
    ObservableList<String> selectedItems = listView.getSelectionModel().getSelectedItems();

    if (!selectedItems.isEmpty()) {
        String selectedItem = selectedItems.get(0);
        String[] tokens = selectedItem.split(", ");
        String marca = tokens[0].split(": ")[1];
        String modelo = tokens[1].split(": ")[1];
        String tipo = tokens[2].split(": ")[1];
        String kilometraje = tokens[3].split(": ")[1];

        final String[] lineaSeleccionada = {""};
        try (BufferedReader bf = new BufferedReader(new FileReader(rutaArchivo))) {
            String line;
            while ((line = bf.readLine()) != null) {
                if (line.contains(marca) && line.contains(modelo) && line.contains(tipo) && line.contains(kilometraje)) {
                    lineaSeleccionada[0] = line;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] detalles = lineaSeleccionada[0].split(",");

        Stage modificarStage = new Stage();
        modificarStage.setTitle("Modificar Vehículo");

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        Label titleLabel = new Label("Modificar Vehículo");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setAlignment(Pos.CENTER);

        Label marcaLabel = new Label("Marca:");
        TextField marcaField = new TextField(detalles[0]);

        Label modeloLabel = new Label("Modelo:");
        TextField modeloField = new TextField(detalles[1]);

        Label precioLabel = new Label("Precio:");
        TextField precioField = new TextField(detalles[2]);

        Label tipoLabel = new Label("Tipo:");
        ComboBox<tipoVehiculo> tipoBox = new ComboBox<>();
        tipoBox.getItems().addAll(tipoVehiculo.values());
        tipoBox.setValue(tipoVehiculo.valueOf(detalles[3]));

        Label anioLabel = new Label("Año:");
        TextField anioField = new TextField(detalles[4]);

        Label kilometrajeLabel = new Label("Kilometraje:");
        TextField kilometrajeField = new TextField(detalles[5]);

        Label combustibleLabel = new Label("Combustible:");
        ComboBox<Combustible> combustibleBox = new ComboBox<>();
        combustibleBox.getItems().addAll(Combustible.values());
        combustibleBox.setValue(Combustible.valueOf(detalles[6]));

        Label traccionLabel = new Label("Tracción:");
        TextField traccionField = new TextField(detalles[7]);

        Label transmisionLabel = new Label("Transmisión:");
        TextField transmisionField = new TextField(detalles[8]);

        Label imagenesLabel = new Label("Imágenes:");
        Button btnAgregarImagen = new Button("Agregar Imagen");
        ListView<String> listaImagenes = new ListView<>();
        String[] imagenes = detalles[9].split(";");
        listaImagenes.getItems().addAll(imagenes);
        btnAgregarImagen.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(modificarStage);
            if (file != null) {
                listaImagenes.getItems().add(file.toURI().toString());
            }
        });

        Button btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(e -> {
            String nuevaMarca = marcaField.getText();
            String nuevoModelo = modeloField.getText();
            String nuevoPrecio = precioField.getText();
            String nuevoTipo = tipoBox.getValue().toString();
            String nuevoAnio = anioField.getText();
            String nuevoKilometraje = kilometrajeField.getText();
            String nuevoCombustible = combustibleBox.getValue().toString();
            String nuevaTraccion = traccionField.getText();
            String nuevaTransmision = transmisionField.getText();
            String nuevasImagenes = String.join(";", listaImagenes.getItems());

            String vehiculoData = String.join(",", nuevaMarca, nuevoModelo, nuevoPrecio, nuevoTipo, nuevoAnio, nuevoKilometraje, nuevoCombustible, nuevaTraccion, nuevaTransmision, nuevasImagenes);

            try {
                // Leer todas las líneas del archivo compartido y del archivo del vendedor y guardarlas en un StringBuffer
                StringBuffer sbCompartido = new StringBuffer();
                Files.lines(Paths.get("vehiculosCompartidos.txt")).forEach(line -> {
                    if (line.equals(lineaSeleccionada[0])) {
                        sbCompartido.append(vehiculoData);
                        sbCompartido.append("\n");
                    } else {
                        sbCompartido.append(line);
                        sbCompartido.append("\n");
                    }
                });

                StringBuffer sbVendedor = new StringBuffer();
                Files.lines(Paths.get(rutaArchivo)).forEach(line -> {
                    if (line.equals(lineaSeleccionada[0])) {
                        sbVendedor.append(vehiculoData);
                        sbVendedor.append("\n");
                    } else {
                        sbVendedor.append(line);
                        sbVendedor.append("\n");
                    }
                });

                // Sobreescribir el archivo compartido y el archivo del vendedor con las líneas actualizadas
                FileWriter fwCompartido = new FileWriter("vehiculosCompartidos.txt");
                fwCompartido.write(sbCompartido.toString());
                fwCompartido.close();

                FileWriter fwVendedor = new FileWriter(rutaArchivo);
                fwVendedor.write(sbVendedor.toString());
                fwVendedor.close();

                // Mostrar una alerta de éxito
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Vehículo Modificado");
                alert.setHeaderText(null);
                alert.setContentText("El vehículo seleccionado ha sido modificado exitosamente.");
                alert.showAndWait();

                // Limpiar y cargar los vehículos en el ListView
                listView.getItems().clear();
                cargarVehiculos(listView);
                modificarStage.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        vbox.getChildren().addAll(
            titleLabel,
            new HBox(10, marcaLabel, marcaField),
            new HBox(10, modeloLabel, modeloField),
            new HBox(10, precioLabel, precioField),
            new HBox(10, tipoLabel, tipoBox),
            new HBox(10, anioLabel, anioField),
            new HBox(10, kilometrajeLabel, kilometrajeField),
            new HBox(10, combustibleLabel, combustibleBox),
            new HBox(10, traccionLabel, traccionField),
            new HBox(10, transmisionLabel, transmisionField),
            new HBox(10, imagenesLabel, btnAgregarImagen),
            listaImagenes,
            btnGuardar
        );

        Scene scene = new Scene(vbox, 400, 600);
        modificarStage.setScene(scene);
        modificarStage.show();
    } else {
        // Si no hay elementos seleccionados, mostrar una alerta de advertencia
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ningún Elemento Seleccionado");
        alert.setHeaderText(null);
        alert.setContentText("No se ha seleccionado ningún elemento.");
        alert.showAndWait();
    }
}

    public void filtrarVehiculosPorMarca(String marca, ListView<String> listView) {
        if (marca == null || marca.isEmpty()) {
            cargarVehiculos(listView);
            return;
        }

        try (BufferedReader bf = new BufferedReader(new FileReader(rutaArchivo))) {
            String line;
            listView.getItems().clear();
            boolean encontrado = false;
            while ((line = bf.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens[0].equalsIgnoreCase(marca)) {
                    String vehiculoInfo = "Marca: " + tokens[0] + ", Modelo: " + tokens[1] + ", Tipo: " + tokens[3] + ", Kilometraje: " + tokens[5];
                    listView.getItems().add(vehiculoInfo);
                    encontrado = true;
                }
            }
            if (!encontrado) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Resultado de Búsqueda");
                alert.setHeaderText(null);
                alert.setContentText("No se encontraron vehículos de la marca especificada.");
                alert.showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}