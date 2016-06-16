package jzl.remotecraft.sensor.ui.presenter.personal;

import android.content.Context;
import android.view.View;

import java.util.List;

import jzl.remotecraft.sensor.ui.presenter.BasePresenter;
import jzl.remotecraft.sensor.ui.view.personal.HistoryListView;

/**
 * Created by jzl on 2016/6/9.
 */
public class HistoryPresenter extends BasePresenter<HistoryListView> {

    private static final String tag = "growthhacker";

    public static final int LIMIT = 10;


    public HistoryPresenter(Context context) {

    }

    @Override
    public void attachView(HistoryListView view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();

    }


    public void loadList(final int page) {
        checkViewAttached();

        String date = null;//增量更新(Incremental Update)

        int offset = (page - 1) * LIMIT;


        //1.Prepare data from webservice
        //2.Call getMvpView().refresh(data)


    }


    //DataBuffer
    private void saveDataToDBorSP() {

    }


}
