
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Scanner;

public class BlogClient {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        MongoClient mongoClient = new MongoClient("localhost", 27017);
        //Connect to database.
        MongoDatabase database = mongoClient.getDatabase("BlogDatabase");


        ArrayList globalPost = new ArrayList<Post>();


        MongoCollection<Document> userCollection = database.getCollection("User");





        // Delete Car
        //postCollection.deleteOne(new Document("model", "iPhone X"));




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

                //Create post
                case 3:
                    //(String title, String author, String postBody, String postDate, int views, ArrayList comments, ArrayList tags)

                    System.out.print("\nEnter title: ");
                    String title = scanner.next();

                    System.out.print("\nEnter body: ");
                    String body = scanner.next();

                    // Post date
                    String postDate = "";
                    //Set view
                    int views = 0;

                    //comments

                    //add tags
                    System.out.print("\nAdd tags");

                    ArrayList tagList =  new ArrayList<String>();
                    tagList.add("test");


                    activeUser.createPost(title, activeUser.getUsername(), body, postDate, tagList);
                    activeUser.getMyPosts();

                    break;
                default:
                    System.out.println("invalid command");

            }
        }



    }

    public static void loginMenu(){
        System.out.println("\n----------Welcome----------------\n" +
                "\n1 - Login " +
                "\n2 - Register" +
                "\n3 - Create Post " +
                "\n4 - NILL " );

    }

    public static void login(String userName, String password){

    }


    public static void createPost(){

    }











}





