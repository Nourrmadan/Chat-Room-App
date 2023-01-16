package com.example.chatroomapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerController {
    @FXML
    private TextArea OfflineUsers;

    @FXML
    private TextArea BusyUsers;

    @FXML
    private TextArea onlineUsers;
    @FXML
    private Button Refresh;

    public ServerController() {
        new Thread() {
            public void run() {
                try {
                    ResultSet results = DBUtils.getStatues("ONLINE");
                    while (results.next()) {
                        String UserName = results.getString(1);
                        onlineUsers.setText(onlineUsers.getText() + "\n" + UserName);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    ResultSet results = DBUtils.getStatues("BUSY");
                    while (results.next()) {
                        String UserName = results.getString(1);
                        BusyUsers.setText(BusyUsers.getText() + "\n" + UserName);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    ResultSet results = DBUtils.getStatues("OFFLINE");
                    while (results.next()) {
                        String UserName = results.getString(1);
                        OfflineUsers.setText(OfflineUsers.getText() + "\n" + UserName);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            }.start();
    }

    public void RefreshButton(ActionEvent actionEvent){
        ChatServer.changScene(actionEvent,"ChatServerUI.fxml","Chat Server",null);
    }
}
