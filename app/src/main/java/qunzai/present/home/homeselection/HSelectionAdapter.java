package qunzai.present.home.homeselection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;

import qunzai.present.R;
import qunzai.present.gson.GsonRequsest;
import qunzai.present.hotsale.repeat.HotRepeatAdapter;

/**
 * Created by dllo on 16/10/25.
 */
public class HSelectionAdapter extends BaseAdapter{
    Context context;
    ArrayList<HSelectionBean> arrayList;
    private String imgUrl;

    public void setArrayList(ArrayList<HSelectionBean> arrayList) {
        this.arrayList = arrayList;
    }

    public HSelectionAdapter(Context context) {
        this.context = context;
    }



    @Override
    public int getCount() {
        return arrayList == null ? 0 : arrayList.size() ;
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
        HomeViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_lv_hselection,parent,false);
            viewHolder = new HomeViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (HomeViewHolder) convertView.getTag();
        }
        viewHolder.tvLikescount.setText(arrayList.get(position).getData().getItems().get(position).getCover_image_url());
        viewHolder.tvSharemsg.setText(arrayList.get(position).getData().getItems().get(position).getShare_msg());
        viewHolder.tvTitle.setText(arrayList.get(position).getData().getItems().get(position).getTitle());

        imgUrl = arrayList.get(position).getData().getItems().get(position).getCover_image_url();
        /********未完待续*********/
        initImageView();

        return null;
    }

    private void initImageView() {
        GsonRequsest<HSelectionBean> requsest = new GsonRequsest<HSelectionBean>(HSelectionBean.class,
                imgUrl, new Response.Listener<HSelectionBean>() {
            @Override
            public void onResponse(HSelectionBean response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    class HomeViewHolder{

        private final ImageView imgImageurl;
        private final TextView tvTitle;
        private final TextView tvSharemsg;
        private final TextView tvLikescount;

        public HomeViewHolder(View convertView) {
            imgImageurl = (ImageView) convertView.findViewById(R.id.img_home_selection_imageurl);
            tvTitle = (TextView) convertView.findViewById(R.id.tv_home_selection_title);
            tvSharemsg = (TextView) convertView.findViewById(R.id.tv_home_selection_sharemsg);
            tvLikescount = (TextView) convertView.findViewById(R.id.tv_home_selection_likescount);

        }
    }
}
