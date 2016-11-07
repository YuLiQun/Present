package qunzai.present.hotsale.repeat.hotdetails.hone;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.util.ArrayList;
import java.util.List;

import qunzai.present.R;
import qunzai.present.base.BaseFragment;
import qunzai.present.been.HDOneGridBean;
import qunzai.present.been.HotSaleRepeatBean;
import qunzai.present.internet.GsonRequest;
import qunzai.present.internet.VolleySingleSimple;

/**
 * Created by dllo on 16/11/5.
 */
public class HDOneFragment extends BaseFragment {

    private static final String KEY = "HDOne";
    private static final String BEAN = "HDOneID";
    private RollPagerView roll;
    private RecyclerView rvHorizontal,rvGrid;
    private HotSaleRepeatBean.DataBean.ItemsBean itemsBean;
    private TextView tvTop,tvDescription,tvShortDescription,tvName,tvPrice,tvLikesCount;
    private LinearLayout ll;
    private int pos;

    public static HDOneFragment getInstance(int pos , HotSaleRepeatBean.DataBean.ItemsBean itemsBean){
        HDOneFragment hotRepeatFragment = new HDOneFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY,pos);
        bundle.putSerializable(BEAN,itemsBean);
        hotRepeatFragment.setArguments(bundle);

        return hotRepeatFragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_hd_one;
    }

    @Override
    protected void initView() {
        rvHorizontal = bindView(R.id.rv_hot_done_horizontal);
        rvGrid  = bindView(R.id.rv_hot_done_grid);
        roll = bindView( R.id.roll_hot_done);
        tvTop = bindView(R.id.tv_hot_done_top);
        tvShortDescription = bindView(R.id.tv_hot_done_shortdescription);
        tvDescription = bindView(R.id.tv_hot_done_description);
        tvName = bindView(R.id.tv_hot_done_name);
        tvPrice = bindView(R.id.tv_hot_done_price);
        tvLikesCount = bindView(R.id.tv_hot_done_likescount);
        ll = bindView(R.id.ll_hot_done_gone);




    }

    @Override
    protected void initData() {

        Bundle bundle = getArguments();
        itemsBean = (HotSaleRepeatBean.DataBean.ItemsBean) bundle.getSerializable(BEAN);
        pos = bundle.getInt(KEY);


        //轮播图的方法
        initroll();
        //设置其他和数据的方法
        initOtherData();
        //添加横向的rv数据
        initRvHorizontal();






        //rv的adapter
        HotDOneAdapter adapter = new HotDOneAdapter();
        rvHorizontal.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        rvHorizontal.setLayoutManager(manager);




    }

    private void initRvHorizontal() {

        String id = String.valueOf(itemsBean.getId());
        String urlFront = "http://api.liwushuo.com/v2/items/";
        String urlBack = "/recommend?num=20&post_num=5";
        String url = urlFront + id + urlBack;

        GsonRequest<HDOneGridBean >request = new GsonRequest<HDOneGridBean>(HDOneGridBean.class, url,
                new Response.Listener<HDOneGridBean>() {
                    @Override
                    public void onResponse(HDOneGridBean response) {

                        //TODO 值没有传过去 ,,明天继续,,oh yeah
                        Log.d("zzz", "response:)))))" + response);
                        HDOneGridAdapter adapter = new HDOneGridAdapter(response);
                        rvGrid.setAdapter(adapter);
                        GridLayoutManager manager = new GridLayoutManager(mContext,2);
                        rvGrid.setLayoutManager(manager);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });

        VolleySingleSimple.getInstance().addRequest(request);



    }

    private void initOtherData() {
        tvShortDescription.setText(itemsBean.getShort_description());
        tvDescription.setText(itemsBean.getDescription());
        tvName.setText(itemsBean.getName());
        tvLikesCount.setText(String.valueOf(itemsBean.getFavorites_count()));

        tvPrice.setText(itemsBean.getPrice());
        int topSize = itemsBean.getOrder();
        tvTop.setText("TOP" + String.valueOf(topSize));
        if (topSize < 3){
            tvTop.setBackgroundResource(R.drawable.hotsale_top_back);
            tvTop.setTextColor(Color.WHITE);
        }else {
            tvTop.setBackgroundResource(R.drawable.hotsale_top_background);
            tvTop.setTextColor(0xFFFF3C58);
        }


        //TODO 这个里有问题...需要优化
        if (pos == 0){
            ll.setVisibility(View.VISIBLE);
        }else {
            ll.setVisibility(View.GONE);
        }


    }

    private void initroll() {

        roll.setAnimationDurtion(500);//透明度
        HotDOneRollAdapter adapterRoll = new HotDOneRollAdapter(roll, mContext);
        ArrayList<String> arrayList = (ArrayList<String>) itemsBean.getImage_urls();
        adapterRoll.setUrls(arrayList);
        roll.setAdapter(adapterRoll);
        roll.setHintView(new ColorPointHintView(mContext, Color.RED,Color.WHITE));//颜色一:选中颜色  颜色二:默认颜色
    }

}
