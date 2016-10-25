package qunzai.present.home.homeselection;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/25.
 */
public class HomeSelectionAdapter extends BaseAdapter{
    Context context;


    public HomeSelectionAdapter(Context context) {
        this.context = context;
    }



    @Override
    public int getCount() {
        return 0 ;
    }

    @Override
    public Object getItem(int position) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
