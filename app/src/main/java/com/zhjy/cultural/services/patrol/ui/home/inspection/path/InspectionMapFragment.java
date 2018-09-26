package com.zhjy.cultural.services.patrol.ui.home.inspection.path;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Polyline;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.PathBean;
import com.zhjy.cultural.services.patrol.databinding.FragmentMainInspectionMapBinding;
import com.zhjy.cultural.services.patrol.databinding.FragmentMainMapStreetBinding;
import com.zhjy.cultural.services.patrol.ui.base.AacFragment;
import com.zhjy.cultural.services.patrol.ui.home.inspection.InspectionFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jialg on 2018/1/24.
 */


public class InspectionMapFragment extends AacFragment<FragmentMainInspectionMapBinding> {

    private static String TAG = "InspectionMapFragment";

    private InspectionFragment mActivity;

    private PopupWindow mPopupWindow;

    private List<Object> PathNameList;

    public static InspectionMapFragment newInstance(InspectionFragment mActivity) {
        InspectionMapFragment instance  =  new InspectionMapFragment();
        instance.mActivity = mActivity;
        return instance;
    }


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_main_inspection_map;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView(){

        initBaiduMap();
        initPopWindow();
        //if(mActivity != null)
            //mActivity.setPopListener(popClickListener);
    }

    private void initBaiduMap(){
        //构建折线点坐标
        LatLng p1 = new LatLng(39.966894,116.328667);
        LatLng p2 = new LatLng(40.019003,116.314824);
        LatLng p3 = new LatLng(40.005354,116.280473);
        LatLng p4 = new LatLng(40.013256,116.213999);

        List<LatLng> points = new ArrayList<LatLng>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);

        //绘制折线
        OverlayOptions ooPolyline = new PolylineOptions().width(4)
                .color(0xFFEC6941).points(points);
        Polyline mPolyline = (Polyline) binding.map.getMap().addOverlay(ooPolyline);
        mPolyline.setDottedLine(true);
    }


    private void initPopWindow(){
        PathNameList = new ArrayList<Object>();
        for(int i = 0 ;i < 10;i++){
            PathBean bean = new PathBean("颐和园",i);
            PathNameList.add(bean);
        }
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        FragmentMainMapStreetBinding StreetBinding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_main_map_street,null,false);
        PathListAdapter adapter = new PathListAdapter(getChildFragmentManager(),PathNameList,mPathListener);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        StreetBinding.recyclerList.setLayoutManager(linearLayoutManager);
        StreetBinding.recyclerList.setAdapter(adapter);
        mPopupWindow = new PopupWindow(StreetBinding.getRoot(), LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);   //生成PopWindow
        mPopupWindow.setOutsideTouchable(true);
        /** 为其设置背景，使得其内外焦点都可以获得 */
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        mPopupWindow.setFocusable(true);
    }

    public void showPopWindow(){
        int offsetX = binding.map.getWidth() - mPopupWindow.getContentView().getMeasuredWidth();
        int offsetY = 0;
        mPopupWindow.showAtLocation(binding.map,Gravity.RIGHT,16,0);
    }

    public OnPathListInteractionListener mPathListener =  new OnPathListInteractionListener(){

        @Override
        public void OnPathListInteractionListener(PathBean bean) {
            Log.i(TAG,String.format("ID: %d NAME: %s",bean.getId(),bean.getName()));
            mPopupWindow.dismiss();
        }
    };

    public OnPopWindowClickListener popClickListener = new OnPopWindowClickListener(){
        @Override
        public void OnPopWindowClickListener(View view) {
            Log.i(TAG,((TextView)view).getText().toString());
            if(mPopupWindow != null){
                if( mPopupWindow.isShowing()){
                    mPopupWindow.dismiss();
                }else {
                    showPopWindow();
                }
            }
        }
    };

}
