package com.savy.viewgroupevent;

public class Activity {

    public static void main(String[] args) {

        ViewGroup viewGroup1 = new ViewGroup(0, 0, 1080, 1920) {

            @Override
            public boolean onInterceptTouchEvent(MotionEvent event) {
                return false;
            }
        };
        viewGroup1.setName("parentViewGroup");

        ViewGroup viewGroup2 = new ViewGroup(0, 0, 500, 500);
        viewGroup2.setName("childViewGroup");

        View myView = new View(0, 0, 150, 150);
        myView.setName("my View");

        viewGroup1.setTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                System.out.println("parent View Group touched!!! ------->");
                return false;
            }
        });


        viewGroup2.setTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                System.out.println("child View Group touched!!! -------> ");
                return false;
            }
        });

        myView.setClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("myView now clicked!!! ------>");
            }
        });

        //viewGroup2.addView(myView);
        viewGroup1.addView(viewGroup2);
        viewGroup1.addView(myView);

        MotionEvent motionEvent = new MotionEvent(100, 100);
        motionEvent.setActionMasked(MotionEvent.ACTION_DOWN);
        viewGroup1.dispatchTouchEvent(motionEvent);

        motionEvent.setActionMasked(MotionEvent.ACTION_UP);
        viewGroup1.dispatchTouchEvent(motionEvent);
    }
}
