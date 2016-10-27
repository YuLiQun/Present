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
import qunzai.present.gson.GsonRequsest;
import qunzai.present.home.homerepeat.HRepeatFragment;
import qunzai.present.home.homeselection.HomeSelectionFragment;
import qunzai.present.single.VolleySingleSimple;

/**
 * Created by dllo on 16/10/25.
 */
public class HomeAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragments;
    private ArrayList<String> arrayList;
//    private String titles[] = {"精选", "关注", "送女票", "海淘", "科技范", "美食", "送基友",
//            "送爸妈", "送同事", "送宝贝", "设计感", "创意生活", "文艺风", "奇葩搞怪", "数码", "萌萌哒"};




    public void setFragments(List<BaseFragment> fragments) {
        this.fragments = fragments;
    }

    public HomeAdapter(FragmentManager fm ,ArrayList<String> arrayList) {
        super(fm);
        this.arrayList = arrayList;


        fragments = new ArrayList<>();
        Log.d("zzz", "arrayList.size():" + arrayList.size());
        for (int i = 0; i < arrayList.size(); i++) {
            if (i == 0) {
                fragments.add(new HomeSelectionFragment());
            } else {
                fragments.add(HRepeatFragment.getInstance(i));
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
//        Log.d("lll", "+++" + arrayList.get(position));
        return arrayList.get(position);
    }
}
