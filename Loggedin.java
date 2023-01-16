package com.example.chatroomapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Loggedin implements Initializable {
    @FXML
    private Button B2_login;
    @FXML
    private Button B2_Signup;
    @FXML
    private Button B2_Cancel;
    @FXML
    private TextField username2;
    @FXML
    private PasswordField password2;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        B2_Signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ChatServer.changScene(actionEvent,"SignUp.fxml", "Sign Up", null);
            }
        });
        B2_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                boolean b = ChatServer.Login(username2.getText(), password2.getText());
                if (b == true) {
                    try {
                        Stage stage = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Client.fxml"));
                        Parent p = fxmlLoader.load();
                        Client c1 = fxmlLoader.getController();
                        c1.username = username2.getText();
                        Scene scene = new Scene(p, 600, 400);
                        stage.setTitle("Chatting Room");
                        stage.setScene(scene);
                        stage.show();
                        username2.clear();
                        password2.clear();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        B2_Cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
    }

}
