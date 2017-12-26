package com.ada.util;

import java.util.Map;

import com.ada.jw.setActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

public class CircleProgressBar extends View {

	private int maxProgress =setActivity.defaultMaxSpeed;
	public  int progress =0 ;
	public static int metricAndEnglish = 0;
	private int progressStrokeWidth = 2;
	private int marxArcStorkeWidth = 16;
	private boolean floatPointEnable = false;
	private setActivity msetActivity =  new setActivity();
	RectF oval;
	Paint paint;
	
	public CircleProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		oval = new RectF();
		paint = new Paint();
	}

	@Override
	public void onDraw(Canvas canvas) {
		// TODO Auto-generated constructor stub
		
		super.onDraw(canvas);
		int width = this.getWidth();
		int height = this.getHeight();

		width = (width > height) ? height : width;
		height = (width > height) ? height : width;

		paint.setAntiAlias(true); // 闁荤姳绀佹晶浠嬫偪閸℃稒鍋ㄩ柤濮愬�楅幊澶娾槈閹捐泛鍔嬪┑顔碱樀閺屻劑顢涢悙娴嬫瀼
		paint.setColor(Color.WHITE); // 闁荤姳绀佹晶浠嬫偪閸℃稒鍋ㄩ柤濮愬�楅幊澶娢涢悧鍫㈢煄濠㈢櫢鎷�
		canvas.drawColor(Color.TRANSPARENT); // 闂佽皫鍐挎嫹閻斿妫岄梺鐓庡暱閺堫剙鈻嶉敓锟�
		paint.setStrokeWidth(progressStrokeWidth); // 缂備焦鍎抽悘婵嬵敊閿燂拷
		paint.setStyle(Style.STROKE);

		oval.left = marxArcStorkeWidth / 2; // 閻庡綊娼荤紓姘辩箔閸屾粍鍠嗛柟璁规嫹
		oval.top = marxArcStorkeWidth / 2; // 閻庡綊娼荤紓姘辩箔閸屾粍鍠嗛柟璁规嫹
		oval.right = width - marxArcStorkeWidth / 2; // 閻庡綊娼荤紓姘辩箔閸涱垱鍠嗛柟璁规嫹
		oval.bottom = height - marxArcStorkeWidth / 2; // 闂佸憡鐟ラ崢鏍箔閸涱垱鍠嗛柟璁规嫹

		canvas.drawArc(oval, -90, 360, false, paint); // 缂傚倷鐒﹂敋闁糕晜顨婇幆鍌炲磹閻斿妫岄梺闈╃畱濡锕㈤敓浠嬫煥濞戞瀚扮�规挸妫欏璇测槈濡警鍞洪梺鍝勵棥閵堝懎澹掗梺璺ㄥ櫐閹凤拷?
		paint.setColor(Color.rgb(0xFF, 0xCC, 0x00));
		paint.setStrokeWidth(marxArcStorkeWidth);
		canvas.drawArc(oval, -90, ((float) progress / maxProgress) * 360,
				false, paint); // 缂傚倷鐒﹂敋闁糕晜顨嗗璇测槈濡警鍞洪梺闈╃畱濡鎱ㄩ鍕櫖鐎癸拷閸愵亞顔愰梻浣瑰絻閺堫剙危閹间焦瀚呮繝闈涳工椤ワ拷

		paint.setStrokeWidth(1);
		
	String text=""+this.progress;
		if(floatPointEnable) {
			
			if (metricAndEnglish== 0) {
				 maxProgress =setActivity.defaultMaxSpeed*10;
				text = ""+(progress / 10) + "." + (progress % 10);
			}
			else if (metricAndEnglish == 1) {
				 maxProgress =setActivity.defaultMaxSpeed*6;
				int	progressSpeedEnglish = progress;
				text = ""+(progressSpeedEnglish/10) + "." + (progressSpeedEnglish % 10);
			}
			//Log.i("C  metricAndEnglish", ""+metricAndEnglish);
		}else {
			maxProgress =setActivity.defaultMaxLncline;
			text = progress + "";
		}
		//String text = progress ;
		int textHeight = height / 4;
		paint.setTextSize(60);//婵炴垶鎼╅崣鍐ㄎ涢崸妤�鍙婇柛鎾椾椒绮甸梺姹囧妼鐎氼喖鈻嶉妸鈺佺闁糕剝顭囧Σ鐑芥煟閵娿儱顏柣婵愬枟閹棃鏁撻敓锟�
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
	 * 闁荤姳绀佹晶浠嬫偪閸℃ɑ浜ゆ繛鎴灻锟�
	 * 
	 * @param progress
	 *            闁哄鏅滅粙鎴狅拷瑙勫▕閹倿骞嗚閻庡鏌ㄩ悤鍌涘?
	 * @param view
	 *            闂佸搫绉村ú鈺呮儊閹寸偞浜ゆ繛鎴灻濠囨煟閵娿儱顏┑鈽嗗弮閹瑧娑甸崪浣圭秺闂佽法鍣﹂幏锟�?
	 */
	public void setProgress(int progress, View view) {
		this.progress = progress;
		
		view.setAnimation(pointRotationAnima(0,
				(int) (((float) 360 / maxProgress) * progress)));
		this.invalidate();
	}

	
	
	public void setFloatPointEnable(boolean enable) {
		floatPointEnable = enable;
	}

	/**
	 * 闂傚倸鐗忛崕鎴狅拷姘樀閺佸秹鍩￠崘銊︾枃缂備礁顑呴鍫ユ儍閻斿吋鏅搁柨鐕傛嫹?
	 */
	protected void setProgressNotInUiThread(int progress, View view) {
		this.progress = progress;
		view.setAnimation(pointRotationAnima(0,
				(int) (((float) 360 / maxProgress) * progress)));
		this.postInvalidate();
	}

	/**
	 * 闂傚倸鐗忛崕鎴狅拷姘樀閺佸秹鍩￠崘銊︾枃缂備礁顑呴鍫ユ儍閻斿吋鏅搁柨鐕傛嫹?
	 */
	protected void setProgressNotInUiThread(int progress, View view,int progressTwo) {
		this.progress = progress;
		view.setAnimation(pointRotationAnima(0,
				(int) (((float) 360 / maxProgress) * progress)));
		this.postInvalidate();
	}
	
	/**
	 * 闁哄鏅滅粙鎴狅拷瑙勫▕瀵粙宕堕妸锔芥畼闂佺粯鍔曞﹢鍗炩枔閹达箑绀夐柕濞у嫭姣�
	 * 
	 * @param fromDegrees
	 * @param toDegrees
	 * @return
	 */
	private Animation pointRotationAnima(float fromDegrees, float toDegrees) {
		int initDegress = 306;// 闁哄鏅滅粙鎴狅拷瑙勫▕閹瑧娑甸崨顔奸棷婵犳鍠栭鍕礊閸涘瓨鏅搁柨鐕傛嫹?(闂佹悶鍎辨晶鑺ユ櫠閺嶎厼纾婚煫鍥ㄦ长閳哄懏鏅搁柨鐕傛嫹?54闂佽法鍣﹂幏锟�?)
		RotateAnimation animation = new RotateAnimation(fromDegrees,
				initDegress + toDegrees, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		animation.setDuration(1);// 闁荤姳绀佹晶浠嬫偪閸℃稑绀夐柕濞у嫭姣庨梺鍦懗閸♀晜鏂�闂佸搫鍟悥鐓幬涢敓锟�
		animation.setRepeatCount(1);// 闁荤姳绀佹晶浠嬫偪閸℃稒鐓傜�广儱鎷嬪Σ濠氭煙缁楁稑妫弨鑺ョ箾閸″繐澧查柡鍡嫹
		animation.setFillAfter(true);// 闁荤姳绀佹晶浠嬫偪閸℃稑绀夐柕濞у嫭姣庣紓鍌欑劍閹歌顭囬锕�瑙﹂幖娣灪绗戦梺鍛婄啲缁犳垶绂掗悩缁樺仼婵炲棗绻戦煬顒傜磽娴ｈ灏版繛纰卞亝閹峰懎顓奸崶鈺傜��
		return animation;
	}

}