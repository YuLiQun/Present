package qunzai.present.home;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

import qunzai.present.base.BaseFragment;
import qunzai.present.been.HomeTitleBean;
import qunzai.present.gson.GsonRequsest;
import qunzai.present.home.homerepeat.HRepeatFragment;
import qunzai.present.home.homeselection.HomeSelectionFragment;
import qunzai.present.single.VolleySingleSimple;

/**
 * Created by dllo on 16/10/25.
 */
public class HomeAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragments;
    private HomeTitleBean arrayList;
//    private String titles[] = {"精选", "关注", "送女票", "海淘", "科技范", "美食", "送基友",
//            "送爸妈", "送同事", "送宝贝", "设计感", "创意生活", "文艺风", "奇葩搞怪", "数码", "萌萌哒"};




    public void setFragments(List<BaseFragment> fragments) {
        this.fragments = fragments;
    }

    public HomeAdapter(FragmentManager fm ,HomeTitleBean arrayList) {
        super(fm);
        this.arrayList = arrayList;
        int titleSize = arrayList.getData().getChannels().size();

//        fragments = new ArrayList<>();
        for (int i = 0; i < titleSize; i++) {
            int titleId = arrayList.getData().getChannels().get(i).getId();
            if (i == 0) {
                fragments.add(new HomeSelectionFragment());
            } else {
                fragments.add(HRepeatFragment.getInstance(i,titleId));
            }

        }



    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    //设置上面tablayout的标题
    @Override
    public CharSequence getPageTitle(int position) {
        return arrayList.getData().getChannels().get(position).getName();
    }


}
