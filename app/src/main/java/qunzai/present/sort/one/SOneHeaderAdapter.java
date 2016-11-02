package qunzai.present.sort.one;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import qunzai.present.R;
import qunzai.present.base.CommonViewHolder;
import qunzai.present.been.SOneHeaderBean;
import qunzai.present.single.VolleySingleSimple;


/**
 * Created by dllo on 16/11/1.
 */
public class SOneHeaderAdapter extends RecyclerView.Adapter<CommonViewHolder>{

    private SOneHeaderBean sOneHeaderBean;


    public void setsOneHeaderBean(SOneHeaderBean sOneHeaderBean) {
        this.sOneHeaderBean = sOneHeaderBean;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(parent,R.layout.item_rv_sort_one);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
//        SOneHeaderBean.DataBean.ColumnsBean columnsBean = sOneHeaderBean.getData().getColumns().get(position);
//        Log.d("ddd", "columnsBean:" + columnsBean);
//        String url = columnsBean.getBanner_image_url();
        Log.d("ddd", "position:" + position);
        String url = sOneHeaderBean.getData().getColumns().get(position).getCover_image_url();
        Log.d("ddd", url);
        VolleySingleSimple.getInstance().getImage(url, (ImageView) holder.getView(R.id.img_sort_one_header));

    }


    @Override
    public int getItemCount() {
        return sOneHeaderBean == null ? 0 :sOneHeaderBean.getData().getColumns().size();
    }





}
