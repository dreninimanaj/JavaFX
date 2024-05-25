package com.example.knk_gr23.Controllers;

import com.example.knk_gr23.App.Navigator;
import com.example.knk_gr23.App.SessionMenager;
import com.example.knk_gr23.Controllers.Client.ClientController;
import com.example.knk_gr23.Models.User;
import com.example.knk_gr23.Models.dto.LoginUserDto;
import com.example.knk_gr23.Services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import com.example.knk_gr23.Services.ClientService;

public class LoginController implements Initializable {

    @FXML
    private Label adresa_perdoruesit;
    @FXML
    private Button login_btn;
    @FXML
    private PasswordField password_hapsira;
    @FXML
    private Label error_lbl;
    @FXML
    private TextField textfield_perdoruesit;
    @FXML
    private Label passwordi_perdoruesit;

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
            System.out.println("test");
            SessionMenager.setUser(user);
            String role = user.getRole();
            System.out.println("role: " + role);
//            lblUsername.setText("hi!" + SessionMenager.getUser().getUsername());

            if (role == null) {
                this.error_lbl.setText("User role is undefined.");
            } else if (role.equals("admin")) {
                System.out.println("admin");
                Navigator.navigate(ae, Navigator.ADMIN_PAGE);
            } else {
                System.out.println("client");
                Navigator.navigate(ae, Navigator.HOME_PAGE);
                SessionMenager.setClient(ClientService.getClient(user.getId()));
            }
            System.out.println("shini");
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.passwordi_perdoruesit.setText(resourceBundle.getString("lblPassword"));
        this.adresa_perdoruesit.setText(resourceBundle.getString("lblUsername"));
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
