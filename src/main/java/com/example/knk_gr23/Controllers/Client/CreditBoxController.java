package com.example.knk_gr23.Controllers.Client;

import com.example.knk_gr23.App.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CreditBoxController {
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
