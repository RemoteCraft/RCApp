package jzl.remotecraft.sensor.ui.view.personal;

import java.util.List;

import jzl.remotecraft.sensor.model.entity.PersonalHistoryList;
import jzl.remotecraft.sensor.ui.view.BaseView;

/**
 * Created by jzl on 2016/6/9.
 */
public interface HistoryListView extends BaseView {

    public void refresh(List<PersonalHistoryList> data);
    public void loadMore(List<PersonalHistoryList> data);
}
