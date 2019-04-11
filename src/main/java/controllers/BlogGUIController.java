package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class BlogGUIController {

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;


    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;




    @FXML
    void login(ActionEvent event) {
        System.out.println("Login button was pressed");

    }


    @FXML
    void register(ActionEvent event) {

        System.out.println(usernameField.getText() + passwordField.getText());

    }





}
