package com.example.knk_gr23.Controllers.Client;

import com.example.knk_gr23.App.Navigator;
import com.example.knk_gr23.Models.dto.LoanDto;
import com.example.knk_gr23.Models.dto.PaymentDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class LoanCalculatorController {

    @FXML
    private Label loanCalculator;
    @FXML
    private Label enter_Amount;
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

    @FXML
    public void initialize() {
        initializeTableColumns();
    }

    @FXML
    private void handleExit(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.HOME_PAGE);
    }

    @FXML
    private void handleCalculate() {
        try {
            double amount = Double.parseDouble(txtAmount.getText());
            double ir = Double.parseDouble(txtInterestRate.getText());
            int years = Integer.parseInt(txtDuration.getText());

            LoanDto loan = new LoanDto(years, ir, amount);
            loan.buildTable();

            double irPaid = loan.getTotalInterest() / loan.getTotalPaid() * 100;
            double capital = amount / loan.getTotalPaid() * 100;

            ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList(
                    new PieChart.Data("Interest", irPaid),
                    new PieChart.Data("Capital", capital)
            );

            pieChart.setData(chartData);

            lblInterest.setText(String.format("$%.2f", loan.getTotalInterest()));
            lblPayment.setText(String.format("$%.2f", loan.getMonthlyPayment()));
            lblTotal.setText(String.format("$%.2f", loan.getTotalPaid()));

            ObservableList<PaymentDto> payments = FXCollections.observableArrayList(loan.getPayments());
            table.setItems(payments);

        } catch (Exception e) {
            alertUser(e.getMessage());
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

    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.loanCalculator.setText(resourceBundle.getString("lblLoanCalculator"));
        this.enter_Amount.setText(resourceBundle.getString("lblEnterAmount"));
        this.interest_rate.setText(resourceBundle.getString("lblInterestRate"));
        this.duration.setText(resourceBundle.getString("lblDuration"));
        this.monthly_payment.setText(resourceBundle.getString("lblMonthlyPayment"));
        this.interest_paid.setText(resourceBundle.getString("lblInterestPaid"));
        this.total_paid.setText(resourceBundle.getString("lblTotalPaid"));
        this.btnCalculate.setText(resourceBundle.getString("lblCalculate"));
        this.btnExit.setText(resourceBundle.getString("lblExit"));
    }
}
