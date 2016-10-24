package qunzai.present.single;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by dllo on 16/10/24.
 * ImageLoader 使用的内存缓存()
 */
public class MemoeyCache implements ImageLoader.ImageCache{
    //1.
    //实现了最近最少使用算法
    //用法和HashMap类似
    private LruCache<String,Bitmap> mCache;

    //2.没有参数的构造
    public MemoeyCache() {
        //缓存的上限是内存的8分之一
        int maxSize = (int) (Runtime.getRuntime().maxMemory() / 8);
        mCache = new LruCache<String, Bitmap>(maxSize){
            //方法sizeOf
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //返回每一个元素占用的大小,单位需要和maxSize保持一致
                return value.getByteCount();//获得每一个图片的占位大小
            }
        };
    }

    //3.
    @Override
    public Bitmap getBitmap(String url) {
        return mCache.get(url);
    }

    //4.
    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        mCache.put(url,bitmap);
    }
}
