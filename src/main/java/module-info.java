module com.example.computershop {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.computershop to javafx.fxml;
    exports com.example.computershop;
}