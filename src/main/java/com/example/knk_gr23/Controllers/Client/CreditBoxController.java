package com.example.knk_gr23.Controllers.Client;

import com.example.knk_gr23.App.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CreditBoxController {
    public Button tbl_btn;
    public Label box_date_lbl;
    public Label sender_lbl;
    public Label recevier_lbl;
    public Label amount_lbl;
    @FXML
    private Button loan_btn;

    @FXML
    public void handlePay(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.PAY_PAGE);
    }

    @FXML
    public void handleTable(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.TABLE_PAGE);
    }

}
