package qunzai.present.hotsale;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import java.util.ArrayList;

import qunzai.present.base.BaseFragment;
import qunzai.present.been.HotSaleTitleBean;
import qunzai.present.hotsale.repeat.HotRepeatFragment;

/**
 * Created by dllo on 16/10/25.
 */
public class HotSaleAdapter extends FragmentPagerAdapter{
    private Context context;
    private SparseArray<BaseFragment> fragments;
    private ArrayList<String> arrayList;
    private ArrayList<Integer> arrayListId;
//    String titles[] = {"每日推荐","TOP100","独立原创榜","新星榜"};


    public HotSaleAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;

    }



    public HotSaleAdapter(FragmentManager fm,ArrayList<String> arrayList,ArrayList<Integer> arrayListId) {
        super(fm);
        this.arrayList = arrayList;
        this.arrayListId = arrayListId;
        int tSize  = arrayList.size();
        //用for循环建立多个布局相似的fragment
        fragments = new SparseArray<>();
//        for (int i = 0; i < tSize; i++) {
//
//            fragments.add(HotRepeatFragment.getInstance(i,arrayListId));
//        }

    }

    @Override
    public Fragment getItem(int position) {
        if (fragments.get(position) == null){
            fragments.put(position,HotRepeatFragment.getInstance(position,arrayListId));

        }
        return fragments.get(position);
    }

    @Override
    public int getCount() {
//        return fragments == null ? 0 :fragments.size();
        return arrayList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return arrayList.get(position);
    }
}
