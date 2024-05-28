package com.example.knk_gr23.Controllers.Client;

import com.example.knk_gr23.App.SessionMenager;
import com.example.knk_gr23.Models.Loan;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import com.example.knk_gr23.Services.LoanService;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class HomeController implements Initializable {
    @FXML
    private Text lblusername;
    @FXML
    private Text accounts_summary;
    @FXML
    private VBox loansListView;



    private void displayLoans(List<Loan> loansList){
        double totalHeight=300;
        loansListView.getChildren().clear();
        for (Loan loan : loansList) {
            System.out.println(loan);
            try {
                System.out.println(loan.getLoan_id());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client/LoanComponent.fxml"));
                Pane busPane = loader.load();
                LoanComponentController loanComponentController = loader.getController();
                loanComponentController.setData(loan);
                loansListView.getChildren().add(busPane);

                totalHeight += busPane.getPrefHeight() + loansListView.getSpacing();

            } catch (IOException e) {
                System.out.println("eroor2");
            }
            loansListView.setPrefHeight(totalHeight);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Loan> loans = null;
        try {
            loans = LoanService.getAllLoansByUser(SessionMenager.getClient());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        displayLoans(loans);
        lblusername.setText("Hi! " + SessionMenager.getUser().getUsername());
        //this.lblusername.setText(resourceBundle.getString("lblHi"));
        this.accounts_summary.setText(resourceBundle.getString("lblAccountsSummary"));
    }
}
