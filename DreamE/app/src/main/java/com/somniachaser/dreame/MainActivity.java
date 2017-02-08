package com.somniachaser.dreame;

import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;

import Helpers.CustomTabActivityHelper;

public class MainActivity extends AppCompatActivity implements CustomTabActivityHelper.ConnectionCallback {

    private CustomTabActivityHelper customTabActivityHelper;
    private final String websiteURL = "http://10.0.1.61:8082";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customTabActivityHelper = new CustomTabActivityHelper();
        customTabActivityHelper.setConnectionCallback(this);

        Bitmap bitmapShareIcon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_share);

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

        Uri uri  = Uri.parse(websiteURL);
        try {
            CustomTabsIntent customTabsIntent =
                    new CustomTabsIntent.Builder(customTabActivityHelper.getSession())
                            .setShowTitle(true)
                            .setStartAnimations(this, android.R.anim.slide_out_right, android.R.anim.slide_in_left)
                            .setExitAnimations(this, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                            .setToolbarColor(Color.BLUE)
                            .setActionButton(bitmapShareIcon, "Share Link", pendingIntent, true)
                            .setCloseButtonIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_arrow_back))
                            .build();
            CustomTabActivityHelper.openCustomTab(
                    this, customTabsIntent, uri, new WebviewFallback());
        } catch (ActivityNotFoundException e) {
            customTabActivityHelper.mayLaunchUrl(uri, null, null);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        customTabActivityHelper.setConnectionCallback(null);
    }

    @Override
    protected void onStart() {
        super.onStart();
        customTabActivityHelper.bindCustomTabsService(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        customTabActivityHelper.unbindCustomTabsService(this);
    }

    @Override
    public void onCustomTabsConnected() {

    }

    @Override
    public void onCustomTabsDisconnected() {

    }
}