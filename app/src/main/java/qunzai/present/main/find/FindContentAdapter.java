package qunzai.present.main.find;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import qunzai.present.R;
import qunzai.present.base.CommonViewHolder;
import qunzai.present.been.FindContentBean;

/**
 * Created by dllo on 16/11/14.
 */
public class FindContentAdapter extends BaseAdapter{
    private FindContentBean findContentBean;


    public void setFindContentBean(FindContentBean findContentBean) {
        this.findContentBean = findContentBean;
    }

    @Override
    public int getCount() {
        return findContentBean.getData().getWords().size();
    }

    @Override
    public Object getItem(int position) {
        return findContentBean.getData().getWords().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(convertView,parent, R.layout.item_find_content);
        String content = findContentBean.getData().getWords().get(position).getWord();
        viewHolder.setText(R.id.tv_find_item_content,content);
        return viewHolder.getItemView();
    }
}
