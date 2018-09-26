package com.zhjy.cultural.services.patrol.ui.view;

/**
 * Created by jialg on 2018/2/28.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.zhjy.cultural.services.patrol.R;

/**
 * 流程图
 */

public class ProcedureText extends View {

    private int defalutSize;

    private String defaultText;

    private Paint mPaint;

    private Rect mBound;

    public ProcedureText(Context context) {
        super(context);
    }

    public ProcedureText(Context context, AttributeSet attrs) {
        super(context);
        mPaint = new Paint();
        //第二个参数就是我们在styles.xml文件中的<declare-styleable>标签
        //即属性集合的标签，在R文件中名称为R.styleable+name
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ProcedureText);
        //第一个参数为属性集合里面的属性，R文件名称：R.styleable+属性集合名称+下划线+属性名称
        //第二个参数为，如果没有设置这个属性，则设置的默认的值
        defalutSize = a.getDimensionPixelSize(R.styleable.ProcedureText_default_size, 100);
        defaultText = a.getString(R.styleable.ProcedureText_default_text);
        //最后记得将TypedArray对象回收
        a.recycle();

        mPaint.setTextSize(32);
        mPaint.setStyle(Paint.Style.FILL);
        mBound = new Rect();
        mPaint.getTextBounds(defaultText, 0, defaultText.length(), mBound);
    }


    private int getMySize(int defaultSize, int measureSpec) {
        int mySize = defaultSize;

        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode) {
            case MeasureSpec.UNSPECIFIED: {//如果没有指定大小，就设置为默认大小
                mySize = defaultSize;
                break;
            }
            case MeasureSpec.AT_MOST: {//如果测量模式是最大取值为size
                //我们将大小取最大值,你也可以取其他值
                mySize = size;
                break;
            }
            case MeasureSpec.EXACTLY: {//如果是固定的大小，那就不要去改变它
                mySize = size;
                break;
            }
        }
        return mySize;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMySize(defalutSize, widthMeasureSpec);
        int height = getMySize(defalutSize, heightMeasureSpec);

        if (width < height) {
            height = width;
        } else {
            width = height;
        }

        setMeasuredDimension(width * 3 , height );
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //调用父View的onDraw函数，因为View这个类帮我们实现了一些
        // 基本的而绘制功能，比如绘制背景颜色、背景图片等
        super.onDraw(canvas);

        int mBaseLength = getHeight();
        Paint pdPaint = new Paint();
        pdPaint.setColor(Color.GRAY);
        Path pdPath = new Path();
        pdPath.moveTo(0, 0);
        pdPath.lineTo(mBaseLength * 2, 0);
        pdPath.lineTo(getWidth() - mBaseLength / 4 * 3, getHeight() / 2);
        pdPath.lineTo(mBaseLength * 2, getHeight());
        pdPath.lineTo(0, getHeight());
        pdPath.close();
        canvas.drawPath(pdPath, pdPaint);

        Paint linePaint = new Paint();
        linePaint.setColor(Color.BLACK);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(2);
        canvas.drawLine(getWidth() - mBaseLength / 4 * 3, getHeight() / 2, getWidth(),getHeight() / 2, linePaint );

        int width = getWidth() / 3  - mBound.width() / 2 ;
        int hight = getHeight() / 2 + mBound.height() / 2;
        canvas.drawText(defaultText, width,hight, mPaint);
    }

}
