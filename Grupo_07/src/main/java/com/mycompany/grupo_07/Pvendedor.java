/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grupo_07;


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
import javafx.stage.Stage;

public class Pvendedor extends Application {
    private String usuario;
    private String rutaArchivo;
    private DoubleLinkedList vehiculos;

    public void start(Stage primaryStage) throws Exception{
        throw new UnsupportedOperationException("No se usa");
    }
                    
    public void start(Stage primaryStage, String usuario) {
        // Imagen y texto estilizado
        this.usuario=usuario;
        this.rutaArchivo="C:\\Users\\rlaur\\OneDrive\\Documents\\NetBeansProjects\\Grupo_07\\Grupo_07\\Vendedor"+this.usuario+".txt";
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
        
        VBox vboxMain = new VBox();
        vboxMain.setSpacing(10);
        vboxMain.setPadding(new Insets(20));

        // Lista para mostrar los vehículos
        ListView<String> listView = new ListView<>();
        cargarVehiculos(listView);
        
        Button btnModificarVehiculo=new Button("Modificar Vehículo");
        btnModificarVehiculo.setEffect(dropShadow);
        btnModificarVehiculo.setFont(font2);
        btnModificarVehiculo.setOnAction(e -> modificarVehiculoSeleccionado(listView));
        
        Button btnAgregarVehiculo = new Button("Agregar Vehículo");
        btnAgregarVehiculo.setEffect(dropShadow);
        btnAgregarVehiculo.setFont(font2);
        btnAgregarVehiculo.setOnAction(e -> agregarVehiculo(listView));

        Button btnBorrarVehiculo = new Button("Borrar Vehículo");
        btnBorrarVehiculo.setEffect(dropShadow);
        btnBorrarVehiculo.setFont(font2);
        btnBorrarVehiculo.setOnAction(e -> borrarVehiculoSeleccionado(listView));

        HBox hBoxBotones = new HBox(btnAgregarVehiculo, btnBorrarVehiculo,btnModificarVehiculo);
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
        vboxMain.getChildren().addAll(hBoxTop, listView,hBoxBotones);
        
        // Configuración de la escena y el escenario
        Scene scene = new Scene(vboxMain, 800, 600);
        primaryStage.setTitle("Vendedor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    

    public void cargarVehiculos(ListView<String> listView) {
        try (BufferedReader bf = new BufferedReader(new FileReader(rutaArchivo))) {
            String line;
            boolean archivoVacio=true;
            while ((line = bf.readLine()) != null) {
                archivoVacio=false;
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
    
    public void agregarVehiculo(ListView<String> listview) {
        // Lógica para agregar un nuevo vehículo
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Agregar Vehículo");
        dialog.setHeaderText(null);
        dialog.setContentText("Ingrese los datos del vehículo (Marca, Modelo, Precio, Tipo, Año, Kilometraje, Combustible, Tracción, Transmisión) separados por comas:");
        dialog.showAndWait().ifPresent(vehiculo -> {
            try (PrintWriter writer = new PrintWriter(new FileWriter(rutaArchivo, true))) {
                writer.println(vehiculo);
                // Mostrar una alerta de vehículo agregado
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Vehículo Agregado");
                alert.setHeaderText(null);
                alert.setContentText("El vehículo ha sido agregado exitosamente.");
                alert.showAndWait();
                listview.getItems().clear();
                cargarVehiculos(listview);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    public void borrarVehiculoSeleccionado(ListView<String> listView) {
        // Obtener el modelo de selección del ListView
        ObservableList<String> selectedItems = listView.getSelectionModel().getSelectedItems();

        // Verificar si hay elementos seleccionados
        if (!selectedItems.isEmpty()) {
            // Obtener el primer elemento seleccionado (en caso de selección múltiple)
            String selectedItem = selectedItems.get(0);
            // Remover el elemento seleccionado del ListView
            listView.getItems().remove(selectedItem);
            // Obtener la ruta del archivo
            
            // Eliminar el elemento del archivo
            borrarElementoDelArchivo(rutaArchivo, selectedItem);
            // Mostrar una alerta de éxito
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Elemento Borrado");
            alert.setHeaderText(null);
            alert.setContentText("El elemento seleccionado ha sido borrado.");
            alert.showAndWait();
            listView.getItems().clear();
            cargarVehiculos(listView);
        } else {
            // Si no hay elementos seleccionados, mostrar una alerta de advertencia
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ningún Elemento Seleccionado");
            alert.setHeaderText(null);
            alert.setContentText("No se ha seleccionado ningún elemento.");
            alert.showAndWait();
        }
    }

    private void borrarElementoDelArchivo(String rutaArchivo, String elemento) {
        try {
            // Leer todas las líneas del archivo
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
            // Sobreescribir el archivo con las líneas actualizadas
            FileWriter fw = new FileWriter(archivo);
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void modificarVehiculoSeleccionado(ListView<String> listView) {
    // Obtener el modelo de selección del ListView
    ObservableList<String> selectedItems = listView.getSelectionModel().getSelectedItems();

    // Verificar si hay elementos seleccionados
    if (!selectedItems.isEmpty()) {
        // Obtener el primer elemento seleccionado (en caso de selección múltiple)
        String selectedItem = selectedItems.get(0);

        // Mostrar un diálogo de entrada de texto para que el usuario ingrese la modificación
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Modificar Vehículo");
        dialog.setHeaderText(null);
        dialog.setContentText("Ingrese los datos del vehículo modificado (Marca, Modelo, Precio, Tipo, Año, Kilometraje, Combustible, Tracción, Transmisión) separados por comas:");
        dialog.showAndWait().ifPresent(modificacion -> {
            try {
                // Obtener la ruta del archivo

                // Leer todas las líneas del archivo y guardarlas en un StringBuffer
                StringBuffer sb = new StringBuffer();
                Files.lines(Paths.get(rutaArchivo)).forEach(line -> {
                    String vehiculoInfo = "Marca: " + line.split(",")[0] + ", Modelo: " + line.split(",")[1] + ", Tipo: " + line.split(",")[3] + ", Kilometraje: " + line.split(",")[5];
                    if (vehiculoInfo.equals(selectedItem)) {
                        // Si la línea corresponde al vehículo seleccionado, agregar la modificación
                        sb.append(modificacion);
                        sb.append("\n");
                    } else {
                        // Si no corresponde, agregar la línea sin cambios
                        sb.append(line);
                        sb.append("\n");
                    }
                });

                // Sobreescribir el archivo con las líneas actualizadas
                FileWriter fw = new FileWriter(rutaArchivo);
                fw.write(sb.toString());
                fw.close();

                // Mostrar una alerta de éxito
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Vehículo Modificado");
                alert.setHeaderText(null);
                alert.setContentText("El vehículo seleccionado ha sido modificado exitosamente.");
                alert.showAndWait();

                // Limpiar y cargar los vehículos en el ListView
                listView.getItems().clear();
                cargarVehiculos(listView);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    } else {
        // Si no hay elementos seleccionados, mostrar una alerta de advertencia
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Ningún Elemento Seleccionado");
        alert.setHeaderText(null);
        alert.setContentText("No se ha seleccionado ningún elemento.");
        alert.showAndWait();
    }
}


    public static void main(String[] args) {
        launch(args);
    }
}