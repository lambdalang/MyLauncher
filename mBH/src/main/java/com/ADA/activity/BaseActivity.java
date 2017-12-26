package com.ADA.activity;

import java.security.PublicKey;
import java.util.concurrent.locks.ReentrantLock;

import javax.security.auth.PrivateCredentialPermission;

import com.ADA.mbh.R;
import com.ADA.util.CircleProgressBar;

import android.R.bool;
import android.R.integer;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BaseActivity extends Activity {


    //密码
    public String getPassword = "";
    //commen 布局定义
    public ImageButton IB_Background_Left_in;
    public ImageButton IB_Background_Left;
    public ImageButton IB_Background_Right_in;
    public ImageButton IB_Background_Right;

    public Button Btn_Back_Bottom;
    public Button Btn_Pause_Bottom;

    public View RL_Big_Layout;
    public View RL_Small_Layout;
    public View RL_Small_Layout_Right;
    public View RL_Big_Layout_Right;
    public View RL_Middle_Layout;
    public View RL_Top_Layout;

    public TextView TV_Gradiend_Show_Top;
    public TextView TV_Distance_Show_Top;
    public TextView TV_Time_Show_Top;
    public TextView TV_Heat_Show_Top;
    public TextView TV_Heart_Show_Top;
    public TextView TV_Speed_Show_Top;
    public TextView TV_Distance;
    public TextView TV_Speed;

    //progressBar右边速度
    public CircleProgressBar PB_Speed;
    public ImageButton IB_Up_Only_Right;
    public ImageButton IB_Up_Both_Right;
    public ImageButton IB_Down_Only_Right;
    public ImageButton IB_Down_Both_Right;
    public static int progressSpeed = 0;
    public ImageView circle_point_imgSpeed;

    //progressBar左边坡度
    public CircleProgressBar PB_Gradiend;
    public ImageButton IB_Up_Only;
    public ImageButton IB_Up_Both;
    public ImageButton IB_Down_Only;
    public ImageButton IB_Down_Both;
    public static int progressGradiend = 0;
    public ImageView circle_point_imgGradiend;
//		public TextView TV_GradiendShowLeft;

    public Thread mThread;
    private boolean isRunning = true;
    private Thread myThread;
    private boolean myisRunning = true;

    final public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:

//		        	 TV_GradiendShowLeft.setText("" +progressGradiend);		        	
                    //TV_Gradiend_Show_Top.setText("" +progressGradiend);
                    TV_Speed_Show_Top.setText("" + progressSpeed);
                    break;
                case 1:

                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commen);

        InitBase();
        BaselistionEvent();

        ReentrantLock mylock = new ReentrantLock();
        mylock.lock();
        mThread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (isRunning) {
                    try {
                        Thread.sleep(100);
//	                    TV_GradiendShowLeft.setText("" +progressGradiend);
//			        	PB_Gradiend.setProgress(progressGradiend,circle_point_img);	
                        mHandler.sendEmptyMessage(0);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        mThread.start();
        mylock.unlock();

//	    
//	    ReentrantLock lock = new ReentrantLock(); 
//	    lock.lock();
//	    myThread = new Thread(new Runnable() {
//
//	        @Override
//	        public void run() {
//	            while (myisRunning) {
//	                try {
//	                    Thread.sleep(100);	                    	                    
//	                  
//	                   
//	                	PB_Speed.setProgress(progressSpeed,circle_point_imgSpeed);	
//			        	
//	                } catch (Exception e) {
//	                    e.printStackTrace();
//	                }
//	            }
//
//	        }
//	    });
//	    myThread.start();
//	    lock.unlock();
    }

    /*
     * 监听事件
     * */
    public void BaselistionEvent() {
        // TODO Auto-generated method stub

        //坡度单上
        IB_Up_Only.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                progressGradiend += 1;
                PB_Gradiend.setProgress(progressGradiend, circle_point_imgGradiend);
                //TV_GradiendShowLeft.setText("" +progressGradiend);
            }
        });

        //坡度双上
        IB_Up_Both.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                progressGradiend += 2;
                PB_Gradiend.setProgress(progressGradiend, circle_point_imgGradiend);
                //	TV_GradiendShowLeft.setText("" +progressGradiend);
            }
        });

        //坡度单下
        IB_Down_Only.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                progressGradiend -= 1;
                PB_Gradiend.setProgress(progressGradiend, circle_point_imgGradiend);
                //	TV_GradiendShowLeft.setText("" +progressGradiend);
            }
        });

        //坡度双下
        IB_Down_Both.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                progressGradiend -= 2;
                PB_Gradiend.setProgress(progressGradiend, circle_point_imgGradiend);
                //	TV_GradiendShowLeft.setText("" +progressGradiend);
            }
        });

        //速度单上
        IB_Up_Only_Right.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                progressSpeed += 1;
                speedDeal(progressSpeed);
//				String  progressSpeedString = ""+(progressSpeed/10)+"."+(progressSpeed%10);
//			   TV_Speed_Show_Top.setText(progressSpeedString);
//				PB_Speed.setProgress(progressSpeed,circle_point_imgSpeed);	
            }
        });

        //速度双上
        IB_Up_Both_Right.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                progressSpeed += 2;
                speedDeal(progressSpeed);
//					String  progressSpeedString = ""+(progressSpeed/10)+"."+(progressSpeed%10);
//					   TV_Speed_Show_Top.setText(progressSpeedString);
//				PB_Speed.setProgress(progressSpeed,circle_point_imgSpeed);	
            }
        });

        //速度单下
        IB_Down_Only_Right.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                progressSpeed -= 1;
                speedDeal(progressSpeed);
//					PB_Speed.setProgress(progressSpeed,circle_point_imgSpeed);	
            }
        });

        //速度双下
        IB_Down_Both_Right.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                progressSpeed -= 2;
                speedDeal(progressSpeed);
//					String  progressSpeedString = ""+(progressSpeed/10)+"."+(progressSpeed%10);
//					TV_Speed_Show_Top.setText(progressSpeedString);
//					PB_Speed.setProgress(progressSpeed,circle_point_imgSpeed);	
            }
        });


        //commen布局代码
        //点击左边 大的      添加IB_Background_Left事件，
        IB_Background_Left.setOnClickListener(new ImageButton.OnClickListener() {
            //document.getElementById("ImageButton1").style.display = "none";
            @Override
            public void onClick(View v) {
                if (v == IB_Background_Left) {
                    //IB_Background_Left隐藏，布局显示
                    RL_Big_Layout.setVisibility(View.GONE);    //左边大的的GONE
                    RL_Small_Layout.setVisibility(View.VISIBLE);//左边小的VISIBLE
                    RL_Middle_Layout.setVisibility(View.GONE);//中间隐藏
                    RL_Big_Layout_Right.setVisibility(View.GONE);    //右边大的隐藏
                    RL_Small_Layout_Right.setVisibility(View.VISIBLE);//右边小的显示
                }
            }
        });

        //点击右边大的        添加IB_Background_Left点击事件，
        IB_Background_Right.setOnClickListener(new ImageButton.OnClickListener() {
            //document.getElementById("ImageButton1").style.display = "none";
            @Override
            public void onClick(View v) {
                if (v == IB_Background_Right) {
                    RL_Big_Layout.setVisibility(View.GONE);    //左边大的的GONE
                    RL_Small_Layout.setVisibility(View.VISIBLE);//左边小的VISIBLE
                    RL_Middle_Layout.setVisibility(View.GONE);//中间隐藏
                    RL_Big_Layout_Right.setVisibility(View.GONE);    //右边大的隐藏
                    RL_Small_Layout_Right.setVisibility(View.VISIBLE);//右边小的显示
                }
            }
        });

        // 点击左边小的      添加IB_Background_Left_in
        IB_Background_Left_in.setOnClickListener(new ImageButton.OnClickListener() {
            //document.getElementById("ImageButton1").style.display = "none";
            @Override
            public void onClick(View v) {
                if (v == IB_Background_Left_in) {
                    //大布局显示，小布局隐藏
                    RL_Small_Layout.setVisibility(View.GONE);//左边小的隐藏
                    RL_Big_Layout.setVisibility(View.VISIBLE);    //大的左边显示
                    RL_Middle_Layout.setVisibility(View.VISIBLE);//中间显示
                    RL_Big_Layout_Right.setVisibility(View.VISIBLE);    //右边大的显示
                    RL_Small_Layout_Right.setVisibility(View.GONE);//小的隐藏
                }
            }
        });

        IB_Background_Left_in.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    IB_Background_Left_in.setImageResource(R.drawable.background_left_in_onclick);
                } else {
                    IB_Background_Left_in.setImageResource(R.drawable.background_left_in);
                }
            }
        });

        //  点击右边小的    添加IB_Background_Left_in
        IB_Background_Right_in.setOnClickListener(new ImageButton.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v == IB_Background_Right_in) {
                    RL_Small_Layout.setVisibility(View.GONE);//左边小的隐藏
                    RL_Big_Layout.setVisibility(View.VISIBLE);    //大的左边显示
                    RL_Middle_Layout.setVisibility(View.VISIBLE);//中间显示
                    RL_Big_Layout_Right.setVisibility(View.VISIBLE);    //右边大的显示
                    RL_Small_Layout_Right.setVisibility(View.GONE);//小的隐藏
                }
            }
        });

        IB_Background_Right_in.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    IB_Background_Right_in.setImageResource(R.drawable.background_right_in_onclick);
                } else {
                    IB_Background_Right_in.setImageResource(R.drawable.background_right_in);
                }
            }
        });

        //返回
        Btn_Back_Bottom.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v == Btn_Back_Bottom) {
                    RL_Big_Layout.setVisibility(View.GONE);    //左边大的的GONE
                    RL_Small_Layout.setVisibility(View.VISIBLE);//左边小的VISIBLE
                    RL_Middle_Layout.setVisibility(View.GONE);//中间隐藏
                    RL_Big_Layout_Right.setVisibility(View.GONE);    //右边大的隐藏
                    RL_Small_Layout_Right.setVisibility(View.VISIBLE);//右边小的显示
                }
            }
        });

        //暂停
        Btn_Pause_Bottom.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (v == Btn_Pause_Bottom) {
                    RL_Big_Layout.setVisibility(View.GONE);    //左边大的的GONE
                    RL_Small_Layout.setVisibility(View.VISIBLE);//左边小的VISIBLE
                    RL_Middle_Layout.setVisibility(View.GONE);//中间隐藏
                    RL_Big_Layout_Right.setVisibility(View.GONE);    //右边大的隐藏
                    RL_Small_Layout_Right.setVisibility(View.VISIBLE);//右边小的显示
                }
            }
        });
    }

    /*
     * 速度数据的处理
     * */
    private void speedDeal(int progressSpeed) {

        String progressSpeedString = "" + (progressSpeed / 10) + "." + (progressSpeed % 10);
        TV_Speed_Show_Top.setText(progressSpeedString);
        PB_Speed.setProgress(progressSpeed, circle_point_imgSpeed);
    }

    /*
     * 密码写入文件
     * */
    public void setPasswordFlieDate(String password) {
        //实例化SharedPreferences对象（第1步）
        SharedPreferences mySharedPreferences = getSharedPreferences("password",
                Activity.MODE_PRIVATE);
        //实例化SharedPreferences.Editor对象（第二步）
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        //用putString的方法保存数据
        editor.putString("password", password);
        //提交当前数据
        editor.commit();
        //使用toast信息提示框提示成功写入数据

    }

    /*
     * 从文件读出密码
     * */
    public String getPasswordFileDate() {

        //同样，在读取SharedPreferences数据前要实例化出1个SharedPreferences对象
        SharedPreferences sharedPreferences = getSharedPreferences("password",
                Activity.MODE_PRIVATE);
        // 使用getString方法获得value，注意第2个参数是value的默认值
        String password = sharedPreferences.getString("password", "123456");

        //使用toast信息提示框显示文本里面的密码信息
        //Toast.makeText(this, "读取数据如下"+"\n"+"password" + password,
        ///Toast.LENGTH_LONG).show();
        return password;
    }

    /*
     * 初始化
     * */
    public void InitBase() {
        //commen初始化代码
        IB_Background_Left_in = (ImageButton) findViewById(R.id.IB_Background_Left_in);
        IB_Background_Left = (ImageButton) findViewById(R.id.IB_Background_Left);
        IB_Background_Right_in = (ImageButton) findViewById(R.id.IB_Background_Right_in);
        IB_Background_Right = (ImageButton) findViewById(R.id.IB_Background_Right);
        Btn_Back_Bottom = (Button) findViewById(R.id.Btn_Back_Bottom);
        Btn_Pause_Bottom = (Button) findViewById(R.id.Btn_Pause_Bottom);

        RL_Big_Layout = (RelativeLayout) findViewById(R.id.RL_Big_Layout);
        RL_Small_Layout = (RelativeLayout) findViewById(R.id.RL_Small_Layout);
        RL_Big_Layout_Right = (RelativeLayout) findViewById(R.id.RL_Big_Layout_Right);
        RL_Small_Layout_Right = (RelativeLayout) findViewById(R.id.RL_Small_Layout_Right);
        RL_Middle_Layout = (LinearLayout) findViewById(R.id.RL_Middle_Layout);
        RL_Top_Layout = (RelativeLayout) findViewById(R.id.Commen_First_Top);

        //控件初始化
        TV_Gradiend_Show_Top = (TextView) findViewById(R.id.TV_Gradiend_Show_Top);
        TV_Distance_Show_Top = (TextView) findViewById(R.id.TV_Distance_Show_Top);
        TV_Time_Show_Top = (TextView) findViewById(R.id.TV_Time_Show_Top);
        TV_Heat_Show_Top = (TextView) findViewById(R.id.TV_Heat_Show_Top);
        TV_Heart_Show_Top = (TextView) findViewById(R.id.TV_Heart_Show_Top);
        TV_Speed_Show_Top = (TextView) findViewById(R.id.TV_Speed_Show_Top);
        TV_Speed = (TextView) findViewById(R.id.TV_Speed);
        TV_Distance = (TextView) findViewById(R.id.TV_Distance);

        //progressBar  PB_Gradiend
        PB_Gradiend = (CircleProgressBar) findViewById(R.id.PB_Gradiend);
        IB_Up_Only = (ImageButton) findViewById(R.id.IB_Up_Only);
        IB_Up_Both = (ImageButton) findViewById(R.id.IB_Up_Both);
        IB_Down_Only = (ImageButton) findViewById(R.id.IB_Down_Only);
        IB_Down_Both = (ImageButton) findViewById(R.id.IB_Down_Both);
        this.circle_point_imgGradiend = (ImageView) findViewById(R.id.circle_point_imgGradiend);
        //	TV_GradiendShowLeft = (TextView)findViewById(R.id.TV_GradiendShowLeft);

        //progressBar  PB_Speed
        PB_Speed = (CircleProgressBar) findViewById(R.id.PB_Speed);
        IB_Up_Only_Right = (ImageButton) findViewById(R.id.IB_Up_Only_Right);
        IB_Up_Both_Right = (ImageButton) findViewById(R.id.IB_Up_Both_Right);
        IB_Down_Only_Right = (ImageButton) findViewById(R.id.IB_Down_Only_Right);
        IB_Down_Both_Right = (ImageButton) findViewById(R.id.IB_Down_Both_Right);
        this.circle_point_imgSpeed = (ImageView) findViewById(R.id.circle_point_imgSpeed);

        PB_Gradiend.setProgress(progressGradiend, circle_point_imgGradiend);
        PB_Speed.setProgress(progressSpeed, circle_point_imgSpeed);

        RL_Big_Layout.setVisibility(View.GONE);
        RL_Big_Layout_Right.setVisibility(View.GONE);
        RL_Middle_Layout.setVisibility(View.GONE);

        //设置初始密码
        //	setPasswordFlieDate("111111");
    }
}
