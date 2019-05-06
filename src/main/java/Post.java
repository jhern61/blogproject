/*
* Allow comments
Users registration (registration/login)
Make new posts
Track post views
Users able to “like” posts
Posts tags – categories
Users “walls”
*
* */
import java.util.ArrayList;

public class Post {

    String title;
    String author;
    String postBody;
    String postDate;
    int views;
    int likes;
    ArrayList comments = new ArrayList<Comment>();
    ArrayList tags = new ArrayList<String>();

    public Post(String title, String author, String postBody, String postDate, int views, int likes, ArrayList comments, ArrayList tags) {
        this.title = title;
        this.author = author;
        this.postBody = postBody;
        this.postDate = postDate;
        this.views = views;
        this.likes = likes;
        this.comments = comments;
        this.tags = tags;
    }

    public Post(){}

    public ArrayList getComments() {
        return comments;
    }

    public int getLikes(){
        return likes;
    }

    public String getTitle() {
        return title;
    }

    public int getViews() {
        return views;
    }

    public ArrayList getTags() {
        return tags;
    }

    public void viewPost() {
        views++;
    }

    public void likePost(){
        likes++;
    }


    public void addComment(String comment) {
        comments.add(comment);
    }

    @Override
    public String toString() {
        return "\n***********************************\nPost: " +
                "\nTitle: " + title +
                "\nAuthor: " + author +
                "\nPostBody: " + postBody +
                "\nPost Date: " + postDate +
                "\nViews: " + views +
                "\nLikes: " + likes +
                "\nComments: " + comments +
                "\nTags: " + tags;
    }
}
