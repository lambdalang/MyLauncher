package com.ADA.activity;


import com.ADA.mbh.R;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class ParameterSettingActivity extends BaseActivity {

	private ImageButton IB_factoryPatternSet;
	private ImageButton IB_androidPatternSet;
	private Button confirm,back;
	
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.parameter_set);
			
			InitBase();
			BaselistionEvent();
			inIt();
			listionEvent();			
		}

		private void listionEvent() {
			// TODO Auto-generated method stub
			
			IB_factoryPatternSet.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					Intent intent = new Intent(ParameterSettingActivity.this,FactoryPatternSet.class);
					startActivity(intent);
				}
			});
			
			IB_androidPatternSet.setOnClickListener(new OnClickListener() {
						
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					  Intent intent =  new Intent(Settings.ACTION_SETTINGS);  
		              startActivity(intent);
				}
			});

			confirm.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {

				}
			});

			back.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					finish();
				}
			});
		}

		private void inIt() {
			// TODO Auto-generated method stub
			
			IB_factoryPatternSet = (ImageButton)findViewById(R.id.IB_factoryPatternSet);
			IB_androidPatternSet = (ImageButton)findViewById(R.id.IB_androidPatternSet);
			confirm= (Button) findViewById(R.id.Confirm);
			back= (Button) findViewById(R.id.Back);
		}
}
