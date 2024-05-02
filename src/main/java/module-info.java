module com.example.knk_gr23 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires de.jensd.fx.glyphs.commons;
    requires de.jensd.fx.glyphs.materialdesignicons;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.example.knk_gr23 to javafx.fxml;
    exports com.example.knk_gr23;
    exports com.example.knk_gr23.Controllers;
    exports com.example.knk_gr23.Controllers.Admin;
    exports com.example.knk_gr23.Controllers.Client;
    exports com.example.knk_gr23.Models;
    exports com.example.knk_gr23.Views;
}