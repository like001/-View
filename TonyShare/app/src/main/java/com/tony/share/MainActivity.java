package com.tony.share;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tony.share.ui.StepProgressActivity;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{


    Button mBtnStep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBtnStep = (Button) findViewById(R.id.btn_step);
        mBtnStep.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_step:
                Intent pIntent = new Intent(this, StepProgressActivity.class);
                startActivity(pIntent);
                break;
            default:
                break;
        }

    }
}
