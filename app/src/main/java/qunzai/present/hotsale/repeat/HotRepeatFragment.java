package qunzai.present.hotsale.repeat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;

import java.util.ArrayList;

import qunzai.present.R;
import qunzai.present.base.BaseFragment;
import qunzai.present.been.HotSaleRepeatBean;
import qunzai.present.home.OnRecyclerItemClickListener;
import qunzai.present.hotsale.repeat.hotdetails.HotDetailsActivity;
import qunzai.present.internet.GsonRequest;
import qunzai.present.internet.VolleySingleSimple;

/**
 * Created by dllo on 16/10/25.
 */
public class HotRepeatFragment extends BaseFragment {

    private TextView tv;
    private static final String KEY = "hotsale";//省去key值,,pos位置
    private static final String ID = "hotid";
    private ImageView imgTit;
    private RecyclerView rv;
    private Context context;
    private RecyclerViewHeader rvHeader;
    private ListView lv;
    private HotRepeatAdapter adapter;
    private HotSaleRepeatBean.DataBean.ItemsBean itemsBean;

    public static HotRepeatFragment getInstance(int pos , ArrayList<Integer> arrayListId){
        HotRepeatFragment hotRepeatFragment = new HotRepeatFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY,pos);
        bundle.putIntegerArrayList(ID,arrayListId);
        hotRepeatFragment.setArguments(bundle);

        return hotRepeatFragment;
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_hot_repeat;
    }

    @Override
    protected void initView() {

        rv = bindView(R.id.rv_hot_repeat);
//        rv.setBackgroundColor(0xFFF7F6F4);
        rvHeader = bindView(R.id.rvheader_hot_repeat);
//        header = RecyclerViewHeader.fromXml(context, R.layout.item_rv_header);
        imgTit = bindView(rvHeader,R.id.img_hot_repeat_header);







    }

    @Override
    protected void initData() {



    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        final int pos = bundle.getInt(KEY);
        ArrayList<Integer> arrayListId = bundle.getIntegerArrayList(ID);
        String fromdUrl = "http://api.liwushuo.com/v2/ranks_v2/ranks/";
        String backUrl = "?limit=20&offset=0HTTP/1.1";
        String url = null;

        for (int i = 0; i < arrayListId.size(); i++) {

            if (i == pos){

                String idUrl = String.valueOf(arrayListId.get(i));
                url = fromdUrl + idUrl + backUrl;
            }

        }

        final GsonRequest<HotSaleRepeatBean> requsest  = new GsonRequest<HotSaleRepeatBean>(HotSaleRepeatBean.class,
                url, new Response.Listener<HotSaleRepeatBean>() {
            @Override
            public void onResponse(HotSaleRepeatBean response) {


                adapter = new HotRepeatAdapter(context);
                adapter.setRepeatBean(response);
                rv.setAdapter(adapter);
                int size = response.getData().getItems().size();
                for (int i = 0; i < size; i++) {
                    itemsBean = response.getData().getItems().get(i);
                }





                rvClick();


                String imgTitUrl = response.getData().getCover_image();
                VolleySingleSimple.getInstance().getImage(imgTitUrl,imgTit);



                GridLayoutManager manager = new GridLayoutManager(context,2);
                rv.setLayoutManager(manager);
                //添加reyclerView的头布局
                rvHeader.attachTo(rv,true);







            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleSimple.getInstance().addRequest(requsest);

        
    }

    private void rvClick() {
        adapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(context, HotDetailsActivity.class);
                Bundle bundle = new Bundle();
                //获取adapter 里的position
                bundle.putSerializable("hotitem" ,adapter.getItemBean(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
