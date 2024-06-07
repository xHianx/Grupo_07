/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grupo_07;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Cristhian
 */
class Vregistro {

    void start(Stage primaryStage) {
       // Crear los elementos de la interfaz
        TextField UsuarioF = new TextField();
        TextField ContraseñaF = new TextField();
        TextField NombreF = new TextField();
        TextField ApellidoF = new TextField();
        TextField EdadF = new TextField();

        Button backButton = new Button("REGRESAR");
        Button createUserButton = new Button("CREAR USUARIO");
        
        ComboBox<String> rolBox = new ComboBox<>();
        rolBox.getItems().addAll("Vendedor", "Comprador");

        // Establecer el estilo del texto
        Color textColor = Color.WHITE;
       
        // Aplicar efecto de contorno a las letras
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLACK);
        UsuarioF.setEffect(dropShadow);
        ContraseñaF.setEffect(dropShadow);
        NombreF.setEffect(dropShadow);
        ApellidoF.setEffect(dropShadow);
        EdadF.setEffect(dropShadow);
        
        // Cargar la imagen de fondo
        Image backgroundImage = new Image("file:C:\\Users\\Cristhian\\Downloads\\c3cb1dd6-dccf-48a8-a50f-7a109a9ad482.png");
        BackgroundImage background = new BackgroundImage(backgroundImage, null, null, null, new BackgroundSize(100, 100, true, true, true, false));

        // Crear el diseño y establecer la imagen de fondo
        StackPane root = new StackPane();
        root.setBackground(new Background(background));

        // Organizar los elementos en una cuadrícula (GridPane)
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10); // Espacio horizontal entre elementos
        gridPane.setVgap(10); // Espacio vertical entre elementos

        // Agregar los elementos al diseño
        Font font = Font.font("Arial Black", FontWeight.BOLD, 18);
        
        Label rolLabel = new Label("ROL:");
        rolLabel.setFont(font);
        rolLabel.setEffect(dropShadow);
        rolLabel.setTextFill(textColor);
        gridPane.add(rolLabel, 0, 0);
        gridPane.add(rolBox, 1, 0);
        
        Label usernameLabel = new Label("USUARIO:");
        usernameLabel.setFont(font);
        usernameLabel.setEffect(dropShadow);
        usernameLabel.setTextFill(textColor);
        gridPane.add(usernameLabel, 0, 1);
        gridPane.add(UsuarioF, 1, 1);

        Label passwordLabel = new Label("CONTRASEÑA:");
        passwordLabel.setFont(font);
        passwordLabel.setEffect(dropShadow);
        passwordLabel.setTextFill(textColor);
        gridPane.add(passwordLabel, 0, 2);
        gridPane.add(ContraseñaF, 1, 2);

        Label nameLabel = new Label("NOMBRE:");
        nameLabel.setFont(font);
        nameLabel.setEffect(dropShadow);
        nameLabel.setTextFill(textColor);
        gridPane.add(nameLabel, 0, 3);
        gridPane.add(NombreF, 1, 3);
        
        Label ApellidoL = new Label("APELLIDO:");
        ApellidoL.setFont(font);
        ApellidoL.setEffect(dropShadow);
        ApellidoL.setTextFill(textColor);
        gridPane.add(ApellidoL, 0, 4);
        gridPane.add(ApellidoF, 1, 4);
        
        Label ageLabel = new Label("EDAD:");
        ageLabel.setFont(font);
        ageLabel.setEffect(dropShadow);
        ageLabel.setTextFill(textColor);
        gridPane.add(ageLabel, 0, 5);
        gridPane.add(EdadF, 1, 5);

        gridPane.add(backButton, 0, 6);
        gridPane.add(createUserButton, 1, 6);

        // Centrar el GridPane en la ventana
        gridPane.setAlignment(Pos.CENTER);

        root.getChildren().add(gridPane);

        // Crear la escena
        Scene scene = new Scene(root, 800, 500);

        // Configurar la ventana
        primaryStage.setTitle("Ventana de Registro");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        backButton.setOnAction(event -> {
        // Cerrar la ventana actual
        primaryStage.close();

        // Crear una nueva ventana (puedes crear una nueva clase que extienda de Application)
        
        App nuevaVentana = new App();
        nuevaVentana.start(new Stage());
        });
    }
    
}
