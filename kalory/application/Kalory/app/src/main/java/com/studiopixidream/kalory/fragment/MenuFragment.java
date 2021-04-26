package com.studiopixidream.kalory.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;

import java.util.Date;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;

import com.studiopixidream.kalory.R;
import com.studiopixidream.kalory.database.UserRepository;
import com.studiopixidream.kalory.database.WeekRepository;
import com.studiopixidream.kalory.model.IOnAddWeek;
import com.studiopixidream.kalory.model.User;
import com.studiopixidream.kalory.model.Week;
import com.studiopixidream.kalory.model.WeekAdapter;


import java.util.ArrayList;

public class MenuFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private TextView textViewName, textViewAge, textViewHeight, textViewWeight, textViewImc;
    private ImageView imageViewProfil, imageViewSettings, imageViewWeek;

    private ListView listViewWeeks;
    private Button buttonAddWeek;

    private ArrayList<Week> weeks = new ArrayList<Week>();
    private User user;
    private Week week;
    private IOnAddWeek listener;
    WeekAdapter weekAdapter;

    public void setListener(IOnAddWeek listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.menu_fragment, null);

        textViewName = v.findViewById(R.id.textViewName);
        textViewAge = v.findViewById(R.id.textViewAge);
        textViewHeight = v.findViewById(R.id.textViewHeight);
        textViewWeight = v.findViewById(R.id.textViewWeight);
        textViewImc = v.findViewById(R.id.textViewImc);
        imageViewProfil = v.findViewById(R.id.imageViewProfil);
        imageViewSettings = v.findViewById(R.id.imageViewSettings);
        imageViewWeek = v.findViewById(R.id.imageViewItemPicture);

        listViewWeeks = v.findViewById(R.id.listViewWeeks);
        listViewWeeks.setOnItemClickListener(this);

        buttonAddWeek = v.findViewById(R.id.buttonAddWeek);
        buttonAddWeek.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        Long tsLong = System.currentTimeMillis() / 1000;
        int total = WeekRepository.getInstance(getContext()).getAll().size();
        String ts = tsLong.toString();
        String name = "Week " + String.valueOf(total + 1);
        Week week = new Week(Integer.parseInt(ts), name);
        WeekRepository.getInstance(getContext()).add(week);
        listener.onAddWeek(week);
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    public void refresh() {
        this.user = UserRepository.getInstance(getContext()).getUser();
        textViewName.setText(this.user.getName());
        textViewAge.setText(this.user.getAge() + " ans");
        textViewHeight.setText(this.user.getHeight() + " cm");
        textViewWeight.setText(this.user.getWeight() + " kg");

        imageViewProfil.setImageResource(R.mipmap.profil);
        imageViewSettings.setImageResource(R.mipmap.settings_foreground);
        if (this.user.getHeight() != null) {
            float height = Integer.parseInt(this.user.getHeight()) / 10;
            height *= height;
            float imc = (Integer.parseInt(this.user.getWeight()) / height) * 100;
            textViewImc.setText(String.valueOf(Math.round(imc)) + " IMC");
        }
        if (this.user.getGender() != null) {
            if (this.user.getGender().equals("Male")) {
                imageViewProfil.setImageResource(R.mipmap.profil);
            } else if (this.user.getGender().equals("Female")) {
                imageViewProfil.setImageResource(R.mipmap.profile);
            }
        }
        weeks = WeekRepository.getInstance(getActivity()).getAll();
        handleAdapterChange();
        handleChangeView();
    }

    private void handleChangeView() {
        if (weeks.size() <= 0) {
            this.listViewWeeks.setVisibility(View.GONE);
        } else {
            this.listViewWeeks.setVisibility(View.VISIBLE);
        }
    }

    private void handleAdapterChange() {
        if (weeks.size() > 0) {
            weekAdapter = new WeekAdapter(getActivity(), weeks);
            listViewWeeks.setAdapter(weekAdapter);
        } else {
            weekAdapter = new WeekAdapter(getActivity(), weeks);
            listViewWeeks.setAdapter(weekAdapter);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        listener.onAddWeek(weeks.get(position));

    }
}