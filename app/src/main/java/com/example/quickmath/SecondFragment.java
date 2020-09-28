package com.example.quickmath;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Random;

public class SecondFragment extends Fragment implements View.OnClickListener{

    int correctAnswer;
    int score = 0;

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
                gameState(view);
                break;
            case R.id.button_1:
                input.append("1");
                break;
            case R.id.button_2:
                input.append("2");
                break;
            case R.id.button_3:
                input.append("3");
                break;
            case R.id.button_4:
                input.append("4");
                break;
            case R.id.button_5:
                input.append("5");
                break;
            case R.id.button_6:
                input.append("6");
                break;
            case R.id.button_7:
                input.append("7");
                break;
            case R.id.button_8:
                input.append("8");
                break;
            case R.id.button_9:
                input.append("9");
                break;
            case R.id.button_0:
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
                    score++;
                    String countScore = getString(R.string.score_text, score);
                    TextView scoreText = view.getRootView().findViewById(R.id.text_score);
                    scoreText.setText(countScore);
                    input.setText("");
                    gameState(view);
                }
                break;
        }
    }

    public void gameState(View view){
        Random random = new java.util.Random();
        boolean run = true;
        correctAnswer = 0;
        char[] operations = new char[]{'+', '-', '/', '*'};
        while(run){
            int opNum = 0;
            int firstNum = 0;
            int secondNum = 0;
            opNum = random.nextInt(3);
            char op = operations[opNum];
            firstNum = random.nextInt(20);
            secondNum = random.nextInt(20);
            if(opNum == 2){
                while(firstNum  % secondNum != 0){
                    firstNum = random.nextInt(20);
                    secondNum = random.nextInt(20);
                }
            }
            TextView problem = (TextView) view.getRootView().findViewById(R.id.textview_display);
            problem.setText(firstNum + " "+ op + " "+ secondNum + " = __");
            if(opNum == 0){
                correctAnswer = firstNum + secondNum;
            } if(opNum == 1){
                correctAnswer = firstNum - secondNum;
            } if(opNum == 2){
                correctAnswer = firstNum / secondNum;
            } if(opNum == 3){
                correctAnswer = firstNum * secondNum;
            }
            run = false;
        }
    }

}