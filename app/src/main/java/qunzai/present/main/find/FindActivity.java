package qunzai.present.main.find;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TimerTask;

import qunzai.present.R;
import qunzai.present.base.BaseActivity;
import qunzai.present.been.FindContentBean;
import qunzai.present.been.FindTitleBean;
import qunzai.present.been.TestBean;
import qunzai.present.been.TextEvent;
import qunzai.present.database.LiteOrmSingleSimple;
import qunzai.present.internet.GsonRequest;
import qunzai.present.internet.MyURL;
import qunzai.present.internet.VolleySingleSimple;
import qunzai.present.other.OnEventbusChangeListener;

/**
 * Created by dllo on 16/11/4.
 */
public class FindActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvFinish;
    private ListView lv;
    private GridLayout gl;
    private View glView;
    private EditText etFind;
    private FindAdapter adapter;

    private TextView tvName;
    private List<String> hot_words;
    private ArrayList<TestBean> arrayListdatabase;
    private TestBean beandatabase;
    private ListView lvFind;
    private String urlFind;

    @Override
    protected int getLayout() {
        return R.layout.activity_find;
    }

    @Override
    protected void initView() {
        tvFinish = bindView(R.id.tv_find);
        etFind = bindView(R.id.et_find);
        lvFind = bindView(R.id.lv_find_content);

        View view = LayoutInflater.from(FindActivity.this).inflate(R.layout.item_lv_header_find, null);

        lv = bindView(R.id.lv_find);
        gl = bindView(R.id.gl_find);
        lv.addHeaderView(view);
        tvFinish.setOnClickListener(this);


    }

    @Override
    protected void initData() {

        adapter = new FindAdapter();

        lv.setAdapter(adapter);

        initGson();//显示gridView的东东,有点击世家以及存数据库

        lvItemClick();//点击历史记录的listView跳转

        liteQuery();//茶数据库并显示

        initFindListener();//et的监听事件,,监听是否输入东西






    }

    private void lvFindClick() {
        lvFind.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FindActivity.this,FindShowActivity.class);
                intent.putExtra("cont",urlFind);
                startActivity(intent);
            }
        });
    }

    private void initGsonFind() {
        String url = "http://api.liwushuo.com/v2/search/word_completed_with_rst_cnt?keyword=";
        String content = etFind.getText().toString();
        urlFind = url + content;
        GsonRequest<FindContentBean> request = new GsonRequest<FindContentBean>(FindContentBean.class,
                urlFind, new Response.Listener<FindContentBean>() {
            @Override
            public void onResponse(FindContentBean response) {

                FindContentAdapter adapter = new FindContentAdapter();
                adapter.setFindContentBean(response);
                lvFind.setAdapter(adapter);
                //显示后的listView 的点击事件
                lvFindClick();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        VolleySingleSimple.getInstance().addRequest(request);
    }

    private void initFindListener() {
        etFind.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() > 0) {
                    lvFind.setVisibility(View.VISIBLE);
                    //把输入的东西解析出来
                    initGsonFind();
                } else {
                    lvFind.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    private void liteQuery() {
        LiteOrmSingleSimple.getInstance().queryAllData(TestBean.class, new LiteOrmSingleSimple.OnQueryListenerAll<TestBean>() {
            @Override
            public void onQuery(List<TestBean> T) {
                ArrayList<TestBean> arrayList = new ArrayList<TestBean>();
                for (int i = 0; i < T.size(); i++) {
                    String data = T.get(i).getData();
                    TestBean testBean = new TestBean();
                    testBean.setData(data);
                    arrayList.add(testBean);

                }

                adapter.setArrayList(T);

            }
        });
    }

    private void lvItemClick() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String data = arrayListdatabase.get(position).getData();
                Intent intent = new Intent(FindActivity.this, FindShowActivity.class);
                intent.putExtra("cont", data);
                startActivity(intent);

            }
        });
    }

    private void initGson() {


        GsonRequest<FindTitleBean> request = new GsonRequest<FindTitleBean>(FindTitleBean.class,
                MyURL.FIND, new Response.Listener<FindTitleBean>() {
            @Override
            public void onResponse(FindTitleBean response) {


                int size = response.getData().getHot_words().size();
                hot_words = response.getData().getHot_words();
                for (int i = 0; i < size; i++) {
                    glView = LayoutInflater.from(FindActivity.this).inflate(R.layout.item_gl_header_find, null);
                    gl.addView(glView);
                    tvName = bindView(glView, R.id.tv_find_item_gl);
                    final String cont = hot_words.get(i);

                    tvName.setText(cont);

                    tvName.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            Intent intent = new Intent(FindActivity.this, FindShowActivity.class);

                            /*这个地方注意,,*/
                            TestBean testBean = new TestBean();
                            testBean.setData(cont);
                            LiteOrmSingleSimple.getInstance().insertData(testBean);
                            intent.putExtra("cont", cont);
                            startActivity(intent);

                        }
                    });


                    if (i < 3) {
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
                String content = etFind.getText().toString();
                beandatabase.setData(content);
                arrayListdatabase.add(beandatabase);
                LiteOrmSingleSimple.getInstance().instertData(arrayListdatabase);
                break;


        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter = new FindAdapter();
        lv.setAdapter(adapter);

        LiteOrmSingleSimple.getInstance().queryAllData(TestBean.class, new LiteOrmSingleSimple.OnQueryListenerAll<TestBean>() {
            @Override
            public void onQuery(List<TestBean> T) {

                adapter.setArrayList(T);

            }
        });

    }
}
