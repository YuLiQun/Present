package qunzai.present.sort.one;

import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import qunzai.present.R;
import qunzai.present.base.BaseFragment;
import qunzai.present.been.SRaidersBean;
import qunzai.present.internet.GsonRequest;
import qunzai.present.internet.VolleySingleSimple;
import qunzai.present.sort.one.orlr.SOneLeftAdapter;
import qunzai.present.sort.one.orlr.SOneRightAdapter;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by dllo on 16/10/31.
 */
public class SOneFragment extends BaseFragment {

    private ListView lv;
    private StickyListHeadersListView sLv;
    private SOneLeftAdapter leftAdapter;


    @Override
    protected int getLayout() {
        return R.layout.fragment_sort_raiders;
    }

    @Override
    protected void initView() {
        lv = bindView(R.id.lv_sort_raiders);
        sLv = bindView(R.id.slv_sort_raiders);



    }

    @Override
    protected void initData() {

        initGsonLeft();
        leftAdapter = new SOneLeftAdapter();
        lv.setAdapter(leftAdapter);


        //listView点击
        lvClick();
        //StickyListHeadersListView  滑动事件
        sLvScrollClick();




    }

    private void sLvScrollClick() {
        sLv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                lv.smoothScrollToPositionFromTop(firstVisibleItem,0);
                //获取滑动的标题的position,,从而改变颜色
                leftAdapter.setSelectdPos(firstVisibleItem);
            }
        });
    }

    private void lvClick() {


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sLv.setSelection(position);
                //获取点击的标题的position,,从而改变颜色
                leftAdapter.setSelectdPos(position);
            }
        });
    }

    private void initGsonLeft() {
        String url = "http://api.liwushuo.com/v2/item_categories/tree";
        GsonRequest<SRaidersBean> requsest = new GsonRequest<SRaidersBean>(SRaidersBean.class,
                url, new Response.Listener<SRaidersBean>() {
            @Override
            public void onResponse(SRaidersBean response) {


                leftAdapter.setsRaidersBean(response);


                int size  = response.getData().getCategories().size();
                for (int i = 0; i < size; i++) {

                }
                SOneRightAdapter rightAdapter = new SOneRightAdapter();
                rightAdapter.setsRaidersBean(response);
                sLv.setAdapter(rightAdapter);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleSimple.getInstance().addRequest(requsest);
    }
}
