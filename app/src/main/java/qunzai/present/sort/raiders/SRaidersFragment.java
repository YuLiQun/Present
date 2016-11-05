package qunzai.present.sort.raiders;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;



import qunzai.present.R;
import qunzai.present.base.BaseFragment;
import qunzai.present.been.SOneBean;
import qunzai.present.been.SOneHeaderBean;
import qunzai.present.internet.GsonRequest;
import qunzai.present.internet.MyURL;
import qunzai.present.internet.VolleySingleSimple;

/**
 * Created by dllo on 16/10/31.
 */
public class SRaidersFragment extends BaseFragment {

    private RecyclerView rv;
    Context context;
    private ListView lv;

    @Override
    protected int getLayout() {
        return R.layout.fragment_sort_one;
    }

    @Override
    protected void initView() {


        lv = bindView(R.id.lv_sort_one);
        View view = LayoutInflater.from(context).inflate(R.layout.item_lv_header, null);
        rv = bindView(view,R.id.rv_sort_one_header);
        lv.addHeaderView(view);



    }

    @Override
    protected void initData() {

        initRvGsonData();

        initHeaderGson();


    }

    private void initHeaderGson() {
//        String url = "http://api.liwushuo.com/v2/columns?limit=20&offset=0";
        GsonRequest<SOneHeaderBean> requsest = new GsonRequest<SOneHeaderBean>(SOneHeaderBean.class,
                MyURL.SORT_RAIDERS_HEADER, new Response.Listener<SOneHeaderBean>() {
            @Override
            public void onResponse(SOneHeaderBean response) {
                SRaidersHeaderAdapter adapter = new SRaidersHeaderAdapter();
                adapter.setsOneHeaderBean(response);
                rv.setAdapter(adapter);


                GridLayoutManager manager = new GridLayoutManager(context,3,LinearLayoutManager.HORIZONTAL,false);
                rv.setLayoutManager(manager);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleSimple.getInstance().addRequest(requsest);
    }

    private void initRvGsonData() {
//        String url = "http://api.liwushuo.com/v2/channel_groups/all";
        GsonRequest<SOneBean> requsest = new GsonRequest<SOneBean>(SOneBean.class,
                MyURL.SORT_RAIDERS_LV, new Response.Listener<SOneBean>() {
            @Override
            public void onResponse(SOneBean response) {
                Log.d("aaa", "response:" + response);
                SRaidersAdapter adapter = new SRaidersAdapter();
                adapter.setsOneBean(response);
                lv.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleSimple.getInstance().addRequest(requsest);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
