package qunzai.present.database;

import android.os.Handler;
import android.os.Looper;

import com.litesuits.orm.LiteOrm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import qunzai.present.internet.MyApp;

/**
 * Created by dllo on 16/11/8.
 */
public class LiteOrmSingleSimple {

    private static LiteOrmSingleSimple liteOrmSingleSimple;
    private LiteOrm mLiteOrm;/*LiteOrm*/
    private Executor mExcutorPool;/*线程池*/
    private Handler mHandler;/*用来做线程之间的切换的*/

    private LiteOrmSingleSimple() {


         /*LiteOrm*/
        mLiteOrm = LiteOrm.newCascadeInstance(MyApp.getmContext(), "perent.db");
        /***Looper.getMainLooper()** :写这个代码,,,类保证Handler一定属于主线程*/
        mHandler = new Handler(Looper.getMainLooper());
        int cpuMore = Runtime.getRuntime().availableProcessors();
        /*newFixedThreadPool 任务队列是无限的*/
        mExcutorPool = Executors.newFixedThreadPool(cpuMore + 1);
    }

    //3.
    public static LiteOrmSingleSimple getInstance() {
        if (liteOrmSingleSimple == null) {
            synchronized (LiteOrmSingleSimple.class) {
                if (liteOrmSingleSimple == null) {
                    liteOrmSingleSimple = new LiteOrmSingleSimple();
                }
            }
        }

        return liteOrmSingleSimple;
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
    public <T> void queryAllData(Class<T> tClass,OnQueryListenerAll<T> onQueryListener) {

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
