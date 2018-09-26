package com.zhjy.cultural.services.patrol;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhjy.cultural.services.patrol.biz.pojo.bean.RecordBean;
import com.zhjy.cultural.services.patrol.network.NoticeList;

import java.util.List;

public class MoreMatterAdapter extends BaseQuickAdapter<RecordBean, BaseViewHolder> {
    public MoreMatterAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecordBean item) {
        helper.setImageResource(R.id.item_notice_iv, R.mipmap.xj_no_wc);
        helper.setText(R.id.item_notice_time, item.getCreateTime());
        helper.setText(R.id.message_info, item.getWw().getTitle());


//        String createTime = item.getNotice().getCreateTime();
//        helper.setText(R.id.item_notice_time, createTime);
//        helper.setText(R.id.message_title, item.getNotice().getTheme());
//        helper.setText(R.id.message_info, item.getNotice().getContent());
//        helper.addOnClickListener(R.id.item_child);
//        ImageView view = helper.getView(R.id.iv_item);
//        int layoutPosition = helper.getLayoutPosition();
        //        helper.setText(R.id.text, item.getTitle());
//        helper.setImageResource(R.id.icon, item.getImageResource());
        // 加载网络图片
//        Glide.with(mContext).load(item.getUserAvatar()).crossFade().into((ImageView) helper.getView(R.id.iv));
//        helper.setText(R.id.item_tv, item.getMsg());
    }
}
