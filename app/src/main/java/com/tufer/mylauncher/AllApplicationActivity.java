package com.tufer.mylauncher;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.tufer.mylauncher.utils.Util;

import java.util.List;

/**
 * Created by Administrator on 2017/9/23 0023.
 */

public class AllApplicationActivity extends Activity {
    private static String TAG = "AllApplicationActivity";
    private GridView allApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_app);
        allApp= (GridView) findViewById(R.id.app_gv);
    }

    private void initapp() {
        final List<ResolveInfo> listApp = Util.getlistApp(this);

        allApp.setAdapter(new AllAppAdapter(this, listApp));
        allApp.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String packageName = listApp.get(position).activityInfo.applicationInfo.packageName;
                Log.i(TAG,"onItemClick-->>" + packageName);
                startAPP(packageName);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initapp();
    }

    public void startAPP(String appPackageName) {
//        if (TvCommonManager.getInstance().getCurrentTvInputSource() != TvCommonManager.INPUT_SOURCE_STORAGE) {
//
//            TvCommonManager.getInstance().setInputSource(
//                    TvCommonManager.INPUT_SOURCE_STORAGE);
//        }
        try {
            Intent intent = this.getPackageManager().getLaunchIntentForPackage(
                    appPackageName);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, getString(R.string.noAPP), Toast.LENGTH_LONG).show();
        }
    }
}
