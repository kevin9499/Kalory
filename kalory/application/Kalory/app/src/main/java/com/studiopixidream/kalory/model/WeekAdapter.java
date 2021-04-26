package com.studiopixidream.kalory.model;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.studiopixidream.kalory.R;
import com.studiopixidream.kalory.fragment.MenuFragment;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeekAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<Week> weeks;

    public WeekAdapter(Activity activity, ArrayList<Week> weeks) {
        this.activity = activity;
        this.weeks = weeks;
    }

    @Override
    public int getCount() {
        return weeks.size();
    }

    @Override
    public Object getItem(int position) {
        return weeks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return weeks.get(position).getId();
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        if (converView == null) {
            converView = LayoutInflater.from(activity).inflate(R.layout.item_list, parent, false);
        }
        TextView textViewItemName = converView.findViewById(R.id.textViewItemName);
        textViewItemName.setText(weeks.get(position).getName());

        return converView;
    }
}
