package qunzai.present.hotsale;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import qunzai.present.base.BaseFragment;
import qunzai.present.hotsale.repeat.HotRepeatFragment;

/**
 * Created by dllo on 16/10/25.
 */
public class HotsaleAdapter extends FragmentPagerAdapter{
    private Context context;
    private ArrayList<BaseFragment> fragments;
    String titles[] = {"每日推荐","TOP100","独立原创榜","新星榜"};

    public HotsaleAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;

    }

    public void setFragments(ArrayList<BaseFragment> fragments) {
        this.fragments = fragments;
    }

    public HotsaleAdapter(FragmentManager fm) {
        super(fm);
        //用for循环建立多个布局相似的fragment
        fragments = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            fragments.add(HotRepeatFragment.getInstance(i));
        }

    }

    @Override
    public Fragment getItem(int position) {
//        if (fragments.get(position) == null){
//
//        }
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 :fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return titles[position];
    }
}
