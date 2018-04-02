package com.tony.share.ui;

import android.os.Bundle;
import android.support.v4.util.TimeUtils;
import android.support.v7.app.AppCompatActivity;

import com.tony.share.R;

/**
 * Created by tolu on 2018/4/1.
 */

public class StepProgressActivity extends AppCompatActivity {

    StepProgressBar mSpb;
    final int mTotalStep = 10000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_pro);
        initView();
        initData();
    }

    private void initData() {
        mSpb.setTotalStep(mTotalStep);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<=mTotalStep;i+= 500){
                    mSpb.setProgress(i);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void initView() {
        mSpb = (StepProgressBar) findViewById(R.id.spb);
    }
}
