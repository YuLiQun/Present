package qunzai.present.home.homeselection;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
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

import qunzai.present.R;
import qunzai.present.base.BaseFragment;
import qunzai.present.been.HSelectionBean;
import qunzai.present.gson.GsonRequsest;
import qunzai.present.home.wheel.HSWheelAdapter;
import qunzai.present.been.HSWheelBean;
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
    Context context;
    private int urlSize;


    private Handler mHandler;
    private GsonRequsest<HSWheelBean> gsonRequsest;
    private GsonRequsest<HSelectionBean> lvRequsest;
    private ArrayList<HSelectionBean> lvBeenArrayList;
    String url = "http://api.liwushuo.com/v2/channels/100/items_v2?ad=2&gender=1&generation=1&limit=20&offset=0";

    /**
     * 轮播图4.
     * 用这个方法,,起到了轮播图定时的作用
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler(Looper.myLooper()) {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //如果msg.what == 1 并且vp != null,,那么定时起器开始执行
                if (msg.what == 1 && vpWheel != null) {
                    //setCurrentItem  通过调用这个方法来是vp获取当前页
                    vpWheel.setCurrentItem(vpWheel.getCurrentItem() + 1);
                }
                //这个方法和sendMessage  差不多,,这个方法里,,当只需要两个参数的时候,,可以用它
                //参数一: msg.what   参数二: 停止的秒数,,例如,,过了3秒在发送这个msg.what == 1 的信息
                sendEmptyMessageDelayed(1, 3000);
            }
        };
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home_selection;
    }


    @Override
    protected void initView() {
        lv = bindView(R.id.lv_home_selection);

        //填充一个
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.home_selection_wheel, null);
        vpWheel = bindView(view, R.id.vp_home_selection_wheel);
        tvWheelTitle = bindView(view, R.id.tv_home_selection_wheel_title);
        llWheel = bindView(view, R.id.ll_home_selection_wheel_point);

        //添加头布局
        lv.addHeaderView(view);


    }


    @Override
    protected void initData() {
        //轮播图的Gson,,,小园点,,和adapter绑定了
        initWheelUrlData();
        //获取之后,添加任务队列
        VolleySingleSimple.getInstance().addRequest(gsonRequsest);

        //下面listview的adapter
        initUrlData();
        VolleySingleSimple.getInstance().addRequest(lvRequsest);


        //第一个轮播图走的时候
        mHandler.sendEmptyMessage(1);

    }

    private void initUrlData() {

        lvRequsest = new GsonRequsest<HSelectionBean>(HSelectionBean.class,
                url, new Response.Listener<HSelectionBean>() {


            @Override
            public void onResponse(HSelectionBean response) {
//                int itemSize = response.getData().getItems().size();
//
//                lvBeenArrayList = new ArrayList<>();
//                for (int i = 0; i < itemSize; i++) {
//
//                    String img = response.getData().getItems().get(i).getCover_image_url();
//                    String title = response.getData().getItems().get(i).getTitle();
//                    String share = response.getData().getItems().get(i).getShare_msg();
//                    int licks = response.getData().getItems().get(i).getLikes_count();
//                    Log.d("kkk", "licks:" + licks);
//
//                    HSelectionBean.DataBean.ItemsBean bean1 = new HSelectionBean.DataBean.ItemsBean();
//                    bean1.setCover_image_url(img);
//                    bean1.setTitle(title);
//                    bean1.setShare_msg(share);
//                    bean1.setLikes_count(licks);
//
//                    List<HSelectionBean.DataBean.ItemsBean> itemsBeenArr = new ArrayList<>();
//                    itemsBeenArr.add(bean1);
//
//                    HSelectionBean.DataBean dataBean = new HSelectionBean.DataBean();
//                    dataBean.setItems(itemsBeenArr);
//
//                    HSelectionBean bean = new HSelectionBean();
//                    bean.setData(dataBean);
//
//
//                    lvBeenArrayList.add(bean);

                HSelectionAdapter adapter = new HSelectionAdapter(context);
                /*把解析下来的东西直接set给ArrayList*/
                adapter.setArrayList(response);
                lv.setAdapter(adapter);
//                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    //轮播图1.
    private void initWheelUrlData() {

        //轮播点的半径
//设置监听vp和小点一起懂
        gsonRequsest = new GsonRequsest<HSWheelBean>(
                HSWheelBean.class, wheelUrl, new Response.Listener<HSWheelBean>() {
            @Override
            public void onResponse(HSWheelBean response) {
                urlSize = response.getData().getBanners().size();
                arrayList = new ArrayList<>();
                for (int i = 0; i < urlSize; i++) {
                    String imgUrl = response.getData().getBanners().get(i).getImage_url();
                    arrayList.add(imgUrl);
                }

                View view;
                imgArrayList = new ArrayList<ImageView>();
                LinearLayout.LayoutParams params;
                //点的数量是由五片的数量决定的
                for (int i = 0; i < arrayList.size(); i++) {
                    view = new View(context);
                    //轮播点的半径
                    params = new LinearLayout.LayoutParams(10, 10);
                    params.leftMargin = 10;
                    view.setBackgroundResource(R.drawable.home_selection_wheel);
                    view.setLayoutParams(params);
                    view.setEnabled(false);
                    llWheel.addView(view);
                }

                HSWheelAdapter adapter = new HSWheelAdapter(arrayList);
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
    }

    //轮播图2.
    private void initAction() {
        BannerListener bannerListener = new BannerListener();
        vpWheel.addOnPageChangeListener(bannerListener);
        Log.d("zzz", "imgArrayList:" + arrayList);
        int index = (100 / 2) - (100 / 2 % arrayList.size());
        vpWheel.setCurrentItem(index, false);
        llWheel.getChildAt(pointIndex).setEnabled(true);


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (!isStop){
//                    SystemClock.sleep(3000);
//                    getActivity().runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            vpWheel.setCurrentItem(vpWheel.getCurrentItem()+ 1);
//                        }
//                    });
//                }
//            }
//        }).start();

    }

    //轮播图5.
    @Override
    public void onDestroyView() {
        //把handler结束掉,,,,否则轮播图会一直跑,,这样给他结束掉就好
        mHandler.removeMessages(1);
        super.onDestroyView();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    //轮播图3.
    class BannerListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

//            String [] wheelTitle = {"","","","","",""};
//            if (wheelTitle.length != 0){
//                int newPostion = position % wheelTitle.length;
//                tvWheelTitle.setText(wheelTitle[newPostion]);
//                Log.d("BannerListener", "newPostion:" + newPostion);
//                llWheel.getChildAt(newPostion).setEnabled(true);
//                llWheel.getChildAt(pointIndex).setEnabled(false);
//                pointIndex = newPostion;
//
//            }


            //这里写轮播图的文字,哈哈哈哈
            if (urlSize != 0) {
                int newPostion = position % urlSize;
                tvWheelTitle.setText("");
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
