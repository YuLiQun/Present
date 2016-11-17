package qunzai.present.internet;

import android.app.Application;
import android.content.Context;

import cn.bmob.v3.Bmob;
import cn.sharesdk.framework.ShareSDK;

/**
 * Created by dllo on 16/10/24.
 */
//防止内存泄漏做的防范措施  继承与Context
//生命周期:整个应用
//启动顺序->Application->四大组件(Application包含四大组件)



/**
 * 写完一定得注册,,一定,,一定,,,(如果不写onCreate不走)
 *
 * 与界面无关的都可以实用,,例如LayoutInflater,,就不行,,,不能用来填充界面,注入布局
 */

public class MyApp extends Application {
    //1.静态的Contxt
    private static Context mContext;

    //2.
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        //这个得在Application 里初始化一下,,记得哦
        Bmob.initialize(this, "ed0266817d5d3ef041ce5f6fa4a1eead");
        ShareSDK.initSDK(this,"19222bec8cbb4");
    }

    //3.Context的get方法
    public static Context getmContext() {
        return mContext;
    }
}
