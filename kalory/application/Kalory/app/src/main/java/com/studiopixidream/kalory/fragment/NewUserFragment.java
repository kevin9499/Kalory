package com.studiopixidream.kalory.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.studiopixidream.kalory.R;
import com.studiopixidream.kalory.database.UserRepository;
import com.studiopixidream.kalory.model.IOnContinue;
import com.studiopixidream.kalory.model.User;

public class NewUserFragment extends Fragment implements View.OnClickListener {

    TextView textViewTitle;
    EditText editTextName, editTextAge, editTextWeight, editTextHeight;
    Spinner spinnerGender;
    Button buttonContinue;

    private IOnContinue listener;

    public void setListener(IOnContinue listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.new_user_fragment, null);
        textViewTitle = v.findViewById(R.id.textViewTitle);
        editTextName = v.findViewById(R.id.editTextTextPersonName);
        editTextAge = v.findViewById(R.id.editTextTextPersonAge);
        editTextHeight = v.findViewById(R.id.editTextTextPersonHeight);
        editTextWeight = v.findViewById(R.id.editTextTextPersonWeight);

        spinnerGender = v.findViewById(R.id.spinner);

        buttonContinue = v.findViewById(R.id.buttonContinue);
        buttonContinue.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        String name = editTextName.getText().toString();
        String age = editTextAge.getText().toString();
        String height = editTextHeight.getText().toString();
        String weight = editTextWeight.getText().toString();
        String gender = spinnerGender.getSelectedItem().toString();
        User user = new User(1, name, age, height, weight, gender);
        UserRepository.getInstance(getContext()).add(user);
        listener.onContinue();
    }
}
