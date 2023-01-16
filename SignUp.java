package com.example.chatroomapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUp implements Initializable {
    @FXML
    private Button B_signup;
    @FXML
    private Button B_login;
    @FXML
    private Button B_Cancel;
    @FXML
    private TextField first_name;
    @FXML
    private TextField last_name;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        B_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!first_name.getText().trim().isEmpty() && !last_name.getText().trim().isEmpty() && !username.getText().trim().isEmpty() && !password.getText().trim().isEmpty()) {
                    ChatServer.SignUp(first_name.getText(), last_name.getText(), username.getText(), password.getText());
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("please fill all information to sign up");
                    alert.show();
                }
            }
        });
        B_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ChatServer.changScene( actionEvent,"LogIn.fxml", "login", null);
            }
        });
        B_Cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

    }
}