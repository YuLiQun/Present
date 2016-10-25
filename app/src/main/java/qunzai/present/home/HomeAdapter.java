package qunzai.present.home;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import qunzai.present.base.BaseFragment;
import qunzai.present.home.homerepeat.HomeRepeatFragment;
import qunzai.present.home.homeselection.HomeSelectionFragment;

/**
 * Created by dllo on 16/10/25.
 */
public class HomeAdapter extends FragmentPagerAdapter {
    private  Context context;
    private List<BaseFragment> fragments;
    private String titles[] = {"精选","关注","送女票","海淘","科技范","美食","送基友",
            "送爸妈","送同事","送宝贝","设计感","创意生活","文艺风","奇葩搞怪","数码","萌萌哒"};


    public HomeAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    public void setFragments(List<BaseFragment> fragments) {
        this.fragments = fragments;
    }

    public HomeAdapter(FragmentManager fm) {
        super(fm);
         fragments = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            if (i == 0){
                fragments.add(new HomeSelectionFragment());
            }else {
                fragments.add(HomeRepeatFragment.getInstance(i + 1));
            }

        }

    }

    @Override
    public Fragment getItem(int position) {
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
