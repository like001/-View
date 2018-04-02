package com.tony.share.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by tolu on 2018/4/1.
 */

public class StepProgressBar extends View {
    private int mViewWidth = 600;
    private int mViewHeight = 600;
    private Paint mPaint;
    private float mRoundWidth = 30;
    private int mRoundColor;
    private float mSweepAngle;
    private float mStartAng;
    private int mProgressColor;
    private int mProgress = 1;
    private int mTextColor;
    private float mTextSize;
    private int mTotalStep;

    public StepProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mRoundColor = Color.BLUE;
        mProgressColor = Color.RED;
        mTextColor = Color.GRAY;
        mStartAng = 120;
        mSweepAngle = 300;
        mTextSize = 50;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        int centerX = mViewWidth / 2;
        int centerY = mViewHeight / 2;
        mPaint.setStrokeWidth(mRoundWidth);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);

//        mPaint.setStrokeJoin(Paint.Join.MITER);
//        mPaint.setStrokeCap(Paint.Cap.SQUARE);

        mPaint.setColor(mRoundColor);
        mPaint.setStyle(Paint.Style.STROKE);

        int radius = (int) ((centerX - mRoundWidth) / 2);
        RectF oval = new RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
        canvas.drawArc(oval, mStartAng, mSweepAngle, false, mPaint);

        mPaint.setColor(mProgressColor);
        float percent = getProgress();
        canvas.drawArc(oval,mStartAng,mSweepAngle*percent,false,mPaint);
        mPaint.reset();
        mPaint.setColor(mTextColor);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(mTextSize);
        String text = getText();
        Rect textBounds = new Rect();
        mPaint.getTextBounds(text,0,text.length(),textBounds);
        int dx = (getWidth()-textBounds.width())/2;
        Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
        int baseLine = (getHeight()/2+(fontMetrics.bottom-fontMetrics.top)/2-fontMetrics.bottom);
        canvas.drawText(text,dx,baseLine,mPaint);
    }

    public float getProgress() {
        return (float) mProgress/mTotalStep;
    }

    public String getText() {
        return (int)(getProgress()*mTotalStep)+"";
    }

    public void setTotalStep(int total){
        mTotalStep = total;
    }

    public void setProgress(int pro){
        this.mProgress = pro;
        postInvalidate();
    }
}
