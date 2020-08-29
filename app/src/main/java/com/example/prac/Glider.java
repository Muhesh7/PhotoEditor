package com.example.prac;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;


public class Glider {

    public static void loader(ImageView img, String uri)
    {
        Bitmap bitmap= BitmapFactory.decodeFile(uri);
        Bitmap.createScaledBitmap(bitmap,120,120,false);
        Glide.with(img.getContext()).load(bitmap).into(img);

    }


    @BindingAdapter("android:imageUrl")
    public static void img(ImageView view,String uri)
    {
        loader(view,uri);
    }
}
