package com.example.hr_app_1;

import android.graphics.Typeface;
import android.widget.*;
import android.view.*;
import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;

// extends BaseAdapter to get all standard Adapter methods
// implements SpinnerAdapter to use getDropDownView() - exclusive to Spinner
public class ImageGridAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> names;
    ArrayList<String> results;
    int[] images;
    LayoutInflater inflater;

    // constructor
    public ImageGridAdapter (Context context, ArrayList names, ArrayList results, int[] images) {

        this.context = context;
        this.names = names;
        this.results = results;
        this.images = images;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int position) {
        return names.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // getView is what shows on the gridView before the user clicks it
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView name;
        ImageView image;

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.grid_layout, parent, false);

            name = (TextView) convertView.findViewById(R.id.type);
            image = (ImageView) convertView.findViewById(R.id.image);

            name.setText(names.get(position) + "\n" + results.get(position));
            name.setTypeface(null, Typeface.BOLD);
            name.setGravity(Gravity.CENTER);

            image.setImageResource(images[position]);
        }

        return convertView;
    }
}

