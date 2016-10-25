package qunzai.present.home.homerepeat;

import android.os.Bundle;
import android.support.annotation.Nullable;

import qunzai.present.R;
import qunzai.present.base.BaseFragment;

/**
 * Created by dllo on 16/10/25.
 */
public class HomeRepeatFragment extends BaseFragment {

    private static final String Key ="homerepeat";



    public static HomeRepeatFragment getInstance(int pos){
        HomeRepeatFragment homeRepeatFragment = new HomeRepeatFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Key,pos);
        homeRepeatFragment.setArguments(bundle);

        return homeRepeatFragment;
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
}
