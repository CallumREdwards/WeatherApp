package sample;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Tidal {

    private List<String> lowTide;
    private List<String> highTide;
    private JSONArray stations;

    private static String key = "7ba821d3ff5d40a8880f5efa51d6f4dc";
    private static Double R = 6371d;

    Tidal() throws IOException {

        File file = new File("weather/stations.json");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;

        StringBuffer data = new StringBuffer();
        while ((st = br.readLine()) != null)
            data.append(st);

        stations = new JSONArray(new String(data));
    }

    private JSONArray getTidal(String stationID) throws IOException {

        String urlRequest = "https://admiraltyapi.azure-api.net/uktidalapi/api/V1/Stations/" + stationID + "/TidalEvents";

        URL url = new URL(urlRequest);
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assert con != null;
            con.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }

        con.setRequestProperty("Ocp-Apim-Subscription-Key", key);
        int status = con.getResponseCode();


        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        con.disconnect();

        return new JSONArray(new String(content));
    }

    private void extractHighLowTides(String stationID) throws IOException {
        lowTide = new ArrayList<>();
        highTide = new ArrayList<>();
        JSONArray tideInfoArray = getTidal(stationID);
        for (int i = 0; i < tideInfoArray.length(); i++) {
            JSONObject tideInfo = tideInfoArray.getJSONObject(i);
            if (tideInfo.getString("EventType").equals("LowWater")) {
                lowTide.add(tideInfo.getString("DateTime"));
            }
            else if (tideInfo.getString("EventType").equals("HighWater")) {
                highTide.add(tideInfo.getString("DateTime"));
            }
        }

    }

    private double calculateDistance(double lat1, double long1, double lat2, double long2) {
        double dlat = Math.toRadians(lat2 - lat1);
        double dlon = Math.toRadians(long2- long1);
        double a = Math.pow(Math.sin(dlat/2), 2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.pow(Math.sin(dlon/2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
        return R * c;
    }

    private String nearestStationID(double lat, double lon) throws IOException {

        double minDist = Double.MAX_VALUE;
        String stationID = "0001";

        for (int i=0; i < stations.length(); i++) {
            double slat = stations.getJSONObject(i).getJSONObject("geometry").getJSONArray("coordinates").getDouble(1);
            double slon = stations.getJSONObject(i).getJSONObject("geometry").getJSONArray("coordinates").getDouble(0);

            double dist = calculateDistance(slat,slon,lat,lon);

            if (dist < minDist) {
                minDist = dist;
                stationID = stations.getJSONObject(i).getJSONObject("properties").getString("Id");
            }
        }

        return stationID;
    }


    public List<String> getLowTideTimes (double lat, double lon) throws IOException {
        String stationID = nearestStationID(lat, lon);
        extractHighLowTides(stationID);
        return lowTide;

    }

    public List<String> getHighTideTimes (double lat, double lon) throws IOException {
        String stationID = nearestStationID(lat, lon);
        extractHighLowTides(stationID);
        return highTide;

    }

    public static void main(String[] args) throws IOException {
        Tidal t = new Tidal();
        System.out.println(t.getHighTideTimes(56, -2));
    }
}