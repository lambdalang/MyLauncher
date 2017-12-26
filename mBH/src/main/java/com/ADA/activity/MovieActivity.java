package com.ADA.activity;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.transform.Source;
import com.ADA.mbh.R;
import com.ADA.activity.ModeNowActivity.MyAdapter;

import android.R.color;
import android.R.string;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MovieActivity extends BaseActivity {

		private ListView LV_MoView;
		private ArrayList<Map<String, Object>> listItem;
		private String Type = "All";
		
		private TextView TV_All;
		private TextView TV_History;
		private TextView TV_Collect;
		
		@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_movie);
					
			inIt();
			listItem = getDate();
			final MyListAdapter mAdapter = new MyListAdapter(this);
			LV_MoView.setAdapter(mAdapter);			
			
			
			TV_All.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					Type = "All";
					TV_All.setTextColor(Color.parseColor("#FFCC00"));
					TV_Collect.setTextColor(Color.parseColor("#FFFFFF"));
					TV_History.setTextColor(Color.parseColor("#FFFFFF"));
					
					refresh(getDate());
					mAdapter.notifyDataSetChanged();
				
					Log.i("---------------", "点击了全部");
				}
			});
			
			TV_Collect.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					Type = "Collect";
					TV_Collect.setTextColor(Color.parseColor("#FFCC00"));
					TV_All.setTextColor(Color.parseColor("#FFFFFF"));
					TV_History.setTextColor(Color.parseColor("#FFFFFF"));
					
					refresh(getDate());
					mAdapter.notifyDataSetChanged();
					
					Log.i("---------------", "点击了收藏");
				}
			});
			
			TV_History.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					Type = "History";
					TV_History.setTextColor(Color.parseColor("#FFCC00"));
					TV_All.setTextColor(Color.parseColor("#FFFFFF"));
					TV_Collect.setTextColor(Color.parseColor("#FFFFFF"));
					
				
					refresh(getDate());
					mAdapter.notifyDataSetChanged();
					
					Log.i("---------------", "点击了历史");
				}
			});
			
			LV_MoView.setOnItemClickListener(new OnItemClickListener() {
				
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					
					 Log.i("MyListViewBase", "你点击了ListView条目"+arg2);
				}
			});
			
			
			
		}
		
		public void refresh(ArrayList<Map<String, Object>> listItem){
			
			this.listItem =listItem;

		}
		
		/*添加一个得到数据的方法，方便使用*/
			private ArrayList<Map<String, Object>> getDate(){
			
			ArrayList<Map<String, Object>> hostoryList = new ArrayList<Map<String, Object>>();		
			Map<String, Object> hostorymap = new HashMap<String, Object>();
			
			ArrayList<Map<String, Object>> allList = new ArrayList<Map<String, Object>>();		
			Map<String, Object> allmap = new HashMap<String, Object>();
			
			ArrayList<Map<String, Object>> collectList = new ArrayList<Map<String, Object>>();		
			Map<String, Object> collectmap = new HashMap<String, Object>();
			
			collectmap = new HashMap<String, Object>();
			collectmap.put("title", "A计划");
			collectmap.put("info", "成龙");
			collectmap.put("number", "23");
			collectList.add(collectmap);
			
			
			hostorymap = new HashMap<String, Object>();
			hostorymap.put("title", "斯密斯夫妇");
			hostorymap.put("info", "布拉德 皮特");
			hostorymap.put("number", "24");
			hostoryList.add(hostorymap);
			
			
			allmap = new HashMap<String, Object>();
			allmap.put("title", "A计划");
			allmap.put("info", "成龙");
			allmap.put("number", "24");
			allList.add(allmap);
			
			allmap = new HashMap<String, Object>();
			allmap.put("title", "斯密斯夫妇");
			allmap.put("info", "布拉德 皮特");
			allmap.put("number", "24");
			allList.add(allmap);
			
			allmap = new HashMap<String, Object>();
			allmap.put("title", "功夫");
			allmap.put("info", "周星驰");
			allmap.put("number", "25");
			allList.add(allmap);
			
			allmap = new HashMap<String, Object>();
			allmap.put("title", "东邪西毒");
			allmap.put("info", "林青霞");
			allmap.put("number", "26");
			allList.add(allmap);
			
			allmap = new HashMap<String, Object>();
			allmap.put("title", "古墓丽影");
			allmap.put("info", "安吉丽娜朱莉");
			allmap.put("number", "27");
			allList.add(allmap);
			
		 if (Type.equals("Collect")) {
				
				return collectList;	
			}else if (Type.equals("History")) {
				
				return hostoryList;	
			}else {
				return allList;
			}	
		};
		

/* 新建一个类继承BaseAdapter，实现视图与数据的绑定
 **/
		
		public class MyListAdapter extends BaseAdapter{
			
					private LayoutInflater mInflater;
						 
			 		public MyListAdapter(Context context){
							 
			 					this.mInflater = LayoutInflater.from(context);		 		
			 		}
			 					
						 @Override
						public int getCount() {
						// TODO Auto-generated method stub
						return  listItem.size();
						}

						@Override
						public Object getItem(int position) {
							// TODO Auto-generated method stub
							return null;
						}

						@Override
						public long getItemId(int position) {
							// TODO Auto-generated method stub
							return 0;
						}

						@Override
						public View getView(int position, View convertView,
								ViewGroup parent) {
							// TODO Auto-generated method stub
							
							ViewHolder holder;
							
							if (convertView == null) {
								
								convertView = mInflater.inflate(R.layout.listview_history, null);
								
								 holder = new ViewHolder();

								 /*得到各个控件的对象*/                    
								 holder.title = (TextView)convertView.findViewById(R.id.title);
								 holder.info = (TextView)convertView.findViewById(R.id.info);
								 holder.number = (TextView)convertView.findViewById(R.id.number);
								 
								//绑定ViewHolder对象
								 convertView.setTag(holder);
							}else {
								
									//取出ViewHolder对象 
									holder = (ViewHolder)convertView.getTag();										
							}
							
									holder.title.setText(getDate().get(position).get("title").toString());
									holder.info.setText(getDate().get(position).get("info").toString());
									holder.number.setText(getDate().get(position).get("number").toString());
								
							return convertView;
						}						
					}
		
				public final class ViewHolder{
					
					    public TextView title;
					    public TextView info;
					    public TextView   number;
				}		

				private void inIt() {
					// TODO Auto-generated method stub
					
					LV_MoView = (ListView)findViewById(R.id.LV_Movie);
					TV_All = (TextView)findViewById(R.id.TV_All);
					TV_Collect = (TextView)findViewById(R.id.TV_Collect);
					TV_History = (TextView)findViewById(R.id.TV_History);	
				}
}
