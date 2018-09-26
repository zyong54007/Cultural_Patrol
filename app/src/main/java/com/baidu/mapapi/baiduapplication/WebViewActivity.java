package com.baidu.mapapi.baiduapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.zhjy.cultural.services.patrol.R;


public class WebViewActivity extends Activity {
	final public static int REQUEST_CODE_ACCESS_CALL_PHONE = 000001;
	private WebView webView;
	private String url;
	private String name;
	/**
	 * ATTENTION: This was auto-generated to implement the App Indexing API.
	 * See https://g.co/AppIndexing/AndroidStudio for more information.
	 */

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		setContentView(R.layout.activity_web_view);
		ImageView titleBack = (ImageView) findViewById(R.id.title_back);
		titleBack.setOnClickListener(clickListener);
		TextView titleName = (TextView) findViewById(R.id.title_name);
		webView = (WebView) findViewById(R.id.webView1);
		webView.requestFocus(View.FOCUS_DOWN);
		WebSettings settings = webView.getSettings();
		//settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN); 
		settings.setDomStorageEnabled(true);
		settings.setJavaScriptEnabled(true);
		Intent intent = getIntent();
		if (intent != null) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				url = (String) bundle.get("url");
				name = (String) bundle.get("name");
				titleName.setText(name);
			}
		}
		webView.loadUrl(url);
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				System.err.println(url);
				//view.loadUrl(url);
				if (!url.isEmpty()) {
					if (url.length() > 4 & url.startsWith("tel:")) {
						if (Build.VERSION.SDK_INT >= 23) {
							int checkAccessFineLocationPermission = ContextCompat.checkSelfPermission(WebViewActivity.this,Manifest.permission.CALL_PHONE);
							if(checkAccessFineLocationPermission != PackageManager.PERMISSION_GRANTED){
								ActivityCompat.requestPermissions(WebViewActivity.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CODE_ACCESS_CALL_PHONE);
							}else{
								Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));
								startActivity(intent);
								return true;
							}
						} else {
							Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));
							startActivity(intent);
							return true;
						}

					} else if (url.length() > 15 & url.startsWith("baidumap://map/")) {
						return false;
						//String loadUlr = url.replaceFirst("baidumap://map/", "http://api.map.baidu.com/") +"&output=html";
						//System.err.println(loadUlr);
						//view.loadUrl(loadUlr);
						//return true;
					} else if (url.length() > 13 & url.startsWith("intent://map/")) {
						return false;
						//String loadUlr = url.replaceFirst("intent://map/", "http://api.map.baidu.com/")+"&output=html";
						//view.loadUrl(loadUlr);
						//return true;
					} else {

						return false;
						//view.loadUrl(url);
					}
				}
				return false;
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO
				  /*if(getCurrentFocus()!=null)
					{
		                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))  
		                .hideSoftInputFromWindow(getCurrentFocus()  
		                        .getWindowToken(),  
		                        InputMethodManager.HIDE_NOT_ALWAYS);   
		            } */
			}
		});
	}

	private OnClickListener clickListener = new OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.title_back:
					finish();
					break;
				default:
					break;
			}
		}
	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
			webView.goBack();// 返回前一个页面
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Checks the orientation of the screen 
		if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			//Toast.makeText(this, "横屏模式", Toast.LENGTH_SHORT).show();
		} else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
			//Toast.makeText(this, "竖屏模式", Toast.LENGTH_SHORT).show();
		}
	}


	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		switch (requestCode) {
			case REQUEST_CODE_ACCESS_CALL_PHONE:
				if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					Toast.makeText(MyApplication.getInstance(), "授权成功", Toast.LENGTH_SHORT).show();

				} else {
					Toast.makeText(MyApplication.getInstance(), "授权失败:动态获取权限被拒绝", Toast.LENGTH_SHORT).show();
				}
				break;
		}

	}
}
