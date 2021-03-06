package qunzai.present.home.wheel;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import qunzai.present.R;
import qunzai.present.internet.VolleySingleSimple;

/**
 * Created by dllo on 16/10/25.
 */
public class HSWheelAdapter extends PagerAdapter{


    private List<ImageView> mList;
    //这个里存的是网址
    ArrayList<String> imgArr;

    public HSWheelAdapter(List<ImageView> list) {
        this.mList = list;
    }

    public HSWheelAdapter(ArrayList<String> imgArr) {
        this.imgArr = imgArr;
    }

    //这个方法是决定了有多少页的图片
    @Override
    public int getCount() {
        //Integer.MAX_VALUE;   一个无限大的数
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //  Log.d("Sysout", "position:" + position);
        long startTime = System.currentTimeMillis();
        ImageView imageView = new ImageView(container.getContext());
        imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        container.addView(imageView);
        //
        String img = imgArr.get(position % imgArr.size());
        imageView.setImageResource(R.mipmap.ic_launcher);
        VolleySingleSimple.getInstance().getImage(img, imageView);
        long endTime = System.currentTimeMillis();
        Log.d("Sysout", "endTime - startTime:" + (endTime - startTime));
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        try {
            container.removeViewAt(position);
        } catch (Exception e) {

        }
    }

}
