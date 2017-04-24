package app.com.thetechnocafe.tutorials;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gurleensethi on 22/03/17.
 */

public class SimpleDrawingView extends View {

    private final int paintColor = Color.BLACK;
    private Paint drawPaint;
    private List<Point> circlePoints;
    private Path path = new Path();

    public SimpleDrawingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setUpPaint();
        setFocusable(true);
        setFocusableInTouchMode(true);
        circlePoints = new ArrayList<>();
    }

    private void setUpPaint() {
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
       canvas.drawPath(path, drawPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                path.moveTo(touchX, touchY);
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                path.lineTo(touchX, touchY);
                break;
            }

            default:
                return false;
        }

        postInvalidate();
        return true;
    }
}
