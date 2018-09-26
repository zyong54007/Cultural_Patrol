package com.zhjy.cultural.services.patrol.mvp;


import com.zhjy.cultural.services.patrol.util.ViewFinder;

/**
 * MVP - View interface
 * <p>
 * Created by wpy on 2017/7/22.
 */

public interface GEMUI {

    boolean isAlive();

    /**
     * whether is resumed or visible to user
     *
     * @return
     */
    boolean isActive();


    /**
     * Return layout resource for activity or fragment
     *
     * @return
     */
    int getContentLayout();

    /**
     * Provide a viewfinder to simplify find a View in Res
     *
     * @return
     */
    ViewFinder finder();

    void showProgress(String title, String content);

    void dismissProgress();


}
