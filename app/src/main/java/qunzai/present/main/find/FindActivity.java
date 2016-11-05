package qunzai.present.main.find;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.List;

import qunzai.present.R;
import qunzai.present.base.BaseActivity;
import qunzai.present.been.FindTitleBean;
import qunzai.present.internet.GsonRequest;
import qunzai.present.internet.MyURL;
import qunzai.present.internet.VolleySingleSimple;

/**
 * Created by dllo on 16/11/4.
 */
public class FindActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvFinish;
    private ListView lv;
    private GridLayout gl;
    private View glView;

    @Override
    protected int getLayout() {
        return R.layout.activity_find;
    }

    @Override
    protected void initView() {
        tvFinish = bindView(R.id.tv_find);
        View view = LayoutInflater.from(FindActivity.this).inflate(R.layout.item_lv_header_find,null);

        lv = bindView(R.id.lv_find);
        gl = bindView(R.id.gl_find);

        lv.addHeaderView(view);
        tvFinish.setOnClickListener(this);


    }

    @Override
    protected void initData() {




        initGson();


        FindAdapter adapter = new FindAdapter();
        lv.setAdapter(adapter);
    }

    private void initGson() {

//        String url = "http://api.liwushuo.com/v2/search/hot_words";

        GsonRequest<FindTitleBean> request = new GsonRequest<FindTitleBean>(FindTitleBean.class,
                MyURL.FIND, new Response.Listener<FindTitleBean>() {
            @Override
            public void onResponse(FindTitleBean response) {

                int size = response.getData().getHot_words().size();
                List<String> hot_words = response.getData().getHot_words();
                for (int i = 0; i < size; i++) {
                    glView = LayoutInflater.from(FindActivity.this).inflate(R.layout.item_gl_header_find,null);
                    gl.addView(glView);
                    TextView tvName = bindView(glView,R.id.tv_find_item_gl);
                    tvName.setText(hot_words.get(i));
                    if (i< 3){
                        tvName.setTextColor(0xFFFF3C58);
                    }



                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleSimple.getInstance().addRequest(request);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_find:
                //调用手机的返回键
                onBackPressed();
                break;
        }
    }
}