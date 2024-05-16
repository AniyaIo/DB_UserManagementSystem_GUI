module com.example.db_usermanagementsystem_gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires org.postgresql.jdbc;
    requires java.sql;

    opens com.example.db_usermanagementsystem_gui to javafx.fxml;
    exports com.example.db_usermanagementsystem_gui;
}