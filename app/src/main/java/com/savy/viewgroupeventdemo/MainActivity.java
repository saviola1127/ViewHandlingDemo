package com.savy.viewgroupeventdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "SAVY-DEBUG";
    private ParentViewGroup parentViewGroup;
    private ChildViewGroup childViewGroup;
    private MyView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parentViewGroup = findViewById(R.id.viewgroup1);
        myView = findViewById(R.id.tv);
        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "MyView Clicked", Toast.LENGTH_SHORT).show();
                Log.e("DEBUG", "MyView --------- clicked!!!!!!");
            }
        });

        myView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("DEBUG", "MyView --------- touched!!!!!!");
                return false;
            }
        });


        childViewGroup = findViewById(R.id.viewgroup2);
        childViewGroup.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e(TAG, "onTouch ----> ChildViewGroup" + childViewGroup);
                return false;
            }
        });
    }
}
