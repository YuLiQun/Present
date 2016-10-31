package qunzai.present.home.homeselection;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import qunzai.present.R;
import qunzai.present.been.HSelectionBean;
import qunzai.present.single.VolleySingleSimple;

/**
 * Created by dllo on 16/10/25.
 */
public class HSelectionAdapter extends BaseAdapter {
    Context context;
    //把集合换成HSelectionBean数据类的bean,,生成set方法
    private HSelectionBean arrayList;


    public void setArrayList(HSelectionBean arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    public HSelectionAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        //获取多个item的长度
        return arrayList == null ? 0 : arrayList.getData().getItems().size();
    }

    @Override
    public Object getItem(int position) {
        //这个也是
        return arrayList.getData().getItems().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HomeViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_lv_hselection, parent, false);
            viewHolder = new HomeViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (HomeViewHolder) convertView.getTag();
        }

        //这个是货物相对item的bean类
        HSelectionBean.DataBean.ItemsBean itemsBean = arrayList.getData().getItems().get(position);
        HSelectionBean.DataBean.ItemsBean.ColumnBean columnBean = itemsBean.getColumn();
        int liks = itemsBean.getLikes_count();
        String imgUrl = itemsBean.getCover_image_url();
        String share = itemsBean.getShare_msg();
        String title = itemsBean.getTitle();


        //之前崩是因为columnBean  是空的,,,所以要加一个判断
        if (columnBean != null) {
            String column = columnBean.getTitle();
            viewHolder.tvColumnTitle.setText(column);
            viewHolder.tvColumnTitle.setVisibility(View.VISIBLE);
            viewHolder.tvColumn.setVisibility(View.VISIBLE);
        } else {

            viewHolder.tvColumnTitle.setVisibility(View.GONE);
            viewHolder.tvColumn.setVisibility(View.GONE);
        }


        viewHolder.tvLikescount.setText(String.valueOf(liks));
        viewHolder.tvSharemsg.setText(share);
        viewHolder.tvTitle.setText(title);




        //TextUtils.isEmpty  这是一个判断,,
        // 这个话的意思是,如果,imagUrl != null 并且 ingUrl.lenght != 0
        if (!TextUtils.isEmpty(imgUrl)) {
            VolleySingleSimple.getInstance().getImage(imgUrl, viewHolder.imgImageurl);
            /*让这个图片的位置消失*/
            viewHolder.imgImageurl.setVisibility(View.VISIBLE);
            viewHolder.tvLikescount.setVisibility(View.VISIBLE);
            viewHolder.tvSharemsg.setVisibility(View.VISIBLE);
            viewHolder.tvTitle.setVisibility(View.VISIBLE);
            viewHolder.imgGone.setVisibility(View.VISIBLE);
            viewHolder.llLine.setVisibility(View.VISIBLE);

        } else {
            /*让这个图片的位置死去,,要不然占位置*/
            viewHolder.imgImageurl.setVisibility(View.GONE);
            viewHolder.tvLikescount.setVisibility(View.GONE);
            viewHolder.tvSharemsg.setVisibility(View.GONE);
            viewHolder.imgGone.setVisibility(View.GONE);
            viewHolder.tvTitle.setVisibility(View.GONE);

            viewHolder.llLine.setVisibility(View.GONE);
        }
        //TODO asdfadsf
        /********未完待续*********/


        return convertView;
    }


    class HomeViewHolder {


        private LinearLayout llLine;//线
        private TextView tvColumn;//栏目
        private TextView tvColumnTitle;//栏目下的标题
        private ImageView imgGone;//点赞的❤️的图片
        //        private TextView tvTitle;
        private TextView tvSharemsg;
        private TextView tvLikescount;
        private ImageView imgImageurl;
        private TextView tvTitle;

        public HomeViewHolder(View convertView) {
            imgImageurl = (ImageView) convertView.findViewById(R.id.img_home_selection_imageurl);
            tvTitle = (TextView) convertView.findViewById(R.id.tv_home_selection_title);
            tvSharemsg = (TextView) convertView.findViewById(R.id.tv_home_selection_sharemsg);
            tvLikescount = (TextView) convertView.findViewById(R.id.tv_home_selection_likescount);
            imgGone = (ImageView) convertView.findViewById(R.id.img_home_selection_gone);
            llLine = (LinearLayout) convertView.findViewById(R.id.ll_home_selection_line);
            tvColumn = (TextView) convertView.findViewById(R.id.tv_home_selection_wheel_title_title);
            tvColumnTitle = (TextView) convertView.findViewById(R.id.tv_home_selection_columntitle);

        }
    }
}
