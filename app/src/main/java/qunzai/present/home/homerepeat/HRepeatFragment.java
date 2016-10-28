package qunzai.present.home.homerepeat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import qunzai.present.R;
import qunzai.present.base.BaseFragment;
import qunzai.present.been.HSelectionBean;
import qunzai.present.been.HomeTitleBean;
import qunzai.present.gson.GsonRequsest;
import qunzai.present.single.VolleySingleSimple;

/**
 * Created by dllo on 16/10/25.
 */
public class HRepeatFragment extends BaseFragment {

    private static final String Key ="homerepeat";
    private ListView lv;
    //    private TextView tv;


    public static HRepeatFragment getInstance(int pos ,int id){
        HRepeatFragment hRepeatFragment = new HRepeatFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Key,pos);
        bundle.putInt(Key,id);
        Log.d("zzz", "++pos:" + pos);
        hRepeatFragment.setArguments(bundle);

        return hRepeatFragment;
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_home_repeat;
    }

    @Override
    protected void initView() {

//        tv = bindView(R.id.tv);
        lv = bindView(R.id.lv_home_repeat);

    }

    @Override
    protected void initData() {

        initGsonData();

    }

    private void initGsonData() {
//        GsonRequsest<HSelectionBean> requsest = new GsonRequsest<HSelectionBean>(HSelectionBean.class,
//                )
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        int pos = bundle.getInt(Key);
//        switch (pos){
//            case 1:
//                break;
//            case 2:
//                break;
//            case 3:
//                break;
//            case 4:
//                break;
//            case 5:
//                break;
//            case 6:
//                break;
//            case 7:
//                break;
//            case 8:
//                break;
//            case 9:
//                break;
//            case 10:
//                break;
//
//        }
//        tv.setText(String.valueOf(pos));


    }
}
