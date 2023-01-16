package com.example.chatroomapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.sql.*;
import java.io.IOException;
import java.util.Objects;

public class DBUtils {

    public static void changScene(ActionEvent event, String fxmlFile, String title, String userName) {
        Parent root = null;
        if (userName != null) {
            try {
                FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
                root = loader.load();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                System.out.println(DBUtils.class.getResource(fxmlFile));
                root = FXMLLoader.load(Objects.requireNonNull(DBUtils.class.getResource(fxmlFile)));
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);

        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void signUp( String FirstName, String LastName, String username, String password) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExist = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/contact", "root", "nourramadan");
            psCheckUserExist = connection.prepareStatement("SELECT * FROM Contact WHERE username = ?");
            psCheckUserExist.setString(1, username);
            resultSet = psCheckUserExist.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("User already exist");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("you cannot use this username");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO Contact values ( ? , ? , ? , ? , default)");
                psInsert.setString(1, FirstName);
                psInsert.setString(2, LastName);
                psInsert.setString(3, username);
                psInsert.setString(4, password);
                psInsert.executeUpdate();
                System.out.println("Inserted");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static Boolean loginUser( String username, String password) {
        Boolean a=true;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/contact", "root", "nourramadan");
            preparedStatement = connection.prepareStatement("SELECT password, FirstName, LastName FROM Contact WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                System.out.println("user not found in the database!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
                a= false;
            } else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    String retrievedFirstName = resultSet.getString("FirstName");
                    String retrievedLastName = resultSet.getString("LastName");
                    if (retrievedPassword.equals(password)) {
                        preparedStatement = connection.prepareStatement("update Contact set Status = ? where username = ? ");
                        preparedStatement.setString(1, "ONLINE");
                        preparedStatement.setString(2, username);
                        preparedStatement.executeUpdate();
                        a= true;

                    } else {
                        System.out.println("password didn't match!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided credentials are incorrect!");
                        alert.show();
                        a= false;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return a;
    }

    public static void Logout(String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/contact", "root", "nourramadan");
            preparedStatement = connection.prepareStatement("update Contact set Status = ? where username = ? ");
            preparedStatement.setString(1, "OFFLINE");
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void onlineUser( String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/contact", "root", "nourramadan");
            preparedStatement = connection.prepareStatement("update Contact set Status = ? where username = ? ");
            preparedStatement.setString(1, "ONLINE");
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void BusyUser( String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/contact", "root", "nourramadan");
            preparedStatement = connection.prepareStatement("update Contact set Status = ? where username = ? ");
            preparedStatement.setString(1, "BUSY");
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static ResultSet getStatues (String Status) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/contact", "root", "nourramadan");
        preparedStatement = connection.prepareStatement("SELECT username FROM Contact WHERE Status = ?");
        preparedStatement.setString(1, Status);
        resultSet = preparedStatement.executeQuery();
        return resultSet;

    }
}
