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
    private static final int TYPE = 1;
    private static final int TYPE_END = 2;


    public void setsOneHeaderBean(SOneHeaderBean sOneHeaderBean) {
        this.sOneHeaderBean = sOneHeaderBean;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE://当ttype = 1 的时候的布局
                CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(parent,R.layout.item_rv_sort_one);
                return viewHolder;
            case TYPE_END://当type = 2 的时候的布局
                CommonViewHolder viewHolderEnd = CommonViewHolder.getViewHolder(parent,R.layout.item_rv_sort_end);
                return viewHolderEnd;
        }

        return null;
    }


    @Override
    public int getItemViewType(int position) {
        // 判断,,如果小于11,,就返回1,,否则,,返回2
        if (position < 11) return TYPE;
        else return TYPE_END;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        SOneHeaderBean.DataBean.ColumnsBean columnsBean = sOneHeaderBean.getData().getColumns().get(position);
        int viewType = getItemViewType(position);
        if (viewType == TYPE) {
            //当type = 1 的时候,,应该显示的东西
            String url = columnsBean.getBanner_image_url();
            String title = columnsBean.getTitle();
            String author = columnsBean.getAuthor();
            holder.setText(R.id.tv_sort_one_header_title, title);
            holder.setText(R.id.tv_sort_one_header_author, author);
            VolleySingleSimple.getInstance().getImage(url, (ImageView) holder.getView(R.id.img_sort_one_header));
        }

//        }else {
//            holder.setText(R.id.)
//        }







    }


    @Override
    public int getItemCount() {
        //返回的item个数,,,因为要返回12 个,,所以这只能写12
//        return sOneHeaderBean == null ? 0 :sOneHeaderBean.getData().getColumns().size();
        return 12;
    }





}
