package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Date;

public class MainController{
    @FXML
    Pane BasePane;

    @FXML
    TextField Location;

    @FXML
    Button Settings;

    @FXML
    Button SafeButton;

    @FXML
    Button TemperatureButton;

    @FXML
    Button UVButton;

    @FXML
    Button WindButton;

    @FXML
    Button TideButton;

    @FXML
    TextField Temperature;

    @FXML
    TextField UV;

    @FXML
    TextField Date;

    @FXML
    TextField Time;

    @FXML
    ImageView settingsIcon;
}
