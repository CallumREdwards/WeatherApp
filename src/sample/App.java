package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {
    private Stage window;
    private Pane MScreen;
    private Pane SScreen;
    private Button Settings = new Button();

    public void initializeSettingsButton(){
        Settings.setLayoutX(469.0);
        Settings.setLayoutY(56.0);
        Settings.setMnemonicParsing(false);
        Settings.setPrefWidth(71.0);
        Settings.setText("Settings");
        Settings.setOnAction(e -> changeToSettings());

        MScreen.getChildren().add(Settings);
    }

    public void changeToSettings(){
        window.setScene(new Scene(SScreen, 1080, 1920));
        window.setTitle("Settings Screen");
        window.show();
    }

    @Override
    public void start(Stage primaryScene) throws Exception{
        MScreen = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        SScreen = FXMLLoader.load(getClass().getResource("SettingsScreen.fxml"));

        window = primaryScene;

        initializeSettingsButton();

        window.setScene(new Scene(MScreen, 1080, 1920));
        window.setTitle("Main Screen");
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}