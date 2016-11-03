package qunzai.present.internet;

/**
 * Created by dllo on 16/10/24.
 * 自定义的请求,直接返回数据类
 * 继承Request
 */

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

public class GsonRequest<T> extends Request<T>{
    //导包  com.google.code.gson:gson:2.7
    //1.
    private final Response.Listener<T> mListener;
    private Gson gson;
    private  Class<T> mClass;



    //在构造的方法里加一个listener,,,,修改
    public GsonRequest(int method,
                       Class<T> mClass,//用于Gson解析的,,数据类的类型,,这个class和上面的不是同一个
                       String url,
                       Response.Listener<T> mListener,//后添加的
                       Response.ErrorListener listener) {
        super(method, url, listener);
        //对成功的监听进行赋值
        this.mListener = mListener;
        gson = new Gson();
        //赋值
        this.mClass = mClass;
    }

    //

    public GsonRequest(
                        Class<T> mClass,//用于Gson解析的,,数据类的类型,,这个class和上面的不是同一个
                        String url,
                        Response.Listener<T> mListener,//后添加的
                        Response.ErrorListener listener) {
        this(Method.GET,mClass, url,mListener, listener);

    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        String parsed;// 请求成功的字符串
        //StringRequest  点进去后复制的
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        //解析
        T t = gson.fromJson(parsed,mClass);
        return Response.success(t,HttpHeaderParser.parseCacheHeaders(response));
    }

    //重写
    //直接返回数据类
    @Override
    protected void deliverResponse(T response) {

        mListener.onResponse(response);
    }
}
