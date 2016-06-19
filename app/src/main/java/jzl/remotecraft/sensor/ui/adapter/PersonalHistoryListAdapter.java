package jzl.remotecraft.sensor.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import jzl.remotecraft.sensor.R;
import jzl.remotecraft.sensor.model.entity.PersonalHistoryListItem;

/**
 * Created by jzl on 2016/6/10.
 */
public class PersonalHistoryListAdapter extends RecyclerView.Adapter<PersonalHistoryListAdapter.ViewHolder> {
    public ArrayList<PersonalHistoryListItem> datas = null;

    Context context;

    public PersonalHistoryListAdapter(Context context, ArrayList<PersonalHistoryListItem> datas) {
        this.context = context;
        this.datas = datas;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.personal_history_list_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        viewHolder.title_text.setOnClickListener(new View.OnClickListener() {
            //点击list中的item触发事件
            @Override
            public void onClick(View view) {

            }
        });

        PersonalHistoryListItem bean = datas.get(position);
        viewHolder.tag_text.setText(bean.getTag());
        viewHolder.title_text.setText(bean.getTitle());
        viewHolder.little_button_text.setText(bean.getLittleContent());

    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tag_text;
        public TextView title_text;
        public TextView little_button_text;


        public ViewHolder(View view) {
            super(view);
            tag_text = (TextView) view.findViewById(R.id.personal_history_list_item_tag_text);
            title_text = (TextView) view.findViewById(R.id.personal_history_list_item_title_text);
            little_button_text = (TextView) view.findViewById(R.id.personal_history_list_item_little_button_content);

        }
    }
}