package com.baidu.mapapi.baiduapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.overlayutil.TransitRouteOverlay;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.route.TransitRouteLine;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.zhjy.cultural.services.patrol.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.baidu.mapapi.search.route.TransitRouteLine.TransitStep.TransitRouteStepType.BUSLINE;
import static com.baidu.mapapi.search.route.TransitRouteLine.TransitStep.TransitRouteStepType.SUBWAY;
import static com.baidu.mapapi.search.route.TransitRouteLine.TransitStep.TransitRouteStepType.WAKLING;

public class BusLineActivity extends Activity {
    private ArrayList<TransitRouteLine.TransitStep > arrList ;
    private ListView listView;
    private BusLineListAdapter adapter;
    private TransitRouteResult result;
    private MapView mMapView;
    private CustomViewPager mViewPager;
    private SparseArray<View> viewDatas = new SparseArray<View>();
    private MainViewpagerAdapter mainAdapter;
    private LayoutInflater inflater;
    private ImageView[] imageViews;
    private LinearLayout lineDot;
    private int position = 0 ;
    private LinearLayout lineList;
    private Boolean isToTop = false;
    private LinearLayout linepop;
    private int mapZoom=15;
    private BaiduMap mBaidumap;
    private ImageView imageBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_line);
        imageBack = (ImageView)findViewById(R.id.back);
        imageBack.setOnClickListener(clickListener);
        inflater = LayoutInflater.from(this);
        mMapView = (MapView) findViewById(R.id.map);
        mMapView.showZoomControls(false);
        mBaidumap = mMapView.getMap();
        result = MyApplication.getBusRouteResult();
        linepop = (LinearLayout)findViewById(R.id.linepop);
        linepop.setOnClickListener(clickListener);
        lineList = (LinearLayout)findViewById(R.id.line_list);
        lineList.setOnClickListener(clickListener);
        listView = (ListView)findViewById(R.id.listView1);
        arrList = new ArrayList<TransitRouteLine.TransitStep>();
        adapter = new BusLineListAdapter(this, arrList);
        listView.setAdapter(adapter);
        mViewPager = (CustomViewPager)findViewById(R.id.vp_bus_line);
        lineDot =(LinearLayout)findViewById(R.id.line_dot);
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                position = bundle.getInt("position");
            }
        }
        setData();
    }

    public void setData(){
        List<TransitRouteLine> routeLines = result.getRouteLines();
        for(int i = 0;i<routeLines.size();i++){
            TransitRouteLine line  = routeLines.get(i);
            View view= inflater.inflate(R.layout.activity_bus_line_viewpager, null);
            LinearLayout pagerLine =(LinearLayout)view.findViewById(R.id.line_pager) ;
            pagerLine.setOnClickListener(clickListener);
            TextView titleText =(TextView)view.findViewById(R.id.title);
            titleText.setText(getTitleName(line));
            TextView timeText =(TextView)view.findViewById(R.id.time);
            timeText.setText(getTime(line.getDuration())+" · " +getTotalNumber(line)+"站 · " +"步行"+getWalkDistance(line)+"米");
            viewDatas.put(i,view);
        }
        addImageView();
        mainAdapter = new MainViewpagerAdapter(BusLineActivity.this, viewDatas);
        mViewPager.setAdapter(mainAdapter);

        //设置缓存数为展示的数目
        mViewPager.setOffscreenPageLimit(viewDatas.size());
        mViewPager.setPageMargin(0);
        mViewPager.setOnPageChangeListener(onPageChangeListener);
        mViewPager.setCurrentItem(position);
        initPageData(position);
        setRouteLine(position);
    }

    public void addImageView(){
        imageViews = new ImageView[result.getRouteLines().size()];
        for(int i = 0; i<result.getRouteLines().size();i++){
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));  //设置图片宽高
            imageView.setPadding(8,0,8,0);
            imageView.setImageResource(R.mipmap.radius_gray);
            if(i == position)
                imageView.setImageResource(R.mipmap.radius_blue);
            imageViews[i] = imageView;
            lineDot.addView(imageView);
        }
        lineDot.setVisibility(View.VISIBLE);
    }

    ViewPager.OnPageChangeListener onPageChangeListener =	new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            // TODO Auto-generated method stub
            mBaidumap.clear();
            for(int i =0;i<imageViews.length;i++){
                imageViews[i].setImageResource(R.mipmap.radius_gray);
            }
            imageViews[position].setImageResource(R.mipmap.radius_blue);
            initPageData(position);
            setRouteLine(position);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub

        }
    };

    public void setRouteLine(int position){
        TransitRouteLine line  = result.getRouteLines().get(position);
        TransitRouteOverlay overlay = new TransitRouteOverlay(mBaidumap);
        mBaidumap.setOnMarkerClickListener(overlay);
        overlay.setData(line);
        overlay.addToMap();
        overlay.zoomToSpan();
    }

    public String  getTitleName(TransitRouteLine line){
        String title = "";
        ArrayList<TransitRouteLine.TransitStep> steps = (ArrayList<TransitRouteLine.TransitStep>) line.getAllStep();
        for (int i =0;i<steps.size();i++) {
            //TransitRouteLine.TransitStep step : steps
            TransitRouteLine.TransitStep step = steps.get(i);
            if(BUSLINE.equals(step.getStepType()) || SUBWAY.equals(step.getStepType())){
                if(!title.isEmpty()){
                    title=title+" -> "+getLineName(step.getInstructions());
                }else {
                    title=getLineName(step.getInstructions());
                }
            }
        }
        return title;
    }
    public int getTotalNumber(TransitRouteLine line){
        int totalNumber = 0;
        ArrayList<TransitRouteLine.TransitStep> steps = (ArrayList<TransitRouteLine.TransitStep>) line.getAllStep();
        for (int i =0;i<steps.size();i++) {
            //TransitRouteLine.TransitStep step : steps
            TransitRouteLine.TransitStep step = steps.get(i);
            if(BUSLINE.equals(step.getStepType()) || SUBWAY.equals(step.getStepType())){
                Pattern pattern = Pattern.compile("经过\\d+站");
                Matcher matcher = pattern.matcher(step.getInstructions());
                matcher.find();
                Pattern patternNumber = Pattern.compile("\\d+");
                Matcher matcherNumber = patternNumber.matcher(matcher.group());
                matcherNumber.find();
                try {
                    totalNumber = totalNumber + Integer.parseInt(matcherNumber.group());
                } catch (NumberFormatException e) {
                }
            }
        }
        return totalNumber;
    }
    public int getWalkDistance(TransitRouteLine line){
        int walkDistance = 0;
        ArrayList<TransitRouteLine.TransitStep> steps = (ArrayList<TransitRouteLine.TransitStep>) line.getAllStep();
        for (int i =0;i<steps.size();i++) {
            //TransitRouteLine.TransitStep step : steps
            TransitRouteLine.TransitStep step = steps.get(i);
            if(WAKLING.equals(step.getStepType())){
                walkDistance = walkDistance + step.getDistance();
            }
        }
        return walkDistance;
    }
    public String getLineName(String instructions ){
        String lineName = instructions.split(",")[0].replace("乘坐","");
        if(lineName.indexOf("(") > 0){
            lineName = lineName.substring(0,lineName.indexOf("("));
        }
        return lineName;
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
        }
        return timeName;
    }

    public void initPageData(int position){
        TransitRouteLine line  = result.getRouteLines().get(position);
        RouteNode start = line.getStarting();
        RouteNode end = line.getTerminal();
        ArrayList<TransitRouteLine.TransitStep> steps = (ArrayList<TransitRouteLine.TransitStep>) line.getAllStep();
        System.out.println("getDistance:"+line.getDistance());
        System.out.println("getDuration:"+line.getDuration());
        System.out.println("getTitle:"+line.getTitle());
        System.out.println("Start Node:getTitle:"+start.getTitle());
        System.out.println("Start Node:getUid:"+start.getUid());
        System.out.println("Start Node:getLocation:"+start.getLocation());
        System.out.println("End Node:getTitle:"+end.getTitle());
        System.out.println("End Node:getUid:"+end.getUid());
        System.out.println("End Node:getLocation:"+end.getLocation());
        System.out.println("Step ..............");
        arrList.clear();
        for (TransitRouteLine.TransitStep step : steps) {
            System.out.println("Distance:"+step.getDistance());
            System.out.println("Duration:"+step.getDuration());
            System.out.println("Instructions:"+step.getInstructions());
            System.out.println("StepType:"+step.getStepType());
            if(step.getEntrance().getTitle() != null){
                System.out.println("Entrance:"+step.getEntrance().getTitle());
            }
            if(step.getExit().getTitle() != null){
                System.out.println("Exit:"+step.getExit().getTitle());
            }
            if(step.getVehicleInfo() != null){
                System.out.println("VehicleInfo Title:"+step.getVehicleInfo().getTitle());
                System.out.println("VehicleInfo PassStationNum:"+step.getVehicleInfo().getPassStationNum());
                System.out.println("VehicleInfo TotalPrice:"+step.getVehicleInfo().getTotalPrice());
                System.out.println("VehicleInfo ZonePrice:"+step.getVehicleInfo().getZonePrice());

            }

            arrList.add(step);
            //System.out.println(step.getVehicleInfo().getTitle());
        }
        adapter.notifyDataSetChanged();
    }

    private void scrollToTop(){

       final float scale = this.getResources().getDisplayMetrics().density;
       final int height = (int) (480 * scale + 0.5f); //dp
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
        params.gravity =  Gravity.BOTTOM;
        lineList.setLayoutParams(params);
        //上面图片的动画效果
        /*TranslateAnimation topAnim = new TranslateAnimation(0,0,0,-height);
        topAnim.setDuration(200);
        //动画终止时停留在最后一帧~不然会回到没有执行之前的状态
        topAnim.setFillAfter(true);
        linepop.startAnimation(topAnim);
        topAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //清除动画
                linepop.clearAnimation();
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) lineList.getLayoutParams();
                params.gravity =  Gravity.BOTTOM;
                params.height = params.height + height;
                lineList.setLayoutParams(params);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });*/
        isToTop = true;
    }

    private void scrollToBottom(){
        final float scale = this.getResources().getDisplayMetrics().density;
        int height = (int) (192 * scale + 0.5f); //dp
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
        params.gravity =  Gravity.BOTTOM;
        lineList.setLayoutParams(params);
        isToTop = false;
    }
    private View.OnClickListener clickListener  = new View.OnClickListener(){
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.back:
                    finish();
                    break;
                case R.id.linepop:
                    if(!isToTop){
                        scrollToTop();
                    }else{
                        scrollToBottom();
                    }
                    break;
                case R.id.line_pager:
                    if(!isToTop){
                        scrollToTop();
                    }else{
                        scrollToBottom();
                    }
                    break;
                default:
                    break;
            }
        }
    };

}
