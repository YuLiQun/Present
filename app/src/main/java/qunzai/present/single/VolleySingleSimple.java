package qunzai.present.single;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.litesuits.orm.LiteOrm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import qunzai.present.R;

/**
 * Created by dllo on 16/10/24.
 * Volley 的单例模式
 */
public class VolleySingleSimple {

    //4.导一个volley的包(2015.5.18版本的)

    //2.
    private static VolleySingleSimple volleySingleSimple;
    private final ImageLoader imageLoader;
    private final RequestQueue mRequestQueue;
    private LiteOrm mLiteOrm;/*LiteOrm*/
    private Executor mExcutorPool;/*线程池*/
    private Handler mHandler;/*用来做线程之间的切换的*/

    //1.
    private VolleySingleSimple() {
        //5.创建请求队列 RequestQueue
        //6.创建一个类,,MyApp
        //7.创建一个类
        mRequestQueue = Volley.newRequestQueue(MyApp.getmContext());
        //7.初始化ImageLoade ,, 用啦i 请求图片的
        //7.创建一个类MemoeyCache ,,, 用来缓存
        imageLoader = new ImageLoader(mRequestQueue, new MemoeyCache());

        /*LiteOrm*/
        mLiteOrm = LiteOrm.newCascadeInstance(MyApp.getmContext(), "perent.db");
        /***Looper.getMainLooper()** :写这个代码,,,类保证Handler一定属于主线程*/
        mHandler = new Handler(Looper.getMainLooper());
        int cpuMore = Runtime.getRuntime().availableProcessors();
        /*newFixedThreadPool 任务队列是无限的*/
        mExcutorPool = Executors.newFixedThreadPool(cpuMore + 1);


    }

    //3.
    public static VolleySingleSimple getInstance() {
        if (volleySingleSimple == null) {
            synchronized (VolleySingleSimple.class) {
                if (volleySingleSimple == null) {
                    volleySingleSimple = new VolleySingleSimple();
                }
            }
        }

        return volleySingleSimple;
    }

    //8.请求图片
    public void getImage(String url, ImageView imageView) {
        //9.RequestQueue 的构造方法
        //10.ImageLoader
        imageLoader.get(url, new AnimImageListener(imageView));
    }

    //9..RequestQueue 的构造方法
    public RequestQueue getmRequestQueue() {
        return mRequestQueue;
    }

    //11.添加请求队列
    //指定泛型
    public <T> void addRequest(Request<T> request) {
        mRequestQueue.add(request);
    }


    //9.
    class AnimImageListener implements ImageLoader.ImageListener {
        /*1.*/
        private ImageView imageView;
        /*2.构造*/

        public AnimImageListener(ImageView imageView) {
            this.imageView = imageView;
        }

        /*3.*/
        @Override
        public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
            Bitmap bitmap = response.getBitmap();
            if (bitmap == null) {
                //图片还在请求中
                imageView.setImageResource(R.mipmap.image_default);
            } else {
                //图片请求成功
                imageView.setImageBitmap(bitmap);

//                //加入添加动画效果(渐变进入)
//                AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
//                alphaAnimation.setDuration(2000);
//                imageView.setAnimation(alphaAnimation);
//                alphaAnimation.start();
            }
        }

        @Override
        public void onErrorResponse(VolleyError error) {

        }
    }

    /*LiteOrm 的存数据方法*/
    public <T> void instertData(final List<T> tList) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                insertDB(tList);
            }
        }).start();

    }

    /*LiteOrm */
    private <T> void insertDB(List<T> T) {
        mLiteOrm.insert(T);
    }

    /*LiteOrm 的查询数据方法*/
    public <T> void queryAllData(OnQueryListenerAll<T> onQueryListener, Class<T> tClass) {

        mExcutorPool.execute(new QueryRunnable<T>(onQueryListener, tClass));

    }

    /*LiteOrm 外部的子线程*/
    class QueryRunnable<T> implements Runnable {
        private OnQueryListenerAll<T> mOnQueryListener;
        private Class<T> tClass;

        //带有两个参数的构造
        public QueryRunnable(OnQueryListenerAll<T> mOnQueryListener, Class<T> tClass) {
            this.mOnQueryListener = mOnQueryListener;
            this.tClass = tClass;
        }

        @Override
        public void run() {
            ArrayList<T> query = mLiteOrm.query(tClass);
            mHandler.post(new CallBackRunnable<T>(mOnQueryListener, query));
        }
    }

    /*LiteOrm */
    class CallBackRunnable<T> implements Runnable {
        OnQueryListenerAll<T> mOnQueryListener;
        List<T> tList;

        //带有两个参数的构造
        public CallBackRunnable(OnQueryListenerAll<T> mOnQueryListener, List<T> tList) {
            this.mOnQueryListener = mOnQueryListener;
            this.tList = tList;
        }

        @Override
        public void run() {
            mOnQueryListener.onQuery(tList);
        }
    }

    /*LiteOrm */
    public interface OnQueryListenerAll<T> {
        void onQuery(List<T> T);
    }


}
