package com.example.schematest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.webkit.JavascriptInterface;

import java.util.List;

public class SchemeTest extends Activity {

    Context mContext;

    public SchemeTest(Context mContext) {
        this.mContext = mContext;
    }

    @JavascriptInterface
    public void SchemeTest() {

        boolean isExist = false;

        PackageManager packageManager = mContext.getPackageManager();
        List<ResolveInfo> mApps;
        Intent mIntent = new Intent(Intent.ACTION_MAIN, null);
        mIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        mApps = packageManager.queryIntentActivities(mIntent, 0);

        try {
            for (int i = 0; i < mApps.size(); i++) {
                if(mApps.get(i).activityInfo.packageName.startsWith("com.google.android.youtube")){
                    isExist = true;
                    break;
                }
            }
        } catch (Exception e) {
            isExist = false;
        }

        // 설치되어 있으면
        if(isExist){
            Intent intent = mContext.getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        }else{
            Intent marketLaunch = new Intent(Intent.ACTION_VIEW);
            marketLaunch.setData(Uri.parse("market://details?id=com.google.android.youtube"));
            mContext.startActivity(marketLaunch);
        }

    }
}
