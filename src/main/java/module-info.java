module com.example.knk_gr23 {
    requires javafx.controls;
    requires javafx.fxml;
//    requires javafx.web;
//    requires de.jensd.fx.glyphs.commons;
//    requires de.jensd.fx.glyphs.materialdesignicons;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    //requires com.almasb.fxgl.all;


    exports com.example.knk_gr23.App;
    exports com.example.knk_gr23.Controllers.Client to javafx.fxml;
    opens com.example.knk_gr23.Controllers to javafx.fxml;
}