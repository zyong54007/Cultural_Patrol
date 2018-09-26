package com.baidu.mapapi.baiduapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhjy.cultural.services.patrol.R;

import java.util.ArrayList;
import java.util.List;

public class PerimeterListAdapter extends RootAdapter {
	private List<PerimeterBean> arr;
	private Context context;
	 private String[] st = new String[] {"A", "B", "C", "D", "E", 
	            "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", 
	            "S", "T", "U", "V", "W", "X", "Y", "Z", "#"}; 
	public PerimeterListAdapter(Context c, List<PerimeterBean> arr) {
		super(c);
		this.context = c;
		this.arr = arr;
	}

	public void setDataSet(List<PerimeterBean> arr) {
		if (arr == null) {
			arr = new ArrayList<PerimeterBean>();
		}
		this.arr = arr;
	}

	@Override
	public int getCount() {
		return arr != null ? arr.size() : super.getCount();
	}

	@Override
	public Object getItem(int position) {
		return arr != null ? arr.get(position) : super.getItem(position);
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder vh;
		if (convertView == null) {
			convertView = mInfalter.inflate(R.layout.fragment_perimeter_listview, null);
			vh = new ViewHolder();
			vh.lineMain = (LinearLayout) convertView.findViewById(R.id.line_main);
			vh.name = (TextView) convertView.findViewById(R.id.name);
			vh.distance = (TextView) convertView.findViewById(R.id.distance);
			convertView.setTag(vh);
		} else{
			vh = (ViewHolder) convertView.getTag();
		}
			//vh.lineMain.setTag(arr.get(position).getPic());
			vh.name.setText((position+1)+"."+arr.get(position).getName());
			vh.distance.setText(arr.get(position).getDistance());
			return convertView;
	}

	static class ViewHolder {
		LinearLayout lineMain;
		TextView name;
		TextView distance;
	}
}
