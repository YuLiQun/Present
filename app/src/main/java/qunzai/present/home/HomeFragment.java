package qunzai.present.home;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;

import qunzai.present.R;
import qunzai.present.base.BaseFragment;
import qunzai.present.been.HomeTitleBean;
import qunzai.present.internet.GsonRequest;
import qunzai.present.internet.VolleySingleSimple;

/**
 * Created by dllo on 16/10/21.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private TabLayout tbHome;
    private ViewPager vpHome;
    private GsonRequest<HomeTitleBean> beenarr;
    private ImageView img;
    private HPopAdapter popAdapter;
    private PopupWindow pop;
    private RecyclerView popRv;


    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {

        tbHome = bindView(R.id.tb_home);
        vpHome = bindView(R.id.vp_home);
        img = bindView(R.id.img_home_pop);
        img.setOnClickListener(this);

    }

    @Override
    protected void initData() {


        initGsonTitle();
        //点击让pop消失

        VolleySingleSimple.getInstance().addRequest(beenarr);


    }

    private void rvClick() {
        Log.d("sss", "消失了么++++"  + "走了么");
        popAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onClick(int position) {

                Log.d("sss", "消失了么++++" + position);
                if (pop.isShowing()){
                    pop.dismiss();
                    Log.d("sss", "消失了么" + position);
                }
            }
        });





    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_home_pop://点击显示pop
                if (pop != null && pop.isShowing()) {
                    return;
                } else {
                    popShow();
                }



                break;
        }
    }


    private void popShow() {
        pop = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        View viewW = LayoutInflater.from(mContext).inflate(R.layout.item_home_pop, null);
        popRv = bindView(viewW, R.id.rv_home_pop);
        popRv.setAdapter(popAdapter);
        GridLayoutManager manager = new GridLayoutManager(mContext, 4, LinearLayoutManager.VERTICAL, false);
        popRv.setLayoutManager(manager);

        pop.setContentView(viewW);
        pop.showAsDropDown(img, 0, 0);



    }


    private void initGsonTitle() {
        String url = "http://api.liwushuo.com/v2/channels/preset?gender=2&generation=1";
        final ArrayList<String> arrayList = new ArrayList<>();
        final ArrayList<Integer> arrayListId = new ArrayList<>();

        beenarr = new GsonRequest<HomeTitleBean>(HomeTitleBean.class,
                url, new Response.Listener<HomeTitleBean>() {
            @Override
            public void onResponse(HomeTitleBean response) {

                int titleSize = response.getData().getChannels().size();

                for (int i = 0; i < titleSize; i++) {
                    String titleUrl = response.getData().getChannels().get(i).getName();
                    int titleId = response.getData().getChannels().get(i).getId();

                    arrayListId.add(titleId);
                    arrayList.add(titleUrl);

                }

                HomeAdapter adapter = new HomeAdapter(getChildFragmentManager(), arrayList, arrayListId);
                //pop的adapter
                popAdapter = new HPopAdapter();
                popAdapter.setArrayList(arrayList);
                //pop的点击事件
                rvClick();

                vpHome.setAdapter(adapter);

                tbHome.setupWithViewPager(vpHome);
//                tbHome.setSelectedTabIndicatorColor(Color.RED);//滑动条颜色
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
