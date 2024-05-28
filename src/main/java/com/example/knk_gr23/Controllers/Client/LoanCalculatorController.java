package com.example.knk_gr23.Controllers.Client;


import com.example.knk_gr23.App.SessionMenager;
import com.example.knk_gr23.Models.dto.LoanDto;
import com.example.knk_gr23.Models.dto.PaymentDto;
import com.example.knk_gr23.Reposirtory.LoanCalculatorRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.knk_gr23.Services.LoanCalculatorService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoanCalculatorController implements Initializable {

    @FXML
    private Label loanCalculator;

    @FXML
    private Label enter_amount;

    @FXML
    private Label interest_rate;

    @FXML
    private Label duration;

    @FXML
    private Label monthly_payment;

    @FXML
    private Label interest_paid;

    @FXML
    private Label total_paid;

    @FXML
    private Label lblInterest;

    @FXML
    private Label lblTotal;

    @FXML
    private Label lblPayment;

    @FXML
    private Button btnCalculate;

    @FXML
    private Button btnExit;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtDuration;


    @FXML
    private TextField txtInterestRate;

    @FXML
    private PieChart pieChart;

    @FXML
    private TableView<PaymentDto> table;

    private LoanDto currentLoan;

    @FXML
    private void handleCalculate() {
        try {
            double amount = Integer.parseInt(txtAmount.getText());
            double ir = Double.parseDouble(txtInterestRate.getText());
            int years = Integer.parseInt(txtDuration.getText());

            currentLoan = new LoanDto(years, ir, amount);
            LoanCalculatorService.buildTable(currentLoan, SessionMenager.getClient().getClientId());

            double irPaid = currentLoan.getTotalInterest() / currentLoan.getTotalPaid() * 100;
            double capital = amount / currentLoan.getTotalPaid() * 100;

            ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList(
                    new PieChart.Data("Interest", irPaid),
                    new PieChart.Data("Capital", capital)
            );

            pieChart.setData(chartData);

            lblInterest.setText(String.format("$%.2f", currentLoan.getTotalInterest()));
            lblPayment.setText(String.format("$%.2f", currentLoan.getMonthlyPayment()));
            lblTotal.setText(String.format("$%.2f", currentLoan.getTotalPaid()));

            ObservableList<PaymentDto> payments = FXCollections.observableArrayList(currentLoan.getPayments());
            table.setItems(payments);

        } catch (Exception e) {
            alertUser(e.getMessage());
        }
    }

    @FXML
    private void handleExit() throws SQLException {
        if (currentLoan != null) {
            LoanCalculatorRepository.saveLoan(currentLoan, SessionMenager.getClient().getClientId());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Loan created successfully!");
            alert.setTitle("Success");
            alert.show();
        } else {
            alertUser("Please calculate the loan first.");
        }
    }

    private void initializeTableColumns() {
        TableColumn<PaymentDto, Integer> monthCol = new TableColumn<>("Month");
        monthCol.setCellValueFactory(new PropertyValueFactory<>("month"));

        TableColumn<PaymentDto, Double> balanceCol = getPaymentDoubleTableColumn("Balance", "oldBalance");

        TableColumn<PaymentDto, Double> paymentCol = getPaymentDoubleTableColumn("Payment", "payment");

        TableColumn<PaymentDto, Double> interestCol = getPaymentDoubleTableColumn("Interest Paid", "interest");

        TableColumn<PaymentDto, Double> capitalCol = getPaymentDoubleTableColumn("Capital Paid", "capital");

        TableColumn<PaymentDto, Double> newBalanceCol = getPaymentDoubleTableColumn("Remaining Balance", "remaining");

        table.getColumns().addAll(monthCol, balanceCol, paymentCol, interestCol, capitalCol, newBalanceCol);
    }

    private static TableColumn<PaymentDto, Double> getPaymentDoubleTableColumn(String Balance, String oldBalance) {
        TableColumn<PaymentDto, Double> balanceCol = new TableColumn<>(Balance);
        balanceCol.setCellValueFactory(new PropertyValueFactory<>(oldBalance));
        balanceCol.setCellFactory(cell -> new TableCell<>() {
            @Override
            protected void updateItem(Double aDouble, boolean empty) {
                super.updateItem(aDouble, empty);
                if (aDouble != null) {
                    setText(String.format("$%.2f", aDouble));
                }
            }
        });
        return balanceCol;
    }

    private void alertUser(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.setTitle("ERROR message");
        alert.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.loanCalculator.setText(resourceBundle.getString("lblLoanCalculator"));
        this.enter_amount.setText(resourceBundle.getString("lblEnterAmount"));
        this.interest_rate.setText(resourceBundle.getString("lblInterestRate"));
        this.duration.setText(resourceBundle.getString("lblDuration"));
        this.monthly_payment.setText(resourceBundle.getString("lblMonthlyPayment"));
        this.interest_paid.setText(resourceBundle.getString("lblInterestPaid"));
        this.total_paid.setText(resourceBundle.getString("lblTotalPaid"));
        this.btnCalculate.setText(resourceBundle.getString("lblCalculate"));
        this.btnExit.setText(resourceBundle.getString("lblApply"));

        initializeTableColumns();
    }
}