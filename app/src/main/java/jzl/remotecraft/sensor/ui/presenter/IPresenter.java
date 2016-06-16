package jzl.remotecraft.sensor.ui.presenter;

/**
 * Created by jzl on 2016/6/9.
 */

import jzl.remotecraft.sensor.ui.view.BaseView;

/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the BaseView type that wants to be attached with.
 */
public interface IPresenter<V extends BaseView> {
    void attachView(V view);
    void detachView();
}
