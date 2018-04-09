package com.tony.share.ui;

import android.animation.TypeEvaluator;
import android.graphics.PointF;
import android.icu.text.DisplayContext;

/**
 * author: tolu
 * created on: 2018/4/9 14:22
 * description:
 */

public class BezierEvaluator implements TypeEvaluator<PointF> {
    
    private PointF point1,point2;
    
    public BezierEvaluator(PointF point1,PointF point2){
        this.point1 = point1;
        this.point2 = point2;
    }
    @Override
    public PointF evaluate(float t, PointF startValue, PointF endValue) {
        PointF point = new PointF();
        point.x = startValue.x*(1-t)*(1-t)*(1-t)
                + 3*point1.x*t*(1-t)*(1-t)
                + 3*point2.x*t*t*(1-t)
                + endValue.x*t*t*t;
        point.y = startValue.y*(1-t)*(1-t)*(1-t)
                + 3*point1.y*t*(1-t)*(1-t)
                + 3*point2.y*t*t*(1-t)
                + endValue.y*t*t*t;
        return point;
    }
}
