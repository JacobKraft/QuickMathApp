package com.example.quickmath;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighScoreFragment extends Fragment implements View.OnClickListener {

    View view;
    int score;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_highscore, container, false);

        Button button_home = view.findViewById(R.id.button_home);

        button_home.setOnClickListener(this);

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assert getArguments() != null;

        TextView score_1 = view.getRootView().findViewById(R.id.text_score_1);
        TextView score_2 = view.getRootView().findViewById(R.id.text_score_2);
        TextView score_3 = view.getRootView().findViewById(R.id.text_score_3);
        TextView score_4 = view.getRootView().findViewById(R.id.text_score_4);
        TextView score_5 = view.getRootView().findViewById(R.id.text_score_5);

        score = HighScoreFragmentArgs.fromBundle(getArguments()).getScoreInt();
        SharedPreferences prefs = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        List<Integer> highscores = new ArrayList<>();

        //retrieves prefs string and turns into an array of numbers -> highscores
        String savedString = prefs.getString("MyPrefs", "");
        assert savedString != null;
        String[] st = savedString.split(",");
        for (String s : st) {
            highscores.add(Integer.parseInt(s));
        }

        //add the current score to prefs by making it a string
        if (score >= 0) {
            highscores.add(score);
        }
        Collections.sort(highscores);
        Collections.reverse(highscores);

        //make sure there are only 5 elements in the array
        if (highscores.size() > 5) {
            highscores.remove(5);
        }
        int arrL = highscores.size();

        //display the numbers
        switch (arrL) {
            case 0:
                break;
            case 1:
                score_1.setText(getString(R.string.highscore_1, highscores.get(0)));
                break;
            case 2:
                score_1.setText(getString(R.string.highscore_1, highscores.get(0)));
                score_2.setText(getString(R.string.highscore_2, highscores.get(1)));
                break;
            case 3:
                score_1.setText(getString(R.string.highscore_1, highscores.get(0)));
                score_2.setText(getString(R.string.highscore_2, highscores.get(1)));
                score_3.setText(getString(R.string.highscore_3, highscores.get(2)));
                break;
            case 4:
                score_1.setText(getString(R.string.highscore_1, highscores.get(0)));
                score_2.setText(getString(R.string.highscore_2, highscores.get(1)));
                score_3.setText(getString(R.string.highscore_3, highscores.get(2)));
                score_4.setText(getString(R.string.highscore_4, highscores.get(3)));
                break;
            case 5:
                score_1.setText(getString(R.string.highscore_1, highscores.get(0)));
                score_2.setText(getString(R.string.highscore_2, highscores.get(1)));
                score_3.setText(getString(R.string.highscore_3, highscores.get(2)));
                score_4.setText(getString(R.string.highscore_4, highscores.get(3)));
                score_5.setText(getString(R.string.highscore_5, highscores.get(4)));
                break;
        }

        //turn array into a string to store in prefs
        StringBuilder str1 = new StringBuilder();
        for (int i = 0; i < highscores.size(); i++) {
            str1.append(highscores.get(i)).append(",");
        }
        editor.putString("MyPrefs", str1.toString());
        editor.apply();


    }

    @Override
    public void onClick(View view) {
        NavHostFragment.findNavController(HighScoreFragment.this).
                navigate(R.id.action_highScoreFragment_to_FirstFragment);

    }
}