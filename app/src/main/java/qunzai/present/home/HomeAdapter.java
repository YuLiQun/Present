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
        private String titles[] = {"精选", "关注", "送女票", "海淘", "科技范", "美食", "送基友",
            "送爸妈", "送同事", "送宝贝", "设计感", "创意生活", "文艺风", "奇葩搞怪", "数码", "萌萌哒"};
//    private String url = "http://api.liwushuo.com/v2/channels/preset?gender=2&generation=1";
//    private int titleSize;
//    private ArrayList<String> arrayList ;



//    GsonRequsest<HomeTitleBean> beenarr = new GsonRequsest<HomeTitleBean>(HomeTitleBean.class,
//            url, new Response.Listener<HomeTitleBean>() {
//        @Override
//        public void onResponse(HomeTitleBean response) {
//
//            titleSize = response.getData().getChannels().size();
//            arrayList = new ArrayList<>();
//
//            for (int i = 0; i < titleSize; i++) {
//                String imgUrl = response.getData().getChannels().get(i).getName();
//                Log.d("lll", imgUrl);
//                arrayList.add(imgUrl);
//
//
//            }
//
//
//        }
//    }, new Response.ErrorListener() {
//        @Override
//        public void onErrorResponse(VolleyError error) {
//
//        }
//    });



    public void setFragments(List<BaseFragment> fragments) {
        this.fragments = fragments;
    }

    public HomeAdapter(FragmentManager fm) {
        super(fm);

//        VolleySingleSimple.getInstance().addRequest(beenarr);
        fragments = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            if (i == 0) {
                fragments.add(new HomeSelectionFragment());
            } else {
                fragments.add(HRepeatFragment.getInstance(i));
            }

        }

//        Log.d("lll", "pppppp" + arrayList);

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
        return titles[position];
    }
}
