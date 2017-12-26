package com.lwjwork.launcher.langlangui.adapter;

import java.util.List;

import com.ada.jw.R;
import com.lwjwork.launcher.langlangui.util.App;
import com.lwjwork.launcher.langlangui.util.Constants;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;

public class LauncherGridViewAdapter extends BaseAdapter {
	private static final String TAG = LauncherGridViewAdapter.class.getName();
	private final int mCount = 12;
	private static Intent intent = new Intent("android.intent.action.MAIN");
	private static List<ResolveInfo> resolveInfos;
	private Context mContext;
	private int[] custom = { 2130837516, 2130837529, 2130837536 };
	private Boolean isDebug = Boolean.valueOf(true);
	private List<App> mList;
	private Typeface mtypefase;
	private PackageManager pm;

	public LauncherGridViewAdapter(Context ctx, List<App> lst) {
		// TODO Auto-generated constructor stub
		mContext = ctx;
		mList = lst;
		pm = ctx.getPackageManager();
		resolveInfos = pm.queryIntentActivities(intent, 1);
		// mtypefase = TypefaceFactory.getInstance(ctx).getTypeface();
	}

	public void UpdateUI() {
		// mList = GetMainLunchApp.getInstance(mContext).loadLunchApps();
		notifyDataSetChanged();
//		Log.d(TAG, "test show");
	}

	@Override
	public int getCount() {
		return mCount;
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View itemView = View.inflate(mContext, R.layout.gridview_item, null);
		RelativeLayout localRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.image);
		TextView appName = (TextView) itemView.findViewById(R.id.name);
		ImageView appIcon = (ImageView) itemView.findViewById(R.id.icon);

		App localApp = null;

		localRelativeLayout.setBackgroundResource(R.drawable.ic_ui_main_1_1_normal);

		if (position < mList.size() && mList.size() > 0) {
			appIcon.setImageDrawable(mList.get(position).getIcon());
			appName.setText(mList.get(position).getName());
		} else {
			appIcon.setImageResource(R.drawable.icon_add);
			appName.setText("");
		}

		return itemView;
	}

}
