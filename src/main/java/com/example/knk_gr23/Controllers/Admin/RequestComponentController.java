package com.example.knk_gr23.Controllers.Admin;

import com.example.knk_gr23.Models.Loan;
import com.example.knk_gr23.Services.LoanService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class RequestComponentController implements Initializable {

    @FXML
    private Label loanIdLabel;

    @FXML
    private Label loanAmountLabel;

    @FXML
    private Label loanStatusLabel;

    @FXML
    private Button acceptLoanButton;

    @FXML
    private Button viewUserInfoButton;

    private Loan loan;

    public void setData(Loan loan) {
        this.loan = loan;
        loanIdLabel.setText(String.valueOf(loan.getLoan_id()));
        loanAmountLabel.setText(String.valueOf(loan.getLoan_amount()));
        loanStatusLabel.setText(loan.getLoan_status());
    }

    @FXML
    private void handleAcceptLoan(ActionEvent event) {
        try {
            boolean success = LoanService.acceptLoan(loan.getLoan_id());
            if (success) {
                loanStatusLabel.setText("approved");
                acceptLoanButton.setDisable(true);  // Disable button after acceptance
            } else {
                System.err.println("Failed to accept loan with ID: " + loan.getLoan_id());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while accepting loan with ID: " + loan.getLoan_id());
        }
    }

    @FXML
    private void handleViewUserInfo(ActionEvent event) {
        // Implement the logic to view user information
        System.out.println("Viewing user info for client ID: " + loan.getClient_id());
        // Navigate to the user info page or display a popup with user info
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.loanIdLabel.setText(resourceBundle.getString("lblLoanID"));
        this.loanAmountLabel.setText(resourceBundle.getString("lblLoanAmount"));
        this.loanStatusLabel.setText(resourceBundle.getString("lblLoanStatus"));
        this.acceptLoanButton.setText(resourceBundle.getString("lblAcceptLoan"));
        this.viewUserInfoButton.setText(resourceBundle.getString("lblViewUserInfo"));

    }
}
