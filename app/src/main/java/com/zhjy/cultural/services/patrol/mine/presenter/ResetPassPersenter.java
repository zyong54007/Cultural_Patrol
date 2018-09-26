package com.zhjy.cultural.services.patrol.mine.presenter;

import com.zhjy.cultural.services.patrol.mvp.ActPresenter;
import com.zhjy.cultural.services.patrol.mvp.GEMUI;
import com.zhjy.cultural.services.patrol.mvp.MVPActivity;

/**
 * 修改密码
 */
public class ResetPassPersenter extends ActPresenter<ResetPassPersenter.ResetPAssUI> {

    public interface ResetPAssUI extends GEMUI {

    }

    @Override
    public void onUIReady(MVPActivity activity, ResetPAssUI ui) {
        super.onUIReady(activity, ui);
    }
}
