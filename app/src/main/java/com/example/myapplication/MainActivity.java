package com.example.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.Bundle;



import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends Activity {

    private Context context;
    private static final String TAG = "test";
    View view;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate :MainActivity 1");
        context = this;
        UiModeManager mUiModeManager = (UiModeManager) getSystemService(UI_MODE_SERVICE);


        this.findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        this.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //先去截屏，
                Bitmap bitmap = ScreenShortHelper.getInstence(MainActivity.this).captureScreenshot();
                Log.e(TAG, "Bitmap: "+bitmap);
                //转成Drawable
                BitmapDrawable drawable= new BitmapDrawable(bitmap);

//                Drawable drawable = MainActivity.this.getResources().getDrawable(R.drawable.screen_day);
                dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.dialog_test_fullscreen);
                //给弹窗设置一个很高的层级。  TYPE_POINTER = FIRST_SYSTEM_WINDOW+18;
                dialog.getWindow().setType(2018);
                dialog.show();
                //将Drawable设置给Dialog的背景
                dialog.getWindow().setBackgroundDrawable(drawable);
                //设置Dialog为全屏
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                //切换主题颜色
                mUiModeManager.setNightMode(UiModeManager.MODE_NIGHT_YES);
                //延迟1秒将弹窗消失掉。
                handler.sendEmptyMessageDelayed(1, 1000);
                Log.e(TAG, "onClick:UiModeManager.MODE_NIGHT_YES");

            }
        });

        this.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = ScreenShortHelper.getInstence(MainActivity.this).captureScreenshot();
                BitmapDrawable drawable= new BitmapDrawable(bitmap);

//                Drawable drawable = MainActivity.this.getResources().getDrawable(R.drawable.screen_night);
                dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.dialog_test_fullscreen);
                dialog.getWindow().setType(2018);
                dialog.show();
                dialog.getWindow().setBackgroundDrawable(drawable);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                mUiModeManager.setNightMode(UiModeManager.MODE_NIGHT_NO);
                handler.sendEmptyMessageDelayed(1, 1000);
                Log.e(TAG, "onClick:UiModeManager.MODE_NIGHT_NO");


            }
        });
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage( Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    dialog.dismiss();
                    dialog = null;
                    break;
                case 2:
                    break;
            }
        }
    };
}