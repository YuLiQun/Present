package qunzai.present.hotsale.repeat.hotdetails.hone;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import qunzai.present.R;
import qunzai.present.base.CommonViewHolder;

/**
 * Created by dllo on 16/11/7.
 */
public class HotDOneAdapter extends RecyclerView.Adapter<CommonViewHolder>{

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_rv_hot_done_grid);
        return null;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
