module com.example.consultoriopacientes {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.consultoriopacientes to javafx.fxml;
    exports com.example.consultoriopacientes;
}