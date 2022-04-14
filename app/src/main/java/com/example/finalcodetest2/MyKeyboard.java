package com.example.finalcodetest2;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import android.widget.LinearLayout;

public class MyKeyboard extends LinearLayout implements View.OnClickListener {

    // constructors
    public MyKeyboard(Context context) {
        this(context, null, 0);
    }

    public MyKeyboard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyKeyboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    // keyboard keys (buttons)
    private Button btn_num1;
    private Button btn_num2;
    private Button btn_num3;
    private Button btn_num4;
    private Button btn_num5;
    private Button btn_num6;
    private Button btn_num7;
    private Button btn_num8;
    private Button btn_num9;
    private Button btn_num0;
    private Button btn_del;
//    private Button btn_enter;

    SparseArray<String> keyValues = new SparseArray<>();

    InputConnection inputConnection;

    private void init(Context context, AttributeSet attrs) {

        LayoutInflater.from(context).inflate(R.layout.keyboard, this, true);
        btn_num1 = findViewById(R.id.btn_num1);
        btn_num2 = findViewById(R.id.btn_num2);
        btn_num3 = findViewById(R.id.btn_num3);
        btn_num4 = findViewById(R.id.btn_num4);
        btn_num5 = findViewById(R.id.btn_num5);
        btn_num6 = findViewById(R.id.btn_num6);
        btn_num7 = findViewById(R.id.btn_num7);
        btn_num8 = findViewById(R.id.btn_num8);
        btn_num9 = findViewById(R.id.btn_num9);
        btn_num0 = findViewById(R.id.btn_num0);
        btn_del = findViewById(R.id.btn_del);
//        btn_enter = findViewById(R.id.btn_enter);

        btn_num1.setOnClickListener(this);
        btn_num2.setOnClickListener(this);
        btn_num3.setOnClickListener(this);
        btn_num4.setOnClickListener(this);
        btn_num5.setOnClickListener(this);
        btn_num6.setOnClickListener(this);
        btn_num7.setOnClickListener(this);
        btn_num8.setOnClickListener(this);
        btn_num9.setOnClickListener(this);
        btn_num0.setOnClickListener(this);
        btn_del.setOnClickListener(this);
//        btn_enter.setOnClickListener(this);

        keyValues.put(R.id.btn_num1, "1");
        keyValues.put(R.id.btn_num2, "2");
        keyValues.put(R.id.btn_num3, "3");
        keyValues.put(R.id.btn_num4, "4");
        keyValues.put(R.id.btn_num5, "5");
        keyValues.put(R.id.btn_num6, "6");
        keyValues.put(R.id.btn_num7, "7");
        keyValues.put(R.id.btn_num8, "8");
        keyValues.put(R.id.btn_num9, "9");
        keyValues.put(R.id.btn_num0, "0");
//        keyValues.put(R.id.btn_enter, "\n");
    }

    @Override
    public void onClick(View v) {

        if (inputConnection == null) return;

        if (v.getId() == R.id.btn_del) {
            CharSequence selectedText = inputConnection.getSelectedText(0);
            if (TextUtils.isEmpty(selectedText)) {
                inputConnection.deleteSurroundingText(1, 0);
            } else {
                inputConnection.commitText("", 1);
            }
        } else {
            String value = keyValues.get(v.getId());
            inputConnection.commitText(value, 1);
        }
    }

    public void setInputConnection(InputConnection ic) {
        this.inputConnection = ic;
    }
}