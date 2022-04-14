package com.example.finalcodetest2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Handler mHandler = new Handler();
    private ImageView iv_badGuyImg;
    private TextView tv_badGuySay;
    private ImageView iv_player1Img;
    private TextView tv_player1Name;
    private TextView tv_player1Say;
    private ImageView iv_player2Img;
    private TextView tv_player2Name;
    private TextView tv_player2Say;
    private EditText et_inputNumber;
    private Button btn_enter;
    private TextView tv_cheatMode;

    private String userInput;
    private String playerName;
    private int userGuessInt;
    private int answer;
    private int player;
    private int count;
    private int min;
    private int max;

    private boolean isPlayer1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = (EditText) findViewById(R.id.et_inputNumber);
        MyKeyboard keyboard = (MyKeyboard) findViewById(R.id.keyboard);

        editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        editText.setTextIsSelectable(true);

        InputConnection ic = editText.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(ic);

        initView();
        changeCheatMode("Ans: " + Integer.toString(answer));

        initHandler();

        mHandler.postDelayed(new Runnable() {
            public void run() {
                changeBadGuySay("炸彈要爆炸了喔!!猜到〔爆點〕的有重賞喔，現在數字範圍是 " + min + "-" + max + " !!");
            }
        }, 3000);
    }


    private void initHandler() {
        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                player++;
//                count++;
//
//                mHandler.postDelayed(new Runnable() {
//                    public void run() {
//                        changeBadGuySay(playerName + "，還等甚麼快輸入數字阿!!!");
//                    }
//                }, 8000);
//
//                if (player % 2 == 0) {
//                    player1();
//                } else {
//                    player2();
//                }
//
//                userInput = et_inputNumber.getText().toString();
//                userGuessInt = Integer.parseInt(userInput);
//
//                if (userGuessInt > max || userGuessInt < min) {
//            changeBadGuySay("超出範圍了啦，專業一點好嗎，你啊罵都比你會猜，〔爆點〕現在介於" + min + "-" + max + "。");
//            cleanInput();
//            return;
//
//
//
//                    if (userGuessInt < answer && userGuessInt >= min) {
//                        min = userGuessInt + 1;
//                        changeBadGuySay("很好，數字比你猜的 " + userGuessInt + " 還大，〔爆點〕現在介於" + min + "-" + max + " !");
//                        cleanInput();
//                    } else if (userGuessInt > answer && userGuessInt <= max) {
//                        max = userGuessInt - 1;
//                        changeBadGuySay("不錯喔，數字比你猜的 " + userGuessInt + " 還小，〔爆點〕現在介於" + min + "-" + max + " !");
//                        cleanInput();
//                    } else if (userGuessInt == answer) {
//                        changeBadGuySay("炸彈引爆，蹦蹦蹦!!! " + playerName + " 掰掰，哈哈哈，獎賞就是再!見!");
//                        changePlayerSay("喔不~我不想死~~~~~哭");
//                        btn_enter.setText("Replay");
//                    }

                if(isPlayer1){
                    player1();
                }else{
                    player2();
                }

                userInput = et_inputNumber.getText().toString();

                if (userInput.isEmpty()) {
                    changeBadGuySay("想騙我啊，你又沒猜!!!〔爆點〕現在介於" + min + "-" + max + "。");
                    return;
                }

                userGuessInt = Integer.parseInt(userInput);

                mHandler.postDelayed(new Runnable() {
                    public void run() {
                        changeBadGuySay(playerName + "，還等甚麼快輸入數字阿!!!");
                    }
                }, 10000);
//
                count++;

                if (userGuessInt > max || userGuessInt < min) {
                    changeBadGuySay("超出範圍了啦，專業一點好嗎，你啊罵都比你會猜，〔爆點〕現在介於" + min + "-" + max + "。");
                    cleanInput();
                    return;
                }

                if (max == min) {
                    changeBadGuySay("炸彈引爆，蹦蹦蹦!!! " + playerName + " 掰掰，哈哈哈，獎賞就是再!見!");
                    changePlayerSay("喔不~我不想死~~~~~哭");
                    btn_enter.setText("Replay");
                    et_inputNumber.setEnabled(false);
                }

//                if (player % 2 == 0) {
//                    player1();
//                } else {
//                    player2();
//                }

                if (userGuessInt == answer) {
                    changeBadGuySay("炸彈引爆，蹦蹦蹦!!! " + playerName + " 掰掰，哈哈哈，獎賞就是再!見!");
                    changePlayerSay("喔不~我不想死~~~~~哭");
                    btn_enter.setText("Replay");
                    et_inputNumber.setEnabled(false);

                } else {
                    if (userGuessInt < answer && userGuessInt >= min) {
                        min = userGuessInt + 1;
                        isPlayer1 = !isPlayer1;
                        changeBadGuySay("很好，數字比你猜的 " + userGuessInt + " 還大，〔爆點〕現在介於" + min + "-" + max + " !");
                        cleanInput();

                    } else if (userGuessInt > answer && userGuessInt <= max) {
                        max = userGuessInt - 1;
                        isPlayer1 = !isPlayer1;
                        changeBadGuySay("不錯喔，數字比你猜的 " + userGuessInt + " 還小，〔爆點〕現在介於" + min + "-" + max + " !");
                        cleanInput();
                    }
                }

            }
        });
    }

//    private void chkEmpty() {
//        userInput = et_inputNumber.getText().toString();
//        if (userInput.isEmpty()) {
//            changeBadGuySay("想騙我啊，你又沒猜!!!〔爆點〕現在介於" + min + "-" + max + "。");
//            return;
//        }
//        userGuessInt = Integer.parseInt(userInput);
//
//        if (userGuessInt > max || userGuessInt < min) {
//            changeBadGuySay("超出範圍了啦，專業一點好嗎，你啊罵都比你會猜，〔爆點〕現在介於" + min + "-" + max + "。");
//            cleanInput();
//            player -= player;
//            return;
//        }
//    }

    private void player1() {
        playerName = "玩家一號";
        iv_player1Img.setVisibility(ImageView.VISIBLE);
        tv_player1Name.setVisibility(TextView.VISIBLE);
        tv_player1Say.setVisibility(TextView.VISIBLE);
        iv_player2Img.setVisibility(ImageView.INVISIBLE);
        tv_player2Name.setVisibility(TextView.INVISIBLE);
        tv_player2Say.setVisibility(TextView.INVISIBLE);
    }

    private void player2() {
        playerName = "玩家二號";
        iv_player1Img.setVisibility(ImageView.INVISIBLE);
        tv_player1Name.setVisibility(TextView.INVISIBLE);
        tv_player1Say.setVisibility(TextView.INVISIBLE);
        iv_player2Img.setVisibility(ImageView.VISIBLE);
        tv_player2Name.setVisibility(TextView.VISIBLE);
        tv_player2Say.setVisibility(TextView.VISIBLE);
    }

    private void initView() {
        iv_badGuyImg = findViewById(R.id.iv_badGuyImg);
        tv_badGuySay = findViewById(R.id.tv_badGuySay);
        iv_player1Img = findViewById(R.id.iv_player1Img);
        tv_player1Name = findViewById(R.id.tv_player1Name);
        tv_player1Say = findViewById(R.id.tv_player1Say);
        iv_player2Img = findViewById(R.id.iv_player2Img);
        tv_player2Name = findViewById(R.id.tv_player2Name);
        tv_player2Say = findViewById(R.id.tv_player2Say);
        et_inputNumber = findViewById(R.id.et_inputNumber);
        btn_enter = findViewById(R.id.btn_enter);
        tv_cheatMode = findViewById(R.id.tv_cheatMode);

        count = 0;
        max = 100;
        min = 0;
        player = 0;

        answer = (int) (Math.random() * (max - (min - 1)) + min);
        isPlayer1 = true;
        playerName = "";
    }

    private void cleanInput() {
        et_inputNumber.setText("");
    }

    private void changeBadGuySay(String s) {
        ((TextView) findViewById(R.id.tv_badGuySay)).setText(s);
    }

    private void changePlayerSay(String s) {
        ((TextView) findViewById(R.id.tv_player1Say)).setText(s);
        ((TextView) findViewById(R.id.tv_player2Say)).setText(s);
    }

    private void changeCheatMode(String s) {
        ((TextView) findViewById(R.id.tv_cheatMode)).setText(s);
    }
}