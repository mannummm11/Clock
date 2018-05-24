package com.rightmove.viewtut;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class CustomCircle extends View {

    protected int circle_color,label_color;
    protected String circle_label;
    protected Paint circlePaint;

    int widthHalf,heightHalf;
    int radius;

    String val="1";
    int valX=540,valY=990;

    String hr="";
    int hrX=540,hrY=990;


    public CustomCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);
    }



    protected void initView(Context context,AttributeSet attrs){
        circlePaint=new Paint();

        TypedArray a=context.obtainStyledAttributes(attrs,R.styleable.CustomCircle,0,0);

        circle_color=a.getInt(R.styleable.CustomCircle_cirColor,0);
        label_color=a.getInt(R.styleable.CustomCircle_labelColor,0);
        circle_label=a.getString(R.styleable.CustomCircle_cirLabel);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        widthHalf=getMeasuredWidth()/2;
        heightHalf=getMeasuredHeight()/2;

        if(widthHalf>heightHalf)radius=widthHalf/4;
        else radius=heightHalf/4;

        circlePaint.setStyle(Paint.Style.FILL);// for circle
        circlePaint.setAntiAlias(true);

        circlePaint.setColor(circle_color);

        canvas.drawCircle(widthHalf,heightHalf,radius,circlePaint);

        circlePaint.setColor(label_color); // for center text color
        circlePaint.setTextAlign(Paint.Align.CENTER);
        circlePaint.setTextSize(50);
        circlePaint.setFakeBoldText(true);


        canvas.drawText(val,valX,valY,circlePaint);

        circlePaint.setColor(label_color); // for minute niddle
        circlePaint.setFakeBoldText(true);

        canvas.drawLine(widthHalf,heightHalf,valX,valY,circlePaint);

        circlePaint.setColor(label_color); // for hr niddle

        canvas.drawLine(widthHalf,heightHalf,hrX+20,hrY+20,circlePaint);
        canvas.drawText(hr,hrX,hrY,circlePaint); // hr time

        invalidate();
        requestLayout();
    }

    public void setCircle_color(int circle_color) {
        this.circle_color = circle_color;
        invalidate();
        requestLayout();
    }

    public void setValuesOn(int angle,int val){


        valX=(int)((radius*Math.cos(angle * 0.0174532925))+widthHalf);
        valY=(int)((radius*Math.sin(angle * 0.0174532925))+heightHalf);
        this.val=String.valueOf(val);


        invalidate();
        requestLayout();
    }

    public void setValuesOnHr(int angle,int val){

        hrX=(int)((radius*Math.cos(angle * 0.0174532925))+widthHalf);
        hrY=(int)((radius*Math.sin(angle * 0.0174532925))+heightHalf);
        this.hr=String.valueOf(val);

        invalidate();
        requestLayout();
    }
}
