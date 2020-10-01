package com.example.quickmath;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;


import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import es.dmoral.toasty.Toasty;

public class SecondFragment extends Fragment implements View.OnClickListener{

    int correctAnswer = -1; //tracks the correct answer of a give problem
    int gameType; //tracks if the game is easy/med/hard based on player selection
    int score = 0; //tracks the user score throughout the game
    int counter = -1; //counter used to display seconds user has left
    Timer timer; //timer used to increase counter every second
    int streak = 0; //keeps track of number of correct answers in a row

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        Button button_start = view.findViewById(R.id.button_start);
        Button button_second = view.findViewById(R.id.button_second);
        Button button_1 = view.findViewById(R.id.button_1);
        Button button_2 = view.findViewById(R.id.button_2);
        Button button_3 = view.findViewById(R.id.button_3);
        Button button_4 = view.findViewById(R.id.button_4);
        Button button_5 = view.findViewById(R.id.button_5);
        Button button_6 = view.findViewById(R.id.button_6);
        Button button_7 = view.findViewById(R.id.button_7);
        Button button_8 = view.findViewById(R.id.button_8);
        Button button_9 = view.findViewById(R.id.button_9);
        Button button_0 = view.findViewById(R.id.button_0);
        Button button_submit = view.findViewById(R.id.button_submit);
        Button button_del = view.findViewById(R.id.button_del);
        Button button_minus = view.findViewById(R.id.button_minus);

        button_start.setOnClickListener(this);
        button_second.setOnClickListener(this);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);
        button_5.setOnClickListener(this);
        button_6.setOnClickListener(this);
        button_7.setOnClickListener(this);
        button_8.setOnClickListener(this);
        button_9.setOnClickListener(this);
        button_0.setOnClickListener(this);
        button_submit.setOnClickListener(this);
        button_del.setOnClickListener(this);
        button_minus.setOnClickListener(this);

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        assert getArguments() != null;
        gameType = SecondFragmentArgs.fromBundle(getArguments()).getFirstInt();
    }

    @Override
    public void onClick(View view) {
        TextView input = (TextView) view.getRootView().findViewById(R.id.textview_input);
        switch(view.getId()){
            case R.id.button_second:
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
                break;
            case R.id.button_start:
                //disable go button during the game
                view.findViewById(R.id.button_start).setEnabled(false);
                counter++;
                gameState(view);
                startTimer(view);
                break;
            case R.id.button_1:
                if(lengthCheck(input)){
                    break;
                }
                input.append("1");
                break;
            case R.id.button_2:
                if(lengthCheck(input)){
                    break;
                }
                input.append("2");
                break;
            case R.id.button_3:
                if(lengthCheck(input)){
                    break;
                }
                input.append("3");
                break;
            case R.id.button_4:
                if(lengthCheck(input)){
                    break;
                }
                input.append("4");
                break;
            case R.id.button_5:
                if(lengthCheck(input)){
                    break;
                }
                input.append("5");
                break;
            case R.id.button_6:
                if(lengthCheck(input)){
                    break;
                }
                input.append("6");
                break;
            case R.id.button_7:
                if(lengthCheck(input)){
                    break;
                }
                input.append("7");
                break;
            case R.id.button_8:
                if(lengthCheck(input)){
                    break;
                }
                input.append("8");
                break;
            case R.id.button_9:
                if(lengthCheck(input)){
                    break;
                }
                input.append("9");
                break;
            case R.id.button_0:
                if(lengthCheck(input)){
                    break;
                }
                input.append("0");
                break;
            case R.id.button_minus:
                input.append("-");
                break;
            case R.id.button_del:
                String newNum = input.getText().toString();
                //makes sure string is at least 1 char long
                if( newNum.length() <= 0){
                    break;
                }
                newNum = newNum.substring(0, newNum.length() - 1);
                input.setText(newNum);
                break;
            case R.id.button_submit:
                if(input.getText().toString().length() == 0){
                    break;
                }
                if(Integer.parseInt(input.getText().toString()) == correctAnswer){
                    //for each consecutive correct answer the streak increases
                    //streak of 5 answers are worth double, 10 worth * 5, 20 worth *10
                    streak++;
                    if (streak >= 20){
                        score = score + 10*10;
                    } else if (streak >= 10){
                        score = score + 10*5;
                    } else if (streak >= 5){
                        score = score + 10*2;
                    } else {
                        score = score + 10;
                    }
                    TextView scoreText = view.getRootView().findViewById(R.id.text_score);
                    scoreText.setText("Score: " + score);
                    input.setText("");
                    gameState(view);
                } else {
                    streak = 0;
                    Toasty.error(requireContext(), "Incorrect", Toast.LENGTH_SHORT,
                            true).show();
                }
                break;
        }
    }

    /**
     * Makes sure that the user input does not exceed 5 digits
     * @param input the textview the user adds input to
     * @return true if the textview would exceed the max length, false if it would not.
     */
    public boolean lengthCheck(TextView input){
        if(input.getText().toString().length() >= 5){
            Toasty.warning(requireContext(), "Max Input", Toasty.LENGTH_SHORT,
                    true).show();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Main method that runs the game by creating a problem and setting a correct answer based on
     * the problem that was randomly generated.
     * @param view gives ability to alter screen
     */
    public void gameState(View view){
        Random random = new java.util.Random();
        char[] operations = new char[]{'+', '-', '*', '/'};
        if (counter == -1){
            view.findViewById(R.id.button_start).setEnabled(true);
        }
        if(counter <= 59 && counter >= 0){
            int opNum = 0;
            int firstNum = 0;
            int secondNum = 0;
            //if else section to set the game to easy(+/-) medium(+/-/*) or hard(+/-/*//)
            if (gameType == 0){
                opNum = random.nextInt(2);
            } else if (gameType == 1){
                opNum = random.nextInt(3);
            } else if (gameType == 2){
                opNum = random.nextInt(4);
            }

            //display the streak multiplier to the user
            TextView displayStreak = (TextView) view.getRootView().findViewById(R.id.streak_text);
            if (streak >= 20){
                displayStreak.setText("X10");
            } else if (streak >= 10){
                displayStreak.setText("X5");
            } else if (streak >= 5){
                displayStreak.setText("X2");
            } else {
                displayStreak.setText("");
            }

            char op = operations[opNum];
            firstNum = random.nextInt(30) + 1;
            secondNum = random.nextInt( 30) + 1;

            //makes sure the division is relatively easy
            if(opNum == 3){
                while(firstNum  % secondNum != 0){
                    firstNum = random.nextInt(30) + 1;
                    secondNum = random.nextInt(30) + 1;
                }
            }
            TextView problem = (TextView) view.getRootView().findViewById(R.id.textview_display);
            problem.setText(firstNum + " "+ op + " "+ secondNum + " = __");

            //sets the correct answer based on the randomly chosen numbers and operation
            if(opNum == 0){
                correctAnswer = firstNum + secondNum;
            } if(opNum == 1){
                correctAnswer = firstNum - secondNum;
            } if(opNum == 2){
                correctAnswer = firstNum * secondNum;
            } if(opNum == 3){
                correctAnswer = firstNum / secondNum;
            }
        }

    }

    public void startTimer(View view){
        timer = new Timer();
        timer.scheduleAtFixedRate(new timeDisplay(view), 0, 1000);
    }

    public class timeDisplay extends TimerTask {
        View view;

        public timeDisplay(View view){
            this.view=view;
        }
        @Override
        public void run() {
            if (counter < 60 && counter >= 0) {
                counter++;
                TextView count = (TextView) view.getRootView().findViewById(R.id.button_start);
                count.setText(String.valueOf(60 - counter));
            } else {
                //re-enable go button
                //view.findViewById(R.id.button_start).setEnabled(true);
                timer.cancel();
                counter = -1;
                TextView count = (TextView) view.getRootView().findViewById(R.id.button_start);
                count.setText("GO!");
            }
        }
    }
}