package com.example.knk_gr23.Controllers.Admin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateClientController implements Initializable {
    public TextField fName_fld;
    public TextField lName_fld;
    public TextField password_fld;
    public TextField pID_fld;
    public TextField hAddress_fld;
    public TextField cHistory_fld;
    public TextField empStatus_fld;
    public TextField Income_fld;
    public TextField cAmount_fld;
    public TextField interest_fld;
    public TextField dToI_FLD;
    public TextField duration_fld;
    public Button create_client_btn;

    @FXML
    private Text first_name;

    @FXML
    private Text last_name;

    @FXML
    private Text password;

    @FXML
    private Text personal_id;
    @FXML
    private Text home_addres;
    @FXML
    private Text credit_history;
    @FXML
    private Text employment_status;

    @FXML
    private Text income;
    @FXML
    private Text credit_amount;
    @FXML
    private Text interest;
    @FXML
    private Text debt_income;
    @FXML
    private Text duration;






    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.first_name.setText(resourceBundle.getString("lblFirstName"));
        this.last_name.setText(resourceBundle.getString("lblLastName"));
        this.password.setText(resourceBundle.getString("lblPassword"));
        this.personal_id.setText(resourceBundle.getString("lblPersonalID"));
        this.home_addres.setText(resourceBundle.getString("lblHomeAddres"));
        this.credit_history.setText(resourceBundle.getString("lblCreditHistory"));
        this.employment_status.setText(resourceBundle.getString("lblEmploymentstatus"));
        this.income.setText(resourceBundle.getString("lblIncome"));
        this.credit_amount.setText(resourceBundle.getString("lblCreditAmount"));
        this.interest.setText(resourceBundle.getString("lblInterest"));
        this.debt_income.setText(resourceBundle.getString("lblDebtToIncomeRatio"));
        this.duration.setText(resourceBundle.getString("lblDuration"));
    }

}

