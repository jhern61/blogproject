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
    ArrayList comments = new ArrayList<Comment>();
    ArrayList tags = new ArrayList<String>();

    public Post(String title, String author, String postBody, String postDate, int views, ArrayList comments, ArrayList tags) {
        this.title = title;
        this.author = author;
        this.postBody = postBody;
        this.postDate = postDate;
        this.views = views;
        this.comments = comments;
        this.tags = tags;
    }

    public Post(){}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public ArrayList getComments() {
        return comments;
    }

    public void setComments(ArrayList comments) {
        this.comments = comments;
    }

    public ArrayList getTags() {
        return tags;
    }

    public void setTags(ArrayList tags) {
        this.tags = tags;
    }



    public void likePost() {
        views++;
    }

    @Override
    public String toString() {
        return "Post: " +
                "\nTitle: " + title +
                "\nAuthor: " + author +
                "\nPostBody: " + postBody +
                "\nPost Date: " + postDate +
                "\nViews: " + views +
                "\nComments: " + comments +
                "\nTags: " + tags;
    }
}
