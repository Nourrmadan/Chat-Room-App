package com.example.chatroomapp;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;

public class Client {
    @FXML
    TextField Msg;
    @FXML
    TextArea All;
    @FXML
    Button Send;
    @FXML
    Button SaveChat;
    @FXML
    Button Logout;
    @FXML
    RadioButton Busy;
    @FXML
    RadioButton ONLINE;
    public String username;
    private File loadedFileReference;


    Socket mySocket;
    DataInputStream dis;
    PrintStream ps;

    public Client() throws IOException {

        mySocket = new Socket("127.0.0.1", 5005);
        dis = new DataInputStream(mySocket.getInputStream());
        ps = new PrintStream(mySocket.getOutputStream());
        new Thread(() -> {
            while (true) {
                try {
                    String msg = dis.readLine();
                    All.setText(All.getText() + "\n " + msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void handle(ActionEvent actionEvent) {
        if (Msg.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Nothing Written to send!");
            alert.show();
        } else {
            System.out.println("send");
            ps.println(username + " :" + Msg.getText());
            ps.flush();
            Msg.clear();
        }
    }

    public void SaveClick(ActionEvent ev) {
        if (loadedFileReference == null) {
            FileChooser Savefile = new FileChooser();
            Savefile.setTitle("Save");
            Savefile.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Text Files", "*.txt")
            );
            File selected = Savefile.showSaveDialog(null);
            if (selected != null) {
                saveNewFile(selected);
            }
        } else {
            try {
                FileWriter myWriter = new FileWriter(loadedFileReference);
                myWriter.write(All.getText());
                myWriter.close();
                loadedFileReference = null;
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                Logger.getLogger(getClass().getName()).log(SEVERE, null, e);
            }
        }
    }

    private void saveNewFile(File input) {
        Task<String> saveTask = new Task<>() {
            @Override
            protected String call() throws Exception {
                FileWriter myWriter = new FileWriter(input);
                myWriter.write(All.getText());
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
                return "";
            }
        };
        saveTask.run();
    }

    public void LogoutClick(ActionEvent actionEvent) {
        ChatServer.Logout(username);
        Stage stage = (Stage) Logout.getScene().getWindow();
        stage.close();
    }

    public void onlineClick(ActionEvent actionEvent) {
        ChatServer.onlineUser(username);
        Busy.setSelected(false);
    }

    public void BusyClick(ActionEvent actionEvent) {
        ChatServer.BusyUser(username);
        ONLINE.setSelected(false);
    }


}


