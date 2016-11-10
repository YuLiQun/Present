package qunzai.present.main.find;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import qunzai.present.R;
import qunzai.present.base.CommonViewHolder;
import qunzai.present.been.TestBean;

import static qunzai.present.R.id.*;

/**
 * Created by dllo on 16/11/4.
 */
public class FindAdapter extends BaseAdapter {
    private ArrayList<TestBean> arrayList;

    public void setArrayList(ArrayList<TestBean> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
        Log.d("sss", "arrayList:" + arrayList);
    }

    @Override
    public int getCount() {
        return arrayList== null ? 0 :arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(convertView,parent, R.layout.item_find);
        viewHolder.setText(tv_find_item_data,arrayList.get(position).getData());
        return viewHolder.getItemView();
    }
}
