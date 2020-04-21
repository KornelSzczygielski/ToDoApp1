package sample.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField loginUsername;

    @FXML
    private JFXPasswordField loginPassword;

    @FXML
    private JFXButton loginButton;

    @FXML
    private JFXButton loginSignupButton;

    @FXML
    void initialize() {

        String loginText = loginUsername.getText().trim();
        String loginPwd  = loginPassword.getText().trim();




        loginSignupButton.setOnAction(event -> {
            //Take users to signup screen
            loginSignupButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/signup.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }


            Parent root = loader.getRoot();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        });

        loginButton.setOnAction(event -> {
            if (loginText.equals("") || !loginPwd.equals("")) {
                loginUser(loginText, loginPwd);
            }else{
                System.out.println("error login in user");
            }

        });

    }

    private void loginUser(String userName, String password) {
        //Chcek in the batabase if user exists,if
        //we take them to AddItem Screen
    }
}
