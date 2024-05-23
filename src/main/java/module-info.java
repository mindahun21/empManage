module com.example.employeemanagement {
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
    requires java.sql;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires MaterialFX;
    requires jdk.compiler;

    opens com.example.employeemanagement to javafx.fxml;
    exports com.example.employeemanagement;
    opens com.example.employeemanagement.database to javafx.base;
    exports com.example.employeemanagement.Controllers;
    exports com.example.employeemanagement.Models;
    opens com.example.employeemanagement.Controllers to javafx.fxml;
    opens com.example.employeemanagement.Models to org.hibernate.orm.core;

}