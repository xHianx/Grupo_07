/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grupo_07;

import TDAs.DoubleNodeList;
import TDAs.*;
import Clases.Combustible;
import Clases.tipoVehiculo;
import Clases.Vehiculos;
import java.util.Collection;
import java.util.Comparator;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class Vcomprador extends Application{
    
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
    private TextField minPriceField;
    private TextField maxPriceField;
    private TextField minKmField;
    private TextField maxKmField;
    private ComboBox tipoCB;
    private DoubleLinkedList<Vehiculos> filteredVehiculos;
    private boolean isFiltered = false;
    private DoubleLinkedList<Vehiculos> filteredVehiculosByKm;
    private boolean isFilteredByKm = false;
    private String usuario;
    private String rutaArchivo;

    public void start(Stage primaryStage)throws Exception{
        throw new UnsupportedOperationException("No se usa");
    }
    
    public void start(Stage primaryStage, String usuario) {
        imagenes = new CircularDoublyLinkedList<>();
        vehiculos = new DoubleLinkedList<>();
        observableList = FXCollections.observableArrayList();
        listView = new ListView<>(observableList);

        Button prevButton = new Button("Anterior");
        Button nextButton = new Button("Siguiente");
        Button sortPrecioButton = new Button("Ordenar por Precio");
        Button sortKmButton = new Button("Ordenar por Kilometraje");
        Button filterButton = new Button("Filtrar por Precio");
        Button limpiarFilter = new Button("Limpiar Filtro");
        Button filterKm = new Button("Filtrar Kilometraje");
        
        Button btnFiltrarPorTipo = new Button("Filtrar por Tipo");
        tipoCB = new ComboBox<>();
        tipoCB.getItems().addAll("SEDAN","CROSSOVER","TODOTERRENO", "CAMIONETA", "VAN");
        
        
        minPriceField = new TextField();
        minPriceField.setPromptText("Precio Mínimo");
        maxPriceField = new TextField();
        maxPriceField.setPromptText("Precio Máximo");
        minKmField = new TextField();
        minKmField.setPromptText("Kilometraje Mínimo");
        maxKmField = new TextField(); 
        maxKmField.setPromptText("Kilometraje Máximo");

        prevButton.setOnAction(e -> showPreviousPage());
        nextButton.setOnAction(e -> showNextPage());
        sortPrecioButton.setOnAction(e -> sortVehiculosPrecio());
        sortKmButton.setOnAction(e -> sortVehiculosKm());
        filterButton.setOnAction(e -> filterVehiculosByPrice());
        limpiarFilter.setOnAction(e -> clearFilters());
        filterKm.setOnAction(e -> filterVehiculosByKm());
        
        imagenes.addLast("file:C:\\Users\\Cristhian\\Downloads\\Borrador\\src\\main\\java\\Imagenes\\4.png");
        imagenes.addLast("file:C:\\Users\\Cristhian\\Downloads\\Borrador\\src\\main\\java\\Imagenes\\kia-stonic-my24-rims-front-intro.jpg");

        Vehiculos vehiculo1 = new Vehiculos("Kia", "modelo1", 20000, "CROSSOVER", 2019, 30000, Combustible.GASOLINA, "2x2", "Automatico", imagenes);
        Vehiculos vehiculo2 = new Vehiculos("Chevrolet", "modelo2", 18000, "SEDAN", 2020, 25000, Combustible.DIESEL, "2x2", "Manual", imagenes);
        Vehiculos vehiculo3 = new Vehiculos("Renault", "modelo2", 10000, "CAMIONETA", 2020, 40000, Combustible.DIESEL, "2x2", "Manual", imagenes);
        Vehiculos vehiculo4 = new Vehiculos("Mazda", "modelo2", 15000, "SEDAN", 2020, 31000, Combustible.DIESEL, "2x2", "Manual", imagenes);
        Vehiculos vehiculo5 = new Vehiculos("Ferrari", "modelo2", 10000, "SEDAN", 2020, 10000, Combustible.DIESEL, "2x2", "Manual", imagenes);
        Vehiculos vehiculo6 = new Vehiculos("Forza", "modelo2", 21000, "SEDAN", 2020, 25000, Combustible.DIESEL, "2x2", "Manual", imagenes);

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

        HBox priceFilterBox = new HBox(10, new Label("Precio Min:"), minPriceField, new Label("Precio Max:"), maxPriceField, filterButton, limpiarFilter);
        priceFilterBox.setStyle("-fx-alignment: center;");
        
        HBox kmFilterBox = new HBox(10, new Label("Kilometraje Min:"), minKmField, new Label("Kilometraje Max:"), maxKmField, filterKm);
        kmFilterBox.setStyle("-fx-alignment: center;");
        
        HBox TipoFilterBox = new HBox(10, new Label("Tipo de carro:"), tipoCB, btnFiltrarPorTipo);
        TipoFilterBox.setStyle("-fx-alignment: center;");

        VBox topBox = new VBox(10, sortButtonBox , priceFilterBox, kmFilterBox, TipoFilterBox,gridPane);
        topBox.setStyle("-fx-alignment: center;");

        ImageView imageView = new ImageView(new Image("file:C:\\Users\\Cristhian\\Downloads\\Borrador\\src\\main\\java\\Imagenes\\logo.png"));
        imageView.setFitHeight(90);
        imageView.setFitWidth(90);
        imageView.setPreserveRatio(true);
        Label lblAutoSelecto = new Label("AUTOSELECTO");
        lblAutoSelecto.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 24px; -fx-font-weight: bold;");
        
        HBox hBoxImagenTexto = new HBox(imageView, lblAutoSelecto);
        hBoxImagenTexto.setAlignment(Pos.CENTER_LEFT);
        hBoxImagenTexto.setSpacing(10);

        Font font = Font.font("Arial Black", FontWeight.BOLD, 20);
        Font font2 = Font.font("Arial", FontWeight.BOLD, 14);
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLACK);

        lblAutoSelecto.setFont(font);
        lblAutoSelecto.setEffect(dropShadow);

        Label lblBuscar = new Label("MARCA O MODELO");
        lblBuscar.setFont(font2);
        imageView.setEffect(dropShadow);

        TextField tfBuscar = new TextField();
        tfBuscar.setEffect(dropShadow);
        tfBuscar.setPromptText("Escribe lo que quieres buscar");
        Button btnBuscar = new Button("Buscar");
        btnBuscar.setEffect(dropShadow);
        btnBuscar.setFont(font2);
        
        // Configurar la acción del botón de búsqueda
        btnBuscar.setOnAction(e -> filterVehiculosBySearch(tfBuscar.getText()));
        
        // Configurar la acción del botón de búsqueda por tipo
        btnFiltrarPorTipo.setOnAction(e -> filterVehiculosByTipo((String) tipoCB.getValue()));
        
        HBox hBoxBuscar = new HBox(lblBuscar, tfBuscar, btnBuscar);
        hBoxBuscar.setAlignment(Pos.CENTER);
        hBoxBuscar.setSpacing(10);

        Button btnCerrarSesion = new Button("Cerrar sesión");
        btnCerrarSesion.setEffect(dropShadow);
        btnCerrarSesion.setFont(font2);
        btnCerrarSesion.setOnAction(e -> {
            Stage appStage = new Stage();
            new App().start(appStage);
            primaryStage.close();
        });

        HBox hBoxTop = new HBox(hBoxImagenTexto, hBoxBuscar, btnCerrarSesion);
        hBoxTop.setAlignment(Pos.CENTER_LEFT);
        hBoxTop.setSpacing(20);
        hBoxTop.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
        hBoxTop.setPadding(new Insets(10));
        hBoxTop.setMinHeight(100);

        HBox.setHgrow(hBoxImagenTexto, Priority.ALWAYS);
        HBox.setHgrow(hBoxBuscar, Priority.ALWAYS);
        hBoxImagenTexto.setAlignment(Pos.CENTER_LEFT);
        hBoxBuscar.setAlignment(Pos.CENTER);
        hBoxTop.setAlignment(Pos.CENTER);
        

        BorderPane root = new BorderPane();
        root.setTop(new VBox(hBoxTop,sortButtonBox, priceFilterBox, kmFilterBox));
        root.setCenter(topBox);
        root.setBottom(buttonBox);

        Scene scene = new Scene(root, 900, 600);

        primaryStage.setTitle("Venta de Carros Usados");
        primaryStage.setScene(scene);
        primaryStage.show();

        updateGridPane();
    }
    
    private void filterVehiculosBySearch(String searchText) {
        if (searchText == null || searchText.trim().isEmpty()) {
            clearFilters(); // Si el texto de búsqueda está vacío, limpiar filtros
            return;
        }

        searchText = searchText.toLowerCase().trim();

        // Crear una lista temporal para los vehículos filtrados
        filteredVehiculos = new DoubleLinkedList<>();

        // Iterar sobre la lista enlazada doble y aplicar el filtro
        DoubleNodeList<Vehiculos> current = vehiculos.getFirst();
        while (current != null) {
            Vehiculos vehiculo = current.getContent();
            if (vehiculo.getMarca().toLowerCase().contains(searchText) || vehiculo.getModelo().toLowerCase().contains(searchText)) {
                filteredVehiculos.addLast(vehiculo);
            }
            current = current.getNext();
        }

        isFiltered = true; // Indicar que hay un filtro aplicado
        currentPage = 0; // Resetear la página a la primera
        updateGridPane(); // Actualizar la vista con los vehículos filtrados
    }
    
    private void filterVehiculosByTipo(String tipoText) {
    if (tipoText == null || tipoText.trim().isEmpty()) {
        clearFilters(); // Si el texto de tipo está vacío, limpiar filtros
        return;
    }

    tipoText = tipoText.toLowerCase().trim();

    // Crear una lista temporal para los vehículos filtrados
    filteredVehiculos = new DoubleLinkedList<>();

        // Iterar sobre la lista enlazada doble y aplicar el filtro
        DoubleNodeList<Vehiculos> current = vehiculos.getFirst();
        while (current != null) {
            Vehiculos vehiculo = current.getContent();
            if (vehiculo.getTipo().toString().toLowerCase().contains(tipoText)) {
                filteredVehiculos.addLast(vehiculo);
            }
            current = current.getNext();
        }

        isFiltered = true; // Indicar que hay un filtro aplicado
        currentPage = 0; // Resetear la página a la primera
        updateGridPane(); // Actualizar la vista con los vehículos filtrados
    }

    private void showPreviousPage(){
        if(currentPage > 0){
            currentPage--;
            updateGridPane();
        }
    }

    private void showNextPage(){
        if((currentPage + 1) * itemsPerpage < vehiculos.size()){
            currentPage++;
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
    
    private void filterVehiculosByPrice() {
        try {
            double minPrice = Double.parseDouble(minPriceField.getText());
            double maxPrice = Double.parseDouble(maxPriceField.getText());

            // Crear una lista temporal para los vehículos filtrados
            filteredVehiculos = new DoubleLinkedList<>();

            // Iterar sobre la lista enlazada doble y aplicar el filtro
            DoubleNodeList<Vehiculos> current = vehiculos.getFirst();
            while (current != null) {
                Vehiculos vehiculo = current.getContent();
                if (vehiculo.getPrecio() >= minPrice && vehiculo.getPrecio() <= maxPrice) {
                    filteredVehiculos.addLast(vehiculo);
                }
                current = current.getNext();
            }

            // Marcar que hay un filtro activo
            isFiltered = true;

            // Actualizar el gridPane con los vehículos filtrados
            currentPage = 0; // Resetear la página a la primera
            updateGridPane();
        } catch (NumberFormatException e) {
            // Manejar error si el formato de precio es incorrecto
            System.out.println("Por favor, ingrese valores de precio válidos.");
        }
}
    
    private void filterVehiculosByKm() {
    try {
        double minKm = Double.parseDouble(minKmField.getText());
        double maxKm = Double.parseDouble(maxKmField.getText());

        // Crear una lista temporal para los vehículos filtrados
        filteredVehiculos = new DoubleLinkedList<>();
        // Iterar sobre la lista enlazada doble y aplicar el filtro
        DoubleNodeList<Vehiculos> current = vehiculos.getFirst();
        while (current != null) {
            Vehiculos vehiculo = current.getContent();
            if (vehiculo.getKilometraje() >= minKm && vehiculo.getKilometraje() <= maxKm) {
                filteredVehiculos.addLast(vehiculo);
            }
            current = current.getNext();
        }

        isFiltered = true; // Indicar que hay un filtro aplicado
        updateGridPane(); // Actualizar la vista con los vehículos filtrados
    } catch (NumberFormatException e) {
        // Manejar error si el formato de kilómetros es incorrecto
        System.out.println("Por favor, ingrese valores de kilómetros válidos.");
    }
}
    
    private void clearFilters() {
        isFiltered = false;
        isFilteredByKm = false;
        filteredVehiculos = null;
        filteredVehiculosByKm = null;
        updateGridPane();
    }

    private void updateGridPane() {
        gridPane.getChildren().clear();

        DoubleLinkedList<Vehiculos> page;
        if (isFiltered && filteredVehiculos != null) {
            page = filteredVehiculos.getPage(currentPage, itemsPerpage);
        } else if (isFilteredByKm && filteredVehiculosByKm != null) {
            page = filteredVehiculosByKm.getPage(currentPage, itemsPerpage);
        } else {
            page = vehiculos.getPage(currentPage, itemsPerpage);
        }

        int row = 0;
        int col = 0;

        for (Vehiculos vehiculo : page) {
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
            if (vehiculo.getImagenes().size() > 0) {
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

    public static void main(String[] args) {
        launch(args);
    }
}