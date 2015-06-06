package com.cntysoft.android.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.cntysoft.android.R;

/**
 * Created by Administrator on 2015/6/5.
 */
public class CircleImage extends ImageView {

    private static final Xfermode MASK_XFERMODE = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
    private static final int COLORDRAWABLE_DIMENSION = 2;

    private static final int DEFAULT_BORDER_WIDTH = 0;
    private static final int DEFAULT_BORDER_COLOR = Color.BLACK;

    private Bitmap mBitmap;
    private Paint mPaint;
    private int mBorderWidth = DEFAULT_BORDER_WIDTH;
    private int mBorderColor = DEFAULT_BORDER_COLOR;

    public CircleImage(Context context) {
        super(context);
    }

    public CircleImage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleImage);
        mBorderWidth = a.getDimensionPixelOffset(R.styleable.CircleImage_border_widths,DEFAULT_BORDER_WIDTH);
        mBorderColor = a.getColor(R.styleable.CircleImage_border_colors,DEFAULT_BORDER_COLOR);

        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        final Drawable localDraw = getDrawable();
        if(localDraw == null) {
            return ;
        }
        if(localDraw instanceof NinePatchDrawable) {
            return ;
        }
        if (this.mPaint == null) {
            final Paint localPaint = new Paint();
            localPaint.setFilterBitmap(false);
            localPaint.setAntiAlias(true);
            localPaint.setXfermode(MASK_XFERMODE);
            this.mPaint = localPaint;
        }
        final int width = getWidth();
        final int height = getHeight();
        /** 保存layer */
        int layer = canvas.saveLayer(0.0F, 0.0F, width, height, null, 31);
        /** 设置drawable的大小 */
        localDraw.setBounds(0, 0, width, height);
        /** 将drawable绑定到bitmap(this.mask)上面（drawable只能通过bitmap显示出来） */
        localDraw.draw(canvas);
        if ((this.mBitmap == null) || (this.mBitmap.isRecycled())) {
            this.mBitmap = createOvalBitmap(width, height);
        }
        /** 将bitmap画到canvas上面 */
        canvas.drawBitmap(this.mBitmap, 0.0F, 0.0F, this.mPaint);
        /** 将画布复制到layer上 */
        canvas.restoreToCount(layer);
        drawBorder(canvas, width, height);
    }

    /**
     * 绘制圆形边框
     */
    private void drawBorder(Canvas canvas, final int width, final int height) {
        if(mBorderWidth == 0) {
            return ;
        }
        final Paint mBorderPaint = new Paint();
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setColor(mBorderColor);
        mBorderPaint.setStrokeWidth(mBorderWidth);
        canvas.drawCircle(width/2, height/2, (width-mBorderWidth)/2, mBorderPaint);
        canvas = null;
    }

    public Bitmap createOvalBitmap(final int width, final int height) {
        Bitmap.Config localConfig = Bitmap.Config.ARGB_8888;
        Bitmap localBitmap = Bitmap.createBitmap(width, height, localConfig);
        Canvas localCanvas = new Canvas(localBitmap);
        Paint localPaint = new Paint();
        final int padding = (mBorderWidth - 3) > 0 ? mBorderWidth - 3 : 1;
        /**
         * 设置椭圆的大小(因为椭圆的最外边会和border的最外边重合的，如果图片最外边的颜色很深，有看出有棱边的效果，所以为了让体验更加好，
         * 让其缩进padding px)
         */
        RectF localRectF = new RectF(padding, padding, width - padding, height - padding);
        localCanvas.drawOval(localRectF, localPaint);

        return localBitmap;
    }

}
