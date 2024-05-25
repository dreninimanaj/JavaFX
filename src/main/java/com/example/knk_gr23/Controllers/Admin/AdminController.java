package com.example.knk_gr23.Controllers.Admin;

import com.example.knk_gr23.Models.User;
import com.example.knk_gr23.Services.ClientService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AdminController {

    @FXML
    private VBox requestsListView;

    @FXML
    public void initialize() throws SQLException {


        try {
            List<User> clients = ClientService.getAllClients();
            displayClients(clients);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void displayClients(List<User> usersList){
        double totalHeight=300;
        requestsListView.getChildren().clear();
        for (User user : usersList) {
            System.out.println(user);
            try {
                System.out.println(user.getId());
                System.out.println();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client/ClientsComponent.fxml"));
                Pane busPane = loader.load();
                ClientsComponentController clientsComponentController = loader.getController();
                clientsComponentController.setData(user);
                requestsListView.getChildren().add(busPane);

                totalHeight += busPane.getPrefHeight() + requestsListView.getSpacing();

            } catch (IOException e) {
                System.out.println("eroor");
            }
            requestsListView.setPrefHeight(totalHeight);
        }
    }
}