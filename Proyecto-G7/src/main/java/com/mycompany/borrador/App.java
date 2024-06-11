package com.mycompany.borrador;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Crear los elementos de la interfaz
        TextField UsuarioF = new TextField();
        PasswordField ContraseñaF = new PasswordField();
        TextField showPasswordField = new TextField();
        showPasswordField.setManaged(false);
        showPasswordField.setVisible(false);

        // Sincronizar el contenido de PasswordField y TextField
        ContraseñaF.textProperty().bindBidirectional(showPasswordField.textProperty());

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
        showPasswordField.setEffect(dropShadow);

        // Cargar la imagen de fondo
        Image backgroundImage = new Image("file:src\\main\\java\\Imagenes\\fondo.png");
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

        HBox passwordBox = new HBox();
        passwordBox.getChildren().addAll(ContraseñaF, showPasswordField);
        gridPane.add(passwordBox, 1, 2);

        var eyeButton = new Button("️");
        eyeButton.setStyle("-fx-font-size: 12px;");
        passwordBox.getChildren().add(eyeButton);

        eyeButton.setOnAction(e -> {
            if (showPasswordField.isVisible()) {
                showPasswordField.setVisible(false);
                showPasswordField.setManaged(false);
                ContraseñaF.setVisible(true);
                ContraseñaF.setManaged(true);
            } else {
                showPasswordField.setVisible(true);
                showPasswordField.setManaged(true);
                ContraseñaF.setVisible(false);
                ContraseñaF.setManaged(false);
            }
        });

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

        createUserButton.setOnAction(event -> {
            String rol = rolBox.getValue();
            String usuario = UsuarioF.getText();
            String contraseña = ContraseñaF.getText();

            if (rol != null && !usuario.isEmpty() && !contraseña.isEmpty()) {
                String fileName = rol.equals("Comprador") ? "compradores.txt" : "vendedores.txt";

                if (credencialesValidas(fileName, usuario, contraseña)) {
                    // Mostrar una alerta de éxito
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Inicio de Sesión Exitoso");
                    alert.setHeaderText(null);
                    alert.setContentText("Bienvenido " + usuario + "!");
                    alert.showAndWait();

                    // Cerrar la ventana actual
                    primaryStage.close();

                    // Abrir la ventana correspondiente según el rol
                    if (rol.equals("Vendedor")) {
                        Pvendedor nuevaVentana = new Pvendedor();
                        nuevaVentana.start(new Stage(),usuario);
                    } else {
                        Vcomprador nuevaVentana = new Vcomprador();
                        nuevaVentana.start(new Stage(),usuario);
                    }
                } else {
                    // Mostrar una alerta de error
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error de Inicio de Sesión");
                    alert.setHeaderText(null);
                    alert.setContentText("Usuario o contraseña incorrectos.");
                    alert.showAndWait();
                }
            } else {
                // Mostrar una alerta si faltan datos
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Advertencia");
                alert.setHeaderText(null);
                alert.setContentText("Por favor, complete todos los campos.");
                alert.showAndWait();
            }
        });
    }

    private boolean credencialesValidas(String fileName, String usuario, String contraseña) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Usuario: " + usuario)) {
                    // Leer las siguientes líneas para obtener la contraseña
                    while ((line = reader.readLine()) != null && !line.startsWith("Usuario: ")) {
                        if (line.startsWith("Contraseña: " + contraseña)) {
                            return true;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}