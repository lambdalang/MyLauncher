package com.ADA.activity;



import com.ADA.mbh.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class WarningActivity extends BaseActivity {

		private TextView TV_waringNumber;
		private int waringNumber;
		private ImageButton IB_ok;
		
		public boolean isOrNoWaring;
		
			@Override
			protected void onCreate(Bundle savedInstanceState) {
				// TODO Auto-generated method stub
				super.onCreate(savedInstanceState);
				setContentView(R.layout.warning);
				
				inIt();
				listionEvent();
			}

			private void listionEvent() {
				// TODO Auto-generated method stub
				
				try {
					
					Intent	intent = this.getIntent();
					Bundle	bundle = intent.getExtras();
					waringNumber = bundle.getInt("receivedData");
					TV_waringNumber.setText(""+waringNumber);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				IB_ok.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
//						isOrNoWaring = setWarningFlag(false);
//						if (!isOrNoWaring) {
//							warning = 1;
//						}
						finish();
					}
				});
			}

			private void inIt() {
				// TODO Auto-generated method stub
				
				TV_waringNumber = (TextView)findViewById(R.id.TV_waringNumber);
				IB_ok = (ImageButton)findViewById(R.id.IB_ok);
			}
}
