package se.typecode.android.test.gpsapp;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.*;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;

public class GridActivity extends AppCompatActivity {

    private GridView gridView;
    static GpsDataAdapter gpsDataAdapterdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_grid);
        Resources res = getResources();

        gridView = (GridView)findViewById(R.id.gridview);
        Toolbar gpsToolbar = (Toolbar) findViewById(R.id.gps_toolbar);
        setSupportActionBar(gpsToolbar);
        gpsDataAdapterdapter = new GpsDataAdapter(this, R.layout.data_grid, R.id.gps_data, GPSLocations.locationsArray, res);
        gridView.setAdapter(gpsDataAdapterdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_gps, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_gps_settings:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
