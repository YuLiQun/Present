package qunzai.present.sort.raiders.srlr;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;

import java.util.ArrayList;

import qunzai.present.R;
import qunzai.present.base.CommonViewHolder;
import qunzai.present.been.SRaidersBean;
import qunzai.present.sort.raiders.srlr.rbody.SRRightBodyAdapter;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by dllo on 16/11/2.
 */
public class SRaidersRightAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    private SRaidersBean sRaidersBean;


    public void setsRaidersBean(SRaidersBean sRaidersBean) {
        this.sRaidersBean = sRaidersBean;
    }


    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(convertView,parent,R.layout.item_slv_right_head);
        SRaidersBean.DataBean.CategoriesBean categoriesBean = sRaidersBean.getData().getCategories().get(position);
        String name = categoriesBean.getName();
        viewHolder.setText(R.id.tv_sort_raiders_right,name);
        return viewHolder.getItemView();
    }

    @Override
    public long getHeaderId(int position) {
        return position;
    }


    @Override
    public int getCount() {
//        return headList.size();
        return sRaidersBean.getData().getCategories().size();
    }

    @Override
    public Object getItem(int position) {
//        SRaidersBean.DataBean.CategoriesBean categoriesBean
//                = sRaidersBean.getData().getCategories().get(position);
//        for (int i = 0; i < ; i++) {
//           SRaidersBean.DataBean.CategoriesBean.SubcategoriesBean  subcategoriesBean ;
//
//        }
////        return bodyList.get(position);
        return sRaidersBean.getData().getCategories().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //TODO 布局不好,,未完待续
        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(convertView, parent, R.layout.item_slv_right_body);

        //注意这里的写法viewHolder.getItemView().
        RecyclerView rv = (RecyclerView) viewHolder.getItemView().findViewById(R.id.rv_sort_raiders_right);

        SRaidersBean.DataBean.CategoriesBean categoriesBean = sRaidersBean.getData().getCategories().get(position);

        ArrayList<SRaidersBean.DataBean.CategoriesBean.SubcategoriesBean> arrayList = new ArrayList<>();
        for (int i = 0; i < categoriesBean.getSubcategories().size(); i++) {
            SRaidersBean.DataBean.CategoriesBean.SubcategoriesBean subcategoriesBean =
                    categoriesBean.getSubcategories().get(i);
            arrayList.add(subcategoriesBean);
        }
        SRRightBodyAdapter adapter = new SRRightBodyAdapter();
        adapter.setArrayList(arrayList);
        rv.setAdapter(adapter);
        GridLayoutManager manager = new GridLayoutManager(parent.getContext(), 3, GridLayoutManager.VERTICAL, false);
        rv.setLayoutManager(manager);



        return viewHolder.getItemView();
    }
}
