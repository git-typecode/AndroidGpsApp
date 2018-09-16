package se.typecode.android.test.gpsapp;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Created by daniel on 2018-05-21.
 */


public class GpsDataAdapter extends ArrayAdapter {

    private final Context context;
    private static Resources res = null;

    public GpsDataAdapter(@NonNull Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull List objects, Resources res) {
        super(context, resource, textViewResourceId, objects);
        this.context = context;
        this.res = res;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getPosition(@Nullable Object item) {
        return super.getPosition(item);

    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        TypedArray icons = res.obtainTypedArray(R.array.gps_data_types);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.gps_data_textview, parent, false);
        }

        TextView textViewItemName = (TextView)
                convertView.findViewById(R.id.gps_data);
        textViewItemName.setText(getItem(position).toString());
        TextView textViewItemType = (TextView)
                convertView.findViewById(R.id.gps_data_type);
        Log.i("gps_data_type: ",icons.getString(position));
        textViewItemType.setText(icons.getString(position));

        return convertView;

    }
}