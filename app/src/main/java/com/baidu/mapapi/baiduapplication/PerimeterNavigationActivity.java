package com.baidu.mapapi.baiduapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.zhjy.cultural.services.patrol.R;
import java.util.LinkedList;
import java.util.List;

public class PerimeterNavigationActivity extends AppCompatActivity {
    public static List<Activity> activityList = new LinkedList<Activity>();
    private LinearLayout topLine1   ;
    private LinearLayout topLine2;
    private TextView topText1;
    private TextView topText2;
    private int currentFragment = 0;
    NavigationFragment navigationFragment = new NavigationFragment();
    PerimeterFragment perimeterFragment = new PerimeterFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perimeter_navigation);
        ImageView imgBack =(ImageView)findViewById(R.id.title_back);
        imgBack.setOnClickListener(onClickListener);
        topLine1 = (LinearLayout)findViewById(R.id.top_line_1);
        topLine1.setOnClickListener(onClickListener);
        topLine2 = (LinearLayout)findViewById(R.id.top_line_2);
        topLine2.setOnClickListener(onClickListener);
        topText1 = (TextView)findViewById(R.id.top_text_1);
        topText2 = (TextView)findViewById(R.id.top_text_2);

        getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, navigationFragment).show(navigationFragment).commit();

    }

    private void chageView(){
        if(currentFragment % 2 != 0){
            topLine1.setBackgroundResource(R.drawable.rb_org_shape);
            topLine2.setBackgroundResource(R.color.org_bg_normal);
            topText1.setTextColor(0xffed6942);
            topText2.setTextColor(0xffffffff);
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container,navigationFragment).show(navigationFragment).commit();
            currentFragment ++;
        }
    }
    private void chageView2(){
        if(currentFragment %2 != 1){
            topLine1.setBackgroundResource(R.color.org_bg_normal);
            topLine2.setBackgroundResource(R.drawable.rb_org_shape);
            topText1.setTextColor(0xffffffff);
            topText2.setTextColor(0xffed6942);
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, perimeterFragment).show(perimeterFragment).commit();// 隐藏当前的fragment，add下一个到Activity中
            currentFragment ++;
        }
    }

    private  View.OnClickListener onClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.title_back:
                     finish();
                     break;
                case R.id.top_line_1:
                    chageView();
                    break;
                case R.id.top_line_2:
                    chageView2();
                    break;
                default:
                    break;
            }
        }
    };
}
