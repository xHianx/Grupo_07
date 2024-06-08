package com.mycompany.grupo_07;

import javafx.application.Application;
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

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Crear los elementos de la interfaz
        TextField UsuarioF = new TextField();
        TextField ContraseñaF = new TextField();

        Button backButton = new Button("REGISTRARSE");
        Button createUserButton = new Button("INICIAR SESION");
        
        Label rolLabel = new Label("ROL:");
        Label usernameLabel = new Label("USUARIO:");
        Label passwordLabel = new Label("CONTRASEÑA:");
        
        ComboBox<String> rolBox = new ComboBox<>();
        rolBox.getItems().addAll("Vendedor", "Comprador");
       
        // Establecer el estilo del texto
        Color textColor = Color.WHITE;
       
        // Aplicar efecto de contorno a las letras
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLACK);
        UsuarioF.setEffect(dropShadow);
        ContraseñaF.setEffect(dropShadow);
       
        
        // Cargar la imagen de fondo
        Image backgroundImage = new Image("file:C:\\Users\\Cristhian\\Downloads\\Borrador\\src\\main\\java\\Imagenes\\c3cb1dd6-dccf-48a8-a50f-7a109a9ad482.png");
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
        
        rolLabel.setFont(font);
        rolLabel.setEffect(dropShadow);
        rolLabel.setTextFill(textColor);
        gridPane.add(rolLabel, 0, 0);
        gridPane.add(rolBox, 1, 0);
        
        usernameLabel.setFont(font);
        usernameLabel.setEffect(dropShadow);
        usernameLabel.setTextFill(textColor);
        gridPane.add(usernameLabel, 0, 1);
        gridPane.add(UsuarioF, 1, 1);

        passwordLabel.setFont(font);
        passwordLabel.setEffect(dropShadow);
        passwordLabel.setTextFill(textColor);
        gridPane.add(passwordLabel, 0, 2);
        gridPane.add(ContraseñaF, 1, 2);

        
        gridPane.add(backButton, 0, 3);
        gridPane.add(createUserButton, 1, 3);

        // Centrar el GridPane en la ventana
        gridPane.setAlignment(Pos.CENTER);

        root.getChildren().add(gridPane);

        // Crear la escena
        Scene scene = new Scene(root, 800, 500);

        // Configurar la ventana
        primaryStage.setTitle("Ventana de Inicio");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        backButton.setOnAction(event -> {
            // Cerrar la ventana actual
            primaryStage.close();

            // Crear una nueva ventana (puedes crear una nueva clase que extienda de Application)

            Vregistro nuevaVentana = new Vregistro();
            nuevaVentana.start(new Stage());
        });
        
        createUserButton.setOnAction(event ->{
            primaryStage.close();
            
            Vvehiculos nuevaVentana = new Vvehiculos();
            nuevaVentana.start(new Stage());
        });
    }
}
