package com.tony.share.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.tony.share.R;

/**
 * author: tolu
 * created on: 2018/4/9 11:35
 * description:
 */

public class LoveActivity extends AppCompatActivity {

    LoveLayout mLL;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mLL.addLove();
            mHandler.sendEmptyMessageDelayed(1,800);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love);
        initView();
        initData();
    }

    private void initData() {
        mHandler.sendEmptyMessage(1);
//        for(int i=0;i<50;i++){
//            mLL.addLove();
//        }
    }

    private void initView() {
        mLL = (LoveLayout) findViewById(R.id.ll_love);
    }
}
