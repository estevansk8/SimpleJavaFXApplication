module com.example.exjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.exjavafx to javafx.fxml;
    exports com.example.exjavafx;
}