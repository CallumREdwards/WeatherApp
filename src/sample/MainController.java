package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.Event;

import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainController extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    private Stage window;
    private Pane MScreen = new Pane();
    private Pane SScreen = new Pane();

    private TextField location = new TextField();
    private Button settings = new Button();
    private TextField lastUpdated = new TextField();
    private TextField safe = new TextField();

    public void initializeButtons(){
        location.setEditable(false);
        location.setLayoutX(15.0);
        location.setLayoutY(14.0);
        location.setPrefHeight(110.951171875);
        location.setPrefWidth(454.0);
        location.setText("Location");

        settings.setLayoutX(469.0);
        settings.setLayoutY(56.0);
        settings.setMnemonicParsing(false);
        settings.setPrefWidth(71.0);
        settings.setText("Settings");

        lastUpdated.setEditable(false);
        lastUpdated.setLayoutX(15.0);
        lastUpdated.setLayoutY(125.0);
        lastUpdated.setPrefWidth(454.0);
        lastUpdated.setText("lastUpdated");

        safe.setEditable(false);
        safe.setLayoutX(106.0);
        location.setLayoutY(181.0);
        location.setPrefHeight(110.951171875);
        location.setPrefWidth(454.0);
        location.setText("Location");

        MScreen.getChildren().add(location);
        MScreen.getChildren().add(settings);
        MScreen.getChildren().add(lastUpdated);
    }

    @Override
    public void start(Stage primaryScene) throws Exception{
        initializeButtons();

        window = primaryScene;

        window.setScene(new Scene (MScreen, 1080, 1920));
        window.setTitle("Main Screen");
        window.show();
    }

    public void random(Event e){
        System.out.println("AAAA");
    }


}
