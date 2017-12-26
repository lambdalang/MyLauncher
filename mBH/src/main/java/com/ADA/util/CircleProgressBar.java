package com.ADA.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

public class CircleProgressBar extends View {

	private int maxProgress = 20;
	private int progress =0 ;
	private int progressStrokeWidth = 2;
	private int marxArcStorkeWidth = 16;
	private boolean floatPointEnable = false;
	// 画圆�?在的距形区域
	RectF oval;
	Paint paint;
	public CircleProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		oval = new RectF();
		paint = new Paint();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO 自动生成的方法存�?
		super.onDraw(canvas);
		int width = this.getWidth();
		int height = this.getHeight();

		width = (width > height) ? height : width;
		height = (width > height) ? height : width;

		paint.setAntiAlias(true); // 设置画笔为抗锯齿
		paint.setColor(Color.WHITE); // 设置画笔颜色
		canvas.drawColor(Color.TRANSPARENT); // 白色背景
		paint.setStrokeWidth(progressStrokeWidth); // 线宽
		paint.setStyle(Style.STROKE);

		oval.left = marxArcStorkeWidth / 2; // 左上角x
		oval.top = marxArcStorkeWidth / 2; // 左上角y
		oval.right = width - marxArcStorkeWidth / 2; // 左下角x
		oval.bottom = height - marxArcStorkeWidth / 2; // 右下角y

		canvas.drawArc(oval, -90, 360, false, paint); // 绘制白色圆圈，即进度条背�?
		paint.setColor(Color.rgb(0xFF, 0xCC, 0x00));
		paint.setStrokeWidth(marxArcStorkeWidth);
		canvas.drawArc(oval, -90, ((float) progress / maxProgress) * 360,
				false, paint); // 绘制进度圆弧，这里是蓝色

		paint.setStrokeWidth(1);
		String text=""+this.progress;
		if(floatPointEnable) {
			text = (progress / 10) + "." + (progress % 10);
		}else {
			text = progress + "";
		}

		int textHeight = height / 4;
		paint.setTextSize(60);//中间显示的百分比的高度
		int textWidth = (int) paint.measureText(text, 0, text.length());
		paint.setStyle(Style.FILL);
		canvas.drawText(text, width / 2 - textWidth / 2, height / 2
				+ textHeight / 2, paint);

	}

	public int getMaxProgress() {
		return maxProgress;
	}

	public void setMaxProgress(int maxProgress) {
		this.maxProgress = maxProgress;
	}

	/**
	 * 设置进度
	 * 
	 * @param progress
	 *            进度百分�?
	 * @param view
	 *            标识进度的节点视�?
	 */
	public void setProgress(int progress, View view) {
		this.progress = progress;
		
		view.setAnimation(pointRotationAnima(0,
				(int) (((float) 360 / maxProgress) * progress)));
		this.invalidate();
	}


	/**
	 * 非ＵＩ线程调�?
	 */
	protected void setProgressNotInUiThread(int progress, View view) {
		this.progress = progress;
		view.setAnimation(pointRotationAnima(0,
				(int) (((float) 360 / maxProgress) * progress)));
		this.postInvalidate();
	}

	/**
	 * 非ＵＩ线程调�?
	 */
	protected void setProgressNotInUiThread(int progress, View view,int progressTwo) {
		this.progress = progress;
		view.setAnimation(pointRotationAnima(0,
				(int) (((float) 360 / maxProgress) * progress)));
		this.postInvalidate();
	}
	
	/**
	 * 进度标注点的动画
	 * 
	 * @param fromDegrees
	 * @param toDegrees
	 * @return
	 */
	private Animation pointRotationAnima(float fromDegrees, float toDegrees) {
		int initDegress = 306;// 进度点起始位�?(图片偏移�?54�?)
		RotateAnimation animation = new RotateAnimation(fromDegrees,
				initDegress + toDegrees, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		animation.setDuration(1);// 设置动画执行时间
		animation.setRepeatCount(1);// 设置重复执行次数
		animation.setFillAfter(true);// 设置动画结束后是否停留在结束位置
		return animation;
	}

}