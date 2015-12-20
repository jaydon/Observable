package com.example.jaydon.observable;

/**
 * 通知中心，用来通知更新数据或者UI，采用单例模式
 * Created by Jaydon on 2015/12/20.
 */
public class Notify {

    private static volatile Notify mNotify;
    private Notify(){

    }

    public static Notify getInstance(){
        if(null == mNotify){
            synchronized (Notify.class) {
                if(null == mNotify) {
                    mNotify =new Notify();
                }
            }
        }
        return mNotify;
    }

    public void NotifyActivity(String eventType){
        EventSubject eventSubject=EventSubject.getInstance();
        EventType eventTypes=EventType.getInstance();
        if(eventTypes.contains(eventType)){
            try {
                eventSubject.notifyObserver(eventType);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

