package qunzai.present.database;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.WhereBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import qunzai.present.been.TestBean;
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




   //LiteOrm 删除数据库全删方法

    public void deleteAllData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mLiteOrm.delete(TestBean.class);
                // mLiteOrm.deleteDatabase();  //库文件一起干掉
                // mLiteOrm.openOrCreateDatabase();  //重建一个数据库
                // 这个方法当库里只有一个表的时候可以用但是如果表多的话会全删光的
            }
        }).start();
    }

    //LiteOrm 删除数据库定向删方法
    public void deleteSpecifyData(final String data){
        mExcutorPool.execute(new Runnable() {
            @Override
            public void run() {
                mLiteOrm.delete(new WhereBuilder(TestBean.class).where("data = ?",data));
            }
        });
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

    public <T> void insertData(final T t){
        mExcutorPool.execute(new Runnable() {
            @Override
            public void run() {
                //insertDB(t);
                mLiteOrm.insert(t);
            }
        });
    }

    /*LiteOrm */
    private <T> void insertDB(List<T> t) {
        Log.d("cccc", "t.size():" + t.size());
        mLiteOrm.insert(t);
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
