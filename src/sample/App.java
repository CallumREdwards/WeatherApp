package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Calendar;
import java.util.Date;

public class App extends Application {
    private FXMLLoader MainLoader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
    private FXMLLoader SettingsLoader = new FXMLLoader(getClass().getResource("SettingsScreen.fxml"));

    private Stage window;
    private Pane MScreen;
    private Pane SScreen;

    private MainController mainC;
    private SettingsController settingsC;

    private Button Settings;

    public void initializeButtons() throws java.io.IOException{
        MScreen = MainLoader.load();
        SScreen = SettingsLoader.load();

        mainC = MainLoader.getController();
        settingsC = SettingsLoader.getController();

        Settings = mainC.Settings;
        Settings.setOnAction(e->changeToSettings());
    }

    public void changeToSettings(){
        window.setScene(new Scene(SScreen, 550, 1920));
        window.setTitle("Settings Screen");
        window.show();
    }

    @Override
    public void start(Stage primaryScene) throws Exception{
        initializeButtons();

        window = primaryScene;
        window.setScene(new Scene(MScreen, 550, 1920));
        window.setTitle("Main Screen");
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}