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
 * @author Que
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

public class BlogGUI extends javax.swing.JFrame {

    private User activeUser = new User();
    
    //Enable MongoDB logging.
        Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
      
        //Database Connection to Atlas
        MongoClientURI uri = new MongoClientURI(
                "mongodb://joe:money100@cluster0-shard-00-00-shmom.mongodb.net:27017," +
                        "cluster0-shard-00-01-shmom.mongodb.net:27017,cluster0-shard-00-02-shmom.mongodb.net:27017/" +
                        "test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true");

        MongoClient mongoClient = new MongoClient(uri);
    
    //Connect to database.
    MongoDatabase database = mongoClient.getDatabase("BlogDatabase");

    ArrayList globalPost = new ArrayList<Post>();

    MongoCollection<Document> userCollection = database.getCollection("User");
    MongoCollection<Document> postCollection = database.getCollection("Blog");

    /**
     * Creates new form BlogGUI
     */
    public BlogGUI() {
        initComponents();
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

        tagsButton.setText("Tags");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(logOutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(logInButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(registerButton, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                        .addComponent(tagsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(clearButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(18, Short.MAX_VALUE))
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
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(tagsButton)
                                                .addGap(53, 53, 53)
                                                .addComponent(clearButton))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed

// TODO add your handling code here:
        String userName = JOptionPane.showInputDialog("Enter a user name: ");
        activeUser.setUsername(userName);
        String password = JOptionPane.showInputDialog("Enter a password: ");
        activeUser.setPassword(password);

        //register user to database
        activeUser.register(activeUser.getUsername(), activeUser.getPassword());

        //Create User.
        Document newUser = new Document("userName", activeUser.getUsername())
                .append("password", activeUser.getPassword());

        /* Insert object into collection. */
        userCollection.insertOne(newUser);

        //output successful completion of registration notification
        JOptionPane.showMessageDialog(null, "User sucessfully registered! Please Log in");

    }//GEN-LAST:event_registerButtonActionPerformed

    private void logInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logInButtonActionPerformed
        // TODO add your handling code here:
        String login = JOptionPane.showInputDialog("Enter a user name: ");
        String loginPassword = JOptionPane.showInputDialog("Enter a password: ");

        try {
            //check to veryify the username is correct
            if (activeUser.getUsername().equalsIgnoreCase(login)) {

                //check to verify password is correct
                if (activeUser.getPassword().equals(loginPassword)) {

                    int selection;
                    String menu = JOptionPane.showInputDialog("\nMENU"
                            + "\n1.Create New Post"
                            + "\n2.Search by tags"
                            + "\n3.View all Posts"
                            + "\n4.View user wall\n\n");
                    selection = Integer.parseInt(menu);

                    switch (selection) {
                        case 1://create post

                            ArrayList tagList = new ArrayList<String>();
                            ArrayList commentList = new ArrayList<String>();

                            String title = JOptionPane.showInputDialog("Enter title of post: ");

                            String body = JOptionPane.showInputDialog("Write post body: ");

                            //add tags
                            do {

                                String tags = JOptionPane.showInputDialog("Do you want to add tags to your post? (y/n)");
                                if (tags.startsWith("y")) {
                                    String tag2 = JOptionPane.showInputDialog("Enter tag: ");
                                    tagList.add(tag2);
                                } else {
                                    menu = JOptionPane.showInputDialog("\nMENU"
                                            + "\n1.Create New Post"
                                            + "\n2.Search by tags"
                                            + "\n3.View all Posts"
                                            + "\n4.View user wall\n\n");
                                    selection = Integer.parseInt(menu);
                                    activeUser.createPost(title, activeUser.getUsername(), body,
                                            "4-55-2019", 0, commentList, tagList);

                                }
                                break;
                            } while (true);

                        case 2://search by tags

                            break;
                        case 3://View all posts in Database
                            
                            //find all documents in collection
                            MongoCursor<Document> cursor = userCollection.find().iterator();
                            try {
                                while (cursor.hasNext()) {
                                    textArea.append(cursor.next().toString()+ "\n");
                                    
                                }
                            } finally {
                                cursor.close();
                            }

                            break;
                        case 4://View User wall
                            textArea.append(activeUser.getMyPosts());

                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Sorry wrong input");

                    }//end switch statement           
                }//end password if statement
            }//end username if statement

        } catch (Exception e) {

            if (!login.equals(activeUser.getUsername())) {
                JOptionPane.showMessageDialog(null, "Sorry username is incorrect");
            }
            if (!loginPassword.equals(activeUser.getPassword())) {
                JOptionPane.showMessageDialog(null, "Sorry password is incorrect");
            }
        }

    }//GEN-LAST:event_logInButtonActionPerformed

    private void logOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutButtonActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "You have been logged out and the system will now close, GOOD BYE!");
        System.exit(0);

    }//GEN-LAST:event_logOutButtonActionPerformed

    private void tagsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tagsButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tagsButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        // TODO add your handling code here:
        textArea.setText("");
    }//GEN-LAST:event_clearButtonActionPerformed

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
    private javax.swing.JButton registerButton;
    private javax.swing.JButton tagsButton;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables
}
