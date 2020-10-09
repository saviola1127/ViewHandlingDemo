package com.savy.viewgroupevent;

import java.rmi.MarshalledObject;
import java.util.ArrayList;
import java.util.List;

public class ViewGroup extends View {

    private View[] children;

    private TouchTarget firstTouchTarget = null;

    private List<View> childList = new ArrayList<>();

    public ViewGroup(int left, int top, int right, int bottom) {
        super(left, top, right, bottom);
    }

    public void addView(View view) {
        if (view == null) {
            return;
        }
        childList.add(view);
        children = childList.toArray(new View[childList.size()]);
    }

    public boolean onInterceptTouchEvent(MotionEvent event) {

        return true;
    }

    //屏幕驱动产生事件，ViewGroup主要负责分发事件
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean handled = false;
        boolean intercepted = onInterceptTouchEvent(event);

        int actionMasked = event.getActionMasked();

        if (!intercepted && actionMasked != MotionEvent.ACTION_CANCEL) {
            //分发事件给子view, 通过x和y值坐标来判断合适的子view

            if (actionMasked == MotionEvent.ACTION_DOWN) {

                //down event
                //move事件不会经过for循环，太好性能
                for (int i = children.length - 1; i >= 0; i--) {
                    //从最小层的开始遍历，考虑那些部分被遮盖的场景
                    View child = children[i];

                    int x = event.getX();
                    int y = event.getY();
                    boolean isContained = child.isContained(x, y);
                    if (!isContained) {
                        continue;
                    }

                    //分发事件
                    if (dispatchTransformedTouchEvent(event, child)) {
                        //说明有子控件有消费行为 setClickListener consume true
                        handled = true;
                        //找到了view，不需要再遍历

                        addTouchTarget(child);
                        break;
                    }
                }
            }

            //move event
        }


        if (firstTouchTarget == null) {
            handled = dispatchTransformedTouchEvent(event, null);
        } else {
            //move handling
            TouchTarget touchTarget = firstTouchTarget;
            while (touchTarget != null) {
                final TouchTarget next = touchTarget.next;

                if (dispatchTransformedTouchEvent(event, touchTarget.child)) {
                    handled = true;
                }

                touchTarget = next;
            }
        }
        //onTouchEvent(event);
        return handled;
    }


    private TouchTarget addTouchTarget(View child) {
        final TouchTarget target = TouchTarget.obtain(child);
        target.next = firstTouchTarget;
        firstTouchTarget = target;
        return target;
    }

    static class TouchTarget {

        private View child;
        public TouchTarget next;

        //回收池设计，避免每次事件都申请内存
        private static TouchTarget touchTargetPool;
        private static final Object objectLocker = new Object();
        private static int poolSize;

        public static TouchTarget obtain(View child) {
            TouchTarget touchTarget;
            synchronized (objectLocker) {
                if (touchTargetPool == null) {
                    touchTarget = new TouchTarget();
                } else {
                    touchTarget = touchTargetPool;
                }

                touchTargetPool = touchTarget.next;
                poolSize --;
                touchTarget.next = null;
            }

            touchTarget.child = child;
            return touchTarget;
        }
    }

    /***
     * 如果有child，那就代表分发给子view，坐标值会转换
     * 如果没有，那就自己调用onTouchEvent消费
     * @param event
     * @param child
     * @return
     */
    private boolean dispatchTransformedTouchEvent(MotionEvent event, View child) {

        boolean handled = false;

        if (child == null) {
            //System.out.println("child View now dispatch Event!!! --------->");
            handled = super.dispatchTouchEvent(event);
        } else {
            //System.out.println("child View now dispatch Event!!! --------->");
            handled = child.dispatchTouchEvent(event);
        }
        return handled;
    }

}
