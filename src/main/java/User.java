
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;

public class User {

    //Database Connection to Atlas
    MongoClientURI uri = new MongoClientURI(
            "mongodb://<username>:<password>@cluster0-shard-00-00-shmom.mongodb.net:27017," +
                    "cluster0-shard-00-01-shmom.mongodb.net:27017,cluster0-shard-00-02-shmom.mongodb.net:27017/" +
                    "test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true");

    MongoClient mongoClient = new MongoClient(uri);
    MongoDatabase database = mongoClient.getDatabase("BlogDatabase");
    MongoCollection<Document> postCollection = database.getCollection("Blog");
    MongoCollection<Document> userCollection = database.getCollection("User");

    String username;
    String password;

    ArrayList<Post> myPosts = new ArrayList<Post>();

    public User(String username, String password) {
        this.password = password;
        this.username = username;
    }

    public User(String username, String password, ArrayList post) {
        this.password = password;
        this.username = username;
    }

    public User() {
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }

    public Post createPost(String title, String author, String postBody, String postDate, int views, int likes, ArrayList comments, ArrayList tags) {
        Date date = new Date();
        Post post = new Post(title, author, postBody, date.toString(), 0, 0, comments, tags);

        //Create MongoDB document with user information.
        Document newPost = new Document("title", post.title)
                .append("author", post.author)
                .append("postBody", post.postBody)
                .append("postDate", date.toString())
                .append("views", 0)
                .append("likes",0)
                .append("comments", comments)
                .append("tags", tags);

        //Insert into mongoDB named PostCollection
        postCollection.insertOne(newPost);
        //Add to users posts
        myPosts.add(post);
        //Return created post.
        return post;
    }

    public String getMyPosts() {
        Post post = new Post();

        if (myPosts.isEmpty()) {
            System.out.println("NO posts from this user");
        } else
            for (int i = 0; i < myPosts.size(); i++) {
                System.out.println(myPosts.get(i));
                post = myPosts.get(i);
            }
        return post.toString();
    }


    public void register(String username, String password) {
        Document newUser = new Document("username", username)
                .append("password", password)
                .append("posts", myPosts);

        userCollection.insertOne(newUser);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", myPosts=" + myPosts +
                '}';
    }


}
