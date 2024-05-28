package com.example.knk_gr23.Controllers.Client;

import com.example.knk_gr23.App.Navigator;
import com.example.knk_gr23.App.SessionMenager;
import com.example.knk_gr23.Models.Filter.PaymentFilter;
import com.example.knk_gr23.Models.dto.PaymentDto;
import com.example.knk_gr23.Reposirtory.PaymentsRepository;
import com.example.knk_gr23.Services.LoanCalculatorService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TableController implements Initializable {

    @FXML
    private Button exit_btn;
    @FXML
    private Button pay_btn;
    @FXML
    private Button resetFilterButton;
    @FXML
    private Button applyFilterButton;
    @FXML
    private TextField monthFilterField;
    @FXML
    private TextField remainingBalanceFilterField;
    @FXML
    private TableView<PaymentDto> table;

    @FXML
    private TableColumn<PaymentDto, Integer> monthCol;

    @FXML
    private TableColumn<PaymentDto, Double> balanceCol;

    @FXML
    private TableColumn<PaymentDto, Double> paymentCol;

    @FXML
    private TableColumn<PaymentDto, Double> interestCol;

    @FXML
    private TableColumn<PaymentDto, Double> capitalCol;

    @FXML
    private TableColumn<PaymentDto, Double> newBalanceCol;

    @FXML
    private TableColumn<PaymentDto, String> actionCol;

    @FXML
    private Pane paneTableView;
    private final ObservableList<PaymentDto> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        monthCol.setCellValueFactory(new PropertyValueFactory<>("month"));
        balanceCol.setCellValueFactory(new PropertyValueFactory<>("oldBalance"));
        paymentCol.setCellValueFactory(new PropertyValueFactory<>("payment"));
        interestCol.setCellValueFactory(new PropertyValueFactory<>("interest"));
        capitalCol.setCellValueFactory(new PropertyValueFactory<>("capital"));
        newBalanceCol.setCellValueFactory(new PropertyValueFactory<>("remaining"));

        loadPayments();

        applyFilterButton.setOnAction(this::handleApplyFilter);
        resetFilterButton.setOnAction(this::handleResetFilter);
    }

    private void loadPayments() {
        data.clear();
        data.addAll(LoanCalculatorService.getPaymentsForLoan(SessionMenager.getLoan().getLoan_id()));
        table.setItems(data);
    }

    public void setPayments(List<PaymentDto> payments) {
        data.addAll(payments);
    }

    public void handleExit(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.HOME_PAGE);
    }

    public void handlePay(ActionEvent ae) {
        System.out.println(SessionMenager.getLoan().getLoan_id());
        Navigator.navigate(ae, Navigator.PAY_PAGE);
    }

    public void handleApplyFilter(ActionEvent ae) {
        Integer month = null;
        Double remainingBalance = null;

        try {
            if (!monthFilterField.getText().isEmpty()) {
                month = Integer.parseInt(monthFilterField.getText());
            }
        } catch (NumberFormatException e) {
            // Handle invalid number format if needed
        }

        try {
            if (!remainingBalanceFilterField.getText().isEmpty()) {
                remainingBalance = Double.parseDouble(remainingBalanceFilterField.getText());
            }
        } catch (NumberFormatException e) {
            // Handle invalid number format if needed
        }

        PaymentFilter filter = new PaymentFilter(month, remainingBalance, remainingBalance, 0, 100);
        List<PaymentDto> filteredPayments = PaymentsRepository.findPaymentsByFilter(filter);
        table.setItems(FXCollections.observableArrayList(filteredPayments));
    }

    public void handleResetFilter(ActionEvent ae) {
        monthFilterField.clear();
        remainingBalanceFilterField.clear();
        loadPayments();
    }

}
