package qunzai.present.hotsale;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;

import qunzai.present.R;
import qunzai.present.base.BaseFragment;
import qunzai.present.been.HotSaleTitleBean;
import qunzai.present.internet.GsonRequest;
import qunzai.present.internet.MyURL;
import qunzai.present.internet.VolleySingleSimple;

/**
 * Created by dllo on 16/10/21.
 */
public class HotSaleFragment extends BaseFragment {

    private TabLayout tbHotsale;
    private ViewPager vpHotsale;
//    private String url = "http://api.liwushuo.com/v2/ranks_v2/ranks";

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



        initGsonData();


        


    }

    private void initGsonData() {
         GsonRequest<HotSaleTitleBean> requsest = new GsonRequest<HotSaleTitleBean>(HotSaleTitleBean.class,
                 MyURL.HOT_TITLE, new Response.Listener<HotSaleTitleBean>() {
            @Override
            public void onResponse(HotSaleTitleBean response) {
                int titleSize = response.getData().getRanks().size();
                ArrayList<String> titles = new ArrayList<>();
                ArrayList<Integer> ids = new ArrayList<>();
                for (int i = 0; i < titleSize; i++) {
                    String name = response.getData().getRanks().get(i).getName();
                    int id = response.getData().getRanks().get(i).getId();
                    titles.add(name);
                    ids.add(id);

                }

                HotSaleAdapter adapter = new HotSaleAdapter(getChildFragmentManager(),titles,ids);
                vpHotsale.setAdapter(adapter);
                tbHotsale.setupWithViewPager(vpHotsale);
                tbHotsale.setSelectedTabIndicatorColor(Color.RED);//滑动条颜色
//        tbHotsale.setTabTextColors(Color.RED,0xFFFF0000);//标题字体颜色

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                
            }
        });

        VolleySingleSimple.getInstance().addRequest(requsest);

    }
}
