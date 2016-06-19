package jzl.remotecraft.sensor.ui.fragment.personal;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import jzl.remotecraft.sensor.R;
import jzl.remotecraft.sensor.model.entity.PersonalHistoryListItem;
import jzl.remotecraft.sensor.ui.adapter.PersonalHistoryListAdapter;
import jzl.remotecraft.sensor.ui.fragment.BaseFragment;
import jzl.remotecraft.sensor.ui.presenter.personal.HistoryPresenter;
import jzl.remotecraft.sensor.ui.view.personal.HistoryListView;
import jzl.remotecraft.sensor.util.common.Common;
import jzl.remotecraft.sensor.util.common.widget.view.hao.HaoRecyclerView;
import jzl.remotecraft.sensor.util.common.widget.view.hao.LoadMoreListener;


import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * interface.
 */
public class HistoryFragment extends BaseFragment implements HistoryListView{

    private FrameLayout history_content = null;
    private SwipeRefreshLayout history_swiperefresh = null;
    private HaoRecyclerView history_recycleview = null;

    public String TITLE = "MyHistory";

    private HistoryPresenter historyPresenter;
    private PersonalHistoryListAdapter historyListAdapter;
    private ArrayList<PersonalHistoryListItem> listData = new ArrayList<>();
    private int page = 1;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public HistoryFragment() {
    }

    @Override
    protected void init(){
        historyListAdapter = new PersonalHistoryListAdapter(mContext, listData);
        history_recycleview.setAdapter(historyListAdapter);
        history_swiperefresh.setColorSchemeResources(R.color.textBlueDark, R.color.textBlueDark, R.color.textBlueDark,
                R.color.textBlueDark);

        history_swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                historyPresenter.loadList(page);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        history_recycleview.setLayoutManager(layoutManager);

        if(fab != null){

        }

        //设置自定义加载中和到底了效果
        TextView view_loading = new TextView(mContext);
        view_loading.setText("加载中...");

        history_recycleview.setFootLoadingView(view_loading);

        TextView view_end = new TextView(mContext);
        view_end.setText("已经到底啦~");
        history_recycleview.setFootEndView(view_end);

        history_recycleview.setLoadMoreListener(new LoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                historyPresenter.loadList(page);
            }
        });


        historyPresenter = new HistoryPresenter(mContext);
        historyPresenter.attachView(this);
        //初次加载
        page = 1;
        historyPresenter.loadList(page);
        showLoading("加载中...");
    }
    protected View getLoadingTargetView(){return history_swiperefresh;}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_history, container, false);

        history_content = (FrameLayout)view.findViewById(R.id.personal_history_content);
        history_swiperefresh = (SwipeRefreshLayout)view.findViewById(R.id.personal_history_swiperefresh);
        history_recycleview = (HaoRecyclerView)view.findViewById(R.id.personal_history_recycleview);
        return view;
    }

    @Override
    public void refresh(List<PersonalHistoryListItem> data) {
//        hideLoading();
        //注意此处
        history_recycleview.refreshComplete();
        history_recycleview.loadMoreComplete();
        history_swiperefresh.setRefreshing(false);
        listData.clear();
        listData.addAll(data);
        historyListAdapter.notifyDataSetChanged();

//        ACache aCache = ACache.get(this.getActivity().getApplicationContext());
//        ArticleListEntity articleListEntity = new ArticleListEntity();
//        articleListEntity.data = data;
//        aCache.put("home_list", GsonUtil.toJson(articleListEntity));

    }

    @Override
    public void loadMore(List<PersonalHistoryListItem> data) {

        if (Common.isEmpty(data)) {
            history_recycleview.loadMoreEnd();
        } else {
            listData.addAll(data);
            historyListAdapter.notifyDataSetChanged();
            history_recycleview.loadMoreComplete();
        }

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
