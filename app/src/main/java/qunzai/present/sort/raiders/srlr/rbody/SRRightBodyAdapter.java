package qunzai.present.sort.raiders.srlr.rbody;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import qunzai.present.R;
import qunzai.present.base.CommonViewHolder;
import qunzai.present.been.SRaidersBean;
import qunzai.present.single.VolleySingleSimple;

/**
 * Created by dllo on 16/11/2.
 */
public class SRRightBodyAdapter extends RecyclerView.Adapter<CommonViewHolder> {

    ArrayList<SRaidersBean.DataBean.CategoriesBean.SubcategoriesBean> arrayList;


    public void setArrayList(ArrayList<SRaidersBean.DataBean.CategoriesBean.SubcategoriesBean> arrayList) {
        this.arrayList = arrayList;
    }


    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_rv_right_body);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {

        for (int i = 0; i < arrayList.size(); i++) {
            SRaidersBean.DataBean.CategoriesBean.SubcategoriesBean sub = arrayList.get(i);

            String imgUrl = sub.getIcon_url();
            String name  = sub.getName();
            holder.setText(R.id.tv_sort_raiders_right_body_name,name);
            VolleySingleSimple.getInstance().getImage(imgUrl, (ImageView) holder.getView(R.id.img_sort_raiders_right_body_icon));
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
