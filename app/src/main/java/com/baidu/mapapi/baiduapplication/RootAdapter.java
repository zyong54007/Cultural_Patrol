
package com.baidu.mapapi.baiduapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

public class RootAdapter extends BaseAdapter {

	public Context mContext;
	protected int currPos;
	public LayoutInflater mInfalter;
	@SuppressWarnings("deprecation")
	protected LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.FILL_PARENT,
			LinearLayout.LayoutParams.WRAP_CONTENT, 1);

	public RootAdapter(Context c) {
		this.mContext = c;
		mInfalter = LayoutInflater.from(mContext);
	}

	public void selectBg(int currPos) {
		this.currPos = currPos;
	}

	@Override
	public int getCount() {
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return null;
	}


}
