
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Scanner;

public class BlogClient {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        MongoClient mongoClient = new MongoClient("localhost", 27017);
        //Connect to database.
        MongoDatabase database = mongoClient.getDatabase("BlogDatabase");


        MongoCollection<Document> userCollection = database.getCollection("User");
        MongoCollection<Document> postCollection = database.getCollection("Post");




        // Delete Car
        postCollection.deleteOne(new Document("model", "iPhone X"));




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
                    System.out.println("-------Registration Page-------");
                    System.out.print("Enter your new username: ");
                    activeUser.setUsername(scanner.next());
                    System.out.print("\nNew password: ");
                    activeUser.setPassword(scanner.next());
                    activeUser.setMyPosts(null);

                    //Create User.
                    Document user = new Document("userName", activeUser.getUsername())
                            .append("password", activeUser.getPassword())
                            .append("posts", activeUser.getMyPosts());

                    /* Insert object into collection. */
                    userCollection.insertOne(user);

                    //Go to homePage



                    break;
                default:
                    System.out.println("invalid command");

            }
        }



    }

    public static void loginMenu(){
        System.out.println("\n----------Welcome----------------\n" +
                "\n1 - Login " +
                "\n2 - Register");

    }

    public static void login(String userName, String password){

    }











}





