module com.example.consultoriopacientes {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.consultoriopacientes to javafx.fxml;
    opens com.example.consultoriopacientes.model to javafx.fxml;
    opens com.example.consultoriopacientes.controllers to javafx.fxml;
    opens com.example.consultoriopacientes.services to javafx.fxml;
    opens com.example.consultoriopacientes.repositories to javafx.fxml;
    exports com.example.consultoriopacientes;
    exports com.example.consultoriopacientes.model;
    exports com.example.consultoriopacientes.controllers;
    exports com.example.consultoriopacientes.services;
    exports com.example.consultoriopacientes.repositories;
}