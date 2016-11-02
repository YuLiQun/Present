package qunzai.present.sort.raiders;

import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import qunzai.present.R;
import qunzai.present.base.BaseFragment;
import qunzai.present.been.SRaidersBean;
import qunzai.present.gson.GsonRequsest;
import qunzai.present.single.VolleySingleSimple;
import qunzai.present.sort.raiders.srlr.SRaidersLeftAdapter;
import qunzai.present.sort.raiders.srlr.SRaidersRightAdapter;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by dllo on 16/10/31.
 */
public class SRaidersFragment extends BaseFragment {

    private ListView lv;
    private StickyListHeadersListView sLv;


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

    }

    private void initGsonLeft() {
        String url = "http://api.liwushuo.com/v2/item_categories/tree";
        GsonRequsest<SRaidersBean> requsest = new GsonRequsest<SRaidersBean>(SRaidersBean.class,
                url, new Response.Listener<SRaidersBean>() {
            @Override
            public void onResponse(SRaidersBean response) {

                SRaidersLeftAdapter leftAdapter = new SRaidersLeftAdapter();
                leftAdapter.setsRaidersBean(response);
                lv.setAdapter(leftAdapter);

                int size  = response.getData().getCategories().size();
                for (int i = 0; i < size; i++) {

                }
                SRaidersRightAdapter rightAdapter = new SRaidersRightAdapter();
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
