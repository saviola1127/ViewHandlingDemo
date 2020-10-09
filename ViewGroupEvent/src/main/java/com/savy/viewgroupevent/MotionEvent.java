package com.savy.viewgroupevent;

public class MotionEvent {

    public static final int ACTION_MASK             = 0xff;

    public static final int ACTION_DOWN             = 0;

    public static final int ACTION_UP               = 1;

    public static final int ACTION_MOVE             = 2;

    public static final int ACTION_CANCEL           = 3;

    private int actionMasked;

    private int x;
    private int y;

    public MotionEvent(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getActionMasked() {
        return actionMasked;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setActionMasked(int actionMasked) {
        this.actionMasked = actionMasked;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
