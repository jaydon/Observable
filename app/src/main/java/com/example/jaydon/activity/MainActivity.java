package com.example.jaydon.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.example.jaydon.R;
import com.example.jaydon.observable.EventObserver;
import com.example.jaydon.observable.EventSubject;
import com.example.jaydon.observable.EventType;
import com.example.jaydon.observable.Notify;

public class MainActivity extends BaseActivity {
    private ActivityEventObserver mActivityEventObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivityEventObserver = new ActivityEventObserver(this);
        registerObserver(mActivityEventObserver);
        initView();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Notify.getInstance().NotifyActivity(EventType.UPDATE_TEXT);
                Notify.getInstance().NotifyActivity(EventType.UPDATE_MAIN);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeObserver(mActivityEventObserver);
    }


    public void registerObserver(EventObserver observer) {
        final String[] observerEventTypes = getObserverEventType();//获取所有需要监听的业务类型
        final EventSubject eventSubject = EventSubject.getInstance();
        if(null != observerEventTypes && observerEventTypes.length > 0){
            for(String eventType : observerEventTypes) {
                try {
                    eventSubject.registerObserver(observer, eventType);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void removeObserver(EventObserver observer) {
        final String[] observerEventTypes = getObserverEventType();//获取所有需要监听的业务类型
        final EventSubject eventSubject = EventSubject.getInstance();
        if(null != observerEventTypes && observerEventTypes.length > 0){
            for(String eventType : observerEventTypes) {
                try {
                    eventSubject.removeObserver(observer, eventType);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    protected void onChange(String eventType) {
        if(EventType.UPDATE_MAIN==eventType){
            showShortToast(EventType.UPDATE_MAIN);
        }else if(EventType.UPDATE_TEXT==eventType){
            showShortToast(EventType.UPDATE_TEXT);
        }
    }

    @Override
    protected String[] getObserverEventType() {
        return new String[]{
                EventType.UPDATE_MAIN,
                EventType.UPDATE_TEXT
        };
    }

}
