/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grupo_07;

import Clases.Combustible;
import Clases.Vehiculos;
import Clases.tipoVehiculo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.application.Application;
import static javafx.application.Application.launch;
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

        // Lista para mostrar los vehículos
        ListView<String> listView = new ListView<>();
        cargarVehiculos(listView);
        
        Button btnAgregarVehiculo = new Button("Agregar Vehículo");
        btnAgregarVehiculo.setEffect(dropShadow);
        btnAgregarVehiculo.setFont(font2);
        btnAgregarVehiculo.setOnAction(e -> agregarVehiculo());

        Button btnBorrarVehiculo = new Button("Borrar Vehículo");
        btnBorrarVehiculo.setEffect(dropShadow);
        btnBorrarVehiculo.setFont(font2);
        btnBorrarVehiculo.setOnAction(e -> borrarVehiculo(listView));

        HBox hBoxBotones = new HBox(btnAgregarVehiculo, btnBorrarVehiculo);
        hBoxBotones.setAlignment(Pos.CENTER);
        hBoxBotones.setSpacing(10);

        // Contenedor principal
        VBox vboxMain = new VBox();
        vboxMain.setSpacing(10);
        vboxMain.setPadding(new Insets(20));

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
        Scene scene = new Scene(vboxMain, 900, 600);
        primaryStage.setTitle("Vendedor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    

    public void cargarVehiculos(ListView<String> listView) {
        try (BufferedReader bf = new BufferedReader(new FileReader(rutaArchivo))) {
            String line;
            while ((line = bf.readLine()) != null) {
                String[] tokens = line.split(",");
                String vehiculoInfo = "Marca: " + tokens[0] + ", Modelo: " + tokens[1] + ", Tipo: " + tokens[3] + ", Kilometraje: " + tokens[5];
                listView.getItems().add(vehiculoInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.vehiculos=Vehiculos.leerVehiculosArchivo(rutaArchivo);
    }
    
    public void agregarVehiculo() {
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void borrarVehiculo(ListView<String> listView) {
        // Lógica para borrar un vehículo seleccionado
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Eliminar Vehículo");
        dialog.setHeaderText(null);
        dialog.setContentText("Ingrese los datos del vehículo (Marca, Modelo, Precio, Tipo, Año, Kilometraje, Combustible, Tracción, Transmisión) separados por comas:");
        dialog.showAndWait().ifPresent(vehiculo -> {
            
        String[] tokens=vehiculo.split(",");
        Vehiculos v =new Vehiculos(tokens[0],tokens[1],Integer.parseInt(tokens[2]),tokens[3],Integer.parseInt(tokens[4]),Integer.parseInt(tokens[5]),Combustible.valueOf(tokens[6]),tokens[7],tokens[8]);
        Vehiculos.borrarVehiculoArchivo(v, rutaArchivo);
             
        });
        
        cargarVehiculos(listView);
    }

    public static void main(String[] args) {
        launch(args);
    }
}