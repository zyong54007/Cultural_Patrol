package com.zhjy.cultural.services.patrol.ui.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.util.ViewFinder;

public class Topbar extends LinearLayout {
    private ViewFinder finder;
    private String title;
    private String leftText;
    private String rightText;
    private Drawable rightIcon;
    private boolean leftVis;
    private int bgColor;


    protected boolean useThemestatusBarColor = false;//是否使用特殊的标题栏背景颜色，android5.0以上可以设置状态栏背景色，如果不使用则使用透明色值
    protected boolean useStatusBarColor = true;//是否使用状态栏文字和图标为暗色，如果状态栏采用了白色系，则需要使状态栏和图标为暗色，android6.0以上可以设置

    public Topbar(Context context) {
        super(context);
        init(context, null);
    }

    public Topbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public Topbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        int compatPadingTop = 0;
        // android 4.4以上将Toolbar添加状态栏高度的上边距，沉浸到状态栏下方
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            compatPadingTop = getStatusBarHeight();
        }

        //((Activity) context).getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

//        this.setPadding(getPaddingLeft(), getPaddingTop() + compatPadingTop, getPaddingRight(), getPaddingBottom());

        LayoutInflater.from(context).inflate(R.layout.layout_topbar, this, true);
        finder = new ViewFinder(this);

        finder.find(R.id.topbar_left_iv_black).setOnClickListener(v -> {
            ((Activity) (getContext())).finish();
        });

        ViewGroup.LayoutParams lp = finder.find(R.id.item_head).getLayoutParams();
        lp.height = compatPadingTop;
        finder.find(R.id.item_head).setLayoutParams(lp);

        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Topbar);
            title = ta.getString(R.styleable.Topbar_title);
            leftText = ta.getString(R.styleable.Topbar_left_text);

            rightText = ta.getString(R.styleable.Topbar_right_text);
            rightIcon = ta.getDrawable(R.styleable.Topbar_right_icon);
            leftVis = ta.getBoolean(R.styleable.Topbar_left_vis, true);
            bgColor = ta.getColor(R.styleable.Topbar_bg_color, getResources().getColor(R.color.color_ffffff));
            ta.recycle();
        }

        setTitle(title);

//        setBackText(leftText, leftVis);

//        setNext(rightText, rightIcon);

//        setBackColor(bgColor);
//        setViewColor(bgColor);

//        setRightIcon(rightIcon);
    }

    public int getStatusBarHeight() {
        int statusBarHeight = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

    /**
     * 设置标题
     *
     * @param title
     */

    public void setTitle(String title) {
        finder.textView(R.id.topbar_title).setText(title);
    }


    /**
     * 设置左上角的搜索显示  同时返回图标隐藏
     */
    public void setLeftSearchvisible() {
        finder.find(R.id.topbar_left_iv_search).setVisibility(VISIBLE);
        finder.find(R.id.topbar_left_iv_black).setVisibility(GONE);
    }


    /**
     * 设置左上角的返回显示  同时搜索隐藏   一般情况不用设置  为默认状态
     */
    public void setLeftBlackVisible() {
        finder.find(R.id.topbar_left_iv_search).setVisibility(GONE);
        finder.find(R.id.topbar_left_iv_black).setVisibility(VISIBLE);
    }

    /**
     * 设置搜索的点击事件
     *
     * @param listener
     */
    public void setLeftSearchListener(OnClickListener listener) {
        finder.find(R.id.topbar_left_iv_search).setOnClickListener(listener);
    }


    /**
     * 设置右上角图标
     *
     * @param drawable
     */
    public void setRightIv(Drawable drawable) {
        finder.imageView(R.id.topbar_next_img).setImageDrawable(drawable);
    }


    /**
     * 设置右上角图标点击事件
     *
     * @param listener
     */
    public void setrightlistener(OnClickListener listener) {
        finder.find(R.id.topbar_next_img).setOnClickListener(listener);
    }


    public void setrightTvListener(OnClickListener listener) {
        finder.find(R.id.topbar_right_tv).setVisibility(VISIBLE);
        finder.find(R.id.topbar_right_tv).setOnClickListener(listener);
    }


//    private void setBackText(String text, boolean leftVis) {
//        if (leftVis) {
//            finder.find(R.id.topbar_back).setVisibility(View.VISIBLE);
//        } else {
//            finder.find(R.id.topbar_back).setVisibility(View.GONE);
//        }
//        finder.textView(R.id.topbar_back).setText(text);
//    }
//
//
//    private void setRightIcon(Drawable rightIcon) {
//        if (rightIcon == null) {
//            finder.find(R.id.topbar_next).setVisibility(View.GONE);
//        } else {
//            finder.find(R.id.topbar_next).setVisibility(View.VISIBLE);
//        }
//
//        finder.textView(R.id.topbar_next).setCompoundDrawablesWithIntrinsicBounds(rightIcon, null, null, null);
//    }
//
//
//    public void setTitle(String title) {
//        finder.textView(R.id.topbar_title).setText(title);
//    }
//
//
//    public void setBackColor(int color) {
//        finder.find(R.id.topbar_bg).setBackgroundColor(color);
//    }
//
////    public void setViewColor(int color) {
////        finder.find(R.id.top_view).setBackgroundColor(color);
////    }
//
//
//    public void setBackText(String text) {
//        if (TextUtils.isEmpty(text)) {
//            finder.textView(R.id.topbar_back).setText("  ");
//        } else {
//            finder.textView(R.id.topbar_back).setText(text);
//        }
//    }
//
//    private void hideBack() {
//        finder.find(R.id.topbar_back).setVisibility(View.GONE);
//    }
//
//
//    public void setNext(String pic, Drawable rightIcon) {
//        finder.textView(R.id.topbar_next).setText(pic);
//        finder.textView(R.id.topbar_next).setCompoundDrawablesWithIntrinsicBounds(rightIcon, null, null, null);
//    }
//
//    public void setNextListener(OnClickListener listener) {
//        finder.find(R.id.topbar_next).setOnClickListener(listener);
//    }
//
//    public void setBackListener(OnClickListener listener) {
//        finder.find(R.id.topbar_back).setOnClickListener(listener);
//    }
//
//
//    public void setTitleColor(int color) {
//        finder.textView(R.id.topbar_title).setTextColor(color);
//
//    }
//
//    /**
//     * 左侧图片点击事件
//     *
//     * @param listener
//     */
//    public void SetLeftListener(OnClickListener listener) {
//        finder.find(R.id.topbar_left_iv).setOnClickListener(listener);
//    }
//
//
//    public void SetLeftIvVisable() {
//
//        finder.find(R.id.topbar_left_iv).setVisibility(VISIBLE);
//    }
//
//    public void setLeftiv(Drawable drawable) {
//        finder.imageView(R.id.topbar_left_iv).setImageDrawable(drawable);
//    }
//
//    public void setNextColor(int color) {
//        finder.textView(R.id.topbar_next).setTextColor(color);
//    }
//
//    public void setDrawableLeft(Drawable drawable) {
//        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//        finder.textView(R.id.topbar_back).setCompoundDrawables(drawable, null, null, null);
//
//    }
//
//
//    /**
//     * 右上角图标
//     *
//     * @param Drawable
//     */
//    public void SetNextImg(int Drawable) {
//        finder.imageView(R.id.topbar_next_img).setImageResource(Drawable);
//    }
//
//    public void SetNextImgListener(OnClickListener listener) {
//        finder.imageView(R.id.topbar_next_img).setOnClickListener(listener);
//    }
//
//    //隐藏左侧文字加图片
//    public void setLeftTextVis() {
//        finder.find(R.id.topbar_back).setVisibility(GONE);
//    }
//
//    /**
//     * 右侧设置图片
//     *
//     * @param drawable
//     */
//    public void SetNextImgdrawable(Drawable drawable) {
//        finder.imageView(R.id.topbar_next_img).setImageDrawable(drawable);
//    }
}
