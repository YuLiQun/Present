package qunzai.present.main.find;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import qunzai.present.R;
import qunzai.present.base.CommonViewHolder;

/**
 * Created by dllo on 16/11/4.
 */
public class FindAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(convertView,parent, R.layout.item_find);

        //TODO 这里写数据
        return viewHolder.getItemView();
    }
}
