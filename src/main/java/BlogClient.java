
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Scanner;

public class BlogClient extends Application {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Application.launch(args);

        MongoClient mongoClient = new MongoClient("localhost", 27017);
        //Connect to database.
        MongoDatabase database = mongoClient.getDatabase("BlogDatabase");


        ArrayList globalPost = new ArrayList<Post>();


        MongoCollection<Document> userCollection = database.getCollection("User");
        MongoCollection<Document> postCollection = database.getCollection("Blog");



        //Variables
        boolean flag = true;
        User activeUser = new User("test", "password");

        //Show login menu
        loginMenu();
        System.out.print("\nEnter command: ");
        int userCommand = scanner.nextInt();


        while (flag) {
            switch (userCommand) {
                //Login
                case 1:

                    break;

                //Register
                case 2:
                    activeUser.register("Player1", "password");
                    //Go to homePage
                    break;

                //Create post
                case 3:
                    //(String title, String author, String postBody, String postDate, int views, ArrayList comments, ArrayList tags)

                    System.out.print("\nEnter title: ");
                    String title = scanner.next();

                    System.out.print("\nEnter body: ");
                    String body = scanner.next();

                    // Post date
                    String postDate = "";


                    //comments

                    //add tags
                    System.out.print("\nAdd tags");

                    ArrayList tagList = new ArrayList<String>();
                    ArrayList commentList = new ArrayList<String>();
                    tagList.add("test");
                    commentList.add("this is a comment");


                    activeUser.createPost(title, activeUser.getUsername(), body, "4-55-2019", 0, commentList , tagList);

                    break;

                //Print my posts
                case 4:

                    System.out.println(activeUser.getMyPosts());


                    break;
                default:
                    System.out.println("invalid command");

            }
        }


    }

    public static void loginMenu() {
        System.out.println("\n----------Welcome----------------\n" +
                "\n1 - Login " +
                "\n2 - Register" +
                "\n3 - Create Post " +
                "\n4 - My Post" +
                "\n4 - Global Posts");


    }

    public static void login(String userName, String password) {

    }


    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("BlogGUI.fxml"));

        Scene scene = new Scene(root, 500, 500);

        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();
    }
}





