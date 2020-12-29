package com.kraftjacob.quickmath;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import es.dmoral.toasty.Toasty;

import static java.lang.Integer.parseInt;

public class FirstFragment extends Fragment implements View.OnClickListener {

    EditText maxVal;
    int val;
    MediaPlayer mp;

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
        ImageButton button_highscore = view.findViewById(R.id.button_highscore);

        maxVal = view.findViewById(R.id.text_number);
        mp = MediaPlayer.create(getContext(), R.raw.button_click);

        //sets up onClickListeners for each button
        button_easy.setOnClickListener(this);
        button_med.setOnClickListener(this);
        button_hard.setOnClickListener(this);
        button_highscore.setOnClickListener(this);

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assert getArguments() != null;
    }

    /**
     * Method that handles all the button clicks with a simple switch statement
     *
     * @param view view
     */
    @Override
    public void onClick(View view) {
        if (maxVal.getText().toString().length() <= 0) {
            val = 20;
        } else if (parseInt(maxVal.getText().toString()) > 300) {
            Toasty.warning(requireContext(), "Max 300. Set to 300",
                    Toasty.LENGTH_SHORT, true).show();
            val = 300;
        } else {
            val = parseInt(maxVal.getText().toString());
        }
        switch (view.getId()) {
            case R.id.button_easy:
                mp.start();
                FirstFragmentDirections.ActionFirstFragmentToSecondFragment action0 =
                        FirstFragmentDirections.actionFirstFragmentToSecondFragment(0, val);
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(action0);
                break;
            case R.id.button_med:
                mp.start();
                FirstFragmentDirections.ActionFirstFragmentToSecondFragment action1 =
                        FirstFragmentDirections.actionFirstFragmentToSecondFragment(1, val);
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(action1);
                break;
            case R.id.button_hard:
                mp.start();
                FirstFragmentDirections.ActionFirstFragmentToSecondFragment action2 =
                        FirstFragmentDirections.actionFirstFragmentToSecondFragment(2, val);
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(action2);
                break;
            case R.id.button_highscore:
                mp.start();
                FirstFragmentDirections.ActionFirstFragmentToHighScoreFragment action3 = FirstFragmentDirections.actionFirstFragmentToHighScoreFragment(-1);
                NavHostFragment.findNavController(FirstFragment.this).
                        navigate(action3);
                break;
        }
    }

}