package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.SVGPath;

import java.io.IOException;

public class MainScreenController {

    @FXML
    private Label temperature;

    @FXML
    private SVGPath windArrow;

    private Backend backend;

    public void setBackend(Backend backend) {
        this.backend = backend;
        temperature.setText(backend.getTemperature() + " ºC");
    }


    @FXML
    private void refresh() {
        try {
            backend.update();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*private void setLocation(){
        refresh();
    }*/
}
