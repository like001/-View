package com.tony.share;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tony.share.opengl.OpenGLActivity;
import com.tony.share.ui.LoveActivity;
import com.tony.share.ui.StepProgressActivity;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{


    private static final String TAG = "MainActivity";
    Button mBtnStep;
    private Button mBtnOpenGL;
    private Button mBtnLove;
    private Button mBtnScale;
    public static final boolean IS_TEST = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        if(IS_TEST){
            test();
        }
    }

    private void test() {
        ValueAnimator animator = ValueAnimator.ofInt(mBtnScale.getWidth(), 1080);
        animator.setDuration(3000);
        animator.setRepeatCount(4);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int curr = (int) animation.getAnimatedValue();
                float animatedFraction = animation.getAnimatedFraction();
                mBtnScale.getLayoutParams().width = curr;
                mBtnScale.requestLayout();
                Log.d(TAG,"curr:"+curr);
                Log.d(TAG,"ani+"+animatedFraction);
            }
        });
        animator.start();
    }

    private void initView() {
        mBtnStep = (Button) findViewById(R.id.btn_step);
        mBtnStep.setOnClickListener(this);
        mBtnOpenGL = (Button) findViewById(R.id.btn_opengl);
        mBtnOpenGL.setOnClickListener(this);
        mBtnLove = (Button) findViewById(R.id.btn_love);
        mBtnLove.setOnClickListener(this);
        mBtnScale = (Button) findViewById(R.id.btn_scale);
    }

    @Override
    public void onClick(View v) {
        Intent pIntent;
        switch (v.getId()){
            case R.id.btn_step:
                pIntent = new Intent(this, StepProgressActivity.class);
                startActivity(pIntent);
                break;
            case R.id.btn_opengl:
                pIntent = new Intent(this, OpenGLActivity.class);
                startActivity(pIntent);
                break;
            case R.id.btn_love:
                pIntent = new Intent(this, LoveActivity.class);
                startActivity(pIntent);
                break;
            default:
                break;
        }

    }
}
