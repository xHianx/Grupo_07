/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.borrador;

import Clases.Vehiculos;
import TDAs.CircularDoublyLinkedList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Vimagenes extends Application {

    private CircularDoublyLinkedList<String> imagenes;
    private int currentImageIndex = 0;
    private ImageView imageView;
    private Vehiculos vehiculo;

    public Vimagenes(CircularDoublyLinkedList<String> imagenes, Vehiculos vehiculo) {
        this.imagenes = imagenes;
        this.vehiculo = vehiculo;
    }

    @Override
    public void start(Stage primaryStage) {
        // Crear el título
        Label titleLabel = new Label("Información del Vehículo");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setTextAlignment(TextAlignment.CENTER);

        imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(400); // Tamaño fijo para la imagen
        imageView.setFitHeight(300); // Tamaño fijo para la imagen

        updateImage();

        Button prevButton = new Button("Anterior");
        prevButton.setOnAction(event -> showPreviousImage());

        Button nextButton = new Button("Siguiente");
        nextButton.setOnAction(event -> showNextImage());

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(prevButton, nextButton);

        VBox root = new VBox(20);
        root.setPadding(new Insets(10));

        // Añadir información del vehículo
        Label marcaLabel = new Label("Marca: " + vehiculo.getMarca());
        Label modeloLabel = new Label("Modelo: " + vehiculo.getModelo());
        Label precioLabel = new Label("Precio: " + vehiculo.getPrecio());
        Label kmLabel = new Label("Kilometraje: " + vehiculo.getKilometraje());
        Label tipoLabel = new Label("Tipo: " + vehiculo.getTipo());
        Label combustibleLabel = new Label("Combustible: " + vehiculo.getCombus().name());
        Label traccionLabel = new Label("Tracción: " + vehiculo.getTraccion());
        Label transmisionLabel = new Label("Transmisión: " + vehiculo.getTransmision());

        // Mensajes de depuración
        System.out.println("Marca: " + vehiculo.getMarca());
        System.out.println("Modelo: " + vehiculo.getModelo());
        System.out.println("Precio: " + vehiculo.getPrecio());
        System.out.println("Kilometraje: " + vehiculo.getKilometraje());
        System.out.println("Tipo: " + vehiculo.getTipo());
        System.out.println("Combustible: " + vehiculo.getCombus().name());
        System.out.println("Tracción: " + vehiculo.getTraccion());
        System.out.println("Transmisión: " + vehiculo.getTransmision());
        System.out.println("Imagenes: " + imagenes.size());

        // Estilizar etiquetas
        Font infoFont = Font.font("Arial", FontWeight.NORMAL, 16);
        marcaLabel.setFont(infoFont);
        modeloLabel.setFont(infoFont);
        precioLabel.setFont(infoFont);
        kmLabel.setFont(infoFont);
        tipoLabel.setFont(infoFont);
        combustibleLabel.setFont(infoFont);
        traccionLabel.setFont(infoFont);
        transmisionLabel.setFont(infoFont);

        VBox infoBox = new VBox(5);
        infoBox.getChildren().addAll(marcaLabel, modeloLabel, precioLabel, kmLabel, tipoLabel, combustibleLabel, traccionLabel, transmisionLabel);
        infoBox.setAlignment(Pos.CENTER_LEFT);

        root.getChildren().addAll(titleLabel, infoBox, imageView, buttonBox);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 500, 600); // Ajusta el tamaño de la ventana
        primaryStage.setTitle("Detalles del Vehículo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateImage() {
        if (!imagenes.isEmpty()) {
            String imageUrl = imagenes.get(currentImageIndex);
            Image image = new Image(imageUrl);
            imageView.setImage(image);
        }
    }

    private void showNextImage() {
        if (!imagenes.isEmpty()) {
            currentImageIndex = (currentImageIndex + 1) % imagenes.size();
            updateImage();
        }
    }

    private void showPreviousImage() {
        if (!imagenes.isEmpty()) {
            currentImageIndex = (currentImageIndex - 1 + imagenes.size()) % imagenes.size();
            updateImage();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}