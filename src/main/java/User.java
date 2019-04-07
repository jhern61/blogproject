import java.util.ArrayList;

public class User {

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

    public ArrayList<Post> getMyPosts() {
        return myPosts;
    }

    public void createPost(String title, String author, String postBody, String postDate) {

        Post post = new Post();
        post.setTitle(title);
        post.setAuthor(author);
        post.setPostBody(postBody);
        post.setPostDate(postDate);
        post.setViews(0);
        post.setComments(null);
        post.setTags(null);

        myPosts.add(post);

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
