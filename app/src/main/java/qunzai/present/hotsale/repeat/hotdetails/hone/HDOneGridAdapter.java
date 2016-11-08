package qunzai.present.hotsale.repeat.hotdetails.hone;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import qunzai.present.R;
import qunzai.present.base.CommonViewHolder;
import qunzai.present.been.HDOneGridBean;
import qunzai.present.internet.VolleySingleSimple;

/**
 * Created by dllo on 16/11/7.
 */
public class HDOneGridAdapter extends RecyclerView.Adapter<CommonViewHolder> {

    private HDOneGridBean hhdOneGridBean;

    public void setHdOneGridBean(HDOneGridBean hdOneGridBean) {
        this.hhdOneGridBean = hdOneGridBean;
        notifyDataSetChanged();
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_rv_hot_done_grid);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        HDOneGridBean.DataBean.RecommendItemsBean recommendItemsBean = hhdOneGridBean.getData().getRecommend_items().get(position);

        String name = recommendItemsBean.getName();
        String price = recommendItemsBean.getPrice();
        String url = recommendItemsBean.getCover_image_url();


        holder.setText(R.id.tv_hot_done_grid_description, name);
        holder.setText(R.id.tv_hot_done_grid_price, price);
        VolleySingleSimple.getInstance().getImage(url, (ImageView) holder.getView(R.id.img_hot_done_img));

    }

    @Override
    public int getItemCount() {
        return hhdOneGridBean.getData().getRecommend_items().size();
    }
}
