package qunzai.present.single;

import android.graphics.Bitmap;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

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

    //1.
    private VolleySingleSimple() {
        //5.创建请求队列 RequestQueue
        //6.创建一个类,,MyApp
        //7.创建一个类
        mRequestQueue = Volley.newRequestQueue(MyApp.getmContext());
        //7.初始化ImageLoade ,, 用啦i 请求图片的
        //7.创建一个类MemoeyCache ,,, 用来缓存
        imageLoader = new ImageLoader(mRequestQueue,new MemoeyCache());

    }
    //3.
    public static VolleySingleSimple getInstance(){
        if (volleySingleSimple == null){
            synchronized (VolleySingleSimple.class){
                if (volleySingleSimple == null){
                    volleySingleSimple = new VolleySingleSimple();
                }
            }
        }

        return volleySingleSimple;
    }

    //8.请求图片
    public void getImage(String url, ImageView imageView){
        //9.RequestQueue 的构造方法
        //10.ImageLoader
        imageLoader.get(url,new AnimImageListener(imageView));
    }

    //9..RequestQueue 的构造方法
    public RequestQueue getmRequestQueue() {
        return mRequestQueue;
    }

    //11.添加请求队列
    //指定泛型
    public <T>void addRequest(Request<T> request){
        mRequestQueue.add(request);
    }



    //9.
    class AnimImageListener implements ImageLoader.ImageListener{
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
            if (bitmap == null){
                //图片还在请求中
                imageView.setImageResource(R.mipmap.image_default);
            }else {
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
}
