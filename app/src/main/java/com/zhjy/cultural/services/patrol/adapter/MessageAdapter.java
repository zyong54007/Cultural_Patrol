package com.zhjy.cultural.services.patrol.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.network.NoticeList;

import java.util.Date;
import java.util.List;

/**
 * 我的消息适配器
 */
public class MessageAdapter extends BaseQuickAdapter<NoticeList.Datas, BaseViewHolder> {
    public MessageAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NoticeList.Datas item) {
        String createTime = item.getNotice().getCreateTime();
        helper.setText(R.id.item_notice_time, createTime);
        helper.setText(R.id.message_title, item.getNotice().getTheme());
        helper.setText(R.id.message_info, item.getNotice().getContent());
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
