package es.promehub.controllers;

import es.promehub.backend.Hotel;
import es.promehub.backend.DatosApp;
import es.promehub.backend.Habitacion;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.File;

public class HotelController {

    private static final Hotel catalogo = DatosApp.hotel;

    @FXML private TextField buscador;
    @FXML private TableView<Habitacion> tabla;

    @FXML private TableColumn<Habitacion, Integer> colNumero;
    @FXML private TableColumn<Habitacion, String> colEstado;
    @FXML private TableColumn<Habitacion, Integer> colPlanta;
    @FXML private TableColumn<Habitacion, String> colTipo;
    @FXML private TableColumn<Habitacion, Integer> colCapacidad;
    @FXML private TableColumn<Habitacion, Integer> colPrecioNoche;
    @FXML private TableColumn<Habitacion, String> colAccesible;

    @FXML private Label labelResults;

    // populate list and display its elements

    @FXML
    public void initialize() {
        cargarXML();
        mostrarCatalogo();
    }

    @FXML
    public void borrarContenido(){

        buscador.setText("");
    }

    // lógica de negocio

    // 2. Mostrar catálogo
    // 6. Buscar videojuego

    // ids are unique
    // search if room of searched id exists and have a label at the bottom of the app display if its available or not
    // shittard

    private void mostrarCatalogo(){

        // hacemos un observable arraylist para trabajar con javaFX
        ObservableList<Habitacion> habitaciones = FXCollections.observableArrayList(catalogo.getHabitaciones());
        FilteredList<Habitacion> filtrados = new FilteredList<>(habitaciones);
        buscador.textProperty().addListener((observable, oldValue, newValue) -> {

            String texto = newValue.trim().toLowerCase();
            filtrados.setPredicate(habitacion -> {

                if(texto.isEmpty()){
                    labelResults.setText("Waiting");
                    return true;
                }
                else{
                    boolean greg = String.valueOf(habitacion.getNumero()).equals(texto) ||
                            Integer.toString(habitacion.getNumero()).contains(texto);

                    // temp

                    if (greg) {
                        labelResults.setText("Found");
                    } else {
                        labelResults.setText("Doesn't exist");
                    }

                    return greg;
                }
            });
        });

        // le pasamos el array con el que queremos trabajar a TableView
        tabla.setItems(filtrados);

        // definimos las reglas con las que queremos que se rellenen las columnas
        colNumero.setCellValueFactory(celda -> new SimpleObjectProperty<>(celda.getValue().getNumero()));
        colEstado.setCellValueFactory(celda -> new SimpleObjectProperty<>(celda.getValue().getEstado()));
        colPlanta.setCellValueFactory(celda -> new SimpleObjectProperty<>(celda.getValue().getPlanta()));
        colTipo.setCellValueFactory(celda -> new SimpleObjectProperty<>(celda.getValue().getTipo()));
        colCapacidad.setCellValueFactory(celda -> new SimpleObjectProperty<>(celda.getValue().getCapacidad()));
        colPrecioNoche.setCellValueFactory(celda -> new SimpleObjectProperty<>(celda.getValue().getPrecioNoche()));
        colAccesible.setCellValueFactory(celda -> new SimpleObjectProperty<>(celda.getValue().getAccesible()));
    }

    @FXML
    // Cargar desde XML
    private void cargarXML(){

        File archivo = new File("src/main/resources/es/promehub/reservas.xml");



        if(archivo.exists()){

            try{
                JAXBContext contexto = JAXBContext.newInstance(Hotel.class);
                Unmarshaller unmarshaller = contexto.createUnmarshaller();
                Hotel hotelImportado = (Hotel) unmarshaller.unmarshal(archivo);


                for (Habitacion habitacion : hotelImportado.getHabitaciones()) {
                    catalogo.insertarHabitacion(habitacion);
                }

                System.out.println(catalogo.getHabitaciones());

                System.out.println("The XML was successfully loaded");

            }catch(JAXBException e){
                System.out.println("The XML was unable to load");
            }

        }
        else{
            System.out.println("The file does not exist");
        }
    }



}
