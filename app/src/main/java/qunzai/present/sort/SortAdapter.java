package qunzai.present.sort;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import qunzai.present.base.BaseFragment;

/**
 * Created by dllo on 16/10/31.
 */
public class SortAdapter extends FragmentPagerAdapter {

    String titles[] = {"攻略", "单品"};
    ArrayList<BaseFragment> fragments;


    public void setFragments(ArrayList<BaseFragment> fragments) {
        this.fragments = fragments;
    }

    public SortAdapter(FragmentManager fm) {
        super(fm);


//        for (int i = 0; i < titles.length; i++) {
//            if (i == 0) {
//                fragments.add(new SOneFragment());
//            } else {
//                fragments.add(new SRaidersFragment());
//            }
//        }
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
