package com.example.jaydon.activity;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.example.jaydon.R;
import com.example.jaydon.observable.EventObserver;
import java.lang.ref.WeakReference;

/**
 * Activity基类
 * Created by Jaydon on 2015/12/20.
 */
public abstract class BaseActivity extends AppCompatActivity{
    protected String TAG = this.getClass().getSimpleName();

    /**
     * 该方法会在具体的观察者对象中调用，可以根据事件的类型来更新对应的UI，这个方法在UI线程中被调用，
     * 所以在该方法中不能进行耗时操作，可以另外开线程
     * @param eventType 事件类型
     */
    protected abstract void onChange(String eventType);

    /**
     * 通过这个方法来告诉具体的观察者需要监听的业务类型
     * @return
     */
    protected abstract String[] getObserverEventType();

    static class ActivityEventObserver extends EventObserver {
        //添加弱引用，防止对象不能被回收
        private final WeakReference<BaseActivity> mActivity;
        public ActivityEventObserver(BaseActivity activity){
            super();
            mActivity = new WeakReference<BaseActivity>(activity);
        }

        @Override
        public void onChange(String eventType) {
            BaseActivity activity = mActivity.get();
            if(activity!=null){
                activity.onChange(eventType);
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishActivity();
    }

    public void finishActivity() {
        finish();
        //定义关闭Activity动画
        overridePendingTransition(0, R.anim.push_right_out);
    }

    /**
     * 显示长Toast
     *
     * @param tip
     */
    public void showLongToast(String tip) {
        Toast.makeText(this, tip, Toast.LENGTH_LONG).show();
    }

    /**
     * 显示短Toast
     *
     * @param tip
     */
    public void showShortToast(String tip) {
        Toast.makeText(this, tip, Toast.LENGTH_SHORT).show();
    }

    /**
     * 网络不可用提示
     */
    public void showNetFailToast() {
        showShortToast(getResources().getString(R.string.common_net_not_available));
    }
}
