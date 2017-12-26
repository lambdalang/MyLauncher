package com.ADA.activity;

import java.io.ObjectOutputStream.PutField;

import javax.security.auth.login.LoginException;

import com.ADA.mbh.R;


import android.R.drawable;
import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class FactoryPatternSet extends BaseActivity {

    //���빦��ģ��
    private View Commen_Two;
    //	private int getNewPassWord = 0;
    private int passWord = 0;
    private int getOldPassWord = 0;
    public static boolean isPasswordOpen=false;


    private int defaultSIENG = 0;
    private int defaultMaxLncline = 20;
    private int defaultSpeedTime = 2;
    private int defaultAutoOil = 800;
    private int defaultoilTime = 10;
    private int defaultLanguage = 1;
    private float defaultMinSpeed = 10;
    private float defaultMaxSpeed = 20;
    private float defaultWhell = 5;

    //����ģ��
    private String version;
    private TextView TV_showVersions;

    //ѡ����9���е���һ��
    private View RL_SIENG;
    private View RL_minSpeed;
    private View RL_maxSpeed;
    private View RL_maxIncline;
    private View RL_speedTime;
    private View RL_whell;
    private View RL_autoOil;
    private View RL_oilTime;
    private View RL_language;

    //3�������л�
    private View FactoryPatternset_elevator;
    private View Password_Close;
    private View RL_PasswordOpen;
    private View LL_showDate;

    //9��չʾ����
    private TextView TV_SIENG;
    private TextView TV_minSpeed;
    private TextView TV_maxSpeed;
    private TextView TV_maxIncline;
    private TextView TV_speedTime;
    private TextView TV_whell;
    private TextView TV_autoOil;
    private TextView TV_oilTime;
    private TextView TV_language;

    //�ұ�5��ѡ��
    private TextView TV_generalSet;
    private TextView TV_elevator;
    private TextView TV_password;
    private TextView TV_recoverSet;
    private TextView TV_exit;

    //��������
    private TextView TV_run;
    private TextView TV_reset;
    private TextView TV_setNumber;

    //�ر�����
    private ImageButton IB_closePasswordSet;
//	private TextView TV_saveClosePassword;

    //������
    private ImageButton IB_openPasswordSet;
    private TextView TV_setDistance;
    private TextView TV_oldPasswordShow;
    private TextView TV_newPasswordShow;
    private TextView TV_openPasswordSave;

    //�ж�Ҫ��ʾ��˭
    private int SIENG = 1;
    private int minSpeed = 2;
    private int maxSpeed = 3;
    private int maxIncline = 4;
    private int speedTime = 5;
    private int whell = 6;
    private int autoOil = 7;
    private int oilTime = 8;
    private int language = 9;
    private int setNumber = 10;
    private int setDistance = 11;
    private int oldPasswordShow = 12;
    private int newPasswordShow = 13;

    public static String Temp = "";//��ż���������ַ�����Ȼ������жϷ����Ӧ��������

    private int choice;

    private Button Btn_One;
    private Button Btn_Two;
    private Button Btn_Three;
    private Button Btn_Four;
    private Button Btn_Five;
    private Button Btn_Six;
    private Button Btn_Seven;
    private Button Btn_Eight;
    private Button Btn_Nine;
    private Button Btn_Point;
    private Button Btn_Zero;
    private Button Btn_Enter;
    private Button Btn_Delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.factorypatternset);

        inIt();
        listionEvent();
        try {
            inItEvent();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /*
     * ��ȡ�汾��
     * */
    private void inItEvent() throws Exception {
        // TODO Auto-generated method stub

        version = getVersionName();
        TV_showVersions.setText(version);

    }

    /*
* �����¼�
* */
    private void listionEvent() {
        // TODO Auto-generated method stub

        //һ������
        TV_generalSet.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Password_Close.setVisibility(View.GONE);
                FactoryPatternset_elevator.setVisibility(View.GONE);
                LL_showDate.setVisibility(View.VISIBLE);

                TV_generalSet.setBackgroundResource(R.drawable.set_blue);
                TV_elevator.setBackgroundResource(R.drawable.set_black);
                TV_password.setBackgroundResource(R.drawable.set_black);
            }
        });

        //������
        TV_elevator.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Log.i("00000", "�������������");
                Password_Close.setVisibility(View.GONE);
                FactoryPatternset_elevator.setVisibility(View.VISIBLE);
                LL_showDate.setVisibility(View.GONE);

                TV_elevator.setBackgroundResource(R.drawable.set_blue);
                TV_generalSet.setBackgroundResource(R.drawable.set_black);
                TV_password.setBackgroundResource(R.drawable.set_black);
            }
        });

        //����
        TV_password.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(isPasswordOpen){
                    Password_Close.setVisibility(View.GONE);
                    RL_PasswordOpen.setVisibility(View.VISIBLE);
                }else{
                    Password_Close.setVisibility(View.VISIBLE);
                    RL_PasswordOpen.setVisibility(View.GONE);
                }

                FactoryPatternset_elevator.setVisibility(View.GONE);
                LL_showDate.setVisibility(View.GONE);

                TV_password.setBackgroundResource(R.drawable.set_blue);
                TV_elevator.setBackgroundResource(R.drawable.set_black);
                TV_generalSet.setBackgroundResource(R.drawable.set_black);
            }
        });

        //�ָ���������
        TV_recoverSet.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Password_Close.setVisibility(View.GONE);
                FactoryPatternset_elevator.setVisibility(View.GONE);
                LL_showDate.setVisibility(View.VISIBLE);

                TV_generalSet.setBackgroundResource(R.drawable.set_blue);
                TV_elevator.setBackgroundResource(R.drawable.set_black);
                TV_password.setBackgroundResource(R.drawable.set_black);

                TV_SIENG.setText("" + defaultSIENG);
                TV_minSpeed.setText("" + defaultMinSpeed);
                TV_maxSpeed.setText("" + defaultMaxSpeed);
                TV_maxIncline.setText("" + defaultMaxLncline);
                TV_speedTime.setText("" + defaultSpeedTime);
                TV_whell.setText("" + defaultWhell);
                TV_autoOil.setText("" + defaultAutoOil);
                TV_oilTime.setText("" + defaultoilTime);
                TV_language.setText("" + defaultLanguage);
            }
        });

        //�ر����밴ť���л������������
        IB_closePasswordSet.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                isPasswordOpen=true;
                RL_PasswordOpen.setVisibility(View.VISIBLE);
                Password_Close.setVisibility(View.GONE);
                FactoryPatternset_elevator.setVisibility(View.GONE);
                LL_showDate.setVisibility(View.GONE);
            }
        });

        //�ر����밴ť���л����ر��������
        IB_openPasswordSet.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                isPasswordOpen=false;
                RL_PasswordOpen.setVisibility(View.GONE);
                Password_Close.setVisibility(View.VISIBLE);
                FactoryPatternset_elevator.setVisibility(View.GONE);
                LL_showDate.setVisibility(View.GONE);
            }
        });

        //�˳�
        TV_exit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                finish();
            }
        });

        RL_SIENG.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                TV_SIENG.setText("");
                choice = SIENG;
                Btn_Point.setEnabled(false);
                Temp = "";//�м��������
            }
        });

        RL_minSpeed.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                TV_minSpeed.setText("");
                choice = minSpeed;
                Btn_Point.setEnabled(true);
                Temp = "";//�м��������
            }
        });

        RL_maxSpeed.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                TV_maxSpeed.setText("");
                choice = maxSpeed;
                Btn_Point.setEnabled(true);
                Temp = "";//�м��������
            }
        });

        RL_maxIncline.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                TV_maxIncline.setText("");
                choice = maxIncline;
                Btn_Point.setEnabled(false);
                Temp = "";//�м��������
            }
        });

        RL_speedTime.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                TV_speedTime.setText("");
                choice = speedTime;
                Btn_Point.setEnabled(false);
                Temp = "";//�м��������
            }
        });

        RL_whell.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                TV_whell.setText("");
                choice = whell;
                Btn_Point.setEnabled(true);
                Temp = "";//�м��������
            }
        });

        RL_autoOil.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                TV_autoOil.setText("");
                choice = autoOil;
                Btn_Point.setEnabled(false);
                Temp = "";//�м��������
            }
        });

        RL_oilTime.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                TV_oilTime.setText("");
                choice = oilTime;
                Btn_Point.setEnabled(false);
                Temp = "";//�м��������
            }
        });

        RL_language.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                TV_language.setText("");
                Btn_Point.setEnabled(false);
                choice = language;
            }
        });

/*
 * �������������
 * */
        TV_setNumber.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                choice = setNumber;
                Btn_Point.setEnabled(false);
                Temp = "";//�м��������
            }
        });

        TV_reset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Temp="";
                TV_setNumber.setText("");
            }
        });
			
/*
 * ���������	����
 * */

        TV_setDistance.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Btn_Point.setEnabled(false);
                choice = setDistance;
                Temp = "";//�м��������
            }
        });

        TV_oldPasswordShow.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Btn_Point.setEnabled(false);

                TV_oldPasswordShow.setText("");
                TV_oldPasswordShow.setBackgroundResource(R.drawable.set_blue);
                TV_newPasswordShow.setBackgroundResource(R.drawable.set_black);
                choice = oldPasswordShow;
                Temp = "";//�м��������
            }
        });

        TV_newPasswordShow.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Btn_Point.setEnabled(false);

                TV_newPasswordShow.setText("");
                TV_newPasswordShow.setBackgroundResource(R.drawable.set_blue);
                TV_oldPasswordShow.setBackgroundResource(R.drawable.set_black);
                choice = newPasswordShow;
                Temp = "";//�м��������

            }
        });

        TV_openPasswordSave.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub



                TV_oldPasswordShow.setBackgroundResource(R.drawable.set_black);
                TV_newPasswordShow.setBackgroundResource(R.drawable.set_black);
                TV_openPasswordSave.setBackgroundResource(R.drawable.set_blue);

                Log.i("oldPasswordShow", "" + oldPasswordShow);
                String passwordFile = getPasswordFileDate();//�õ��ļ��е�����
                int passwordFileInt = Integer.parseInt(passwordFile);//�ļ��е������Ϊint����
                if (getOldPassWord == passwordFileInt) {
                    if(passWord!=0){
                        AlertDialog.Builder builder = new AlertDialog.Builder(FactoryPatternSet.this);
                        setPasswordFlieDate("" + passWord);
                        Log.i("���뵽�ļ��е�passWord", "" + passWord);
                        builder.setTitle(R.string.str_tishi);
                        builder.setMessage(R.string.str_password_uadata);
                        builder.setPositiveButton(R.string.str_confirm, null);
                        builder.show();
                        TV_openPasswordSave.setBackgroundResource(R.drawable.set_black);
                        TV_newPasswordShow.setText("");
                        TV_oldPasswordShow.setText("");
                    }else{
                        Toast.makeText(FactoryPatternSet.this, R.string.str_new_password_null, LENGTH_SHORT).show();
                        TV_newPasswordShow.setText("");
                        TV_oldPasswordShow.setText("");
                    }

                } else {
                    choice = oldPasswordShow;
                    Temp = "";//�м��������
                    TV_newPasswordShow.setText("");
                    TV_oldPasswordShow.setText("");
                    TV_newPasswordShow.setBackgroundResource(R.drawable.set_black);
                    AlertDialog.Builder builder = new AlertDialog.Builder(FactoryPatternSet.this);
                    builder.setTitle(R.string.str_tishi);
                    builder.setMessage(R.string.str_old_password_error);
                    builder.setPositiveButton(R.string.Confirm, null);
                    builder.show();
                }

            }
        });
			
/*
 * ���������̵���¼�
 */
        //��� 1
        Btn_One.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Temp += "1";
                sortSelect();
            }
        });

        //��� 2
        Btn_Two.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Temp += "2";
                sortSelect();
            }
        });

        //��� 3
        Btn_Three.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Temp += "3";
                sortSelect();
            }
        });

        //��� 4
        Btn_Four.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Temp += "4";
                sortSelect();
            }
        });

        //��� 5
        Btn_Five.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Temp += "5";
                sortSelect();
            }
        });

        //��� 6
        Btn_Six.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Temp += "6";
                sortSelect();
            }
        });

        //��� 7
        Btn_Seven.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Temp += "7";
                sortSelect();
            }
        });

        //��� 8
        Btn_Eight.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Temp += "8";
                sortSelect();
            }
        });

        //��� 9
        Btn_Nine.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Temp += "9";
                sortSelect();
            }
        });

        //��� 0
        Btn_Zero.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Temp += "0";
                sortSelect();
            }
        });

        //��� .
        Btn_Point.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(Temp.indexOf(".")<0){
                    Temp += ".";
                    sortSelect();
                }

            }
        });

        //���enter��
        Btn_Enter.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //RL_Keyboard_Layout.setVisibility(View.INVISIBLE);
            }
        });

        //���Btn_Delete��
        Btn_Delete.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (Temp != null && Temp.length() > 0) {
                    Temp = Temp.substring(0, Temp.length()-1);

                }else if(Temp.length() == 0){
                    Temp="0";
                }
                sortSelect();
            }
        });
    }

    /*
     * �жϵ����˭
     * */
    private void sortSelect() {
        boolean flag=false;
        // TODO Auto-generated method stub
        if (Temp.equals("")) {
            Temp = "0";
            flag=true;
        }
        switch (choice) {
            case 1:
                setData(5,true,TV_SIENG,1f,"");
                break;
            case 2:
                setData(4,false,TV_minSpeed,6.00f,"Km/h");
                break;
            case 3:
                setData(5,false,TV_maxSpeed,30.00f,"Km/h");
                break;
            case 4:
                setData(5,true,TV_maxIncline,25f,"");
                break;
            case 5:
                setData(5,true,TV_speedTime,5f,"s");
                break;
            case 6:
                setData(4,false,TV_whell,8.00f,"Hz");
                break;
            case 7:
                setData(5,true,TV_autoOil,999f,"Km");
                break;
            case 8:
                setData(5,true,TV_oilTime,50f,"s");
                break;
            case 9:
                setData(5,true,TV_language,1f,"");
                break;
            case 10:
                setData(2,true,TV_setNumber,99f,"");
//                if (Temp.length() > 2) {
//
//                    Temp = Temp.substring(0, 2);
//                }
//                TV_setNumber.setText("" + Temp);
                break;
            case 11:
                setData(7,true,TV_setDistance,999999f,"");
//                int setDistancetemp = Integer.parseInt(Temp);
//                setDistancetemp = (setDistancetemp < 999999) ? setDistancetemp : 999999;
//                TV_setDistance.setText("" + setDistancetemp);
                break;
            case 12:
                getOldPassWord=setData(7,true,TV_oldPasswordShow,999999f,"");
//                getOldPassWord = Integer.parseInt(Temp);
//                getOldPassWord = (getOldPassWord < 999999) ? getOldPassWord : 999999;
//                TV_oldPasswordShow.setText("" + getOldPassWord);
                break;
            case 13:
                passWord=setData(7,true,TV_newPasswordShow,999999f,"");
//                passWord = Integer.parseInt(Temp);
//                passWord = (passWord < 999999) ? passWord : 999999;
//                TV_newPasswordShow.setText("" + passWord);
                break;
            default:
                break;
        }
        //Toast.makeText(this,"hou:"+Temp,Toast.LENGTH_SHORT).show();
        if (Temp.equals("0")&&flag) {
            Temp = "";
        }
    }

    private int setData(int length,boolean isInt,TextView tv,float max,String danwei) {
        if(Temp.length()>length){
            Temp = Temp.substring(0, length);
            Toast.makeText(this, getString(R.string.str_data_lenght_out), LENGTH_SHORT).show();
        }
        if(isInt){
            int temp=0;
            try{
                temp = Integer.parseInt(Temp);
            }catch (Exception e){
                Toast.makeText(this, getString(R.string.str_number_error), LENGTH_SHORT).show();
                Temp="";
                tv.setText("" + temp);
                return 0;
            }
            if(temp>max){
                temp= (int) max;
                Toast.makeText(this, getString(R.string.str_number_out)+max+danwei, LENGTH_SHORT).show();
                Temp="";
            }
            tv.setText("" + temp);
            return temp;
        }else{
            Float temp=0.0f;
            try{
                temp = Float.parseFloat(Temp);
            }catch (Exception e){
                Toast.makeText(this,getString(R.string.str_number_error), LENGTH_SHORT).show();
                Temp="";
                tv.setText("" + temp + danwei);
                return 0;
            }
            if(temp>max){
                temp=max;
                Toast.makeText(this,getString(R.string.str_number_out), LENGTH_SHORT).show();
                Temp="";
            }
            tv.setText("" + temp + danwei);
        }

        return 0;


    }

    /*
     * ��ȡ��ǰӦ�ð汾��
     * */
    private String getVersionName() throws Exception {
        // ��ȡpackagemanager��ʵ��
        PackageManager packageManager = getPackageManager();
        // getPackageName()���㵱ǰ��İ�����0�����ǻ�ȡ�汾��Ϣ
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        String version = packInfo.versionName;
        return version;
    }

    /*
     * ��ʼ��
     * */
    private void inIt() {
        // TODO Auto-generated method stub

        Commen_Two = (RelativeLayout) findViewById(R.id.Commen_Two);
        //��ʾ�汾��
        TV_showVersions = (TextView) findViewById(R.id.TV_showVersions);
        //�ɵ���ľŸ�����
        RL_SIENG = (RelativeLayout) findViewById(R.id.RL_SIENG);
        RL_minSpeed = (RelativeLayout) findViewById(R.id.RL_minSpeed);
        RL_maxSpeed = (RelativeLayout) findViewById(R.id.RL_maxSpeed);
        RL_maxIncline = (RelativeLayout) findViewById(R.id.RL_maxIncline);
        RL_speedTime = (RelativeLayout) findViewById(R.id.RL_speedTime);
        RL_whell = (RelativeLayout) findViewById(R.id.RL_whell);
        RL_autoOil = (RelativeLayout) findViewById(R.id.RL_autoOil);
        RL_oilTime = (RelativeLayout) findViewById(R.id.RL_oilTime);
        RL_language = (RelativeLayout) findViewById(R.id.RL_language);

        //���أ����ֵ�3��
        FactoryPatternset_elevator = (RelativeLayout) findViewById(R.id.FactoryPatternset_elevator);
        Password_Close = (RelativeLayout) findViewById(R.id.Password_Close);
        RL_PasswordOpen = (RelativeLayout) findViewById(R.id.RL_PasswordOpen);
        LL_showDate = (LinearLayout) findViewById(R.id.LL_showDate);


        //����
        Btn_One = (Button) findViewById(R.id.Btn_One);
        Btn_Two = (Button) findViewById(R.id.Btn_Two);
        Btn_Three = (Button) findViewById(R.id.Btn_Three);
        Btn_Four = (Button) findViewById(R.id.Btn_Four);
        Btn_Five = (Button) findViewById(R.id.Btn_Five);
        Btn_Six = (Button) findViewById(R.id.Btn_Six);
        Btn_Seven = (Button) findViewById(R.id.Btn_Seven);
        Btn_Eight = (Button) findViewById(R.id.Btn_Eight);
        Btn_Nine = (Button) findViewById(R.id.Btn_Nine);
        Btn_Zero = (Button) findViewById(R.id.Btn_Zero);
        Btn_Point = (Button) findViewById(R.id.Btn_Point);
        Btn_Enter = (Button) findViewById(R.id.Btn_Enter);
        Btn_Delete = (Button) findViewById(R.id.Btn_Delete);

        //9��չʾ���ݵ�TextView
        TV_SIENG = (TextView) findViewById(R.id.TV_SIENG);
        TV_minSpeed = (TextView) findViewById(R.id.TV_minSpeed);
        TV_maxSpeed = (TextView) findViewById(R.id.TV_maxSpeed);
        TV_maxIncline = (TextView) findViewById(R.id.TV_maxIncline);
        TV_speedTime = (TextView) findViewById(R.id.TV_speedTime);
        TV_whell = (TextView) findViewById(R.id.TV_whell);
        TV_autoOil = (TextView) findViewById(R.id.TV_autoOil);
        TV_oilTime = (TextView) findViewById(R.id.TV_oilTime);
        TV_language = (TextView) findViewById(R.id.TV_language);

        TV_setNumber = (TextView) findViewById(R.id.TV_setNumber);

        TV_generalSet = (TextView) findViewById(R.id.TV_generalSet);
        TV_elevator = (TextView) findViewById(R.id.TV_elevator);
        TV_password = (TextView) findViewById(R.id.TV_password);
        TV_recoverSet = (TextView) findViewById(R.id.TV_recoverSet);
        TV_exit = (TextView) findViewById(R.id.TV_exit);

        //������
        TV_run = (TextView) findViewById(R.id.TV_run);
        TV_reset = (TextView) findViewById(R.id.TV_reset);

        //���������
        IB_openPasswordSet = (ImageButton) findViewById(R.id.IB_openPasswordSet);
        TV_setDistance = (TextView) findViewById(R.id.TV_setDistance);
        TV_oldPasswordShow = (TextView) findViewById(R.id.TV_oldPasswordShow);
        TV_newPasswordShow = (TextView) findViewById(R.id.TV_newPasswordShow);
        TV_openPasswordSave = (TextView) findViewById(R.id.TV_openPasswordSave);

        //�ر��������
        IB_closePasswordSet = (ImageButton) findViewById(R.id.IB_closePasswordSet);
        //����Ĺ���ȥ����
//			TV_saveClosePassword = (TextView)findViewById(R.id.TV_saveClosePassword);

        Commen_Two.setVisibility(View.GONE);
    }
}
