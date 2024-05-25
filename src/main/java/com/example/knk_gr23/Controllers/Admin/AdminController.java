package com.example.knk_gr23.Controllers.Admin;

import com.example.knk_gr23.App.SessionMenager;
import com.example.knk_gr23.Models.Loan;
import com.example.knk_gr23.Models.User;
import com.example.knk_gr23.Services.ClientService;
import com.example.knk_gr23.Services.LoanService;
import com.example.knk_gr23.Services.PendingLoanService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    private VBox requestsListView;

    private void displayRequests(List<Loan> loansList){
        double totalHeight=300;
        requestsListView.getChildren().clear();
        for (Loan loan : loansList) {
            System.out.println(loan);
            try {
                System.out.println(loan.getLoan_id());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/RequestComponent.fxml"));
                Pane busPane = loader.load();
                RequestComponentController requestComponentController = loader.getController();
                requestComponentController.setData(loan);
                requestsListView.getChildren().add(busPane);

                totalHeight += busPane.getPrefHeight() + requestsListView.getSpacing();

            } catch (IOException e) {
                System.out.println("eroor1");
            }
            requestsListView.setPrefHeight(totalHeight);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            List<Loan> pendingLoans = PendingLoanService.getAllPendingLoans();
            displayRequests(pendingLoans);
        } catch (SQLException e) {
            System.out.println("error");
        }

    }
}