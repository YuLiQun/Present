package qunzai.present.hotsale.repeat.hotdetails.comment;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import qunzai.present.R;
import qunzai.present.base.BaseFragment;
import qunzai.present.been.HDCommentBean;
import qunzai.present.been.HDOneGridBean;
import qunzai.present.been.HotSaleRepeatBean;
import qunzai.present.internet.GsonRequest;
import qunzai.present.internet.VolleySingleSimple;

/**
 * Created by dllo on 16/11/5.
 */
public class HDCommentFragment extends BaseFragment {
    private static final String KEY = "HDComment";
    private static final String ID = "HDCommentId";
    private int id;
    private ListView lv;

    public static HDCommentFragment getInstance(int pos, int id) {
        HDCommentFragment hotRepeatFragment = new HDCommentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY, pos);
        bundle.putInt(ID, id);
        hotRepeatFragment.setArguments(bundle);
        return hotRepeatFragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_hd_comment;
    }


    @Override
    protected void initView() {

        lv = bindView(R.id.lv_hot_d_comment);

    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        id = bundle.getInt(ID);
        initGsonData();


    }

    private void initGsonData() {
        String frontUrl  = "http://api.liwushuo.com/v2/items/";
        String backUrl = "/comments?limit=20&offset=0";
        String idUrl = String.valueOf(id);
        String url = frontUrl + idUrl + backUrl;
        GsonRequest<HDCommentBean> request = new GsonRequest<HDCommentBean>(HDCommentBean.class, url,
                new Response.Listener<HDCommentBean>() {
                    @Override
                    public void onResponse(HDCommentBean response) {

                        HDCommentAdapter adapter = new HDCommentAdapter(response);
                        lv.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleSimple.getInstance().addRequest(request);
    }
}
