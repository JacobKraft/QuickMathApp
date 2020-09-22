package com.example.quickmath;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class FirstFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        //Finds each button from its id
        Button button_easy = view.findViewById(R.id.button_easy);
        Button button_med = view.findViewById(R.id.button_med);
        Button button_hard = view.findViewById(R.id.button_hard);

        //sets up onClickListeners for each button
        button_easy.setOnClickListener(this);
        button_med.setOnClickListener(this);
        button_hard.setOnClickListener(this);

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    /**
     * Method that handles all the button clicks with a simple switch statement
     * @param view view
     */
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button_easy:
            case R.id.button_med:
            case R.id.button_hard:
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
                break;
        }
    }
}