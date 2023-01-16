package com.example.chatroomapp;
import javafx.event.ActionEvent;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.Vector;

public class ChatServer {
    ServerSocket serverSocket;

    public ChatServer() throws IOException {
        serverSocket = new ServerSocket(5005);
        while (true) {
            Socket s = serverSocket.accept();
            new ChatHandler(s);

        }
    }
    public static Boolean Login(String userName, String Pass){
        return DBUtils.loginUser(userName,Pass);
    }
    public static void Logout(String userName){
         DBUtils.Logout(userName);
    }
    public static void SignUp(String FirstName,String LastName,String userName,String Password){
        DBUtils.signUp(FirstName, LastName, userName, Password);
    }
    public static void onlineUser(String userName){
        DBUtils.onlineUser(userName);
    }
    public static void BusyUser (String userName){
        DBUtils.BusyUser(userName);
    }
    public static void changScene(ActionEvent event, String fxmlFile, String title, String userName){
        DBUtils.changScene(event,fxmlFile,title,userName);
    }

    public static void main(String[] args) throws IOException {
        new ChatServer();
    }

}

class ChatHandler extends Thread {
    DataInputStream dis;
    PrintStream ps;
    static Vector<ChatHandler> clientsVector = new Vector<ChatHandler>();

    public ChatHandler(Socket cs) throws IOException {

        dis = new DataInputStream(cs.getInputStream());
        ps = new PrintStream(cs.getOutputStream());
        clientsVector.add(this);
        start();

    }
    public void run() {

        while (true) {
            try {
                String str = dis.readLine();
                sendMessageToAll(str);
            } catch (IOException ignored) {
            }
        }
    }

    void sendMessageToAll(String msg) {
        for (ChatHandler ch : clientsVector) {
            ch.ps.println(msg);
        }
    }
}
