package qunzai.present.sort.raiders.srlr;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import qunzai.present.R;
import qunzai.present.base.CommonViewHolder;
import qunzai.present.been.SRaidersBean;

/**
 * Created by dllo on 16/11/2.
 */
public class SRaidersLeftAdapter  extends BaseAdapter{

    private SRaidersBean sRaidersBean;


    public void setsRaidersBean(SRaidersBean sRaidersBean) {
        this.sRaidersBean = sRaidersBean;
    }

    //    ArrayList<String> arrayList;
    @Override
    public int getCount() {
        return sRaidersBean.getData().getCategories().size();
    }

    @Override
    public Object getItem(int position) {
        return sRaidersBean.getData().getCategories().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(convertView,parent, R.layout.item_slv_left);
        SRaidersBean.DataBean.CategoriesBean categoriesBean = sRaidersBean.getData().getCategories().get(position);
        String name = categoriesBean.getName();

        viewHolder.setText(R.id.tv_sort_raiders_left,name);
        return viewHolder.getItemView();
    }
}
