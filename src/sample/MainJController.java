package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

public class MainJController {


    @FXML
    private Text temperature;
    private Backend backend = new Backend("Cambridge",new Date());
    private App app;




    private String location_text;
    private Boolean warning_icon_visible;
    private String weather_icon_type;
    private Double temperature_value;
    private String uv_level_text;
    private Double wind_direction_degrees;
    private String current_tide_text;
    private String next_tide_text;
    private String current_tide_time_text;
    private String next_tide_time_text;
    private String last_update_text;
    private String date_text;
    private String time_text;
    private Double wind_speed_value;
    private Boolean wind_speed_metric;
    private String safety_rating;

    public MainJController() throws IOException {
        location_text = backend.getLocation();
        //warining_icon_visible
        //weather_icon_type
        temperature_value = backend.getTemperature();
        wind_direction_degrees = backend.getWindDirection();
        //current_tide_text
        //next_tide_text
        //current_tide_time_text
        //next_tide_time_text
        last_update_text = backend.getTimeOfUpdate().toString();
        //date_text
        //time_text
        wind_speed_value = backend.getWindSpeed();
        //wind_speed_metric
        //safety_rating
    }

    public void setApp(App something){
        app = something;
        temperature.setText(Double.toString(backend.getTemperature()));
    }

    public void changeToSettings(){
        Stage window = app.getStage();
        window.setScene(app.getSettingsScene());
        window.setTitle("Settings Screen");
        window.show();
    }

    public void update_values() {

    }


}
