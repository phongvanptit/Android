package phongvh.approtrain.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomView extends View {
    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context,
                      @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        int color = (int) (Math.random() * 4);
        switch (color) {
            case 0:
                paint.setColor(Color.YELLOW);
                break;
            case 1:
                paint.setColor(Color.RED);
                break;
            case 2:
                paint.setColor(Color.GREEN);
                break;
            case 3:
                paint.setColor(Color.BLUE);
                break;
        }
        canvas.drawRect(20, 50, 220, 250, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == event.ACTION_UP) {
            float x = event.getX();
            float y = event.getY();
            if (x >= 20 && x <= 220 && y >= 50 && y <= 250) {
                invalidate();
            }
        }
        return true;
    }
}
