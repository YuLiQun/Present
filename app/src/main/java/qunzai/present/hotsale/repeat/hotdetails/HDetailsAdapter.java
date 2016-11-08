package qunzai.present.hotsale.repeat.hotdetails;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.util.SparseArray;

import qunzai.present.base.BaseFragment;
import qunzai.present.been.HotSaleRepeatBean;
import qunzai.present.hotsale.repeat.hotdetails.comment.HDCommentFragment;
import qunzai.present.hotsale.repeat.hotdetails.ddetails.HDDetailsFragment;
import qunzai.present.hotsale.repeat.hotdetails.hone.HDOneFragment;

/**
 * Created by dllo on 16/11/5.
 */
public class HDetailsAdapter extends FragmentPagerAdapter{

    private String title[] = {"单品" ,"详情" ,"评论"};
    private SparseArray<BaseFragment> fragments;
    private  HotSaleRepeatBean.DataBean.ItemsBean itemsBean;


    public void setItemsBean(HotSaleRepeatBean.DataBean.ItemsBean itemsBean) {
        this.itemsBean = itemsBean;

    }

    public HDetailsAdapter(FragmentManager fm  ) {
        super(fm);


        fragments = new SparseArray<>();
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            fragments.put(position, HDOneFragment.getInstance(position,itemsBean));
        }else if (position == 1){
            String url = itemsBean.getUrl();
            fragments.put(position, HDDetailsFragment.getInstance(position,url));
        }else {
            int id = itemsBean.getId();
            fragments.put(position, HDCommentFragment.getInstance(position,id));
        }
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
