package com.bfdelivery.cavaliers.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.bfdelivery.cavaliers.R;

/**
 * Created by Panoo on 2017/7/26.
 */

public class CircleImageView extends android.support.v7.widget.AppCompatImageView {

	private float mDefBorderWidth = 2F;
	private int mDefBorderColor = Color.parseColor("#ffffff");
	private Paint mPaint = new Paint();
	private Rect mRect = new Rect();

	public CircleImageView(Context context) {
		super(context);
		initView(context, null);
	}

	public CircleImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context, attrs);
	}

	public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context, attrs);
	}

	private void initView(Context context, AttributeSet attrs) {

		mPaint.setAntiAlias(true);
		mPaint.setFilterBitmap(true);
		mPaint.setDither(true);

		if (attrs != null) {
			TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleImage);

			mDefBorderWidth = typedArray.getDimension(R.styleable.CircleImage_rimWidth, mDefBorderWidth);
			mDefBorderColor = typedArray.getColor(R.styleable.CircleImage_rimColor, mDefBorderColor);

			typedArray.recycle();
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {

		Drawable drawable = getDrawable();

		if (drawable == null) {
			return;
		}

		if (getWidth() == 0 || getHeight() == 0) {
			return;
		}
		Bitmap b = ((BitmapDrawable) drawable).getBitmap();
		Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);

		int width = getMeasuredWidth();

		Bitmap roundBitmap = getCroppedBitmap(bitmap, width);
		canvas.drawBitmap(roundBitmap, 0, 0, null);
	}

	private Bitmap getCroppedBitmap(Bitmap bmp, int outSize) {
		Bitmap sbmp;
		float radius = outSize / 2f;
		if (bmp.getWidth() != outSize || bmp.getHeight() != outSize) {
			float smallest = Math.min(bmp.getWidth(), bmp.getHeight());
			float factor = smallest / outSize;
			sbmp = Bitmap.createScaledBitmap(bmp,
					(int) (bmp.getWidth() / factor),
					(int) (bmp.getHeight() / factor), false);
		} else {
			sbmp = bmp;
		}

		Bitmap output = Bitmap.createBitmap(outSize, outSize, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		mRect.set(0, 0, outSize, outSize);

		mPaint.setXfermode(null);
		mPaint.setStyle(Paint.Style.FILL);
		mPaint.setColor(mDefBorderColor);
		mPaint.setStrokeWidth(0);

		canvas.drawARGB(0, 0, 0, 0);
		canvas.drawCircle(radius + 0.5f, radius + 0.5f,
				radius - 0.5f, mPaint);
		mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(sbmp, mRect, mRect, mPaint);

		if (mDefBorderWidth >= 1.0F) {
			mPaint.setStyle(Paint.Style.STROKE);
			mPaint.setStrokeWidth(mDefBorderWidth);
			canvas.drawCircle(radius + 0.5f, radius + 0.5f, radius - (mDefBorderWidth / 2 + 0.5F), mPaint);
		}

		return output;
	}

}
