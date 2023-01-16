package com.example.chatroomapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ServerMain extends Application {
        public static void main(String[] args) throws IOException {
            launch(args);
        }
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(ServerMain.class.getResource("ChatServerUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Chat Server");
        stage.setScene(scene);
        stage.show();

    }
}
