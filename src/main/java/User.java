import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

public class User {

    //MongoDB stuff
    MongoClient mongoClient = new MongoClient("localhost", 27017);
    //Connect to database.
    MongoDatabase database = mongoClient.getDatabase("BlogDatabase");
    MongoCollection<Document> postCollection = database.getCollection("Blog");

    String username;
    String password;

    ArrayList<Post> myPosts = new ArrayList<Post>();


    public User(String username, String password) {
        this.password = password;
        this.username = username;
    }

    public User(){};



    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMyPosts(ArrayList<Post> myPosts) {
        this.myPosts = myPosts;
    }




    public Post getMyPosts() {
        Post post = new Post();
        for (int i = 0; i < myPosts.size() ; i++) {
            System.out.println(myPosts.get(i));
                post = myPosts.get(i);
        }
        return post;
    }




    //String title, String author, String postBody, String postDate, int views, ArrayList comments, ArrayList tags
    public Post createPost(String title, String author, String postBody, String postDate, int views, ArrayList comments, ArrayList tags) {

        Post post = new Post(title, author, postBody, postDate, 0, comments,  tags);





        //Create object.
//        Document newPost = new Document("title", title)
//                                .append("author", author)
//                                .append("postBody", postBody)
//                                .append("postDate", postDate)
//                                .append("views", 0)
//                                .append("comments", "null")
//                                .append("tags", "null");

        Document newPost = new Document("title", post.title)
                .append("author", post.author)
                .append("postBody", post.postBody)
                .append("postDate", post.postDate)
                .append("views", 0)
                .append("comments", "null")
                .append("tags", "null");

        postCollection.insertOne(newPost);
        myPosts.add(post);
        return  post;
    }


    public void likePost(String postTitle){

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
