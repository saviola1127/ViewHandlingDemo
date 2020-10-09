package com.savy.viewgroupeventdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class ChildViewGroup extends LinearLayout {


    public ChildViewGroup(Context context) {
        super(context);
    }

    public ChildViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChildViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("DEBUG", "onInterceptTouchEvent ---> ChildViewGroup");
        //return super.onInterceptTouchEvent(ev);
        return true;
    }
////
////    @Override
////    public boolean dispatchTouchEvent(MotionEvent ev) {
////        return false;
////    }
////
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("DEBUG", "onTouchEvent ---> ChildViewGroup");
        return super.onTouchEvent(event);
    }
}
