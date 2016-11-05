package qunzai.present.home.homerepeat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;

import qunzai.present.R;
import qunzai.present.base.BaseFragment;
import qunzai.present.been.HSelectionBean;
import qunzai.present.home.homerepeat.homedetails.HomeDetailsActivity;
import qunzai.present.internet.GsonRequest;
import qunzai.present.home.homeselection.HSelectionAdapter;
import qunzai.present.internet.VolleySingleSimple;

/**
 * Created by dllo on 16/10/25.
 */
public class HRepeatFragment extends BaseFragment {

    private static final String KEY = "homerepeat";
    private static final String ID = "homeid";
    private ListView lv;
    Context context;
    private ArrayList<String> arrayUrl = new ArrayList<>();


    public static HRepeatFragment getInstance(int pos, ArrayList<Integer> arrayListId) {
        HRepeatFragment hRepeatFragment = new HRepeatFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY, pos);
        bundle.putIntegerArrayList(ID, arrayListId);

        hRepeatFragment.setArguments(bundle);

        return hRepeatFragment;
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_home_repeat;
    }

    @Override
    protected void initView() {
        lv = bindView(R.id.lv_home_repeat);

    }

    @Override
    protected void initData() {


    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        int pos = bundle.getInt(KEY);
        String url = null;
        ArrayList<Integer> arrayListId = bundle.getIntegerArrayList(ID);
        //拼接字符串
        String frontUrl = "http://api.liwushuo.com/v2/channels/";
        String backUrl = "/items_v2?gender=2&limit=20&offset=0&generation=1HTTP/1.1";
        for (int i = 0; i < arrayListId.size(); i++) {
            //第0个是精选的id,,,所以把第0个去掉,,i+ 1
            if ((i + 1) == pos) {
                String idUrl = String.valueOf(arrayListId.get(i + 1));
                url = frontUrl + idUrl + backUrl;
            } else {
                Log.d("HRepeatFragment", "网络不给力");
            }


        }

        final GsonRequest<HSelectionBean> requsest = new GsonRequest<HSelectionBean>(HSelectionBean.class,
                url, new Response.Listener<HSelectionBean>() {

            @Override
            public void onResponse(HSelectionBean response) {

                //这个adapter和Bean类用的是精选的adapter和bean类
                HSelectionAdapter adapter = new HSelectionAdapter(context);
                adapter.setArrayList(response);
                lv.setAdapter(adapter);

                for (int i = 0; i < response.getData().getItems().size(); i++) {
                    String str = response.getData().getItems().get(i).getUrl();
                     arrayUrl.add(str);
                }


                lvClick();



            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleSimple.getInstance().addRequest(requsest);

    }


    private void lvClick() {

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = arrayUrl.get(position);

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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
