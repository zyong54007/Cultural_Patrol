package com.zhjy.cultural.services.patrol.binding;

import android.databinding.BindingAdapter;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

public class BindingAdapters {

    @BindingAdapter("visibleGone")
    public static void showHide(View view, boolean show) {
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("imageUrl")
    public static void bindImage(ImageView image, String url) {
        Picasso.with(image.getContext()).load(url).into(image);
    }

    @BindingAdapter("filePath")
    public static void bindImageFile(ImageView image, File filePath) {
        Picasso.with(image.getContext()).load(filePath).into(image);
    }

    @BindingAdapter("html")
    public static void html(WebView webView, String html) {
        webView.loadDataWithBaseURL(null, html, "text/html", "utf8", null);
    }
}