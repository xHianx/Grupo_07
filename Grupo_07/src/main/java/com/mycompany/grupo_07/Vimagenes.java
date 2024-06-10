/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grupo_07;

import java.util.Iterator;
import java.util.ListIterator;
import static java.util.Spliterators.iterator;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author edu-g
 */

public class Vimagenes {

    private CircularDoublyLinkedList<String> imagenes;
    private int currentImageIndex = 0;
    private ImageView imageView;

    public Vimagenes(CircularDoublyLinkedList<String> imagenes) {
        this.imagenes = imagenes;
    }

    public void start(Stage primaryStage) {
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
        root.getChildren().addAll(imageView, buttonBox);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 800, 600); // Ajusta el tamaño de la ventana según tu preferencia
        primaryStage.setTitle("Carrusel de Imágenes");
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
}
