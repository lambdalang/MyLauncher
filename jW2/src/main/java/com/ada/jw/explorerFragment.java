package com.ada.jw;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import com.ada.mcu.service.Beep;
import com.ada.util.VolumeSeekBar;

public class explorerFragment extends Fragment {

	public static explorerFragment newInstance(Activity mActivity) {

		explorerFragment mExplorerFragment = new explorerFragment();
		mExplorerFragment.currentActivity = (setActivity) mActivity;

		return mExplorerFragment;
	}

	private View view;
	private setActivity currentActivity;

	private CustomViewCallback customViewCallback;
	private WebView webViewExplorer;
	private ImageButton ibBack;
	private VolumeSeekBar seekBar;

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		seekBar.show();
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		view = inflater.inflate(R.layout.explorer_fragment, null, false);
		findViewById(view);
		return view;
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		inItUi();
		listionEvent();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			webViewExplorer.onPause();
		}
		webViewExplorer.reload();// 返回，声音停止
		super.onPause();
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();

		currentActivity.RL_commenTop.setVisibility(View.VISIBLE);
		currentActivity.slidingDrawerSet.setVisibility(view.VISIBLE);
		currentActivity.LL_BackAndPause.setVisibility(view.VISIBLE);
		currentActivity.IB_downSmall.setVisibility(view.VISIBLE);

		webViewExplorer.removeAllViews();// 删除所有添加的view
		webViewExplorer.destroy();// 销毁webView，清理内存//无这2句，第二次进来点击无反应
	}

	private void findViewById(View view) {

		webViewExplorer = (WebView) view.findViewById(R.id.webViewExplorer);
		ibBack = (ImageButton) view.findViewById(R.id.TV_Back);
		seekBar = new VolumeSeekBar(getActivity(), view, R.id.volume_seekbar);
	}

	private void inItUi() {

		currentActivity.RL_commenTop = currentActivity
				.findViewById(R.id.RL_commenTop);
		currentActivity.slidingDrawerSet.setVisibility(view.GONE);
		currentActivity.LL_BackAndPause.setVisibility(view.GONE);
		currentActivity.IB_downSmall.setVisibility(view.GONE);

		// webViewExplorer.getSettings().setBlockNetworkImage(true);
		// //把图片加载放在最后来加载渲染
		// webViewExplorer.getSettings().setRenderPriority(RenderPriority.HIGH);
		// //提高渲染优先级

		// //启动缓存
		// webViewExplorer.getSettings().setAppCacheEnabled(true);
		// //设置缓存模式
		// webViewExplorer.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
		//
		WebSettings settings = webViewExplorer.getSettings();
		settings.setBuiltInZoomControls(true);// 可缩放
		settings.setJavaScriptCanOpenWindowsAutomatically(true); // 支持通过js打开新的窗口
		settings.setJavaScriptEnabled(true);// 支持JS
		settings.setPluginState(PluginState.ON);// 支持插件
		settings.setLoadWithOverviewMode(true);// 自适应屏幕
		webViewExplorer.setWebChromeClient(new DefaultWebChromeClient()); // 播放视频
		webViewExplorer.setLayerType(View.LAYER_TYPE_HARDWARE, null);
		webViewExplorer.requestFocusFromTouch();// 设置支持获取手势焦点

		webViewExplorer.loadUrl("http://wap.baidu.com");

		// 覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
		// webViewExplorer.setWebViewClient(new WebViewClient(){
		// @Override
		// public boolean shouldOverrideUrlLoading(WebView view, String url) {
		// // TODO Auto-generated method stub
		// //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
		// view.loadUrl(url);
		// return false;
		// }
		// });
		//
	}

	/*
	 * 监听事件
	 */
	private void listionEvent() {
		ibBack.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				int action = event.getAction();
				switch (action) {
				case MotionEvent.ACTION_DOWN:
					ibBack.setBackgroundResource(R.drawable.icon_back_clicked);
					Beep.beep(getActivity().getApplication());
					currentActivity.switchFragment = 6;
					currentActivity
							.switchFragment(currentActivity.switchFragment);
					break;
				case MotionEvent.ACTION_UP:
					ibBack.setBackgroundResource(R.drawable.icon_back);
					break;
				default:
					break;
				}
				return true;
			}
		});
	}

	/**
	 * 设置全屏
	 */
	private void setFullScreen() {
		// 设置全屏的相关属性，获取当前的屏幕状态，然后设置全屏
		currentActivity.getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// 全屏下的状态码：1098974464
		// 窗口下的状态吗：1098973440
	}

	// 关联webview 类
	class MyWebViewClient extends WebViewClient {

		// 加载结束的时候
		@Override
		public void onPageFinished(WebView view, String url) {
			// TODO Auto-generated method stub
			super.onPageFinished(view, url);
		}
	}

	private class DefaultWebChromeClient extends WebChromeClient {
		// 一个回调接口使用的主机应用程序通知当前页面的自定义视图已被撤职

		// 进入全屏的时候
		@Override
		public void onShowCustomView(View view, CustomViewCallback callback) {
			// 赋值给callback
			customViewCallback = callback;

			// 将video放到当前视图中

			webViewExplorer.addView(view);// 无这一句，黑屏无法播放视频
			// 横屏显示
			// setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			// 设置全屏
			setFullScreen();
		}

		// 退出全屏
		@Override
		public void onHideCustomView() {
			// TODO Auto-generated method stub
			super.onHideCustomView();

			if (customViewCallback != null) {
				// 隐藏掉
				customViewCallback.onCustomViewHidden();
			}
			webViewExplorer.setVisibility(View.VISIBLE);// 播放完显示webView，没有这句播放完黑屏
		}

		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			super.onProgressChanged(view, newProgress);
		}
	}
}
