package com.savy.viewgroupevent;

public class View {

    private int left;
    private int right;
    private int top;
    private int bottom;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private OnClickListener clickListener;
    private OnTouchListener touchListener;

    public OnClickListener getClickListener() {
        return clickListener;
    }

    public void setClickListener(OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public OnTouchListener getTouchListener() {
        return touchListener;
    }

    public void setTouchListener(OnTouchListener touchListener) {
        this.touchListener = touchListener;
    }

    public View(int left, int top, int right, int bottom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    public boolean isContained(int x, int y) {
        if (x >= left && x <= right
                && y >= top && y <= bottom) {
            return true;
        }
        return false;
    }

    boolean dispatchTouchEvent(MotionEvent event) {

        //System.out.println("view.dispatchTouchEvent!! -------->");

        boolean result = false;
        if (touchListener != null && touchListener.onTouch(this, event)) {
            result = true;
        }

        //click的优先级比touch低
        if (!result && onTouchEvent(event)) {
            result = true;
        }

        return result;
    }

    boolean onTouchEvent(MotionEvent event) {
        if (clickListener != null) {
            clickListener.onClick(this);
            return true;
        }
        return false;
    }
}
