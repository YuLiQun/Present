package qunzai.present.hotsale.repeat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import qunzai.present.R;
import qunzai.present.base.BaseFragment;

/**
 * Created by dllo on 16/10/25.
 */
public class HotRepeatFragment extends BaseFragment {

    private TextView tv;
    private static final String key = "pos";//省去key值,,pos位置
    private ImageView imgTit;
    private RecyclerView rv;

    public static HotRepeatFragment getInstance(int pos){
        HotRepeatFragment hotRepeatFragment = new HotRepeatFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(key,pos);
        hotRepeatFragment.setArguments(bundle);

        return hotRepeatFragment;
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_hot_repeat;
    }

    @Override
    protected void initView() {
        imgTit = bindView(R.id.img_hot_repeat_titleimg);
        rv = bindView(R.id.rv_hot_repeat);
//        tv = bindView(R.id.tv_hot_repeat);



    }

    @Override
    protected void initData() {


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        int pos = bundle.getInt(key);
//        tv.setText(String.valueOf(pos));
    }
}
