package com.example.consultoriopacientes.controllers;

import com.example.consultoriopacientes.model.Paciente;
import com.example.consultoriopacientes.services.PacienteService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class AppController {
    @FXML
    private TableView<Paciente> tblPacientes;
    @FXML
    private TableColumn<Paciente, String> colCurp, colNombre, colEdad, colTelefono, colAlergias, colEstatus;
    @FXML
    private Label lblMensaje;
    @FXML
    private TextField txtCURP;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtEdad;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtAlergias;

    @FXML
    private final ObservableList<Paciente> data = FXCollections.observableArrayList();
    private PacienteService service = new PacienteService();

    @FXML
    public void initialize() {
        colCurp.setCellValueFactory(new PropertyValueFactory<>("curp"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colAlergias.setCellValueFactory(new PropertyValueFactory<>("alergias"));
        colEstatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        tblPacientes.setItems(data);

        loadFromFile();
        tblPacientes.getSelectionModel().selectedItemProperty().addListener((obs, oldView, newView) -> {
            loadDataToForm(newView);
        });
    }

    @FXML
    public void onAddPaciente() {
        try {
            String curp = txtCURP.getText();
            String name = txtNombre.getText();
            String age = txtEdad.getText();
            String tel = txtTelefono.getText();
            String aler = txtAlergias.getText();
            String stat = "ACTIVO";

            service.addPaciente(curp, name, age, tel, aler, stat);

            lblMensaje.setText("Paciente agregado con éxito");
            lblMensaje.setStyle("-fx-text-fill: green");

            limpiar();
            loadFromFile();

        } catch (IOException e) {
            lblMensaje.setText("Hubo un error en el archivo.");
            lblMensaje.setStyle("-fx-text-fill: red");
        } catch (IllegalArgumentException ex) {
            lblMensaje.setText(ex.getMessage());
            lblMensaje.setStyle("-fx-text-fill: red");
        }
    }


    private void loadFromFile(){
        try {
            List<Paciente> items = service.loadDataForList();
            data.setAll(items);
            lblMensaje.setText("Datos cargados exitosamente!");
            lblMensaje.setStyle("-fx-text-fill: green");
        } catch (IOException e) {
            lblMensaje.setText(e.getMessage());
            lblMensaje.setStyle("-fx-text-fill: red");
        }
    }
    private void loadDataToForm(Paciente pacien) {
        if (pacien == null) return;
        txtCURP.setText(pacien.getCurp());
        txtNombre.setText(pacien.getNombreCompleto());
        txtEdad.setText(pacien.getEdad());
        txtTelefono.setText(pacien.getTelefono());
        txtAlergias.setText(pacien.getAlergias());
    }
    @FXML
    public void onCambiarStatus() {
        Paciente seleccionado = tblPacientes.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            lblMensaje.setText("Primero selecciona un paciente.");
            lblMensaje.setStyle("-fx-text-fill: red");
            return;
        }
        try {
            service.cambiarEstatus(seleccionado.getCurp());

            lblMensaje.setText("El estatus se ha actualizado correctamente.");
            lblMensaje.setStyle("-fx-text-fill: green");
            loadFromFile();
        } catch (IOException e) {
            lblMensaje.setText("Hubo un error al cambiar el estatus.");
            lblMensaje.setStyle("-fx-text-fill: red");
        }
    }

    @FXML
    private void limpiar(){
        txtCURP.clear();
        txtNombre.clear();
        txtEdad.clear();
        txtTelefono.clear();
        txtAlergias.clear();
    }
}
