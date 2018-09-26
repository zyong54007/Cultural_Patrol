package com.zhjy.cultural.services.patrol.ui.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.jph.takephoto.model.TImage;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.databinding.PopupWindowForImageBinding;
import com.zhjy.cultural.services.patrol.ui.view.adapter.OnImageClickListener;
import com.zhjy.cultural.services.patrol.ui.view.adapter.ImageAdapter;

import java.util.ArrayList;

/**
 * Created by jialg on 2018/2/2.
 */

public class PopupWindowForImage extends PopupWindow implements ViewPager.OnPageChangeListener {

    public ImageView[] imageViews;

    private Activity context;

    private ArrayList<TImage> pDatas;

    PopupWindowForImageBinding WindowBinding;

    private int currentIndex = 0;

    private ImageAdapter imageAdapter;

    private OnImageClickListener imageClickListener;

    private boolean isCancle = true;

    public PopupWindowForImage(final Activity context, ArrayList<TImage> pDatas, int position, OnImageClickListener imageClickListener) {
        super(context);
        this.context = context;
        this.pDatas = pDatas;
        this.currentIndex = position;
        this.imageClickListener = imageClickListener;
        this.isCancle = true;
        initView();
    }

    public PopupWindowForImage(final Activity context, ArrayList<TImage> pDatas, int position, OnImageClickListener imageClickListener, boolean isCancle) {
        super(context);
        this.context = context;
        this.pDatas = pDatas;
        this.currentIndex = position;
        this.imageClickListener = imageClickListener;
        this.isCancle = isCancle;
        initView();
    }

    private void initView() {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        WindowBinding = DataBindingUtil.inflate(layoutInflater, R.layout.popup_window_for_image, null, false);
        WindowBinding.lineDot.setVisibility(View.GONE);
        if (isCancle) {
            WindowBinding.imgCancle.setVisibility(View.VISIBLE);
            WindowBinding.imgCancle.setOnClickListener(deleteListener);
        }
        //设置SelectPicPopupWindow的View
        this.setContentView(WindowBinding.getRoot());
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        //this.setAnimationStyle(R.style.popwin_anim_style);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        WindowBinding.getRoot().setOnTouchListener(new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            public boolean onTouch(View v, MotionEvent event) {

                int height = WindowBinding.linePopLayout.getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        //dismiss();
                    }
                }
                return true;
            }
        });

        initPageView();

    }

    private void initPageView() {
//        String originalPath = pDatas.get(0).getOriginalPath();
//        Log.i("TAG", "目前的图片地址========================" + originalPath);

        imageAdapter = new ImageAdapter(imageClickListener, pDatas);
        WindowBinding.viewPageImg.setAdapter(imageAdapter);
        //设置缓存数为展示的数目
        WindowBinding.viewPageImg.setOffscreenPageLimit(pDatas.size());
        WindowBinding.viewPageImg.setPageMargin(0);
        WindowBinding.viewPageImg.addOnPageChangeListener(this);
        if (pDatas.size() > 0) {
            WindowBinding.viewPageImg.setVisibility(View.VISIBLE);
            WindowBinding.textEmpty.setVisibility(View.GONE);
            addImageView();
        } else {
            WindowBinding.textEmpty.setVisibility(View.VISIBLE);
            WindowBinding.viewPageImg.setVisibility(View.GONE);
            WindowBinding.textEmpty.setOnClickListener(clickListener);
        }
        WindowBinding.viewPageImg.setCurrentItem(currentIndex);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.e("position", position + "");
        currentIndex = position;
        setImageView(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void addImageView() {
        imageViews = new ImageView[pDatas.size()];
        for (int i = 0; i < pDatas.size(); i++) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));  //设置图片宽高
            if (currentIndex == i) {
                imageView.setImageResource(R.mipmap.lbccon);
            } else {
                imageView.setImageResource(R.mipmap.lbcc);
            }
            imageView.setPadding(8, 0, 0, 0);
            imageViews[i] = imageView;
            WindowBinding.lineDot.addView(imageView);
        }
        WindowBinding.lineDot.setVisibility(View.VISIBLE);
    }

    public void setImageView(int position) {
        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i].setImageResource(R.mipmap.lbcc);
        }
        imageViews[position].setImageResource(R.mipmap.lbccon);
    }

    public void deletelImageView() {
        WindowBinding.lineDot.removeAllViewsInLayout();
        addImageView();
    }

    private View.OnClickListener deleteListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            imageAdapter.removeData(currentIndex);
            imageAdapter.notifyDataSetChanged();
            deletelImageView();
            if (imageAdapter.getCount() == 0) {
                TImage image = TImage.of("", TImage.FromType.OTHER);
                imageClickListener.OnImageClickListener(view, image);
            }
            if (imageAdapter.getCount() != 0) {
                TImage image = TImage.of("", TImage.FromType.OTHER);
                imageClickListener.OnImageClickListener(view, image, 1);
            }


        }
    };

    private View.OnClickListener clickListener = new View.OnClickListener() {
        public void onClick(View v) {
            dismiss();
        }
    };

}
