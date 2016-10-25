package qunzai.present.hotsale;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import qunzai.present.R;
import qunzai.present.base.BaseFragment;
import qunzai.present.single.MyApp;

/**
 * Created by dllo on 16/10/21.
 */
public class HotSaleFragment extends BaseFragment {

    private TabLayout tbHotsale;
    private ViewPager vpHotsale;

    @Override
    protected int getLayout() {
        return R.layout.fragment_hotsale;
    }

    @Override
    protected void initView() {
        tbHotsale = bindView(R.id.tb_hotsale);
        vpHotsale = bindView(R.id.vp_hotsale);





    }

    @Override
    protected void initData() {



        HotsaleAdapter adapter = new HotsaleAdapter(getChildFragmentManager());
        vpHotsale.setAdapter(adapter);
        tbHotsale.setupWithViewPager(vpHotsale);
        tbHotsale.setSelectedTabIndicatorColor(Color.RED);//滑动条颜色
//        tbHotsale.setTabTextColors(Color.RED,0xFFFF0000);//标题字体颜色


    }
}
