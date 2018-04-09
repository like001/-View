package com.tony.share.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.tony.share.R;

/**
 * author: tolu
 * created on: 2018/4/9 20:00
 * description:
 */

public class AlphabetActivity extends AppCompatActivity {


    TextView mTvLetter;
    Alphabet mAlphabet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alpha);
        initView();
        initData();
    }

    private void initData() {
    }

    private void initView() {
        mTvLetter = (TextView) findViewById(R.id.tv_letter);
        mAlphabet = (Alphabet) findViewById(R.id.sb_letter);
        mAlphabet.setSideBarTouchEvent(new Alphabet.SideBarTouchEvent() {
            @Override
            public void onTouch(String str, boolean isTouch) {
                if(!isTouch){
                    mTvLetter.setText("");
                }else {
                    mTvLetter.setText(str);
                }
            }
        });
    }
}
