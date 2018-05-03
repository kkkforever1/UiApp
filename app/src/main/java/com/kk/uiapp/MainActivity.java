package com.kk.uiapp;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e("tag","onCreate");
    }


    public void onClickScan(View v){
        try {
            /*Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI"));
            intent.putExtra("LauncherUI.From.Scaner.Shortcut", true);
            intent.setFlags(335544320);
            intent.setAction("android.intent.action.VIEW");
            startActivity(intent);*/

            /*Intent intent = getPackageManager().getLaunchIntentForPackage("com.tencent.mm");
            intent.putExtra("LauncherUI.From.Scaner.Shortcut", true);
            startActivity(intent);*/

            /*Intent intent = new Intent("com.tencent.mm.action.BIZSHORTCUT");
            intent.setPackage("com.tencent.mm");
            intent.putExtra("LauncherUI.From.Scaner.Shortcut", true);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            startActivity(intent);*/


            Intent intent = new Intent();
            ComponentName comp = new ComponentName("com.tencent.mm",
                    "com.tencent.mm.plugin.scanner.ui.BaseScanUI");
            intent.setComponent(comp);
            intent.putExtra("other", "true");
            intent.setAction("android.intent.action.VIEW");
            startActivity(intent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void onClickMall(View v){
        try {
            Intent intent = new Intent();
//            ComponentName comp = new ComponentName("com.tencent.mm",
//                    "com.tencent.mm.plugin.mall.ui.MallIndexUI");
            ComponentName comp = new ComponentName("com.tencent.mm",
                    "com.tencent.mm.plugin.wallet.balance.ui.WalletBalanceManagerUI");
            intent.setComponent(comp);
//            intent.putExtra("other", "true");
            intent.setAction("android.intent.action.VIEW");
            startActivity(intent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void onClickChat(View v){
        try {
            /*Intent pickIntent = new Intent(Intent.ACTION_PICK_ACTIVITY);
            Intent mainIntent = new Intent () ;
            mainIntent.setAction(Intent.ACTION_MAIN);
            mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

            pickIntent.putExtra(Intent.EXTRA_INTENT, mainIntent);
            pickIntent.putExtra(Intent.EXTRA_TITLE, "选择应用程序"); //设置界面title

            //继续选择所有应用程序
            startActivityForResult(pickIntent,0 );*/


            Intent intent = new Intent();
            ComponentName comp = new ComponentName("com.tencent.mm",
                    "com.tencent.mm.plugin.offline.ui.WalletOfflineCoinPurseUI");
            intent.setComponent(comp);
            intent.putExtra("other", "true");
            intent.setAction("android.intent.action.VIEW");
            startActivity(intent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void onClickChange(View v){
       int oriention = getRequestedOrientation();
        Log.e("tag","oriention:"+oriention);
        if(oriention == -1){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }else if(oriention == 1){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }else if(oriention == 0){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.e("tag","onResume");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.e("tag","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("tag","onStop");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("tag","onDestroy");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e("tag","onConfigurationChanged:"+newConfig.orientation);
    }
}
