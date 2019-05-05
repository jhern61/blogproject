
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

import java.util.Scanner;

public class ConsoleClient {

    private static Scanner scanner = new Scanner(System.in);
    static ArrayList globalPost = new ArrayList<Post>();
    static ArrayList globalUsers = new ArrayList<User>();
    static User activeUser = new User("test", "password");
    static int index = 0;


    public static void main(String[] args) {


        //Enable MongoDB logging.
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
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


        //Variables
        boolean flag = true;


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

                //Print all posts
                case 5:

                    //Press 'a' to go back 'd' to go next
                    loadFromPostCollection(postCollection);

                    boolean viewFlag = true;
                    while (viewFlag) {

                        System.out.println(globalPost.get(0).toString());
                        //Get each post from global list
                        for (int i = 0; i <globalPost.size() ; i++) {
                           Post post = new Post();
                           post = globalPost.get(i);

                        }

                        viewPostMenu();
                        int menuCommand = scanner.nextInt();
                        switch (menuCommand) {
                            //Like Post
                            case 1:


                                //Show menu
                                viewPostMenu();
                                System.out.print("\n Enter Command: ");
                                menuCommand = scanner.nextInt();
                                break;

                            //Add comment to post
                            case 2:


                                System.out.print("\nEnter comment: ");
                                String comment = scanner.next();


                                break;

                            //Next Post
                            case 3:
                                System.out.println(globalPost.get(index).toString());
                                index++;
                                break;

                            //Last Post
                            case 4:
                                System.out.println(globalPost.get(index).toString());
                                index--;
                                break;

                            //Exit
                            case 5:

                                System.out.println("LEAVING GLOBAL POSTS....");
                                viewFlag = false;
                                break;

                        }


                    }


                    //Show login menu
                    loginMenu();
                    System.out.print("\nEnter command: ");
                    userCommand = scanner.nextInt();
                    break;


                case 6:

                    ArrayList<Post> tagPosts = new ArrayList<Post>();
                    // tagPosts =  postWithTag(postCollection, "food");

                    for (int i = 0; i < tagPosts.size(); i++) {
                        System.out.println(tagPosts.get(i).toString());
                    }

                    break;


                case 10:

                    //System.out.println(activeUser.loadFromDatabase());
                    //loadFromUserCollection(userCollection);
                    //loadFromPostCollection(postCollection);
                    findUser(userCollection, "Dude9FIKA0", "gGgsZqwUyxrEXr/95i+4H9nDPbVyyUKL");

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
                "\n6 - Exit");
    }


    public static void viewPostMenu() {
        System.out.println("---------- Post Options ----------------\n" +
                "\n1 - Like " +
                "\n2 - Comment" +
                "\n3 - Next post " +
                "\n4 - Last post  " +
                "\n5 - Exit Global posts");
    }


//    public static void loadFromPostCollection(MongoCollection postCollection) {
//        MongoCursor<Document> cursor = postCollection.find().iterator();
//        try {
//            while (cursor.hasNext()) {
//                Document myObj = cursor.next();
//                String myTitle = (String) myObj.get("title");
//                String myAuthor = (String) myObj.get("author");
//                String myBody = (String) myObj.get("postBody");
//                String myDate = (String) myObj.get("postDate");
//                Object myViews = myObj.get("views");
//                ArrayList myComments = (ArrayList) myObj.get("comments");
//                ArrayList myTags = (ArrayList) myObj.get("tags");
//
//                Post post = new Post(myTitle, myAuthor, myBody, myDate, (Integer) myViews, myComments, myTags);
//
//                //Insert post from database into a list so we can access post
//                globalPost.add(post);
//
//                // System.out.println(post.toString());
//            }
//        } finally {
//            cursor.close();
//        }
//}

    public static void loadFromPostCollection(MongoCollection postCollection) {
        MongoCursor<Document> cursor = postCollection.find().iterator();
        try {
            while (cursor.hasNext()) {
                Document myObj = cursor.next();
                String myTitle = (String) myObj.get("title");
                String myAuthor = (String) myObj.get("author");
                String myBody = (String) myObj.get("postBody");
                String myDate = (String) myObj.get("postDate");
                int myViews = (Integer) myObj.get("views");
                ArrayList myComments = (ArrayList) myObj.get("comments");
                ArrayList myTags = (ArrayList) myObj.get("tags");

                Post post = new Post(myTitle, myAuthor, myBody, myDate, (Integer) myViews, myComments, myTags);

                //Insert post from database into a list so we can access post
                globalPost.add(post);

                // System.out.println(post.toString());
            }
        } finally {
            cursor.close();
        }

    }

    public static String findUser(MongoCollection userCollection, String login, String loginPassword) {
        MongoCursor<Document> cursor = userCollection.find().iterator();

        try {
            while (cursor.hasNext()) {
                Document myObj = cursor.next();
                String username = (String) myObj.get("username");
                String password = (String) myObj.get("password");
                if (username.equalsIgnoreCase(login) && password.equalsIgnoreCase(loginPassword)) {
                    System.out.print("Valid userName");
                    System.out.println("Valid password");
                }

            }
        } catch (NullPointerException e) {
            System.out.print("");

        } finally {
            cursor.close();

        }
        return login;

    }

//    public static ArrayList<Post> postWithTag(MongoCollection postCollection, String tag){
//        loadFromPostCollection(postCollection);
//        ArrayList<Post> tagPosts = new ArrayList<Post>();
//
//        for (int i = 0; i <globalPost.size() ; i++) {
//            if(globalPost.get(i).toString() == tag.toString()) {
//                System.out.println(globalPost.get(i).toString());
//                tagPosts.add(globalPost.add(i));
//            }
//        }
//        return
//    }


    public static void loadFromUserCollection(MongoCollection userCollection) {
        MongoCursor<Document> cursor = userCollection.find().iterator();
        try {
            while (cursor.hasNext()) {
                Document myObj = cursor.next();
                String username = (String) myObj.get("username");
                String password = (String) myObj.get("password");
                ArrayList posts = (ArrayList) myObj.get("posts");

                User loadedUser = new User(username, password, posts);

                //Insert user from database into a list so we can access users
                globalUsers.add(loadedUser);

                System.out.println(loadedUser.toString());
            }
        } finally {
            cursor.close();
        }
    }


}//end class