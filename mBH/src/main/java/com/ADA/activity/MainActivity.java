package com.ADA.activity;

import com.ADA.mbh.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity {

	private Button btn_begin ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Display mDisplay = getWindowManager().getDefaultDisplay();
        @SuppressWarnings("deprecation")
		int W = mDisplay.getWidth();
        int H = mDisplay.getHeight();
        Log.i("Main", "Width = " + W);
        Log.i("Main", "Height = " + H);
        btn_begin = (Button)findViewById(R.id.btn_begin); 
        
    
        //
        // 添加button点击事件
        btn_begin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			if (v == btn_begin) {
		
				Intent intent = new Intent(MainActivity.this,SetActivity.class);
				startActivity(intent);
			}			
			}
		});
    }
}
