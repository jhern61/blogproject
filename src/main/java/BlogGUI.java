/*
 * CS485 MongoDB Project
 * Description: A GUI implementation of a database project of a online blog website. 
 * It allows users to register log in, (create, edit, and delete posts), view tags, and user walls. 
 * Author: Amel Nukic & Joe Hernandez
 * Date: 4/10/2019
Hi guys, this is your requirements for the blog/forum:
Allow comments
Users registration (registration/login)
Make new posts
Track post views
Users able to “like” posts
Posts tags – categories
Users “walls”
 */

/**
 *
 * @author bk8355tn
 */
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.MongoClientURI;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import com.mongodb.Block;
import com.mongodb.util.JSON;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.conversions.Bson;

public class BlogGUI extends javax.swing.JFrame {

    private User activeUser = new User();
    static ArrayList globalPost = new ArrayList<Post>();
    static ArrayList globalUsers = new ArrayList<User>();
    static ArrayList globalTags = new ArrayList<Post>();
    static ArrayList<Post> myPosts = new ArrayList<Post>();
    //Enable MongoDB logging.
    static final Logger MONGOLOGGER = Logger.getLogger("org.mongodb.driver");

    //Database Connection to Atlas
    MongoClientURI uri = new MongoClientURI(
            "mongodb://joe:money100@cluster0-shard-00-00-shmom.mongodb.net:27017,"
            + "cluster0-shard-00-01-shmom.mongodb.net:27017,cluster0-shard-00-02-shmom.mongodb.net:27017/"
            + "test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true");

    MongoClient mongoClient = new MongoClient(uri);

    //Connect to database.
    MongoDatabase database = mongoClient.getDatabase("BlogDatabase");

    MongoCollection<Document> userCollection = database.getCollection("User");
    MongoCollection<Document> postCollection = database.getCollection("Blog");
    

    /**
     * Creates new form BlogGUI
     */
    public BlogGUI() {
        initComponents();
        logOutButton.setEnabled(false);
        tagsButton.setEnabled(false);
        menuButton.setEnabled(false);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        registerButton = new javax.swing.JButton();
        logInButton = new javax.swing.JButton();
        logOutButton = new javax.swing.JButton();
        tagsButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        menuButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        registerButton.setText("Register");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        logInButton.setText("Log in");
        logInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logInButtonActionPerformed(evt);
            }
        });

        logOutButton.setText("Log out");
        logOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutButtonActionPerformed(evt);
            }
        });

        tagsButton.setText("Search Tags");
        tagsButton.setToolTipText("");
        tagsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tagsButtonActionPerformed(evt);
            }
        });

        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel1.setText("Mongo Blog");

        menuButton.setText("Main Menu");
        menuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(logOutButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logInButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(registerButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tagsButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clearButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(menuButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(203, 203, 203))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(registerButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(logInButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(logOutButton)
                        .addGap(34, 34, 34)
                        .addComponent(menuButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tagsButton)
                        .addGap(18, 18, 18)
                        .addComponent(clearButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed

// TODO add your handling code here:
        String userName = JOptionPane.showInputDialog("Enter a user name: ");
        String password = JOptionPane.showInputDialog("Enter a password: ");
        
        //encrypt password/////////////////////
        ///////////////////////
        /////////////////////

        activeUser.register(userName, password);
       
        
        
        //output successful completion of registration notification
        JOptionPane.showMessageDialog(null, "User sucessfully registered! Please Log in");

    }//GEN-LAST:event_registerButtonActionPerformed

    private void logInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logInButtonActionPerformed
        // TODO add your handling code here:
        
        
        //decrypt password////////////////////
        //////////////////////////
        ///////////////
        try {
            String username = JOptionPane.showInputDialog("Enter a user name: ");
            String password = JOptionPane.showInputDialog("Enter a password: ");
            
                    
                    
             if (findUser(userCollection, username, password) == true) {

                activeUser.setUsername(username);
                logOutButton.setEnabled(true);
                tagsButton.setEnabled(true);
                menuButton.setEnabled(true);

                JOptionPane.showMessageDialog(null, "Sucessfuly Logged in");
            }//end if 

            if (findUser(userCollection, username, password) == false) {
                JOptionPane.showMessageDialog(null, "Wrong Username or Password");
            }

        } catch (Exception e) {

        }

    }//GEN-LAST:event_logInButtonActionPerformed

    private void logOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutButtonActionPerformed
        // TODO add your handling code here:
        globalPost.clear();
        JOptionPane.showMessageDialog(null, "You have been logged out and the system will now close, GOOD BYE!");
        System.exit(0);


    }//GEN-LAST:event_logOutButtonActionPerformed

    private void tagsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tagsButtonActionPerformed
         // TODO add your handling code here:
        String tagSearch = JOptionPane.showInputDialog("Enter tag name");

        //method to retreive tags from database
        postTags(postCollection, tagSearch);
    }//GEN-LAST:event_tagsButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        // TODO add your handling code here:
        textArea.setText("");
    }//GEN-LAST:event_clearButtonActionPerformed

    private void menuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuButtonActionPerformed
        // TODO add your handling code here:
        loadFromPostCollection(postCollection);
        try {
            findUser(userCollection, activeUser.getUsername(), activeUser.getPassword());
            String menu;
            int selection;

            menu = JOptionPane.showInputDialog("\nMENU"
                    + "\n1.Create New Post"
                    + "\n2.View all Posts"
                    + "\n3.View user wall\n\n");
            selection = Integer.parseInt(menu);

            boolean flag = true;
            while (flag) {
                switch (selection) {
                    case 1://create post

                        ArrayList tagList = new ArrayList<String>();
                        ArrayList commentList = new ArrayList<String>();

                        String title = JOptionPane.showInputDialog("Enter title of post: ");

                        String body = JOptionPane.showInputDialog("Write post body: ");

                        do {
                            String tags = JOptionPane.showInputDialog("Do you want to add tags to your post? (y/n)");

                            //add tags
                            if (tags.startsWith("y")) {
                                String tag2 = JOptionPane.showInputDialog("Enter tag: ");
                                tagList.add(tag2);
                            } else {
                                break;
                            }

                        } while (true);

                        Post newPost = activeUser.createPost(title, activeUser.getUsername(), body,
                                "4-55-2019", 0, 0, commentList, tagList);
                        
                        
                        myPosts.add(newPost);

                        break;

                    case 2://View all posts in Database
                        
                        int index = 0;
                        
                        textArea.append(globalPost.get(index).toString());  
                        Post post = (Post) globalPost.get(index);

                        boolean viewMenuFlag = true;
                        while (viewMenuFlag) {
                            
                            int postMenuSelection;
                            String postMenu = JOptionPane.showInputDialog("---------- Post Options ----------------\n"
                                    + "\n1 - Like "
                                    + "\n2 - Comment"
                                    + "\n3 - Next post "
                                    + "\n4 - Last post  "
                                    + "\n5 - Exit Global posts");
                            postMenuSelection = Integer.parseInt(postMenu);

                            switch (postMenuSelection) {
                                
                                case 1://Like Post

                                    post.likePost();
                                    textArea.append(globalPost.get(index).toString());
                                    //Update like
                                    Bson filter = new Document("title", post.getTitle());
                                    Bson newValue = new Document("likes", post.getLikes());
                                    Bson updateOperationDocument = new Document("$set", newValue);
                                    postCollection.updateOne(filter, updateOperationDocument);
                                    break;

                                
                                case 2://Add comment to post

                                    //Add comment
                                    String comment = JOptionPane.showInputDialog("\nEnter comment: ");

                                    post.addComment(comment);
                                    textArea.append(globalPost.get(index).toString());

                                    //Update comment
                                    filter = new Document("title", post.getTitle());
                                    newValue = new Document("comments", post.getComments());
                                    updateOperationDocument = new Document("$set", newValue);
                                    postCollection.updateOne(filter, updateOperationDocument);

                                    break;

                                
                                case 3://Next Post
                                    
                                    textArea.setText("");
                                    textArea.append(globalPost.get(index).toString());
                                    post.viewPost();
                                    index++;
                                    
                                    break;

                                
                                case 4://Last Post
                                    
                                    textArea.setText("");
                                    textArea.append(globalPost.get(index).toString());
                                    post.viewPost();
                                    index--;
                                    break;

                                
                                case 5://Exit
         
                                    viewMenuFlag = false;
                                    flag = false;
                                    textArea.setText("");
                                    
                                    break;
                                    
                                    
                            }

                        }

                        break;

                    case 3://View User wall
                        
                        
                        
                        String user = activeUser.getUsername();
                         //System.out.println(user);
                        for(int i = 0; i<globalPost.size(); i++) {
                            Post myPost = (Post) globalPost.get(i);
                            if(user.equalsIgnoreCase(myPost.author)) {
                                System.out.println(myPost.toString());
                                textArea.setText("\n"+ myPost + "\n");
                            }
                            
                        }
                      
                        
                        break;
                        
                        
                        
                    case 4: 
                        
                        loadFromPostCollection(postCollection);
                        for(int i = 0; i<globalPost.size(); i++) {
                           Post quePost = (Post) globalPost.get(i);
                           System.out.println(quePost.toString());
                          
                        }
                        break;
                     default:
                        JOptionPane.showMessageDialog(null, "Sorry wrong input");

                }//end switch statement 

            }

        } catch (Exception e) {

        }
    }//GEN-LAST:event_menuButtonActionPerformed

    //Method to get all posts from all users
    public void loadFromPostCollection(MongoCollection postCollection) {
        MongoCursor<Document> cursor = postCollection.find().iterator();
        try {
            while (cursor.hasNext()) {
                Document myObj = cursor.next();
                String myTitle = (String) myObj.get("title");
                String myAuthor = (String) myObj.get("author");
                String myBody = (String) myObj.get("postBody");
                String myDate = (String) myObj.get("postDate");
                int myViews = (Integer) myObj.get("views");
                int myLikes = (Integer) myObj.get("likes");
                ArrayList myComments = (ArrayList) myObj.get("comments");
                ArrayList myTags = (ArrayList) myObj.get("tags");

                Post post = new Post(myTitle, myAuthor, myBody, myDate, myViews, myLikes, myComments, myTags);

                //add post to global arrayist containing all posts 
                globalPost.add(post);
            }
        } finally {
            cursor.close();
        }
    }
    
    
    //method to find users in database for log in button
    public static boolean findUser(MongoCollection userCollection, String login, String loginPassword) {
        MongoCursor<Document> cursor = userCollection.find().iterator();

        try {
            while (cursor.hasNext()) {
                Document myObj = cursor.next();
                String myUsername = (String) myObj.get("username");
                String myPassword = (String) myObj.get("password");
                
                if (myUsername.equalsIgnoreCase(login) && myPassword.equalsIgnoreCase(loginPassword)) {
                    return true;
                }
            }
        } catch (NullPointerException e) {
            System.out.print("");

        } finally {
            cursor.close();

        }
        return false;

    }
     

    //method to search posts by tags
    public void postTags(MongoCollection postCollection, String tag) {
        MongoCursor<Document> cursor = postCollection.find().iterator();

        try {
            while (cursor.hasNext()) {
                Document myObj = cursor.next();
                String myTitle = (String) myObj.get("title");
                String myAuthor = (String) myObj.get("author");
                String myBody = (String) myObj.get("postBody");
                String myDate = (String) myObj.get("postDate");
                int myViews = (Integer) myObj.get("views");
                int myLikes = (Integer) myObj.get("likes");
                ArrayList myComments = (ArrayList) myObj.get("comments");
                ArrayList myTags = (ArrayList) myObj.get("tags");

                Post post = new Post(myTitle, myAuthor, myBody, myDate, myViews, myLikes, myComments, myTags);
                //Insert post from database into a list so we can access post

                //add post to global arrayist containing all posts 
                globalPost.add(post);

                for (int i = 0; i < globalPost.size(); i++) {

                    for (int x = 0; x < myTags.size(); x++) {
                        if (myTags.contains(tag)) {
                            textArea.append(post.toString());
                        }
                    }//end inner for loop
                }//end for loop
            }
        } catch (Exception e) {
        }

        /*loadFromPostCollection(postCollection);
        for(int i=0; i<globalPost.size(); i++){
            Object post = globalPost.get(i);
            Post temp = (Post) post;
            if(temp.tags.contains(tag)){
                textArea.append(temp.tags.toString());
            }
        }*/
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BlogGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logInButton;
    private javax.swing.JButton logOutButton;
    private javax.swing.JButton menuButton;
    private javax.swing.JButton registerButton;
    private javax.swing.JButton tagsButton;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables
}
