package com.example.chatroomapp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.layout.Pane;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.ResourceBundle;
//
//public class WelcomeController implements Initializable {
//    @FXML
//    Label WelcomeLable;
//    @FXML
//    Button JoinChat;
//    public String userName;
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        JoinChat.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                try {
//                    Stage stage = new Stage();
//                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Client.fxml"));
//                    Parent p = fxmlLoader.load();
//                    Client c1 = fxmlLoader.getController();
//                    c1.username = userName;
//                    Scene scene = new Scene(p, 600, 400);
//                    stage.setTitle("Chatting Room");
//                    stage.setScene(scene);
//                    stage.show();
//                    DBUtils.changScene(actionEvent, "LogIn.fxml", "login!", null);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    public void setUserinformation(String username) {
//        this.userName = username;
//        WelcomeLable.setText("Welcome " + username + "!");
//    }
//}
