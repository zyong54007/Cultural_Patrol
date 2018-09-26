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

public class NavigationBusListAdapter extends RootAdapter {
	private String title ="";
	private int walkDistance = 0;
	private String entrance  = "" ;
	private int totalNumber = 0;
	private List<TransitRouteLine> arr;
	private Context context;
	public NavigationBusListAdapter(Context c, List<TransitRouteLine> arr) {
		super(c);
		this.context = c;
		this.arr = arr;
	}
	/**
	 * 设置数据集合
	 * 
	 */
	public void setDataSet(List<TransitRouteLine> arr) {
		if (arr == null) {
			arr = new ArrayList<TransitRouteLine>();
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
			convertView = mInfalter.inflate(R.layout.fragment_navigation_bus_listview, null);
			vh = new ViewHolder();
			vh.lineMain = (LinearLayout) convertView.findViewById(R.id.line_main);
			vh.tag = (TextView) convertView.findViewById(R.id.tag);
			vh.time = (TextView) convertView.findViewById(R.id.time);
			vh.distance = (TextView) convertView.findViewById(R.id.distance);
			vh.busNumber = (TextView) convertView.findViewById(R.id.bus_number);
			vh.number = (TextView) convertView.findViewById(R.id.number);
			convertView.setTag(vh);
		} else{
			vh = (ViewHolder) convertView.getTag();
		}
			//vh.lineMain.setTag(arr.get(position).getPic());
			getAllName(arr.get(position));
			if(0 == position){
				vh.tag.setVisibility(View.VISIBLE);
			}
			if(arr.get(position).getDuration() > 0)
				vh.time.setText(getTime(arr.get(position).getDuration()));
			if(walkDistance > 0)
				vh.distance.setText("步行"+walkDistance+"米");
			vh.busNumber.setText(title);
			if(totalNumber > 0)
				vh.number.setText(totalNumber + "站"+" · "+entrance+"上车");
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
			if(0 == time / 60){
				timeName = "1分钟";
			}
		}
		return timeName;
	}


	public void  getAllName(TransitRouteLine line){
		title ="";
		walkDistance = 0;
		entrance  = "" ;
		totalNumber = 0;
		ArrayList<TransitRouteLine.TransitStep> steps = (ArrayList<TransitRouteLine.TransitStep>) line.getAllStep();
		for (int i =0;i<steps.size();i++) {
			//TransitRouteLine.TransitStep step : steps
			TransitRouteLine.TransitStep step = steps.get(i);
			if(BUSLINE.equals(step.getStepType()) || SUBWAY.equals(step.getStepType())){
				if(!title.isEmpty()){
					title=title+" -> "+step.getVehicleInfo().getTitle();
				}else {
					title=step.getVehicleInfo().getTitle();
				}
				if("".equals(entrance) || entrance.isEmpty()){
					entrance = step.getEntrance().getTitle();
				}
				totalNumber = totalNumber + step.getVehicleInfo().getPassStationNum();
				/*Pattern pattern = Pattern.compile("经过\\d+站");
				Matcher matcher = pattern.matcher(step.getInstructions());
				matcher.find();
				Pattern patternNumber = Pattern.compile("\\d+");
				Matcher matcherNumber = patternNumber.matcher(matcher.group());
				matcherNumber.find();
				try {
					totalNumber = totalNumber + Integer.parseInt(matcherNumber.group());
				} catch (NumberFormatException e) {
				}*/
			}else{
				walkDistance = walkDistance + step.getDistance();
			}

		}

	}

	public String getLineName(String instructions ){
		String lineName = instructions.split(",")[0].replace("乘坐","");
		if(lineName.indexOf("(") > 0){
			lineName = lineName.substring(0,lineName.indexOf("("));
		}
		return lineName;
	}


	static class ViewHolder {
		LinearLayout lineMain;
		TextView tag;
		TextView time;
		TextView distance;
		TextView busNumber;
		TextView number;
	}
}
