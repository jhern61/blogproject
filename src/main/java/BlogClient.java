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
        MongoDatabase database = mongoClient.getDatabase("BlogDatabase");
        MongoCollection<Document> userCollection = database.getCollection("User");

        ArrayList globalPost = new ArrayList<Post>();






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
                    //Create User.

                    System.out.println("-------Registration Page-------");
                    //activeUser.register("Player1", "password");
                    User testUser = new User();
                    System.out.print("Enter your new username: ");
                    activeUser.setUsername(scanner.next());
                    System.out.print("\nNew password: ");
                    activeUser.setPassword(scanner.next());
                    activeUser.setMyPosts(null);

                    //Create User.
                    Document user = new Document("userName", activeUser.getUsername())
                            .append("password", activeUser.getPassword())
                            .append("posts", "null"); //getMyPosts

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

                    //System.out.println(activeUser.getMyPosts());
                    break;

                case 5:
                    System.out.println("Hi");
                    break;
                case 6:
                    System.out.println("leaving");
                    break;


                default:
                    System.out.println("invalid command");

            }
        }


    }// end //while loop

    public static void loginMenu() {
        System.out.println("\n----------Welcome----------------\n" +
                "\n1 - Login " +
                "\n2 - Register" +
                "\n3 - Create Post " +
                "\n4 - My Post" +
                "\n5 - Global Posts" +
                "\n6 - Exit");


    }









}





