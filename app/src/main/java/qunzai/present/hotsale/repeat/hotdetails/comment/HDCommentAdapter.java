package qunzai.present.hotsale.repeat.hotdetails.comment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.text.SimpleDateFormat;

import qunzai.present.R;
import qunzai.present.base.CommonViewHolder;
import qunzai.present.been.HDCommentBean;
import qunzai.present.internet.VolleySingleSimple;
import qunzai.present.other.CircleDrawable;

/**
 * Created by dllo on 16/11/8.
 */
public class HDCommentAdapter extends BaseAdapter{
    private HDCommentBean hdCommentBean;

    public HDCommentAdapter(HDCommentBean hdCommentBean) {
        this.hdCommentBean = hdCommentBean;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return hdCommentBean.getData().getComments().size();
    }

    @Override
    public Object getItem(int position) {
        return hdCommentBean.getData().getComments().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HDCommentBean.DataBean.CommentsBean commentsBean = hdCommentBean.getData().getComments().get(position);
        String content  = commentsBean.getContent();
        String imgUrl = commentsBean.getUser().getAvatar_url();
        String name = commentsBean.getUser().getNickname();
        int timeId = commentsBean.getCreated_at();

        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(convertView,parent, R.layout.item_lv_hd_comment);
//        Bitmap bitmap = BitmapFactory.
//        CircleDrawable circleDrawable = new CircleDrawable()
        viewHolder.setCircleImg(R.id.img_hot_dcomment_avatar,imgUrl);
//        VolleySingleSimple.getInstance().getImage(imgUrl, (ImageView) viewHolder.getView(R.id.img_hot_dcomment_avatar));
        viewHolder.setText(R.id.tv_hot_dcomment_content,content);
        viewHolder.setText(R.id.tv_hot_dcomment_nickname,name);
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日 hh:mm");
        String time = format.format(Long.valueOf(timeId));
        viewHolder.setText(R.id.tv_hot_dcomment_created,time);
        return viewHolder.getItemView();
    }
}
