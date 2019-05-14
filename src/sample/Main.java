//package sample;
//
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import javafx.scene.control.Button;
//
//public class Main extends Application{
//    private Stage window;
//    private Scene MainScreen, SettingsScreen;
//
//    @Override
//    public void start(Stage primaryScene) throws Exception{
//        Parent MScreen = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
//        Parent SScreen = FXMLLoader.load(getClass().getResource("SettingsScreen.fxml"));
//
//        MainScreen = new Scene (MScreen, 1080, 1920);
//        SettingsScreen = new Scene (SScreen, 1080, 1920);
//
//        window = primaryScene;
//
//        window.setScene(MainScreen);
//        window.setTitle("Main Screen");
//        window.show();
//    }
//
//
//
//
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//
//
//
//
//}
