package com.olhodedeus;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.*;
import android.view.*;
import android.content.pm.PackageManager;
import android.net.Uri;

public class MainActivity extends Activity {
  WebView web;
  @Override public void onCreate(Bundle b){ super.onCreate(b); getWindow().setFlags(1024,1024); web=new WebView(this); web.setBackgroundColor(0xFF000000); WebSettings s=web.getSettings(); s.setJavaScriptEnabled(true); s.setDomStorageEnabled(true); s.setMediaPlaybackRequiresUserGesture(false); s.setAllowFileAccess(true); s.setAllowContentAccess(true); web.setWebChromeClient(new WebChromeClient(){
    @Override public void onPermissionRequest(final PermissionRequest r){ runOnUiThread(() -> r.grant(r.getResources())); }
  }); web.setWebViewClient(new WebViewClient()); web.loadUrl("file:///android_asset/index.html"); setContentView(web); if(android.os.Build.VERSION.SDK_INT>=23 && checkSelfPermission(Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED) requestPermissions(new String[]{Manifest.permission.CAMERA},10); }
  @Override public void onBackPressed(){ if(web.canGoBack()) web.goBack(); else super.onBackPressed(); }
}
