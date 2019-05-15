package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {
    private Stage window;
    private Pane MScreen;
    private Pane SScreen;

    @Override
    public void start(Stage primaryScene) throws Exception{
        MScreen = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        SScreen = FXMLLoader.load(getClass().getResource("SettingsScreen.fxml"));

        window = primaryScene;

        window.setScene(new Scene(MScreen, 1080, 1920));
        window.setTitle("Main Screen");
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}