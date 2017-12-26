package com.ADA.activity;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;

import com.ADA.mbh.R;

import android.R.integer;
import android.R.layout;
import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.net.PskKeyManager;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.test.InstrumentationTestSuite;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

public class ModeNowActivity extends BaseActivity {

    //��SetPerActivity����������
    public String IB_Type_String;
    public String Type;

    //ֹͣ����ͣ������
    private LinearLayout IB_Stop_NowMode;
    private LinearLayout IB_Pause_NowMode;
    private LinearLayout IB_Back_NowMode;

    //�ֶ����������Ъ
    private LinearLayout IB_Hand_Move;
    private LinearLayout IB_Fluctuate;
    private LinearLayout IB_Climbing;
    private LinearLayout IB_Interval;
    private LinearLayout IB_LostWeight;
    private LinearLayout IB_Mountion;
    private LinearLayout IB_Burn;

    //ʱ�䣬���룬����
    private LinearLayout IB_Time;
    private LinearLayout IB_Distance;
    private LinearLayout IB_Hot;

    //����
    private ImageButton IB_Heart_65;
    private ImageButton IB_Heart_75;
    private ImageButton IB_Heart_85;

    //����Ϊѭ������viewpage������
    private ViewPager viewPager;
    //������
    private MyAdapter adapter;
    //װ�ؽ���
    private List<View> list;
    //�����
    private LayoutInflater inflater;
    //layout
    private View Now_Mode_First;
    private View Now_Mode_Second;
    private View Now_Mode_Three;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_mode);

        //��ʼ��
        InitBase();
        BaselistionEvent();
        init();
        inItShow();
        listionEvent();
    }

    //��ʼ����
    private void inItShow() {
        // TODO Auto-generated method stub

        RL_Big_Layout.setVisibility(View.GONE);
        RL_Big_Layout_Right.setVisibility(View.GONE);
        RL_Middle_Layout.setVisibility(View.GONE);
    }

    /*
     * �����¼�
     * */
    private void listionEvent() {
        // TODO Auto-generated method stub

        //ֹͣ
        IB_Stop_NowMode.setOnClickListener(new ImageButton.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

            }
        });

        //��ͣ
        IB_Pause_NowMode.setOnClickListener(new ImageButton.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

            }
        });

        //����
        IB_Back_NowMode.setOnClickListener(new ImageButton.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                ModeNowActivity.this.finish();
            }
        });
    }

    /*
     * ��ʼ��
     * */
    private void init() {

        // TODO Auto-generated method stub
        IB_Stop_NowMode = (LinearLayout) findViewById(R.id.IB_Stop);
        IB_Pause_NowMode = (LinearLayout) findViewById(R.id.IB_Pause);
        IB_Back_NowMode = (LinearLayout) findViewById(R.id.IB_Back);

        //����Ϊѭ�������ʼ��
        //InitViewPager
        viewPager = (ViewPager) findViewById(R.id.VPager_ModeNow);//��ȡviewPager
        inflater = getLayoutInflater().from(this);//�����Ϊ��ǰlayout
        Now_Mode_First = inflater.inflate(R.layout.now_mode_first, null);//ͨ��������Ѳ�����䵽View
        Now_Mode_Second = inflater.inflate(R.layout.now_mode_second, null);
        Now_Mode_Three = inflater.inflate(R.layout.now_mode_three, null);
        list = new ArrayList<View>();//new listview
        list.add(Now_Mode_First);//��View����list
        list.add(Now_Mode_Second);
        list.add(Now_Mode_Three);
        //������������
        adapter = new MyAdapter();
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
    }

    /*
     * �Զ�����������pagerAdapter��
     */
    public class MyAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // TODO Auto-generated method stub
            ((ViewPager) container).addView(list.get(position), 0);

/*����������Ŀؼ���ʼ���������¼�*/
            //	View Now_Mode_Three =  inflater.inflate(R.layout.now_mode_three, container, false);
            //�ֶ����������Ъ
            IB_Hand_Move = (LinearLayout) Now_Mode_First.findViewById(R.id.IB_Hand_Move);
            IB_Fluctuate = (LinearLayout) Now_Mode_First.findViewById(R.id.IB_Fluctuate);
            IB_Climbing = (LinearLayout) Now_Mode_First.findViewById(R.id.IB_Climbing);
            IB_Interval = (LinearLayout) Now_Mode_First.findViewById(R.id.IB_Interval);
            IB_LostWeight = (LinearLayout) Now_Mode_First.findViewById(R.id.IB_LostWeight);
            IB_Mountion = (LinearLayout) Now_Mode_First.findViewById(R.id.IB_Mountion);
            IB_Burn = (LinearLayout) Now_Mode_First.findViewById(R.id.IB_Burn);

            //ʱ�䣬�����������ֳ�ʼ��IB_Distance
            IB_Time = (LinearLayout) Now_Mode_Three.findViewById(R.id.IB_Time);
            IB_Hot = (LinearLayout) Now_Mode_Three.findViewById(R.id.IB_Hot);
            IB_Distance = (LinearLayout) Now_Mode_Three.findViewById(R.id.IB_Distacne);

            //����
            IB_Heart_65 = (ImageButton) Now_Mode_Second.findViewById(R.id.IB_Heart_65);
            IB_Heart_75 = (ImageButton) Now_Mode_Second.findViewById(R.id.IB_Heart_75);
            IB_Heart_85 = (ImageButton) Now_Mode_Second.findViewById(R.id.IB_Heart_85);

            //���
            IB_Fluctuate.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    Type = "IB_Fluctuate";
                    sendDate();
                }
            });

            //����
            IB_Climbing.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    Type = "IB_Climbing";
                    sendDate();
                }
            });

            //��Ъ
            IB_Interval.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    Type = "IB_Interval";
                    sendDate();
                }
            });

            //����
            IB_LostWeight.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    Type = "IB_LostWeight";
                    sendDate();
                }
            });

            //ɽ��
            IB_Mountion.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    Type = "IB_Mountion";
                    sendDate();
                }
            });

            //ȼ֬
            IB_Burn.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    Type = "IB_Burn";
                    sendDate();
                }
            });

            //�ֶ�
            IB_Hand_Move.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    Type = "IB_Hand";
                    Intent intent = new Intent();
                    intent.setClass(ModeNowActivity.this, TaskActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("IB_Type_String", Type);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });

            //ʱ��
            IB_Time.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    Type = "IB_Time";
                    sendDate();
                }
            });

            //����
            IB_Hot.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    Type = "IB_Hot";
                    sendDate();
                }
            });

            //����
            IB_Distance.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub

                    Type = "IB_Distance";
                    sendDate();
                }
            });

            //����65
            IB_Heart_65.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub

                    Type = "IB_Heart_65";
                    sendDate();
                }
            });

            //����75
            IB_Heart_75.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub

                    Type = "IB_Heart_75";
                    sendDate();
                }
            });

            //����85
            IB_Heart_85.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub

                    Type = "IB_Heart_85";
                    sendDate();
                }
            });

            return list.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // TODO Auto-generated method stub
            ((ViewPager) container).removeView(list.get(position));
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        public void sendDate() {

            Intent intent = new Intent();
            intent.setClass(ModeNowActivity.this, SetPerActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("IB_Type_String", Type);
            intent.putExtras(bundle);
            android.util.Log.i("ssssssssssssssssssssssssssssssssssssssss    ", Type);
            startActivity(intent);

        }
    }
}
