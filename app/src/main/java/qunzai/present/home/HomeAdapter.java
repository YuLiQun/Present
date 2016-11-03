package qunzai.present.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.util.SparseArray;

import java.util.ArrayList;

import qunzai.present.base.BaseFragment;
import qunzai.present.home.homerepeat.HRepeatFragment;
import qunzai.present.home.homeselection.HSelectionFragment;

/**
 * Created by dllo on 16/10/25.
 */
public class HomeAdapter extends FragmentPagerAdapter {

    private SparseArray<BaseFragment> fragments;
    private ArrayList<String> arrayList;
    private ArrayList<Integer> arrayListId;

//    private String titles[] = {"精选", "关注", "送女票", "海淘", "科技范", "美食", "送基友",
//            "送爸妈", "送同事", "送宝贝", "设计感", "创意生活", "文艺风", "奇葩搞怪", "数码", "萌萌哒"};


    public HomeAdapter(FragmentManager fm, ArrayList<String> arrayList, ArrayList<Integer> arrayListId) {
        super(fm);
        this.arrayList = arrayList;
        this.arrayListId = arrayListId;
        fragments = new SparseArray<>();
//        for (int i = 0; i < arrayList.size(); i++) {
//            int id = arrayListId.get(i);
//
//            if (i == 0) {
//                fragments.put(i,new HSelectionFragment());
//            } else {
//
//                fragments.put(i,HRepeatFragment.getInstance(i,arrayListId));
//            }
//
//        }


    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            fragments.put(position, new HSelectionFragment());

        } else {
            if (fragments.get(position) == null) {
                fragments.put(position, HRepeatFragment.getInstance(position, arrayListId));
                Log.d("qqq", "arrayListId +++:" + arrayListId);
                Log.d("qqq", "position+++:" + position);
            }
        }
        return fragments.get(position);
    }


    @Override
    public int getCount() {
//        return fragments == null ? 0 : fragments.size();
        return arrayList.size();

    }

    //设置上面tablayout的标题
    @Override
    public CharSequence getPageTitle(int position) {
        return arrayList.get(position);

    }


}
