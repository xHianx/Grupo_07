/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grupo_07;

import java.util.Collection;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author edu-g
 */
public class Vvehiculos {
    
    private DoubleLinkedList<Vehiculos> vehiculos;
    private ObservableList<Vehiculos> observableList;
    private ListView<Vehiculos> listView;
    private int currentPage = 0;
    private final int itemsPerpage = 4;
    GridPane gridPane;
    private CircularDoublyLinkedList<String> imagenes;
    private enum Orden { ASCENDENTE, DESCENDENTE };
    private Orden sortOrderPrice = Orden.ASCENDENTE;
    private Orden sortOrderMileage = Orden.ASCENDENTE;
    
    public void start(Stage primaryStage){
        
        imagenes = new CircularDoublyLinkedList<>();
        vehiculos = new DoubleLinkedList<>();
        observableList = FXCollections.observableArrayList();
        listView = new ListView<>(observableList);
        
        Button prevButton = new Button("Anterior");
        Button nextButton = new Button("Siguiente");
        Button sortPrecioButton = new Button("Ordenar por Precio");
        Button sortKmButton = new Button("Ordenar por Kilometraje");
        Button backButton = new Button("Regresar");
        
        prevButton.setOnAction(e -> showPreviousPage());
        nextButton.setOnAction(e -> showNextPage());
        sortPrecioButton.setOnAction(e -> sortVehiculosPrecio());
        sortKmButton.setOnAction(e -> sortVehiculosKm());
        backButton.setOnAction(e -> {
            primaryStage.close();
            App app = new App();
            Stage appStage = new Stage();
            try {
                app.start(appStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        
        imagenes.addLast("file:C:\\Users\\edu-g\\Documents\\GitHub\\Grupo_07\\Grupo_07\\src\\main\\java\\Imagenes\\4.png");
        imagenes.addLast("file:C:\\Users\\edu-g\\Documents\\GitHub\\Grupo_07\\Grupo_07\\src\\main\\java\\Imagenes\\kia-stonic-my24-rims-front-intro.jpg");
        
        
        Vehiculos vehiculo1 = new Vehiculos("Kia", "modelo1", 20000, tipoVehiculo.CROSSOVER,
                                            2019, 30000, Combustible.GASOLINA, "2x2", "Automatico", imagenes);
        Vehiculos vehiculo2 = new Vehiculos("Chevrolet", "modelo2", 18000, tipoVehiculo.SEDAN,
                                            2020, 25000, Combustible.DIESEL, "2x2", "Manual", imagenes);
        Vehiculos vehiculo3 = new Vehiculos("Renault", "modelo2", 10000, tipoVehiculo.SEDAN,
                                            2020, 40000, Combustible.DIESEL, "2x2", "Manual", imagenes);
        Vehiculos vehiculo4 = new Vehiculos("Mazda", "modelo2", 15000, tipoVehiculo.SEDAN,
                                            2020, 31000, Combustible.DIESEL, "2x2", "Manual", imagenes);
        Vehiculos vehiculo5 = new Vehiculos("Ferrari", "modelo2", 10000, tipoVehiculo.SEDAN,
                                            2020, 10000, Combustible.DIESEL, "2x2", "Manual", imagenes);
        Vehiculos vehiculo6 = new Vehiculos("Forza", "modelo2", 21000, tipoVehiculo.SEDAN,
                                            2020, 25000, Combustible.DIESEL, "2x2", "Manual", imagenes);
        
        vehiculos.addLast(vehiculo1);
        vehiculos.addLast(vehiculo2);
        vehiculos.addLast(vehiculo3);
        vehiculos.addLast(vehiculo4);
        vehiculos.addLast(vehiculo5);
        vehiculos.addLast(vehiculo6);
        
        gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setStyle("-fx-alignment: center;");
        
        HBox buttonBox = new HBox(10, prevButton, nextButton);
        buttonBox.setStyle("-fx-alignment: center;");
        
        HBox sortButtonBox = new HBox(10, sortPrecioButton, sortKmButton);
        sortButtonBox.setStyle("-fx-alignment: center;");

        HBox topButtonBox = new HBox(10, backButton);
        topButtonBox.setStyle("-fx-alignment: top-left;");
        
        VBox topBox = new VBox(10, sortButtonBox, gridPane);
        topBox.setStyle("-fx-alignment: center;");
        
        BorderPane root = new BorderPane();
        root.setTop(new VBox(topButtonBox, sortButtonBox));
        root.setCenter(topBox);
        root.setBottom(buttonBox);
        
        Scene scene = new Scene(root, 800, 500);
        
        primaryStage.setTitle("Venta de Carros Usados");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        updateGridPane();
        
    }
    
    private void updateGridPane(){
        gridPane.getChildren().clear();
        DoubleLinkedList<Vehiculos> page = vehiculos.getPage(currentPage, itemsPerpage);
        
        int row = 0;
        int col = 0;
        
        for(Vehiculos vehiculo: page){
            VBox vbox = new VBox(10);
            vbox.setStyle("-fx-alignment: top-left;");
            vbox.setPrefWidth(350);
            
            Label marcaLabel = new Label("Marca: " + vehiculo.getMarca());
            Label modeloLabel = new Label("Modelo: " + vehiculo.getModelo());
            Label precioLabel = new Label("Precio: " + vehiculo.getPrecio());
            Label kmLabel = new Label("Kilometraje: " + vehiculo.getKilometraje());

            marcaLabel.setStyle("-fx-alignment: top-left;");
            modeloLabel.setStyle("-fx-alignment: top-left;");
            precioLabel.setStyle("-fx-alignment: top-left;");
            kmLabel.setStyle("-fx-alignment: top-left;");
            
            vbox.getChildren().addAll(marcaLabel, modeloLabel, precioLabel, kmLabel);
            
            ImageView imageView = new ImageView();
            if(vehiculo.getImagenes().size() > 0){
                Image image = new Image(vehiculo.obtenerImagenes(0));
                imageView.setImage(image);
                imageView.setFitWidth(200); // Aumentar el tamaño de la imagen
                imageView.setFitHeight(200);
                imageView.setPreserveRatio(true);
                
                imageView.setOnMouseClicked(event -> {
                    // Obtener el Stage actual
                    Stage currentStage = (Stage) imageView.getScene().getWindow();
                    currentStage.close(); // Cerrar la ventana actual

                    // Abrir la ventana de imágenes
                    Vimagenes vimagenes = new Vimagenes(vehiculo.getImagenes());
                    Stage imageStage = new Stage();
                    try {
                        vimagenes.start(imageStage);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
            }
            
            HBox hbox = new HBox(10);
            hbox.getChildren().addAll(vbox, imageView);
            hbox.setPadding(new Insets(20)); // Añadir padding para separar las imágenes de los datos

            gridPane.add(hbox, col, row);

            col++;
            if (col > 1) {
                col = 0;
                row++;
            }
        }   
    }
        
    private void showNextPage(){
        if((currentPage + 1) * itemsPerpage < vehiculos.size()){
            currentPage++;
            updateGridPane();
        }
    }
    
    private void showPreviousPage(){
        if(currentPage > 0){
            currentPage--;
            updateGridPane();
        }
    }
    
    private void sortVehiculosPrecio() {
        if (sortOrderPrice == Orden.ASCENDENTE) {
            vehiculos.sort(Vehiculos.VehiculosPorPrecio().reversed());
            sortOrderPrice = Orden.DESCENDENTE;
        } else {
            vehiculos.sort(Vehiculos.VehiculosPorPrecio());
            sortOrderPrice = Orden.ASCENDENTE;
        }
        updateGridPane();
    }

    private void sortVehiculosKm() {
        if (sortOrderMileage == Orden.ASCENDENTE) {
            vehiculos.sort(Vehiculos.VehiculosPorKilometraje().reversed());
            sortOrderMileage = Orden.DESCENDENTE;
        } else {
            vehiculos.sort(Vehiculos.VehiculosPorKilometraje());
            sortOrderMileage = Orden.ASCENDENTE;
        }
        updateGridPane();
    }
}