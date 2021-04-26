package com.studiopixidream.kalory.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.studiopixidream.kalory.R;
import com.studiopixidream.kalory.database.DayRepository;
import com.studiopixidream.kalory.database.UserRepository;
import com.studiopixidream.kalory.database.WeekRepository;
import com.studiopixidream.kalory.model.IOnSelectDay;
import com.studiopixidream.kalory.model.User;
import com.studiopixidream.kalory.model.Week;
import com.studiopixidream.kalory.model.Day;

import java.util.ArrayList;
import java.util.Arrays;

import android.widget.ArrayAdapter;
import android.widget.TextView;


public class WeekFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private TextView textViewWeek;
    private ListView listViewDays;
    private Day day;
    private User user;
    private Week Currentweek;

    public void setCurrentweek(Week currentweek) {
        Currentweek = currentweek;
    }

    private IOnSelectDay listener;
    private ArrayAdapter<String> listAdapter;
    ArrayList<String> dayList = new ArrayList<String>();


    public void setListener(IOnSelectDay listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.day_list, null);

        textViewWeek = v.findViewById(R.id.textViewWeek);
        listViewDays = v.findViewById(R.id.listViewDays);

        refresh();
        listViewDays.setOnItemClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void onResume() {
        super.onResume();
        String[] days = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        dayList = new ArrayList<String>();
        dayList.addAll(Arrays.asList(days));
        listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.view_list, dayList);
        listViewDays.setAdapter(listAdapter);
        refresh();
        handleChangeView();
    }

    public void refresh() {
        int totalKcal = 0;
        if (Currentweek != null) {
            this.user = UserRepository.getInstance(getContext()).getUser();
            String gender = this.user.getGender();
            if (gender.equals("Male")) {
                totalKcal = ((Integer.parseInt(this.user.getHeight()) + Integer.parseInt(this.user.getWeight()) - Integer.parseInt(this.user.getAge())) * 7) * 7;
            } else if (gender.equals("Female") || gender.equals("Other")) {
                totalKcal = ((Integer.parseInt(this.user.getHeight()) + Integer.parseInt(this.user.getWeight()) - Integer.parseInt(this.user.getAge())) * 6) * 7;
            }

            textViewWeek.setText("" + WeekRepository.getInstance(getContext()).getTotalWeekCal(Currentweek.getId()) + " cal / " + totalKcal + " cal");
        }
        handleChangeView();
    }

    private void handleChangeView() {
        if (dayList.size() <= 0) {
            this.listViewDays.setVisibility(View.GONE);
        } else {
            this.listViewDays.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Long tsLong = System.currentTimeMillis() / 1000;
        String ts = tsLong.toString();
        Day day = new Day();
        if (!DayRepository.getInstance(getContext()).isDayExist(dayList.get(position), Currentweek.getId())) {
            day = new Day(Integer.parseInt(ts), dayList.get(position), "0", Currentweek.getId());
            DayRepository.getInstance(getContext()).add(day);
        } else {
            day = DayRepository.getInstance(getContext()).getDay(dayList.get(position));
        }
        listener.onSelectDay(dayList.get(position), day);
    }
}