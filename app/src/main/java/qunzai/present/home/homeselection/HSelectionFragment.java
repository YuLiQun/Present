package qunzai.present.home.homeselection;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import qunzai.present.R;
import qunzai.present.base.BaseFragment;
import qunzai.present.been.HSelectionBean;
import qunzai.present.home.homerepeat.homedetails.HomeDetailsActivity;
import qunzai.present.internet.GsonRequest;
import qunzai.present.home.wheel.HSWheelAdapter;
import qunzai.present.been.HSWheelBean;
import qunzai.present.internet.MyURL;
import qunzai.present.internet.VolleySingleSimple;
import qunzai.present.refresh.MeiTuanListView;


/**
 * Created by dllo on 16/10/25.
 */
public class HSelectionFragment extends BaseFragment implements MeiTuanListView.OnMeiTuanRefreshListener, MeiTuanListView.OnLoadListener {

    /*刷新的方法是美团listView,,
    * 加载是用OnScrollListener,,但是这个监听和美团listView重复了,,,所一可以在美团listView里写*/
    private final static int REFRESH_COMPLETE = 0;
    private MeiTuanListView lv;
    private InterHandler mInterHandler = new InterHandler(this);

    private ArrayList<String> arrayList;
    private ArrayList<ImageView> imgArrayList;
    private ViewPager vpWheel;
    private TextView tvWheelTitle;
    private LinearLayout llWheel;
    private int pointIndex = 0;
    private boolean isStop = false;
    Context context;
    private int urlSize;
    private ArrayList<String> arrayUrl = new ArrayList<>();


    private Handler mHandler;
    private GsonRequest<HSWheelBean> gsonRequsest;
    private GsonRequest<HSelectionBean> lvRequsest;
    private ArrayList<HSelectionBean> lvBeenArrayList;
    private int j = 20;

    private HSelectionAdapter adapter;
    private LinearLayout ll;

    private boolean canLoad = true;

    @Override
    public void onLoad() {
        initGsonLoading();
        lv.setLoadComplite();//网络加载,,如果没有加载完的话,,就酱
    }

    /*刷新动画*/
    private static class InterHandler extends Handler {
        private WeakReference<HSelectionFragment> mActivity;

        public InterHandler(HSelectionFragment activity) {
            mActivity = new WeakReference<HSelectionFragment>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            final HSelectionFragment activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case REFRESH_COMPLETE:
//                        activity.initData();
                        activity.lv.setOnRefreshComplete();
                        activity.adapter.notifyDataSetChanged();
                        activity.lv.setSelection(0);


                        break;
                }
            } else {
                super.handleMessage(msg);
            }
        }
    }

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
        ll = bindView(R.id.ll_home_selection_touch);
        lv = bindView(R.id.lv_home_selection);

        //填充一个头布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.home_selection_wheel, null);
        vpWheel = bindView(view, R.id.vp_home_selection_wheel);
        tvWheelTitle = bindView(view, R.id.tv_home_selection_wheel_title);
        llWheel = bindView(view, R.id.ll_home_selection_wheel_point);

        //填充一个脚布局
        View viewFoot = LayoutInflater.from(mContext).inflate(R.layout.home_selection_foot, null);
        TextView tvFoot = bindView(R.id.tv_home_selection_foot);

        //添加头布局
        lv.addHeaderView(view);

        lv.setOnMeiTuanRefreshListener(this);
        //监听器在ListView滚动时触发，
      //  lv.setOnScrollListener(this);
        lv.setOnLoadListener(this);

    }

    @Override
    protected void initData() {
        //轮播图的Gson,,,小园点,,和adapter绑定了
        initWheelUrlData();
        //获取之后,添加任务队列
        VolleySingleSimple.getInstance().addRequest(gsonRequsest);

        //下面listview的adapter
        adapter = new HSelectionAdapter(context);
        initUrlData();
        VolleySingleSimple.getInstance().addRequest(lvRequsest);


        //第一个轮播图走的时候
        mHandler.sendEmptyMessage(1);


    }

//    //监听器在ListView滚动时触发，
//    @Override
//    public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//
//    }
//
//    ////监听器在ListView滚动时触发，
//    @Override
//    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//        if (adapter == null) {
//            return;
//        }
//        if (firstVisibleItem + visibleItemCount >= adapter.getCount() - 1 && canLoad) {
//           // initGsonLoading();
//
//        }
//
//
//    }

    private void initGsonLoading() {
        canLoad = false;
        if (j >= 20) {
            j = j + 20;
        }
        final String url = "http://api.liwushuo.com/v2/channels/108/items_v2?ad=2&gender=1&generation=1&limit=20&offset=" + j;
        GsonRequest<HSelectionBean> request = new GsonRequest<HSelectionBean>(HSelectionBean.class,
                url, new Response.Listener<HSelectionBean>() {
            @Override
            public void onResponse(HSelectionBean response) {
                Log.d("zzz", "j:" + j);
                Log.d("zzz", url);
                adapter.setArrayList(response, false);

                canLoad = true;

                for (int i = 0; i < response.getData().getItems().size(); i++) {
                    String url = response.getData().getItems().get(i).getUrl();
                    arrayUrl.add(url);
                }
                lvClick();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "网络不给力", Toast.LENGTH_SHORT).show();
            }
        });

        VolleySingleSimple.getInstance().addRequest(request);

    }

//    private void lvPullToRefresh() {
//        lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
//            @Override
//            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
//
//
//            }
//
//            @Override
//            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
//
//
//
//                if (j >= 20) {
//                    j = j + 20;
//                }
//                String url = "http://api.liwushuo.com/v2/channels/108/items_v2?ad=2&gender=1&generation=1&limit=20&offset=" + j;
//                GsonRequest<HSelectionBean> request = new GsonRequest<HSelectionBean>(HSelectionBean.class,
//                        url, new Response.Listener<HSelectionBean>() {
//                    @Override
//                    public void onResponse(HSelectionBean response) {
//
//                        adapter.setArrayList(response, false);
//                        lv.onRefreshComplete();
//
//
//                        for (int i = 0; i < response.getData().getItems().size(); i++) {
//
//                            String url = response.getData().getItems().get(i).getUrl();
//
//                            arrayUrl.add(url);
//                        }
//
//
//                        lvClick();
//
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                        Toast.makeText(context, "网络不给力", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                initPullUpToRefresh();
//                pullToRefreshBase.onRefreshComplete();
//                VolleySingleSimple.getInstance().addRequest(request);
//            }
//
//
//        });
//
//    }


    private void initUrlData() {

        lvRequsest = new GsonRequest<HSelectionBean>(HSelectionBean.class,
                MyURL.HOME_WHEEL_LV, new Response.Listener<HSelectionBean>() {


            @Override
            public void onResponse(HSelectionBean response) {


                /*把解析下来的东西直接set给ArrayList*/
                adapter.setArrayList(response);
                lv.setAdapter(adapter);


                for (int i = 0; i < response.getData().getItems().size(); i++) {

                    String url = response.getData().getItems().get(i).getUrl();

                    arrayUrl.add(url);
                }


                lvClick();


            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
    }

    private void lvClick() {

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = arrayUrl.get(position - 2);

                if (s != null && !"".equals(s)) {
                    Intent intent = new Intent(context, HomeDetailsActivity.class);
                    intent.putExtra("content", s);
                    startActivity(intent);
                } else {
                    Toast.makeText(context, "找不到网址", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //轮播图1.
    private void initWheelUrlData() {

        //轮播点的半径
        //设置监听vp和小点一起懂
        gsonRequsest = new GsonRequest<HSWheelBean>(
                HSWheelBean.class, MyURL.HOME_WHEEL, new Response.Listener<HSWheelBean>() {
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

    /*刷新动画*/
    @Override
    public void onRefresh() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    //  mDatas.add(0, "new data");
                    mInterHandler.sendEmptyMessage(REFRESH_COMPLETE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}
