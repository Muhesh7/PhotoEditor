package com.example.prac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.RelativeLayout;


import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        Intent intent=getIntent();
        String s=intent.getStringExtra("uri");

        int height;
        int width;

        Fragment fragment;
        RelativeLayout relativeLayout =findViewById(R.id.act);
        if(this.getResources().getConfiguration().orientation== ORIENTATION_LANDSCAPE)
        { height =getResources().getDisplayMetrics().heightPixels;
            width =getResources().getDisplayMetrics().heightPixels;
            relativeLayout.setGravity(Gravity.CENTER_HORIZONTAL);
            relativeLayout.setMinimumHeight(height);}
        else {height =getResources().getDisplayMetrics().heightPixels;
            width =getResources().getDisplayMetrics().widthPixels;
            relativeLayout.setGravity(Gravity.CENTER);}
        fragment = ListFrag.newInstance(width,height,s);

        getSupportFragmentManager().beginTransaction().replace(R.id.canvass,fragment)
                .replace(R.id.tool1,new ToolOne())
                .replace(R.id.tool2,new ToolTwo()).commit();
    }
}
