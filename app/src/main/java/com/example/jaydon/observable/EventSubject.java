package com.example.jaydon.observable;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 具体的主题角色的实现，这里用来监听事件的发生，采用单例模式来实现
 * Created by Jaydon on 2015/12/20.
 */
public class EventSubject implements EventSubjectInterface{
//    private List<EventObserver> mEventObservers = new ArrayList<EventObserver>();
    private static final Map<String, CopyOnWriteArrayList<EventObserver>> mEventObservers = new HashMap<String, CopyOnWriteArrayList<EventObserver>>(); //每个事件的父类、接口
    private static volatile EventSubject mEventSubject;
    private EventSubject(){
    }

    public static EventSubject getInstance(){
        if(null == mEventSubject){
            synchronized (EventSubject.class) {
                if(null == mEventSubject) {
                    mEventSubject =new EventSubject();
                }
            }
        }
        return mEventSubject;
    }

    @Override
    public void registerObserver(EventObserver observer, String eventType) throws Exception {
        if(TextUtils.isEmpty(eventType) || null == observer) {
            throw new Exception("参数不正确");
        }
        synchronized (mEventObservers){
            CopyOnWriteArrayList<EventObserver> observers = mEventObservers.get(eventType);
            if(null == observers) {
                observers = new CopyOnWriteArrayList<>();
                mEventObservers.put(eventType, observers);
            }
            if(observers.contains(observer)){
                throw new Exception("已经注册过");
            }
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(EventObserver observer, String eventType) throws Exception {
        if(TextUtils.isEmpty(eventType) || null == observer) {
            throw new Exception("参数不正确");
        }
        synchronized (mEventObservers){
            CopyOnWriteArrayList<EventObserver> observers = mEventObservers.get(eventType);
            if(null == observers) {
                throw new Exception("未注册过");
            }
            int index = observers.indexOf(observer);
            if (index >= 0) {
                mEventObservers.remove(observer);
            }
        }
    }

    @Override
    public void notifyObserver(String eventType) throws Exception {
        if(TextUtils.isEmpty(eventType)) {
            throw new Exception("参数不正确");
        }
        CopyOnWriteArrayList<EventObserver> observers = mEventObservers.get(eventType);
        if(null == observers) {
            throw new Exception("未注册过");
        }
        if(mEventObservers!=null && mEventObservers.size()>0){
            for(EventObserver observer : observers){
                observer.dispatchChange(eventType);
            }
        }

    }
}

