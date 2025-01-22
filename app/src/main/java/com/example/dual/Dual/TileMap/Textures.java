package com.example.dual.Dual.TileMap;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.example.dual.R;

import java.net.URL;


public class Textures extends View {

    public Drawable marble;

    public Textures(Context context) {
        super(context);
        marble = context.getResources().getDrawable(R.mipmap.marble_foreground);
    }
}

