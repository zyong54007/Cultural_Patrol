package com.baidu.mapapi.baiduapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mapapi.search.route.TransitRouteLine;
import com.zhjy.cultural.services.patrol.R;

import java.util.ArrayList;
import java.util.List;

import static com.baidu.mapapi.search.route.TransitRouteLine.TransitStep.TransitRouteStepType.BUSLINE;
import static com.baidu.mapapi.search.route.TransitRouteLine.TransitStep.TransitRouteStepType.SUBWAY;
import static com.baidu.mapapi.search.route.TransitRouteLine.TransitStep.TransitRouteStepType.WAKLING;

public class BusLineListAdapter extends RootAdapter {
	private List<TransitRouteLine.TransitStep> arr;
	private Context context;
	public BusLineListAdapter(Context c, List<TransitRouteLine.TransitStep> arr) {
		super(c);
		this.context = c;
		this.arr = arr;
	}
	/**
	 * 设置数据集合
	 * 
	 */
	public void setDataSet(List<TransitRouteLine.TransitStep> arr) {
		if (arr == null) {
			arr = new ArrayList<TransitRouteLine.TransitStep>();
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
			convertView = mInfalter.inflate(R.layout.fragment_bus_line_listview, null);
			vh = new ViewHolder();
			vh.line_start = (LinearLayout) convertView.findViewById(R.id.line_start);
			vh.line_subway = (LinearLayout) convertView.findViewById(R.id.line_subway);
			vh.line_walk = (LinearLayout) convertView.findViewById(R.id.line_walk);
			vh.line_bus = (LinearLayout) convertView.findViewById(R.id.line_bus);
			vh.line_end = (LinearLayout) convertView.findViewById(R.id.line_end);
			vh.start_time = (TextView) convertView.findViewById(R.id.start_time);
			vh.start_instructions = (TextView) convertView.findViewById(R.id.start_instructions);
			vh.subway_name = (TextView) convertView.findViewById(R.id.subway_name);
			vh.subway_time = (TextView) convertView.findViewById(R.id.subway_time);
			vh.subway_instructions = (TextView) convertView.findViewById(R.id.subway_instructions);
			vh.subway_entrance = (TextView) convertView.findViewById(R.id.subway_entrance);
			vh.subway_exit = (TextView) convertView.findViewById(R.id.subway_exit);
			vh.subway_number = (TextView) convertView.findViewById(R.id.subway_number);
			vh.walk_time = (TextView) convertView.findViewById(R.id.walk_time);
			vh.walk_instructions = (TextView) convertView.findViewById(R.id.walk_instructions);
			vh.bus_name = (TextView) convertView.findViewById(R.id.bus_name);
			vh.bus_time = (TextView) convertView.findViewById(R.id.bus_time);
			vh.bus_instructions = (TextView) convertView.findViewById(R.id.bus_instructions);
			vh.bus_entrance = (TextView) convertView.findViewById(R.id.bus_entrance);
			vh.bus_exit = (TextView) convertView.findViewById(R.id.bus_exit);
			vh.bus_number = (TextView) convertView.findViewById(R.id.bus_number);
			vh.end_time = (TextView) convertView.findViewById(R.id.end_time);
			vh.end_instructions = (TextView) convertView.findViewById(R.id.end_instructions);
			convertView.setTag(vh);
		} else{
			vh = (ViewHolder) convertView.getTag();
		}
			if(position == 0 && WAKLING.equals(arr.get(position).getStepType())){
				vh.line_start.setVisibility(View.VISIBLE);
				vh.line_end.setVisibility(View.GONE);
				vh.line_walk.setVisibility(View.GONE);
				vh.line_subway.setVisibility(View.GONE);
				vh.line_bus.setVisibility(View.GONE);

				vh.start_time.setText(getTime(arr.get(position).getDuration()));
				vh.start_instructions.setText(arr.get(position).getInstructions());
			}else if(position == (arr.size() - 1)){
				vh.line_end.setVisibility(View.VISIBLE);
				vh.line_start.setVisibility(View.GONE);
				vh.line_walk.setVisibility(View.GONE);
				vh.line_subway.setVisibility(View.GONE);
				vh.line_bus.setVisibility(View.GONE);
				vh.end_time.setText(getTime(arr.get(position).getDuration()));
				vh.end_instructions.setText(arr.get(position).getInstructions());

			}else if(WAKLING.equals(arr.get(position).getStepType())){
				vh.line_walk.setVisibility(View.VISIBLE);
				vh.line_start.setVisibility(View.GONE);
				vh.line_end.setVisibility(View.GONE);
				vh.line_subway.setVisibility(View.GONE);
				vh.line_bus.setVisibility(View.GONE);
				vh.walk_time.setText(getTime(arr.get(position).getDuration()));
				vh.walk_instructions.setText(arr.get(position).getInstructions());
			}else if(SUBWAY.equals(arr.get(position).getStepType())){
				vh.line_subway.setVisibility(View.VISIBLE);
				vh.line_start.setVisibility(View.GONE);
				vh.line_end.setVisibility(View.GONE);
				vh.line_walk.setVisibility(View.GONE);
				vh.line_bus.setVisibility(View.GONE);
				vh.subway_name.setText(arr.get(position).getVehicleInfo().getTitle());
				vh.subway_time.setText(getTime(arr.get(position).getDuration()));
				vh.subway_instructions.setText(arr.get(position).getInstructions());
				vh.subway_entrance.setText(arr.get(position).getEntrance().getTitle());
				vh.subway_exit.setText(arr.get(position).getExit().getTitle());
				vh.subway_number.setText(arr.get(position).getVehicleInfo().getPassStationNum()+"站");
			}else if(BUSLINE.equals(arr.get(position).getStepType())){
				vh.line_bus.setVisibility(View.VISIBLE);
				vh.line_start.setVisibility(View.GONE);
				vh.line_end.setVisibility(View.GONE);
				vh.line_walk.setVisibility(View.GONE);
				vh.line_subway.setVisibility(View.GONE);
				vh.bus_name.setText(arr.get(position).getVehicleInfo().getTitle());
				vh.bus_time.setText(getTime(arr.get(position).getDuration()));
				vh.bus_instructions.setText(arr.get(position).getInstructions());
				vh.bus_entrance.setText(arr.get(position).getEntrance().getTitle());
				vh.bus_exit.setText(arr.get(position).getExit().getTitle());
				vh.bus_number.setText(arr.get(position).getVehicleInfo().getPassStationNum()+"站");
			}
			//vh.lineMain.setTag(arr.get(position).getPic());
			//vh.name.setText((position+1)+"."+arr.get(position).getInstructions());
			//vh.distance.setText(arr.get(position).getDistance()+"");
			return convertView;
	}

	public String getTime(int time){
		String timeName  = "";
		int h = 0;
		int m = 0;
		h = time / 3600;
		if(h > 1){
			timeName = h+"小时" + (time % 3600)/60 +"分钟";
		}else {
			timeName = (time / 60) + "分钟";
			if(0 == time / 60)
				timeName =  "1分钟";
		}
		return timeName;
	}

	static class ViewHolder {
		LinearLayout line_start;
		LinearLayout line_subway;
		LinearLayout line_walk;
		LinearLayout line_bus;
		LinearLayout line_end;

		TextView start_time;
		TextView start_instructions;

		TextView subway_name;
		TextView subway_time;
		TextView subway_instructions;
		TextView subway_entrance;
		TextView subway_exit;
		TextView subway_number;

		TextView walk_time;
		TextView walk_instructions;

		TextView bus_name;
		TextView bus_time;
		TextView bus_instructions;
		TextView bus_entrance;
		TextView bus_exit;
		TextView bus_number;

		TextView end_time;
		TextView end_instructions;


	}
}
