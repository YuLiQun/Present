package qunzai.present.home.homeselection;

import android.content.Context;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

import qunzai.present.R;
import qunzai.present.base.BaseFragment;
import qunzai.present.gson.GsonRequsest;
import qunzai.present.home.HomeSelectionWheelAdapter;
import qunzai.present.single.VolleySingleSimple;

/**
 * Created by dllo on 16/10/25.
 */
public class HomeSelectionFragment extends BaseFragment {

    private ListView lv;
    String wheelUrl = "http://api.liwushuo.com/v2/banners";
    private ArrayList<String> arrayList;
    private ArrayList<ImageView> imgArrayList;
    private ViewPager vpWheel;
    private TextView tvWheelTitle;
    private LinearLayout llWheel;
    private int pointIndex = 0;
    private boolean isStop = false;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home_selection;
    }

    @Override
    protected void initView() {
        lv = bindView(R.id.lv_home_selection);

        //填充一个
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.home_selection_wheel,null);
        vpWheel = bindView(view, R.id.vp_home_selection_wheel);
        tvWheelTitle = bindView(view, R.id.tv_home_selection_wheel_title);
        llWheel = bindView(view, R.id.ll_home_selection_wheel_point);

        //添加头布局
        lv.addHeaderView(view);


    }

    @Override
    protected void initData() {
        GsonRequsest<HomeSelectionBean> gsonRequsest = new GsonRequsest<HomeSelectionBean>(
                HomeSelectionBean.class, wheelUrl, new Response.Listener<HomeSelectionBean>() {
            @Override
            public void onResponse(HomeSelectionBean response) {
                arrayList = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    String imgUrl = response.getData().getBanners().get(i).getImage_url();
                    Log.d("HomeSelectionFragment", imgUrl);
                    arrayList.add(imgUrl);
                }

                View view ;
                imgArrayList = new ArrayList<ImageView>();
                LinearLayout.LayoutParams params;
                for (int i = 0; i < arrayList.size(); i++) {
                    view = new View(getActivity());
                    params = new LinearLayout.LayoutParams(10, 10);
                    params.leftMargin = 10;
                    view.setBackgroundResource(R.drawable.home_selection_wheel);
                    view.setLayoutParams(params);
                    view.setEnabled(false);
                    llWheel.addView(view);
                }

                HomeSelectionWheelAdapter adapter = new HomeSelectionWheelAdapter(arrayList);
                vpWheel.setAdapter(adapter);
                //设置监听vp和小点一起懂
                initAction();
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );

        VolleySingleSimple.getInstance().addRequest(gsonRequsest);
    }

    private void initAction() {
        BannerListener bannerListener = new BannerListener();
        vpWheel.addOnPageChangeListener(bannerListener);
        Log.d("zzz", "imgArrayList:" + arrayList);
        int index  = (100/2) -(100/2 % arrayList.size());
        vpWheel.setCurrentItem(index,false);
        llWheel.getChildAt(pointIndex).setEnabled(true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isStop){
                    SystemClock.sleep(3000);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            vpWheel.setCurrentItem(vpWheel.getCurrentItem()+ 1);
                        }
                    });
                }
            }
        }).start();

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    class BannerListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            String [] wheelTitle = {"","","","","",""};
            if (wheelTitle.length != 0){
                int newPostion = position % wheelTitle.length;
                tvWheelTitle.setText(wheelTitle[newPostion]);
                llWheel.getChildAt(newPostion).setEnabled(true);
                llWheel.getChildAt(pointIndex).setEnabled(false);
                pointIndex = newPostion;

            }


        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
