import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SimpleBrowser extends Application {
    @Override
    public void start(Stage primaryStage) {
        BrowserUI browserUI = new BrowserUI(primaryStage);
        Scene scene = new Scene(browserUI.getRoot(), 800, 600);
        primaryStage.setTitle("ZENSearch: by Arkaprava");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}