package com.zhjy.cultural.services.patrol.ui.setup.notice.list;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

//import com.zhjy.cultural.services.patrol.biz.pojo.bean.RecordBean;
//import com.zhjy.cultural.services.patrol.databinding.ActivityMyNoticeListItemBinding;
//import com.zhjy.cultural.services.patrol.network.Contant;
//import com.zhjy.cultural.services.patrol.ui.inspection.punchclock.PunchClockActivity;

/**
 * Created by jialg on 2018/1/31.
 */

public class NoticeListItem {

}
//        extends RecyclerView.ViewHolder implements View.OnClickListener {
//
//    ActivityMyNoticeListItemBinding binding;
//
//    NoticeListItem(View itemView) {
//        super(itemView);
//        binding = DataBindingUtil.bind(itemView);
//        itemView.setOnClickListener(this);
//    }
//
//    void bind(RecordBean bean) {
//        itemView.setTag(bean);
//        binding.setBean(bean);
//        //消除数据刷新时闪动问题
//        binding.executePendingBindings();
//    }
//
//    @Override
//    public void onClick(View v) {
//        RecordBean bean = (RecordBean) v.getTag();
//        Intent intent = new Intent(v.getContext(), PunchClockActivity.class);
//        intent.putExtra("wwId", bean.getWw().getId());
//        intent.putExtra("recordId", bean.getId());
//        intent.putExtra(Contant.POINT, bean.getWw().getPoint());
//        v.getContext().startActivity(intent);
//    }
//
//}
