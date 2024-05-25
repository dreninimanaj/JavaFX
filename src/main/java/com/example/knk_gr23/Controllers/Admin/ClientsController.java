package com.example.knk_gr23.Controllers.Admin;

import com.example.knk_gr23.Models.User;
import com.example.knk_gr23.Services.ClientService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ClientsController  implements Initializable {


    @FXML
    private VBox clientsList;

    private void displayClients(List<User> usersList){
        double totalHeight=300;
        clientsList.getChildren().clear();
        for (User user : usersList) {
            System.out.println(user);
            try {
                System.out.println(user.getId());
                System.out.println();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/ClientsComponent.fxml"));
                Pane busPane = loader.load();
                ClientsComponentController clientsComponentController = loader.getController();
                clientsComponentController.setData(user);
                clientsList.getChildren().add(busPane);

                totalHeight += busPane.getPrefHeight() + clientsList.getSpacing();

            } catch (IOException e) {
                System.out.println("eroor");
            }
            clientsList.setPrefHeight(totalHeight);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            List<User> clients = ClientService.getAllClients();
            displayClients(clients);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
