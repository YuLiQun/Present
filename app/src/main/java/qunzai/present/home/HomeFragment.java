package qunzai.present.home;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;

import qunzai.present.R;
import qunzai.present.base.BaseFragment;
import qunzai.present.been.HomeTitleBean;
import qunzai.present.gson.GsonRequsest;
import qunzai.present.single.VolleySingleSimple;

/**
 * Created by dllo on 16/10/21.
 */
public class HomeFragment extends BaseFragment {

    private TabLayout tbHome;
    private ViewPager vpHome;
    private GsonRequsest<HomeTitleBean> beenarr;


    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

        tbHome = bindView(R.id.tb_home);
        vpHome = bindView(R.id.vp_home);

    }

    @Override
    protected void initData() {


        initGsonTitle();
        VolleySingleSimple.getInstance().addRequest(beenarr);

    }

    private void initGsonTitle() {
        String url = "http://api.liwushuo.com/v2/channels/preset?gender=2&generation=1";
        final ArrayList<String> arrayList = new ArrayList<>();
        //滑动条颜色
//设置可以滑动
        beenarr = new GsonRequsest<HomeTitleBean>(HomeTitleBean.class,
                url, new Response.Listener<HomeTitleBean>() {
            @Override
            public void onResponse(HomeTitleBean response) {

//                int titleSize = response.getData().getChannels().size();
//
//
//                for (int i = 0; i < titleSize; i++) {
//                    String titleUrl = response.getData().getChannels().get(i).getName();
//                    int titleId = response.getData().getChannels().get(i).getId();
//                    Log.d("zzz", titleUrl);
//                    arrayList.add(titleId,titleUrl);
//                    Log.d("zzz", "arrayList:" + arrayList);
//                }

                HomeAdapter adapter = new HomeAdapter(getChildFragmentManager(),beenarr);
                vpHome.setAdapter(adapter);
                tbHome.setupWithViewPager(vpHome);
                tbHome.setSelectedTabIndicatorColor(Color.RED);//滑动条颜色
                //设置可以滑动
                tbHome.setTabMode(TabLayout.MODE_SCROLLABLE);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }


}
