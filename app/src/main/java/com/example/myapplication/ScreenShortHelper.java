package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.hardware.display.DisplayManager;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.DisplayAddress;
import android.view.SurfaceControl;

public class ScreenShortHelper {


    Context context;

    public static ScreenShortHelper mScreenShortHelper;

    public  static ScreenShortHelper getInstence(Context context){
        if (mScreenShortHelper == null) {
            mScreenShortHelper  = new ScreenShortHelper(context);
        }
     return mScreenShortHelper;
    }

    private ScreenShortHelper(Context context) {
        this.context = context;
    }

    private String TAG;

    private  DisplayManager mDisplayManager;

    public static final int DEFAULT_DISPLAY = 0;

    @SuppressLint("NewApi")
    public Bitmap captureScreenshot() {

        DisplayMetrics displayMetrics = new DisplayMetrics();

        Rect crop = new Rect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);

        mDisplayManager =context.getSystemService(DisplayManager.class);

        int width = crop.width();
        int height = crop.height();
        Bitmap screenshot = null;
        final Display display = mDisplayManager.getDisplay(DEFAULT_DISPLAY);
        final DisplayAddress address = display.getAddress();
        if (!(address instanceof DisplayAddress.Physical)) {
            Log.e(TAG, "Skipping Screenshot - Default display does not have a physical address: "
                    + display);
        } else {
            final DisplayAddress.Physical physicalAddress = (DisplayAddress.Physical) address;

            final IBinder displayToken = SurfaceControl.getPhysicalDisplayToken(
                    physicalAddress.getPhysicalDisplayId());
            final SurfaceControl.DisplayCaptureArgs captureArgs =
                    new SurfaceControl.DisplayCaptureArgs.Builder(displayToken)
                            .setSourceCrop(crop)
                            .setSize(width, height)
                            .build();
            final SurfaceControl.ScreenshotHardwareBuffer screenshotBuffer =
                    SurfaceControl.captureDisplay(captureArgs);
            screenshot = screenshotBuffer == null ? null : screenshotBuffer.asBitmap();
        }
        return screenshot;
    }

    private Display getDefaultDisplay() {
        return mDisplayManager.getDisplay(DEFAULT_DISPLAY);
    }
}
