package com.example.mijuegosinnombre.TileMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.view.View;

import com.example.mijuegosinnombre.R;



public class Textures extends View {

    public Drawable marble;
    public Bitmap marbleBitmap;
    public Bitmap [] splashesR = new Bitmap[4];
    public Bitmap [] splashesB = new Bitmap[4];

    public Textures(Context context) {
        super(context);
        marble = context.getResources().getDrawable(R.mipmap.marble_foreground);
        marbleBitmap = BitmapFactory.decodeResource(context.getResources(),R.mipmap.marble_foreground);
        Bitmap sp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.mipmap.splash1), 200, 200, true);
        splashesB[0] = colorize(sp,Color.CYAN);
        splashesR[0] = colorize(sp,Color.RED);
        sp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.mipmap.splash2), 200, 200, true);
        splashesB[1] = colorize(sp,Color.CYAN);
        splashesR[1] = colorize(sp,Color.RED);
        sp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.mipmap.splash3), 200, 200, true);
        splashesB[2] = colorize(sp,Color.CYAN);
        splashesR[2] = colorize(sp,Color.RED);
        sp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.mipmap.splash4), 200, 200, true);
        splashesB[3] = colorize(sp,Color.CYAN);
        splashesR[3] = colorize(sp,Color.RED);
    }

    public Bitmap colorize(Bitmap srcBmp, int dstColor) {

        int width = srcBmp.getWidth();
        int height = srcBmp.getHeight();

        float srcHSV[] = new float[3];
        float dstHSV[] = new float[3];

        Bitmap dstBitmap = Bitmap.createBitmap(width, height,Bitmap.Config.ARGB_8888);

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                int pixel = srcBmp.getPixel(col, row);
                int alpha = Color.alpha(pixel);
                Color.colorToHSV(pixel, srcHSV);
                Color.colorToHSV(dstColor, dstHSV);

                // If it area to be painted set only value of original image
                dstHSV[2] = srcHSV[2];  // value
                dstBitmap.setPixel(col, row, Color.HSVToColor(alpha, dstHSV));
            }
        }

        return dstBitmap;
    }
}

