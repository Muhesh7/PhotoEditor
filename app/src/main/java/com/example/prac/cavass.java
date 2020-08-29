package com.example.prac;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class cavass extends View {

        Paint brush;
        Paint mPaint,circlePaint;
        Context mContext;
        int[] colors;
        Canvas mCanvas;
        Paint mPaint2;
        Path circlePath;
        LinearGradient mLinearGradient;
        RadialGradient mRadialGradient;
        String uri;
        public cavass(Context context,String uri) {
            super(context);
            mContext=context;
            this.uri=uri;
            init();

        }

        public void init(){
            mPath2=new Path();
            mPath=new Path();
            colors = mContext.getResources().getIntArray(R.array.colors);
            mLinearGradient=new LinearGradient(0,0,20,20,Color.BLACK,Color.DKGRAY, Shader.TileMode.MIRROR);
            mRadialGradient=new RadialGradient(0,0,20,Color.RED,Color.BLACK, Shader.TileMode.REPEAT);
            brush=new Paint();
            brush.setStrokeWidth(4f);
            brush.setColor(Color.BLACK);
            brush.setAntiAlias(true);
            brush.setDither(true);
            brush.setStyle(Paint.Style.STROKE);
            brush.setStrokeJoin(Paint.Join.BEVEL);
            brush.setStrokeCap(Paint.Cap.BUTT);
             mPaint2=new Paint(Paint.LINEAR_TEXT_FLAG);
            mPaint2.setColor(Color.RED);
            mPaint2.setStrokeWidth(10);
            mPaint2.setTextSize(200f);
            mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaint.setTextSize(30f);
            mPaint.setStrokeWidth(10f);
            mPaint.setColor(Color.RED);
            circlePaint = new Paint();
            circlePath = new Path();
            circlePaint.setAntiAlias(true);
            circlePaint.setColor(Color.BLUE);
            circlePaint.setStyle(Paint.Style.STROKE);
            circlePaint.setStrokeJoin(Paint.Join.MITER);
            circlePaint.setStrokeWidth(4f);
        }
        int c=0;
        Bitmap b;
        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            b=BitmapFactory.decodeFile(uri);
            Log.d("URDD",uri);
            b = Bitmap.createScaledBitmap(b,w, h,false);
            mCanvas=new Canvas(b);
            x1=b.getWidth()/2;
                    y1=b.getHeight()/2;
            x2=b.getWidth()/2;
            y2=b.getHeight()/2;
        }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawBitmap(b,0,0,mPaint);
            if(i==1)
            {canvas.drawCircle(x1,y1,radi,brush); }
        if(i==2)
        {canvas.drawRect(x1,y1,x1+radi,y1+radi,brush); }
        if(i==3)
        {drawTriangle(canvas,brush,x1,y1,radi); }
        if(i==4)
        {canvas.drawLine(ux,uy,dx,dy,mPaint2);}
        if(rr==1)
        { canvas.rotate(rotate,b.getWidth()/2,b.getHeight()/2);
        rr=0; }
        if(n==1)
        {canvas.drawText(s,x2,y2,mPaint2);}

        super.onDraw(canvas);
    }
    Path mPath,mPath2;
        void drawPath(Canvas canvas)
        {
            canvas.drawPath(mPath2,brush);
        }
    public void drawTriangle(Canvas canvas, Paint paint, float x, float y, float width) {
        int halfWidth = (int) (width / 2);
        mPath = new Path();
        mPath.moveTo(x, y - halfWidth);
        mPath.lineTo(x - halfWidth, y + halfWidth);
        mPath.lineTo(x + halfWidth, y + halfWidth);
        mPath.lineTo(x, y - halfWidth);
        mPath.close();
        canvas.drawPath(mPath, paint);
    }

    float x1,x2,y1,y2;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventType = event.getActionMasked();
        if(i!=4) {
            if (eventType == MotionEvent.ACTION_DOWN) {
                x1 = event.getX();
                y1 = event.getY();
                invalidate();
            } else if (eventType == MotionEvent.ACTION_MOVE) {
                x1 = event.getX();
                y1 = event.getY();
                invalidate();
            } else if (eventType == MotionEvent.ACTION_UP) {
                x1 = event.getX();
                y1 = event.getY();
                invalidate();
            }
        }
        else {
            switch (event.getAction()){

                case MotionEvent.ACTION_DOWN:
                    ux=event.getX();
                    uy=event.getY();
                    break;

                case MotionEvent.ACTION_MOVE:
                    dx=event.getX();
                    dy=event.getY();
                    mCanvas.drawLine(ux,uy,dx,dy,mPaint2);
                    break;

                case MotionEvent.ACTION_UP:
                    dx=event.getX();
                    dy=event.getY();
                    invalidate();
                    break;
            }
        }
        if(eventType == MotionEvent.ACTION_DOWN)
        { if(event.getX()>x2&&event.getY()<y2) {
            if (eventType == MotionEvent.ACTION_UP) {
                x2 = event.getX();
                y2 = event.getY();
            }
            if (eventType == MotionEvent.ACTION_SCROLL) {
                mPaint2.setTextSize(mPaint2.getTextSize()+300);
            }
        }


        }
        return super.onTouchEvent(event);
    }
    float ux=0,dx=0 ;
    float uy=0,dy=0;
    float rotate=0;
    public Bitmap getCroppedBitmap() {
        Bitmap output = Bitmap.createBitmap(b.getWidth(),
                b.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect((int) (x1-radi), (int) (y1-radi),(int) (x1+radi),(int) (y1+radi));
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        canvas.drawText(s,x2,y1,paint);
        if(i==1)
        {canvas.drawCircle(x1, y1,radi, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(b,rect, rect,paint);}
        else if(i==2)
        {
            canvas.drawRect(x1,y1,x1+radi,y1+radi,paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            final Rect rect2 = new Rect((int) (x1), (int) (y1),(int) (x1+radi),(int) (y1+radi));
            canvas.drawBitmap(b,rect2, rect2,paint);
        }
        else if(i==3)
        {
            canvas.drawPath(mPath,paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(b,0,0,paint);
        }
        else if(i==4)
        {
            canvas.drawPath(mPath2,paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(b,0,0,paint);
        }
       // Bitmap _bmp = Bitmap.createScaledBitmap(output, (i)radi, radi, false);
        //return _bmp;
        return output;
    }
    int i=0;
    void circlecrop()
    {
        i=1;
        invalidate();
    }
    void rectcrop()
    {
        i=2;
        invalidate();
    }
    void tricrop()
    {
        i=3;
        invalidate();
    }
    void freecrop()
    {
        i=4;
        invalidate();
    }
    float radi=50;
    void setRadius(int j)
    {
        radi=j;
        invalidate();
    }
    int rr;
    void setRotate(int j)
    {  rotate=j;
    rr=1;
        invalidate();
    }
    String s;
    int n=0;
    void putString(String a)
    {  n=1;
        s=a;
    }
    void setColors(int i)
    {
        c=i;
        invalidate();
    }
}
