package com.test.climber.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.test.climber.R;

public class CircleProgressView extends View {
    private Paint paint = new Paint();
    private int roundColor = 0;
    private float roundWidth = 0;
    private int progressColor = 0;
    private float progressWidth = 0;
    private int max = 0;
    private int startAngle = 0;
    private int progress = 0;

    public CircleProgressView(Context context) {
        super(context);
        init(context, null);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float centerX = getWidth() / 2f;
        float radius = (centerX - roundWidth / 2);
        paint.setStrokeWidth(roundWidth);
        paint.setColor(roundColor);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(centerX, centerX, radius, paint);

        paint.setStrokeWidth(progressWidth);
        paint.setColor(progressColor);
        paint.setStrokeCap(Paint.Cap.ROUND);
        float left = (centerX - radius);
        float top = (centerX - radius);
        float right = (centerX + radius);
        float bottom = (centerX + radius);
        float sweepAngle = 360f * progress / max;
        canvas.drawArc(left, top, right, bottom, startAngle, sweepAngle, false, paint);
    }

    public void setProgress(int progress) {
        int temp = progress;
        if (progress > max) {
            temp = max;
        }
        this.progress = temp;
        postInvalidate();
    }

    public int getProgress() {
        return progress;
    }

    void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressView);
            roundColor = mTypedArray.getColor(R.styleable.CircleProgressView_roundColor, Color.RED);
            roundWidth = mTypedArray.getDimension(R.styleable.CircleProgressView_roundWidth, 5f);
            progressColor = mTypedArray.getColor(R.styleable.CircleProgressView_progressColor, Color.GREEN);
            progressWidth = mTypedArray.getDimension(R.styleable.CircleProgressView_progressWidth, roundWidth);
            max = mTypedArray.getInteger(R.styleable.CircleProgressView_maxProgress, 100);
            startAngle = mTypedArray.getInt(R.styleable.CircleProgressView_startAngle, 90);
            progress = mTypedArray.getInt(R.styleable.CircleProgressView_progress, 0);
            mTypedArray.recycle();
        }
    }
}
