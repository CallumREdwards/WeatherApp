package sample;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.Date;

public class Backend {
    private String location;
    private Date time;
    private String units = "metric";
    private double timeOfUpdate;
    private String APPID = "1bf10981e67c1d2094f4c94b404d2ab0";
    private JSONObject hourly;
    private JSONObject current;
    private JSONArray uv;
    private double currentuv;

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    private static JSONObject readJsonFromUrl(String url) throws IOException, org.json.JSONException {//Code copied from https://stackoverflow.com/questions/4308554/simplest-way-to-read-json-from-a-url-in-java
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            return new JSONObject(jsonText);
        } finally {
            is.close();
        }
    }

    private static JSONArray readJSONArrayFromUrl(String url) throws IOException, org.json.JSONException  {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            return new JSONArray(jsonText);
        } finally {
            is.close();
        }
    }

    public Backend(String l, Date d) throws IOException, org.json.JSONException  {
        location = l;
        time = d;
        update();
    }

    public void setLocation(String l) throws IOException, org.json.JSONException {
        location = l;
        update();
    }

    public void setTime(Date d) throws org.json.JSONException{
        time = d;
        setCurrentWeather();
    }

    public void toggleUnits() throws IOException, org.json.JSONException{
        if (units.equals("metric")) {
            units = "imperial";
        } else {
            units = "metric";
        }
        update();
    }

    public void update() throws IOException, org.json.JSONException  {
        hourly = readJsonFromUrl("https://api.openweathermap.org/data/2.5/forecast/hourly?q=" + location + "&units=" + units + "&APPID=" + APPID);
        timeOfUpdate = (new Date()).getTime();
        JSONObject coord = hourly.getJSONObject("city").getJSONObject("coord");
        uv = readJSONArrayFromUrl("https://api.openweathermap.org/data/2.5/uvi/forecast?lat=" + coord.getDouble("lat") + "&lon=" + coord.getDouble("lon") + "&appid=" + APPID);
        setCurrentWeather();
    }

    private void setCurrentWeather() throws org.json.JSONException  {//UV forecast of only 8 hours from now
        int hoursFromNow = (int) (time.getTime() / 60000 - timeOfUpdate / 60000);
        current = hourly.getJSONArray("list").getJSONObject(hoursFromNow);
        if (hoursFromNow < 8) {
            currentuv = uv.getJSONObject(hoursFromNow).getInt("value");
        } else {
            currentuv = -1;
        }
    }

    public String getWeather() throws org.json.JSONException  {
        return current.getJSONArray("weather").getJSONObject(0).getString("main");
    }

    public String getDescription() throws org.json.JSONException  {
        return current.getJSONArray("weather").getJSONObject(0).getString("description");
    }

    public double getTemperature()throws org.json.JSONException  {//Celcius of Farenheit
        return current.getJSONObject("main").getDouble("temp");
    }

    public double getPressure() throws org.json.JSONException {
        return current.getJSONObject("main").getDouble("pressure");
    }

    public double getHumidity() throws org.json.JSONException  {
        return current.getJSONObject("main").getDouble("humidity");
    }

    public double getWindSpeed() throws org.json.JSONException  {//meter/sec or miles/hour
        return current.getJSONObject("wind").getDouble("speed");
    }

    public double getWindDirection() throws org.json.JSONException  {//in degrees
        return current.getJSONObject("wind").getDouble("deg");
    }

    public double getUV(){
        return currentuv;
    }

    public Date getTime() throws org.json.JSONException {
        return new Date(current.getLong("dt"));
    }

    public String getLocation(){
        return this.location;
    }


    public static void main(String[] args) throws IOException, org.json.JSONException  {
        Backend b = new Backend("cambridge", new Date());
        System.out.println("Weather " + b.getWeather());
        System.out.println("Description " + b.getDescription());
        System.out.println("Temperature " + b.getTemperature());
        System.out.println("Pressure " + b.getPressure());
        System.out.println("Humidity " + b.getHumidity());
        System.out.println("WindSpeed " + b.getWindSpeed());
        System.out.println("WindDirection " + b.getWindDirection());
        System.out.println("Time " + b.getTime());
        System.out.println("UV " + b.getUV());
    }
}
