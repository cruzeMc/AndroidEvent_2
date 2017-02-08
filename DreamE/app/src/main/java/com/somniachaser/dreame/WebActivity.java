package com.somniachaser.dreame;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class WebActivity extends AppCompatActivity {

    final String CUSTOM_TAB_PACKAGE_NAME = "com.android.chrome";
    final String websiteURL = "http://10.0.1.61:8082";

    CustomTabsClient mCustomTabsClient;
    CustomTabsSession mCustomTabsSession;
    CustomTabsServiceConnection mCustomTabsServiceConnection;
    CustomTabsIntent mCustomTabsIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_acitivity);

        mCustomTabsServiceConnection = new CustomTabsServiceConnection() {
            @Override
            public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
                mCustomTabsClient= customTabsClient;
                mCustomTabsClient.warmup(0L);
                mCustomTabsSession = mCustomTabsClient.newSession(null);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mCustomTabsClient = null;
            }
        };

        CustomTabsClient.bindCustomTabsService(this, CUSTOM_TAB_PACKAGE_NAME, mCustomTabsServiceConnection);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_share);

        Intent bitmapIntent = new Intent(Intent.ACTION_SEND);
        bitmapIntent.setType("text/plain");
        bitmapIntent.putExtra(Intent.EXTRA_TEXT, websiteURL);

        int requestCode = 100;

        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                requestCode,
                bitmapIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        mCustomTabsIntent = new CustomTabsIntent.Builder(mCustomTabsSession)
                .setShowTitle(true)
                .setStartAnimations(this, android.R.anim.slide_out_right, android.R.anim.slide_in_left)
                .setExitAnimations(this, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .setToolbarColor(Color.BLUE)
                .setActionButton(bitmap, "Share Link", pendingIntent, true)
                .addDefaultShareMenuItem()
                .build();
    }

    public void chromeCustomTabExample(View view) {
        mCustomTabsIntent.launchUrl(this, Uri.parse(websiteURL));
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data)  {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 0xe110)
//            ActiviyFinishedNowDoSomethingAmazing();
//    }
}