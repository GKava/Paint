package bar.appbarbottom.canvasdraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawView extends View {
    Paint paint = new Paint();
    Paint paintblack = new Paint();
    Paint paintHand = new Paint();
    float x;
    float y;
    int intX;
    int intY;
    int modX;
    int modY;

    private void init() {
        paint.setColor(Color.DKGRAY);
        paintblack.setColor(Color.BLACK);
        paintHand.setColor(Color.BLACK);
    }

    public DrawView(Context context) {
        super(context);
        init();
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    public void onDraw(Canvas canvas) {
        drawGrid(canvas);

        int width = canvas.getWidth();
        int height = canvas.getHeight();
         intX = (int) x;
         intY = (int) y;
         modX = intX % 100;
         modY = intY % 100;
         intX = intX - modX;
         intY = intY - modY;

         if (x !=0) {
             canvas.drawRect(intX, intY, intX + 100, intY + 100, paintHand);
         }
            //кубики
//        for (int s=100;s<500;s=s+100) {
//            for (int cub = 0; cub < 10000; cub = cub + 100) {
//                canvas.drawRect(0 + cub, 100 + cub-s, 100 + cub, 200 + cub+s, paint);
//            }
//        }

        //сетка
        for (int a=0;a<10000;a=a+100) {
            canvas.drawLine(0, 100+a, 2000, 100+a, paintblack);
            canvas.drawLine(100+a, 0, 100+a, 2000, paintblack);
        }

    }
    public void drawGrid(Canvas canvas){
                for (int s=100;s<500;s=s+100) {
            for (int cub = 0; cub < 10000; cub = cub + 100) {
                canvas.drawRect(0 + cub, 100 + cub-s, 100 + cub, 200 + cub+s, paint);
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
                break;

        }
        return true;
     }

}
