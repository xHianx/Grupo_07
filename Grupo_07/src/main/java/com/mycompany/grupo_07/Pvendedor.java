/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grupo_07;


import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    @Override
    public void start(Stage primaryStage) {
        // Imagen y texto estilizado
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
        //lblBuscar.setEffect(dropShadow);
        
        imageView.setEffect(dropShadow);
        
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

        // Añadir el HBox superior al VBox principal
        vboxMain.getChildren().addAll(hBoxTop);

        // Configuración de la escena y el escenario
        Scene scene = new Scene(vboxMain, 800, 600);
        primaryStage.setTitle("Vendedor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}