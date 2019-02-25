package bar.appbarbottom.canvasdraw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.Arrays;

public class DrawView extends View {
    Paint paint = new Paint();
    Paint paintblack = new Paint();
    Paint paintRed = new Paint();
    Paint paintGreen = new Paint();
    float x,y;

    int intX,intY,modX,modY;

    Bitmap bitmap;
    protected Rect mContentRect = new Rect();
    int sizePixWidth,sizePixHeight,sizePixAll;

    int[] completedPixels ={0,1,0,0,1,0,0,1}; //10 пикселей

    private void init() {
        paint.setColor(Color.DKGRAY);
        paintblack.setColor(Color.BLACK);
        paintRed.setColor(Color.RED);
        paintGreen.setColor(Color.GREEN);

    }

    public DrawView(Context context) {
        super(context);
        init();


    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.incon2);

        String info =
                String.format("Info: size = %s x %s, bytes = %s (%s), config = %s",
                        bitmap.getWidth(),
                        bitmap.getHeight(),
                        bitmap.getByteCount(),
                        bitmap.getRowBytes(),
                        bitmap.getConfig());
        Log.d("log", info);
//
//        matrix = new Matrix();
//        matrix.postRotate(45);
//        matrix.postScale(2, 3);
//        matrix.postTranslate(200, 50);
//
//        rectSrc = new Rect(0, 0, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
//        rectDst = new Rect(300, 100, 500, 200);

    }

    public DrawView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mContentRect.set(
                getPaddingLeft(),
                getPaddingTop(),
                getWidth() - getPaddingRight(),
                getHeight() - getPaddingBottom());

        sizePixWidth = mContentRect.width()/100;
        sizePixHeight = mContentRect.height()/100;
        sizePixAll =sizePixWidth*sizePixHeight;
       String s = String.valueOf(sizePixAll);
        Log.d(" SizePixWidth:  ", s);
    }

    @Override
    public void onDraw(Canvas canvas) {
      //  paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        canvas.drawARGB(80, 102, 204, 255);
        canvas.drawBitmap(bitmap, 50, 50, paint);
        canvas.scale(1,1);
//        canvas.translate();
//        canvas.drawBitmap(bitmap, matrix, paint);
//        canvas.drawBitmap(bitmap, rectSrc, rectDst, paint);

     //    drawCircle(canvas);
         massivDraw(canvas);
         intX = (int) x;
         intY = (int) y;
         modX = intX % 100;
         modY = intY % 100;
         intX = intX - modX;
         intY = intY - modY;

         if (x !=0) {
             canvas.drawRect(intX, intY, intX + 100, intY + 100, paintRed);
         }

//сетка
        for (int a=0;a<10000;a=a+100) {
            canvas.drawLine(0, 100+a, 2000, 100+a, paintblack); // горизонтальные линии
            canvas.drawLine(100+a, 0, 100+a, 2000, paintblack);   // вертикальные линии
        }

    }
    // черная хрень
    public void drawCircle(Canvas canvas){
        mContentRect.height();
        mContentRect.width();
        for (int aw=0;aw<1000;aw=aw+100) {
            for (int ah = 0; ah < 1000; ah = ah + 100) {
                canvas.drawCircle(50+aw, 50 + ah, 40, paintRed);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP: // отпускание
                x = event.getX();
                y = event.getY();
                invalidate();


                completedPixels[1]=1;
                break;
            case MotionEvent.ACTION_MOVE: // движение

                break;

        }
        return true;
     }
//    int[] completedPixels ={0,1,0,0,1,0,0,1}; //10 пикселей
public void massivDraw(Canvas canvas){
    int[] completedPixels ={0,1,0,0,1,0,0,1};

    for (int i = 0; i < completedPixels.length; i++){
        if (completedPixels[i]==1){
            int sto=i*100;
            canvas.drawRect(0+sto, 0, 0 + sto+100, 100 , paintRed);
        }
        if (completedPixels[i]==0){
            int sto=i*100;
            canvas.drawRect(0+sto, 200, 0 + sto+100, 300 , paintGreen);
        }

    }
}


}
