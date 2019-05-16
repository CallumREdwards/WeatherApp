package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class App extends Application {
    private FXMLLoader MainLoader = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
    private FXMLLoader SettingsLoader = new FXMLLoader(getClass().getResource("SettingsScreen.fxml"));

    private Stage window;
    private Pane MScreen;
    private Pane SScreen;

    private Scene mainScene;
    private Scene settingsScene;

    private MainController mainC;
    private SettingsController settingsC;

    private Backend info;

    private Pane BasePane;
    private TextField Location;
    private TextField Temperature;
    private TextField UV;
    private TextField Date;
    private TextField Time;

    private ImageView backToMainButton;
    private ImageView settingsIcon;

    public void initializeButtons() throws java.io.IOException, org.json.JSONException{
        info = new Backend("Bucharest", new Date());

        MScreen = MainLoader.load();
        SScreen = SettingsLoader.load();

        mainC = MainLoader.getController();
        settingsC = SettingsLoader.getController();

        BasePane = mainC.BasePane;

        settingsIcon = mainC.settingsIcon;
        settingsIcon.setOnMouseClicked(e->changeToSettings());

//        Settings.setGraphic(mainC.settingsIcon);    //this is how to add images over buttons

        backToMainButton = settingsC.backToMainButton;  //delete this
        backToMainButton.setOnMouseClicked(e->changeToMain());

        Temperature = mainC.Temperature;
        Temperature.setText(String.valueOf(info.getTemperature()));

        UV = mainC.UV;
        UV.setText(String.valueOf(info.getUV()));

        String date = info.getTime().toString();

        Date = mainC.Date;
        Date.setText(date.substring(0, 10));

        Time = mainC.Time;
        Time.setText(date.substring(11, 28));

        Location = mainC.Location;
        Location.setText(info.getLocation());
    }

    public void changeToSettings(){
        window.setScene(settingsScene);
        window.setTitle("Settings Screen");
        window.show();
    }

    public void changeToMain(){
        window.setScene(mainScene);
        window.setTitle("Main Screen");
        window.show();
    }

    //use this method to change the BackgGround when you return from the SettingsScreen
    public void changeBackgroundColor(String color){
        BasePane.setStyle("-fx-background-color: " + color + ";");
    }

    @Override
    public void start(Stage primaryScene) throws Exception{
        initializeButtons();

        mainScene = new Scene(MScreen,559.6666259765625, 959.9999000000025);
        settingsScene = new Scene(SScreen,559.6666259765625, 959.9999000000025);

        window = primaryScene;
        changeToMain();

    }

    public static void main(String[] args) {
        launch(args);
    }

}