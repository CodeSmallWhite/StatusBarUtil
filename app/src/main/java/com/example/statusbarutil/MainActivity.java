package com.example.statusbarutil;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.mylibrary.StatusBarUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout rootLayout = findViewById(R.id.status_bar_layout);
        StatusBarUtil statusBarUtil = StatusBarUtil.initStatusBar(this);
        statusBarUtil.isFullStatusBar(true,rootLayout,false, Color.YELLOW);


    }
}