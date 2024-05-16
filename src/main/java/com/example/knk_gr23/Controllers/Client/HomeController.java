package com.example.knk_gr23.Controllers.Client;

import com.example.knk_gr23.App.Navigator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class HomeController {
    @FXML
    private ListView<AnchorPane> loans_listView;
//    @FXML
//    public ListView loans_listview;

    @FXML
    public Button add_loan_button;

    @FXML
    public void handleApply(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.PAY_PAGE);
    }

    public void loadListItem() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client/CreditBox.fxml"));
            AnchorPane pane = loader.load();
            loans_listView.getItems().add(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }



        loans_listView.setCellFactory(param -> new ListCell<AnchorPane>() {
            @Override
            protected void updateItem(AnchorPane item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    setGraphic(item);
                }
            }
        });
    }
    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            loadListItem();
        });
    }


}
