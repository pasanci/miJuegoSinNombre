package com.example.dual.Dual.TileMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.example.dual.R;



public class Textures extends View {

    public Drawable marble;
    public Bitmap marbleBitmap;

    public Textures(Context context) {
        super(context);
        marble = context.getResources().getDrawable(R.mipmap.marble_foreground);
        marbleBitmap = BitmapFactory.decodeResource(context.getResources(),R.mipmap.marble_foreground);
    }
}

