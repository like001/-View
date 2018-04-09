package com.tony.share.ui;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.animation.AnimatorSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tony.share.R;

import java.util.Random;

/**
 * author: tolu
 * created on: 2018/4/9 11:36
 * description:
 */

public class LoveLayout extends RelativeLayout {
    Drawable[] drawables = new Drawable[6];
    private int intrinsicWidth;
    private int intrinsicHeight;
    private int measuredHeight;
    private int measuredWidth;
    private LayoutParams layoutParams;
    Random mRandom = new Random();
    Path mPath = new Path();
    Paint mPaint;
    private ValueAnimator bezierValueAnimator;

    public LoveLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        initViews();
        layoutParams = new LayoutParams(intrinsicWidth, intrinsicHeight);
        layoutParams.addRule(CENTER_HORIZONTAL,TRUE);
        layoutParams.addRule(ALIGN_PARENT_BOTTOM,TRUE);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPath.moveTo(500,500);
        mPath.lineTo(800,800);
//        mPath.quadTo(200,200,300,300);
    }

    private void initViews() {
        drawables[0] = getResources().getDrawable(R.drawable.heart_default);
        drawables[1] = getResources().getDrawable(R.drawable.ss_heart1);
        drawables[2] = getResources().getDrawable(R.drawable.ss_heart2);
        drawables[3] = getResources().getDrawable(R.drawable.ss_heart3);
        drawables[4] = getResources().getDrawable(R.drawable.ss_heart4);
        drawables[5] = getResources().getDrawable(R.drawable.ss_heart5);

        intrinsicWidth = drawables[0].getIntrinsicWidth()*3;
        intrinsicHeight = drawables[0].getIntrinsicHeight()*3;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measuredWidth = getMeasuredWidth();
        measuredHeight = getMeasuredHeight();
    }

    void addLove(){
        final ImageView imageView = new ImageView(getContext());
        imageView.setImageDrawable(drawables[mRandom.nextInt(drawables.length)]);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(layoutParams);
        addView(imageView);

        AnimatorSet animationSet = getAnimatioSet(imageView);
        animationSet.start();
        final ValueAnimator bezierValueAnimator = getBezierValueAnimator(imageView);
        bezierValueAnimator.setStartDelay(2000);
        bezierValueAnimator.start();
        bezierValueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                removeView(imageView);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private AnimatorSet getAnimatioSet(ImageView imageView) {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(imageView,"alpha",0.3f,1f);

        ObjectAnimator scaleX = ObjectAnimator.ofFloat(imageView,"scaleX",0.2f,1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(imageView,"scaleY",0.2f,1f);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(2000);
        set.playTogether(alpha,scaleX,scaleY);
        set.setTarget(imageView);
        return  set;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    ValueAnimator getBezierValueAnimator(final ImageView loveIv){
        // 曲线的两个顶点
        PointF pointF2 = getPonitF(2);
        PointF pointF1 = getPonitF(1);
        // 起点位置
        PointF pointF0 = new PointF((measuredWidth - intrinsicWidth) / 2, measuredHeight
                - intrinsicHeight);
        // 结束的位置
        PointF pointF3 = new PointF(mRandom.nextInt(measuredWidth), 0);
        // 估值器Evaluator,来控制view的行驶路径（不断的修改point.x,point.y）
        BezierEvaluator evaluator = new BezierEvaluator(pointF1, pointF2);
        // 属性动画不仅仅改变View的属性，还可以改变自定义的属性
        ValueAnimator animator = ValueAnimator.ofObject(evaluator, pointF0,
                pointF3);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 不断改变ImageView的x,y的值
                PointF pointF = (PointF) animation.getAnimatedValue();
                loveIv.setX(pointF.x);
                loveIv.setY(pointF.y);
                loveIv.setAlpha(1 - animation.getAnimatedFraction() + 0.1f);// 得到百分比
            }
        });
        animator.setTarget(loveIv);
        animator.setDuration(3000);
        return animator;
    }

    private PointF getPonitF(int i) {
        return new PointF(mRandom.nextInt(measuredWidth),mRandom.nextInt(measuredHeight));
    }
}
