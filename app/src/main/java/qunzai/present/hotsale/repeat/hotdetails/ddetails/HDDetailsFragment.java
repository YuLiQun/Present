package qunzai.present.hotsale.repeat.hotdetails.ddetails;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import qunzai.present.R;
import qunzai.present.base.BaseFragment;
import qunzai.present.been.HotSaleRepeatBean;

/**
 * Created by dllo on 16/11/5.
 */
public class HDDetailsFragment extends BaseFragment {


    private static final String KEY = "HDDetails";
    private static final String URL = "HDDetailsUrl";
    private WebView web;

    public static HDDetailsFragment getInstance(int pos , String url){
        HDDetailsFragment hotRepeatFragment = new HDDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY,pos);
        bundle.putString(URL,url);
        hotRepeatFragment.setArguments(bundle);

        return hotRepeatFragment;
    }
    @Override
    protected int getLayout() {
        return R.layout.fragment_hd_details;
    }

    @Override
    protected void initView() {
        web = bindView(R.id.web_hot_done_details);

    }

    @Override
    protected void initData() {

        Bundle bundle = getArguments();
        String url = bundle.getString(URL);


        web.getSettings().setUseWideViewPort(true);
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setJavaScriptEnabled(false);
        web.loadUrl(url);
    }
}
