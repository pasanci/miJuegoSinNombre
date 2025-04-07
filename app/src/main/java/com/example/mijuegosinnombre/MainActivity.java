package com.example.mijuegosinnombre;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import com.example.mijuegosinnombre.Main.Main;

public class MainActivity extends Activity {

    private Main main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        int topMargin = 0;
        int bottomMargin = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Display display = getWindowManager().getDefaultDisplay();
            if(display.getCutout()!=null) {
                topMargin = display.getCutout().getSafeInsetTop();
                bottomMargin = display.getCutout().getSafeInsetBottom();
            }
        }

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.main = new Main(this, topMargin, bottomMargin);
        setContentView(main);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onPause() {
        main.pause();
        super.onPause();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    public void onBackPressed() {
        this.main.notifyBackPressed();
        //super.onBackPressed();
    }
}