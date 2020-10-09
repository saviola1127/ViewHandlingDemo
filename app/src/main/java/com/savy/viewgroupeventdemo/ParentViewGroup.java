package com.savy.viewgroupeventdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class ParentViewGroup extends LinearLayout {


    public ParentViewGroup(Context context) {
        super(context);
    }

    public ParentViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ParentViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("DEBUG", "onInterceptTouchEvent ----> ParentViewGroup");
        return super.onInterceptTouchEvent(ev);
        //return true;
    }
//
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("DEBUG", "onTouchEvent ---> ParentViewGroup");
        return super.onTouchEvent(event);
    }
//
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        return false;
//    }
}
