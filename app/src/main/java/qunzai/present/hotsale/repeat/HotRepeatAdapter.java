package qunzai.present.hotsale.repeat;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import qunzai.present.R;
import qunzai.present.base.BaseFragment;
import qunzai.present.been.HotSaleRepeatBean;
import qunzai.present.single.VolleySingleSimple;

/**
 * Created by dllo on 16/10/25.
 */
public class HotRepeatAdapter extends RecyclerView.Adapter<HotRepeatAdapter.MyViewHolder> {
    private Context context;
    private HotSaleRepeatBean repeatBean;

    public HotRepeatAdapter(Context context) {
        this.context = context;
    }


    public void setRepeatBean(HotSaleRepeatBean repeatBean) {
        this.repeatBean = repeatBean;
    }

    @Override
    public HotRepeatAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_data,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HotRepeatAdapter.MyViewHolder holder, int position) {

        HotSaleRepeatBean.DataBean.ItemsBean itemsBean = repeatBean.getData().getItems().get(position);

        String imgUrl = itemsBean.getCover_image_url();
        String description  = itemsBean.getShort_description();
        String name  = itemsBean.getName();
        String price = itemsBean.getPrice();

        holder.tvName.setText(name);
        holder.tvDescription.setText(description);
        holder.tvPrice.setText(price);
        holder.tvTop.setText("top" +( position + 1));
        VolleySingleSimple.getInstance().getImage(imgUrl,holder.imgCoverimage);


//        holder.rlBack.setBackgroundResource(R.drawable.hotsale_coverimg_back);

        if (position < 3){
//            holder.tvTop.setBackgroundColor(0xFFFF3C58);
            holder.tvTop.setBackgroundResource(R.drawable.hotsale_top_back);
            holder.tvTop.setTextColor(Color.WHITE);
        }
        else {
//            holder.tvTop.setBackgroundColor(0xFFFFE1E3);
            holder.tvTop.setBackgroundResource(R.drawable.hotsale_top_background);
            holder.tvTop.setTextColor(0xFFFF3C58);
        }

    }

    @Override
    public int getItemCount() {
        return repeatBean.getData().getItems().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout rlBack;
        private ImageView imgCoverimage;
        private TextView tvTop;
        private TextView tvDescription;
        private TextView tvName;
        private TextView tvPrice;

        public MyViewHolder(View itemView) {
            super(itemView);

            imgCoverimage = (ImageView) itemView.findViewById(R.id.img_hot_repeat_coverimage);
            tvTop = (TextView) itemView.findViewById(R.id.tv_hot_repeat_repeat_top);
            tvDescription = (TextView) itemView.findViewById(R.id.tv_hot_repeat_description);
            tvName = (TextView) itemView.findViewById(R.id.tv_hot_repeat_name);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_hot_repeat_price);
//            rlBack = (RelativeLayout) itemView.findViewById(R.id.rl_hot_repeat_background);



        }
    }
}
