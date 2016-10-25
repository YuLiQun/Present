package qunzai.present.home;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import qunzai.present.R;
import qunzai.present.base.BaseFragment;

/**
 * Created by dllo on 16/10/21.
 */
public class HomeFragment extends BaseFragment {

    private TabLayout tbHome;
    private ViewPager vpHome;


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

        HomeAdapter adapter = new HomeAdapter(getChildFragmentManager());


        vpHome.setAdapter(adapter);
        tbHome.setupWithViewPager(vpHome);
        tbHome.setSelectedTabIndicatorColor(Color.RED);//滑动条颜色
        //设置可以滑动
        tbHome.setTabMode(TabLayout.MODE_SCROLLABLE);

    }
}
