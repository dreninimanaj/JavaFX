package com.example.knk_gr23.Controllers.Client;

import com.example.knk_gr23.App.Navigator;
import com.example.knk_gr23.Models.Loan;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LoanComponentController {
    
    @FXML
    private Label loanIdLabel;
    @FXML
    private Label loanAmountLabel;
    @FXML
    private Label loanStatusLabel;

    @FXML
    public void handlePay(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.PAY_PAGE);
    }

    @FXML
    public void handleTable(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.TABLE_PAGE);
    }
    public void setData(Loan loan) {
        loanIdLabel.setText(String.valueOf(loan.getLoan_id()));
        loanAmountLabel.setText(String.valueOf(loan.getLoan_amount()));
        loanStatusLabel.setText(loan.getLoan_status());
    }
}
