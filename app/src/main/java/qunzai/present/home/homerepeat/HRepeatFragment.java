package qunzai.present.home.homerepeat;

import android.os.Bundle;
import android.support.annotation.Nullable;

import qunzai.present.R;
import qunzai.present.base.BaseFragment;

/**
 * Created by dllo on 16/10/25.
 */
public class HRepeatFragment extends BaseFragment {

    private static final String Key ="homerepeat";



    public static HRepeatFragment getInstance(int pos){
        HRepeatFragment hRepeatFragment = new HRepeatFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Key,pos);
        hRepeatFragment.setArguments(bundle);

        return hRepeatFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            int type = (int) getArguments().getSerializable(Key);
        }

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home_repeat;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
