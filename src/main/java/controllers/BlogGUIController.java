package controllers;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.bson.Document;


public class BlogGUIController {

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;


    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    MongoClient mongoClient = new MongoClient("localhost", 27017);
    //Connect to database.
    MongoDatabase database = mongoClient.getDatabase("BlogDatabase");

    MongoCollection<Document> userCollection = database.getCollection("User");

    @FXML
    void login(ActionEvent event) {
        System.out.println("Login button was pressed");

    }


    @FXML
    void register(ActionEvent event) {

        System.out.println(usernameField.getText() + passwordField.getText());


    }





}
