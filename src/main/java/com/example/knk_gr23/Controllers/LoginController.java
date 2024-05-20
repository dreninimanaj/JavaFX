package com.example.knk_gr23.Controllers;

import com.example.knk_gr23.App.Navigator;
import com.example.knk_gr23.Controllers.Client.ClientController;
import com.example.knk_gr23.Models.User;
import com.example.knk_gr23.Models.dto.LoginUserDto;
import com.example.knk_gr23.Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Label adresa_perdoruesit;
    @FXML
    private Button login_btn;
    @FXML
    private PasswordField password_hapsira;
    @FXML
    private ChoiceBox acc_selector;
    @FXML
    private Label error_lbl;
    @FXML
    private TextField textfield_perdoruesit;

    @FXML
    private void handleLogin(ActionEvent ae) {
        LoginUserDto loginUserData = new LoginUserDto(
                this.textfield_perdoruesit.getText(),
                this.password_hapsira.getText()
        );

        User user = UserService.login(loginUserData);
        if (user == null) {
            this.error_lbl.setText("Login failed");
        } else {
            String role = user.getRole();
            if (role == null) {
                this.error_lbl.setText("User role is undefined.");
            } else if (role.equals("admin")) {
                Navigator.navigate(ae, Navigator.ADMIN_PAGE);
            } else {
                Navigator.navigate(ae, Navigator.HOME_PAGE);
            }
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        login_btn.setOnAction(event -> {
//            ClientController clientController = new ClientController();
//            Navigator.navigate(event, Navigator.HOME_PAGE, clientController);
//        });
        Locale locale = Locale.getDefault();
        ResourceBundle bundle = ResourceBundle.getBundle("Translations.content", locale);


    }

    @FXML
    public void handleChangeLanguage(ActionEvent ae) {
        if (Locale.getDefault().getLanguage().equals("sq")) {
            Locale.setDefault(new Locale("en"));
        } else {
            Locale.setDefault(new Locale("sq"));
        }
        System.out.println(Locale.getDefault().getLanguage());
        Navigator.navigate(ae, Navigator.LOGIN_PAGE);
    }
}
