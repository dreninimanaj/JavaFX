package com.example.knk_gr23.Controllers.Admin;

import com.example.knk_gr23.Models.dto.CreateClientDto;
import com.example.knk_gr23.Reposirtory.UserRepository;
import com.example.knk_gr23.Services.PasswordHasher;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class CreateClientController {

    @FXML
    private Text create_client_account;
    @FXML
    private Text first_name;
    @FXML
    private Text last_name;
    @FXML
    private Text username;
    @FXML
    private Text password;
    @FXML
    private Text email;
    @FXML
    private Text personal_id;
    @FXML
    private Text home_address;
    @FXML
    private Text phone;
    @FXML
    private Text credit_history;
    @FXML
    private Text employment_status;
    @FXML
    private Text income;

    @FXML
    private TextField fName_fld;
    @FXML
    private TextField lName_fld;
    @FXML
    private TextField username_fld;
    @FXML
    private TextField password_fld;
    @FXML
    private TextField email_fld;
    @FXML
    private TextField pID_fld;
    @FXML
    private TextField hAddress_fld;
    @FXML
    private TextField phone_fld;
    @FXML
    private TextField cHistory_fld;
    @FXML
    private TextField empStatus_fld;
    @FXML
    private TextField Income_fld;
    @FXML
    private Button create_client_btn;

    @FXML
    private void handleCreateClient() {
        try {
            String salt = PasswordHasher.generateSalt();
            String passwordHash = PasswordHasher.generateSaltedHash(password_fld.getText(), salt);
            CreateClientDto createClientDto = new CreateClientDto(
                    fName_fld.getText(),
                    passwordHash, // Ideally, you should hash the password here
                    email_fld.getText(),
                    "customer",
                    salt,  // You should generate a real salt here
                    fName_fld.getText() + " " + lName_fld.getText(),
                    hAddress_fld.getText(),
                    phone_fld.getText(),
                    empStatus_fld.getText(),
                    Double.parseDouble(Income_fld.getText()),
                    cHistory_fld.getText(),
                    0.0  // Assuming a default value for debtToIncomeRatio
            );

            boolean success = UserRepository.signUp(createClientDto);

            Alert alert = new Alert(success ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
            alert.setTitle(success ? "Success" : "Error");
            alert.setHeaderText(null);
            alert.setContentText(success ? "Client created successfully!" : "Username already exists. Please choose another username.");
            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred: " + e.getMessage());
            alert.showAndWait();
        }
    }
}
