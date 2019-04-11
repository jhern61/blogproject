
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Scanner;

public class BlogClient extends Application {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Application.launch(args);




    }





    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("BlogGUI.fxml"));

        Scene scene = new Scene(root, 500, 500);

        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();


    }
}





