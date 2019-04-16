
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

import java.util.Scanner;

public class ConsoleClient {

    private static Scanner scanner = new Scanner(System.in);
    ArrayList globalPost = new ArrayList<Post>();


    public static void main(String[] args) {

        //Enable MongoDB logging.
        Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
        mongoLogger.setLevel(Level.SEVERE); // e.g. or Log.WARNING, etc.
        //Database Connection to Atlas
        MongoClientURI uri = new MongoClientURI(
                "mongodb://joe:money100@cluster0-shard-00-00-shmom.mongodb.net:27017," +
                        "cluster0-shard-00-01-shmom.mongodb.net:27017,cluster0-shard-00-02-shmom.mongodb.net:27017/" +
                        "test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true");

        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("BlogDatabase");
        MongoCollection<Document> postCollection = database.getCollection("Blog");
        MongoCollection<Document> userCollection = database.getCollection("User");





        //oadfromDatabase(postCollection);



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
                System.out.println("\nWelcome, " + activeUser.getUsername());


                //Show login menu
                loginMenu();
                System.out.print("\nEnter command: ");
                userCommand = scanner.nextInt();



                break;

            //Register
            case 2:

                //Go to homePage
                System.out.println("\n ----- Registration Menu -----");
                System.out.print("\nCreate Username: ");
                String username = scanner.next();

                System.out.print("Create Password: ");
                String password = scanner.next();

                activeUser.register(username, password);

                System.out.println("\nAccount Created!");

                //Show login menu
                loginMenu();
                System.out.print("\nEnter command: ");
                userCommand = scanner.nextInt();



                break;

            //Create post
            case 3:

                System.out.print("\nEnter title: ");
                String title = scanner.next();

                System.out.print("\nEnter body: ");
                String body = scanner.next();

                //add tags
                System.out.print("\nAdd tags");


                ArrayList tagList = new ArrayList<String>();
                ArrayList commentList = new ArrayList<String>();

                do {

                    System.out.println("Do you want to add tags to your post? (y/n)");
                    if (scanner.next().startsWith("y")) {
                        System.out.println("Enter tag: ");
                        tagList.add(scanner.next());
                    } else {
                        break;
                    }
                } while (true);

                commentList.add("this is a comment");


                activeUser.createPost(title, activeUser.getUsername(), body, "null", 0, commentList, tagList);

                //Show login menu
                loginMenu();
                System.out.print("\nEnter command: ");
                userCommand = scanner.nextInt();

                break;

            //Print my posts
            case 4:

                System.out.println(activeUser.getMyPosts());

                //Show login menu
                loginMenu();
                System.out.print("\nEnter command: ");
                userCommand = scanner.nextInt();

                break;

            case 10:

                //System.out.println(activeUser.loadFromDatabase());

                //Show login menu
                loginMenu();
                System.out.print("\nEnter command: ");
                userCommand = scanner.nextInt();

                break;

            default:
                System.out.println("Invalid command");

        }
    }


    }//end while loop

    public static void loginMenu() {
        System.out.println("\n----------Welcome----------------\n" +
                "\n1 - Login " +
                "\n2 - Register" +
                "\n3 - Create Post " +
                "\n4 - My Post" +
                "\n5 - Global Posts" +
                "\n6 - Exit" );



    }



    public static void alreadyLoggedInMenu() {
        System.out.println("\n----------Welcome ----------------\n" +
                "\n3 - Create " +
                "\n4 - My Posts" +
                "\n5 - Global Post " +
                "\n6 - Exit" );
    }

    public ArrayList<Post> loadFromDatabase(MongoDatabase database, MongoCollection postCollection) {
        ArrayList<Post> loadedPost = new ArrayList<Post>();


//        //Print all.
//        for (Document cur : postCollection.find()) {
//            System.out.println(cur.toJson());
//                String post = database.getCollection("Blog");
//                globalPost.add(post);
//        }
//        for (int i = 0; i <loadedPost.size() ; i++) {
//            loadedPost.get(i);
//        }
        return loadedPost;
    }




}





