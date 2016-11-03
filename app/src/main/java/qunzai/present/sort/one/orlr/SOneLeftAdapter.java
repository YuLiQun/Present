package qunzai.present.sort.one.orlr;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import qunzai.present.R;
import qunzai.present.base.CommonViewHolder;
import qunzai.present.been.SRaidersBean;

/**
 * Created by dllo on 16/11/2.
 */
public class SOneLeftAdapter extends BaseAdapter{

    private SRaidersBean sRaidersBean;

    /*选中这个position*/
    private int selectedPos = 0;

    /**自定义一个方法*/
    public void setSelectdPos(int selectedPos) {
        this.selectedPos = selectedPos;
        notifyDataSetChanged();
    }

    public void setsRaidersBean(SRaidersBean sRaidersBean) {
        this.sRaidersBean = sRaidersBean;
        notifyDataSetChanged();
    }

    //    ArrayList<String> arrayList;
    @Override
    public int getCount() {
        return sRaidersBean == null ? 0 : sRaidersBean.getData().getCategories().size();
    }

    @Override
    public Object getItem(int position) {
        return sRaidersBean.getData().getCategories().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(convertView,parent, R.layout.item_slv_left);
        SRaidersBean.DataBean.CategoriesBean categoriesBean = sRaidersBean.getData().getCategories().get(position);
        String name = categoriesBean.getName();

        viewHolder.setText(R.id.tv_sort_raiders_left,name);

        //如果选中的标题的position 和 这里的position 相同,,就让他改变颜色,,否则,,,不变
        if(position == selectedPos){
            viewHolder.setTextColor(R.id.tv_sort_raiders_left,0xFFFF3C58);
            viewHolder.setBackgroundColor(R.id.ll_sort_raiders_left,0xfffefefe);
        }else {
            viewHolder.setTextColor(R.id.tv_sort_raiders_left,0xff000000);
            viewHolder.setBackgroundColor(R.id.ll_sort_raiders_left, Color.TRANSPARENT);

        }

        return viewHolder.getItemView();
    }
}
