package jzl.remotecraft.sensor.ui.view;

import android.view.View;

/**
 * Created by jzl on 2016/6/9.
 */
public interface BaseView {
    void showLoading(String msg);

    void hideLoading();

    void showError(String msg, View.OnClickListener onClickListener);

    void showEmpty(String msg, View.OnClickListener onClickListener);

    void showEmpty(String msg, View.OnClickListener onClickListener,int imageId);

    void showNetError(View.OnClickListener onClickListener);
}
