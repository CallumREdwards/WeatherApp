package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SettingsController{
    private App app;

    public void setApp(App something){
        app = something;
    }

    public void changeToMain(){
        Stage window = app.getStage();
        window.setScene(app.getMainScene());
        window.setTitle("Main Screen");
        window.show();
    }
}
