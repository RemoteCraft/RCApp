package jzl.remotecraft.sensor.ui.view.personal;

import java.util.List;

import jzl.remotecraft.sensor.model.entity.PersonalHistoryListItem;
import jzl.remotecraft.sensor.ui.view.BaseView;

/**
 * Created by jzl on 2016/6/9.
 */
public interface HistoryListView extends BaseView {

    public void refresh(List<PersonalHistoryListItem> data);
    public void loadMore(List<PersonalHistoryListItem> data);
}
