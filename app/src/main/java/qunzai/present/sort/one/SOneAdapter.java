package qunzai.present.sort.one;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.google.gson.Gson;

import qunzai.present.R;
import qunzai.present.base.CommonViewHolder;
import qunzai.present.been.SOneBean;
import qunzai.present.single.VolleySingleSimple;

/**
 * Created by dllo on 16/11/1.
 */
public class SOneAdapter  extends BaseAdapter{

    private SOneBean sOneBean;

    public void setsOneBean(SOneBean sOneBean) {
        this.sOneBean = sOneBean;
    }



    @Override
    public int getCount() {
        return sOneBean.getData().getChannel_groups().size();
    }

    @Override
    public Object getItem(int position) {
        return sOneBean.getData().getChannel_groups().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(convertView,parent,R.layout.item_lv_sort_one);

        SOneBean.DataBean.ChannelGroupsBean  channelGroupsBean = sOneBean.getData().getChannel_groups().get(position);
        SOneBean.DataBean.ChannelGroupsBean.ChannelsBean  bean = channelGroupsBean.getChannels().get(position);
        String tvCategory = channelGroupsBean.getName();
        Log.d("SOneAdapter", tvCategory);


        if (channelGroupsBean.getChannels().size() > 6){
            viewHolder.getView(R.id.tv_sort_one_all).setVisibility(View.VISIBLE);
        }else {
            viewHolder.getView(R.id.tv_sort_one_all).setVisibility(View.GONE);
        }

        viewHolder.setText(R.id.tv_sort_one_category,tvCategory);
        int ids[] = {R.id.img_sort_one_one,R.id.img_sort_one_two,R.id.img_sort_one_three,R.id.img_sort_one_four,
                R.id.img_sort_one_five,R.id.img_sort_one_six};

        for (int i = 0; i < ids.length; i++) {
            String img = channelGroupsBean.getChannels().get(i).getCover_image_url();
            VolleySingleSimple.getInstance().getImage(img, (ImageView) viewHolder.getView(ids[i]));
        }

        return viewHolder.getItemView();
    }
}
