package com.lwjwork.launcher.langlangui.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.ada.jw.R;
import com.lwjwork.launcher.langlangui.activity.AllApp;
import com.lwjwork.launcher.langlangui.util.App;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CommonGridViewAdapter extends BaseAdapter {
	private static final String TAG = CommonGridViewAdapter.class.getName();
	private List<App> mApps = new ArrayList<App>();
	private int[] backgrounds = { R.drawable.item_blue, R.drawable.item_cyan,
			R.drawable.item_drak_cyan, R.drawable.item_green, R.drawable.item_grey,
			R.drawable.item_orange, R.drawable.item_purple, R.drawable.item_red };
	private Context mContext;
	// private Typeface font;
	private int index = 0;
	private LayoutInflater inflater;
	private Locale locale;
	private Random random = new Random();
	private int type = -1;

	public CommonGridViewAdapter(Context ctx) {
		mContext = ctx;
		locale = ctx.getResources().getConfiguration().locale;
		// font = TypefaceFactory.getInstance(paramContext).getTypeface();
		inflater = LayoutInflater.from(ctx);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mApps.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mApps.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressWarnings("deprecation")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Holder holder;
		App app;
		if (convertView == null) {
			holder = new Holder();
			convertView = inflater.inflate(R.layout.common_gridview_item, null);
			holder.icon = ((ImageView) convertView.findViewById(R.id.app_icon));
			holder.hook = ((ImageView) convertView.findViewById(R.id.app_hook));
			holder.name = ((TextView) convertView.findViewById(R.id.app_name));
			index %= backgrounds.length;
			convertView.setBackgroundDrawable(mContext.getResources().getDrawable(
					backgrounds[index]));
			index = (1 + index);
			
			app = (App) mApps.get(position);
			holder.icon.setImageDrawable(app.getIcon());
			holder.name.setText(app.getName());
			holder.hook.setVisibility(8);

			convertView.setTag(holder);
			
			return convertView;
		} else {
			holder = (Holder) convertView.getTag();
			app = (App) mApps.get(position);
			holder.icon.setImageDrawable(app.getIcon());
			holder.name.setText(app.getName());
			holder.hook.setVisibility(8);
		}
		return convertView;
	}

	public void setApps(List<App> list) {
		// TODO Auto-generated method stub
		mApps = list;
	}

	public void updateItems() {
		index = 0;
		notifyDataSetChanged();
	}
}
