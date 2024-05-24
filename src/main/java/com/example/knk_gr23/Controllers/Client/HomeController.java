package com.example.knk_gr23.Controllers.Client;

import com.example.knk_gr23.App.Navigator;
import com.example.knk_gr23.App.SessionMenager;
import com.example.knk_gr23.Models.Loan;
import com.example.knk_gr23.Models.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import com.example.knk_gr23.Services.LoanService;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class HomeController {
    @FXML
    private ListView<AnchorPane> loans_listView;
//    @FXML
//    public ListView loans_listview;

    @FXML
    public Button add_loan_button;

    @FXML
    private VBox loansListView;

    @FXML
    public void handleApply(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.PAY_PAGE);
    }
    @FXML
    private Text lblusername;


    @FXML
    public void initialize() throws SQLException {
        lblusername.setText("Hi! " + SessionMenager.getUser().getUsername());
        // Assume the user is retrieved from some authentication context or session
        User user = new User();
        List<Loan> loans = LoanService.getAllLoansByUser(SessionMenager.getUser());

        displayLoans(loans);
    }
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
                System.out.println("eroor");
            }
            loansListView.setPrefHeight(totalHeight);
        }
    }
}
