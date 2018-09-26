package com.zhjy.cultural.services.patrol.network;

import android.text.TextUtils;
import android.util.Log;

import com.zhjy.cultural.services.patrol.R;
import com.zhjy.cultural.services.patrol.network.exception.APIStatusErrorException;
import com.zhjy.cultural.services.patrol.util.DebugLog;
import com.zhjy.cultural.services.patrol.util.ToastUtil;

import rx.functions.Action0;
import rx.functions.Action1;

public class EasySubscriber <T> extends BaseSubscriber<T> {

    private Action0 before;

    private Action1<T> next;

    private Action1<APIStatusErrorException> failed;

    private Action1<Throwable> error;

    private Action0 after;


    protected EasySubscriber() {
    }

    protected EasySubscriber(Action1<T> next) {
        this.next = next;
    }

    protected EasySubscriber(Action1<T> next, Action1<APIStatusErrorException> failed, Action1<Throwable> error) {
        this.next = next;
        this.failed = failed;
        this.error = error;
    }

    protected EasySubscriber(Action0 before, Action1<T> next, Action1<APIStatusErrorException> failed, Action1<Throwable> error) {
        this.before = before;
        this.next = next;
        this.failed = failed;
        this.error = error;
    }

    protected EasySubscriber(Action0 before, Action1<T> next, Action1<APIStatusErrorException> failed,
                             Action1<Throwable> error, Action0 after) {
        this.before = before;
        this.next = next;
        this.failed = failed;
        this.error = error;
        this.after = after;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (before != null) {
            before.call();
        }
    }

    @Override
    public final void onError(Throwable e) {
        DebugLog.e(e);
        if (e instanceof APIStatusErrorException)
            onFailed((APIStatusErrorException) e);
        else {
            onError2(e);
        }

        super.onError(e);
    }


    @Override
    public void onNext(T t) {
        super.onNext(t);
        if (next != null) {
            next.call(t);
        }
    }

    public void onFailed(APIStatusErrorException e) {
        if (failed != null)
            failed.call(e);

        if (!TextUtils.isEmpty(e.getErrMsg()))
            ToastUtil.toastLong(e.getErrMsg());
        else
            // ToastUtil.toastLong(R.string.server_error);
            ToastUtil.showToast(R.string.server_error);
    }

    public void onError2(Throwable e) {
        if (error != null)
            error.call(e);

        // ToastUtil.toastLong(R.string.server_error);

        ToastUtil.showToast(R.string.server_error);
    }

    @Override
    public void onAfter() {
        super.onAfter();
        if (after != null)
            after.call();
    }

    public static <B> EasySubscriber<B> create(Action1<B> onNext) {
        return new EasySubscriber<B>(onNext);
    }

    public static <B> EasySubscriber<B> create(Action1<B> next, Action1<APIStatusErrorException> failed,
                                               Action1<Throwable> error) {
        return new EasySubscriber<B>(next, failed, error);
    }

    public static <B> EasySubscriber<B> create(Action0 before, Action1<B> next, Action1<APIStatusErrorException> failed,
                                               Action1<Throwable> error) {
        return new EasySubscriber<B>(before, next, failed, error);
    }

    public static <B> EasySubscriber<B> create(Action0 before, Action1<B> next, Action1<APIStatusErrorException> failed,
                                               Action1<Throwable> error, Action0 after) {
        return new EasySubscriber<B>(before, next, failed, error, after);
    }


}
