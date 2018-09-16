package se.typecode.android.test.gpsapp;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by daniel on 2018-05-20.
 */

public class GPSLocations implements LocationListener {

    private TextView textView;
    protected static ArrayList<String> locationsArray;
    private static Location latestLocation;
    private static float totalDistance = 0;
    private static final String GPS_DATE_FORMAT = "HH:mm:ss";
    private static Date gpsDate;
    private static final Pattern LAT_LONG_PATTERN = Pattern.compile("(\\d\\d?\\d?):(\\d\\d?):(\\d\\d?).(\\d{1,5})");
    private static final char MINUTE_SIGN = 39;
    private static final char DEGREE_SIGN = 176;
    private static String latLongDegree = "00";
    private static String latLongMinute = "00";
    private static String latLongSec = "00";
    private static SimpleDateFormat simp;;

    public GPSLocations(TextView textView) {

        this.textView = textView;
        simp = new SimpleDateFormat(GPS_DATE_FORMAT);
        gpsDate = new Date();
        locationsArray = new ArrayList<>();

    }

    private String fixLatLongString(String latLongString) {

        Matcher m = LAT_LONG_PATTERN.matcher(latLongString);
        Log.i("latlongString: ", latLongString);

        if(m.matches()) {

            latLongDegree = m.group(1) + DEGREE_SIGN;
            latLongMinute = m.group(2) + MINUTE_SIGN;
            latLongSec = m.group(3) + MINUTE_SIGN + MINUTE_SIGN;
            Log.i("fixLatLongString: ", latLongDegree + " " + latLongMinute + " " + latLongSec);

        }

        return latLongDegree + latLongMinute + " " + latLongSec;
    }

    @Override
    public void onLocationChanged(Location location) {

        locationsArray.clear();
        locationsArray.add(fixLatLongString((Location.convert(location.getLatitude(), Location.FORMAT_SECONDS))));
        locationsArray.add(fixLatLongString((Location.convert(location.getLongitude(), Location.FORMAT_SECONDS))));
        locationsArray.add(String.valueOf(location.getSpeed()));
        locationsArray.add(String.valueOf(location.getBearing()));
        locationsArray.add(String.valueOf(location.getAltitude()));

        gpsDate.setTime(location.getTime());

        String gpsTimeString = simp.format(gpsDate);
        locationsArray.add(gpsTimeString);

        locationsArray.add(String.valueOf(location.getExtras().getInt("satellites")));

        if(latestLocation != null){
            totalDistance += location.distanceTo(latestLocation);
            locationsArray.add(String.valueOf(totalDistance));
        }

        latestLocation = location;
        if(GridActivity.gpsDataAdapterdapter != null) {
            GridActivity.gpsDataAdapterdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

        String status;

        switch(i) {
            case LocationProvider.TEMPORARILY_UNAVAILABLE: status = "TEMPORARILY UNAVAILABLE";
                break;
            case LocationProvider.AVAILABLE: status = "AVAILABLE";
                break;
            default: status = "OUT OF SERVICE";

        }
        textView.setText(s + " " + status);

    }

    @Override
    public void onProviderEnabled(String s) {
        //textview.setText("GPS enabled!");
        textView.setText(R.string.GPS_enabled);
        Log.i("onProviderEnabled", s);


    }

    @Override
    public void onProviderDisabled(String s) {
        //textview.setText("GPS disabled!");
        textView.setText(R.string.GPS_disabled);
        Log.i("onProviderDisabled", s);
    }
}
