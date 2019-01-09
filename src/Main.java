import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane root = FXMLLoader.load(getClass().getResource("/login/login.fxml"));
        primaryStage.setTitle("Madrasti System");
        primaryStage.setScene(new Scene(root, 960, 640));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
        System.out.println("Let Game Starts!");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
