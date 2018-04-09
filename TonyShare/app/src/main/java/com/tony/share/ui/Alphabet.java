package com.tony.share.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * author: tolu
 * created on: 2018/4/2 15:50
 * description:
 */

public class Alphabet extends View {

    private static final String TAG = "Alphabet";
    private char[] mCharArray = {'A','B','C','D','E','F','G',
            'H','I','J','K','L','M','N',
            'O','P','Q','D','E','F','G',
            'A','B','C','D','E','F','G','#'};
    private Paint mPaint;
    int mSingleHeight;
    private int mLastSelect;
    boolean mShowSelectLetter;

    public Alphabet(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GREEN);
        mPaint.setTextSize(50);
//        this.setBackgroundColor(Color.BLUE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();
        int i = (int) (y / mSingleHeight + 0.5);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastSelect = i;
                if(mSideBarTouchListener != null){
                    mSideBarTouchListener.onTouch(mCharArray[mLastSelect]+"", true);
                    mShowSelectLetter = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(mLastSelect == i){
                    return true;
                }
                if(mSideBarTouchListener != null){
                    mSideBarTouchListener.onTouch(mCharArray[mLastSelect]+"", false);
                }
                mLastSelect = i;
                if(mSideBarTouchListener != null){
                    mSideBarTouchListener.onTouch(mCharArray[mLastSelect]+"",true);
                    mShowSelectLetter = true;
                }
                Log.d(TAG, "onTouchEvent: "+mLastSelect);
                break;
            case MotionEvent.ACTION_UP:
                if(mSideBarTouchListener != null){
                    mSideBarTouchListener.onTouch(mCharArray[mLastSelect]+"",false);
                    mShowSelectLetter = true;
                }
                break;
            default:
                break;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int length = mCharArray.length;
        int height = getMeasuredHeight();
        int width = getMeasuredWidth();
        mSingleHeight = height / length;
        for(int i=0;i<length;i++){
            if(mShowSelectLetter && i == mLastSelect){
                mPaint.setTextSize(70);
                mPaint.setColor(Color.RED);
            }
            String letter = mCharArray[i]+"";
            float textWidth = mPaint.measureText(letter);
            float spcaeWidth = width-getPaddingLeft()-getPaddingRight();
            int x = (int)(getPaddingLeft()+(spcaeWidth-textWidth)/2);
            Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
            Log.d(TAG, "onDraw: "+fontMetrics.top+"#"+fontMetrics.bottom);
            int baseLine = (int)(mSingleHeight*i+(mSingleHeight-(fontMetrics.top+fontMetrics.bottom))/2);
            canvas.drawText(letter,x,baseLine,mPaint);
            if(mShowSelectLetter && i == mLastSelect){
                mPaint.setTextSize(50);
                mPaint.setColor(Color.GREEN);
            }
        }
    }

    SideBarTouchEvent mSideBarTouchListener;
    public void setSideBarTouchEvent(SideBarTouchEvent listener){
        this.mSideBarTouchListener = listener;
    }
    public interface SideBarTouchEvent{
        void onTouch(String str, boolean isTouch);
    }
}
